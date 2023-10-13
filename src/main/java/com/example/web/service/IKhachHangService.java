package com.example.web.service;
import com.example.web.model.KhachHang;
import org.springframework.data.domain.Page;

public interface IKhachHangService {

    Page<KhachHang> getAll(Integer page);

    KhachHang getKhachHangById(String id);

    Page<KhachHang> getKhachHangByHoTenOrSdtOrTaiKhoan(String timKiem , Integer page);

    KhachHang themMoiKhachHang(KhachHang khachHang);
}
