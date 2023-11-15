package com.example.web.repository;
import com.example.web.model.HoaDonChiTiet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface IHoaDonChiTietRepository extends JpaRepository<HoaDonChiTiet , UUID> {

    @Override
    <S extends HoaDonChiTiet> S save(S entity);

    @Override
    void deleteById(UUID uuid);

    @Override
    Optional<HoaDonChiTiet> findById(UUID uuid);

    HoaDonChiTiet findByChiTietSanPham_IdAndAndHoaDon_IdAndTrangThai(UUID idCtsp , UUID idHD ,Integer trangThai);

    @Query(value = "update hoa_don_chi_tiet set so_luong =?2 where id_hoa_don=?1",nativeQuery = true)
    String updateHoaDonChiTietByIdHoaDon(String idHd,String soLuong);

    @Query(value = "Select hdct From HoaDonChiTiet hdct where hdct.hoaDon.id = ?1")
    List<HoaDonChiTiet> getAllByIdHoaDon(UUID idHD);

    @Query(value = "select Sum(so_luong) from hoa_don_chi_tiet",nativeQuery = true)
    Integer soLuongSPDaBan();

    @Query(value = "SELECT \n" +
            "  SUM([don_gia] * [so_luong])\n" +
            "FROM [DUANTN].[dbo].[hoa_don_chi_tiet]",nativeQuery = true)
    Integer tongDoanhThu();

    @Query(value = " select count(Id) from hoa_don_chi_tiet",nativeQuery = true)
    Integer tongHoaDon();

    @Query(value = "select Sum(don_gia *so_luong ) from hoa_don_chi_tiet,hoa_don where hoa_don_chi_tiet.id_hoa_don = hoa_don.Id and \n" +
            "CONVERT(DATE,hoa_don.ngay_thanh_toan) = CONVERT(DATE,GETDATE())",nativeQuery = true)
    Double getDoanhThuTrongNgay();

    @Query(value = "select Sum(don_gia *so_luong ) from hoa_don_chi_tiet,hoa_don where hoa_don_chi_tiet.id_hoa_don = hoa_don.Id and \n" +
            "MONTH(hoa_don.ngay_thanh_toan) = ?1",nativeQuery = true)
    Double getDoanhThuTheoThang(Integer thang);
}
