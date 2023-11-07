package com.example.web.service;

import com.example.web.model.HoaDonChiTiet;
import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

public interface IHoaDonChiTietService {

    HoaDonChiTiet addHoaDonChiTiet(String idCTSP, String idHD, Integer soLuong);

    HoaDonChiTiet deleteSanPhamHoaDon(String idHDCT);

    String deleteSanPhamHoaDon2(String idHDCT);

    HoaDonChiTiet getHoaDonChiTiet(String id);

    HoaDonChiTiet updateHoaDonChiTiet(String idHDCT, String soLuong);

    HoaDonChiTiet getOne(String id);

    String addSanPhamHoaDonChiTietKhiUpdate(String idCTSP, String idHD, Integer soLuong);

    String updateSoLuongSanPhamHoaDonChiTietKhiUpdate(String idHDCT, String soLuong);

    BigDecimal tongTienHDCT(UUID idHD);

    List<HoaDonChiTiet> getAllByIdHoaDon(UUID idHD);

}
