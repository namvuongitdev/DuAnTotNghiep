package com.example.web.service;

import com.example.web.model.DanhMuc;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.UUID;

public interface DanhMucService {
    List<DanhMuc> getAll();

    List<DanhMuc> getAll1();

    DanhMuc getOne(String id);

    DanhMuc getOne1(UUID id);

    void add(DanhMuc danhMuc);

    DanhMuc save(DanhMuc danhMuc);

    Page<DanhMuc> findAll(Pageable pageable);

    String updateStatus(String id,Integer trangThai);

    boolean isExists(String value);
}
