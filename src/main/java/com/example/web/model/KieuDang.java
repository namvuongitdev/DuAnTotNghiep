package com.example.web.model;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
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

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "form_dang")

public class KieuDang {
    @Id
    @GeneratedValue(generator = "UUID")
    @Column(name = "id")
    private UUID id;

    @NotBlank(message = "Không được để trống")
    @Column(name = "ten")
    @Nationalized
    private String ten;

    @Column(name = "trangthai")
    private Integer trangThai;

    @Column(name = "ngay_tao")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date ngayTao;

    @Column(name = "ngay_sua")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date ngaySua;

    @OneToMany(mappedBy = "kieuDang")
    @JsonIgnore
    private List<SanPham> sanPhams;

    public KieuDang(String ten, Integer trangThai) {
        this.ten = ten;
        this.trangThai = trangThai;
    }
}
