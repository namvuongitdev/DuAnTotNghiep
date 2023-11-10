package com.example.web.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class DataThemVaoGioHang {

    private String idSP;
    private String idMS;
    private Integer soLuong;
    private String idKC;
}
