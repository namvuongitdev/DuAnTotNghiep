package com.example.web.repository;
import com.example.web.model.HoaDon;
import com.example.web.model.HoaDonChiTiet;
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

public interface IHoaDonRepository extends JpaRepository<HoaDon , UUID> , JpaSpecificationExecutor<HoaDon> {

    @Override
    <S extends HoaDon> S save(S entity);

    @Query(value = "select hd.id , hd.ma  , hd.ngayTao  ,hd.trangThai from HoaDon hd  where hd.trangThai = :trangThaiHD")
    List<Object[]> findAllByHoaDonCho(@Param("trangThaiHD") Integer trangThaiHD);

    @Query(value = "select new com.example.web.response.HoaDonReponse(hdct.id ,  sp.ten ,sp.img ,  hdct.donGia , hdct.soLuong , ctsp.mauSac.ten , ctsp.size.ten , hd.hoTen , hd.sdt ,hd.diaChi , kh.email , ctsp.soLuong) from HoaDon hd left join  hd.hoaDonChiTiets hdct left join hdct.chiTietSanPham ctsp left join  ctsp.sanPham sp left join hd.khachHang kh where hd.id = ?1 and hdct.trangThai = ?2 and hd.trangThai = 0")
    List<HoaDonReponse> getSanPhamHD(UUID id , Integer trangThai);

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

    @Query(value = "select hdct from HoaDon hd left join hd.hoaDonChiTiets hdct " +
            "left join hdct.chiTietSanPham ctsp where hd.id = ?1 and hdct.trangThai=1")
    Page<HoaDonChiTiet> getHoaDonHuyChiTiet(UUID id ,Pageable pageable);

    @Query(value = "select * from hoa_Don where getDate() >= cast(ngay_nhan_hang as datetime) + 4" , nativeQuery = true)
    HoaDon ngayHetHanTraHang();

    @Query(value = "select hd from HoaDon hd where hd.trangThai<>0")
    Page<HoaDon> findAllHoaDonByTrangThaiKhacHoaDonCho(Pageable pageable);

    @Query("SELECT hd.id, hd.ma, hd.ngayTao, hd.tongTien, SUM(hdct.soLuong), hd.trangThai FROM HoaDon hd " +
            "JOIN HoaDonChiTiet hdct ON hdct.hoaDon.id = hd.id " +
            "WHERE hd.khachHang.taiKhoan = ?1 AND hd.loaiHoaDon = true " +
            "GROUP BY hd.ma, hd.ngayTao, hd.id, hd.tongTien, hd.trangThai,hdct.soLuong "+
            "HAVING hdct.soLuong in(SELECT hdct.soLuong from HoaDonChiTiet hdct where hdct.trangThai=0)")
    Page<Object[]> findHoaDonByTaiKhoan(String taiKhoan, Pageable pageable);

    @Query("SELECT hd.id, hd.ma, hd.ngayTao, hd.tongTien, SUM(hdct.soLuong), hd.trangThai FROM HoaDon hd " +
            "JOIN HoaDonChiTiet hdct ON hdct.hoaDon.id = hd.id " +
            "WHERE hd.khachHang.taiKhoan = ?1 AND hd.loaiHoaDon = true AND hd.trangThai = ?2 " +
            "GROUP BY hd.ma, hd.ngayTao, hd.id, hd.tongTien, hd.trangThai")
    Page<Object[]> findHoaDonByTrangThai(String taiKhoan, Integer trangThai, Pageable pageable);

    @Query("select hd from HoaDon hd join hd.khachHang kh where kh.id = ?1 and hd.id = ?2")
    HoaDon findHoaDonByKhachHang(UUID idKH ,UUID idHD);

    @Query(value = "\n" +
            "  select COUNT(id) from hoa_don where trang_thai=5  and Id in (\n" +
            " select id_hoa_don from lich_su_hoa_don where CONVERT(date,ngay_thao_tac) = CONVERT(date, GETDATE()))",nativeQuery = true)
    Integer tongHoaDonHuy();

    @Query(value = "\n" +
            "  select COUNT(id) from hoa_don where trang_thai=1  and Id in (\n" +
            " select id_hoa_don from lich_su_hoa_don where CONVERT(date,ngay_thao_tac) = CONVERT(date, GETDATE()))",nativeQuery = true)
    Integer tongHoaDonChoXacNhan();

    @Query(value = "\n" +
            "\t\t\tselect sum(tong_tien) from hoa_don where trang_thai = 4",nativeQuery = true)
    Integer tongDoanhThu();

    @Query(value = """
			select sum(tong_tien) from hoa_don where trang_thai = 4 and CONVERT(DATE,hoa_don.ngay_thanh_toan) = CONVERT(DATE,GETDATE())
""",nativeQuery = true)
    Double getDoanhThuTrongNgay();

    @Query(value = "select count(Id) from hoa_don where CONVERT(DATE,hoa_don.ngay_tao) = CONVERT(DATE,GETDATE())",nativeQuery = true)
    Integer tongHoaDon();

    @Query(value = "select sum(tong_tien) from hoa_don where trang_thai = 4 and MONTH(hoa_don.ngay_thanh_toan) = ?1",nativeQuery = true)
    Double getDoanhThuTheoThang(Integer thang);
}

