package com.example.web.service.impl;
import com.example.web.model.KhachHang;
import com.example.web.repository.IKhachHangRepository;
import com.example.web.service.IKhachHangService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.UUID;

@Service
public class KhachHangServiceImpl implements IKhachHangService {

    @Autowired
    private IKhachHangRepository khachHangRepository;

    @Override
    public List<KhachHang> getAll() {
        return khachHangRepository.findAll();
    }

    @Override
    public KhachHang getKhachHangById(String id) {
        KhachHang khachHang =  khachHangRepository.findById(UUID.fromString(id)).orElse(null);
        return khachHang;
    }
}
