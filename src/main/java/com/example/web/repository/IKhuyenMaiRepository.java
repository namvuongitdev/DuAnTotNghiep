package com.example.web.repository;
import com.example.web.model.KhuyenMai;
import com.example.web.model.SanPhamKhuyenMai;
import com.example.web.response.SanPhamAsKhuyenMai;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface IKhuyenMaiRepository extends JpaRepository<KhuyenMai , UUID>  , JpaSpecificationExecutor<KhuyenMai> {

    @Override
    <S extends KhuyenMai> S save(S entity);

    @Override
    Page<KhuyenMai> findAll(Pageable pageable);

    @Query(value = "select spkm from SanPhamKhuyenMai spkm inner join spkm.khuyenMai km where km.id = ?1")
    Page<SanPhamKhuyenMai> chiTietKhuyenMaiById(UUID id, Pageable pageable);

    @Query(value = "select spkm from KhuyenMai km join km.sanPhamKhuyenMais spkm join spkm.sanPhamKM sp where sp.id = ?1")
    Optional<SanPhamKhuyenMai> findBySanPham_id(UUID id);

    @Query(value = "select km from KhuyenMai km where km.ngayKetThuc <= current_date ")
    List<KhuyenMai> findKhuyenMaiByHetHan();

    @Query(value = "select km from KhuyenMai km where km.ngayBatDau = current_date")
    List<KhuyenMai> findKhuyenMaiByChuBatDau();

    @Query(value = "select kmsp from KhuyenMai km join km.sanPhamKhuyenMais kmsp join kmsp.sanPhamKM sp where sp.id =?1 and  (km.trangThai = 1 or km.trangThai = 2) and kmsp.trangThai = 1")
    SanPhamKhuyenMai checkTonTaiSanPham(UUID idSP);

}