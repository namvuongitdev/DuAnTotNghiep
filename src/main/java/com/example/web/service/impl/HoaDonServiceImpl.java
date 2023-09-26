package com.example.web.service.impl;

import com.example.web.model.HoaDon;
import com.example.web.model.TrangThaiHoaDon;
import com.example.web.repository.IHoaDonRepository;
import com.example.web.repository.IKhachHangRepository;
import com.example.web.response.HoaDonReponse;
import com.example.web.service.IHoaDonService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.UUID;

@Service
public class HoaDonServiceImpl implements IHoaDonService {

    @Autowired
    private IHoaDonRepository hoaDonRepository;

    @Autowired
    private IKhachHangRepository khachHangRepository;


    @Override
    public String addHoaDon() {

        Date date = java.util.Calendar.getInstance().getTime();

        Random random = new Random();
        HoaDon hoaDon = HoaDon.builder()
                .trangThai(TrangThaiHoaDon.CHO_XAC_NHAN.getValue())
                .ngayTao(date)
                .hoTen("khách bán lẻ")
                .ma("HD" + random.nextInt())
                .build();

        hoaDon = hoaDonRepository.save(hoaDon);

        return "redirect:/hoa-don/detail?idHD=" + hoaDon.getId();
    }

    @Override
    public Page<Object[]> getAllByTrangThai(Integer trangThai, Pageable pageable) {
        Page<Object[]> listHoaDonCho = hoaDonRepository.findAllByTrangThai_hoaDon(trangThai, 0, pageable);
        return listHoaDonCho;
    }

    @Override
    public String getHoaDonById(Model model, String id) {
        Optional<HoaDon> hoaDon = hoaDonRepository.findById(UUID.fromString(id));
        if (hoaDon.isPresent()) {
            List<HoaDonReponse> sanPhams = hoaDonRepository.getSanPhamHD(UUID.fromString(id), 0);
            model.addAttribute("khachHangs", khachHangRepository.findAll());
            model.addAttribute("sanPhams", sanPhams);

//       sanPhams.stream().reduce(0 , (a , b) ->  a. * b.getSoLuong() , (integer, integer2) -> );

//            model.addAttribute("tongTien", tongTien);
            model.addAttribute("hoaDon", hoaDon.get());

        }
        return "banHangTaiQuay/gio-hang";
    }

    @Override
    public String updateHoaDonTrangThai(String id, String ghiChu) {
        Optional<HoaDon> hoaDon = hoaDonRepository.findById(UUID.fromString(id));
        if (hoaDon.isPresent()) {

            HoaDon hd = hoaDon.get();
            hd.setTrangThai(TrangThaiHoaDon.HUY_HOA_DON.getValue());
            hd.setMoTa(ghiChu);

            hd.getHoaDonChiTiets().stream().filter(o -> o.getTrangThai() == 0).forEach(hoaDonChiTiet -> {
                Integer soLuong = hoaDonChiTiet.getSoLuong() + hoaDonChiTiet.getChiTietSanPham().getSoLuong();
                hoaDonChiTiet.getChiTietSanPham().setSoLuong(soLuong);
                hoaDonChiTiet.setTrangThai(1);
                hoaDonRepository.save(hd);
            });
            return "redirect:/hoa-don/hien-thi-hoa-cho";

        } else {
            return null;
        }
    }

}
