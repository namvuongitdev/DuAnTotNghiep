package com.example.web.service;
import com.example.web.request.HoaDonRequest;
import com.example.web.model.HoaDon;
import com.example.web.model.HoaDonChiTiet;
import com.example.web.response.HoaDonFilter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.ui.Model;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

public interface IHoaDonService {

    String addHoaDon();

    Page<Object[]> findByHoaDonCho(Integer trangThaiHD ,Pageable pageable);

    String getHoaDonById(Model model ,  String id);

    String updateHoaDonTrangThai(String id);

    String thanhToan(HoaDonRequest request  , RedirectAttributes attributes);

    List<HoaDon> getAll();

    HoaDon getOne(String id);

    Page<HoaDonChiTiet> getHoaDonChiTiet(UUID id,Integer pageNo , Integer size);

    Page<HoaDonChiTiet> getHoaDonHuyChiTiet(UUID id,Integer pageNo , Integer size);

    Page<HoaDon> pagination(Integer pageNo , Integer size);

    Page<HoaDon> hoaDonFillter(HoaDonFilter filter , Pageable pageable);

    HoaDon add(HoaDon hoaDon);

    String updateHoaDonById(HoaDon hoaDon);

    String updateThongTin(HoaDon hoaDon);

    String updatePVC(HoaDon hoaDon);

    String updateStatusHoaDonById(HoaDon hoaDon,String trangThai);

}
