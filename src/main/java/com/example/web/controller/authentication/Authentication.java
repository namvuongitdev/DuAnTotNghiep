package com.example.web.controller.authentication;
import com.example.web.Config.BcryptedPasswordEncoderConfig;
import com.example.web.model.KhachHang;
import com.example.web.service.IKhachHangService;
import com.example.web.service.MailService;
import com.example.web.service.impl.DiaChiServiceImpl;
import com.example.web.util.RandomUntil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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

    @Autowired
    private IKhachHangService khachHangService;

    @GetMapping(value = "/login")
    public String adminLogin() {
        return "login/login";
    }

    @GetMapping(value = "/quenMatKhau")
    public String quenMatKhau(){
        return "login/quenMatKhau";
    }

    @GetMapping(value = "/dangKy")
    public String dangKy(Model model){
        model.addAttribute("khachHang", new KhachHang());
        return "login/dangKy";
    }

    @PostMapping("/quenMatKhau")
    public String guiEmail(@RequestParam("email") String email, KhachHang kh, Model model, RedirectAttributes attributes){
        char[] password = RandomUntil.randomFull();
        KhachHang checkEmail = iKhachHangService.findByEmail(email);

        if(checkEmail == null){
            model.addAttribute("error", "Email này chưa được đăng ký.");
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
            attributes.addFlashAttribute("success", "Mật khẩu mới đã gửi đến mail của bạn. Vui lòng kiểm tra mail.");
            return "redirect:/login";
        }

    }

    @PostMapping("/dangKy")
    public String dangKyTaiKhoan(Model model, RedirectAttributes attributes,
                                 @RequestParam("email") String email, @RequestParam("taiKhoan") String taiKhoan,
                                 @RequestParam("hoTen") String hoTen, @RequestParam("sdt") String sdt){
        char[] password = RandomUntil.randomFull();

        if(email.equals("") || taiKhoan.equals("") || hoTen.equals("") || sdt.equals("")){
            model.addAttribute("error", "Vui lòng không để trống thông tin");
            return "login/dangKy";
        }
        if (khachHangService.findByEmail(email)!=null){
            model.addAttribute("error", "Email này đã được sử dụng");
            return "login/dangKy";
        }
        if (khachHangService.findByTaiKhoan(taiKhoan)!=null){
            model.addAttribute("error", "Tài khoản đã tồn tại");
            return "login/dangKy";
        }
        if (khachHangService.findBySdt(sdt)!=null){
            model.addAttribute("error", "Số điện thoại đã được sử dụng");
            return "login/dangKy";
        }
        Date date = java.util.Calendar.getInstance().getTime();
        UUID uuid = UUID.randomUUID();
        KhachHang khachHang = new KhachHang(uuid, hoTen, email, 0, taiKhoan, passwordEncoder.encode(new String(password)), date,sdt);
        mailService.sendMail("sportsclothing46@gmail.com",
                email,
                "Chúc mừng bạn đã đến với Sport Clothing.",
                "Tên đăng nhập: " + taiKhoan + "\n"
                        + "Mật khẩu : " + new String(password) + "\n\n"
                        + "Họ tên  : " + hoTen + "\n"
                        + "Số điện thoại  :" + sdt + "\n\n");
        khachHangService.add(khachHang);
        attributes.addFlashAttribute("success", "Đăng ký tài khoản thành công");
        return "redirect:/login";
    }

}
