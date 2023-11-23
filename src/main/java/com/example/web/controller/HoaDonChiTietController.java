package com.example.web.controller;

import com.example.web.model.HoaDonChiTiet;
import com.example.web.response.HoaDonChiTietReponse;
import com.example.web.service.IHoaDonChiTietService;
import com.example.web.service.IHoaDonService;
import com.example.web.service.ILichSuHoaDonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.Principal;

@Controller
@RequestMapping("/admin")
public class HoaDonChiTietController {

    @Autowired
    private IHoaDonChiTietService hoaDonChiTietService;

    @Autowired
    private IHoaDonService hoaDonService;

    @Autowired
    private ILichSuHoaDonService lichSuHoaDonService;

    @PostMapping(value = "/hoa-don-chi-tiet/delete")
    public String deleteSanPham(@RequestParam String idHDCT, @RequestParam String ghiChu, RedirectAttributes attributes , Principal principal) {
        HoaDonChiTiet hdct = hoaDonChiTietService.deleteSanPhamHoaDon(idHDCT);
        if (hdct != null) {
            attributes.addFlashAttribute("success", "xoá thành công");
            HoaDonChiTietReponse response = hoaDonService.getHoaDonChiTiet(hdct.getId());
            lichSuHoaDonService.add(principal.getName() ,"chỉnh sửa hoá đơn" , hdct.getHoaDon() ,
            "xoá sản phẩm:"+response.getTenSanPham()+"["+response.getTenMauSac()+"]"+"["+response.getTenKichCo()+"]." + ghiChu);
        } else {
            attributes.addFlashAttribute("error", "không tìm thầy hoá đơn chi tiết");
        }
        return "redirect:/admin/hoa-don/chi-tiet-hoa-dons/" + hdct.getHoaDon().getId();
    }

    @PostMapping(value = "/hoa-don-chi-tiet/update")
    public String update(@RequestParam String idHDCT , @RequestParam String ghiChuUpdate ,@RequestParam String soLuongUpdate ,  RedirectAttributes attributes ,Principal principal){
        HoaDonChiTiet hdct = hoaDonChiTietService.updateHoaDonChiTiet(idHDCT , soLuongUpdate);
        if(hdct != null){
            attributes.addFlashAttribute("success", "update thành công");
            HoaDonChiTietReponse response = hoaDonService.getHoaDonChiTiet(hdct.getId());
            lichSuHoaDonService.add(principal.getName() ,"chỉnh sửa hoá đơn" , hdct.getHoaDon() ,
                    "update số lượng sản phẩm:"+response.getTenSanPham()+"["+response.getTenMauSac()+"]"+"["+response.getTenKichCo()+"] số lượng ["+soLuongUpdate+"]." + ghiChuUpdate);
        }else{
            attributes.addFlashAttribute("error", "không tìm thầy hoá đơn chi tiết");
        }
        return "redirect:/admin/hoa-don/chi-tiet-hoa-dons/" + hdct.getHoaDon().getId();
    }

}
