package com.example.web.controller;

import com.example.web.Config.BcryptedPasswordEncoderConfig;
import com.example.web.model.ChucVu;
import com.example.web.model.NhanVien;
import com.example.web.response.NhanVienFilter;
import com.example.web.response.SanPhamFilter;
import com.example.web.service.IChucVuService;
import com.example.web.service.INhanVienService;
import com.example.web.service.MailService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import com.example.web.util.RandomUntil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;
import java.util.Optional;
import java.util.UUID;

@Controller
@RequestMapping("/admin/nhan-vien")
public class NhanVienController {

    @Autowired
    private INhanVienService nhanVienService;

    @Autowired
    private IChucVuService chucVuService;

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private BcryptedPasswordEncoderConfig passwordEncoder;

    @Autowired
    private MailService mailService;

    Page<NhanVien> nhanVienPage = null;

    @GetMapping("/hien-thi")
    public String hienThi(Model model, @RequestParam(defaultValue = "1") Integer page){
        Sort sort = Sort.by("ngayTao").descending();
        Pageable pageable = PageRequest.of(page - 1, 10, sort);
        nhanVienPage = nhanVienService.getAll(pageable);
        model.addAttribute("listNhanVien", nhanVienPage);
        model.addAttribute("filterNhanVien", new NhanVienFilter());
        model.addAttribute("url", "/admin/nhan-vien/hien-thi?page=");
        return "quanLyTaiKhoan/nhanVien/hien-thi";
    }

    @GetMapping("/filter")
    public String filterNhanVien(Model model, @RequestParam(defaultValue = "1") Integer page,
                                 @ModelAttribute("filterNhanVien") NhanVienFilter filter){
        Sort sort = Sort.by("ngayTao").descending();
        Pageable pageable = PageRequest.of(page - 1, 10, sort);
        nhanVienPage = nhanVienService.nhanVienFilter(filter, pageable);
        String url = "/admin/nhan-vien/filter?" + request.getQueryString().replaceAll("[&?]page.*?(?=&|\\?|$)", "") + "&page=";
        model.addAttribute("filter", filter);
        model.addAttribute("listNhanVien", nhanVienPage);
        model.addAttribute("url", url);
        return "quanLyTaiKhoan/nhanVien/hien-thi";
    }

    @GetMapping("/view-add")
    public String viewAdd(Model model){
        model.addAttribute("listChucVu", chucVuService.getAll1());
        model.addAttribute("chucVu", new ChucVu());
        model.addAttribute("nhanVien", new NhanVien());
        return "quanLyTaiKhoan/nhanVien/add";
    }

    @PostMapping("/add")
    public String add(@Valid @ModelAttribute("nhanVien") NhanVien nhanVien, BindingResult result, Model model){
        char[] password = RandomUntil.randomFull();
        NhanVien checkEmail = nhanVienService.checkEmail(nhanVien.getEmail());
        NhanVien checkTaiKhoan = nhanVienService.checkTaiKhoan(nhanVien.getTaiKhoan());
        NhanVien checkSDT = nhanVienService.findBySDT(nhanVien.getSdt());

        if(result.hasErrors()){
            model.addAttribute("chucVu", new ChucVu());
            model.addAttribute("listChucVu", chucVuService.getAll1());
            return "quanLyTaiKhoan/nhanVien/add";
        }
        if (checkTaiKhoan != null) {
            result.rejectValue("taiKhoan", "error.nhanVien", "Tài khoản đã tồn tại.");
            model.addAttribute("chucVu", new ChucVu());
            model.addAttribute("listChucVu", chucVuService.getAll1());
            return "quanLyTaiKhoan/nhanVien/add";
        }
        if (checkEmail != null) {
            result.rejectValue("email", "error.nhanVien", "Email này đã được sử dụng");
            model.addAttribute("chucVu", new ChucVu());
            model.addAttribute("listChucVu", chucVuService.getAll1());
            return "quanLyTaiKhoan/nhanVien/add";
        }
        if (checkSDT != null) {
            result.rejectValue("sdt", "error.nhanVien", "Số điện thoại này đã được sử dụng");
            model.addAttribute("chucVu", new ChucVu());
            model.addAttribute("listChucVu", chucVuService.getAll1());
            return "quanLyTaiKhoan/nhanVien/add";
        }
        Date date = java.util.Calendar.getInstance().getTime();
        nhanVien.setNgayTao(date);
        System.out.println(password);
        nhanVien.setMatKhau(passwordEncoder.encode(new String(password)));
        mailService.sendMail("sportsclothing46@gmail.com",
                nhanVien.getEmail(),
                "Chúc mừng bạn đã ứng tuyển thành công.",
                "Tên đăng nhập: " + nhanVien.getTaiKhoan() + "\n"
                        + "Mật khẩu : " + new String(password) + "\n\n"
                        + "Họ tên  : " + nhanVien.getHoTen() + "\n"
                        + "Số điện thoại  :" + nhanVien.getSdt() + "\n\n"
                        + "Chúc bạn ngày đầu đi làm vui vẻ.");
        nhanVienService.add(nhanVien);
        return "redirect:/admin/nhan-vien/hien-thi";
    }

