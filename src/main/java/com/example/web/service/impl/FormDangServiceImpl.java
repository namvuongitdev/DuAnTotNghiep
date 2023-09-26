package com.example.web.service.impl;

import com.example.web.model.KieuDang;
import com.example.web.repository.IFormDangRepository;
import com.example.web.service.IFormDangService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class FormDangServiceImpl implements IFormDangService {
    @Autowired
    IFormDangRepository iFormDangRepository;

    @Override
    public Page<KieuDang> findAll(Pageable pageable) {
        return iFormDangRepository.findAll(pageable);
    }

    @Override
    public void deleteById(UUID id) {
        iFormDangRepository.delele(id);
    }

    @Override
    public KieuDang save(KieuDang form) {
        return iFormDangRepository.save(form);
    }

    @Override
    public KieuDang getOne(UUID id) {
        return iFormDangRepository.getOne(id);
    }

    @Override
    public List<KieuDang> getAll() {
        return iFormDangRepository.findAll();
    }
}
