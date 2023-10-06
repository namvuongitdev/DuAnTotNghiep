package com.example.web.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.hibernate.annotations.Nationalized;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@Entity
@Table(name = "mau_sac")
public class MauSac {

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

    public MauSac(String ten, Integer trangThai) {
        this.ten = ten;
        this.trangThai = trangThai;
    }
}
