package com.example.web.controller;
import com.example.web.model.Anh;
import com.example.web.model.MauSac;
import com.example.web.service.IAnhService;
import com.example.web.service.IMauSacService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/admin/anh")
public class AnhController {

    @Autowired
    private IAnhService anhService;

    @Autowired
    private IMauSacService mauSacService;

    @GetMapping("/anh-mau-sac")
    @ResponseBody
    public List<Anh> getAnhsBySanPham_idAndMauSac_id(@RequestParam String idSP , @RequestParam String idMS , Model model){
       List<Anh> anhs = anhService.findAnhBySanPham_idAndMauSac_id(UUID.fromString(idSP) , UUID.fromString(idMS));
       return anhs;
    }
}
