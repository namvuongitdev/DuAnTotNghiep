package com.example.web.model;
import com.fasterxml.jackson.annotation.JsonIgnore;
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
import org.hibernate.annotations.Nationalized;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "hoa_don")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class HoaDon {

    @Id
    @Column(name = "id")
    @GeneratedValue(generator = "UUID")
    private UUID id;

    @Column(name = "ma", unique = true)
    private String ma;

    @Column(name = "diaChi")
    @Nationalized
    private String diaChi;

    @Column(name = "hoTen")
    @Nationalized
    private String hoTen;

    @Column(name = "sdt")
    private String sdt;

    @Column(name = "ngayTao")
    private Date ngayTao;

    @Column(name = "ngaySua")
    private Date ngaySua;

    @Column(name = "ngayShip")
    private Date ngayShip;

    @Column(name = "ngay_thanh_toan")
    private Date ngayThanhToan;

    @Column(name = "phiVanChuyen")
    private BigDecimal phiVanChuyen;

    @Column(name = "moTa")
    @Nationalized
    private String moTa;

    @Column(name = "tongTien")
    private BigDecimal tongTien;

    @Column(name = "trangThai")
    private Integer trangThai;

    @Column(name = "loaiHoaDon")
    private Boolean loaiHoaDon;

    @Column(name = "phuongThucThanhToan")
    private Boolean phuongThucThanhToan;

    @OneToMany(mappedBy = "hoaDon")
    @JsonIgnore
    private List<HoaDonChiTiet> hoaDonChiTiets;

    @ManyToOne
    @JoinColumn(name = "idNhanVien")
    private NhanVien nhanVien;

    @ManyToOne
    @JoinColumn(name = "idKhachHang")
    private KhachHang khachHang;

}
