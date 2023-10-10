package com.example.web.repository;

import com.example.web.model.KieuDang;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

public interface IFormDangRepository extends JpaRepository<KieuDang, UUID> {
    @Transactional
    @Modifying
    @Query("DELETE from KieuDang where id =?1")
    void delele(UUID id);

    @Query("Select kd from KieuDang kd where kd.id=?1")
    KieuDang getOne(UUID id);

    @Query("select n from KieuDang n where n.trangThai = 1")
    List<KieuDang> getAll1();

}
