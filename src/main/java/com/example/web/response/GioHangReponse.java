package com.example.web.response;
import org.springframework.beans.factory.annotation.Value;
import java.math.BigDecimal;

public interface GioHangReponse {

    @Value("#{target.idGHCT}")
    String getId();

    @Value("#{target.anhSanPham}")
    String getImg();

    @Value("#{target.tenSanPham}")
    String getTenSanPham();

    @Value("#{target.giaBanSanPham}")
    BigDecimal getGiaBanSanPham();

    @Value("#{target.soLuong}")
    Integer getSoLuong();

    @Value("#{target.mauSac}")
    String getMauSac();

    @Value("#{target.kichCo}")
    String getKichCo();

    @Value("#{target.donGiaSauKhiGiam}")
    BigDecimal getDonGiaSauKhiGiam();

    @Value("#{target.trangThaiKMCT}")
    Integer getTrangThaiKMCT();

    @Value("#{target.trangThaiKM}")
    Integer getTrangThaiKM();

    @Value("#{target.trangThaiKM == 1 && target.trangThaiKMCT == 1 ? (target.donGiaSauKhiGiam * target.soLuong) : (target.giaBanSanPham * target.soLuong)}")
    Integer getThanhTien();

}
