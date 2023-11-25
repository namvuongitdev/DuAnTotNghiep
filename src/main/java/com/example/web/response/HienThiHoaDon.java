package com.example.web.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class HienThiHoaDon {

    private UUID id;
    private String maHoaDon;
    private Boolean loaiHoaDon;
    private Date ngayTao;
    private String tenKhachHang;
    private BigDecimal tongTien;
    private Integer trangThai;
}
