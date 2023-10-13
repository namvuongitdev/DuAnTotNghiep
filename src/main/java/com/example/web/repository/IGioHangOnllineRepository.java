package com.example.web.repository;
import com.example.web.model.GioHangChiTiet;
import com.example.web.response.GioHangOnllineResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

public interface IGioHangOnllineRepository extends JpaRepository<GioHangChiTiet, UUID> {

    @Query(value = """
            select new com.example.web.response.GioHangOnllineResponse( gc.id,s.ten,a.ten,gc.donGia,gc.soLuong,m.ten,k.ten)
            from ChiTietSanPham c,Anh a,GioHang g,SanPham s,MauSac m,Size k,GioHangChiTiet gc,KhachHang kh
            where c.sanPham.id=s.id and c.mauSac.id=m.id
            and c.size.id=k.id and c.id=a.chiTietSanPham.id
            and gc.chiTietSanPham.id=c.id and g.id=gc.gioHang.id
            and g.khachHang.id=kh.id and g.khachHang.id = ?1
            """)
    Page<GioHangOnllineResponse> findAll(Pageable pageable,UUID idKhachHang);

    @Query(value = """
            select new com.example.web.response.GioHangOnllineResponse( gc.id,s.ten,a.ten,gc.donGia,gc.soLuong,m.ten,k.ten)
            from ChiTietSanPham c,Anh a,GioHang g,SanPham s,MauSac m,Size k,GioHangChiTiet gc,KhachHang kh
            where c.sanPham.id=s.id and c.mauSac.id=m.id
            and c.size.id=k.id and c.id=a.chiTietSanPham.id
            and gc.chiTietSanPham.id=c.id and g.id=gc.gioHang.id
            and g.khachHang.id=kh.id and g.khachHang.id = ?1
            """)
    List<GioHangOnllineResponse> findAll(UUID idKhachHang);

    @Transactional
    @Modifying
    @Query(value = "\n" +
            "update gio_hang_chi_tiet set so_luong = ?1 where Id = ?2",nativeQuery = true)
    void updateSoLuong(Integer soLuong, UUID idGioHangCT);

    @Transactional
    @Modifying
    @Query(value = "delete gio_hang_chi_tiet  where Id = ?1",nativeQuery = true)
    void delete (UUID idGioHangCT);
}
