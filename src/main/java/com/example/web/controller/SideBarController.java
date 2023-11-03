package com.example.web.controller;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@Controller
@RequestMapping("/admin/trang-chu")
public class SideBarController {

   @GetMapping
   public String getSideBar(Principal principal, Model model, HttpSession session) {
        if (principal != null){
            String username = principal.getName();
            session.setAttribute("username", username);
        }
        return "sidebar/trangchu";
   }
}
