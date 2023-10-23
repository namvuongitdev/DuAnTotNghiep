package com.example.web.model;
import com.fasterxml.jackson.annotation.JsonIgnore;
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
import java.sql.Date;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "khuyen_mai")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class KhuyenMai {

    @Id
    @Column(name = "id")
    @GeneratedValue(generator = "UUID")
    private UUID id;

    @Column(name = "ma", unique = true)
    private String ma;

    @Column(name = "ten")
    private String ten;

    @Column(name = "ngay_bat_dau")
    private Date ngayBatDau;

    @Column(name = "ngay_Ket_thuc")
    private Date ngayKetThuc;

    @Column(name = "mo_ta" , length = 500)
    @Nationalized
    private String moTa;

    @Column(name = "trang_thai")
    private Integer trangThai;

    @OneToMany(mappedBy = "khuyenMai")
    @JsonIgnore
    private List<SanPhamKhuyenMai> sanPhamKhuyenMais;

}