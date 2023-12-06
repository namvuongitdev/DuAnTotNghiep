package com.example.web.service;

import com.example.web.model.KieuDang;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.UUID;

public interface IFormDangService {
    List<KieuDang> getAll();

    List<KieuDang> getAll1();

    void add(KieuDang kieuDang);

    KieuDang getOne(UUID id);

    KieuDang save(KieuDang kieuDang);

    Page<KieuDang> findAll(Pageable pageable);

    String updateStatus(String id,Integer trangThai);

    boolean isExists(String value);
}
