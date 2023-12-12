package com.example.web.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class HoaDonFilter {

    private String search;

    private Integer loaiHoaDon;

    private Integer trangThai;

    private String dateBegin;

    private String dateEnd;

}