    @PostMapping("/update/{id}")
    public String update(@Valid @ModelAttribute("nhanVien") NhanVien nhanVien, BindingResult result, @PathVariable String id, Model model){
        NhanVien nv = nhanVienService.findById(UUID.fromString(id));
        if(result.hasErrors()){
            model.addAttribute("chucVu", new ChucVu());
            model.addAttribute("listChucVu", chucVuService.getAll1());
            return "quanLyTaiKhoan/nhanVien/update";
        }else{
            Date date = java.util.Calendar.getInstance().getTime();
            nhanVien.setId(nv.getId());
            nhanVien.setNgayTao(nv.getNgayTao());
            nhanVien.setNgaySua(date);
            nhanVien.setMatKhau(nv.getMatKhau());
            nhanVienService.update(UUID.fromString(id), nhanVien);
            return "redirect:/admin/nhan-vien/hien-thi";
        }
    }

    @GetMapping("/view-update/{id}")
    public String hienThi(@PathVariable String id, Model model, @ModelAttribute("chucVu") ChucVu chucVu){
        NhanVien nv = nhanVienService.findById(UUID.fromString(id));
        model.addAttribute("listChucVu", chucVuService.getAll1());
        model.addAttribute("nhanVien", nv);
        return "quanLyTaiKhoan/nhanVien/update";
    }

    @PostMapping("/modal-add-chuc-vu")
    public String addCV(@ModelAttribute("chucVu") ChucVu chucVu){
        UUID uuid = UUID.randomUUID();
        Date date = java.util.Calendar.getInstance().getTime();
        chucVu.setId(uuid);
        chucVu.setTrangThai(0);
        chucVu.setNgayTao(date);
        chucVuService.add(chucVu);
        return "redirect:/admin/nhan-vien/view-add";
    }

    @PostMapping("/modal-update-chuc-vu")
    public String addChucVu(@ModelAttribute("chucVu") ChucVu chucVu, @RequestParam("id") String id){
        NhanVien nv = nhanVienService.findById(UUID.fromString(id));
        UUID uuid = UUID.randomUUID();
        Date date = java.util.Calendar.getInstance().getTime();
        chucVu.setId(uuid);
        chucVu.setTrangThai(0);
        chucVu.setNgayTao(date);
        chucVuService.add(chucVu);
        return "redirect:/admin/nhan-vien/view-update/" + nv.getId();
    }

    @GetMapping("/stop/{id}")
    public String stop(@PathVariable("id") UUID id){
        NhanVien sp = nhanVienService.findById(id);
        if (sp.getTrangThai()==0){
            sp.setTrangThai(1);
        }else {
            sp.setTrangThai(0);
        }
        sp.setNgaySua(java.util.Calendar.getInstance().getTime());
        nhanVienService.update(id, sp);
        return "redirect:/admin/nhan-vien/hien-thi";
    }
}
