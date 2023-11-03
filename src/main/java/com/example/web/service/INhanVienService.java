package com.example.web.service;

import com.example.web.model.NhanVien;
import com.example.web.response.NhanVienFilter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.List;
import java.util.UUID;

public interface INhanVienService {
    public Page<NhanVien> getAll(Pageable pageable);

    public List<NhanVien> findAll();

    public NhanVien findById(UUID id);

    public NhanVien add(NhanVien nhanVien);

    public NhanVien update(UUID id, NhanVien nhanVien);

    Page<NhanVien> nhanVienFilter(NhanVienFilter filter , Pageable pageable);

    NhanVien checkTaiKhoan(String taiKhoan);

    NhanVien checkEmail(String email);
}
