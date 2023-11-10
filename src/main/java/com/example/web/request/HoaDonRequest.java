package com.example.web.request;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import java.math.BigDecimal;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class HoaDonRequest {

    private String id;

    @NotBlank(message = "chưa nhập họ tên")
    private String hoTen;

    @NotBlank(message = "chưa nhập số điện thoại")
    @Pattern(regexp =  "^0[1-9]\\d{8}$" , message = "số điện thoại chưa đúng định dạng")
    private String sdt;

    @NotBlank(message = "chưa nhập địa chỉ")
    private String diaChi;

    @NotNull(message = "chưa nhập phí vận chuyển")
    private BigDecimal phiVanChuyen;

    private String soTienThanhToan;
    private Integer hinhThucThanhToan;
    private String moTa;
    private String idKhachHang;
    private BigDecimal tongTien;
    private Integer trangThai;
    private Date ngayThanhToan;

}
