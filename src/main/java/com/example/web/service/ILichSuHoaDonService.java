package com.example.web.service;

import com.example.web.model.HoaDon;
import com.example.web.model.LichSuHoaDon;
import java.util.List;
import java.util.UUID;

public interface ILichSuHoaDonService {
    List<LichSuHoaDon> getAll();

    List<LichSuHoaDon> getListById(UUID id);

    LichSuHoaDon getOne(String idHd,String Thaotac);

    LichSuHoaDon getOneTao(String idHd,String thaoTac);

    void add(Integer thaoTac, HoaDon hoaDon,String ghiChu);

    void update(LichSuHoaDon lshd);

    void delete(UUID id);

}
