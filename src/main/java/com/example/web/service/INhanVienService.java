package com.example.web.service;

import com.example.web.model.NhanVien;
import com.example.web.response.NhanVienFilter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.UUID;

public interface INhanVienService {
    public Page<NhanVien> getAll(Pageable pageable);

    List<NhanVien> findAll();

    NhanVien findById(UUID id);

    NhanVien add(NhanVien nhanVien);

    NhanVien update(UUID id, NhanVien nhanVien);

    Page<NhanVien> nhanVienFilter(NhanVienFilter filter, Pageable pageable);

    NhanVien checkTaiKhoan(String taiKhoan);

    NhanVien checkEmail(String email);

    NhanVien findBySDT(String sdt);

    UUID findIdByEmail(String email);

    String findEmailToPass(String taiKhoan);

    NhanVien getNhanVienLogin();

    NhanVien getByEmailOrTaiKhoan(String usename);
}
