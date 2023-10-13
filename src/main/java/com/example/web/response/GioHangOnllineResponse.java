package com.example.web.response;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.UUID;

public class GioHangOnllineResponse {
    private UUID idGioHangCT;

    private String tenSanPham;

    private String tenAnh;

    private BigDecimal donGia;

    private Integer soLuong;

    private String tenMau;

    private String tenKichCo;


    public GioHangOnllineResponse(UUID idGioHangCT,String tenSanPham, String tenAnh, BigDecimal donGia, Integer soLuong, String tenMau, String tenKichCo) {
        this.idGioHangCT=idGioHangCT;
        this.tenSanPham = tenSanPham;
        this.tenAnh = tenAnh;
        this.donGia = donGia;
        this.soLuong = soLuong;
        this.tenMau = tenMau;
        this.tenKichCo = tenKichCo;
    }

    public UUID getIdGioHangCT() {
        return idGioHangCT;
    }

    public void setIdGioHangCT(UUID idGioHangCT) {
        this.idGioHangCT = idGioHangCT;
    }

    public String getTenSanPham() {
        return tenSanPham;
    }

    public void setTenSanPham(String tenSanPham) {
        this.tenSanPham = tenSanPham;
    }

    public String getTenAnh() {
        return tenAnh;
    }

    public void setTenAnh(String tenAnh) {
        this.tenAnh = tenAnh;
    }

    public BigDecimal getDonGia() {
        return donGia;
    }

    public String getDonGiaFormatter(){
        DecimalFormat formatter = new DecimalFormat("###,###,###");
        String formatted = formatter.format(this.donGia);
        return formatted;
    }

    public void setDonGia(BigDecimal donGia) {
        this.donGia = donGia;
    }

    public Integer getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(Integer soLuong) {
        this.soLuong = soLuong;
    }

    public String getTenMau() {
        return tenMau;
    }

    public void setTenMau(String tenMau) {
        this.tenMau = tenMau;
    }

    public String getTenKichCo() {
        return tenKichCo;
    }

    public void setTenKichCo(String tenKichCo) {
        this.tenKichCo = tenKichCo;
    }

    public String getThanhTien() {
        DecimalFormat formatter = new DecimalFormat("###,###,###");
        String gia = formatter.format(this.donGia.multiply(BigDecimal.valueOf(this.soLuong)));
        return gia;
    }


}
