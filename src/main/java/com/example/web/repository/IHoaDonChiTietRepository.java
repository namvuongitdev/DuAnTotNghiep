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

    @Query(value = """
			select Sum(so_luong) from hoa_don_chi_tiet,hoa_don
			 where hoa_don_chi_tiet.trang_thai = 4 and hoa_don.trang_thai=4 
			 and hoa_don.id =hoa_don_chi_tiet.id_hoa_don 
			 and CONVERT(DATE,hoa_don.ngay_thanh_toan) = CONVERT(DATE,GETDATE())
""",nativeQuery = true)
    Integer soLuongSPDaBan();

    @Query(value = " select count(Id) from hoa_don where CONVERT(DATE,hoa_don.ngay_tao) = CONVERT(DATE,GETDATE())",nativeQuery = true)
    Integer tongHoaDon();
}

