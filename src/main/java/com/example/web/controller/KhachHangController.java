package com.example.web.controller;
import com.example.web.Config.BcryptedPasswordEncoderConfig;
import com.example.web.model.DiaChi;
import com.example.web.model.KhachHang;
import com.example.web.request.NewDiaChiOnline;
import com.example.web.response.KhachHangFilter;
import com.example.web.service.IKhachHangService;
import com.example.web.service.impl.DiaChiServiceImpl;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import com.example.web.service.MailService;
import com.example.web.util.RandomUntil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import java.util.Date;
import java.util.UUID;

@Controller
@RequestMapping("/admin/khach-hang")
public class KhachHangController {

    @Autowired
    private IKhachHangService khachHangService;

    Page<KhachHang> khachHangPage = null;

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private BcryptedPasswordEncoderConfig passwordEncoder;

    @Autowired
    private MailService mailService;

    @Autowired
    private DiaChiServiceImpl diaChiService;

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping("/detail")
    public String getKhachHangs(@RequestParam String id , @RequestParam String idHD , RedirectAttributes attributes){
         KhachHang khachHang =  khachHangService.getKhachHangById(id);
         attributes.addFlashAttribute("khachHang" , khachHang);
         return "redirect:/admin/hoa-don/detail?idHD="+idHD ;
    }

    @GetMapping("/api-hien-thi")
    @ResponseBody
    public Page<KhachHang> getKhachHangs(@RequestParam Integer page , @RequestParam(required = false) String value){
        Page listKhachHang = null;
        if(value.isEmpty()){
            listKhachHang = khachHangService.getAll(page);
        }else{
            listKhachHang =  khachHangService.getKhachHangByHoTenOrSdtOrTaiKhoan(value, page);
        }
        return listKhachHang;
    }

    @PostMapping("/create")
    @ResponseBody
    public KhachHang createKhachHang(@RequestBody KhachHang khachHang){
        char[] password = RandomUntil.randomFull();
        Date date = java.util.Calendar.getInstance().getTime();
        khachHang.setTrangThai(0);
        khachHang.setNgayTao(date);
        khachHang.setMatKhau(passwordEncoder.encode(new String(password)));
        mailService.sendMail("sportsclothing46@gmail.com",
                khachHang.getEmail(),
                "Chúc mừng bạn đã đến với Sport Clothing.",
                "Tên đăng nhập: " + khachHang.getTaiKhoan() + "\n"
                        + "Mật khẩu : " + new String(password) + "\n\n"
                        + "Họ tên  : " + khachHang.getHoTen() + "\n"
                        + "Số điện thoại  :" + khachHang.getSdt() + "\n\n");
      return  khachHangService.themMoiKhachHang(khachHang);
    }
//    /----------------------------------------------------------/
    @GetMapping("/hien-thi")
    public String hienThi(Model model, @RequestParam(defaultValue = "1") Integer page){
        khachHangPage =khachHangService.getAll(page);
        model.addAttribute("lst", khachHangPage);
        model.addAttribute("filterKhachHang", new KhachHangFilter());
        model.addAttribute("url", "/admin/khach-hang/hien-thi?page=");
        return "quanLyTaiKhoan/khachHang/hien-thi";
    }

    @GetMapping("/filter")
    public String filterNhanVien(Model model, @RequestParam(defaultValue = "1") Integer page,
                                 @ModelAttribute("filterKhachHang") KhachHangFilter filter){
        Sort sort = Sort.by("ngayTao").descending();
        Pageable pageable = PageRequest.of(page - 1, 10, sort);
        khachHangPage = khachHangService.khachHangFillter(filter, pageable);
        String url = "/admin/khach-hang/filter?" + request.getQueryString().replaceAll("[&?]page.*?(?=&|\\?|$)", "") + "&page=";
        model.addAttribute("filter", filter);
        model.addAttribute("lst", khachHangPage);
        model.addAttribute("url", url);
        return "quanLyTaiKhoan/khachHang/hien-thi";
    }

    @GetMapping("/view-add")
    public String viewAdd(Model model){
        model.addAttribute("khachHang", new KhachHang());
        return "quanLyTaiKhoan/khachHang/add";
    }

