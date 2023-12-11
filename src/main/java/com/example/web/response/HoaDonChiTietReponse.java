package com.example.web.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.math.BigDecimal;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class HoaDonChiTietReponse {

    private UUID id;
    private String tenSanPham;
    private String tenMauSac;
    private String tenKichCo;
    private Integer trangThai;
    private String img;
    private Integer soLuong;
    private BigDecimal donGia;
    private Integer soLuongCTSP;
}
