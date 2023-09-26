package com.example.web.service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.ui.Model;

public interface IHoaDonService {

    String addHoaDon();

    Page<Object[]> getAllByTrangThai(Integer trangThai , Pageable pageable);

    String getHoaDonById(Model model ,  String id);

    String updateHoaDonTrangThai(String id , String ghiChu);


}
