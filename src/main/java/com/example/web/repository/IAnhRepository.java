package com.example.web.repository;
import com.example.web.model.Anh;
import com.example.web.response.AnhMauSacReponse;
import com.example.web.response.AnhResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;
import java.util.UUID;

public interface IAnhRepository extends JpaRepository<Anh , UUID> {

    @Query(value = "select anh from Anh anh join anh.sanPham sp join anh.mauSac ms where sp.id = ?1 and ms.id = ?2")
    List<Anh> getAnhBySanPham_idAndMauSac_id(UUID idSP , UUID idMS);

    @Query(value = "select anh from Anh anh join anh.sanPham sp where sp.id = ?1")
    List<Anh> findAnhBySanPham_id(UUID idSP);

    @Query(value = "select a.ten as tenAnh from anh a join san_pham sp on sp.id = a.id_san_pham  join mau_sac ms on ms.id = a.id_mau_sac where sp.id = ?1 and ms.id = ?2" , nativeQuery = true)
    List<AnhMauSacReponse> getAnhBySanPham_idAndMauSac_idNativeQuery(String idSP , String idMS);

}
