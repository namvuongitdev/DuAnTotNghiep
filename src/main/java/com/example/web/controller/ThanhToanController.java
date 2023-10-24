package com.example.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/index/checkouts")
public class ThanhToanController {

    @GetMapping("")
    public String thanhToan(){
        return "gioHangOnlline/thanhtoan";
    }
}
