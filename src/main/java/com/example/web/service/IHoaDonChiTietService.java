package com.example.web.service;

import com.example.web.model.HoaDonChiTiet;
import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

public interface IHoaDonChiTietService {

    HoaDonChiTiet addHoaDonChiTiet(UUID idCTSP, UUID idHD, Integer soLuong);

    HoaDonChiTiet deleteSanPhamHoaDon(String idHDCT);

    HoaDonChiTiet getHoaDonChiTiet(String id);

    HoaDonChiTiet updateHoaDonChiTiet(String idHDCT, String soLuong);

    HoaDonChiTiet getOne(String id);


    BigDecimal tongTienHDCT(UUID idHD);

    List<HoaDonChiTiet> getAllByIdHoaDon(UUID idHD);

}
