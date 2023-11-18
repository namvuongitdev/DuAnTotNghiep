package com.example.web.service;

import com.example.web.model.DanhMuc;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.UUID;

public interface DanhMucService {
    List<DanhMuc> getAll();

    List<DanhMuc> getAll1();

    DanhMuc getOne(String id);

    DanhMuc getOne1(UUID id);

    void add(DanhMuc danhMuc);

    void update(DanhMuc danhMuc);

    void delete(String id);

    Page<DanhMuc> page(Integer pageNo, Integer size);

    String updateStatus(String id,Integer trangThai);
}
