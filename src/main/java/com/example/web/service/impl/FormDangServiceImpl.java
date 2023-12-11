package com.example.web.service.impl;

import com.example.web.model.KieuDang;
import com.example.web.repository.IFormDangRepository;
import com.example.web.service.IFormDangService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class FormDangServiceImpl implements IFormDangService {
    @Autowired
    private IFormDangRepository repository;

    @Autowired
    private JdbcTemplate jdbcTemplate;

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
    public KieuDang save(KieuDang kieuDang) {
        return repository.save(kieuDang);
    }

    @Override
    public Page<KieuDang> findAll(Pageable pageable) {
        return repository.findAll(pageable);
    }

    @Override
    public void add(KieuDang kieuDang) {
        repository.save(kieuDang);
    }

    @Override
    public String updateStatus(String id, Integer trangThai) {
        KieuDang kieuDang = repository.getReferenceById(UUID.fromString(id));
        Date date = java.util.Calendar.getInstance().getTime();
        if (trangThai==0){
            kieuDang.setTrangThai(1);
        }else {
            kieuDang.setTrangThai(0);
        }
        kieuDang.setNgaySua(date);
        repository.save(kieuDang);
        return "redirect:/admin/kieu-dang/hien-thi";
    }

    @Override
    public boolean isExists(String value) {
        String sql = "SELECT COUNT(*) FROM form_dang WHERE form_dang.ten = ?";
        int count = jdbcTemplate.queryForObject(sql, Integer.class, value);
        return count > 0;
    }
}
