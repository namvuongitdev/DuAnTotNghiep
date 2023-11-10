package com.example.web.service;

import com.example.web.model.GioHangChiTiet;
import java.util.List;
import java.util.UUID;

public interface IGioHangCTService {

    GioHangChiTiet save(GioHangChiTiet gioHangChiTiet);

    List<GioHangChiTiet> getListGHCTTheoKhachHang(UUID idKH);

    GioHangChiTiet getTheoIdGioHangAndIdCTSP(UUID idGioHang, UUID idCTSP);
}
