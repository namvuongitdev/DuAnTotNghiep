package com.example.web.service;
import com.example.web.request.HoaDonRequest;
import com.example.web.model.HoaDon;
import com.example.web.model.HoaDonChiTiet;
import com.example.web.response.HoaDonFilter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.ui.Model;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.UUID;

public interface IHoaDonService {

    String addHoaDon();

    Page<Object[]> getAllByTrangThai(Integer trangThai , Pageable pageable);

    String getHoaDonById(Model model ,  String id);

    String updateHoaDonTrangThai(String id , String ghiChu);

    String thanhToan(HoaDonRequest request  , RedirectAttributes attributes);

    List<HoaDon> getAll();

    HoaDon getOne(String id);

    List<HoaDonChiTiet> getHoaDonChiTiet(UUID id );

    Page<HoaDon> pagination(Integer pageNo , Integer size);

    Page<HoaDon> hoaDonFillter(HoaDonFilter filter , Pageable pageable);

    HoaDon add(HoaDon hoaDon);

}
