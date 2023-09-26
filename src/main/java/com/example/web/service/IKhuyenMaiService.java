package com.example.web.service;
import com.example.web.model.KhuyenMai;
import com.example.web.response.FilterKhuyenMai;
import org.springframework.data.domain.Page;

public interface IKhuyenMaiService {

    String addKhuyenMai(KhuyenMai khuyenMai);

    Page<KhuyenMai>  getAll(Integer page);

    Page<KhuyenMai> filterKhuyenMai(Integer page , FilterKhuyenMai filter);
}
