package com.example.web.controller;
import com.example.web.model.MauSac;
import com.example.web.service.IMauSacService;
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
import java.util.UUID;

@Controller
@RequestMapping("/admin/mau-sac")
public class MauSacController {
    @Autowired
    private IMauSacService iMauSacService;

    @GetMapping("/hien-thi")
    public String hienThi(Model model, @RequestParam(value = "p", defaultValue = "0") Integer pageNo){

        model.addAttribute("mauSac", new MauSac());
        model.addAttribute("list", iMauSacService.page(pageNo, 5).getContent());
        model.addAttribute("totalPage", iMauSacService.page(pageNo, 5).getTotalPages());
        model.addAttribute("currentPage", pageNo);
        return "quanLySanPham/qlimausac/mausac";
    }

    @GetMapping("/view-update/{id}")
    public String viewUpdate(@PathVariable("id") String id, Model model, @ModelAttribute("mauSac") MauSac mauSac,
                             @RequestParam(value = "p", defaultValue = "0") Integer pageNo){
        mauSac = iMauSacService.getOne(id);
        model.addAttribute("mauSac", mauSac);
        model.addAttribute("list", iMauSacService.page(pageNo, 5).getContent());
        model.addAttribute("totalPage", iMauSacService.page(pageNo, 5).getTotalPages());
        model.addAttribute("currentPage", pageNo);
        return "quanLySanPham/qlimausac/mausac";
    }

    @GetMapping("/stop/{id}")
    public String delete(@PathVariable("id") String id){
        MauSac ms = iMauSacService.getOne(id);
        ms.setTrangThai(0);
        ms.setNgaySua(java.util.Calendar.getInstance().getTime());
        iMauSacService.update(ms);
        return "redirect:/admin/mau-sac/hien-thi";
    }

    @GetMapping("/hien-thi/{p}")
    public String page(@PathVariable("p") Integer p, Model model){
        model.addAttribute("mauSac", new MauSac());
        model.addAttribute("list", iMauSacService.page(p, 5).getContent());
        model.addAttribute("totalPage", iMauSacService.page(p, 5).getTotalPages());
        model.addAttribute("currentPage", p);
        return "quanLySanPham/qlimausac/mausac";
    }

    @PostMapping("/add")
    public String add(@Valid @ModelAttribute("mauSac") MauSac mauSac, BindingResult result,
                      @RequestParam(value = "p", defaultValue = "0") Integer pageNo, Model model){
        if(result.hasErrors()){
            model.addAttribute("list", iMauSacService.page(pageNo, 5).getContent());
            model.addAttribute("totalPage", iMauSacService.page(pageNo, 5).getTotalPages());
            model.addAttribute("currentPage", pageNo);
            return "quanLySanPham/qlimausac/mausac";
        }else{
            UUID uuid = UUID.randomUUID();
            mauSac.setId(uuid);
            mauSac.setTrangThai(1);
            mauSac.setNgayTao(java.util.Calendar.getInstance().getTime());
            iMauSacService.add(mauSac);
            return "redirect:/admin/mau-sac/hien-thi";
        }
    }

    @PostMapping("/update/{id}")
    public String update(@RequestParam(name = "ten") String ten,
                         @PathVariable("id") String id,@Valid @ModelAttribute("mauSac") MauSac mauSac, BindingResult result,
                         @RequestParam(value = "p", defaultValue = "0") Integer pageNo, Model model){
        if(ten.equals("")){
            model.addAttribute("erro", "Không được để trống");
            model.addAttribute("list", iMauSacService.page(pageNo, 5).getContent());
            model.addAttribute("totalPage", iMauSacService.page(pageNo, 5).getTotalPages());
            model.addAttribute("currentPage", pageNo);
            return "quanLySanPham/qlimausac/mausac";
        }else{
            MauSac ms = iMauSacService.getOne(id);
            mauSac.setId(UUID.fromString(id));
            mauSac.setNgayTao(ms.getNgayTao());
            mauSac.setTrangThai(ms.getTrangThai());
            mauSac.setNgaySua(java.util.Calendar.getInstance().getTime());
            iMauSacService.update(mauSac);
            return "redirect:/admin/mau-sac/hien-thi";
        }
    }
}
