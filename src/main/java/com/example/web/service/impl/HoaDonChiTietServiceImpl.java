package com.example.web.service.impl;

import com.example.web.Config.status.HoaDonChiTietStatus;
import com.example.web.model.ChiTietSanPham;
import com.example.web.model.HoaDon;
import com.example.web.model.HoaDonChiTiet;
import com.example.web.repository.IChiTietSanPhamRepository;
import com.example.web.repository.IHoaDonChiTietRepository;
import com.example.web.repository.IHoaDonRepository;
import com.example.web.service.IHoaDonChiTietService;
import com.example.web.service.IKhuyenMaiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class HoaDonChiTietServiceImpl implements IHoaDonChiTietService {

    @Autowired
    private IHoaDonChiTietRepository hoaDonChiTietRepository;

    @Autowired
    private IHoaDonRepository hoaDonRepository;

    @Autowired
    private IChiTietSanPhamRepository chiTietSanPhamRepository;

    @Autowired
    private IKhuyenMaiService khuyenMaiService;


    @Override
    public HoaDonChiTiet addHoaDonChiTiet(UUID idCTSP, UUID idHD, Integer soLuong) {
        Optional<HoaDon> hoaDon = hoaDonRepository.findById(idHD);
        Optional<ChiTietSanPham> ctsp = chiTietSanPhamRepository.findById(idCTSP);
        HoaDonChiTiet hdct = null;
        ChiTietSanPham chiTietSanPham = ctsp.get();
        BigDecimal donGiaSauKhiGiam = khuyenMaiService.donGiaSauKhiGiam(chiTietSanPham.getSanPham().getSanPhamKhuyenMais());
        if (chiTietSanPham.getSoLuong() < soLuong) {
            return null;
        } else {
            Integer result = chiTietSanPham.getSoLuong() - soLuong;
            chiTietSanPham.setSoLuong(result);
            hdct = hoaDonChiTietRepository.findByChiTietSanPham_IdAndAndHoaDon_IdAndTrangThai(idCTSP, idHD, 0);
            if (hdct != null) {
                Integer setSoLuongSanPhamTrongHDCT = hdct.getSoLuong() + soLuong;
                hdct.setSoLuong(setSoLuongSanPhamTrongHDCT);
                hdct.setChiTietSanPham(chiTietSanPham);
            } else {
                hdct = HoaDonChiTiet.builder()
                        .hoaDon(hoaDon.get())
                        .soLuong(soLuong)
                        .chiTietSanPham(chiTietSanPham)
                        .trangThai(HoaDonChiTietStatus.KICH_HOAT)
                        .build();
                if (donGiaSauKhiGiam == null) {
                    hdct.setDonGia(chiTietSanPham.getSanPham().getGiaBan());
                } else {
                    hdct.setDonGia(donGiaSauKhiGiam);
                }
            }
            return hoaDonChiTietRepository.save(hdct);
        }

    }

    @Override
    public HoaDonChiTiet deleteSanPhamHoaDon(String idHDCT) {
        Optional<HoaDonChiTiet> hoaDonChiTiet = hoaDonChiTietRepository.findById(UUID.fromString(idHDCT));
        if (hoaDonChiTiet.isPresent()) {
            HoaDonChiTiet hdct = hoaDonChiTiet.get();
            ChiTietSanPham ctsp = hdct.getChiTietSanPham();
            Integer result = ctsp.getSoLuong() + hdct.getSoLuong();
            ctsp.setSoLuong(result);
            hdct.setChiTietSanPham(ctsp);
            hdct.setTrangThai(HoaDonChiTietStatus.XOA);
            return hoaDonChiTietRepository.save(hdct);
        } else {
            return null;
        }
    }

    @Override
    public HoaDonChiTiet getHoaDonChiTiet(String id) {
        Optional<HoaDonChiTiet> hdct = hoaDonChiTietRepository.findById(UUID.fromString(id));
        if (hdct.isPresent()) {
            return hdct.get();
        }
        return null;
    }

    @Override
    public HoaDonChiTiet updateHoaDonChiTiet(String idHDCT, String soLuong) {
        Optional<HoaDonChiTiet> hoaDonChiTiet = hoaDonChiTietRepository.findById(UUID.fromString(idHDCT));
        if (hoaDonChiTiet.isPresent()) {
            HoaDonChiTiet hdct = hoaDonChiTiet.get();
            if (Integer.parseInt(soLuong) == 0 || soLuong.isEmpty()) {
                return hoaDonChiTietRepository.save(deleteSanPhamHoaDon(idHDCT));
            } else {
                ChiTietSanPham ctsp = hdct.getChiTietSanPham();
                Integer soLuongTon = hdct.getSoLuong() + ctsp.getSoLuong();
                if (Integer.parseInt(soLuong) > soLuongTon) {
                    return null;
                } else {

                    ctsp.setSoLuong(soLuongTon - Integer.parseInt(soLuong));
                    hdct.setChiTietSanPham(ctsp);
                    hdct.setSoLuong(Integer.parseInt(soLuong));
                    return hoaDonChiTietRepository.save(hdct);
                }
            }
        } else {
            return null;
        }
    }

    @Override
    public Integer traHang(UUID idHDCT, Integer soLuong) {
        Optional<HoaDonChiTiet> hdct = hoaDonChiTietRepository.findById(idHDCT);
        if (hdct.isPresent()) {
            HoaDonChiTiet hoaDonChiTiet = hdct.get();
            //check số lượng
            if (soLuong > hoaDonChiTiet.getSoLuong()) {
                return 1;
            }
            if (soLuong <= 0) {
                return 1;
            }
            // update lại số lượng sản phẩm
            ChiTietSanPham ctsp = chiTietSanPhamRepository.findById(hoaDonChiTiet.getChiTietSanPham().getId()).get();
            Integer updateSoLuong = ctsp.getSoLuong() + soLuong;
            Integer soLuongConLai = hoaDonChiTiet.getSoLuong() - soLuong;
            ctsp.setSoLuong(updateSoLuong);
            // nếu là trả hết thì cập nhập lại trạng thái luôn
            if (soLuong == hoaDonChiTiet.getSoLuong()) {
                hoaDonChiTiet.setTrangThai(HoaDonChiTietStatus.TRA_HANG);
            }
            if (soLuong < hoaDonChiTiet.getSoLuong()) {
                // insert thêm một hoá đơn chi tiết có trạng thái trả hàng
                HoaDonChiTiet newHDCT = HoaDonChiTiet.builder()
                        .hoaDon(hoaDonChiTiet.getHoaDon())
                        .trangThai(HoaDonChiTietStatus.TRA_HANG)
                        .chiTietSanPham(hoaDonChiTiet.getChiTietSanPham())
                        .donGia(hoaDonChiTiet.getDonGia())
                        .soLuong(soLuong)
                        .build();
                hoaDonChiTiet.setSoLuong(soLuongConLai);
                hoaDonChiTietRepository.save(newHDCT);
                hoaDonChiTietRepository.save(hoaDonChiTiet);
            }
            chiTietSanPhamRepository.save(ctsp);
            return 2;
        } else {
            return 0;
        }
    }

    @Override
    public HoaDonChiTiet getOne(String id) {
        return hoaDonChiTietRepository.getReferenceById(UUID.fromString(id));
    }


    @Override
    public BigDecimal tongTienHDCT(UUID idHD) {
        BigDecimal tongTien = hoaDonRepository.tongTien(idHD);
        return tongTien;
    }

    @Override
    public List<HoaDonChiTiet> getAllByIdHoaDon(UUID idHD) {
        return hoaDonChiTietRepository.getAllByIdHoaDon(idHD);
    }

    @Override
    public Integer soLuongSPDaBan() {
        return hoaDonChiTietRepository.soLuongSPDaBan();
    }

    @Override
    public Integer tongDoanhThu() {
        return hoaDonChiTietRepository.tongDoanhThu();
    }

    @Override
    public Integer tongHoaDon() {
        return hoaDonChiTietRepository.tongHoaDon();
    }

    @Override
    public Double getDoanhThuTrongNgay() {
        return hoaDonChiTietRepository.getDoanhThuTrongNgay();
    }

    @Override
    public Double getDoanhThuTheoThang(Integer thang) {
        return hoaDonChiTietRepository.getDoanhThuTheoThang(thang);
    }
}
