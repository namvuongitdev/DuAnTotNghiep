package com.example.web.service;

import com.example.web.model.LichSuHoaDon;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

public interface ILichSuHoaDonService {
    List<LichSuHoaDon> getAll();

    List<LichSuHoaDon> getListById(UUID id);

    LichSuHoaDon getOne(String idHd,String Thaotac);

    LichSuHoaDon getOneTao(String idHd);

    void add(LichSuHoaDon lshd);

    void update(LichSuHoaDon lshd);

    void delete(UUID id);

}
