package com.example.web.request;
import com.example.web.model.KhachHang;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.math.BigDecimal;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class HoaDonRequest {

    private String id;
    private String hoTen;
    private String sdt;
    private String diaChi;
    private BigDecimal phiVanChuyen;
    private String soTienThanhToan;
    private Boolean hinhThucThanhToan;
    private String moTa;
    private String idKhachHang;
    private BigDecimal tongTien;
    private Integer trangThai;
    private Date ngayThanhToan;

}
