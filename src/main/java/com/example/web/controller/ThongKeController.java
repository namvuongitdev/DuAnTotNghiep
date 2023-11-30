package com.example.web.controller;

import com.example.web.model.ChiTietSanPham;
import com.example.web.service.IChiTietSanPhamService;
import com.example.web.service.IHoaDonChiTietService;
import com.example.web.service.IHoaDonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.time.Year;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/admin/thong-ke")
public class ThongKeController {

    @Autowired
    private IHoaDonChiTietService iHoaDonChiTietService;

    @Autowired
    private IHoaDonService iHoaDonService;

    @Autowired
    private IChiTietSanPhamService chiTietSanPhamService;

    @GetMapping("")
    public String hienThi(Model model){
        Integer soLuongDaBan = iHoaDonChiTietService.soLuongSPDaBan();
        model.addAttribute("soLuongDaBan",soLuongDaBan);
        Integer tongDoanhThu = iHoaDonService.tongDoanhThu();
        model.addAttribute("tongDoanhThu",tongDoanhThu);
        Integer tongHoaDon = iHoaDonService.tongHoaDon();
        model.addAttribute("tongHoaDon",tongHoaDon);
        Double doanhThuTrongNgay = iHoaDonService.getDoanhThuTrongNgay();
        model.addAttribute("doanhThuTrongNgay",doanhThuTrongNgay);
        List<ChiTietSanPham> get5SanPhamBanChay = chiTietSanPhamService.getTop5SanPhamBanChay();
        model.addAttribute("sanPhamBanChay",get5SanPhamBanChay);
        Integer tongHDHuy = iHoaDonService.tongHoaDonHuy();
        model.addAttribute("tongHDHuy",tongHDHuy);
        Integer tongHoaDonChoXacNhan = iHoaDonService.tongHoaDonChoXacNhan();
        model.addAttribute("tongHoaDonChoXacNhan",tongHoaDonChoXacNhan);
        int nam = Year.now().getValue();
        model.addAttribute("nam",nam);
        return "thongKe/thongKe";
    }

    @ResponseBody
    @GetMapping("/doanh-thu")
    public List<Double> doanhThu(){
        List<Double> listTongDoanhThu = new ArrayList<>();
        listTongDoanhThu.add(iHoaDonService.getDoanhThuTheoThang(1));
        listTongDoanhThu.add(iHoaDonService.getDoanhThuTheoThang(2));
        listTongDoanhThu.add(iHoaDonService.getDoanhThuTheoThang(3));
        listTongDoanhThu.add(iHoaDonService.getDoanhThuTheoThang(4));
        listTongDoanhThu.add(iHoaDonService.getDoanhThuTheoThang(5));
        listTongDoanhThu.add(iHoaDonService.getDoanhThuTheoThang(6));
        listTongDoanhThu.add(iHoaDonService.getDoanhThuTheoThang(7));
        listTongDoanhThu.add(iHoaDonService.getDoanhThuTheoThang(8));
        listTongDoanhThu.add(iHoaDonService.getDoanhThuTheoThang(9));
        listTongDoanhThu.add( iHoaDonService.getDoanhThuTheoThang(10));
        listTongDoanhThu.add( iHoaDonService.getDoanhThuTheoThang(11));
        listTongDoanhThu.add( iHoaDonService.getDoanhThuTheoThang(12));
        return listTongDoanhThu;
    }


}
