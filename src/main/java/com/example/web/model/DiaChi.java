package com.example.web.model;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
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
    @Pattern(regexp = "^[\\p{L}\\s']+", message = "Họ và tên không đúng định dạng.")
    @Nationalized
    private String hoTen;

    @Pattern(regexp = "^0\\d{9}$", message = "Số điện thoại không đúng định dạng.")
    @Column(name = "sdt")
    private String sdt;

    @Column(name = "dia_chi" , length = 500)
    @Pattern(regexp = "[\\p{L}0-9\\s,./-]+", message = "Địa chỉ không đúng định dạng.")
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
