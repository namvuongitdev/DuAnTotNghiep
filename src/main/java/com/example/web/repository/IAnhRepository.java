package com.example.web.repository;
import com.example.web.model.Anh;
import com.example.web.response.AnhResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface IAnhRepository extends JpaRepository<Anh , UUID> {

    @Override
    <S extends Anh> S save(S entity);

    List<Anh> findByChiTietSanPham_Id(UUID id);

    @Override
    void deleteById(UUID uuid);

    @Query(value = """
       select anh.id,anh.ten,anh.idctsp from anh where idctsp IN (select id from chi_tiet_san_pham where idsanpham=?1)
""",nativeQuery = true)
    List<Anh> getTenAnh(UUID idSanPham);

  // Anh findByChiTietSanPham_Id(UUID id);

}
