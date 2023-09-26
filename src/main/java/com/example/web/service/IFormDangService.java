package com.example.web.service;

import com.example.web.model.KieuDang;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.UUID;

public interface IFormDangService {
    Page<KieuDang> findAll(Pageable pageable);

    void deleteById(UUID id);

    KieuDang save(KieuDang form);

    KieuDang getOne(UUID id);

    List<KieuDang> getAll();
}
