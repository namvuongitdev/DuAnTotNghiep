package com.example.web.controller;

import com.example.web.Config.BcryptedPasswordEncoderConfig;
import com.example.web.model.DiaChi;
import com.example.web.model.KhachHang;
import com.example.web.service.IDiaChiService;
import com.example.web.service.IGioHangOnllineService;
import com.example.web.service.IKhachHangService;
import com.example.web.service.MailService;
import com.example.web.util.RandomUntil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/cuaToi")
public class ThongTinKH {

    @Autowired
    private IKhachHangService iKhachHangService;

    @Autowired
    private BcryptedPasswordEncoderConfig passwordEncoder;

    @Autowired
    private IDiaChiService iDiaChiService;

    @Autowired
    private IGioHangOnllineService iGioHangOnllineService;

    @Autowired
    private MailService mailService;

    @GetMapping("/thongTin/{taiKhoan}")
    public String thongTin(@PathVariable("taiKhoan") String taiKhoan, Model model){
        UUID id = iKhachHangService.findIdByTaiKhoan(taiKhoan);
        KhachHang khachHang = iKhachHangService.findKhachHangByTaiKhoan(taiKhoan);
        model.addAttribute("count", iGioHangOnllineService.countSoLuongSPTrongGioHang(khachHang.getId()));
        model.addAttribute("listDiaChi", iDiaChiService.getDiaChiByKhachHang_id(id));
        model.addAttribute("khachHang", khachHang);
        return "login/thongTinKH";
    }

    @PostMapping("/doiThongTin/{taiKhoan}")
    public String doiThongTin(@PathVariable String taiKhoan,  @RequestParam("hoTen") String hoTen,
                              @RequestParam("sdt") String sdt, @RequestParam("email") String email, KhachHang kh){
        UUID id = iKhachHangService.findIdByTaiKhoan(taiKhoan);
        kh = iKhachHangService.findById(id);
        Date date = java.util.Calendar.getInstance().getTime();
        kh.setId(kh.getId());
        kh.setNgayTao(kh.getNgayTao());
        kh.setNgaySua(date);

        if(hoTen.equals("") || sdt.equals("") || email.equals("")){
            kh.setHoTen(kh.getHoTen());
            kh.setSdt(kh.getSdt());
            kh.setEmail(kh.getEmail());
        }else{
            kh.setHoTen(hoTen);
            kh.setSdt(sdt);
            kh.setEmail(email);
        }
        iKhachHangService.update(kh);
        return "redirect:/cuaToi/thongTin/" + taiKhoan;
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
        return "redirect:/cuaToi/thongTin/" + taiKhoan;
    }

    @PostMapping("/themDiaChi/{taiKhoan}")
    public String themDiaChi(@PathVariable String taiKhoan,  @RequestParam("hoTenNhan") String hoTen,
                             @RequestParam("sdtNhan") String sdt, @RequestParam("diaChiNhan") String diaChiNhan){
        KhachHang idKH = iKhachHangService.findKhachHangByTaiKhoan(taiKhoan);
        UUID id = UUID.randomUUID();
        DiaChi diaChi = new DiaChi(id, hoTen, sdt, diaChiNhan, null, idKH, null);

        iDiaChiService.add(diaChi);
        return "redirect:/cuaToi/thongTin/" + taiKhoan;
    }

    @GetMapping("/xoaDiaChi/{id}")
    public String xoaDiaChi(@PathVariable UUID id, @RequestParam("taiKhoanById") String taiKhoan){
        iDiaChiService.delete(id);
        return "redirect:/cuaToi/thongTin/" + taiKhoan;
    }
}
