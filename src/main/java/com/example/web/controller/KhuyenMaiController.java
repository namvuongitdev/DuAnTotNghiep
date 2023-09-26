package com.example.web.controller;

import com.example.web.model.ChiTietSanPham;
import com.example.web.model.KhuyenMai;
import com.example.web.response.FilterKhuyenMai;
import com.example.web.service.IChiTietSanPhamService;
import com.example.web.service.IKhuyenMaiService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/admin/khuyen-mai")
public class KhuyenMaiController {

    @Autowired
    private IChiTietSanPhamService chiTietSanPhamService;

    @Autowired
    private IKhuyenMaiService khuyenMaiService;

    @Autowired
    private HttpServletRequest request;

    private Page<KhuyenMai> khuyenMais = null;


    @GetMapping("/")
    public String hienThi(Model model, @RequestParam(defaultValue = "1") Integer page) {
        khuyenMais = khuyenMaiService.getAll(page);
        model.addAttribute("khuyenMais", khuyenMais);
        model.addAttribute("khuyenMai", new KhuyenMai());
        model.addAttribute("url", request.getRequestURI() + "?page=");
        return "quanlykhuyenmai/khuyenmai/khuyen-mai";
    }

    @GetMapping("/filter")
    public String filter(Model model, @RequestParam(defaultValue = "1") Integer page, @ModelAttribute("khuyenMai") FilterKhuyenMai filter) {
        khuyenMais = khuyenMaiService.filterKhuyenMai(page, filter);
        String url = request.getRequestURI() + "?" + request.getQueryString().replaceAll("[&?]page.*?(?=&|\\?|$)", "") + "&page=";
        model.addAttribute("khuyenMais", khuyenMais);
        model.addAttribute("filter", filter);
        model.addAttribute("url", url);
        return "quanlykhuyenmai/khuyenmai/khuyen-mai";
    }

    @GetMapping("/new")
    public String newCreate(Model model) {
        Pageable pageable = PageRequest.of(1-1  , 5);
        Page<ChiTietSanPham> list = chiTietSanPhamService.findAll(pageable);
        model.addAttribute("listCtspSanPham", list);
        model.addAttribute("khuyenMai", new KhuyenMai());
        return "quanlykhuyenmai/khuyenmai/new-khuyen-mai";
    }

    @PostMapping("/create")
    public String create(@ModelAttribute("khuyenMai") KhuyenMai khuyenMai) {
        String url = khuyenMaiService.addKhuyenMai(khuyenMai);
        return url;
    }
}
