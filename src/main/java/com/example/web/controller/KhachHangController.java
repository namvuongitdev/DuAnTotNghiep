package com.example.web.controller;
import com.example.web.model.KhachHang;
import com.example.web.service.IKhachHangService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
         return "redirect:/admin/hoa-don/detail?idHD="+idHD ;
    }

    @GetMapping("/api-hien-thi")
    @ResponseBody
    public Page<KhachHang> getKhachHangs(@RequestParam Integer page , @RequestParam(required = false) String value){
        Page listKhachHang = null;
        if(value.isEmpty()){
            listKhachHang = khachHangService.getAll(page);
        }else{
            listKhachHang =  khachHangService.getKhachHangByHoTenOrSdtOrTaiKhoan(value, page);
        }
        return listKhachHang;
    }

    @PostMapping("/create")
    @ResponseBody
    public KhachHang createKhachHang(@RequestBody KhachHang khachHang){
      return khachHangService.themMoiKhachHang(khachHang);
    }

}
