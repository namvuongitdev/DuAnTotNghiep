package com.example.web.controller;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping()
public class TrangChuController {

    @GetMapping("trang-chu")
    public String hienThi() {
        return "banHangOnlline/trangchu";
    }

    @GetMapping("gio-hang-onl-hien-thi")
    public String gioHang(){
        return "gioHangOnlline/giohang";
    }
}
