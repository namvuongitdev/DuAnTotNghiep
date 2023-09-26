package com.example.web.model;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum TrangThaiLichSuHoaDon {

    Tao_Hoa_Don(0),
    Xoa_San_Pham(1 ),
    Update_So_Luong(2 ),
    Them_san_pham(3 ),
    HUY_HOA_DON(4);
    private Integer value;
}
