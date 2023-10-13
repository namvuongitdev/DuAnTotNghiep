package com.example.web.service.impl;

import com.example.web.model.ChucVu;
import com.example.web.repository.IChucVuRepository;
import com.example.web.service.IChucVuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ChucVuServiceImpl implements IChucVuService {
    @Autowired
    private IChucVuRepository repository;

    @Override
    public List<ChucVu> getAll() {
        return repository.findAll();
    }

    @Override
    public ChucVu getOne(UUID id) {
        return repository.getReferenceById(id);
    }

    @Override
    public void add(ChucVu chucVu) {
        repository.save(chucVu);
    }

    @Override
    public void update(ChucVu chucVu) {
        repository.save(chucVu);
    }

    @Override
    public void delete(UUID id) {
        repository.deleteById(id);
    }

    @Override
    public Page<ChucVu> getAll(Pageable pageable) {
        return repository.findAll(pageable);
    }

    @Override
    public List<ChucVu> getAll1() {
        return repository.getAll1();
    }
}
