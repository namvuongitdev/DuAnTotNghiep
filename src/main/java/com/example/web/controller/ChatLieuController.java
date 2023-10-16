package com.example.web.controller;
import com.example.web.model.ChatLieu;
import com.example.web.service.IChatLieuService;
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

import java.util.Date;
import java.time.LocalDate;
import java.util.UUID;

@Controller
@RequestMapping(value = "/admin/chat-lieu")
public class ChatLieuController {

    @Autowired
    private IChatLieuService service;

    @GetMapping("/hien-thi")
    public String hienThi(Model model, @RequestParam(value = "p", defaultValue = "0") Integer pageNo){

        model.addAttribute("chatLieu", new ChatLieu());
        model.addAttribute("list", service.page(pageNo, 5).getContent());
        model.addAttribute("totalPage", service.page(pageNo, 5).getTotalPages());
        model.addAttribute("currentPage", pageNo);
        return "quanLySanPham/chatlieu/chat-lieu";
    }

    @GetMapping("/view-update/{id}")
    public String viewUpdate(@PathVariable("id") UUID id, Model model, @ModelAttribute("chatLieu") ChatLieu chatLieu,
                             @RequestParam(value = "p", defaultValue = "0") Integer pageNo){
        chatLieu = service.getOne(id);
        model.addAttribute("chatLieu", chatLieu);
        model.addAttribute("list", service.page(pageNo, 5).getContent());
        model.addAttribute("totalPage", service.page(pageNo, 5).getTotalPages());
        model.addAttribute("currentPage", pageNo);
        return "quanLySanPham/chatlieu/chat-lieu";
    }

    @GetMapping("/stop/{id}")
    public String delete(@PathVariable("id") UUID id){
        ChatLieu cl = service.getOne(id);
        cl.setTrangThai(0);
        cl.setNgaySua(java.util.Calendar.getInstance().getTime());
        service.update(cl);
        return "redirect:/chat-lieu/hien-thi";
    }

    @GetMapping("/hien-thi/{p}")
    public String page(@PathVariable("p") Integer p, Model model){
        model.addAttribute("chatLieu", new ChatLieu());
        model.addAttribute("list", service.page(p, 5).getContent());
        model.addAttribute("totalPage", service.page(p, 5).getTotalPages());
        model.addAttribute("currentPage", p);
        return "quanLySanPham/chatlieu/chat-lieu";
    }

    @PostMapping("/add")
    public String add(@Valid @ModelAttribute("chatLieu") ChatLieu chatLieu, BindingResult result,
                      @RequestParam(value = "p", defaultValue = "0") Integer pageNo, Model model){
        if(result.hasErrors()){
            model.addAttribute("list", service.page(pageNo, 5).getContent());
            model.addAttribute("totalPage", service.page(pageNo, 5).getTotalPages());
            model.addAttribute("currentPage", pageNo);
            return "quanLySanPham/chatlieu/chat-lieu";
        }else{
            UUID uuid = UUID.randomUUID();
            chatLieu.setId(uuid);
            chatLieu.setTrangThai(1);
            chatLieu.setNgayTao(java.util.Calendar.getInstance().getTime());
            service.add(chatLieu);
            return "redirect:/chat-lieu/hien-thi";
        }
    }

    @PostMapping("/update/{id}")
    public String update(@RequestParam(name = "ten") String ten,
                         @PathVariable("id") UUID id,@Valid @ModelAttribute("chatLieu") ChatLieu chatLieu, BindingResult result,
                         @RequestParam(value = "p", defaultValue = "0") Integer pageNo, Model model){
        if(ten.equals("")){
            model.addAttribute("erro", "Không được để trống");
            model.addAttribute("list", service.page(pageNo, 5).getContent());
            model.addAttribute("totalPage", service.page(pageNo, 5).getTotalPages());
            model.addAttribute("currentPage", pageNo);
            return "quanLySanPham/chatlieu/chat-lieu";
        }else{
            ChatLieu cl = service.getOne(id);
            chatLieu.setId(id);
            chatLieu.setNgayTao(cl.getNgayTao());
            chatLieu.setTrangThai(cl.getTrangThai());
            chatLieu.setNgaySua(java.util.Calendar.getInstance().getTime());
            service.update(chatLieu);
            return "redirect:/chat-lieu/hien-thi";
        }
    }
}
