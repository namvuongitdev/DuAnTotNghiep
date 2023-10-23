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
public class KhuyenMaiReponse {

    private UUID id;
    private String tenSanPham;
    private String maSanPham;
    private Boolean loaiGiamGia;
    private BigDecimal mucGiam;
    private BigDecimal giaBanDau;
    private BigDecimal donGiaKhiGiam;
    private Integer trangThai;
}
