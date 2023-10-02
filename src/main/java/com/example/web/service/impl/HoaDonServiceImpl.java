package com.example.web.service.impl;

import com.example.web.model.HoaDon;
import com.example.web.model.TrangThaiHoaDon;
import com.example.web.repository.IHoaDonRepository;
import com.example.web.repository.IKhachHangRepository;
<<<<<<< Updated upstream
import com.example.web.response.HoaDonReponse;
import com.example.web.service.IHoaDonService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
=======
import com.example.web.request.HoaDonRequest;
import com.example.web.response.HoaDonFilter;
import com.example.web.response.HoaDonReponse;
import com.example.web.service.IHoaDonService;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.SneakyThrows;
>>>>>>> Stashed changes
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
<<<<<<< Updated upstream
=======
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
>>>>>>> Stashed changes

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
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

<<<<<<< Updated upstream
=======
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
//                hd.setPhuongThucThanhToan(request.getHinhThucThanhToan());
                hoaDonRepository.save(hd);
                return "redirect:/hoa-don/hien-thi-hoa-cho";
            } else {
                return null;
            }
        }
    }

    @Override
    public List<HoaDon> getAll() {
        return hoaDonRepository.findAll();
    }

    @Override
    public HoaDon getOne(String id) {
        return hoaDonRepository.getReferenceById(UUID.fromString(id));
    }

    @Override
    public HoaDonChiTiet getHoaDonChiTiet(UUID id) {
        return hoaDonRepository.getHoaDonChiTiet(id);
    }

    @Override
    public Page<HoaDon> pagination(Integer pageNo, Integer size) {
        Pageable pageable = PageRequest.of(pageNo, size);
        return hoaDonRepository.findAll(pageable);
    }

    @Override
    public Page<HoaDon> hoaDonFillter(HoaDonFilter filter, Pageable pageable) {
        return hoaDonRepository.findAll(new Specification<HoaDon>(){
            @SneakyThrows
            @Override
            public Predicate toPredicate(Root<HoaDon> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                List<Predicate> predicates = new ArrayList<>();
                if (!filter.getSearch().isBlank()){
                    predicates.add(criteriaBuilder.and(criteriaBuilder.equal(root.get("ma") , filter.getSearch()),
                            criteriaBuilder.equal(root.get("sdt") , filter.getSearch()),
                            criteriaBuilder.equal(root.get("hoTen") , filter.getSearch())));
                }
                if (!filter.getTrangThai().isBlank()){
                    predicates.add(criteriaBuilder.and(criteriaBuilder.equal(root.get("trangThai") , filter.getTrangThai())));
                }
                if (filter.getDateBegin()!=null && filter.getDateEnd()!=null){
                    System.out.println("ngày"+filter.getDateBegin());
//                    SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy-dd-MM HH:mm:ss");
                    predicates.add(criteriaBuilder.and(criteriaBuilder.between(root.get("ngayTao") , filter.getDateBegin(),filter.getDateEnd())));
                }
                return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
            }
        },pageable);
    }


>>>>>>> Stashed changes
}
