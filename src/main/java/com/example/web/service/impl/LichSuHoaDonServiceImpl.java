package com.example.web.service.impl;

import com.example.web.Config.status.HoaDonStatus;
import com.example.web.Config.status.LoaiHoaDon;
import com.example.web.model.HoaDon;
import com.example.web.model.KhachHang;
import com.example.web.model.LichSuHoaDon;
import com.example.web.model.NhanVien;
import com.example.web.repository.IHoaDonRepository;
import com.example.web.repository.IKhachHangRepository;
import com.example.web.repository.ILichSuHoaDonRepository;
import com.example.web.repository.INhanVienRepository;
import com.example.web.service.ILichSuHoaDonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class LichSuHoaDonServiceImpl implements ILichSuHoaDonService {

    @Autowired
    private ILichSuHoaDonRepository iLichSuHoaDonRepository;

    @Autowired
    private INhanVienRepository nhanVienRepository;

    @Autowired
    private IKhachHangRepository khachHangRepository;

    @Autowired
    private IHoaDonRepository hoaDonRepository;

    @Override
    public List<LichSuHoaDon> getAll() {
        return iLichSuHoaDonRepository.findAll();
    }

    @Override
    public List<LichSuHoaDon> getListById(UUID id) {
        return iLichSuHoaDonRepository.get(id);
    }

    @Override
    public void add(Integer thaoTac, UUID idHD, String ghiChu) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        NhanVien nhanVien = nhanVienRepository.findByEmailOrTaiKhoan(authentication.getName());
        Optional<HoaDon> hoaDon = hoaDonRepository.findById(idHD);
        Date date = java.util.Calendar.getInstance().getTime();
        LichSuHoaDon lshd = LichSuHoaDon.builder()
                .hoaDon(hoaDon.get())
                .thaoTac(thaoTac)
                .ngayThaoTac(date)
                .ghiChu(ghiChu)
                .build();
        if(hoaDon.get().getLoaiHoaDon() == LoaiHoaDon.ONLINE){
            KhachHang khachHang = khachHangRepository.findByEmailOrAndTaiKhoan(authentication.getName());
            lshd.setNguoiThaoTac(khachHang.getHoTen());
        }
        if (nhanVien != null) {
            lshd.setNguoiThaoTac(nhanVien.getHoTen() + " - " + nhanVien.getTaiKhoan());
        }
        iLichSuHoaDonRepository.save(lshd);
    }
}
