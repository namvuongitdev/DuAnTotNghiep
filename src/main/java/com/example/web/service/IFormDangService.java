package com.example.web.service;

import com.example.web.model.KieuDang;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.UUID;

public interface IFormDangService {
    List<KieuDang> getAll();

    List<KieuDang> getAll1();

    KieuDang getOne(UUID id);

    void add(KieuDang kieuDang);

    void update(KieuDang kieuDang);

    void delete(UUID id);

    Page<KieuDang> page(Integer pageNo, Integer size);
}
