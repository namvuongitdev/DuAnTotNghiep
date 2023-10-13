package com.example.web.service.impl;

import com.example.web.repository.IGioHangOnllineRepository;
import com.example.web.response.GioHangOnllineResponse;
import com.example.web.service.IGioHangOnllineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.List;
import java.util.UUID;

@Service
public class GioHangOnllineServiceImpl implements IGioHangOnllineService {
    @Autowired
    IGioHangOnllineRepository iGioHangOnllineRepository;

    @Override
    public Page<GioHangOnllineResponse> findAll(Pageable pageable, UUID idKhachHang) {
        return iGioHangOnllineRepository.findAll(pageable,idKhachHang);
    }

    @Override
    public Page<GioHangOnllineResponse> page(Integer pageNo, Integer size, UUID idKhachHang) {
        Pageable pageable = PageRequest.of(pageNo, size);
        return iGioHangOnllineRepository.findAll(pageable,idKhachHang);
    }

    @Override
    public void updateSoLuong(Integer soLuong, UUID idGioHangCT) {
        iGioHangOnllineRepository.updateSoLuong(soLuong,idGioHangCT);
    }

    @Override
    public void delete(UUID idGioHangCT) {
        iGioHangOnllineRepository.delete(idGioHangCT);
    }

    @Override
    public String getTongTienTrongGio(UUID idKhachHang) {
        DecimalFormat formatter = new DecimalFormat("###,###,###");
        List<GioHangOnllineResponse> list = iGioHangOnllineRepository.findAll(idKhachHang);
        BigDecimal tong = BigDecimal.valueOf(0);
        for(int i=0;i<list.size();i++){
            tong =tong.add( list.get(i).getDonGia().multiply(BigDecimal.valueOf(list.get(i).getSoLuong())));
        }
        String gia = formatter.format(tong);
        return gia;
    }
}
