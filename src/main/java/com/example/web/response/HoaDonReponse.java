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
public class HoaDonReponse {

    private UUID id;

    private String tenSanPham;

    private String img;

    private BigDecimal donGia;

    private Integer soLuong;

    private String mauSac;

    private String kichCo;

    private String hoTen;

    private String soDienThoai;

    private String diaChi;

    private String email;

    private Integer soLuongSanPham;

}


