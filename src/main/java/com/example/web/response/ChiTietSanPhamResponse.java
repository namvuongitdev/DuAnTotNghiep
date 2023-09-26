package com.example.web.response;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class ChiTietSanPhamResponse {

    private UUID id;

    private Integer soLuong;

    private SanPhamResponse sanPham;

    private Integer trangThai;

    private MauSacReponse mauSac;

    private KichCoResponse size;

    private DanhMucResponse danhMuc;

}
