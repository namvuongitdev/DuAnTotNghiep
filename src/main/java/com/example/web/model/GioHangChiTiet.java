package com.example.web.model;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
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
import java.util.UUID;

@Entity
@Table(name = "gio_hang_chi_tiet")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter

public class GioHangChiTiet {

    @Id
    @Column(name = "id")
    @GeneratedValue(generator = "UUID")
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "idGioHang")
    private GioHang gioHang;

    @ManyToOne
    @JoinColumn(name = "idCTSP")
    private ChiTietSanPham chiTietSanPham;

    @Column(name="soLuong")
    private Integer soLuong;

}
