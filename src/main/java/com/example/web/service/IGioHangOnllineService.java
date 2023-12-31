package com.example.web.service;

import com.example.web.model.ChiTietSanPham;
import com.example.web.model.GioHangChiTiet;
import com.example.web.response.GioHangReponse;
import org.springframework.ui.Model;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

public interface IGioHangOnllineService {
    List<GioHangReponse> findAll(UUID idKhachHang);

   // Page<GioHangOnllineResponse> page(Integer pageNo, Integer size,UUID idKhachHang);

    void updateSoLuong(Integer soLuong, String idGioHangCT);

    void delete (String idGioHangCT);

   // String getTongTienTrongGio(UUID idKhachHang);

    void addGioHang( ChiTietSanPham chiTietSanPham, Integer soLuongThem);

    Integer countSoLuongSPTrongGioHang(UUID id);

    void clearAllGioHangChiTietByKhachHang_id(UUID idKH);

    List<GioHangChiTiet> getGHCTByKhachHang_id(UUID idKh);

    BigDecimal tongTienSanPhamTrongGioHang(UUID idKH);


}
