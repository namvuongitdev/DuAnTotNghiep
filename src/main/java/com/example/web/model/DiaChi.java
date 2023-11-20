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
import org.hibernate.annotations.Nationalized;
import java.util.UUID;

@Entity
@Table(name = "dia_chi")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class DiaChi {

    @Id
    @Column(name = "id")
    @GeneratedValue(generator = "UUID")
    private UUID id;

    @Column(name = "ho_ten")
    @Nationalized
    private String hoTen;

    @Column(name = "sdt")
    private String sdt;

    @Column(name = "dia_chi" , length = 500)
    @Nationalized
    private String diaChi;

    @Column(name = "dia_chi_mac_dinh")
    private Boolean diaChiMacDinh;

    @ManyToOne
    @JoinColumn(name = "id_khachHang")
    private KhachHang khachHang;

    @Transient
    private String ghiChu;
}
