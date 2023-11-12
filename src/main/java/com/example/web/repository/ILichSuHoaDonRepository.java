package com.example.web.repository;

import com.example.web.model.LichSuHoaDon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;
@Repository
public interface ILichSuHoaDonRepository extends JpaRepository<LichSuHoaDon, UUID> {

    @Query(value = "SELECT lshd from LichSuHoaDon lshd where lshd.hoaDon.id=?1 ORDER BY lshd.ngayThaoTac DESC")
    List<LichSuHoaDon> get(UUID idHd);

    @Query(value = "SELECT lshd from LichSuHoaDon lshd where lshd.hoaDon.id=?1 and lshd.thaoTac LIKE %?2%")
    LichSuHoaDon getOne(UUID idHd,String Thaotac);

    @Query(value = "SELECT * from lich_su_hoa_don where id_hoa_don=?1 and thao_tac=?2",nativeQuery = true)
    LichSuHoaDon getOneTao(UUID idHd,String thaoTac);
}
