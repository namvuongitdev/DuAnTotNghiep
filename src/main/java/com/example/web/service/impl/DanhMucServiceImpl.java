package com.example.web.service.impl;

import com.example.web.model.DanhMuc;
import com.example.web.repository.DanhMucRepository;
import com.example.web.service.DanhMucService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class DanhMucServiceImpl implements DanhMucService {
    @Autowired
    private DanhMucRepository danhMucRepository;

    @Override
    public List<DanhMuc> getAll() {
        return danhMucRepository.findAll();
    }

    @Override
    public List<DanhMuc> getAll1() {
        return danhMucRepository.getAll1();
    }

    @Override
    public DanhMuc getOne(String id) {
        return danhMucRepository.getReferenceById(id);
    }

    @Override
    public DanhMuc getOne1(UUID id) {
        return danhMucRepository.getOne1(id);
    }

    @Override
    public void add(DanhMuc danhMuc) {
        danhMucRepository.save(danhMuc);
    }

    @Override
    public DanhMuc save(DanhMuc danhMuc) {
        return danhMucRepository.save(danhMuc);
    }

    @Override
    public Page<DanhMuc> findAll(Pageable pageable) {
        return danhMucRepository.findAll(pageable);
    }

    @Override
    public String updateStatus(String id, Integer trangThai) {
        DanhMuc danhMuc = danhMucRepository.getReferenceById(id);
        Date date = java.util.Calendar.getInstance().getTime();
        if (trangThai==0){
            danhMuc.setTrangThai(1);
        }else {
            danhMuc.setTrangThai(0);
        }
        danhMuc.setNgaySua(date);
        danhMucRepository.save(danhMuc);
        return "redirect:/admin/danh-muc/hien-thi";
    }

    @Override
    public boolean isExists(String value) {
        return false;
    }
}
