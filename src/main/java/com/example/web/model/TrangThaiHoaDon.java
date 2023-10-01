package com.example.web.model;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum TrangThaiHoaDon {

    CHO_XAC_NHAN(0 ) , DA_XAC_NHAN(1 ) , DANG_VAN_CHUYEN(2 ) , DA_HOAN_THANH(4) , HUY_HOA_DON(5);
    private Integer value;
}
