package com.example.web.service;

import com.example.web.model.ChucVu;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.UUID;

public interface IChucVuService {
    List<ChucVu> getAll();

    ChucVu getOne(UUID id);

    void add(ChucVu chucVu);

    void update(ChucVu chucVu);

    void delete(UUID id);

    public Page<ChucVu> getAll(Pageable pageable);

    List<ChucVu> getAll1();
}
