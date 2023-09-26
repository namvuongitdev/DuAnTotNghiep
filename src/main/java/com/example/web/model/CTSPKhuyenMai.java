package com.example.web.model;

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
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "ctsp_khuen_mai")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class CTSPKhuyenMai {

    @Id
    @Column(name = "id")
    @GeneratedValue(generator = "UUID")
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "id_ctsp")
    private ChiTietSanPham chiTietSanPham;

    @ManyToOne
    @JoinColumn(name = "id_khuyen_mai")
    private KhuyenMai khuyenMai;

    @Column(name = "donGia")
    private BigDecimal donGia;

    @Column(name = "so_tien_sau_khi_giam")
    private BigDecimal soTienSauKhiGiam;

}
