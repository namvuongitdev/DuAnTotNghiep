package com.example.web.service;
import com.example.web.model.ChiTietSanPham;
import com.example.web.model.HoaDon;

public interface ILichSuHoaDonService {

    void create(HoaDon hoaDon , ChiTietSanPham chiTietSanPham , Integer trangThai);
}
