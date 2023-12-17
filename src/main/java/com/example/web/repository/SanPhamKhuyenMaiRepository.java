package com.example.web.repository;
import com.example.web.model.SanPhamKhuyenMai;
import com.example.web.response.SanPhamAsKhuyenMai;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import java.util.UUID;

public interface SanPhamKhuyenMaiRepository extends JpaRepository<SanPhamKhuyenMai, UUID>  , JpaSpecificationExecutor<SanPhamKhuyenMai> {

    @Override
    <S extends SanPhamKhuyenMai> S save(S entity);

    @Query(value = "select new com.example.web.response.SanPhamAsKhuyenMai(smpk.id , smpk.sanPhamKM.ten , smpk.sanPhamKM.ma  , smpk.sanPhamKM.giaBan, smpk.loaiGiamGia , smpk.mucGiam , smpk.trangThai)  from SanPhamKhuyenMai smpk where smpk.id = ?1")
    SanPhamAsKhuyenMai findSanPhamKhuyenMaiById(UUID id);

}
