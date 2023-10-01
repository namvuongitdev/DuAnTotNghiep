package com.example.web.request;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class HoaDonRequest {

    private String hoaDon;
    private BigDecimal soTienThanhToan;
    private Boolean hinhThucThanhToan;
    private BigDecimal tienThuaTraKhach;
    private String moTa;

}
