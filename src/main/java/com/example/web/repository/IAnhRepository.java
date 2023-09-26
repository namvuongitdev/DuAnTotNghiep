package com.example.web.repository;
import com.example.web.model.Anh;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.UUID;

public interface IAnhRepository extends JpaRepository<Anh , UUID> {

    @Override
    <S extends Anh> S save(S entity);

    List<Anh> findByChiTietSanPham_Id(UUID id);

    @Override
    void deleteById(UUID uuid);

  // Anh findByChiTietSanPham_Id(UUID id);

}
