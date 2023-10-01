package com.example.web.service;
import com.example.web.request.HoaDonRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.ui.Model;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

public interface IHoaDonService {

    String addHoaDon();

    Page<Object[]> getAllByTrangThai(Integer trangThai , Pageable pageable);

    String getHoaDonById(Model model ,  String id);

    String updateHoaDonTrangThai(String id , String ghiChu);

    String thanhToan(HoaDonRequest request , RedirectAttributes attributes);

}
