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
import lombok.ToString;
import java.util.Date;
import java.util.UUID;

@Entity
@Table(name = "anh")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString

public class Anh {

    @Id
    @Column(name="id")
    @GeneratedValue(generator = "UUID")
    private UUID id;

    @Column(name = "ten")
    private String ten;

    @Column(name = "ngay_tao")
    private Date ngayTao;

    @Column(name = "trang_thai")
    private Integer trangThai;

    @Column(name = "ngay_sua")
    private Date ngaySua;

    @ManyToOne
    @JoinColumn(name = "idctsp")
    private ChiTietSanPham chiTietSanPham;
}
