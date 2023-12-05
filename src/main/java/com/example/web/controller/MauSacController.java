package com.example.web.controller;
import com.example.web.model.MauSac;
import com.example.web.service.IMauSacService;
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
@RequestMapping("/admin/mau-sac")
public class MauSacController {
    @Autowired
    private IMauSacService iMauSacService;

    private Page<MauSac> mauSacPage = null;

    @GetMapping("/hien-thi")
    public String hienThi(Model model, @RequestParam(defaultValue = "1") Integer page){
        Pageable pageable = PageRequest.of(page - 1, 5 , Sort.by("ngayTao").descending());
        mauSacPage = iMauSacService.findAll(pageable);
        model.addAttribute("list", mauSacPage);
        model.addAttribute("url", "/admin/mau-sac/hien-thi?page=");
        return "quanLySanPham/qlimausac/mausac";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute("mauSac") MauSac mauSac){
        Date date = java.util.Calendar.getInstance().getTime();
        UUID uuid = UUID.randomUUID();
        mauSac.setId(uuid);
        mauSac.setTrangThai(1);
        mauSac.setNgayTao(date);
        iMauSacService.save(mauSac);
        return "redirect:/admin/mau-sac/hien-thi";
    }

    @PostMapping("/update/{id}")
    public String update(@PathVariable("id") String id, @ModelAttribute("mauSac") MauSac mauSac){
        MauSac data = iMauSacService.getOne(id);
        Date date = java.util.Calendar.getInstance().getTime();
        mauSac.setId(UUID.fromString(id));
        mauSac.setTrangThai(data.getTrangThai());
        mauSac.setNgayTao(data.getNgayTao());
        mauSac.setNgaySua(date);
        iMauSacService.save(mauSac);
        return "redirect:/admin/mau-sac/hien-thi";
    }

    @GetMapping("/hien-thi/{id}")
    public String hienThiDuLieu(@PathVariable String id, Model model, @RequestParam(defaultValue = "1") Integer page){
        MauSac mauSac = iMauSacService.getOne(id);
        Pageable pageable = PageRequest.of(page - 1, 5 , Sort.by("ngayTao").descending());
        mauSacPage = iMauSacService.findAll(pageable);
        model.addAttribute("list", mauSacPage);
        model.addAttribute("url", "/admin/mau-sac/hien-thi/"+id+"?page=");
        model.addAttribute("mauSac", new MauSac());
        model.addAttribute("dl", mauSac);
        return "quanLySanPham/qlimausac/update";
    }

    @GetMapping("/update-status/{id}")
    public String page(@PathVariable("id") String id, RedirectAttributes redirectAttributes,
                       @RequestParam(name = "trangThai")Integer trangThai ){
        redirectAttributes.addFlashAttribute("mauSac", new MauSac());
        String url = iMauSacService.updateStatus(id,trangThai);
        return url;
    }

    @PostMapping("/check")
    @ResponseBody
    public String checkDuplicate(@RequestParam("ten") String value) {
        boolean isDuplicate = iMauSacService.isExists(value);
        System.out.println(isDuplicate);
        return String.valueOf(isDuplicate);
    }

}
