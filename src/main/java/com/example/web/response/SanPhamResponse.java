package com.example.web.response;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class SanPhamResponse {

    private UUID id;
    private String ma;
    private String ten;
    private String img;
    private Integer trangThai;
    private Date ngayTao;
    private BigDecimal giaNhap;
    private BigDecimal giaBan;
    private String moTa;
}
