package com.example.web.model;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Nationalized;
import org.springframework.format.annotation.DateTimeFormat;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "san_pham")

public class SanPham {

    @Id
    @GeneratedValue(generator = "UUID")
    @Column(name = "id")
    private UUID id;

    @Column(name = "ma" , unique = true)
    private String ma;

    @NotBlank(message = "Vui lòng điền tên sản phẩm.")
    @Column(name = "ten")
    @Nationalized
    private String ten;

    @Column(name="img")
    private String img;

    @Column(name = "trangthai")
    private Integer trangThai;

    @Column(name = "ngaytao")
    private Date ngayTao;

    @Column(name = "ngaysua")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date ngaySua;

    @NotNull(message = "Vui lòng điền giá bán.")
    @DecimalMin(value = "50000.00", message = "Giá bán phải lớn hơn hoặc bằng 50.000")
    @Column(name = "giaban")
    private BigDecimal giaBan;

    @NotBlank(message = "Vui lòng điền mô tả.")
    @Column(name = "mota",length = 3000)
    private String moTa;

    @Column(name = "gioiTinh")
    private Boolean gioiTinh;

    @ManyToOne
    @JoinColumn(name = "id_Kieu_dang")
    private KieuDang kieuDang;

    @ManyToOne
    @JoinColumn(name = "id_chat_lieu")
    private ChatLieu chatLieu;

    @ManyToOne
    @JoinColumn(name = "id_danh_muc_san_pham")
    private DanhMuc danhMuc;

    @OneToMany(mappedBy = "sanPham")
    @JsonIgnore
    private List<Anh> anhs;

    @OneToMany(mappedBy = "sanPham")
    @JsonIgnore
    private List<ChiTietSanPham> chiTietSanPhams;

    @OneToMany(mappedBy = "sanPhamKM")
    private List<SanPhamKhuyenMai> sanPhamKhuyenMais;

}
