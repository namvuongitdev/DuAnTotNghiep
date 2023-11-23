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
public class ThongTinKhachHang {
    private String hoTen;
    private String sdt;
    private BigDecimal phiVanChuyen;
    private String diaChi;
    private String ghiChu;
}
