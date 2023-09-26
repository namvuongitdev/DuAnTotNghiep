package com.example.web.repository;

import com.example.web.model.KieuDang;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

public interface IFormDangRepository extends JpaRepository<KieuDang, UUID> {
    @Transactional
    @Modifying
    @Query(value = "DELETE from FormDang where id =?1", nativeQuery = true)
    void delele(UUID id);

    @Query(value = "Select * from FormDang where id=?1",nativeQuery = true)
    KieuDang getOne(UUID id);


}
