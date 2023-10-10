package com.example.web.controller;
import com.example.web.model.KhachHang;
import com.example.web.service.IKhachHangService;
import org.apache.coyote.Request;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/admin/khach-hang")
public class KhachHangController {

    @Autowired
    private IKhachHangService khachHangService;

    @GetMapping("/detail")
    public String getKhachHangs(@RequestParam String id , @RequestParam String idHD , RedirectAttributes attributes){
         KhachHang khachHang =  khachHangService.getKhachHangById(id);
         attributes.addFlashAttribute("khachHang" , khachHang);
         return "redirect:/hoa-don/detail?idHD="+idHD ;
    }

    @GetMapping("/api-hien-thi")
    @ResponseBody
    public Page<KhachHang> getKhachHangs(@RequestParam Integer page){
        return khachHangService.getAll(page);
    }
}
