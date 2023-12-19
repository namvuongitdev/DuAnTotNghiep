package com.example.web.controller;

import com.example.web.model.ChiTietSanPham;
import com.example.web.service.IChiTietSanPhamService;
import com.example.web.service.IHoaDonChiTietService;
import com.example.web.service.IHoaDonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.xml.crypto.Data;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Year;
import java.util.ArrayList;
import java.util.Date;
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
    public String hienThi(Model model) {
        Integer soLuongDaBan = iHoaDonChiTietService.soLuongSPDaBan();
        model.addAttribute("soLuongDaBan", soLuongDaBan);
        Integer tongDoanhThu = iHoaDonService.tongDoanhThu();
        model.addAttribute("tongDoanhThu", tongDoanhThu);
        Integer tongHoaDon = iHoaDonService.tongHoaDon();
        model.addAttribute("tongHoaDon", tongHoaDon);
        Double doanhThuTrongNgay = iHoaDonService.getDoanhThuTrongNgay();
        model.addAttribute("doanhThuTrongNgay", doanhThuTrongNgay);
        List<Object[]> get5SanPhamBanChay = chiTietSanPhamService.getTop5SanPhamBanChay();
        model.addAttribute("sanPhamBanChay", get5SanPhamBanChay);
        Integer tongHDHuy = iHoaDonService.tongHoaDonHuy();
        model.addAttribute("tongHDHuy", tongHDHuy);
        Integer tongHoaDonChoXacNhan = iHoaDonService.tongHoaDonChoXacNhan();
        model.addAttribute("tongHoaDonChoXacNhan", tongHoaDonChoXacNhan);
        int nam = Year.now().getValue();
        model.addAttribute("nam", nam);
        model.addAttribute("getNams", iHoaDonService.getNams());

        return "thongKe/thongKe";
    }

    @ResponseBody
    @GetMapping("/ngay/{day}/{month}/{year}")
    public List<Double> thongKe(@PathVariable String day, @PathVariable String month , @PathVariable String year){
        String nam = day +"/"+month+"/"+year;
        List<Double> list = new ArrayList<>();
        Double namHienThi = 0.0;
        try {
            // Chuyển đổi chuỗi thành đối tượng Date
            Date date = new SimpleDateFormat("dd/MM/yyyy").parse(nam);
            namHienThi =  iHoaDonService.getDoanhThuTrongNgayDangChon(date);
            list.add(namHienThi);
        } catch (ParseException e) {
            // Xử lý nếu có lỗi chuyển đổi
            e.printStackTrace();
        }

        return list;
    }

    @ResponseBody
    @GetMapping("/date1/{day}/{month}/{year}")
    public List<Integer> thongKeHDThanhCong(@PathVariable String day, @PathVariable String month , @PathVariable String year){
        String nam = day +"/"+month+"/"+year;
        List<Integer> list = new ArrayList<>();
        Integer tong = 0;
        try {
            // Chuyển đổi chuỗi thành đối tượng Date
            Date date = new SimpleDateFormat("dd/MM/yyyy").parse(nam);
            tong =  iHoaDonService.tongHoaDonDangChon(date);
            list.add(tong);
        } catch (ParseException e) {
            // Xử lý nếu có lỗi chuyển đổi
            e.printStackTrace();
        }

        return list;
    }

    @ResponseBody
    @GetMapping("/huy/{day}/{month}/{year}")
    public List<Integer> huy(@PathVariable String day, @PathVariable String month , @PathVariable String year){
        String nam = day +"/"+month+"/"+year;
        List<Integer> list = new ArrayList<>();
        Integer tong = 0;
        try {
            // Chuyển đổi chuỗi thành đối tượng Date
            Date date = new SimpleDateFormat("dd/MM/yyyy").parse(nam);
            tong =  iHoaDonService.tongHoaDonHuyDangChon(date);
            list.add(tong);
        } catch (ParseException e) {
            // Xử lý nếu có lỗi chuyển đổi
            e.printStackTrace();
        }

        return list;
    }

    @ResponseBody
    @GetMapping("/doanh-thu/{nam}")
    public List<Double> doanhThu(@PathVariable Integer nam) {
        List<Double> listTongDoanhThu = new ArrayList<>();
        listTongDoanhThu.add(iHoaDonService.getDoanhThuTheoThang(1, nam));
        listTongDoanhThu.add(iHoaDonService.getDoanhThuTheoThang(2, nam));
        listTongDoanhThu.add(iHoaDonService.getDoanhThuTheoThang(3, nam));
        listTongDoanhThu.add(iHoaDonService.getDoanhThuTheoThang(4, nam));
        listTongDoanhThu.add(iHoaDonService.getDoanhThuTheoThang(5, nam));
        listTongDoanhThu.add(iHoaDonService.getDoanhThuTheoThang(6, nam));
        listTongDoanhThu.add(iHoaDonService.getDoanhThuTheoThang(7, nam));
        listTongDoanhThu.add(iHoaDonService.getDoanhThuTheoThang(8, nam));
        listTongDoanhThu.add(iHoaDonService.getDoanhThuTheoThang(9, nam));
        listTongDoanhThu.add(iHoaDonService.getDoanhThuTheoThang(10, nam));
        listTongDoanhThu.add(iHoaDonService.getDoanhThuTheoThang(11, nam));
        listTongDoanhThu.add(iHoaDonService.getDoanhThuTheoThang(12, nam));
        return listTongDoanhThu;
    }


}
