package com.example.web.controller;

import com.example.web.model.GioHangChiTiet;
import com.example.web.model.HoaDon;
import com.example.web.model.KhachHang;
import com.example.web.request.CheckoutRequest;
import com.example.web.response.GioHangReponse;
import com.example.web.service.CheckoutService;
import com.example.web.service.IGioHangOnllineService;
import com.example.web.service.IHoaDonChiTietService;
import com.example.web.service.IKhachHangService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import java.math.BigDecimal;
import java.util.List;

@Controller
@RequestMapping("/checkouts")
public class ThanhToanController {

    @Autowired
    private IGioHangOnllineService gioHangOnllineService;

    @Autowired
    private IKhachHangService khachHangService;

    @Autowired
    private IHoaDonChiTietService hoaDonChiTietService;

    @Autowired
    private CheckoutService checkoutService;


    @GetMapping("")
    public String thanhToan(Model model, RedirectAttributes attributes) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        KhachHang khachHang = khachHangService.findByEmailOrAndTaiKhoan(authentication.getName());
        List<GioHangReponse> response = gioHangOnllineService.findAll(khachHang.getId());
        if (response.isEmpty()) {
            attributes.addFlashAttribute("gioHangEmpty", "giỏ hàng đang trống");
            return "redirect:/gio-hang-onl";
        } else {
            BigDecimal tongTien = gioHangOnllineService.tongTienSanPhamTrongGioHang(khachHang.getId());
            model.addAttribute("sanPhamTrongGioHang", response);
            model.addAttribute("tongTien", tongTien);
           // model.addAttribute("checkoutRequest", new CheckoutRequest());
        }
        return "gioHangOnlline/thanhtoan";
    }

    @PostMapping(value = "/order")
    @ResponseBody
    public HoaDon order(@RequestBody CheckoutRequest request) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        KhachHang khachHang = khachHangService.findByEmailOrAndTaiKhoan(authentication.getName());
        HoaDon hd = checkoutService.createOrder(khachHang, request);
        List<GioHangChiTiet> response = gioHangOnllineService.getGHCTByKhachHang_id(khachHang.getId());
        response.forEach(o -> { ;
            hoaDonChiTietService.addHoaDonChiTiet(o.getChiTietSanPham().getId(), hd.getId(), o.getSoLuong());
        });
        gioHangOnllineService.clearAllGioHangChiTietByKhachHang_id(khachHang.getId());
        return hd;
    }
}
