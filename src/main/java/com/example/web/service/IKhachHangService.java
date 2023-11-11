package com.example.web.service;
import com.example.web.model.KhachHang;
import com.example.web.response.KhachHangFilter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.List;
import java.util.UUID;

public interface IKhachHangService {

    Page<KhachHang> getAll(Integer page);

    KhachHang getKhachHangById(String id);

    Page<KhachHang> getKhachHangByHoTenOrSdtOrTaiKhoan(String timKiem , Integer page);

    KhachHang themMoiKhachHang(KhachHang khachHang);

    KhachHang findByEmailOrAndTaiKhoan(String username);

    List<KhachHang> findAll();

    KhachHang findById(UUID id);

    void add(KhachHang khachHang);

    void  update( KhachHang khachHang);

    Page<KhachHang> khachHangFillter(KhachHangFilter filter , Pageable pageable);

    KhachHang findByEmail(String email);

    KhachHang findByTaiKhoan(String taiKhoan);

    KhachHang findBySdt(String sdt);

    KhachHang getKhachHangLogin();

}
