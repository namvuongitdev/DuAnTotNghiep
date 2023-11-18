package com.example.web.service;
import com.example.web.model.MauSac;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.UUID;

public interface IMauSacService {
    List<MauSac> getAll();

    MauSac getOne(String id);

    void add(MauSac mauSac);

    void update(MauSac mauSac);

    void delete(String id);

    Page<MauSac> page(Integer pageNo, Integer size);

    List<MauSac> getAll1();

    List<MauSac> getTheoCTSP(UUID idSP);

    String updateStatus(String id,Integer trangThai);
}
