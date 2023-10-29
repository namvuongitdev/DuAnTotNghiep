package com.example.web.service;
import com.example.web.model.HoaDonChiTiet;

public interface IHoaDonChiTietService {

    HoaDonChiTiet addHoaDonChiTiet(String idCTSP , String idHD , Integer soLuong);

    String deleteSanPhamHoaDon(String idHDCT , String idKhacHang);

    String deleteSanPhamHoaDon2(String idHDCT);

    HoaDonChiTiet getHoaDonChiTiet(String id);

    String updateHoaDonChiTiet(String idHDCT , String soLuong  ,String idKhachHang);

    HoaDonChiTiet getOne(String id);

    String addSanPhamHoaDonChiTietKhiUpdate(String idCTSP , String idHD ,Integer soLuong);

   String updateSoLuongSanPhamHoaDonChiTietKhiUpdate(String idHDCT, String soLuong);

}
