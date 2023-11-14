package com.example.web.controller.authentication;
import com.example.web.Config.BcryptedPasswordEncoderConfig;
import com.example.web.model.KhachHang;
import com.example.web.service.IKhachHangService;
import com.example.web.service.MailService;
import com.example.web.util.RandomUntil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;
import java.util.UUID;

@Controller
public class Authentication {

    @Autowired
    private BcryptedPasswordEncoderConfig passwordEncoder;

    @Autowired
    private MailService mailService;

    @Autowired
    private IKhachHangService iKhachHangService;

    @GetMapping(value = "/login")
    public String adminLogin() {
        return "login/login";
    }

    @GetMapping(value = "/quenMatKhau")
    public String quenMatKhau(){
        return "login/quenMatKhau";
    }

    @PostMapping("/quenMatKhau")
    public String guiEmail(@RequestParam("email") String email, KhachHang kh, Model model){
        char[] password = RandomUntil.randomFull();
        KhachHang checkEmail = iKhachHangService.findByEmail(email);

        if(checkEmail == null){
            model.addAttribute("erroEmail", "Email này chưa được đăng ký.");
            return "login/quenMatKhau";
        }else{
            UUID id = iKhachHangService.findIdByEmail(email);
            kh = iKhachHangService.findById(id);
            mailService.sendMail("sportsclothing46@gmail.com",
                    email,
                    "Thay đổi mật khẩu thành công.",
                    "Email : " + email + "\n"
                            + "Tài khoản : " + kh.getTaiKhoan() + "\n"
                            + "Mật khẩu mới : " + new String(password) + "\n");

            Date date = java.util.Calendar.getInstance().getTime();
            kh.setId(kh.getId());
            kh.setNgayTao(kh.getNgayTao());
            kh.setNgaySua(date);
            kh.setMatKhau(passwordEncoder.encode(String.valueOf(password)));
            iKhachHangService.update(kh);
            model.addAttribute("thongBao", "Mật khẩu mới đã gửi đến mail của bạn. Vui lòng kiểm tra mail.");
            return "login/quenMatKhau";
        }

    }

}
