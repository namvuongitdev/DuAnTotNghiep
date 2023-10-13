package com.example.web.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class NhanVienFilter {

    private String search;
    private Integer trangThai;
    private Boolean gioiTinh;
}
