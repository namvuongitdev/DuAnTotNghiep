package com.example.web.response;


import com.example.web.model.ChatLieu;
import com.example.web.model.DanhMuc;
import com.example.web.model.KieuDang;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.annotations.Nationalized;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.Date;
import java.util.UUID;

@Getter
@Setter
@ToString
public class SanPhamAndKhuyenMai {
    private UUID id;

    private String ma;

    private String ten;

    private String img;

    private Integer trangThai;

    private Date ngayTao;

    private Date ngaySua;

    private BigDecimal giaNhap;

    private BigDecimal giaBan;

    private String moTa;

    private Boolean gioiTinh;

    private KieuDang kieuDang;

    private ChatLieu chatLieu;

    private DanhMuc danhMuc;

    private BigDecimal donGiaSauKhiGiam;

    private Integer trangThaiSPKM;

    private Boolean loaiGiamGia;

    private BigDecimal mucGiam;

    private Integer trangThaiKM;

    public SanPhamAndKhuyenMai() {
    }

    public SanPhamAndKhuyenMai(UUID id, String ma, String ten, String img, Integer trangThai, Date ngayTao, Date ngaySua, BigDecimal giaNhap, BigDecimal giaBan, String moTa, Boolean gioiTinh, KieuDang kieuDang, ChatLieu chatLieu, DanhMuc danhMuc, BigDecimal donGiaSauKhiGiam, Integer trangThaiSPKM, Boolean loaiGiamGia, BigDecimal mucGiam, Integer trangThaiKM) {
        this.id = id;
        this.ma = ma;
        this.ten = ten;
        this.img = img;
        this.trangThai = trangThai;
        this.ngayTao = ngayTao;
        this.ngaySua = ngaySua;
        this.giaNhap = giaNhap;
        this.giaBan = giaBan;
        this.moTa = moTa;
        this.gioiTinh = gioiTinh;
        this.kieuDang = kieuDang;
        this.chatLieu = chatLieu;
        this.danhMuc = danhMuc;
        this.donGiaSauKhiGiam = donGiaSauKhiGiam;
        this.trangThaiSPKM = trangThaiSPKM;
        this.loaiGiamGia = loaiGiamGia;
        this.mucGiam = mucGiam;
        this.trangThaiKM = trangThaiKM;
    }
}
