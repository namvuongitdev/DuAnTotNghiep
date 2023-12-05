package com.example.web.controller;
import com.example.web.model.DanhMuc;
import com.example.web.service.DanhMucService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Date;
import java.util.UUID;

@Controller
@RequestMapping("/admin/danh-muc")
public class DanhMucController {
    @Autowired
    private DanhMucService danhMucService;

    private Page<DanhMuc> danhMucPage = null;

    @GetMapping("/hien-thi")
    public String hienThi(Model model, @RequestParam(defaultValue = "1") Integer page){
        Pageable pageable = PageRequest.of(page - 1, 5 , Sort.by("ngayTao").descending());
        danhMucPage = danhMucService.findAll(pageable);
        model.addAttribute("list", danhMucPage);
        model.addAttribute("url", "/admin/danh-muc/hien-thi?page=");
        return "quanLySanPham/danhmuc/danhmuc";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute("danhMuc") DanhMuc danhMuc){
        Date date = java.util.Calendar.getInstance().getTime();
        UUID uuid = UUID.randomUUID();
        danhMuc.setId(String.valueOf(uuid));
        danhMuc.setTrangThai(1);
        danhMuc.setNgayTao(date);
        danhMucService.save(danhMuc);
        return "redirect:/admin/danh-muc/hien-thi";
    }

    @PostMapping("/update/{id}")
    public String update(@PathVariable("id") String id, @ModelAttribute("danhMuc") DanhMuc danhMuc){
        DanhMuc data = danhMucService.getOne(id);
        Date date = java.util.Calendar.getInstance().getTime();
        danhMuc.setId(id);
        danhMuc.setTrangThai(data.getTrangThai());
        danhMuc.setNgayTao(data.getNgayTao());
        danhMuc.setNgaySua(date);
        danhMucService.save(danhMuc);
        return "redirect:/admin/danh-muc/hien-thi";
    }

    @GetMapping("/hien-thi/{id}")
    public String hienThiDuLieu(@PathVariable String id, Model model, @RequestParam(defaultValue = "1") Integer page){
        DanhMuc danhMuc = danhMucService.getOne(id);
        Pageable pageable = PageRequest.of(page - 1, 5 , Sort.by("ngayTao").descending());
        danhMucPage = danhMucService.findAll(pageable);
        model.addAttribute("list", danhMucPage);
        model.addAttribute("url", "/admin/danh-muc/hien-thi/"+id+"?page=");
        model.addAttribute("danhMuc", new DanhMuc());
        model.addAttribute("dl", danhMuc);
        return "quanLySanPham/danhmuc/update";
    }

    @GetMapping("/update-status/{id}")
    public String page(@PathVariable("id") String id, RedirectAttributes redirectAttributes,
                       @RequestParam(name = "trangThai")Integer trangThai ){
        redirectAttributes.addFlashAttribute("danhMuc", new DanhMuc());
        String url = danhMucService.updateStatus(id,trangThai);
        return url;
    }

    @PostMapping("/check")
    @ResponseBody
    public String checkDuplicate(@RequestParam("ten") String value) {
        boolean isDuplicate = danhMucService.isExists(value);
        System.out.println(isDuplicate);
        return String.valueOf(isDuplicate);
    }
}
