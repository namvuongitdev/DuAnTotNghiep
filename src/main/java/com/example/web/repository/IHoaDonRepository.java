package com.example.web.repository;
import com.example.web.model.HoaDon;
import com.example.web.model.HoaDonChiTiet;
import com.example.web.response.HoaDonReponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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

    @Query(value = "select hd.id , hd.ma  , hd.ngayTao  ,hd.trangThai from HoaDon hd  where hd.trangThai = :trangThaiHD")
    Page<Object[]> findAllByHoaDonCho(@Param("trangThaiHD") Integer trangThaiHD , Pageable pageable);

    @Query(value = "select new com.example.web.response.HoaDonReponse(hdct.id ,  sp.ten ,sp.img ,  hdct.donGia , hdct.soLuong , ctsp.mauSac.ten , ctsp.size.ten , hd.hoTen , hd.sdt ,hd.diaChi , kh.email , ctsp.soLuong) from HoaDon hd left join  hd.hoaDonChiTiets hdct left join hdct.chiTietSanPham ctsp left join  ctsp.sanPham sp left join hd.khachHang kh where hd.id = ?1 and hdct.trangThai = ?2 and hd.trangThai = 0")
    List<HoaDonReponse> getSanPhamHD(UUID id , Integer trangThai);

    @Override
    Optional<HoaDon> findById(UUID uuid);

    @Query(value = "select  sum(hdct.donGia * hdct.soLuong)  from HoaDon hd left join hd.hoaDonChiTiets hdct where hd.id = ?1 and hdct.trangThai = 0 ")
    BigDecimal tongTien(UUID idHD);


    Page<HoaDon> findAll(Specification<HoaDon> hoaDonSpecification, Pageable pageable);

    @Query(value = "select hdct from HoaDon hd left join hd.hoaDonChiTiets hdct " +
            "left join hdct.chiTietSanPham ctsp where hd.id = ?1 and hdct.trangThai=0")
    Page<HoaDonChiTiet> getHoaDonChiTiet(UUID id ,Pageable pageable);

    @Query(value = "select hdct from HoaDon hd left join hd.hoaDonChiTiets hdct " +
            "left join hdct.chiTietSanPham ctsp where hd.id = ?1 and hdct.trangThai=1")
    Page<HoaDonChiTiet> getHoaDonHuyChiTiet(UUID id ,Pageable pageable);

    @Query(value = "select hd from HoaDon hd where hd.trangThai<>0")
    Page<HoaDon> findAll3(Pageable pageable);

    @Query("SELECT hd.id, hd.ma, hd.ngayTao, hd.tongTien, SUM(hdct.soLuong), hd.trangThai FROM HoaDon hd " +
            "JOIN HoaDonChiTiet hdct ON hdct.hoaDon.id = hd.id " +
            "WHERE hd.khachHang.taiKhoan = ?1 AND hd.loaiHoaDon = true " +
            "GROUP BY hd.ma, hd.ngayTao, hd.id, hd.tongTien, hd.trangThai")
    Page<Object[]> findHoaDonByTaiKhoan(String taiKhoan, Pageable pageable);

    @Query("SELECT hd.id, hd.ma, hd.ngayTao, hd.tongTien, SUM(hdct.soLuong), hd.trangThai FROM HoaDon hd " +
            "JOIN HoaDonChiTiet hdct ON hdct.hoaDon.id = hd.id " +
            "WHERE hd.khachHang.taiKhoan = ?1 AND hd.loaiHoaDon = true AND hd.trangThai = ?2 " +
            "GROUP BY hd.ma, hd.ngayTao, hd.id, hd.tongTien, hd.trangThai")
    Page<Object[]> findHoaDonByTrangThai(String taiKhoan, Integer trangThai, Pageable pageable);

    @Query("select hd from HoaDon hd join hd.khachHang kh where kh.id = ?1 and hd.id = ?2")
    HoaDon findHoaDonByKhachHang(UUID idKH ,UUID idHD);
}
