package com.example.web.controller;

import com.example.web.model.SanPham;
import com.example.web.response.SanPhamFilter;
import com.example.web.service.*;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping()
public class TrangChuController {

    @Autowired
    private IFormDangService iFormDangService;

    @Autowired
    private IChatLieuService iChatLieuService;

    @Autowired
    private SizeService sizeService;

    @Autowired
    private IMauSacService mauSacService;

    @Autowired
    private DanhMucService danhMucService;

    @Autowired
    private IChiTietSanPhamService chiTietSanPhamService;

    @Autowired
    ISanPhamService iSanPhamService;

    @Autowired
    private HttpServletRequest request;

    @GetMapping("trang-chu")
    public String hienThi(Model model, @RequestParam(defaultValue = "1") int page) {
        if (page < 1) page = 1;
        Pageable pageable = PageRequest.of(page - 1, 20);
        Page<SanPham> list = iSanPhamService.findAll(pageable);
        model.addAttribute("list", list);
        model.addAttribute("listChatLieu", iChatLieuService.getAll());
        model.addAttribute("listFormDang", iFormDangService.getAll());
        model.addAttribute("listDanhMuc", danhMucService.getAll());
        model.addAttribute("filterSanPham", new SanPhamFilter());
        model.addAttribute("pageNo", page);
        model.addAttribute("page", page != 1 ? page * 5 - 4 : page);
        return "banHangOnlline/index";
    }

    @GetMapping("filter")
    public String filterSanPham(@RequestParam(defaultValue = "1") int page,
                                @ModelAttribute("filterSanPham") SanPhamFilter filter,
                                Model model) {
        if (page < 1) page = 1;
        Pageable pageable = PageRequest.of(page - 1, 20);
        Page<SanPham> sanPhamPage = iSanPhamService.sanPhamFilter1(filter, pageable);
        model.addAttribute("list", sanPhamPage);
        model.addAttribute("filterSanPham", filter);
        model.addAttribute("listChatLieu", iChatLieuService.getAll());
        model.addAttribute("listFormDang", iFormDangService.getAll());
        model.addAttribute("listDanhMuc", danhMucService.getAll());

        model.addAttribute("pageNo", page);
        model.addAttribute("page", page != 1 ? page * 5 - 4 : page);
        return "banHangOnlline/index";
    }

    @GetMapping("thoi-trang-nam")
    public String thoiTrangNam(Model model, @RequestParam(defaultValue = "1") int page) {
        if (page < 1) page = 1;
        Pageable pageable = PageRequest.of(page - 1, 12);
        boolean gioiTinh = true;
        Page<SanPham> list = iSanPhamService.findAllGender(pageable,gioiTinh);
        model.addAttribute("list", list);
        model.addAttribute("pageNo", page);
        model.addAttribute("page", page != 1 ? page * 5 - 4 : page);
        return "banHangOnlline/index";
    }

    @GetMapping("thoi-trang-nu")
    public String thoiTrangNu(Model model, @RequestParam(defaultValue = "1") int page) {
        if (page < 1) page = 1;
        Pageable pageable = PageRequest.of(page - 1, 12);
        boolean gioiTinh = false;
        Page<SanPham> list = iSanPhamService.findAllGender(pageable,gioiTinh);
        model.addAttribute("list", list);
        model.addAttribute("pageNo", page);
        model.addAttribute("page", page != 1 ? page * 5 - 4 : page);
        return "banHangOnlline/index";
    }


}
