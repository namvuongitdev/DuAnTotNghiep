package com.example.web.model;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "ctsp_khuen_mai")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class SanPhamKhuyenMai {

    @Id
    @Column(name = "id")
    @GeneratedValue(generator = "UUID")
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "id_san_pham")
    @JsonIgnore
    private SanPham sanPhamKM;

    @ManyToOne
    @JoinColumn(name = "id_khuyen_mai")
    private KhuyenMai khuyenMai;

    @Column(name = "loai_giam_gia")
    private Boolean loaiGiamGia;

    @Column(name = "muc_giam")
    private BigDecimal mucGiam;

    @Column(name = "trang_thai")
    private Integer trangThai;

    @Column(name = "don_gia_sau_khi_giam")
    private BigDecimal donGiaSauKhiGiam;

    @Transient
    private List<SanPham> sanPhams;



}
