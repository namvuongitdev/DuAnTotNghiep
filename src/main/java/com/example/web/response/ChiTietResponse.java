package com.example.web.response;

import com.example.web.model.MauSac;
import com.example.web.model.SanPham;
import com.example.web.model.Size;
import lombok.*;

import java.util.UUID;
import java.util.UUID;


@Getter
@Setter
@ToString
public class ChiTietResponse {
    private Integer soLuong;

    private Integer trangThai;

    private UUID id;

    private MauSac mauSac;

    private SanPham sanPham;

    private Size size;

    private String qrCode;

    public ChiTietResponse(Integer soLuong, Integer trangThai, UUID id, MauSac mauSac, SanPham sanPham, Size size, String qrCode) {
        this.soLuong = soLuong;
        this.trangThai = trangThai;
        this.id = id;
        this.mauSac = mauSac;
        this.sanPham = sanPham;
        this.size = size;
        this.qrCode = qrCode;
    }
}
