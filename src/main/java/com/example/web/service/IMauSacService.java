package com.example.web.service;
import com.example.web.model.MauSac;
import com.example.web.model.SanPham;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.UUID;

public interface IMauSacService {
    List<MauSac> getAll();

    MauSac getOne(String id);

    void add(MauSac mauSac);

    MauSac save(MauSac mauSac);

    Page<MauSac> findAll(Pageable pageable);

    List<MauSac> getAll1();

    List<MauSac> getTheoCTSP(UUID idSP);

    String updateStatus(String id,Integer trangThai);

    boolean isExists(String value);
}
