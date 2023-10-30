package com.example.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/index")
public class ThanhToanController {

    @GetMapping("/checkouts")
    public String thanhToan(){
        return "gioHangOnlline/thanhtoan";
    }

    @GetMapping("/hoa-don-cua-toi")
    public String hoaDonCuaToi(){
        return "donHangOnline/hoa-don-cua-toi";
    }
}
