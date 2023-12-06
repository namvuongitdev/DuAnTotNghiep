package com.example.web.service;

import com.example.web.model.Size;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.UUID;

public interface SizeService {
    List<Size> getAll();

    Size getOne(String id);

    void add(Size size);

    Size save(Size size);

    Page<Size> findAll(Pageable pageable);

    List<Size> getAll1();

    List<Size> getTheoCT (UUID idSP);

    String updateStatus(String id,Integer trangThai);

    boolean isExists(String value);


}
