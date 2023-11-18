package com.example.web.service.impl;

import com.example.web.model.DiaChi;
import com.example.web.repository.DiaChiRepository;
import com.example.web.service.IDiaChiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class DiaChiServiceImpl implements IDiaChiService {

    @Autowired
    private DiaChiRepository diaChiRepository;

    @Override
    public DiaChi add(DiaChi diaChi) {
        return diaChiRepository.save(diaChi);
    }

    @Override
    public DiaChi getDiaChiByKhachHang_idAndDiaChiMacDinh(UUID idKH) {
        DiaChi diaChi = diaChiRepository.findDiaChiByKhachHang_idAndDiaChiMacDinh(idKH);
        return diaChi;
    }

    @Override
    public List<DiaChi> getDiaChiByKhachHang_id(UUID idKH) {
        List<DiaChi> diaChis = diaChiRepository.findDiaChiByKhachHang_id(idKH);
        return diaChis;
    }
}
