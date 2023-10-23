package com.example.web.controller.hoaDon.donDatHang;

import com.example.web.response.HoaDonFilter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin/hoa-don-onl")
public class HoaDonOnlController {

    @GetMapping("/cho-xac-nhan/hien-thi")
    public String choXacNhan(Model model,@ModelAttribute("hoaDonFillter") HoaDonFilter filter){
        model.addAttribute("contentPage", "choXacNhan.jsp");
        model.addAttribute("fillter", filter);
        return "quanLyHoaDon/hoaDonOnline/hoa-don-onl";
    }

    @GetMapping("/cho-giao-hang/hien-thi")
    public String choGiaoHang(Model model,@ModelAttribute("hoaDonFillter") HoaDonFilter filter){
        model.addAttribute("contentPage", "choGiaoHang.jsp");
        model.addAttribute("fillter", filter);
        return "quanLyHoaDon/hoaDonOnline/hoa-don-onl";
    }

    @GetMapping("/dang-giao/hien-thi")
    public String dangGiao(Model model,@ModelAttribute("hoaDonFillter") HoaDonFilter filter){
        model.addAttribute("contentPage", "dangGiao.jsp");
        model.addAttribute("fillter", filter);
        return "quanLyHoaDon/hoaDonOnline/hoa-don-onl";
    }

    @GetMapping("/da-giao/hien-thi")
    public String daGiao(Model model,@ModelAttribute("hoaDonFillter") HoaDonFilter filter){
        model.addAttribute("contentPage", "daGiao.jsp");
        model.addAttribute("fillter", filter);
        return "quanLyHoaDon/hoaDonOnline/hoa-don-onl";

    }

    @GetMapping("/da-huy/hien-thi")
    public String daHuy(Model model,@ModelAttribute("hoaDonFillter") HoaDonFilter filter){
        model.addAttribute("contentPage", "daHuy.jsp");
        model.addAttribute("fillter", filter);
        return "quanLyHoaDon/hoaDonOnline/hoa-don-onl";

    }
}
