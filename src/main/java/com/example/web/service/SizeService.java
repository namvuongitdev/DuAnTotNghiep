package com.example.web.service;

import com.example.web.model.Size;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.UUID;

public interface SizeService {
    List<Size> getAll();

    List<Size> getAll1();

    Size getOne(String id);

    Size getOne1(UUID id);

    void add(Size size);

    void update(Size size);

    void delete(String id);

    Page<Size> pagination(Integer pageNo, Integer size);

    List<Size> getTheoCT (UUID idSP);

    String updateStatus(String id,Integer trangThai);

}
