package com.example.web.service.impl;

import com.example.web.Config.status.HoaDonChiTietStatus;
import com.example.web.model.ChiTietSanPham;
import com.example.web.model.HoaDon;
import com.example.web.model.HoaDonChiTiet;
import com.example.web.model.LichSuHoaDon;
import com.example.web.model.NhanVien;
import com.example.web.model.SanPhamKhuyenMai;
import com.example.web.repository.IChiTietSanPhamRepository;
import com.example.web.repository.IHoaDonChiTietRepository;
import com.example.web.repository.IHoaDonRepository;
import com.example.web.repository.ILichSuHoaDonRepository;
import com.example.web.repository.INhanVienRepository;
import com.example.web.service.IHoaDonChiTietService;
import com.example.web.service.IKhuyenMaiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.util.Date;
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
    private INhanVienRepository nhanVienRepository;

    @Autowired
    private ILichSuHoaDonRepository lichSuHoaDonRepository;

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
                if(donGiaSauKhiGiam == null){
                     hdct.setDonGia(chiTietSanPham.getSanPham().getGiaBan());
                }else{
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
    public HoaDonChiTiet getOne(String id) {
        return hoaDonChiTietRepository.getReferenceById(UUID.fromString(id));
    }

    //--------------------------------------------------------------------------
    @Override
    public String addSanPhamHoaDonChiTietKhiUpdate(String idCTSP, String idHD, Integer soLuong) {
        Optional<HoaDon> hoaDon = hoaDonRepository.findById(UUID.fromString(idHD));
        Optional<ChiTietSanPham> ctsp = chiTietSanPhamRepository.findById(UUID.fromString(idCTSP));
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        NhanVien nhanVien = nhanVienRepository.findByEmailOrTaiKhoan(authentication.getName());
        HoaDonChiTiet hdct = null;
        if (hoaDon.isEmpty() || ctsp.isEmpty()) {
            return null;
        } else {
            ChiTietSanPham chiTietSanPham = ctsp.get();
            if (chiTietSanPham.getSoLuong() < soLuong) {
                return null;
            } else {
                Integer result = chiTietSanPham.getSoLuong() - soLuong;
                chiTietSanPham.setSoLuong(result);
                hdct = hoaDonChiTietRepository.findByChiTietSanPham_IdAndAndHoaDon_IdAndTrangThai(UUID.fromString(idCTSP), UUID.fromString(idHD), 0);
                if (hdct != null) {
                    Integer setSoLuongSanPhamTrongHDCT = hdct.getSoLuong() + soLuong;
                    hdct.setSoLuong(setSoLuongSanPhamTrongHDCT);
                    hdct.setChiTietSanPham(chiTietSanPham);
                } else {
                    hdct = HoaDonChiTiet.builder()
                            .hoaDon(hoaDon.get())
                            .donGia(chiTietSanPham.getSanPham().getGiaBan())
                            .soLuong(soLuong)
                            .chiTietSanPham(chiTietSanPham)
                            .trangThai(0)
                            .build();
                    if (!chiTietSanPham.getSanPham().getSanPhamKhuyenMais().isEmpty()) {
                        for (SanPhamKhuyenMai o : chiTietSanPham.getSanPham().getSanPhamKhuyenMais()) {
                            if (o.getKhuyenMai().getTrangThai() == 1 && o.getTrangThai() == 1) {
                                Integer donGiaSauKhiGiam = o.getSanPhamKM().getGiaBan().intValue() - (o.getSanPhamKM().getGiaBan().intValue() / 100) * o.getMucGiam().intValue();
                                hdct.setDonGia(BigDecimal.valueOf(donGiaSauKhiGiam));
                            } else {
                                hdct.setDonGia(chiTietSanPham.getSanPham().getGiaBan());
                            }
                        }
                    } else {
                        hdct.setDonGia(chiTietSanPham.getSanPham().getGiaBan());
                    }
                }
                Date date = java.util.Calendar.getInstance().getTime();
                LichSuHoaDon lshd = LichSuHoaDon.builder()
                        .hoaDon(hoaDon.get())
                        .nguoiThaoTac(nhanVien.getHoTen() + " (" + nhanVien.getChucVu().getTen() + ")")
                        .thaoTac("Thêm sản phẩm vào hóa đơn")
                        .ngayThaoTac(date)
                        .build();
                lichSuHoaDonRepository.save(lshd);
                hoaDonChiTietRepository.save(hdct);
                return "redirect:/admin/hoa-don-onl/detail/" + idHD;
            }
        }
    }

    @Override
    public String updateSoLuongSanPhamHoaDonChiTietKhiUpdate(String idHDCT, String soLuong) {
        Optional<HoaDonChiTiet> hoaDonChiTiet = hoaDonChiTietRepository.findById(UUID.fromString(idHDCT));
        if (hoaDonChiTiet.isPresent()) {
            HoaDonChiTiet hdct = hoaDonChiTiet.get();
            ChiTietSanPham ctsp = hdct.getChiTietSanPham();
            Integer soLuongTon = hdct.getSoLuong() + ctsp.getSoLuong();
            if (Integer.parseInt(soLuong) > soLuongTon) {
                return null;
            } else {
                ctsp.setSoLuong(soLuongTon - Integer.parseInt(soLuong));
                hdct.setChiTietSanPham(ctsp);
                if (Integer.parseInt(soLuong)<=0){
                    hdct.setTrangThai(1);
                    hdct.setSoLuong(hdct.getSoLuong());
                }else {
                    hdct.setTrangThai(0);
                    hdct.setSoLuong(Integer.parseInt(soLuong));
                }
                hoaDonChiTietRepository.save(hdct);
                return "redirect:/admin/hoa-don-onl/detail/" + hdct.getHoaDon().getId();
            }
        } else {
            return null;
        }
    }

    @Override
    public BigDecimal tongTienHDCT(UUID idHD) {
        BigDecimal tongTien = hoaDonRepository.tongTien(idHD);
        return tongTien;
    }

    @Override
    public String deleteSanPhamHoaDon2(String idHDCT) {
        Optional<HoaDonChiTiet> hoaDonChiTiet = hoaDonChiTietRepository.findById(UUID.fromString(idHDCT));
        if (hoaDonChiTiet.isPresent()) {
            HoaDonChiTiet hdct = hoaDonChiTiet.get();
            ChiTietSanPham ctsp = hdct.getChiTietSanPham();
            Integer result = ctsp.getSoLuong() + hdct.getSoLuong();
            ctsp.setSoLuong(result);
            hdct.setChiTietSanPham(ctsp);
            hdct.setTrangThai(HoaDonChiTietStatus.XOA);
            hoaDonChiTietRepository.save(hdct);
            if (hdct.getHoaDon().getLoaiHoaDon()){
                return "redirect:/admin/hoa-don-onl/detail/" + hdct.getHoaDon().getId();
            }else {
                return "redirect:/admin/hoa-don/view-update/" + hdct.getHoaDon().getId();
            }
        } else {
            return null;
        }
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
