package com.example.web.repository;

import com.example.web.model.ChucVu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface IChucVuRepository extends JpaRepository<ChucVu, UUID> {
    @Query("select cv from ChucVu cv where cv.trangThai = 0")
    List<ChucVu> getAll1();
}
