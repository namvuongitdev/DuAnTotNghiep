package com.example.web.controller.hoaDon.donTaiQuay;

import com.example.web.controller.SanPhamController;
import com.example.web.model.HoaDon;
import com.example.web.model.KhachHang;
import com.example.web.model.TrangThaiHoaDon;
import com.example.web.service.IHoaDonChiTietService;
import com.example.web.service.IHoaDonService;
import com.example.web.model.HoaDonChiTiet;
import com.example.web.request.HoaDonRequest;
import com.example.web.response.HoaDonFilter;
import com.example.web.response.SanPhamFilter;
import com.example.web.service.IKhachHangService;
import com.example.web.service.ISanPhamService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.math.BigDecimal;
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
    public String getHoaDon(Model model, @RequestParam("idHD") String id, @RequestParam(required = false) String idKhachHang, @RequestParam(defaultValue = "1") Integer page ,
                            RedirectAttributes attributes) {
        if (idKhachHang != null && !idKhachHang.isEmpty()) {
            KhachHang khachHang = khachHangService.getKhachHangById(idKhachHang);
            model.addAttribute("khachHang", khachHang);
        }
        model.addAttribute("filter", new SanPhamFilter());
        sanPhamController.danhSachThuocTinhSanPham(model);
        model.addAttribute("request", new HoaDonRequest());
        url = hoaDonService.getHoaDonById(model, id);
        return url;
    }

    @GetMapping("/add-san-pham")
    public String addSanPham(@RequestParam("ctsp") String idCTSP, @RequestParam("soLuong") String soLuong,
                             @RequestParam("idHD") String idHD ,
                             @RequestParam(value = "idKhachHang") String idKhachHang) {

        HoaDonChiTiet hdct = hoaDonChiTietService.addHoaDonChiTiet(idCTSP, idHD, Integer.parseInt(soLuong));
        if(idKhachHang != null && !idKhachHang.isEmpty()){
            return "redirect:/hoa-don/detail?idHD="+ idHD + "&idKhachHang="+idKhachHang;
        }
        return "redirect:/admin/hoa-don/detail?idHD="+idHD;
    }

    @GetMapping("/delete")
    public String deleteSanPhamHoaDonDonChiTiet(@RequestParam String idHDCT , @RequestParam String idKhachHang) {
        url = hoaDonChiTietService.deleteSanPhamHoaDon(idHDCT , idKhachHang);
        return url;
    }

    @GetMapping("/update-san-pham")
    public String updateSoLuongSanPhamHoaDonChiTiet(@RequestParam("idHD") String idHDCT, @RequestParam String soLuong , @RequestParam String idKhachHang) {
        url = hoaDonChiTietService.updateHoaDonChiTiet(idHDCT, soLuong , idKhachHang);
        return url;
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
        model.addAttribute("totalPage",hoaDonService.pagination(page,10).getTotalPages());
        return "quanLyHoaDon/hoaDonTaiQuay/hoa-don";
    }
    @GetMapping("/hien-thi/{page}")
    public String phanTrang(Model model, @PathVariable("page") Integer page) {
        model.addAttribute("hoaDonFillter", new HoaDonFilter());
        model.addAttribute("lst", hoaDonService.pagination(page, 10).getContent());
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPage", hoaDonService.pagination(page, 10).getTotalPages());
        return "quanLyHoaDon/hoaDonTaiQuay/hoa-don";
    }

    @GetMapping("/filter")
    public String fillter(Model model,
                          @RequestParam(defaultValue = "0") Integer page,
                          @ModelAttribute("hoaDonFillter") HoaDonFilter filter) {
        Pageable pageable = PageRequest.of(page, 10);
        String url = "/admin/hoa-don/filter?" + request.getQueryString().replaceAll("[&?]page.*?(?=&|\\?|$)", "") + "&page=";
        model.addAttribute("lst", hoaDonService.hoaDonFillter(filter, pageable).getContent());
        model.addAttribute("fillter", filter);
        return "quanLyHoaDon/hoaDonTaiQuay/hoa-don";
    }
    @GetMapping("/view-update/{id}")
    public String viewUpdate(Model model,
                             @RequestParam(defaultValue = "0") Integer page,
                             @PathVariable("id") String id) {
        Page<HoaDonChiTiet> lst = hoaDonService.getHoaDonChiTiet(UUID.fromString(id),page,5);
        model.addAttribute("hd",hoaDonService.getOne(id));
        model.addAttribute("lst",lst.getContent());
        model.addAttribute("hoaDon",new HoaDon());
        model.addAttribute("khachHang",new KhachHang());
        model.addAttribute("currentPage",page);
        model.addAttribute("totalPage",lst.getTotalPages());
        return "quanLyHoaDon/hoaDonTaiQuay/update-hoa-don";
    }
    @GetMapping("/update")
    public String updateHoaDonChiTiet(@RequestParam("ctsp") String idCTSP, @RequestParam("soLuong") String soLuong, @RequestParam("idHD") String idHD) {
        url = hoaDonChiTietService.addSanPhamHoaDonChiTietKhiUpdate(idCTSP,idHD,Integer.parseInt(soLuong));
        return url;
    }
    @GetMapping("/update-so-luong")
    public String updateSoLuongSanPhamHoaDonChiTiet2(@RequestParam("hdct") String idHdct, @RequestParam("soLuong") String soLuong) {
        url = hoaDonChiTietService.updateSoLuongSanPhamHoaDonChiTietKhiUpdate(idHdct,soLuong);
        return url;
    }
    @PostMapping("/update-hoa-don/{id}")
    public String capNhatHoaDonChiTiet(@RequestParam(defaultValue = "0") Integer page,@Valid @ModelAttribute("hoaDon")HoaDon hoaDon,@PathVariable("id") String id) {
        Page<HoaDonChiTiet> lst = hoaDonService.getHoaDonChiTiet(UUID.fromString(id),page,5);
        Integer tongTien = 0;
        for (int i = 0; i <= lst.getContent().size()-1; i++) {
            tongTien+=lst.getContent().get(i).getSoLuong()*lst.getContent().get(i).getChiTietSanPham().getSanPham().getGiaBan().intValue();
        }
        hoaDon.setId(UUID.fromString(id));
        hoaDon.setTongTien(BigDecimal.valueOf(tongTien));
        hoaDonService.updateHoaDonById(hoaDon);
        return  "redirect:/admin/hoa-don/view-update/"+id;
    }

}