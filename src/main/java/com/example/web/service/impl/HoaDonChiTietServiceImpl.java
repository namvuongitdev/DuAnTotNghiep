package com.example.web.service.impl;
import com.example.web.model.ChiTietSanPham;
import com.example.web.model.HoaDon;
import com.example.web.model.HoaDonChiTiet;
import com.example.web.repository.IChiTietSanPhamRepository;
import com.example.web.repository.IHoaDonChiTietRepository;
import com.example.web.repository.IHoaDonRepository;
import com.example.web.service.IHoaDonChiTietService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class HoaDonChiTietServiceImpl implements IHoaDonChiTietService {

    @Autowired
    private IHoaDonChiTietRepository hoaDonChiTietRepository;

    @Autowired
    private IHoaDonRepository hoaDonRepository;

    @Autowired
    private IChiTietSanPhamRepository chiTietSanPhamRepository;

    @Override
    public String addHoaDonChiTiet(String idCTSP, String idHD, Integer soLuong) {
        Optional<HoaDon> hoaDon = hoaDonRepository.findById(UUID.fromString(idHD));
        Optional<ChiTietSanPham> ctsp = chiTietSanPhamRepository.findById(UUID.fromString(idCTSP));
        HoaDonChiTiet hdct = null;
       if (hoaDon.isEmpty() || ctsp.isEmpty()) {
            return null;
        } else {
            ChiTietSanPham chiTietSanPham = ctsp.get();
            if(chiTietSanPham.getSoLuong() < soLuong){
                return null;
            }else{
                Integer result = chiTietSanPham.getSoLuong() - soLuong;
                chiTietSanPham.setSoLuong(result);
                hdct = hoaDonChiTietRepository.findByChiTietSanPham_IdAndAndHoaDon_IdAndTrangThai(UUID.fromString(idCTSP) , UUID.fromString(idHD) , 0);
                    if(hdct != null){
                        Integer setSoLuongSanPhamTrongHDCT = hdct.getSoLuong() + soLuong;
                        hdct.setSoLuong(setSoLuongSanPhamTrongHDCT);
                        hdct.setChiTietSanPham(chiTietSanPham);
                    }else{
                        hdct  = HoaDonChiTiet.builder()
                                .hoaDon(hoaDon.get())
                                .donGia(chiTietSanPham.getSanPham().getGiaBan())
                                .soLuong(soLuong)
                                .chiTietSanPham(chiTietSanPham)
                                .trangThai(0)
                                .build();
                    }
                 hoaDonChiTietRepository.save(hdct);
                return "redirect:/hoa-don/detail?idHD=" + hoaDon.get().getId();
            }
        }
    }

    @Override
    public String deleteSanPhamHoaDon(String idHDCT) {
        Optional<HoaDonChiTiet> hoaDonChiTiet = hoaDonChiTietRepository.findById(UUID.fromString(idHDCT));
        if (hoaDonChiTiet.isPresent()) {
            HoaDonChiTiet hdct = hoaDonChiTiet.get();
            ChiTietSanPham ctsp = hdct.getChiTietSanPham();
            Integer result = ctsp.getSoLuong() + hdct.getSoLuong();
            ctsp.setSoLuong(result);
            hdct.setChiTietSanPham(ctsp);
            hdct.setTrangThai(1);
            hoaDonChiTietRepository.save(hdct);
            return "redirect:/hoa-don/detail?idHD=" + hdct.getHoaDon().getId();
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
    public String updateHoaDonChiTiet(String idHDCT, String soLuong) {
        Optional<HoaDonChiTiet> hoaDonChiTiet = hoaDonChiTietRepository.findById(UUID.fromString(idHDCT));
        if (hoaDonChiTiet.isPresent()) {
            HoaDonChiTiet hdct = hoaDonChiTiet.get();
            if (Integer.parseInt(soLuong) == 0 || soLuong.isEmpty()) {
                return "redirect:/hoa-don/delete?idHDCT=" + hdct.getId() + "&idHD=" + hdct.getHoaDon().getId();
            } else {
                ChiTietSanPham ctsp = hdct.getChiTietSanPham();
                Integer soLuongTon = hdct.getSoLuong() + ctsp.getSoLuong();
                if(Integer.parseInt(soLuong) > soLuongTon){
                    return null;
                }else{

                    ctsp.setSoLuong(soLuongTon - Integer.parseInt(soLuong));
                    hdct.setChiTietSanPham(ctsp);
                    hdct.setSoLuong(Integer.parseInt(soLuong));

                    hoaDonChiTietRepository.save(hdct);
                    return "redirect:/hoa-don/detail?idHD=" + hdct.getHoaDon().getId();
                }
            }
        }else{
            return null;
        }
    }

    @Override
    public HoaDonChiTiet getOne(String id) {
        return hoaDonChiTietRepository.getReferenceById(UUID.fromString(id));
    }
}
