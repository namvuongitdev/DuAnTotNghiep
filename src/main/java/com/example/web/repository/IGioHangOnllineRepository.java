package com.example.web.repository;

import com.example.web.model.GioHang;
import com.example.web.model.GioHangChiTiet;
import com.example.web.response.GioHangReponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.UUID;

public interface IGioHangOnllineRepository extends JpaRepository<GioHangChiTiet, UUID> {

    @Query(value = "select " +
            " ghct.id as idGHCT ,sp.img as img ,sp.id as idSP , ms.id as idMS, sp.ten as tenSanPham ," +
            " sp.giaban as giaBanSanPham , ghct.so_luong as soLuong , ms.ten as mauSac , kc.ten as kichCo, ctsp.id as idCTSP, " +
            " kmct.don_gia_sau_khi_giam as donGiaSauKhiGiam , kmct.trang_thai as trangThaiKMCT , km.trang_thai as trangThaiKM  from gio_hang_chi_tiet ghct left join gio_hang gh on ghct.id_gio_hang = gh.id" +
            " left join chi_tiet_san_pham ctsp on ghct.idctsp = ctsp.id" +
            " left join mau_sac ms on ms.id = ctsp.idmausac " +
            " left join kich_co kc on kc.id = ctsp.idsize " +
            " left join san_pham sp on ctsp.idsanpham = sp.id" +
            " left join ctsp_khuen_mai kmct on sp.id = kmct.id_san_pham" +
            " left join khuyen_mai km on kmct.id_khuyen_mai = km.id" +
            " left join khach_hang kh on kh.id = gh.id_khach_hang where kh.id = ?1", nativeQuery = true)
    List<GioHangReponse> findAll(UUID idKH);

    @Transactional
    @Modifying
    @Query(value =
            "update gio_hang_chi_tiet set so_luong = ?1 where Id = ?2", nativeQuery = true)
    void updateSoLuong(Integer soLuong, String idGioHangCT);

    @Transactional
    @Modifying
    @Query(value = "delete gio_hang_chi_tiet  where Id = ?1", nativeQuery = true)
    void delete(String idGioHangCT);

    GioHang save(GioHang gioHang);

    @Query(value = "select count(ctsp)from GioHangChiTiet ghct join ghct.gioHang gh join ghct.chiTietSanPham ctsp join gh.khachHang kh where kh.id = ?1")
    Integer countSanPhamTrongGioHangByKhachHang_id(UUID idKH);

    @Transactional
    @Modifying
    @Query(value = "delete from GioHangChiTiet ghct where ghct.gioHang.id in (select gh.id from GioHang gh where gh.khachHang.id = ?1)")
    void deleteAllGioHangChiTiet(UUID idKH);

    @Query(value = "select ghct from GioHangChiTiet ghct left join  ghct.gioHang gh left join gh.khachHang kh where kh.id =?1")
    List<GioHangChiTiet> findGioHangChiTietByKhachHang_id(UUID idKH);
}
