package com.example.web.service;
import com.example.web.model.HoaDonChiTiet;

public interface IHoaDonChiTietService {

    String  addHoaDonChiTiet(String idCTSP , String idHD ,Integer soLuong);

    String deleteSanPhamHoaDon(String idHDCT);

    HoaDonChiTiet getHoaDonChiTiet(String id);

    String updateHoaDonChiTiet(String idHDCT , String soLuong);

    HoaDonChiTiet getOne(String id);

}
