package com.example.web.model;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "chi_tiet_san_pham")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ChiTietSanPham {

    @Id
    @GeneratedValue(generator = "UUID")
    @Column(name = "id")
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "idsanpham")
    private SanPham sanPham;

    @Column(name = "soluong")
    private Integer soLuong;

    @Column(name = "trangthai")
    private Integer trangThai;

    @Column(name = "qrcode")
    private String qrCode;

    @ManyToOne
    @JoinColumn(name = "idmausac")
    private MauSac mauSac;

    @ManyToOne
    @JoinColumn(name = "idsize")
    private Size size;

    @OneToMany(mappedBy = "chiTietSanPham")
    private List<Anh> anhs;

    @OneToMany(mappedBy = "chiTietSanPham")
    private List<HoaDonChiTiet> hoaDonChiTiets;

    @OneToMany(mappedBy = "chiTietSanPham")
    private List<GioHangChiTiet> gioHangChiTiets;

    public ChiTietSanPham(UUID id, SanPham sanPham, Integer soLuong, Integer trangThai, String qrCode, MauSac mauSac, Size size) {
        this.id = id;
        this.sanPham = sanPham;
        this.soLuong = soLuong;
        this.trangThai = trangThai;
        this.qrCode = qrCode;
        this.mauSac = mauSac;
        this.size = size;
    }
}
