package com.example.web.controller;

import com.example.web.model.ChiTietSanPham;
import com.example.web.model.GioHangChiTiet;
import com.example.web.model.KhachHang;
import com.example.web.request.DataThemVaoGioHang;
import com.example.web.response.ChiTietResponse;
import com.example.web.response.GioHangReponse;
import com.example.web.service.*;
import org.apache.commons.collections4.Get;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("gio-hang-onl")
public class GioHangOnllineController {
    @Autowired
    IGioHangOnllineService iGioHangOnllineService;

    @Autowired
    IChiTietSanPhamService iChiTietSanPhamService;

    @Autowired
    IGioHangCTService iGioHangCTService;

    @Autowired
    IKhachHangService iKhachHangService;

    @GetMapping()
    public String hienThi(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        KhachHang khachHang = iKhachHangService.findByEmailOrAndTaiKhoan(authentication.getName());
        List<GioHangReponse> list = iGioHangOnllineService.findAll(khachHang.getId());
        model.addAttribute("list", list);
        BigDecimal tongTien = iGioHangOnllineService.tongTienSanPhamTrongGioHang(khachHang.getId());
        model.addAttribute("count", iGioHangOnllineService.countSoLuongSPTrongGioHang(khachHang.getId()));
        model.addAttribute("tongTien", tongTien);
        return "gioHangOnlline/giohang";
    }

    @ResponseBody
    @GetMapping("/get-one/{idGioHangCT}")
    ChiTietSanPham soLuongTon(@PathVariable(name = "idGioHangCT") String idGioHangCT){
        ChiTietSanPham chiTietSanPham = iChiTietSanPhamService.getSL(idGioHangCT);
        ChiTietSanPham chiTietSanPham1 = new ChiTietSanPham();
        chiTietSanPham1.setId(chiTietSanPham.getId());
        chiTietSanPham1.setSoLuong(chiTietSanPham.getSoLuong());
        chiTietSanPham1.setTrangThai(chiTietSanPham.getTrangThai());
        return chiTietSanPham1;
    }

    @GetMapping("/cap-nhat-gio-hang/{soLuong}/{idGioHangCT}")
    public String updateSoLuong(@PathVariable(name = "soLuong") Integer soLuong,
                                @PathVariable(name = "idGioHangCT") String idGioHangCT, RedirectAttributes attributes) {
        ChiTietSanPham chiTietSanPham = iChiTietSanPhamService.getSL(idGioHangCT);
        iGioHangOnllineService.updateSoLuong(soLuong, idGioHangCT);
        return "redirect:/gio-hang-onl";
    }

    @GetMapping("/xoa/{idGioHangCT}")
    public String xoa(@PathVariable(name = "idGioHangCT") String idGioHangCT) {
        iGioHangOnllineService.delete(idGioHangCT);
        return "redirect:/gio-hang-onl";
    }

    @PostMapping("/them-moi-gio-hang/{idSP}")
    public String themGioHang(
            @PathVariable(name = "idSP") String idSP,
            @RequestParam(value = "color", defaultValue = "") String idMau,
            @RequestParam(value = "size", defaultValue = "") String idSize,
            @RequestParam(name = "quantity") Integer soLuongThem, RedirectAttributes redirectAttributes) {

        DataThemVaoGioHang data = DataThemVaoGioHang.builder()
                .idKC(idSize)
                .idSP(idSP)
                .idMS(idMau)
                .soLuong(soLuongThem)
                .build();
        ChiTietResponse chiTietSanPham = null;
        if (idMau.isEmpty() || idSize.isEmpty()) {
            redirectAttributes.addFlashAttribute("error", "bạn chưa lựa chọn thuộc tín sản phẩm");
            redirectAttributes.addFlashAttribute("dataRequest", data);
            return "redirect:/index/chi-tiet-san-pham-onl?id=" + idSP;
        } else {
            chiTietSanPham =  iChiTietSanPhamService.getChiTietSanPhamByMauSac_IdAndSize_IdAndIdSP(UUID.fromString(idMau), idSize, UUID.fromString(idSP));
            if (soLuongThem <= 0 || chiTietSanPham.getSoLuong() < soLuongThem) {
                redirectAttributes.addFlashAttribute("error", "số lượng không thoả mãn");
                redirectAttributes.addFlashAttribute("dataRequest", data);
                return "redirect:/index/chi-tiet-san-pham-onl?id=" + idSP;
            } else {
                ChiTietSanPham sanPham = new ChiTietSanPham(chiTietSanPham.getId(), chiTietSanPham.getSanPham(), chiTietSanPham.getSoLuong(), chiTietSanPham.getTrangThai(), chiTietSanPham.getQrCode(),
                        chiTietSanPham.getMauSac(), chiTietSanPham.getSize());
                iGioHangOnllineService.addGioHang(sanPham, soLuongThem);
                redirectAttributes.addFlashAttribute("dataRequest", data);
                redirectAttributes.addFlashAttribute("success", "sản phẩm đã được thêm vào giỏ hàng");
                redirectAttributes.addFlashAttribute("error", null);
                return "redirect:/index/chi-tiet-san-pham-onl?id=" + idSP;
            }
        }
    }
}