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
import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "hoa_don_chi_tiet")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class HoaDonChiTiet {

    @Id
    @GeneratedValue(generator = "UUID")
    @Column(name = "id")
    private UUID id;

    @Column(name = "donGia")
    private BigDecimal donGia;

    @Column(name = "soLuong")
    private Integer soLuong;

    @Column(name = "trangThai")
    private Integer trangThai;

    @ManyToOne
    @JoinColumn(name = "idHoaDon")
    private HoaDon hoaDon;

    @ManyToOne
    @JoinColumn(name = "idCTSP")
    private ChiTietSanPham chiTietSanPham;

}
