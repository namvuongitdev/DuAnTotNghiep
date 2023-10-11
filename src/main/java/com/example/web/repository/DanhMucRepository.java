package com.example.web.repository;

import com.example.web.model.DanhMuc;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;
import java.util.UUID;

public interface DanhMucRepository extends JpaRepository<DanhMuc, String> {
    @Query("Select dm from DanhMuc dm where dm.id=?1")
    DanhMuc getOne1(UUID id);
    @Query("select n from DanhMuc n where n.trangThai = 1")
    List<DanhMuc> getAll1();
}
