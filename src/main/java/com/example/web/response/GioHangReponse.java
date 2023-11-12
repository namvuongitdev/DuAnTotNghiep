package com.example.web.response;
import org.springframework.beans.factory.annotation.Value;
import java.math.BigDecimal;
import java.util.UUID;

public interface GioHangReponse {

    @Value("#{target.idGHCT}")
    String getId();

    @Value("#{target.img}")
    String getImg();

    @Value("#{target.idSP}")
    String getIdSP();

    @Value("#{target.idMS}")
    String getIdMS();

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
    BigDecimal getThanhTien();

    @Value("#{target.idCTSP}")
    UUID getIdCTSP();

}
