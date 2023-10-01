package com.example.web.controller;
import com.example.web.model.DanhMuc;
import com.example.web.service.DanhMucService;
import jakarta.validation.Valid;
import org.hibernate.annotations.Parameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.net.http.HttpRequest;
import java.sql.Date;
import java.time.LocalDate;
import java.util.UUID;

@Controller
@RequestMapping("/danh-muc")
public class DanhMucController {
    @Autowired
    private DanhMucService danhMucService;


    @GetMapping("/hien-thi")
    public String hienThi(Model model, @RequestParam(value = "p", defaultValue = "0") Integer pageNo){

        model.addAttribute("danhMuc", new DanhMuc());
        model.addAttribute("list", danhMucService.page(pageNo, 5).getContent());
        model.addAttribute("totalPage", danhMucService.page(pageNo, 5).getTotalPages());
        model.addAttribute("currentPage", pageNo);
        return "quanLySanPham/danhmuc/danhmuc";
    }

    @GetMapping("/view-update/{id}")
    public String viewUpdate(@PathVariable("id") String id, Model model, @ModelAttribute("danhMuc") DanhMuc danhMuc,
                             @RequestParam(value = "p", defaultValue = "0") Integer pageNo){
        danhMuc = danhMucService.getOne(id);
        model.addAttribute("danhMuc", danhMuc);
        model.addAttribute("list", danhMucService.page(pageNo, 5).getContent());
        model.addAttribute("totalPage", danhMucService.page(pageNo, 5).getTotalPages());
        model.addAttribute("currentPage", pageNo);
        return "quanLySanPham/danhmuc/danhmuc";
    }

    @GetMapping("/stop/{id}")
    public String delete(@PathVariable("id") String id){
        DanhMuc dm = danhMucService.getOne(id);
        dm.setTrangThai(0);
        dm.setNgaySua(Date.valueOf(LocalDate.now()));
        danhMucService.update(dm);
        return "redirect:/danh-muc/hien-thi";
    }

    @GetMapping("/hien-thi/{p}")
    public String page(@PathVariable("p") Integer p, Model model){
        model.addAttribute("danhMuc", new DanhMuc());
        model.addAttribute("list", danhMucService.page(p, 5).getContent());
        model.addAttribute("totalPage", danhMucService.page(p, 5).getTotalPages());
        model.addAttribute("currentPage", p);
        return "quanLySanPham/danhmuc/danhmuc";
    }

    @PostMapping("/add")
    public String add(@Valid @ModelAttribute("danhMuc") DanhMuc danhMuc, BindingResult result,
                      @RequestParam(value = "p", defaultValue = "0") Integer pageNo, Model model){
        if(result.hasErrors()){
            model.addAttribute("list", danhMucService.page(pageNo, 5).getContent());
            model.addAttribute("totalPage", danhMucService.page(pageNo, 5).getTotalPages());
            model.addAttribute("currentPage", pageNo);
            return "quanLySanPham/danhmuc/danhmuc";
        }else{
            UUID uuid = UUID.randomUUID();
            danhMuc.setId(String.valueOf(uuid));
            danhMuc.setTrangThai(1);
            danhMuc.setNgayTao(Date.valueOf(LocalDate.now()));
            danhMucService.add(danhMuc);
            return "redirect:/danh-muc/hien-thi";
        }
    }

    @PostMapping("/update/{id}")
    public String update(@RequestParam(name = "ten") String ten, @PathVariable("id") String id, Model model,
                         @ModelAttribute("danhMuc") DanhMuc danhMuc, BindingResult result,
                         @RequestParam(value = "p", defaultValue = "0") Integer pageNo){
        if(ten.equals("")){
            model.addAttribute("erro", "Không được để trống");
            model.addAttribute("list", danhMucService.page(pageNo, 5).getContent());
            model.addAttribute("totalPage", danhMucService.page(pageNo, 5).getTotalPages());
            model.addAttribute("currentPage", pageNo);
            return "quanLySanPham/danhmuc/danhmuc";
        }else{
            DanhMuc dm = danhMucService.getOne(id);
            danhMuc.setId(id);
            danhMuc.setTrangThai(dm.getTrangThai());
            danhMuc.setNgayTao(dm.getNgayTao());
            danhMuc.setNgaySua(Date.valueOf(LocalDate.now()));
            danhMucService.update(danhMuc);
            return "redirect:/danh-muc/hien-thi";
        }
    }
}
