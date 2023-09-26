package com.example.web.service.impl;
import com.example.web.model.ChiTietSanPham;
import com.example.web.model.HoaDon;
import com.example.web.model.LichSuHoaDon;
import com.example.web.repository.ILichSuHoaDonRepository;
import com.example.web.response.ChiTietSanPhamResponse;
import com.example.web.service.ILichSuHoaDonService;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.io.IOException;
import java.util.Date;

@Service
public class LichSuHoaDonServiceImpl implements ILichSuHoaDonService {

    @Autowired
    private ILichSuHoaDonRepository lichSuHoaDonRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public void create(HoaDon hoaDon, ChiTietSanPham ctsp, Integer trangThai) {
        Date date = java.util.Calendar.getInstance().getTime();
        ObjectMapper mapper = new ObjectMapper();
        ChiTietSanPhamResponse response = modelMapper.map(ctsp , ChiTietSanPhamResponse.class);
        try {
            String ghiChu = mapper.writeValueAsString(response);
            LichSuHoaDon lichSuHoaDon = LichSuHoaDon.builder()
                    .trangThai(trangThai)
                    .thoi_gian(date)
                    .ghiChu(ghiChu)
                    .hoaDon(hoaDon)
                    .build();

            System.out.println("ctsp :" + ghiChu);

            lichSuHoaDonRepository.save(lichSuHoaDon);
        } catch (JsonGenerationException e) {
            e.printStackTrace();
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
