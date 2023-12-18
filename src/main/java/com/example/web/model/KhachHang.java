package com.example.web.model;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
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
@Table(name = "khach_hang")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class KhachHang {

    @Id
    @Column(name = "id")
    @GeneratedValue(generator = "UUID")
    private UUID id;

    @Pattern(regexp = "^[\\p{L}\\s']+", message = "Họ và tên không đúng định dạng.")
    @Column(name = "hoTen")
    @Nationalized
    private String hoTen;

    @Pattern(regexp = "^(.+)@(\\S+)$", message = "Email không đúng định dạng.")
    @Column(name = "email")
    private String email;

    @Column(name = "trangThai")
    private Integer trangThai;

    @NotBlank(message = "Vui lòng không để trống thông tin.")
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

    @Pattern(regexp = "^0\\d{9}$", message = "Số điện thoại không đúng định dạng.")
    @Column(name="sdt")
    private String sdt;

    @Pattern(regexp = "[\\p{L}0-9\\s,./-]+", message = "Địa chỉ không đúng định dạng.")
    @Column(name = "diaChi")
    @Nationalized
    private String diaChi;

    @OneToMany(mappedBy = "khachHang")
    @JsonIgnore
    private List<HoaDon> hoaDons;

    @OneToMany(mappedBy = "khachHang" ,fetch = FetchType.EAGER)
    @JsonIgnore
    private List<DiaChi> diaChis;

    public KhachHang(UUID id, @NotBlank(message = "Vui lòng không để trống thông tin.") @Pattern(regexp = "^[\\p{L}\\s']+", message = "Họ và tên không đúng định dạng.") String hoTen, @NotBlank(message = "Vui lòng không để trống thông tin.") @Pattern(regexp = "^(.+)@(\\S+)$", message = "Email không đúng định dạng.") String email, Integer trangThai, @NotBlank(message = "Vui lòng không để trống thông tin.") String taiKhoan, String matKhau, Date ngayTao, @NotBlank(message = "Vui lòng không để trống thông tin.") @Pattern(regexp = "^0\\d{9}$", message = "Số điện thoại không đúng định dạng.") String sdt) {
        this.id = id;
        this.hoTen = hoTen;
        this.email = email;
        this.trangThai = trangThai;
        this.taiKhoan = taiKhoan;
        this.matKhau = matKhau;
        this.ngayTao = ngayTao;
        this.sdt = sdt;
    }
}
