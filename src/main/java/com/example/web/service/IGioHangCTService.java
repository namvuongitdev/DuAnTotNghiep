package com.example.web.service;

import com.example.web.model.ChiTietSanPham;
import com.example.web.model.GioHangChiTiet;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface IGioHangCTService {

    GioHangChiTiet save(GioHangChiTiet gioHangChiTiet);

    List<GioHangChiTiet> getListGHCTTheoKhachHang(UUID idKH);

    GioHangChiTiet getTheoIdGioHangAndIdCTSP(UUID idGioHang, UUID idCTSP);
}