    @PostMapping("/add")
    public String add(@Valid @ModelAttribute("khachHang") KhachHang khachHang, BindingResult result, Model model, RedirectAttributes attributes){
        char[] password = RandomUntil.randomFull();

        if(result.hasErrors()){
            return "quanLyTaiKhoan/khachHang/add";
        }
        if (khachHangService.findByTaiKhoan(khachHang.getTaiKhoan())!=null){
            model.addAttribute("taiK","Tài khoản đã tồn tại");
            return "quanLyTaiKhoan/khachHang/add";
        }
        if (khachHangService.findByEmail(khachHang.getEmail())!=null){
            model.addAttribute("email","Email này đã được sử dụng");
            return "quanLyTaiKhoan/khachHang/add";
        }
        if (khachHangService.findBySdt(khachHang.getSdt())!=null){
            model.addAttribute("sdt","SĐT này đã được sử dụng");
            return "quanLyTaiKhoan/khachHang/add";
        }
            Date date = java.util.Calendar.getInstance().getTime();
            khachHang.setTrangThai(0);
            khachHang.setNgayTao(date);
            khachHang.setMatKhau(passwordEncoder.encode(new String(password)));
        mailService.sendMail("sportsclothing46@gmail.com",
                khachHang.getEmail(),
                "Chúc mừng bạn đã đến với Sport Clothing.",
                "Tên đăng nhập: " + khachHang.getTaiKhoan() + "\n"
                        + "Mật khẩu : " + new String(password) + "\n\n"
                        + "Họ tên  : " + khachHang.getHoTen() + "\n"
                        + "Số điện thoại  :" + khachHang.getSdt() + "\n\n");
            khachHangService.add(khachHang);
        attributes.addFlashAttribute("success", "Tạo dữ liệu thành công");
            return "redirect:/admin/khach-hang/hien-thi";
    }

    @PostMapping("/update/{id}")
    public String update(@Valid @ModelAttribute("khachHang") KhachHang khachHang, BindingResult result, @PathVariable String id,
                         Model model, RedirectAttributes attributes){
        KhachHang kh = khachHangService.findById(UUID.fromString(id));
        if(result.hasErrors()) {
            model.addAttribute("lstdChi", diaChiService.getDiaChiByKhachHang_id(UUID.fromString(id)));
            return "quanLyTaiKhoan/khachHang/update";
        }
            Date date = java.util.Calendar.getInstance().getTime();
            khachHang.setId(kh.getId());
            khachHang.setTrangThai(kh.getTrangThai());
            khachHang.setMatKhau(kh.getMatKhau());
            khachHang.setNgayTao(kh.getNgayTao());
            khachHang.setNgaySua(date);
            khachHangService.update(khachHang);
            attributes.addFlashAttribute("success", "Sửa dữ liệu thành công");
            return "redirect:/admin/khach-hang/hien-thi";
    }

    @GetMapping("/view-update/{id}")
    public String hienThi(@PathVariable String id, Model model){
        KhachHang kh = khachHangService.findById(UUID.fromString(id));
        model.addAttribute("khachHang", kh);
        model.addAttribute("lstdChi", diaChiService.getDiaChiByKhachHang_id(UUID.fromString(id)));
        return "quanLyTaiKhoan/khachHang/update";
    }


    @GetMapping("/update-status/{id}")
    public String page(@PathVariable("id") String id, RedirectAttributes redirectAttributes,
                       @RequestParam(name = "trangThai")Integer trangThai ){
        redirectAttributes.addFlashAttribute("success", "Cập nhật trạng thái thành công");
        String url = khachHangService.updateStatus(id,trangThai);
        return url;
    }

    @GetMapping("/them-dia-chi/{id}")
    public String newDiaChi(@RequestParam String hoTen, @PathVariable("id") String idKH,
                            @RequestParam String sdt,@Valid DiaChi diaChi, BindingResult result,
                            @RequestParam("diaChi") String diaChiNhan, RedirectAttributes attributes) {
        KhachHang khachHang = khachHangService.getKhachHangById(idKH);
        if(result.hasErrors()){
            attributes.addFlashAttribute("error", "Thêm dữ liệu thất bại");
        }else{
            diaChi = new DiaChi();
            diaChi.setHoTen(hoTen);
            diaChi.setDiaChi(diaChiNhan);
            diaChi.setSdt(sdt);
            diaChi.setHoTen(hoTen);
            diaChi.setKhachHang(khachHang);
            diaChi.setDiaChiMacDinh(false);
            diaChiService.add(diaChi);
            attributes.addFlashAttribute("success", "Thêm dữ liệu thành công");
        }
        return "redirect:/admin/khach-hang/view-update/"+khachHang.getId();
    }
    @GetMapping("/xoa-dia-chi/{id}")
    public String xoaDiaChi(@PathVariable("id") String id,
                            @RequestParam String idKH, RedirectAttributes attributes) {
        diaChiService.delete(UUID.fromString(id));
        attributes.addFlashAttribute("success", "Xóa dữ liệu thành công");
        return "redirect:/admin/khach-hang/view-update/"+idKH;
    }
}
