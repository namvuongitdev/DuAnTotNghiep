package com.example.web.repository;
import com.example.web.model.CTSPKhuyenMai;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;

public interface ICTSPKhuyenMai extends JpaRepository<CTSPKhuyenMai , UUID> {

    @Override
    <S extends CTSPKhuyenMai> S save(S entity);
}
