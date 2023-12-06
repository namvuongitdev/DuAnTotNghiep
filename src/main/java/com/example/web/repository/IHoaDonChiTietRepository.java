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

    @Query(value = "Select hdct From HoaDonChiTiet hdct where hdct.hoaDon.id = ?1 and hdct.trangThai=0")
    List<HoaDonChiTiet> getAllByIdHoaDon(UUID idHD);

    @Query(value = "select sum(hdct.so_luong) from hoa_don hd join hoa_don_chi_tiet hdct on hd.id = hdct.id_hoa_don  where hdct.trang_thai = 0 and MONTH(hd.ngay_tao) = MONTH(GETDATE()) and  hd.trang_thai <> 0 and hd.trang_thai <> 5" , nativeQuery = true)
    Integer soLuongSPDaBan();

}

