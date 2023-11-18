package com.example.web.controller.hoaDon.donTaiQuay;

import com.example.web.controller.SanPhamController;
import com.example.web.model.HoaDon;
import com.example.web.model.KhachHang;
import com.example.web.model.LichSuHoaDon;
import com.example.web.model.TrangThaiHoaDon;
import com.example.web.service.IHoaDonChiTietService;
import com.example.web.service.IHoaDonService;
import com.example.web.model.HoaDonChiTiet;
import com.example.web.request.HoaDonRequest;
import com.example.web.response.HoaDonFilter;
import com.example.web.response.SanPhamFilter;
import com.example.web.service.IKhachHangService;
import com.example.web.service.ILichSuHoaDonService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/admin/hoa-don")
public class HoDonController {

    private String url;

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private IHoaDonService hoaDonService;

    @Autowired
    private IHoaDonChiTietService hoaDonChiTietService;

    @Autowired
    private SanPhamController sanPhamController;

    @Autowired
    private IKhachHangService khachHangService;

    @Autowired
    private ILichSuHoaDonService lichSuHoaDonService;


    @GetMapping("/hien-thi-hoa-cho")
    public String hoaDon(Model model, @RequestParam(defaultValue = "1") Integer page) {
        if (page < 1) page = 1;
        Pageable pageable = PageRequest.of(page - 1, 5);
        Page<Object[]> hoaDons = hoaDonService.findByHoaDonCho(TrangThaiHoaDon.HOA_DON_CHO.getValue(), pageable);
        model.addAttribute("pageNo", page);
        model.addAttribute("hoaDons", hoaDons);
        model.addAttribute("page", page != 1 ? page * 5 - 4 : page);
        return "banHangTaiQuay/hoa-don-cho";
    }

    @PostMapping(value = "/create")
    public String creatHoaDon() {
        url = hoaDonService.addHoaDon();
        return url;
    }

    @GetMapping(value = "/detail")
    public String getHoaDon(Model model, @RequestParam("idHD") String id, @RequestParam(required = false) String idKhachHang,
                           RedirectAttributes attributes) {
        if (idKhachHang != null && !idKhachHang.isEmpty()) {
            KhachHang khachHang = khachHangService.getKhachHangById(idKhachHang);
            model.addAttribute("khachHang", khachHang);
        }
        model.addAttribute("filter", new SanPhamFilter());
        sanPhamController.danhSachThuocTinhSanPham(model);
        model.addAttribute("request", new HoaDonRequest());
        url = hoaDonService.getHoaDonById(model, id , attributes);
        return url;
    }

    @GetMapping("/add-san-pham")
    public String addSanPham(@RequestParam("ctsp") String idCTSP, @RequestParam("soLuong") String soLuong,
                             @RequestParam("idHD") String idHD ,
                             @RequestParam(value = "idKhachHang") String idKhachHang) {

        HoaDonChiTiet hdct = hoaDonChiTietService.addHoaDonChiTiet(UUID.fromString(idCTSP), UUID.fromString(idHD), Integer.parseInt(soLuong));
        if(idKhachHang != null && !idKhachHang.isEmpty()){
            return "redirect:/hoa-don/detail?idHD="+ idHD + "&idKhachHang="+idKhachHang;
        }
        return "redirect:/admin/hoa-don/detail?idHD="+idHD;
    }

    @DeleteMapping("/delete")
    @ResponseBody
    public HoaDonChiTiet deleteSanPhamHoaDonDonChiTiet(@RequestParam String idHDCT) {
        HoaDonChiTiet hoaDonChiTiet = hoaDonChiTietService.deleteSanPhamHoaDon(idHDCT);
        return hoaDonChiTiet;
    }

    @PutMapping("/update-san-pham")
    @ResponseBody
    public HoaDonChiTiet updateSoLuongSanPhamHoaDonChiTiet(@RequestParam("idHD") String idHDCT, @RequestParam String soLuong) {
        HoaDonChiTiet hoaDonChiTiet = hoaDonChiTietService.updateHoaDonChiTiet(idHDCT, soLuong);
        return hoaDonChiTiet;
    }

    @GetMapping("/tong-tien")
    @ResponseBody
    public Integer getTongTien(@RequestParam String idHD){
      BigDecimal tongTien =  hoaDonChiTietService.tongTienHDCT(UUID.fromString(idHD));
      if(tongTien == null){
          return 0;
      }
      return tongTien.intValue();
    }


    @GetMapping("/huy")
    public String huyHoaDon(@RequestParam String idHD) {
        url = hoaDonService.updateHoaDonTrangThai(idHD);
        return url;
    }

    @GetMapping("/loai-hoa-don")
    public String loaiHoaDon(@RequestParam String idHD, @RequestParam String idKhachHang,  @RequestParam Boolean loaiHoaDon) {
        HoaDon hoaDon = hoaDonService.getOne(idHD);
        hoaDon.setLoaiHoaDon(loaiHoaDon);
        hoaDonService.add(hoaDon);
        return "redirect:/admin/hoa-don/detail?idHD=" + idHD + "&idKhachHang=" +idKhachHang;
    }

    @PostMapping("/thanh-toan")
    private String xacNhanThanhToan(@RequestParam String idHD, @RequestParam String idKhachHang, @Valid @ModelAttribute("request") HoaDonRequest hoaDonRequest, BindingResult result, RedirectAttributes attributes) {
       HoaDon hoaDon = hoaDonService.getOne(idHD);
       if(hoaDon.getLoaiHoaDon()){
           if(result.hasErrors()){
               for (FieldError fieldError : result.getFieldErrors()) {
                 attributes.addFlashAttribute(fieldError.getField() , fieldError.getDefaultMessage());
               }
               attributes.addFlashAttribute("datHang" , hoaDonRequest);
               return  "redirect:/admin/hoa-don/detail?idHD=" + hoaDon.getId() + "&idKhachHang=" + idKhachHang;
           }
       }
        hoaDonRequest.setId(idHD);
        hoaDonRequest.setIdKhachHang(idKhachHang);
        url = hoaDonService.thanhToan(hoaDonRequest, attributes);
        return url;
    }

