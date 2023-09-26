package com.example.web.service;
import com.example.web.model.ChiTietSanPham;
import com.example.web.response.ChiTietSanPhamResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.List;
import java.util.UUID;

public interface IChiTietSanPhamService {

    Page<ChiTietSanPham> findAll(Pageable pageable);

    ChiTietSanPham save(ChiTietSanPham sanPham);

    ChiTietSanPham getOne(UUID id);

    List<ChiTietSanPham> getChiTietSanPham(String id);

    List<ChiTietSanPhamResponse> getCTSP(String id);

    ChiTietSanPhamResponse getByMauSacAndKichCoAndSanPham(String idMS , String idKC , String idSP);
}
