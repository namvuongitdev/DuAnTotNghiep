package com.example.web.controller;

import com.example.web.model.KieuDang;
import com.example.web.service.IFormDangService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Date;
import java.util.UUID;

@Controller
@RequestMapping("/admin/kieu-dang")
public class FormDangController {

    @Autowired
    private IFormDangService service;

    private Page<KieuDang> kieuDangPage = null;

    @GetMapping("/hien-thi")
    public String hienThi(Model model, @RequestParam(defaultValue = "1") Integer page){
        Pageable pageable = PageRequest.of(page - 1, 5 , Sort.by("ngayTao").descending());
        kieuDangPage = service.findAll(pageable);
        model.addAttribute("list", kieuDangPage);
        model.addAttribute("url", "/admin/kieu-dang/hien-thi?page=");
        return "quanLySanPham/formdang/formdang";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute("kieuDang") KieuDang kieuDang, RedirectAttributes attributes){
        boolean checkTen = service.isExists(kieuDang.getTen());

        if(checkTen){
            attributes.addFlashAttribute("error", "Tên dữ liệu đã tồn tại");
        }else{
            Date date = java.util.Calendar.getInstance().getTime();
            UUID uuid = UUID.randomUUID();
            kieuDang.setId(uuid);
            kieuDang.setTrangThai(1);
            kieuDang.setNgayTao(date);
            service.save(kieuDang);
            attributes.addFlashAttribute("success", "Tạo dữ liệu thành công");
        }
        return "redirect:/admin/kieu-dang/hien-thi";
    }

    @PostMapping("/update/{id}")
    public String update(@PathVariable("id") String id, @ModelAttribute("kieuDang") KieuDang kieuDang, RedirectAttributes attributes){
         KieuDang data = service.getOne(UUID.fromString(id));
        Date date = java.util.Calendar.getInstance().getTime();
        kieuDang.setId(UUID.fromString(id));
        kieuDang.setTrangThai(data.getTrangThai());
        kieuDang.setNgayTao(data.getNgayTao());
        kieuDang.setNgaySua(date);
        service.save(kieuDang);
        attributes.addFlashAttribute("success", "Sửa dữ liệu thành công");
        return "redirect:/admin/kieu-dang/hien-thi";
    }

    @GetMapping("/hien-thi/{id}")
    public String hienThiDuLieu(@PathVariable String id, Model model, @RequestParam(defaultValue = "1") Integer page){
        KieuDang kieuDang = service.getOne(UUID.fromString(id));
        Pageable pageable = PageRequest.of(page - 1, 5 , Sort.by("ngayTao").descending());
        kieuDangPage = service.findAll(pageable);
        model.addAttribute("list", kieuDangPage);
        model.addAttribute("url", "/admin/kieu-dang/hien-thi/"+id+"?page=");
        model.addAttribute("kieuDang", new KieuDang());
        model.addAttribute("dl", kieuDang);
        return "quanLySanPham/formdang/update";
    }

    @GetMapping("/update-status/{id}")
    public String page(@PathVariable("id") String id, RedirectAttributes redirectAttributes,
                       @RequestParam(name = "trangThai")Integer trangThai ){
        redirectAttributes.addFlashAttribute("kieuDang", new KieuDang());
        redirectAttributes.addFlashAttribute("success", "Cập nhật trạng thái thành công");
        String url = service.updateStatus(id,trangThai);
        return url;
    }

    @PostMapping("/check")
    @ResponseBody
    public String checkDuplicate(@RequestParam("ten") String value) {
        boolean isDuplicate = service.isExists(value);
        System.out.println(isDuplicate);
        return String.valueOf(isDuplicate);
    }

}
