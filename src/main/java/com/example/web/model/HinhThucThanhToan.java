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
import org.hibernate.annotations.Nationalized;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "hinh_thuc_thanh_toan")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class HinhThucThanhToan {

    @Id
    @GeneratedValue(generator = "UUID")
    @Column(name = "id")
    private UUID id;

    @Column(name = "loai_hinh_thuc_thanh_toan")
    private Boolean loaiHinhThanhToan;

    @Column(name="so_tien_thanh_toan")
    private BigDecimal soTienThanhToan;

    @Column(name="trang_thai")
    private Integer trangThai;

    @Column(name = "ghi_chu")
    @Nationalized
    private String ghiChu;

    @Column(name = "thoi_gian")
    private Date date;

    @Column(name="loai_giao_dich")
    private Integer loaiGiaoDich;

    @ManyToOne
    @JoinColumn(name="id_nhan_vien")
    private NhanVien nhanVien;

    @OneToMany(mappedBy = "hinhThucThanhToan")
    private List<HoaDonHinhThucThanhToan> hoaDonHinhThucThanhToans;
}
