package com.example.web.model;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
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

    @NotBlank(message = "Vui lòng không để trống thông tin.")
    @Column(name = "hoTen")
    @Nationalized
    private String hoTen;

    @Column(name = "gioiTinh")
    private Boolean gioiTinh;

    @NotBlank(message = "Vui lòng không để trống thông tin.")
    @Pattern(regexp = "^(.+)@(\\S+)$", message = "Email không đúng định dạng.")
    @Column(name = "email", unique = true)
    private String email;

    @Column(name = "trangThai")
    private Integer trangThai;

    @NotBlank(message = "Vui lòng không để trống thông tin.")
    @Column(name = "taiKhoan", unique = true)
    private String taiKhoan;

    @Column(name = "matKhau")
    private String matKhau;

    @Column(name = "ngayTao")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date ngayTao;

    @Column(name = "ngaySua")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date ngaySua;

    @NotBlank(message = "Vui lòng không để trống thông tin.")
    @Pattern(regexp = "^0\\d{9}$", message = "Số điện thoại không đúng định dạng.")
    @Column(name="sdt")
    private String sdt;

    @NotBlank(message = "Vui lòng không để trống thông tin.")
    @Column(name = "diaChi")
    @Nationalized
    private String diaChi;

    @OneToMany(mappedBy = "nhanVien")
    private List<HoaDon> hoaDons;

    @ManyToOne
    @JoinColumn(name = "idChucVu")
    private ChucVu chucVu;

    @NotBlank(message = "Vui lòng không để trống thông tin.")
    @Pattern(regexp = "\\d{12}$", message = "Số CCCD không đúng định dạng.")
    @Column(name="cccd")
    private String cccd;

}
