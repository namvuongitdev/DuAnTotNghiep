package com.example.web.controller;

import com.example.web.Config.BcryptedPasswordEncoderConfig;
import com.example.web.model.KhachHang;
import com.example.web.service.IKhachHangService;
import com.example.web.service.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;
import java.util.UUID;

@Controller
@RequestMapping("/cuaToi")
public class ThongTinKH {

    @Autowired
    private IKhachHangService iKhachHangService;

    @Autowired
    private BcryptedPasswordEncoderConfig passwordEncoder;

    @Autowired
    private MailService mailService;

    @GetMapping("/thongTin")
    public String thongTin(){
        return "login/thongTinKH";
    }

    @PostMapping("/doiMatKhau/{taiKhoan}")
    public String doiMatKhau(@PathVariable("taiKhoan") String taiKhoan, @RequestParam("matKhauMoi") String matKhauMoi,
                             @RequestParam("nhapLai") String nhapLai, KhachHang kh){

        UUID id = iKhachHangService.findIdByTaiKhoan(taiKhoan);
        kh = iKhachHangService.findById(id);
        Date date = java.util.Calendar.getInstance().getTime();
        kh.setId(kh.getId());
        kh.setNgayTao(kh.getNgayTao());
        kh.setNgaySua(date);

        if(matKhauMoi.equals("") || nhapLai.equals("")){
            kh.setMatKhau(kh.getMatKhau());
        }else if(!matKhauMoi.equals(nhapLai)){
            kh.setMatKhau(kh.getMatKhau());
        }else{
            kh.setMatKhau(passwordEncoder.encode(matKhauMoi));
        }
        iKhachHangService.update(kh);
        return "redirect:/cuaToi/thongTin";
    }
}
