package com.example.web.service;
import com.example.web.model.ChiTietSanPham;
import com.example.web.response.ChiTietOnllineResponse;
import com.example.web.response.ChiTietResponse;
import com.example.web.response.ChiTietSanPhamResponse;
import com.example.web.response.SanPhamFilter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface IChiTietSanPhamService {

    Page<ChiTietSanPham> findAll(Pageable pageable);

    ChiTietSanPham save(ChiTietSanPham sanPham);

    ChiTietSanPham getOne(UUID id);

    List<ChiTietSanPham> getChiTietSanPham(String id);

    List<ChiTietSanPhamResponse> getCTSP(String id);

    ChiTietSanPhamResponse getByMauSacAndKichCoAndSanPham(String idMS , String idKC , String idSP);

    List<ChiTietSanPham> listCTSPTheoIdSP(UUID idSP);

    ChiTietOnllineResponse getChiTietSanPhamByMauSac_IdAndSize_IdAndSanPham_Id1(UUID mauSac_Id, String size, UUID sanPham_Id);

    ChiTietResponse getChiTietSanPhamByMauSac_IdAndSize_IdAndIdSP(UUID mauSac_Id, String size, UUID sanPham_Id);
}
