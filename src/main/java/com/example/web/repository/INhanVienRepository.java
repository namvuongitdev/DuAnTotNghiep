package com.example.web.repository;

import com.example.web.model.NhanVien;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface INhanVienRepository extends JpaRepository<NhanVien, UUID> {

    Page<NhanVien> findAll(Specification<NhanVien> nhanVienSpecification, Pageable pageable);
}
