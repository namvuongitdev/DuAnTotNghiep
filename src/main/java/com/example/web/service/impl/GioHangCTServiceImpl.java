package com.example.web.service.impl;

import com.example.web.model.GioHangChiTiet;
import com.example.web.repository.IGioHangCTRepository;
import com.example.web.service.IGioHangCTService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class GioHangCTServiceImpl implements IGioHangCTService {

    @Autowired
    IGioHangCTRepository iGioHangCTRepository;


    @Override
    public GioHangChiTiet save(GioHangChiTiet gioHangChiTiet) {
        return iGioHangCTRepository.save(gioHangChiTiet);
    }

    @Override
    public List<GioHangChiTiet> getListGHCTTheoKhachHang(UUID idKH) {
        return iGioHangCTRepository.getListGHCTTheoKhachHang(idKH);
    }

    @Override
    public GioHangChiTiet getTheoIdGioHangAndIdCTSP(UUID idGioHang, UUID idCTSP) {
        return iGioHangCTRepository.getTheoIdGioHangAndIdCTSP(idGioHang,idCTSP);
    }
}
