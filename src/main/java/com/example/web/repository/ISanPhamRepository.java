package com.example.web.repository;
import com.example.web.model.SanPham;
import com.example.web.response.SanPhamAsKhuyenMai;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import java.util.List;
import java.util.UUID;

public interface ISanPhamRepository extends JpaRepository<SanPham, UUID>  , JpaSpecificationExecutor<SanPham> {

    @Query(value = "Select * from san_pham where id=?1", nativeQuery = true)
    SanPham getOne(UUID id);

    @Override
    Page<SanPham> findAll(Pageable pageable);

    @Query(value = "select new com.example.web.response.SanPhamAsKhuyenMai(sp.id , sp.ten , sp.ma , sp.img ,sp.giaBan , sp.trangThai , spkm.trangThai , km.trangThai ,spkm.loaiGiamGia , spkm.mucGiam , spkm.donGiaSauKhiGiam) from SanPham sp left join sp.sanPhamKhuyenMais spkm left join spkm.khuyenMai km")
    Page<SanPhamAsKhuyenMai> findAllSanPham(Specification<SanPhamAsKhuyenMai> specification, Pageable pageable);

    @Query(value = "\n" +
            "select top 8 * from san_pham where ten like ?1 or ten like ?2 or ten like ?3",nativeQuery = true)
    List<SanPham> theoTen(String ten,String ten2,String ten3 );


    @Query(value = "select  sanPham from SanPham sanPham where sanPham.ten like ?1 or sanPham.ma like ?1 ")
    Page<SanPham> getAllSanPhamByTenOrMa(String value, Pageable pageable);

    @Query(value = "Select * from san_pham where gioi_tinh = ?1", nativeQuery = true)
    Page<SanPham> findAllGender(Pageable pageable,boolean gioi_tinh);

}
