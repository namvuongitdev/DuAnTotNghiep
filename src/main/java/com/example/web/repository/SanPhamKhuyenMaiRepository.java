package com.example.web.repository;
import com.example.web.model.SanPhamKhuyenMai;
import com.example.web.response.SanPhamAsKhuyenMai;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import java.util.UUID;

public interface SanPhamKhuyenMaiRepository extends JpaRepository<SanPhamKhuyenMai, UUID>  , JpaSpecificationExecutor<SanPhamKhuyenMai> {

    @Override
    <S extends SanPhamKhuyenMai> S save(S entity);

    @Query(value = "select smpk from SanPhamKhuyenMai smpk where smpk.sanPhamKM.id = ?1 and smpk.khuyenMai.id = ?2")
    SanPhamKhuyenMai kiemTraDaTonTai(UUID idSPKM , UUID idKM);

    @Query(value = "select new com.example.web.response.SanPhamAsKhuyenMai(smpk.id , smpk.sanPhamKM.ten , smpk.sanPhamKM.ma , smpk.loaiGiamGia , smpk.mucGiam , smpk.trangThai)  from SanPhamKhuyenMai smpk where smpk.id = ?1")
    SanPhamAsKhuyenMai findSanPhamKhuyenMaiById(UUID id);

}
