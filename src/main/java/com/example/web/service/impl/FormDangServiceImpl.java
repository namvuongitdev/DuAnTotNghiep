package com.example.web.service.impl;

import com.example.web.model.KieuDang;
import com.example.web.repository.IFormDangRepository;
import com.example.web.service.IFormDangService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class FormDangServiceImpl implements IFormDangService {
    @Autowired
    private IFormDangRepository repository;

    @Override
    public List<KieuDang> getAll() {
        return repository.findAll();
    }

    @Override
    public List<KieuDang> getAll1() {
        return repository.getAll1();
    }

    @Override
    public KieuDang getOne(UUID id) {
        return repository.getReferenceById(id);
    }

    @Override
    public void add(KieuDang kieuDang) {
        repository.save(kieuDang);
    }

    @Override
    public void update(KieuDang kieuDang) {
        repository.save(kieuDang);
    }

    @Override
    public void delete(UUID id) {
        repository.deleteById(id);
    }

    @Override
    public Page<KieuDang> page(Integer pageNo, Integer size) {
        Pageable pageable = PageRequest.of(pageNo, size);
        return repository.findAll(pageable);
    }

    @Override
    public String updateStatus(String id, Integer trangThai) {
        KieuDang kieuDang = repository.getReferenceById(UUID.fromString(id));
        if (trangThai==0){
            kieuDang.setTrangThai(1);
        }else {
            kieuDang.setTrangThai(0);
        }
        repository.save(kieuDang);
        return "redirect:/admin/kieu-dang/hien-thi";
    }
}
