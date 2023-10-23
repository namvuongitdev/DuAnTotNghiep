package com.example.web.repository;

import com.example.web.model.ChiTietSanPham;
import com.example.web.model.GioHang;
import com.example.web.model.GioHangChiTiet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface IGioHangCTRepository extends JpaRepository<GioHangChiTiet, UUID> {
    GioHangChiTiet save(GioHangChiTiet gioHangChiTiet);

    @Query(value = "\n" +
            "select gio_hang_chi_tiet.* from gio_hang_chi_tiet,gio_hang where gio_hang_chi_tiet.id_gio_hang=gio_hang.id and gio_hang.id_khach_hang=?1",nativeQuery = true)
    List<GioHangChiTiet> getListGHCTTheoKhachHang(UUID idKH);

    @Query(value = "select * from gio_hang_chi_tiet where id_gio_hang=?1 and idctsp=?2",nativeQuery = true)
    GioHangChiTiet getTheoIdGioHangAndIdCTSP(UUID idGioHang, UUID idCTSP);


}
