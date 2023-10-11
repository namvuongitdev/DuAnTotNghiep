package com.example.web.model;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum TrangThaiHoaDon {

    HOA_DON_CHO(0 ) ,Cho_xac_nhan(1) ,DA_XAC_NHAN(2) , DANG_VAN_CHUYEN(3) , DA_HOAN_THANH(4) , HUY_HOA_DON(5);
    private Integer value;
}
