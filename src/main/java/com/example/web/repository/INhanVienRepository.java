package com.example.web.repository;

import com.example.web.model.NhanVien;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.UUID;

public interface INhanVienRepository extends JpaRepository<NhanVien, UUID> {

    Page<NhanVien> findAll(Specification<NhanVien> nhanVienSpecification, Pageable pageable);

    @Query(value = "select nv from NhanVien nv where nv.email = ?1 or nv.taiKhoan = ?1")
    NhanVien findByEmailOrTaiKhoan(String username);

    @Query(value = "select * from nhan_vien where email = ?1",nativeQuery = true)
    NhanVien findByEmail(String email);

    @Query(value = "select * from nhan_vien where tai_khoan = ?1",nativeQuery = true)
    NhanVien findByTaiKhoan(String taiKhoan);
}
