package com.example.web.controller;

import com.example.web.model.KieuDang;
import com.example.web.service.IFormDangService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.time.LocalDate;
import java.util.UUID;

@Controller
@RequestMapping("/admin/kieu-dang")
public class FormDangController {

    @Autowired
    private IFormDangService service;

    @GetMapping("/hien-thi")
    public String hienThi(Model model, @RequestParam(value = "p", defaultValue = "0") Integer pageNo){

        model.addAttribute("kieuDang", new KieuDang());
        model.addAttribute("list", service.page(pageNo, 5).getContent());
        model.addAttribute("totalPage", service.page(pageNo, 5).getTotalPages());
        model.addAttribute("currentPage", pageNo);
        return "quanLySanPham/formdang/formdang";
    }

    @GetMapping("/view-update/{id}")
    public String viewUpdate(@PathVariable("id") UUID id, Model model,@ModelAttribute("kieuDang") KieuDang kieuDang,
                             @RequestParam(value = "p", defaultValue = "0") Integer pageNo){
        kieuDang = service.getOne(id);
        model.addAttribute("kieuDang", kieuDang);
        model.addAttribute("list", service.page(pageNo, 5).getContent());
        model.addAttribute("totalPage", service.page(pageNo, 5).getTotalPages());
        model.addAttribute("currentPage", pageNo);
        return "quanLySanPham/formdang/formdang";
    }

    @GetMapping("/stop/{id}")
    public String delete(@PathVariable("id") UUID id){
        KieuDang kd = service.getOne(id);
        kd.setTrangThai(0);
        kd.setNgaySua(java.util.Calendar.getInstance().getTime());
        service.update(kd);
        return "redirect:/kieu-dang/hien-thi";
    }

    @GetMapping("/hien-thi/{p}")
    public String page(@PathVariable("p") Integer p, Model model){
        model.addAttribute("kieuDang", new KieuDang());
        model.addAttribute("list", service.page(p, 5).getContent());
        model.addAttribute("totalPage", service.page(p, 5).getTotalPages());
        model.addAttribute("currentPage", p);
        return "quanLySanPham/formdang/formdang";
    }

    @PostMapping("/add")
    public String add(@Valid @ModelAttribute("kieuDang") KieuDang kieuDang, BindingResult result,
                      @RequestParam(value = "p", defaultValue = "0") Integer pageNo, Model model){
        if(result.hasErrors()){
            model.addAttribute("list", service.page(pageNo, 5).getContent());
            model.addAttribute("totalPage", service.page(pageNo, 5).getTotalPages());
            model.addAttribute("currentPage", pageNo);
            return "quanLySanPham/formdang/formdang";
        }else{
            UUID uuid = UUID.randomUUID();
            kieuDang.setId(uuid);
            kieuDang.setTrangThai(1);
            kieuDang.setNgayTao(java.util.Calendar.getInstance().getTime());
            service.add(kieuDang);
            return "redirect:/kieu-dang/hien-thi";
        }
    }

    @PostMapping("/update/{id}")
    public String update(@RequestParam(name = "ten") String ten,@PathVariable("id") UUID id,
                         @ModelAttribute("kieuDang") KieuDang kieuDang,
                         @RequestParam(value = "p", defaultValue = "0") Integer pageNo, Model model){
        if(ten.equals("")){
            model.addAttribute("erro", "Không được để trống");
            model.addAttribute("list", service.page(pageNo, 5).getContent());
            model.addAttribute("totalPage", service.page(pageNo, 5).getTotalPages());
            model.addAttribute("currentPage", pageNo);
            return "quanLySanPham/formdang/formdang";
        }else{
            KieuDang kd = service.getOne(id);
            kieuDang.setId(id);
            kieuDang.setTrangThai(kd.getTrangThai());
            kieuDang.setNgayTao(kd.getNgayTao());
            kieuDang.setNgaySua(java.util.Calendar.getInstance().getTime());
            service.update(kieuDang);
            return "redirect:/kieu-dang/hien-thi";
        }
    }


}
