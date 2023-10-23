package com.example.web.repository;
import com.example.web.model.KhuyenMai;
import com.example.web.model.SanPhamKhuyenMai;
import com.example.web.response.KhuyenMaiReponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;
import java.util.UUID;

public interface IKhuyenMaiRepository extends JpaRepository<KhuyenMai , UUID>  , JpaSpecificationExecutor<KhuyenMai> {

    @Override
    <S extends KhuyenMai> S save(S entity);

    @Override
    Page<KhuyenMai> findAll(Pageable pageable);

    @Query(value = "select new com.example.web.response.KhuyenMaiReponse(spkm.id  ,sp.ten , sp.ma , spkm.loaiGiamGia ,spkm.mucGiam , spkm.sanPhamKM.giaBan ,spkm.donGiaSauKhiGiam ,spkm.trangThai) from KhuyenMai km inner join km.sanPhamKhuyenMais spkm inner join spkm.sanPhamKM sp where km.id = ?1")
    Page<KhuyenMaiReponse> chiTietKhuyenMaiById(UUID id, Pageable pageable);

}