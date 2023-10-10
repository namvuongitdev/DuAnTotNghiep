package com.example.web.service;
import com.example.web.model.KhachHang;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IKhachHangService {

    Page<KhachHang> getAll(Integer page);

    KhachHang getKhachHangById(String id);
}
