package com.example.web.repository;
import com.example.web.model.HoaDon;
import com.example.web.model.HoaDonChiTiet;
import com.example.web.response.HoaDonReponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface IHoaDonRepository extends JpaRepository<HoaDon , UUID> {

    @Override
    <S extends HoaDon> S save(S entity);

    @Query(value = "select hd.id , hd.ma , count(hdct), sum(hdct.donGia * hdct.soLuong) ,hd.trangThai  , hd.ngayTao from HoaDon hd left join hd.hoaDonChiTiets hdct  where hd.trangThai = :trangThaiHD and hdct.trangThai = :trangThaiHDCT group by hd.id , hd.ma , hd.trangThai , hd.ngayTao")
    Page<Object[]> findAllByTrangThai_hoaDon(@Param("trangThaiHD") Integer trangThaiHD , @Param("trangThaiHDCT") Integer trangThaiHDCT , Pageable pageable);

    @Query(value = "select new com.example.web.response.HoaDonReponse(hdct.id ,  sp.ten ,sp.img ,  hdct.donGia , hdct.soLuong , ctsp.mauSac.ten , ctsp.size.ten , hd.hoTen , hd.sdt ,hd.diaChi , kh.email , ctsp.soLuong) from HoaDon hd left join  hd.hoaDonChiTiets hdct left join hdct.chiTietSanPham ctsp left join  ctsp.sanPham sp left join hd.khachHang kh where hd.id = ?1 and hdct.trangThai = ?2")
    List<HoaDonReponse> getSanPhamHD(UUID id , Integer trangThai);

    @Override
    Optional<HoaDon> findById(UUID uuid);

<<<<<<< Updated upstream
=======
    @Query(value = "select  sum(hdct.donGia * hdct.soLuong)  from HoaDon hd left join hd.hoaDonChiTiets hdct where hd.id = ?1 and hdct.trangThai = 0 ")
    BigDecimal tongTien(UUID idHD);

    Page<HoaDon> findAll(Specification<HoaDon> hoaDonSpecification, Pageable pageable);

    @Query(value = "select * from hoa_don a join hoa_don_chi_tiet b on a.id = b.id_hoa_don \n" +
            "\tjoin chi_tiet_san_pham c on b.idctsp=c.id where a.id=?1",nativeQuery = true)
    HoaDonChiTiet getHoaDonChiTiet(UUID id );
>>>>>>> Stashed changes
}
