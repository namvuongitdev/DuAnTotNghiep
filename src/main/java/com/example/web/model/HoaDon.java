package com.example.web.model;
import com.example.web.Config.status.HoaDonChiTietStatus;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Nationalized;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "hoa_don")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class HoaDon {

    @Id
    @Column(name = "id")
    @GeneratedValue(generator = "UUID")
    private UUID id;

    @Column(name = "ma", unique = true)
    private String ma;

    @Column(name = "diaChi")
    @Nationalized
    private String diaChi;

    @Column(name = "hoTen")
    @Nationalized
    private String hoTen;

    @Column(name = "sdt")
    private String sdt;

    @Column(name = "ngayTao")
    private Date ngayTao;

    @Column(name = "ngay_nhan_hang")
    private Date ngayNhanHang;

    @Column(name = "ngay_thanh_toan")
    private Date ngayThanhToan;

    @Column(name = "phiVanChuyen")
    private BigDecimal phiVanChuyen;

    @Column(name = "moTa")
    @Nationalized
    private String moTa;

    @Column(name = "tongTien")
    private BigDecimal tongTien;

    @Column(name = "trangThai")
    private Integer trangThai;

    @Column(name = "loaiHoaDon")
    private Integer loaiHoaDon;

    @Column(name = "phuongThucThanhToan")
    private Integer phuongThucThanhToan;

    @Column(name = "ma_giao_dich")
    private String maGiaoDich;

    @OneToMany(mappedBy = "hoaDon")
    @JsonIgnore
    private List<HoaDonChiTiet> hoaDonChiTiets;

    @ManyToOne
    @JoinColumn(name = "idNhanVien")
    private NhanVien nhanVien;

    @ManyToOne
    @JoinColumn(name = "idKhachHang")
    private KhachHang khachHang;


    public BigDecimal tongTien(){
        Integer tongTien = 0;
          for(HoaDonChiTiet hoaDonChiTiet : this.hoaDonChiTiets){
              if(hoaDonChiTiet.getTrangThai() == HoaDonChiTietStatus.KICH_HOAT){
                    tongTien += (hoaDonChiTiet.getDonGia().intValue() * hoaDonChiTiet.getSoLuong().intValue());
              }
          }
           Integer phiVanChuyen = this.getPhiVanChuyen() != null ? this.getPhiVanChuyen().intValue() : 0;
          return BigDecimal.valueOf(tongTien + phiVanChuyen);
    }

    public BigDecimal tongTienTraHang(){
        Integer tongTienTraHang = 0;
        for(HoaDonChiTiet hoaDonChiTiet : this.hoaDonChiTiets){
            if(hoaDonChiTiet.getTrangThai() == HoaDonChiTietStatus.TRA_HANG){
                tongTienTraHang += (hoaDonChiTiet.getDonGia().intValue() * hoaDonChiTiet.getSoLuong().intValue());
            }
        }
        return BigDecimal.valueOf(tongTienTraHang);
    }

    public BigDecimal tongTienHang(){
        Integer tongTien = 0;
        for(HoaDonChiTiet hoaDonChiTiet : this.hoaDonChiTiets){
            if(hoaDonChiTiet.getTrangThai() == HoaDonChiTietStatus.KICH_HOAT || hoaDonChiTiet.getTrangThai() == HoaDonChiTietStatus.TRA_HANG){
                tongTien += (hoaDonChiTiet.getDonGia().intValue() * hoaDonChiTiet.getSoLuong().intValue());
            }
        }
        return BigDecimal.valueOf(tongTien);
    }
}
