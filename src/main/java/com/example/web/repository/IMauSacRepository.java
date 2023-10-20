package com.example.web.repository;

import com.example.web.model.MauSac;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

public interface IMauSacRepository extends JpaRepository<MauSac, UUID> {

    @Transactional
    @Modifying
    @Query(value = "DELETE from MauSac where id =?1", nativeQuery = true)
    void delele(UUID id);

    @Query(value = "Select * from MauSac where id=?1",nativeQuery = true)
    MauSac getOne(UUID id);

    @Query("select n from MauSac n where n.trangThai = 1")
    List<MauSac> getAll1();

    @Query(value = "select distinct mau_sac.* from mau_sac,chi_tiet_san_pham where mau_sac.id=chi_tiet_san_pham.idmausac and idsanpham= ?1",nativeQuery = true)
    List<MauSac> getTheoCTSP(UUID idSP);
}
