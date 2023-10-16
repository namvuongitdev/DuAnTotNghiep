package com.example.web.controller;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin/trang-chu")
public class SideBarController {

    @GetMapping
   public String getSideBar() {

        return "sidebar/trangchu";
    }
}
