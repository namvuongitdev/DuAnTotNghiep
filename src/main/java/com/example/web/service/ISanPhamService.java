package com.example.web.service;
import com.example.web.model.SanPham;
import com.example.web.model.SanPhamKhuyenMai;
import com.example.web.response.SanPhamAndKhuyenMai;
import com.example.web.response.SanPhamAsKhuyenMai;
import com.example.web.response.SanPhamFilter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.List;
import java.util.UUID;

public interface ISanPhamService {

    Page<SanPham> findAll(Pageable pageable);

    SanPham save(SanPham sanPham);

    SanPham getOne(UUID id);

    List<SanPham> getAll();

    Page<SanPham> sanPhamFilter(SanPhamFilter filter , Pageable pageable);

    Page<SanPham> getAllByTenOrMa(String value  , Integer page);

    List<SanPham> theoTen(UUID chatLieu,UUID kieuDang,String danhMuc );

    Page<SanPham> findAllGender(Pageable pageable,boolean gioi_tinh);

    SanPham getSanPhamTheoCTSP(UUID idCTSP);

    Page<SanPhamAndKhuyenMai> getALL(Pageable pageable);

}
