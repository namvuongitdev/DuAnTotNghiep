package com.example.web.repository;
import com.example.web.model.ChiTietSanPham;
import com.example.web.response.ChiTietOnllineResponse;
import com.example.web.response.ChiTietResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface IChiTietSanPhamRepository extends JpaRepository<ChiTietSanPham, UUID> , JpaSpecificationExecutor<ChiTietSanPham> {

    @Query(value = "Select * from chi_tiet_san_pham where id=?1", nativeQuery = true)
    ChiTietSanPham getOne(UUID id);

    List<ChiTietSanPham> findBySanPham_Id(UUID id);

    @Query(value = "select * from chi_tiet_san_pham where idsanpham=?1",nativeQuery = true)
    List<ChiTietSanPham> listCTSPTheoIdSP(UUID idSP);

    @Query(value = "select * from ChiTietSanPham ctsp where ctsp.idmausac=?1 and ctsp.idsize = ?2 and ctsp.idsanpham = ?3" , nativeQuery = true)
    Optional<ChiTietSanPham> getChiTietSanPhamByMauSac_IdAndSize_IdAndSanPham_Id(String mauSac_Id, String size, String sanPham_Id);

    @Query(value = "" +
            "select  new com.example.web.response.ChiTietOnllineResponse(ctsp.id,ctsp.soLuong,ctsp.trangThai) from ChiTietSanPham ctsp where ctsp.mauSac.id=?1 and ctsp.size.id = ?2 and ctsp.sanPham.id = ?3" +
            "")
    ChiTietOnllineResponse getChiTietSanPhamByMauSac_IdAndSize_IdAndSanPham_Id1(UUID mauSac_Id, String size, UUID sanPham_Id);

    @Query(value = "select new com.example.web.response.ChiTietResponse(ctsp.soLuong,ctsp.trangThai,ctsp.id,ctsp.mauSac,ctsp.sanPham,ctsp.size,ctsp.qrCode) from ChiTietSanPham ctsp where ctsp.mauSac.id=?1 and ctsp.size.id = ?2 and ctsp.sanPham.id = ?3" )
    ChiTietResponse getChiTietSanPhamByMauSac_IdAndSize_IdAndIdSP(UUID mauSac_Id, String size, UUID sanPham_Id);

}
