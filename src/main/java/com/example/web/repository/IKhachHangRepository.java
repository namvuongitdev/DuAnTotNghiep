package com.example.web.repository;
import com.example.web.model.KhachHang;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
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
}
