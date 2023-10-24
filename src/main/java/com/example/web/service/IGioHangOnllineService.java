package com.example.web.service;

import com.example.web.model.ChatLieu;
import com.example.web.model.ChiTietSanPham;
import com.example.web.model.GioHang;
import com.example.web.response.ChiTietResponse;
import com.example.web.response.GioHangOnllineResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

public interface IGioHangOnllineService {
    Page<GioHangOnllineResponse> findAll(Pageable pageable, UUID idKhachHang);

    Page<GioHangOnllineResponse> page(Integer pageNo, Integer size,UUID idKhachHang);

    void updateSoLuong(Integer soLuong, UUID idGioHangCT);

    void delete (UUID idGioHangCT);

    String getTongTienTrongGio(UUID idKhachHang);

    void addGioHang( ChiTietSanPham chiTietSanPham, Integer soLuongThem);


}
