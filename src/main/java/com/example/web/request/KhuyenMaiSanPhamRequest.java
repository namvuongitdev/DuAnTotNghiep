package com.example.web.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class KhuyenMaiSanPhamRequest {

    private String idSanPham;
    private Boolean loaiGiamGia;
    private BigDecimal mucGiam;
    private String idKM;
}
