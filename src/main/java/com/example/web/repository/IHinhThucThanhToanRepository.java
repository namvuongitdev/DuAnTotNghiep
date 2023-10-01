package com.example.web.repository;

import com.example.web.model.HinhThucThanhToan;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;

public interface IHinhThucThanhToanRepository extends JpaRepository<HinhThucThanhToan , UUID> {

    @Override
    <S extends HinhThucThanhToan> S save(S entity);
}
