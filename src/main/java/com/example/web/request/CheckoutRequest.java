package com.example.web.request;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
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

public class CheckoutRequest {

    @NotBlank(message = "chưa chọn thành phố")
    private String thanhPho;

    @NotBlank(message = "chưa chọn quận huyện")
    private String quanHuyen;

    @NotBlank(message = "chưa chọn phường xã")
    private String phuongXa;

    @NotBlank(message = "chưa nhập họ tên")
    private String hoTen;

    @NotBlank(message = "chưa nhập số điện thoại")
    @Pattern(regexp = "^0\\d{9}$", message = "Số điện thoại không đúng định dạng.")
    private String soDienThoai;

    @NotBlank(message = "chưa nhập địa chỉ")
    private String diaChi;

    private Integer phuongThucThanhToan;

    private String ghiChu;

    private String ngayThanhToan;
}
