package com.example.web.repository;
import com.example.web.model.HoaDonChiTiet;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;
import java.util.UUID;

public interface IHoaDonChiTietRepository extends JpaRepository<HoaDonChiTiet , UUID> {

    @Override
    <S extends HoaDonChiTiet> S save(S entity);

    @Override
    void deleteById(UUID uuid);

    @Override
    Optional<HoaDonChiTiet> findById(UUID uuid);

    HoaDonChiTiet findByChiTietSanPham_IdAndAndHoaDon_IdAndTrangThai(UUID idCtsp , UUID idHD ,Integer trangThai);

}
