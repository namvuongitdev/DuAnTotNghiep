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
@AllArgsConstructor
@NoArgsConstructor
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

    public String getGiaFormat() {
        DecimalFormat formatter = new DecimalFormat("###,###,###");
        String formatted = formatter.format(this.giaBan);
        return formatted;
    }
    public String getDonGiaSauKhiGiam1() {
        if(donGiaSauKhiGiam==null){
            return "";
        }else {
            DecimalFormat formatter = new DecimalFormat("###,###,###");
            String formatted = formatter.format(this.donGiaSauKhiGiam);
            return formatted +" đ";
        }
    }


}
