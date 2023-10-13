package com.example.web.repository;
import com.example.web.model.HoaDon;
import com.example.web.model.HoaDonChiTiet;
import com.example.web.response.HoaDonReponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface IHoaDonRepository extends JpaRepository<HoaDon , UUID> , JpaSpecificationExecutor<HoaDon> {

    @Override
    <S extends HoaDon> S save(S entity);

    @Query(value = "select hd.id , hd.ma  , hd.ngayTao  ,hd.trangThai from HoaDon hd left join hd.hoaDonChiTiets hdct  where hd.trangThai = :trangThaiHD")
    Page<Object[]> findAllByHoaDonCho(@Param("trangThaiHD") Integer trangThaiHD , Pageable pageable);

    @Query(value = "select new com.example.web.response.HoaDonReponse(hdct.id ,  sp.ten ,sp.img ,  hdct.donGia , hdct.soLuong , ctsp.mauSac.ten , ctsp.size.ten , hd.hoTen , hd.sdt ,hd.diaChi , kh.email , ctsp.soLuong) from HoaDon hd left join  hd.hoaDonChiTiets hdct left join hdct.chiTietSanPham ctsp left join  ctsp.sanPham sp left join hd.khachHang kh where hd.id = ?1 and hdct.trangThai = ?2")
    List<HoaDonReponse> getSanPhamHD(UUID id , Integer trangThai);

    @Override
    Optional<HoaDon> findById(UUID uuid);

    @Query(value = "select  sum(hdct.donGia * hdct.soLuong)  from HoaDon hd left join hd.hoaDonChiTiets hdct where hd.id = ?1 and hdct.trangThai = 0 ")
    BigDecimal tongTien(UUID idHD);

    Page<HoaDon> findAll(Specification<HoaDon> hoaDonSpecification, Pageable pageable);

    @Query(value = "select hdct from HoaDon hd left join hd.hoaDonChiTiets hdct " +
            "left join hdct.chiTietSanPham ctsp where hd.id = ?1")
    Page<HoaDonChiTiet> getHoaDonChiTiet(UUID id ,Pageable pageable);

    @Query(value = "update hoa_don set ho_ten =?1,dia_chi=?2,sdt=?3,tong_tien=?4 where id=?5",nativeQuery = true)
    String updateHoaDonById(String hoTen,String diaChi,String sdt,String tongTien,String idHd);

}
