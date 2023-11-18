package com.example.web.service.impl;

import com.example.web.model.HoaDon;
import com.example.web.model.LichSuHoaDon;
import com.example.web.repository.ILichSuHoaDonRepository;
import com.example.web.service.ILichSuHoaDonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.UUID;
@Service
public class LichSuHoaDonServiceImpl implements ILichSuHoaDonService {
    @Autowired
    private ILichSuHoaDonRepository iLichSuHoaDonRepository;
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
    public void add(String nguoiThaoTac, String thaoTac, HoaDon hoaDon, String chucVu) {
        Date date = java.util.Calendar.getInstance().getTime();
        LichSuHoaDon lshd;
        if (chucVu==null){
            lshd= LichSuHoaDon.builder()
                    .hoaDon(hoaDon)
                    .nguoiThaoTac("Khách hàng: "+nguoiThaoTac)
                    .thaoTac(thaoTac)
                    .ngayThaoTac(date)
                    .build();
        }else {
            lshd = LichSuHoaDon.builder()
                    .hoaDon(hoaDon)
                    .nguoiThaoTac(nguoiThaoTac+" ("+chucVu+")")
                    .thaoTac(thaoTac)
                    .ngayThaoTac(date)
                    .build();
        }
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
