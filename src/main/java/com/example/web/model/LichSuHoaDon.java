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
import org.hibernate.annotations.Nationalized;
import java.util.Date;
import java.util.UUID;

@Entity
@Table(name="lich_su_hoa_don")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LichSuHoaDon {

    @Id
    @Column(name = "id")
    @GeneratedValue(generator = "UUID")
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "id_hoa_don")
    private HoaDon hoaDon;

    @Column(name = "ma_nhan_vien")
    private String maNhanVien;

    @Column(name = "ho_ten_nhan_vien")
    @Nationalized
    private String hoTenNhanVien;

    @Column(name = "thoi_gian")
    private Date thoi_gian;

    @Column(name = "ghi_chu")
    @Nationalized
    private String ghiChu;

    @Column(name = "trang_thai")
    private Integer trangThai;
}
