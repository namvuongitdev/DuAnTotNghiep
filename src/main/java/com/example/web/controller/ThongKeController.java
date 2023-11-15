package com.example.web.controller;

import com.example.web.model.ChiTietSanPham;
import com.example.web.service.IChiTietSanPhamService;
import com.example.web.service.IHoaDonChiTietService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/admin/thong-ke")
public class ThongKeController {

    @Autowired
    private IHoaDonChiTietService iHoaDonChiTietService;

    @Autowired
    private IChiTietSanPhamService chiTietSanPhamService;

    @GetMapping("")
    public String hienThi(Model model){
        Integer soLuongDaBan = iHoaDonChiTietService.soLuongSPDaBan();
        model.addAttribute("soLuongDaBan",soLuongDaBan);
        Integer tongDoanhThu = iHoaDonChiTietService.tongDoanhThu();
        model.addAttribute("tongDoanhThu",tongDoanhThu);
        Integer tongHoaDon = iHoaDonChiTietService.tongHoaDon();
        model.addAttribute("tongHoaDon",tongHoaDon);
        Double doanhThuTrongNgay = iHoaDonChiTietService.getDoanhThuTrongNgay();
        model.addAttribute("doanhThuTrongNgay",doanhThuTrongNgay);
        List<ChiTietSanPham> get5SanPhamBanChay = chiTietSanPhamService.getTop5SanPhamBanChay();
        model.addAttribute("sanPhamBanChay",get5SanPhamBanChay);
        return "thongKe/thongKe";
    }

    @ResponseBody
    @GetMapping("/doanh-thu")
    public List<Double> doanhThu(){
        List<Double> listTongDoanhThu = new ArrayList<>();
        listTongDoanhThu.add( iHoaDonChiTietService.getDoanhThuTheoThang(1));
        listTongDoanhThu.add(iHoaDonChiTietService.getDoanhThuTheoThang(2));
        listTongDoanhThu.add(iHoaDonChiTietService.getDoanhThuTheoThang(3));
        listTongDoanhThu.add(iHoaDonChiTietService.getDoanhThuTheoThang(4));
        listTongDoanhThu.add(iHoaDonChiTietService.getDoanhThuTheoThang(5));
        listTongDoanhThu.add(iHoaDonChiTietService.getDoanhThuTheoThang(6));
        listTongDoanhThu.add(iHoaDonChiTietService.getDoanhThuTheoThang(7));
        listTongDoanhThu.add(iHoaDonChiTietService.getDoanhThuTheoThang(8));
        listTongDoanhThu.add(iHoaDonChiTietService.getDoanhThuTheoThang(9));
        listTongDoanhThu.add( iHoaDonChiTietService.getDoanhThuTheoThang(10));
        listTongDoanhThu.add( iHoaDonChiTietService.getDoanhThuTheoThang(11));
        listTongDoanhThu.add( iHoaDonChiTietService.getDoanhThuTheoThang(12));
        return listTongDoanhThu;
    }


}
