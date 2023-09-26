package com.example.web.controller;
import com.example.web.model.KhachHang;
import com.example.web.service.IKhachHangService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/khach-hang")
public class KhachHangController {

    @Autowired
    private IKhachHangService khachHangService;

    @GetMapping("/{id}")
    @ResponseBody
    public KhachHang getKhachHang( @PathVariable String id){
         KhachHang khachHang =  khachHangService.getKhachHangById(id);
         return khachHang;
    }
}
