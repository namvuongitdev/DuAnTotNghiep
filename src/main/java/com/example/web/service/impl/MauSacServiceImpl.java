package com.example.web.service.impl;
import com.example.web.model.MauSac;
import com.example.web.model.SanPham;
import com.example.web.repository.IMauSacRepository;
import com.example.web.service.IMauSacService;
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
public class MauSacServiceImpl implements IMauSacService {
    @Autowired
    private IMauSacRepository mauSacRepository;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<MauSac> getAll() {
        return mauSacRepository.findAll();
    }

    @Override
    public MauSac getOne(String id) {
        return mauSacRepository.getReferenceById(UUID.fromString(id));
    }

    @Override
    public void add(MauSac mauSac) {
        mauSacRepository.save(mauSac);
    }

    @Override
    public MauSac save(MauSac mauSac) {
        return mauSacRepository.save(mauSac);
    }

    @Override
    public Page<MauSac> findAll(Pageable pageable) {
        return mauSacRepository.findAll(pageable);
    }

    @Override
    public List<MauSac> getAll1() {
        return mauSacRepository.getAll1();
    }

    @Override
    public List<MauSac> getTheoCTSP(UUID idSP) {
        return mauSacRepository.getTheoCTSP(idSP);
    }

    @Override
    public String updateStatus(String id, Integer trangThai) {
        MauSac mauSac = mauSacRepository.getReferenceById(UUID.fromString(id));
        Date date = java.util.Calendar.getInstance().getTime();
        if (trangThai==0){
            mauSac.setTrangThai(1);
        }else {
            mauSac.setTrangThai(0);
        }
        mauSac.setNgaySua(date);
        mauSacRepository.save(mauSac);
        return "redirect:/admin/mau-sac/hien-thi";
    }

    @Override
    public boolean isExists(String value) {
        String sql = "SELECT COUNT(*) FROM mau_sac WHERE mau_sac.ten = ?";
        int count = jdbcTemplate.queryForObject(sql, Integer.class, value);
        return count > 0;
    }


}
