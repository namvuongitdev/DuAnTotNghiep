package com.example.web.repository;
import com.example.web.model.DiaChi;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;
import java.util.UUID;

public interface DiaChiRepository extends JpaRepository<DiaChi , UUID> {

    @Query(value = "select diaChi from DiaChi diaChi join diaChi.khachHang kh where kh.id = ?1 and diaChi.diaChiMacDinh = false")
     DiaChi findDiaChiByKhachHang_idAndDiaChiMacDinh(UUID idKH);

    @Query(value = "select diaChi from DiaChi diaChi join KhachHang kh where kh.id = ?1")
    List<DiaChi> findDiaChiByKhachHang_id(UUID idKH);
}
