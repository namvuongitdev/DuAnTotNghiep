package com.example.web.service.impl;

import com.example.web.Config.status.HoaDonStatus;
import com.example.web.model.HoaDon;
import com.example.web.model.LichSuHoaDon;
import com.example.web.model.NhanVien;
import com.example.web.repository.ILichSuHoaDonRepository;
import com.example.web.repository.INhanVienRepository;
import com.example.web.service.ILichSuHoaDonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class LichSuHoaDonServiceImpl implements ILichSuHoaDonService {

    @Autowired
    private ILichSuHoaDonRepository iLichSuHoaDonRepository;

    @Autowired
    private INhanVienRepository nhanVienRepository;

    @Override
    public List<LichSuHoaDon> getAll() {
        return iLichSuHoaDonRepository.findAll();
    }

    @Override
    public List<LichSuHoaDon> getListById(UUID id) {
        return iLichSuHoaDonRepository.get(id);
    }

    @Override
    public LichSuHoaDon getOne(String idHd, String Thaotac) {
        return iLichSuHoaDonRepository.getOne(UUID.fromString(idHd),Thaotac);
    }

    @Override
    public LichSuHoaDon getOneTao(String idHd,String thaoTac) {
        return iLichSuHoaDonRepository.getOneTao(UUID.fromString(idHd),thaoTac);
    }

    @Override
    public void add(Integer thaoTac, HoaDon hoaDon,String ghiChu) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        NhanVien nhanVien = nhanVienRepository.findByEmailOrTaiKhoan(authentication.getName());
        String thoaTacHoaDon = null;
        if(thaoTac == HoaDonStatus.DA_TIEP_NHAN){
            thoaTacHoaDon = "xác nhận hoá đơn";
        }if(thaoTac == HoaDonStatus.GIAO_HANG){
            thoaTacHoaDon = "xác nhận giao hàng";
        }if(thaoTac == HoaDonStatus.GIAO_HANG_THANH_CONG){
            thoaTacHoaDon = "xác nhận giao hàng thành công";
        }if(thaoTac == HoaDonStatus.DA_THANH_TOAN){
            thoaTacHoaDon = "xác nhận hoàn thành";
        }if(thaoTac == HoaDonStatus.CHINH_SUA){
            thoaTacHoaDon = "chỉnh sửa hoá đơn";
        }if(thaoTac == HoaDonStatus.NHAN_VIEN_TAO_HOA_DON){
            thoaTacHoaDon = "nhân viên tạo hoá đơn cho khách";
        }if(thaoTac == HoaDonStatus.HUY){
            thoaTacHoaDon = "huỷ hoá đơn";
        }
        Date date = java.util.Calendar.getInstance().getTime();
        LichSuHoaDon lshd = LichSuHoaDon.builder()
                    .hoaDon(hoaDon)
                    .nguoiThaoTac(nhanVien.getHoTen())
                    .thaoTac(thoaTacHoaDon)
                    .ngayThaoTac(date)
                    .ghiChu(ghiChu)
                    .build();

        iLichSuHoaDonRepository.save(lshd);
    }

    @Override
    public void update(LichSuHoaDon lshd) {
        iLichSuHoaDonRepository.save(lshd);
    }

    @Override
    public void delete(UUID id) {

    }

}
