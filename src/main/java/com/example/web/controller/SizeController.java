package com.example.web.controller;
import com.example.web.model.Size;
import com.example.web.service.impl.SizeServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.sql.Date;
import java.time.LocalDate;
import java.util.UUID;

@Controller
@RequestMapping("/size/")
public class SizeController {

    @Autowired
    private SizeServiceImpl sizeService;

    @GetMapping("hien-thi")
    public String hienThi(Model model,@RequestParam(value = "p",defaultValue = "0")Integer pageNo){
        model.addAttribute("size",new Size());
        model.addAttribute("list",sizeService.pagination(pageNo,5).getContent());
        model.addAttribute("currentPage",pageNo);
        model.addAttribute("totalPage",sizeService.pagination(pageNo,5).getTotalPages());
        return "quanLySanPham/kichco/kichco";
    }

    @PostMapping("add")
    public String add(@ModelAttribute("size") @Valid  Size size, BindingResult result, Model model,
                      @RequestParam(value = "p", defaultValue = "0") Integer pageNo) {
        if (result.hasErrors()){
            model.addAttribute("list",sizeService.pagination(pageNo,5).getContent());
            model.addAttribute("currentPage",pageNo);
            model.addAttribute("totalPage",sizeService.pagination(pageNo,5).getTotalPages());
            return "quanLySanPham/kichco/kichco";
        }else {
            UUID id = UUID.randomUUID();
            size.setId(String.valueOf(id));
            size.setNgayTao(Date.valueOf(LocalDate.now()));
            size.setTrangThai(1);
            sizeService.add(size);
            return "redirect:/size/hien-thi";
        }
    }

    @PostMapping("update/{id}")
    public String update(@RequestParam(name = "ten") String ten, @ModelAttribute("size")Size size, @PathVariable("id")String id,
                         @RequestParam(value = "p", defaultValue = "0") Integer pageNo, Model model) {
        if(ten.equals("")){
            model.addAttribute("erro", "Không được để trống");
            model.addAttribute("list",sizeService.pagination(pageNo,5).getContent());
            model.addAttribute("currentPage",pageNo);
            model.addAttribute("totalPage",sizeService.pagination(pageNo,5).getTotalPages());
            return "quanLySanPham/kichco/kichco";
        }else{
            Size s = sizeService.getOne(id);
            size.setId(id);
            size.setNgayTao(s.getNgayTao());
            size.setTrangThai(s.getTrangThai());
            size.setNgaySua(Date.valueOf(LocalDate.now()));
            sizeService.update(size);
            return "redirect:/size/hien-thi";
        }

    }

    @GetMapping("stop/{id}")
    public String delete(@PathVariable("id")String id){
        Size s = sizeService.getOne(id);
        s.setTrangThai(0);
        s.setNgaySua(Date.valueOf(LocalDate.now()));
        sizeService.update(s);
        return "redirect:/size/hien-thi";
    }
    @GetMapping("view-update/{id}")
    public String viewUpdate(@PathVariable("id")String id,Model model,@ModelAttribute("size")Size size,
                             @RequestParam(value = "p", defaultValue = "0") Integer pageNo){
        size=sizeService.getOne(id);
        model.addAttribute("size",size);
        model.addAttribute("list",sizeService.pagination(pageNo,5).getContent());
        model.addAttribute("currentPage",pageNo);
        model.addAttribute("totalPage",sizeService.pagination(pageNo,5).getTotalPages());
        return "quanLySanPham/kichco/kichco";
    }
    @GetMapping("hien-thi/{p}")
    public String phanTrang(@PathVariable("p")Integer p,Model model){
        model.addAttribute("size",new Size());
        model.addAttribute("list",sizeService.pagination(p,5).getContent());
        model.addAttribute("currentPage",p);
        model.addAttribute("totalPage",sizeService.pagination(p,5).getTotalPages());
        return "quanLySanPham/kichco/kichco";
    }

}
