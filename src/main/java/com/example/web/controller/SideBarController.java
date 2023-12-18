package com.example.web.controller;
import com.example.web.Config.BcryptedPasswordEncoderConfig;
import com.example.web.model.NhanVien;
import com.example.web.service.INhanVienService;
import com.example.web.service.MailService;
import com.example.web.util.RandomUntil;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.Principal;
import java.util.Date;
import java.util.UUID;

@Controller
@RequestMapping("/admin")
public class SideBarController {

    @Autowired
    private INhanVienService iNhanVienService;

    @Autowired
    private BcryptedPasswordEncoderConfig passwordEncoder;

    @Autowired
    private MailService mailService;

    private String url;

    @GetMapping("/trang-chu")
    public String getSideBar(Principal principal, Model model, HttpSession session) {
        if (principal != null){
            String username = principal.getName();
            session.setAttribute("username", username);
        }
        return "sidebar/trangchu";
    }

    @GetMapping("/doiMatKhau")
    public String viewDoiMatKhau(Principal principal, Model model){
        if (principal != null){
            model.addAttribute("myEmail", iNhanVienService.findEmailToPass(principal.getName()));
        }
        return "login/resetPassword";
    }

    @PostMapping("/doiMatKhau/{email}")
    public String doiMatKhau(Principal principal, Model model,@PathVariable("email") String email,NhanVien nv, RedirectAttributes attributes,
                             @RequestParam("password") String password, @RequestParam("confirmPassword") String confirmPassword){

        UUID id = iNhanVienService.findIdByEmail(email);

        if(password.equals("") || confirmPassword.equals("")){
            model.addAttribute("pass", "Vui lòng không để trống.");
            model.addAttribute("nhapLai", "Vui lòng không để trống.");
            model.addAttribute("myEmail", iNhanVienService.findEmailToPass(principal.getName()));
            return "login/resetPassword";
        }
        if(password.length() < 6 || password.length() > 25){
            model.addAttribute("pass", "Mật khẩu phải từ 6 đến 25 ký tự");
            model.addAttribute("myEmail", iNhanVienService.findEmailToPass(principal.getName()));
            return "login/resetPassword";
        }
        if(!password.equals(confirmPassword)){
            model.addAttribute("nhapLai", "Mật khẩu không trùng khớp");
            model.addAttribute("myEmail", iNhanVienService.findEmailToPass(principal.getName()));
            return "login/resetPassword";
        }

        mailService.sendMail("sportsclothing46@gmail.com",
                email,
                "Thay đổi mật khẩu thành công.",
                "Email : " + email + "\n"
                        + "Mật khẩu mới : " + password + "\n"
                        + "Chúc bạn làm việc vui vẻ.");

        nv = iNhanVienService.findById(id);
        Date date = java.util.Calendar.getInstance().getTime();
        nv.setId(nv.getId());
        nv.setNgayTao(nv.getNgayTao());
        nv.setNgaySua(date);
        nv.setMatKhau(passwordEncoder.encode(password));

        iNhanVienService.update(id, nv);

        attributes.addFlashAttribute("success", "Đổi mật khẩu thành công");
        return "redirect:/admin/doiMatKhau";
    }

}
