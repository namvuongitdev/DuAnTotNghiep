package com.example.web.controller;

import com.example.web.model.DiaChi;
import com.example.web.model.HoaDon;
import com.example.web.model.KhachHang;
import com.example.web.request.CheckoutRequest;
import com.example.web.request.NewDiaChiOnline;
import com.example.web.response.GioHangReponse;
import com.example.web.service.CheckoutService;
import com.example.web.service.IDiaChiService;
import com.example.web.service.IGioHangOnllineService;
import com.example.web.service.IHoaDonService;
import com.example.web.service.IKhachHangService;
import com.example.web.service.ILichSuHoaDonService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/checkouts")
public class ThanhToanController {

    @Autowired
    private IGioHangOnllineService gioHangOnllineService;

    @Autowired
    private IKhachHangService khachHangService;

    @Autowired
    private CheckoutService checkoutService;

    @Autowired
    private IHoaDonService hoaDonService;

    @Autowired
    private ILichSuHoaDonService lichSuHoaDonService;

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private IDiaChiService diaChiService;

    @Autowired
    private ModelMapper modelMapper;

    private CheckoutRequest checkout = null;


    @GetMapping("")
    public String thanhToan(Model model, RedirectAttributes attributes) {
        KhachHang khachHang = khachHangService.getKhachHangLogin();
        List<GioHangReponse> response = gioHangOnllineService.findAll(khachHang.getId());
        DiaChi diaChiMacDinh = diaChiService.getDiaChiByKhachHang_idAndDiaChiMacDinh(khachHang.getId());
        if (diaChiMacDinh != null) {
            model.addAttribute("diaChiMacDinh", diaChiMacDinh);
        }
        if (!model.containsAttribute("checkoutRequest")) {
            model.addAttribute("checkoutRequest", new CheckoutRequest());
        }
        if (response.isEmpty()) {
            attributes.addFlashAttribute("gioHangEmpty", "giỏ hàng đang trống");
            return "redirect:/gio-hang-onl";
        } else {
            BigDecimal tongTien = gioHangOnllineService.tongTienSanPhamTrongGioHang(khachHang.getId());
            model.addAttribute("sanPhamTrongGioHang", response);
            model.addAttribute("tongTien", tongTien);
            model.addAttribute("diaChis", khachHang.getDiaChis());
            model.addAttribute("KhachHang", khachHang.getId());
            model.addAttribute("newDiaChiOnline", new NewDiaChiOnline());
        }
        return "gioHangOnlline/thanhtoan";
    }

    @PostMapping("/new-dia-chi")
    public String newDiaChi(@ModelAttribute("newDiaChiOnline") NewDiaChiOnline newDiaChiOnline, @RequestParam String idKH) {
        KhachHang khachHang = khachHangService.getKhachHangById(idKH);
        DiaChi diaChi = modelMapper.map(newDiaChiOnline, DiaChi.class);
        diaChi.setKhachHang(khachHang);
        diaChi.setDiaChiMacDinh(true);
        return "redirect:/checkouts";
    }

    @PostMapping(value = "/order")
    public String order(@Valid @ModelAttribute("checkoutRequest") CheckoutRequest checkoutRequest, BindingResult result, RedirectAttributes attributes) throws UnsupportedEncodingException {
        if (result.hasErrors()) {
            attributes.addFlashAttribute("org.springframework.validation.BindingResult.checkoutRequest", result);
            attributes.addFlashAttribute("checkoutRequest", checkoutRequest);
            attributes.addFlashAttribute("error", "error");
            return "redirect:/checkouts";
        } else {
            checkout = checkoutRequest;
            KhachHang khachHang = khachHangService.getKhachHangLogin();
            String url = checkoutService.createOrder(khachHang, checkoutRequest, request);
            return "redirect:" + url;
        }
    }

    @GetMapping(value = "/success")
    public String datHangThanhCong(Model model, @RequestParam String idHD) {
        KhachHang khachHang = khachHangService.getKhachHangLogin();
        HoaDon hd = hoaDonService.getHoaDonByKhachHang_idAndHoaDon_id(khachHang.getId(), UUID.fromString(idHD));
        model.addAttribute("hoaDon", hd);
        return "gioHangOnlline/success";
    }

    @GetMapping(value = "/payment/return")
    public String payment(@RequestParam(name = "vnp_TransactionNo") String maGiaDich) {
        Integer ischeck = checkoutService.orderReturn(request);
        KhachHang khachHang = khachHangService.getKhachHangLogin();
        if (ischeck == 1) {
            checkout.setMaGiaDich(maGiaDich);
            HoaDon hoaDon = checkoutService.saveOrder(checkout, khachHang);
            return "redirect:/checkouts/success?idHD=" + hoaDon.getId();
        }
        return null;
    }

}
