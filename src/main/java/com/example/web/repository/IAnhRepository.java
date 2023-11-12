package com.example.web.repository;
import com.example.web.model.Anh;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;
import java.util.UUID;

public interface IAnhRepository extends JpaRepository<Anh , UUID> {

    @Query(value = "select anh from Anh anh join anh.sanPham sp join anh.mauSac ms where sp.id = ?1 and ms.id = ?2")
    List<Anh> getAnhBySanPham_idAndMauSac_id(UUID idSP , UUID idMS);

    @Query(value = "select anh from Anh anh join anh.sanPham sp where sp.id = ?1")
    List<Anh> findAnhBySanPham_id(UUID idSP);

}
