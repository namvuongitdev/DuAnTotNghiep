package com.example.web.repository;
import com.example.web.model.SanPham;
import com.example.web.response.SanPhamAndKhuyenMai;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import java.util.List;
import org.springframework.lang.Nullable;
import java.util.UUID;

public interface  ISanPhamRepository extends JpaRepository<SanPham, UUID>, JpaSpecificationExecutor<SanPham> {

    @Query(value = "Select * from san_pham where id=?1", nativeQuery = true)
    SanPham getOne(UUID id);

    @Override
    Page<SanPham> findAll(Pageable pageable);

    @Query(value = "select sanPham from SanPham sanPham left join sanPham.chiTietSanPhams ctsp  where sanPham.ten like ?1 or sanPham.ma like ?1 or  ctsp.qrCode like ?1")
    Page<SanPham> getAllSanPhamByTenOrMa(String value, Pageable pageable);


    @Query(value = "Select sanPham from SanPham sanPham where sanPham.gioiTinh = ?1")
    Page<SanPham> findAllGender(Pageable pageable, boolean gioi_tinh);

    @Query(value = "Select sanPham from SanPham sanPham where sanPham.danhMuc.id = ?1")
    Page<SanPham> findAllDanhMuc(Pageable pageable, String id);


    @Query(value = "\n" +
            "select san_pham.* from chi_tiet_san_pham,san_pham where chi_tiet_san_pham.id=?1 and chi_tiet_san_pham.idsanpham=san_pham.id",nativeQuery = true)
    SanPham getSanPhamTheoCTSP(UUID idCTSP);

    @Query(value = "select sanPham from SanPham sanPham where sanPham.id in (select ct.sanPham.id from ChiTietSanPham ct where ct.id in" +
            " (select hd.chiTietSanPham.id from HoaDonChiTiet hd where hd.trangThai = 4 group by  hd.chiTietSanPham.id) )")
    List<SanPham> sanPhamNhieuNguoiMua ();

    @Query(value = """
        select new com.example.web.response.SanPhamAndKhuyenMai(s.id,s.ma,s.ten,s.img,s.trangThai,s.ngayTao,s.ngaySua,
        s.giaBan,s.moTa,s.gioiTinh,s.kieuDang,s.chatLieu,s.danhMuc,spkm.donGiaSauKhiGiam, spkm.trangThai, spkm.loaiGiamGia, spkm.mucGiam, km.trangThai) from SanPham s 
        left join SanPhamKhuyenMai spkm on s.id=spkm.sanPhamKM.id
        left join KhuyenMai km on km.id = spkm.khuyenMai.id
""")
    Page<SanPhamAndKhuyenMai> getALL(Pageable pageable);
}