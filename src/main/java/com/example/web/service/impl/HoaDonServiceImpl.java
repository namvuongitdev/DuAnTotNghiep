package com.example.web.service.impl;
import com.example.web.model.HoaDon;
import com.example.web.model.HoaDonChiTiet;
import com.example.web.model.TrangThaiHoaDon;
import com.example.web.repository.IHoaDonRepository;
import com.example.web.repository.IKhachHangRepository;
import com.example.web.request.HoaDonRequest;
import com.example.web.response.HoaDonReponse;
import com.example.web.service.IHoaDonService;
import com.example.web.service.ISanPhamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class HoaDonServiceImpl implements IHoaDonService {

    @Autowired
    private IHoaDonRepository hoaDonRepository;

    @Autowired
    private IKhachHangRepository khachHangRepository;

    @Override
    public String addHoaDon() {

        Date date = java.util.Calendar.getInstance().getTime();
        HoaDon hoaDon = HoaDon.builder()
                .trangThai(TrangThaiHoaDon.CHO_XAC_NHAN.getValue())
                .ngayTao(date)
                .hoTen("khách bán lẻ")
                .ma("HD" + (hoaDonRepository.findAll().size() + 1))
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
            model.addAttribute("sanPhamGioHang", sanPhams);
            BigDecimal tongTien = hoaDonRepository.tongTien(hoaDon.get().getId());
            model.addAttribute("tongTien", tongTien);
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

    @Override
    public String thanhToan(HoaDonRequest request, RedirectAttributes attributes) {
        Optional<HoaDon> hoaDon = hoaDonRepository.findById(UUID.fromString(request.getHoaDon()));
        BigDecimal tongTienHoaDon = hoaDonRepository.tongTien(UUID.fromString(request.getHoaDon()));
        List<HoaDonChiTiet> ctsp = hoaDon.get().getHoaDonChiTiets().stream().filter(o -> o.getTrangThai() != 1).collect(Collectors.toList());
        if (ctsp.isEmpty()) {
            return "redirect:/hoa-don/detail?idHD=" + request.getHoaDon();
        } else if (tongTienHoaDon.doubleValue() > request.getSoTienThanhToan().doubleValue()) {
            return "redirect:/hoa-don/detail?idHD=" + request.getHoaDon();
        } else {
            if (hoaDon.isPresent()) {
                Date date = java.util.Calendar.getInstance().getTime();
                HoaDon hd = hoaDon.get();
                hd.setMoTa(request.getMoTa());
                hd.setLoaiHoaDon(true);
                hd.setTrangThai(TrangThaiHoaDon.DA_HOAN_THANH.getValue());
                hd.setTongTien(tongTienHoaDon);
                hd.setNgayThanhToan(date);
                hd.setPhuongThucThanhToan(request.getHinhThucThanhToan());
                hoaDonRepository.save(hd);
                return "redirect:/hoa-don/hien-thi-hoa-cho";
            } else {
                return null;
            }
        }
    }
}
