package com.example.web.model;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Table(name = "gio_hang_chi_tiet")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter

public class GioHangChiTiet {

    @Id
    @ManyToOne
    @JoinColumn(name = "idGioHang")
    private GioHang gioHang;

    @Id
    @ManyToOne
    @JoinColumn(name = "idCTSP")
    private ChiTietSanPham chiTietSanPham;

    @Column(name="soLuong")
    private Integer soLuong;

    @Column(name = "donGia")
    private BigDecimal donGia;

    @Column(name = "donGiaKHiGiam")
    private BigDecimal donGiaKhiGiam;

}
