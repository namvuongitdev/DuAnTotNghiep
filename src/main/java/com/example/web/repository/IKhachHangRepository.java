package com.example.web.repository;
import com.example.web.model.KhachHang;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.Optional;
import java.util.UUID;

public interface IKhachHangRepository extends JpaRepository<KhachHang , UUID> {

    @Override
    Page<KhachHang> findAll(Pageable pageable);

    @Override
    Optional<KhachHang> findById(UUID uuid);

    @Query(value = "select kh from KhachHang kh where kh.hoTen like ?1 or kh.sdt like ?1 or kh.taiKhoan like ?1")
    Page<KhachHang> findAllByHoTenLikeOrSdtOrTaiKhoan(String timKiem ,Pageable pageable);

    @Override
    <S extends KhachHang> S save(S entity);

    @Query(value = "select kh from KhachHang kh where kh.email = ?1 or kh.taiKhoan = ?1")
    KhachHang findByEmailOrAndTaiKhoan(String username);


    Page<KhachHang> findAll(Specification<KhachHang> khachHangSpecification, Pageable pageable);

    @Query(value = "select kh from KhachHang kh where kh.email = ?1")
    KhachHang findByEmail(String email);

    @Query(value = "select kh from KhachHang kh where kh.taiKhoan = ?1")
    KhachHang findByTaiKhoan(String taiKhoan);

    @Query(value = "select kh from KhachHang kh where kh.sdt = ?1")
    KhachHang findBySdt(String sdt);

    @Query(value = "select kh.id from KhachHang kh where kh.taiKhoan = ?1")
    UUID findIdByTaiKhoan(String taiKhoan);

    @Query(value = "select kh.id from KhachHang kh where kh.email = ?1")
    UUID findIdByEmail(String email);

    @Query(value = "select kh from KhachHang kh where kh.taiKhoan = ?1")
    KhachHang findKhachHangByTaiKhoan(String taiKhoan);

    @Modifying
    @Query(value = "INSERT INTO khach_hang (id, ho_ten, email, tai_khoan, mat_khau, sdt, ngay_tao, dia_chi, trang_thai) VALUES (?1, ?2, ?3, ?4, ?5, ?6, ?7, ?8, ?9)", nativeQuery = true)
    void insertKhachHang(UUID id, String hoTen, String email, String taiKhoan, String matKhau, String sdt, Date ngayTao, String diaChi, Integer trangThai);
}
