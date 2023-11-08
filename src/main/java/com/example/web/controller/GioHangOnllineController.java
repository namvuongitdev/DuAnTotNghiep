package com.example.web.controller;

import com.example.web.model.ChiTietSanPham;
import com.example.web.model.KhachHang;
import com.example.web.response.ChiTietResponse;
import com.example.web.response.GioHangReponse;
import com.example.web.service.IChiTietSanPhamService;
import com.example.web.service.IGioHangOnllineService;
import com.example.web.service.IKhachHangService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("gio-hang-onl")
public class GioHangOnllineController {
    @Autowired
    IGioHangOnllineService iGioHangOnllineService;

    @Autowired
    IChiTietSanPhamService iChiTietSanPhamService;

    @Autowired
    IKhachHangService iKhachHangService;

    @GetMapping()
    public String hienThi(Model model) {
        Integer tongTien = 0;
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        KhachHang khachHang = iKhachHangService.findByEmailOrAndTaiKhoan(authentication.getName());
        List<GioHangReponse> list = iGioHangOnllineService.findAll(khachHang.getId());
        model.addAttribute("list", list);
        for(GioHangReponse response : list){
            tongTien += response.getThanhTien();
        }
        model.addAttribute("tongTien" , tongTien);
        return "gioHangOnlline/giohang";
    }


    @GetMapping("/cap-nhat-gio-hang/{soLuong}/{idGioHangCT}")
    public String updateSoLuong(@PathVariable(name = "soLuong") String soLuong,
                                @PathVariable(name = "idGioHangCT") String idGioHangCT){
        iGioHangOnllineService.updateSoLuong(Integer.parseInt(soLuong),idGioHangCT);
        return "redirect:/gio-hang-onl";
    }

    @GetMapping("/xoa/{idGioHangCT}")
    public String xoa(@PathVariable(name = "idGioHangCT") String idGioHangCT) {
        iGioHangOnllineService.delete(idGioHangCT);
        return "redirect:/gio-hang-onl";
    }

    @PostMapping("/them-moi-gio-hang/{idSP}/{quantity}/{color}/{size}")
    @ResponseBody
    public void themGioHang(
            @PathVariable(name = "idSP") String idSP,
            @PathVariable(value = "color") String idMau,
            @PathVariable(value = "size") String idSize,
            @PathVariable(name = "quantity") String soLuongThem,Model model){

            ChiTietResponse chiTietSanPham = iChiTietSanPhamService.getChiTietSanPhamByMauSac_IdAndSize_IdAndIdSP(UUID.fromString(idMau),idSize,UUID.fromString(idSP));
            ChiTietSanPham sanPham = new ChiTietSanPham(chiTietSanPham.getId(),chiTietSanPham.getSanPham(),chiTietSanPham.getSoLuong(),chiTietSanPham.getTrangThai(),chiTietSanPham.getQrCode(),
                    chiTietSanPham.getMauSac(),chiTietSanPham.getSize());
            iGioHangOnllineService.addGioHang(sanPham,Integer.parseInt(soLuongThem));
    }
}