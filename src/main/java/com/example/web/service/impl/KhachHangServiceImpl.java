package com.example.web.service.impl;
import com.example.web.model.KhachHang;
import com.example.web.repository.IKhachHangRepository;
import com.example.web.service.IKhachHangService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.UUID;

@Service
public class KhachHangServiceImpl implements IKhachHangService {

    @Autowired
    private IKhachHangRepository khachHangRepository;

    @Override
    public Page<KhachHang> getAll(Integer page) {
        Pageable pageable = PageRequest.of(page - 1, 10);
        return khachHangRepository.findAll(pageable);
    }

    @Override
    public KhachHang getKhachHangById(String id) {
        KhachHang khachHang =  khachHangRepository.findById(UUID.fromString(id)).orElse(null);
        return khachHang;
    }
}
