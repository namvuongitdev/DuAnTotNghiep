package com.example.web.service;

import com.example.web.model.LichSuHoaDon;
import java.util.List;
import java.util.UUID;

public interface ILichSuHoaDonService {

    List<LichSuHoaDon> getAll();

    List<LichSuHoaDon> getListById(UUID id);

    void add(Integer thaoTac, UUID idHD,String ghiChu);

}