    //    -----------------------------------------------------------
    @GetMapping("/hien-thi")
    public String hienThi(Model model, @RequestParam(defaultValue = "0") Integer page) {
        model.addAttribute("hoaDonFillter",new HoaDonFilter());
        model.addAttribute("lst",hoaDonService.pagination(page,10).getContent());
        model.addAttribute("lst1",hoaDonService.pagination(page,10).getNumber());
        model.addAttribute("currentPage",page);
        model.addAttribute("url","/admin/hoa-don/hien-thi");
        model.addAttribute("totalPage",hoaDonService.pagination(page,10).getTotalPages());
        model.addAttribute("contentPage", "hoa-don-tai-quay.jsp");
        return "quanLyHoaDon/hoaDonOnline/hoa-don-onl";
    }
    @GetMapping("/hien-thi/{page}")
    public String phanTrang(Model model, @PathVariable("page") Integer page) {
        model.addAttribute("hoaDonFillter", new HoaDonFilter());
        model.addAttribute("lst", hoaDonService.pagination(page, 10).getContent());
        model.addAttribute("lst1",hoaDonService.pagination(page,10).getNumber());
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPage", hoaDonService.pagination(page, 10).getTotalPages());
        model.addAttribute("contentPage", "hoa-don-tai-quay.jsp");
        return "quanLyHoaDon/hoaDonOnline/hoa-don-onl";
    }

    @GetMapping("/filter")
    public String fillter(Model model,
                          @RequestParam(defaultValue = "0") Integer page,
                          @ModelAttribute("hoaDonFillter") HoaDonFilter filter) {
        Pageable pageable = PageRequest.of(page, 10, Sort.by("ngayTao").descending());
        String url = "/admin/hoa-don/filter?" + request.getQueryString().replaceAll("[&?]page.*?(?=&|\\?|$)", "") + "&page=";
        model.addAttribute("lst", hoaDonService.hoaDonFillter(filter, pageable).getContent());
        model.addAttribute("fillter", filter);
        model.addAttribute("url","/admin/hoa-don/hien-thi");
        model.addAttribute("contentPage", "hoa-don-tai-quay.jsp");
        return "quanLyHoaDon/hoaDonOnline/hoa-don-onl";
    }
    @GetMapping("/view-update/{id}")
    public String viewUpdate(Model model,
                             @RequestParam(defaultValue = "0") Integer page,
                             @PathVariable("id") String id) {
        Page<HoaDonChiTiet> lst2 = hoaDonService.getHoaDonHuyChiTiet(UUID.fromString(id),page,5);
        Page<HoaDonChiTiet> lst = hoaDonService.getHoaDonChiTiet(UUID.fromString(id),page,5);
        if (!hoaDonService.getOne(id).getLoaiHoaDon()){
            Integer tongTien = 0;
            for (int i = 0; i <= lst.getContent().size()-1; i++) {
                tongTien+=lst.getContent().get(i).getSoLuong()*lst.getContent().get(i).getDonGia().intValue();
            }
            model.addAttribute("hd",hoaDonService.getOne(id));
            model.addAttribute("lst",lst.getContent());
            model.addAttribute("hdc",tongTien);
            model.addAttribute("hoaDon",new HoaDon());
            model.addAttribute("khachHang",new KhachHang());
            return "quanLyHoaDon/hoaDonTaiQuay/update-hoa-don";
        }else {
            Integer tongTien2 = 0;
            List<LichSuHoaDon> lshd =lichSuHoaDonService.getListById(UUID.fromString(id));
            model.addAttribute("hd",hoaDonService.getOne(id));
            if (hoaDonService.getOne(id).getTrangThai()==5){
                for (int i = 0; i <= lst2.getContent().size()-1; i++) {
                    tongTien2+=lst2.getContent().get(i).getSoLuong()*lst2.getContent().get(i).getDonGia().intValue();
                }
                model.addAttribute("hdc",tongTien2);
                model.addAttribute("lst1",lst2.getContent());
            }else {
                for (int i = 0; i <= lst.getContent().size()-1; i++) {
                    tongTien2+=lst.getContent().get(i).getSoLuong()*lst.getContent().get(i).getDonGia().intValue();
                }
                model.addAttribute("hdc",tongTien2);
                model.addAttribute("lst1",lst.getContent());
            }
            model.addAttribute("xacNhan",lichSuHoaDonService.getOne(id,"xác"));
            model.addAttribute("choGiao",lichSuHoaDonService.getOne(id,"ể"));
            model.addAttribute("daGiao",lichSuHoaDonService.getOne(id,"ô"));
            model.addAttribute("taoDon",lichSuHoaDonService.getOneTao(id,"Tạo đơn hàng"));
            model.addAttribute("lshd",lshd);
            return "quanLyHoaDon/chiTietHoaDonOnline/CTChoXacNhan";
        }
    }

    @GetMapping("/in-hoa-don/{id}")
    public String generateAndSaveInvoice(@PathVariable("id") String id) {
        Page<HoaDonChiTiet> lst = hoaDonService.getHoaDonChiTiet(UUID.fromString(id),0,5);
        url = hoaDonService.inHoaDon(id,lst);
        return url;
    }

}
