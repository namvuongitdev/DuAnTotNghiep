package com.example.web.service;

import com.example.web.model.DiaChi;
import java.util.List;
import java.util.UUID;

public interface IDiaChiService {

    DiaChi add(DiaChi diaChi);

    DiaChi getDiaChiByKhachHang_idAndDiaChiMacDinh(UUID idKH);

    List<DiaChi> getDiaChiByKhachHang_id(UUID idKH);
}
