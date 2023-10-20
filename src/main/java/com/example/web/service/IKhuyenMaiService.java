package com.example.web.service;
import com.example.web.model.KhuyenMai;
import com.example.web.model.SanPhamKhuyenMai;
import com.example.web.response.FilterKhuyenMai;
import com.example.web.response.KhuyenMaiReponse;
import org.springframework.data.domain.Page;
import java.util.UUID;

public interface IKhuyenMaiService {

    KhuyenMai addKhuyenMai(KhuyenMai khuyenMai);

    Page<KhuyenMai>  getAll(Integer page);

    Page<KhuyenMai> filterKhuyenMai(Integer page , FilterKhuyenMai filter);

    Page<KhuyenMaiReponse> getKhuyenMaiById(UUID id , Integer page);

    Boolean addSanPhamKhuyenMai(SanPhamKhuyenMai sanPhamKhuyenMai , String idKM);

    KhuyenMai getById(UUID uuid);

    SanPhamKhuyenMai updateTrangThaiKhuyenMaiChiTiet(Integer trangThai , UUID uuid);

    KhuyenMai updateTrangThaiKhuyenMai(Integer trangThai , UUID uuid);

    SanPhamKhuyenMai getSanPhamKhuyenMai(UUID id);

    SanPhamKhuyenMai updateSanPhamKhuyenMai(SanPhamKhuyenMai sanPhamKhuyenMai);
}
