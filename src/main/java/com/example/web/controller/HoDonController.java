package com.example.web.controller;

import com.example.web.Config.status.HoaDonStatus;
import com.example.web.model.HoaDon;
import com.example.web.model.KhachHang;
import com.example.web.model.TrangThaiHoaDon;
import com.example.web.request.ThongTinKhachHang;
import com.example.web.response.HoaDonChiTietReponse;
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
import java.security.Principal;
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
    public String hoaDon(Model model) {
        List<Object[]> hoaDons = hoaDonService.findByHoaDonCho(TrangThaiHoaDon.HOA_DON_CHO.getValue());
        model.addAttribute("hoaDons", hoaDons);
        return "banHangTaiQuay/hoa-don-cho";
    }

    @PostMapping(value = "/create")
    public String creatHoaDon(RedirectAttributes attributes) {
        List<Object[]> listHoaDonCho = hoaDonService.findByHoaDonCho(TrangThaiHoaDon.HOA_DON_CHO.getValue());
        if(listHoaDonCho.size() >= 5){
            attributes.addFlashAttribute("error" , "không được tạo quá 5 hoá đơn chờ");
            return "redirect:/admin/hoa-don/hien-thi-hoa-cho";
        }
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
        url = hoaDonService.getHoaDonById(model, id, attributes);
        return url;
    }

    @GetMapping("/add-san-pham")
    public String addSanPham(@RequestParam("ctsp") String idCTSP, @RequestParam("soLuong") String soLuong,
                             @RequestParam("idHD") String idHD,
                             @RequestParam(value = "idKhachHang") String idKhachHang) {

        HoaDonChiTiet hdct = hoaDonChiTietService.addHoaDonChiTiet(UUID.fromString(idCTSP), UUID.fromString(idHD), Integer.parseInt(soLuong));
        if (idKhachHang != null && !idKhachHang.isEmpty()) {
            return "redirect:/hoa-don/detail?idHD=" + idHD + "&idKhachHang=" + idKhachHang;
        }
        return "redirect:/admin/hoa-don/detail?idHD=" + idHD;
    }

    @PutMapping("/delete")
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
    public Integer getTongTien(@RequestParam String idHD) {
        BigDecimal tongTien = hoaDonChiTietService.tongTienHDCT(UUID.fromString(idHD));
        if (tongTien == null) {
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
    public String loaiHoaDon(@RequestParam String idHD, @RequestParam String idKhachHang, @RequestParam Boolean loaiHoaDon) {
        HoaDon hoaDon = hoaDonService.getOne(idHD);
        hoaDon.setLoaiHoaDon(loaiHoaDon);
        hoaDonService.add(hoaDon);
        return "redirect:/admin/hoa-don/detail?idHD=" + idHD + "&idKhachHang=" + idKhachHang;
    }

    @PostMapping("/thanh-toan")
    private String xacNhanThanhToan(@RequestParam String idHD, @RequestParam String idKhachHang, @Valid @ModelAttribute("request") HoaDonRequest hoaDonRequest, BindingResult result, RedirectAttributes attributes) {
        HoaDon hoaDon = hoaDonService.getOne(idHD);
        if (hoaDon.getLoaiHoaDon()) {
            if (result.hasErrors()) {
                for (FieldError fieldError : result.getFieldErrors()) {
                    attributes.addFlashAttribute(fieldError.getField(), fieldError.getDefaultMessage());
                }
                attributes.addFlashAttribute("datHang", hoaDonRequest);
                return "redirect:/admin/hoa-don/detail?idHD=" + hoaDon.getId() + "&idKhachHang=" + idKhachHang;
            }
        }
        hoaDonRequest.setId(idHD);
        hoaDonRequest.setIdKhachHang(idKhachHang);
        url = hoaDonService.thanhToan(hoaDonRequest, attributes);
        return url;
    }

    // quản lý hoá đơn

    @GetMapping("/hien-thi")
    public String hienThi(Model model, @RequestParam(defaultValue = "1") Integer page) {
        Page<HoaDon> hoaDons = hoaDonService.getAllHoaDonByTrangThaiKhachHoaDonCho(page);
        model.addAttribute("hoaDons", hoaDons);
        model.addAttribute("hoaDonFillter", new HoaDonFilter());
        model.addAttribute("url", request.getRequestURI() + "?page=");
        return "quanLyHoaDon/hoaDonOnline/hoa-don-tai-quay";
    }

    @GetMapping("/filter")
    public String fillter(Model model, @RequestParam(defaultValue = "1") Integer page, @ModelAttribute("hoaDonFillter") HoaDonFilter filter) {
        Pageable pageable = PageRequest.of(page - 1, 10, Sort.by("ngayTao").descending());
        Page<HoaDon> hoaDons = hoaDonService.hoaDonFillter(filter, pageable);
        String url = "/admin/hoa-don/filter?" + request.getQueryString().replaceAll("[&?]page.*?(?=&|\\?|$)", "") + "&page=";
        model.addAttribute("fillter", filter);
        model.addAttribute("url", url);
        model.addAttribute("hoaDons", hoaDons);
        return "quanLyHoaDon/hoaDonOnline/hoa-don-tai-quay";
    }

    @GetMapping("/chi-tiet-hoa-dons/{id}")
    public String getAllChiTietHoaDonByHoaDon_id(Model model, @PathVariable("id") String id) {
        List<HoaDonChiTietReponse> response = hoaDonService.getHoaDonChiTiets(UUID.fromString(id));
        Boolean isCheck = hoaDonService.kiemTraConTrongHDCT(UUID.fromString(id));
        BigDecimal tongTien = hoaDonChiTietService.tongTienHDCT(UUID.fromString(id));
        HoaDon updateTrangThaiTraHang = hoaDonService.updateThoiGianTraHang();
        HoaDon hoaDon = hoaDonService.getOne(id);
        model.addAttribute("hoaDon", hoaDon);
        model.addAttribute("listHoaDonChiTiet", response);
        model.addAttribute("thongTinKhachHang", new ThongTinKhachHang());
        model.addAttribute("tongTien" , tongTien);
        model.addAttribute("trangThaiTraHang" , updateTrangThaiTraHang);
        model.addAttribute("isChecks" , isCheck);
        return "quanLyHoaDon/chiTietHoaDonOnline/CTChoXacNhan";
    }

    @GetMapping("/chi-tiet-hoa-don/{idHDCT}")
    @ResponseBody
    public HoaDonChiTietReponse getChiTietHoaDonByHoaDon_id(@PathVariable String idHDCT){
         return hoaDonService.getHoaDonChiTiet(UUID.fromString(idHDCT));
    }

    @PostMapping("/update-thong-tin-khach-hang")
    public String updateThongTinKhachHang(@RequestParam String idHD, @ModelAttribute("thongTinKhachHang") ThongTinKhachHang request, RedirectAttributes attributes , Principal principal) {
        HoaDon hd = hoaDonService.updateThongTinKhachHang(UUID.fromString(idHD), request);
        if (hd != null) {
            attributes.addFlashAttribute("success", "update thông tin khách hàng thành công");
            lichSuHoaDonService.add(HoaDonStatus.CHINH_SUA, hd.getId(),"Chỉ sửa thông tin khách hàng." +request.getGhiChu());
        } else {
            attributes.addFlashAttribute("error", "không tìm thấy hoá đơn");
        }
        return "redirect:/admin/hoa-don/chi-tiet-hoa-dons/" + hd.getId();
    }

    @PostMapping("/update-trang-thai")
    public String updateTrangThaiDonHang(@RequestParam Integer trangThai , @RequestParam String idHD , @RequestParam String ghiChuXacNhan , RedirectAttributes attributes){
        Integer isCheck = hoaDonService.xacNhanHoaDon(trangThai , UUID.fromString(idHD) , ghiChuXacNhan);
        if(isCheck == 1){
            attributes.addFlashAttribute("success", "xác nhận hoá đơn thành công");
        }else if (isCheck == 2){
            attributes.addFlashAttribute("error", "bạn chưa nhập phí vận chuyển");
        }else{
            attributes.addFlashAttribute("error", "không tìm thấy hoá đơn");
        }
         return "redirect:/admin/hoa-don/chi-tiet-hoa-dons/" + idHD;
    }


    @GetMapping("/in-hoa-don/{id}")
    public String generateAndSaveInvoice(@PathVariable("id") String id) {
        //  Page<HoaDonChiTiet> lst = hoaDonService.getHoaDonChiTiet(UUID.fromString(id),0,5);
        //   url = hoaDonService.inHoaDon(id,lst);
        return url;
    }

}
