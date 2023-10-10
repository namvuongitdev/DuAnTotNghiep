package com.example.web.repository;
import com.example.web.model.KhachHang;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface IKhachHangRepository extends JpaRepository<KhachHang , UUID> {

    @Override
    Page<KhachHang> findAll(Pageable pageable);

    @Override
    Optional<KhachHang> findById(UUID uuid);
}
