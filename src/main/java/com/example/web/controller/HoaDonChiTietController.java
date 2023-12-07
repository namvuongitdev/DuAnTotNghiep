package com.example.web.controller;

import com.example.web.Config.status.HoaDonStatus;
import com.example.web.model.HoaDonChiTiet;
import com.example.web.response.HoaDonChiTietReponse;
import com.example.web.service.IChiTietSanPhamService;
import com.example.web.service.IHoaDonChiTietService;
import com.example.web.service.IHoaDonService;
import com.example.web.service.ILichSuHoaDonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import java.util.UUID;

@Controller
@RequestMapping("/admin")
public class HoaDonChiTietController {

    @Autowired
    private IHoaDonChiTietService hoaDonChiTietService;

    @Autowired
    private IHoaDonService hoaDonService;

    @Autowired
    private ILichSuHoaDonService lichSuHoaDonService;

    @Autowired
    private IChiTietSanPhamService chiTietSanPhamService;

    @PostMapping(value = "/hoa-don-chi-tiet/delete")
    public String deleteSanPham(@RequestParam String idHDCT, @RequestParam String ghiChu, RedirectAttributes attributes) {
        HoaDonChiTiet hdct = hoaDonChiTietService.deleteSanPhamHoaDon(idHDCT);
        if (hdct != null) {
            attributes.addFlashAttribute("success", "xoá thành công");
            HoaDonChiTietReponse response = hoaDonService.getHoaDonChiTiet(hdct.getId());
            lichSuHoaDonService.add(HoaDonStatus.CHINH_SUA, hdct.getHoaDon().getId(),
                    "xoá sản phẩm:" + response.getTenSanPham() + "[" + response.getTenMauSac() + "]" + "[" + response.getTenKichCo() + "]." + ghiChu);
        } else {
            attributes.addFlashAttribute("error", "không tìm thầy hoá đơn chi tiết");
        }
        return "redirect:/admin/hoa-don/chi-tiet-hoa-dons/" + hdct.getHoaDon().getId();
    }

    @PostMapping(value = "/hoa-don-chi-tiet/update")
    public String update(@RequestParam String idHDCT, @RequestParam String ghiChuUpdate, @RequestParam String soLuongUpdate, RedirectAttributes attributes) {
        HoaDonChiTiet hdct = hoaDonChiTietService.updateHoaDonChiTiet(idHDCT, soLuongUpdate);
        if (hdct != null) {
            attributes.addFlashAttribute("success", "update thành công");
            HoaDonChiTietReponse response = hoaDonService.getHoaDonChiTiet(hdct.getId());
            lichSuHoaDonService.add(HoaDonStatus.CHINH_SUA, hdct.getHoaDon().getId(),
                    "update số lượng sản phẩm:" + response.getTenSanPham() + "[" + response.getTenMauSac() + "]" + "[" + response.getTenKichCo() + "] số lượng [" + soLuongUpdate + "]." + ghiChuUpdate);
        } else {
            attributes.addFlashAttribute("error", "không tìm thầy hoá đơn chi tiết");
        }
        return "redirect:/admin/hoa-don/chi-tiet-hoa-dons/" + hdct.getHoaDon().getId();
    }

    @GetMapping(value = "/hoa-don-chi-tiet/add")
    public String add(@RequestParam String idCTSP, @RequestParam String idHD,
                      @RequestParam Integer soLuong, RedirectAttributes attributes) {

        HoaDonChiTiet hdct = hoaDonChiTietService.addHoaDonChiTiet(UUID.fromString(idCTSP), UUID.fromString(idHD), soLuong);
        if (hdct != null) {
            attributes.addFlashAttribute("success", "thêm thành công");
            HoaDonChiTietReponse response = hoaDonService.getHoaDonChiTiet(hdct.getId());
            lichSuHoaDonService.add(HoaDonStatus.CHINH_SUA, hdct.getHoaDon().getId(),
                    "thêm sản phẩm:" + response.getTenSanPham() + "[" + response.getTenMauSac() + "]" + "[" + response.getTenKichCo() + "] số lượng [" + soLuong + "]");
        } else {
            attributes.addFlashAttribute("error", "không tìm thầy hoá đơn chi tiết");
        }
        return "redirect:/admin/hoa-don/chi-tiet-hoa-dons/" + idHD;
    }

    @GetMapping("/hoa-don-chi-tiet/qrcode")
    public String qrCode(@RequestParam String qrCode, @RequestParam("soLuong") String soLuong,
                         @RequestParam("idHD") String idHD,
                         @RequestParam(value = "idKhachHang") String idKhachHang, RedirectAttributes attributes) {
        UUID idCTSP = chiTietSanPhamService.getByQrcode(qrCode);
        if (idCTSP == null) {
            attributes.addFlashAttribute("error", "không tìm thấy qr code phù hợp");
            return "redirect:/admin/hoa-don/detail?idHD=" + idHD;
        } else {
            attributes.addFlashAttribute("success", "thêm sản phẩm thành công");
            return "redirect:/admin/hoa-don/add-san-pham?ctsp=" + idCTSP + "&soLuong=" + soLuong + "&idHD=" + idHD
                    + "&idKhachHang=" + idKhachHang;
        }
    }

    @PostMapping(value = "/hoa-don-chi-tiet/tra-hang")
    public String traHangUpdateLaiTrangThai(@RequestParam String idHDCT, @RequestParam Integer soLuong, @RequestParam String idHD,@RequestParam String ghiChuKhiTraHang , RedirectAttributes attributes) {
        Integer ischeck = hoaDonChiTietService.traHang(UUID.fromString(idHDCT), soLuong);
        if (ischeck == 2) {
            attributes.addFlashAttribute("success", "xác nhận trả hàng thành công");
            HoaDonChiTietReponse response = hoaDonService.getHoaDonChiTiet(UUID.fromString(idHDCT));
            lichSuHoaDonService.add(HoaDonStatus.HOAN_TRA ,UUID.fromString(idHD) ,"hoàn trả sản phẩm :"+response.getTenSanPham()+"["+response.getTenMauSac()+"]["+response.getTenKichCo()+"]số lượng["+soLuong+"]."+ghiChuKhiTraHang);
        } else if (ischeck == 1) {
            attributes.addFlashAttribute("error", "số lượng trả không thoả mãn");
        } else {
            attributes.addFlashAttribute("error", "không tìm thấy hoá đơn chi tiết");
        }
        return "redirect:/admin/hoa-don/chi-tiet-hoa-dons/" + idHD;
    }
}
