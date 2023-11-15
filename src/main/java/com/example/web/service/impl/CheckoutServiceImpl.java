package com.example.web.service.impl;

import com.example.web.Config.status.HoaDonStatus;
import com.example.web.model.HoaDon;
import com.example.web.model.KhachHang;
import com.example.web.repository.IHoaDonRepository;
import com.example.web.request.CheckoutRequest;
import com.example.web.service.CheckoutService;
import com.example.web.service.IGioHangOnllineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.util.Date;

@Service
public class CheckoutServiceImpl implements CheckoutService {

    @Autowired
    private IGioHangOnllineService gioHangOnllineService;

    @Autowired
    private IHoaDonRepository hoaDonRepository;

    @Override
    public HoaDon createOrder(KhachHang khachHang, CheckoutRequest request) {
        Date date = java.util.Calendar.getInstance().getTime();
        BigDecimal tongTien = gioHangOnllineService.tongTienSanPhamTrongGioHang(khachHang.getId());
        String diaChi = request.getDiaChi() + "," + request.getPhuongXa() + "," + request.getQuanHuyen() + "," + request.getThanhPho();
        HoaDon hoaDon = HoaDon.builder()
                .trangThai(HoaDonStatus.CHO_XAC_NHAN)
                .ngayTao(date)
                .ma("HD" + (hoaDonRepository.findAll().size() + 1))
                .loaiHoaDon(true)
                .hoTen(request.getHoTen())
                .khachHang(khachHang)
                .diaChi(diaChi)
                .sdt(request.getSoDienThoai())
                .moTa(request.getGhiChu())
                .phuongThucThanhToan(request.getPhuongThucThanhToan())
                .tongTien(tongTien)
                .build();
        return hoaDonRepository.save(hoaDon);
    }
}
