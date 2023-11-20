package com.example.web.service;
import com.example.web.model.KhuyenMai;
import com.example.web.model.SanPhamKhuyenMai;
import com.example.web.request.KhuyenMaiRequest;
import com.example.web.response.FilterKhuyenMai;
import com.example.web.response.SanPhamAsKhuyenMai;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

public interface IKhuyenMaiService {

    KhuyenMai addKhuyenMai(KhuyenMai khuyenMai);

    Page<KhuyenMai>  getAll(Integer page);

    Page<KhuyenMai> filterKhuyenMai(Integer page , FilterKhuyenMai filter);

    Page<SanPhamKhuyenMai> getKhuyenMaiById(UUID id , Integer page);

    Boolean addSanPhamKhuyenMai(SanPhamKhuyenMai sanPhamKhuyenMai , String idKM);

    KhuyenMai getById(UUID uuid);

    SanPhamKhuyenMai updateTrangThaiKhuyenMaiChiTiet(Integer trangThai , UUID uuid);

    SanPhamAsKhuyenMai getSanPhamAsKhuyenMai(UUID id);

    SanPhamKhuyenMai updateSanPhamKhuyenMai(SanPhamKhuyenMai sanPhamKhuyenMai);

    SanPhamKhuyenMai getSanPhamKhuyenMaiById(UUID id);

    KhuyenMai updateKhuyenMai(KhuyenMaiRequest request , UUID idKM);

    Page<SanPhamKhuyenMai> filterSanPhamKhuyeMai(SanPhamAsKhuyenMai filter , Pageable pageable , UUID idKM);

    SanPhamKhuyenMai getSanPhamById(UUID idSP);

    BigDecimal donGiaSauKhiGiam(List<SanPhamKhuyenMai> sanPhamKhuyenMais);

    Integer validateTrangThai(KhuyenMai khuyenMai);

    KhuyenMai getKhuyenMaiById(UUID uuid);

}
