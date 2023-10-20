package com.example.web.repository;
import com.example.web.model.SanPham;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

    @Query(value = "\n" +
            "select top 8 * from san_pham where id_chat_lieu like ?1 or id_kieu_dang like ?2 or id_danh_muc_san_pham like ?3",nativeQuery = true)
    List<SanPham> theoTen(UUID chatLieu,UUID kieuDang,String danhMuc );


    @Query(value = "select  sanPham from SanPham sanPham where sanPham.ten like ?1 or sanPham.ma like ?1 ")
    Page<SanPham> getAllSanPhamByTenOrMa(String value, Pageable pageable);

    @Query(value = "Select * from san_pham where gioi_tinh = ?1", nativeQuery = true)
    Page<SanPham> findAllGender(Pageable pageable,boolean gioi_tinh);

}
