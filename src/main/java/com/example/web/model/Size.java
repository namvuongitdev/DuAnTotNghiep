package com.example.web.model;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.UUID;

@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "kich_co")
public class Size {
    @Id
    @Column(name = "Id")
    private String id;

    @NotBlank(message = "Không được để trống")
    @Column(name = "Ten")
    private String ten;

    @Column(name = "trangthai")
    private Integer trangThai;

    @Column(name = "ngay_tao")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date ngayTao;

    @Column(name = "ngay_sua")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date ngaySua;

}
