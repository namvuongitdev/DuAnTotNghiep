package com.example.web.repository;
import com.example.web.model.LichSuHoaDon;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;

public interface ILichSuHoaDonRepository extends JpaRepository<LichSuHoaDon , UUID> {

    @Override
    <S extends LichSuHoaDon> S save(S entity);
}
