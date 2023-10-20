package com.example.web.response;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.math.BigDecimal;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class SanPhamAsKhuyenMai {

    private UUID idSanPham;
    private String tenSanPham;
    private String maSanPham;
    private String img;
    private BigDecimal giaSanPham;
    private Integer trangThaiSanPham;
    private Integer trangThaiSanPhamKhuyenMai;
    private Integer trangThaiKhuyenMai;
    private Boolean loaiGiamGia;
    private BigDecimal mucGiam;
    private BigDecimal donGiaKhiGiam;
}
