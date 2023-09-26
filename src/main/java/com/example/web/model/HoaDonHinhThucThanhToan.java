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
import java.util.UUID;

@Entity
@Table(name="hoa_don_hinh_thuc_thanh_Toan")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class HoaDonHinhThucThanhToan {

    @Id
    @GeneratedValue(generator = "UUID")
    @Column(name = "id")
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "id_hinh_thuc_thanh_toan")
    private HinhThucThanhToan hinhThucThanhToan;

    @ManyToOne
    @JoinColumn(name="id_hoa_don")
    private HoaDon hoaDon;
}
