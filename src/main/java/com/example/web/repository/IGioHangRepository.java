package com.example.web.repository;

import com.example.web.model.GioHang;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.UUID;

public interface IGioHangRepository extends JpaRepository<GioHang, UUID> {
    @Query(value = """
    select gio_hang.id,gio_hang.id_khach_hang,gio_hang.ngay_tao,gio_hang.ngay_thanh_toan
    ,gio_hang.ten_nguoi_nhan,gio_hang.dia_chi,gio_hang.sdt,gio_hang.trang_thai from gio_hang where id_khach_hang=?1
    """,nativeQuery = true)
    GioHang getTheoIdKH(UUID idKH);
}
