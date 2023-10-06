package com.example.web.model;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
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

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "danh_muc")
@Builder
@Getter
@Setter

public class DanhMuc {
    @Id
    @Column(name = "id")
    private String id;

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

    @OneToMany(mappedBy = "danhMuc")
    @JsonIgnore
    private List<SanPham> sanPhams;
}
