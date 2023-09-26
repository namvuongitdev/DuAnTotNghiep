package com.example.web.service;
import com.example.web.model.KhachHang;
import java.util.List;

public interface IKhachHangService {

    List<KhachHang> getAll();

    KhachHang getKhachHangById(String id);
}
