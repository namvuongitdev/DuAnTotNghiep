package com.example.web.request;
import com.example.web.model.KhachHang;
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
    private String hoTen;
    private String sdt;
    private String diaChi;
    private BigDecimal phiVanChuyen;
    private BigDecimal soTienThanhToan;
    private Boolean hinhThucThanhToan;
    private String moTa;
    private String idKhachHang;

}
