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

    private UUID id;
    private String tenSanPham;
    private String maSanPham;
    private Boolean loaiGiamGia;
    private BigDecimal mucGiam;
    private Integer trangThai;

    public SanPhamAsKhuyenMai(UUID id, BigDecimal mucGiam) {
        this.id = id;
        this.mucGiam = mucGiam;
    }
}
