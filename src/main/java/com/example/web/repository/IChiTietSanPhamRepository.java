package com.example.web.repository;

import com.example.web.model.ChiTietSanPham;
import com.example.web.model.Size;
import com.example.web.response.ChiTietOnllineResponse;
import jakarta.transaction.Transactional;
import com.example.web.response.ChiTietResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface IChiTietSanPhamRepository extends JpaRepository<ChiTietSanPham, UUID>, JpaSpecificationExecutor<ChiTietSanPham> {

    @Query(value = "Select * from chi_tiet_san_pham where id=?1", nativeQuery = true)
    ChiTietSanPham getOne(UUID id);

    List<ChiTietSanPham> findBySanPham_Id(UUID id);

    @Query(value = "select * from chi_tiet_san_pham where idsanpham=?1", nativeQuery = true)
    List<ChiTietSanPham> listCTSPTheoIdSP(UUID idSP);

    @Query(value = "select * from ChiTietSanPham ctsp where ctsp.idmausac=?1 and ctsp.idsize = ?2 and ctsp.idsanpham = ?3", nativeQuery = true)
    Optional<ChiTietSanPham> getChiTietSanPhamByMauSac_IdAndSize_IdAndSanPham_Id(String mauSac_Id, String size, String sanPham_Id);

    @Query(value = "" +
            "select  new com.example.web.response.ChiTietOnllineResponse(ctsp.id,ctsp.soLuong,ctsp.trangThai) from ChiTietSanPham ctsp where ctsp.mauSac.id=?1 and ctsp.size.id = ?2 and ctsp.sanPham.id = ?3" +
            "")
    ChiTietOnllineResponse getChiTietSanPhamByMauSac_IdAndSize_IdAndSanPham_Id1(UUID mauSac_Id, String size, UUID sanPham_Id);

    //update lại toàn bộ các trường có trạng thái 0, vì là câu native query nên tên bảng sẽ lấy theo tên trong sql
    @Transactional
    @Modifying
    @Query(value = "update chi_tiet_san_pham set trangthai=0 where idsanpham = ?1", nativeQuery = true)
    void updateTT_0(UUID idSP);

    //update lại toàn bộ các trường có trạng thái 0, vì là câu native query nên tên bảng sẽ lấy theo tên trong sql
    @Transactional
    @Modifying
    @Query(value = "update chi_tiet_san_pham set trangthai=1 where idsanpham = ?1", nativeQuery = true)
    void updateTT_1(UUID idSP);

    @Query(value = "select new com.example.web.response.ChiTietResponse(ctsp.soLuong,ctsp.trangThai,ctsp.id,ctsp.mauSac,ctsp.sanPham,ctsp.size,ctsp.qrCode) from ChiTietSanPham ctsp where ctsp.mauSac.id=?1 and ctsp.size.id = ?2 and ctsp.sanPham.id = ?3")
    ChiTietResponse getChiTietSanPhamByMauSac_IdAndSize_IdAndIdSP(UUID mauSac_Id, String size, UUID sanPham_Id);

    @Query(value = "select ctsp.size from ChiTietSanPham ctsp join ctsp.sanPham sp join ctsp.mauSac ms  where sp.id = ?1 and ms.id = ?2")
    List<Size> findSizeBySanPham_idAndMauSac_id(UUID idSP, UUID idMS);

    @Query(value = "select sp.img ,sp.ten , sp.giaban ,SUM(hdct.so_luong) from chi_tiet_san_pham ctsp join hoa_don_chi_tiet hdct on ctsp.id = hdct.idctsp\n" +
            "join san_pham sp on sp.id = ctsp.idsanpham\n" +
            "where month(hdct.ngay_tao) = month(getDate()) and hdct.trang_thai = 0\n" +
            "group by sp.img ,sp.ten , sp.giaban\n" +
            "order by SUM(hdct.so_luong) desc", nativeQuery = true)
    List<Object[]> getTop5SanPhamBanChay();

    @Query(value = "select ctsp.id from ChiTietSanPham ctsp where ctsp.qrCode = ?1")
    Optional<UUID> findByQrCode(String qrCode);

    @Query(value = "select ctsp from ChiTietSanPham ctsp where ctsp.mauSac.id = ?1 and ctsp.size.id = ?2 and ctsp.sanPham.id = ?3")
    ChiTietSanPham checkSizeMauSac(UUID mauSac, String kichCo, UUID idSanPham);

    @Query(value = "select sp.ma  , ctsp.qrCode from ChiTietSanPham ctsp join ctsp.sanPham sp  where ctsp.id = ?1")
    Object[] findQrCodeById(UUID uuid);

    @Query(value = """ 
    select * from chi_tiet_san_pham where id in (select gio_hang_chi_tiet.idctsp from gio_hang_chi_tiet where id = ?1)
""",nativeQuery = true)
    ChiTietSanPham getSL(String idGioHang);
}
