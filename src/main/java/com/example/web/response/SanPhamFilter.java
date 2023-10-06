package com.example.web.response;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SanPhamFilter {

    private String search;
    private String danhMuc;
    private String chatLieu;
    private String kieuDang;
    private String mauSac;
    private String kichCo;
    private Integer trangThai;
    private String kichCo;
    private String mauSac;
    private String sapXep;
}
