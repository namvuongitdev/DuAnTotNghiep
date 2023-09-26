package com.example.web.model;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "khuyen_mai")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class KhuyenMai {

    @Id
    @Column(name = "id")
    @GeneratedValue(generator = "UUID")
    private UUID id;

    @Column(name = "ma", unique = true)
    private String ma;

    @Column(name = "ten")
    private String ten;

    @Column(name = "ngay_bat_dau")
    private Date ngayBatDau;

    @Column(name = "ngay_Ket_thuc")
    private Date ngayKetThuc;

    @Column(name = "loai_giam_gia")
    private Boolean loaiGiamGia;

    @Column(name = "muc_giam_gia")
    private BigDecimal mucGiamGia;

    @Column(name = "trang_thai")
    private Integer trangThai;

    @OneToMany(mappedBy = "khuyenMai")
    private List<CTSPKhuyenMai> ctspKhuyenMais;

    @Transient
    private List<ChiTietSanPham> chiTietSanPhams;

}