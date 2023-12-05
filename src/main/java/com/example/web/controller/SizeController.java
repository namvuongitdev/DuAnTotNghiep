package com.example.web.controller;
import com.example.web.model.Size;
import com.example.web.service.impl.SizeServiceImpl;
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
@RequestMapping("/admin/size/")
public class SizeController {

    @Autowired
    private SizeServiceImpl sizeService;

    private Page<Size> sizePage = null;

    @GetMapping("/hien-thi")
    public String hienThi(Model model, @RequestParam(defaultValue = "1") Integer page){
        Pageable pageable = PageRequest.of(page - 1, 5 , Sort.by("ngayTao").descending());
        sizePage = sizeService.findAll(pageable);
        model.addAttribute("list", sizePage);
        model.addAttribute("url", "/admin/size/hien-thi?page=");
        return "quanLySanPham/kichco/kichco";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute("size") Size size){
        Date date = java.util.Calendar.getInstance().getTime();
        UUID uuid = UUID.randomUUID();
        size.setId(String.valueOf(uuid));
        size.setTrangThai(1);
        size.setNgayTao(date);
        sizeService.save(size);
        return "redirect:/admin/size/hien-thi";
    }

    @PostMapping("/update/{id}")
    public String update(@PathVariable("id") String id, @ModelAttribute("size") Size size){
        Size data = sizeService.getOne(id);
        Date date = java.util.Calendar.getInstance().getTime();
        size.setId(id);
        size.setTrangThai(data.getTrangThai());
        size.setNgayTao(data.getNgayTao());
        size.setNgaySua(date);
        sizeService.save(size);
        return "redirect:/admin/size/hien-thi";
    }

    @GetMapping("/hien-thi/{id}")
    public String hienThiDuLieu(@PathVariable String id, Model model, @RequestParam(defaultValue = "1") Integer page){
        Size size = sizeService.getOne(id);
        Pageable pageable = PageRequest.of(page - 1, 5 , Sort.by("ngayTao").descending());
        sizePage = sizeService.findAll(pageable);
        model.addAttribute("list", sizePage);
        model.addAttribute("url", "/admin/size/hien-thi/"+id+"?page=");
        model.addAttribute("size", new Size());
        model.addAttribute("dl", size);
        return "quanLySanPham/kichco/update";
    }

    @GetMapping("/update-status/{id}")
    public String page(@PathVariable("id") String id, RedirectAttributes redirectAttributes,
                       @RequestParam(name = "trangThai")Integer trangThai ){
        redirectAttributes.addFlashAttribute("size", new Size());
        String url = sizeService.updateStatus(id,trangThai);
        return url;
    }

    @PostMapping("/check")
    @ResponseBody
    public String checkDuplicate(@RequestParam("ten") String value) {
        boolean isDuplicate = sizeService.isExists(value);
        System.out.println(isDuplicate);
        return String.valueOf(isDuplicate);
    }
}
