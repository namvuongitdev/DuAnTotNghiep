package com.example.web.model;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Nationalized;
import org.springframework.format.annotation.DateTimeFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "nhan_vien")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class NhanVien {

    @Id
    @Column(name="id")
    @GeneratedValue(generator = "UUID")
    private UUID id;

    @Column(name = "hoTen")
    @Nationalized
    private String hoTen;

    @Column(name = "gioiTinh")
    private Boolean gioiTinh;

    @Column(name = "email")
    private String email;

    @Column(name = "trangThai")
    private Integer trangThai;

    @Column(name = "taiKhoan")
    private String taiKhoan;

    @Column(name = "matKhau")
    private String matKhau;

    @Column(name = "ngayTao")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date ngayTao;

    @Column(name = "ngaySua")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date ngaySua;

    @Column(name="sdt")
    private String sdt;

    @Column(name = "diaChi")
    @Nationalized
    private String diaChi;

    @OneToMany(mappedBy = "nhanVien")
    private List<HoaDon> hoaDons;

}
