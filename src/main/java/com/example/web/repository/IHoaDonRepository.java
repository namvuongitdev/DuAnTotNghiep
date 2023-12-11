package com.example.web.repository;

import com.example.web.model.HoaDon;
import com.example.web.response.HoaDonChiTietReponse;
import com.example.web.response.HoaDonReponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface IHoaDonRepository extends JpaRepository<HoaDon, UUID>, JpaSpecificationExecutor<HoaDon> {

    @Override
    <S extends HoaDon> S save(S entity);

    @Query(value = "select hd.id , hd.ma  , hd.ngayTao  ,hd.trangThai from HoaDon hd  where hd.trangThai = :trangThaiHD")
    List<Object[]> findAllByHoaDonCho(@Param("trangThaiHD") Integer trangThaiHD);

    @Query(value = "select new com.example.web.response.HoaDonReponse(hdct.id ,  sp.ten ,sp.img ,  hdct.donGia , hdct.soLuong , ctsp.mauSac.ten , ctsp.size.ten , hd.hoTen , hd.sdt ,hd.diaChi , kh.email , ctsp.soLuong) from HoaDon hd left join  hd.hoaDonChiTiets hdct left join hdct.chiTietSanPham ctsp left join  ctsp.sanPham sp left join hd.khachHang kh where hd.id = ?1 and hdct.trangThai = ?2 and hd.trangThai = 0")
    List<HoaDonReponse> getSanPhamHD(UUID id, Integer trangThai);

    @Override
    Optional<HoaDon> findById(UUID uuid);

    @Query(value = "select  sum(hdct.donGia * hdct.soLuong)  from HoaDon hd left join hd.hoaDonChiTiets hdct where hd.id = ?1 and hdct.trangThai = 0 ")
    BigDecimal tongTien(UUID idHD);

    @Query(value = "select new com.example.web.response.HoaDonChiTietReponse(hdct.id , sp.ten , ms.ten , s.ten , hdct.trangThai , sp.img , hdct.soLuong , hdct.donGia , ctsp.soLuong) from HoaDon hd left join hd.hoaDonChiTiets hdct " +
            "left join hdct.chiTietSanPham ctsp left join ctsp.sanPham sp left join" +
            " ctsp.size s left join ctsp.mauSac ms where hd.id = ?1 and hdct.trangThai <> 1  order by hdct.ngayTao asc")
    List<HoaDonChiTietReponse> findAllHoaDonChiTietByHoaDon_id(UUID id);

    @Query(value = "select new com.example.web.response.HoaDonChiTietReponse(hdct.id , sp.ten , ms.ten , s.ten , hdct.trangThai , sp.img , hdct.soLuong , hdct.donGia , ctsp.soLuong) from HoaDon hd left join hd.hoaDonChiTiets hdct " +
            "left join hdct.chiTietSanPham ctsp left join ctsp.sanPham sp left join" +
            " ctsp.size s left join ctsp.mauSac ms where hd.id = ?1 and hdct.trangThai = 0")
    List<HoaDonChiTietReponse> isCheckSanPhamTrongHoaDon(UUID id);

    @Query(value = "select new com.example.web.response.HoaDonChiTietReponse(hdct.id , sp.ten , ms.ten , s.ten , hdct.trangThai , sp.img , hdct.soLuong , hdct.donGia , ctsp.soLuong) from HoaDon hd left join hd.hoaDonChiTiets hdct " +
            "left join hdct.chiTietSanPham ctsp left join ctsp.sanPham sp left join" +
            " ctsp.size s left join ctsp.mauSac ms where hdct.id = ?1")
    HoaDonChiTietReponse findHoaDonChiTietByHoaDon_id(UUID id);

    @Query(value = "select * from hoa_don where getDate() >= cast(ngay_nhan_hang as datetime) + 4  and trang_thai= 4 and id = ?1", nativeQuery = true)
    HoaDon ngayHetHanTraHang(String id);

    @Query(value = "select hd from HoaDon hd where hd.trangThai<>0 and hd.trangThai <> 10")
    Page<HoaDon> findAllHoaDonByTrangThaiKhacHoaDonCho(Pageable pageable);

    @Query(value = "select hd.id , hd.ma , hd.hoTen , sum(hdct.soLuong * hdct.donGia) , hdct.ngayTao , sum(hdct.soLuong) from HoaDon hd join hd.hoaDonChiTiets hdct where hdct.trangThai = 2 group by hd.id , hd.ma , hd.hoTen , hdct.ngayTao")
    Page<Object[]> findHoaDonHoanTien(Pageable pageable);

    @Query("SELECT hd.id, hd.ma, hd.ngayTao, SUM(hdct.donGia * hdct.soLuong), SUM(hdct.soLuong), hd.trangThai , hd.phiVanChuyen FROM HoaDon hd " +
            "JOIN hd.hoaDonChiTiets hdct join hd.khachHang kh " +
            "WHERE kh.taiKhoan = ?1 AND hd.loaiHoaDon = true AND hdct.trangThai=0" +
            "group by hd.id, hd.ma, hd.ngayTao, hd.trangThai , hd.phiVanChuyen")
    Page<Object[]> findHoaDonByTaiKhoan(String taiKhoan, Pageable pageable);

    @Query("SELECT hd.id, hd.ma, hd.ngayTao, SUM(hdct.donGia * hdct.soLuong), SUM(hdct.soLuong), hd.trangThai , hd.phiVanChuyen FROM HoaDon hd " +
            "JOIN hd.hoaDonChiTiets hdct join hd.khachHang kh " +
            "WHERE kh.taiKhoan = ?1 AND hd.trangThai = ?2 AND hd.loaiHoaDon = true AND hdct.trangThai=0" +
            "group by hd.id, hd.ma, hd.ngayTao, hd.trangThai , hd.phiVanChuyen")
    Page<Object[]> findHoaDonByTrangThai(String taiKhoan, Integer trangThai, Pageable pageable);


    @Query("select hd from HoaDon hd join hd.khachHang kh where kh.id = ?1 and hd.id = ?2")
    HoaDon findHoaDonByKhachHang(UUID idKH, UUID idHD);

    @Query(value = "select count(hd.id) from hoa_don hd  join lich_su_hoa_don lshd on hd.id = lshd.id_hoa_don\n" +
            "where lshd.thao_tac = 5 and CONVERT(date  ,lshd.ngay_thao_tac) = CONVERT(date , GETDATE())", nativeQuery = true)
    Integer tongHoaDonHuy();

    @Query(value = "select count(hd.id) from HoaDon hd where  hd.trangThai = 1")
    Integer tongHoaDonChoXacNhan();

    @Query(value = "\n" +
            "\t\t\tselect sum(tong_tien) from hoa_don where trang_thai = 4", nativeQuery = true)
    Integer tongDoanhThu();

    @Query(value = "select sum(hd.tong_tien) from hoa_don hd join lich_su_hoa_don lshd on hd.id = lshd.id_hoa_don where lshd.thao_tac = 4 and CONVERT(DATE,lshd.ngay_thao_tac) = CONVERT(DATE,GETDATE())"
            , nativeQuery = true)
    Double getDoanhThuTrongNgay();

    @Query(value = "select count(hd.id) from hoa_don hd join lich_su_hoa_don lshd on hd.id = lshd.id_hoa_don where CONVERT(DATE,lshd.ngay_thao_tac) = CONVERT(DATE,GETDATE()) and lshd.thao_tac = 4", nativeQuery = true)
    Integer tongHoaDon();

    @Query(value = "select sum(hd.tong_tien) from hoa_don hd join lich_su_hoa_don lshd on hd.id = lshd.id_hoa_don where lshd.thao_tac = 4 and MONTH(lshd.ngay_thao_tac) =?1 and  YEAR(hd.ngay_tao) = ?2  and YEAR(lshd.ngay_thao_tac) = YEAR(GETDATE())", nativeQuery = true)
    Double getDoanhThuTheoThang(Integer thang, Integer nam);

    @Query(value = " select distinct YEAR(hd.ngay_tao) from hoa_don hd order by YEAR(hd.ngay_tao) asc", nativeQuery = true)
    List<Integer> getNam();
}

