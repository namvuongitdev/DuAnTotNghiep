package com.example.web.controller;
import com.example.web.model.ChatLieu;
import com.example.web.service.IChatLieuService;
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
@RequestMapping(value = "/admin/chat-lieu")
public class ChatLieuController {

    @Autowired
    private IChatLieuService service;

    private Page<ChatLieu> chatLieuPage = null;

    @GetMapping("/hien-thi")
    public String hienThi(Model model, @RequestParam(defaultValue = "1") Integer page){
        Pageable pageable = PageRequest.of(page - 1, 5 , Sort.by("ngayTao").descending());
        chatLieuPage = service.findAll(pageable);
        model.addAttribute("list", chatLieuPage);
        model.addAttribute("url", "/admin/chat-lieu/hien-thi?page=");
        return "quanLySanPham/chatlieu/chat-lieu";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute("chatLieu") ChatLieu chatLieu, RedirectAttributes attributes){
        boolean checkTen = service.isExists(chatLieu.getTen());

        if(checkTen){
            attributes.addFlashAttribute("error", "Tên dữ liệu đã tồn tại");
        }else{
            Date date = java.util.Calendar.getInstance().getTime();
            UUID uuid = UUID.randomUUID();
            chatLieu.setId(uuid);
            chatLieu.setTrangThai(1);
            chatLieu.setNgayTao(date);
            service.save(chatLieu);
            attributes.addFlashAttribute("success", "Tạo dữ liệu thành công");
        }
        return "redirect:/admin/chat-lieu/hien-thi";
    }

    @PostMapping("/update/{id}")
    public String update(@PathVariable("id") String id, @ModelAttribute("chatLieu") ChatLieu chatLieu, RedirectAttributes attributes){
        ChatLieu data = service.getOne(UUID.fromString(id));
        Date date = java.util.Calendar.getInstance().getTime();
        chatLieu.setId(UUID.fromString(id));
        chatLieu.setTrangThai(data.getTrangThai());
        chatLieu.setNgayTao(data.getNgayTao());
        chatLieu.setNgaySua(date);
        service.save(chatLieu);
        attributes.addFlashAttribute("success", "Sửa dữ liệu thành công");
        return "redirect:/admin/chat-lieu/hien-thi";
    }

    @GetMapping("/hien-thi/{id}")
    public String hienThiDuLieu(@PathVariable String id, Model model, @RequestParam(defaultValue = "1") Integer page){
        ChatLieu chatLieu = service.getOne(UUID.fromString(id));
        Pageable pageable = PageRequest.of(page - 1, 5 , Sort.by("ngayTao").descending());
        chatLieuPage = service.findAll(pageable);
        model.addAttribute("list", chatLieuPage);
        model.addAttribute("url", "/admin/chat-lieu/hien-thi/"+id+"?page=");
        model.addAttribute("chatLieu", new ChatLieu());
        model.addAttribute("dl", chatLieu);
        return "quanLySanPham/chatlieu/update";
    }

    @GetMapping("/update-status/{id}")
    public String page(@PathVariable("id") String id, RedirectAttributes redirectAttributes,
                       @RequestParam(name = "trangThai")Integer trangThai ){
        redirectAttributes.addFlashAttribute("chatLieu", new ChatLieu());
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
