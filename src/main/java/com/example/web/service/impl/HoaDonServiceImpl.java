package com.example.web.service.impl;
import com.example.web.model.HoaDon;
import com.example.web.model.HoaDonChiTiet;
import com.example.web.model.KhachHang;
import com.example.web.model.NhanVien;
import com.example.web.model.TrangThaiHoaDon;
import com.example.web.repository.IHoaDonRepository;
import com.example.web.repository.IKhachHangRepository;
import com.example.web.repository.INhanVienRepository;
import com.example.web.request.HoaDonRequest;
import com.example.web.response.HoaDonReponse;
import com.example.web.service.IHoaDonService;
import com.example.web.response.HoaDonFilter;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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

    @Autowired
    private INhanVienRepository nhanVienRepository;

    @Override
    public String addHoaDon() {

        Date date = java.util.Calendar.getInstance().getTime();
        HoaDon hoaDon = HoaDon.builder()
                .trangThai(TrangThaiHoaDon.HOA_DON_CHO.getValue())
                .ngayTao(date)
                .ma("HD" + (hoaDonRepository.findAll().size() + 1))
                .loaiHoaDon(false)
                .build();

        hoaDon = hoaDonRepository.save(hoaDon);

        return "redirect:/admin/hoa-don/detail?idHD=" + hoaDon.getId();
    }

    @Override
    public Page<Object[]> findByHoaDonCho(Integer trangThai, Pageable pageable) {
        return hoaDonRepository.findAllByHoaDonCho(trangThai, pageable);
    }

    @Override
    public String getHoaDonById(Model model, String id) {
        Optional<HoaDon> hoaDon = hoaDonRepository.findById(UUID.fromString(id));
        if (hoaDon.isPresent()) {
            List<HoaDonReponse> sanPhams = hoaDonRepository.getSanPhamHD(UUID.fromString(id), 0);
            model.addAttribute("sanPhamGioHang", sanPhams);
            BigDecimal tongTien = hoaDonRepository.tongTien(hoaDon.get().getId());
            model.addAttribute("tongTien", tongTien);
            model.addAttribute("hoaDon", hoaDon.get());

        }
        return "banHangTaiQuay/gio-hang";
    }

    @Override
    public String updateHoaDonTrangThai(String id) {
        Optional<HoaDon> hoaDon = hoaDonRepository.findById(UUID.fromString(id));
        if (hoaDon.isPresent()) {

            HoaDon hd = hoaDon.get();
            hd.setTrangThai(TrangThaiHoaDon.HUY_HOA_DON.getValue());

            hd.getHoaDonChiTiets().stream().filter(o -> o.getTrangThai() == 0).forEach(hoaDonChiTiet -> {
                Integer soLuong = hoaDonChiTiet.getSoLuong() + hoaDonChiTiet.getChiTietSanPham().getSoLuong();
                hoaDonChiTiet.getChiTietSanPham().setSoLuong(soLuong);
                hoaDonChiTiet.setTrangThai(1);
                hoaDonRepository.save(hd);
            });
            return "redirect:/admin/hoa-don/hien-thi-hoa-cho";

        } else {
            return null;
        }
    }

    @Override
    public String thanhToan(HoaDonRequest request, RedirectAttributes attributes) {
        Optional<HoaDon> hoaDon = hoaDonRepository.findById(UUID.fromString(request.getId()));
        BigDecimal tongTienHoaDon = hoaDonRepository.tongTien(hoaDon.get().getId());
        List<HoaDonChiTiet> ctsp = hoaDon.get().getHoaDonChiTiets().stream().filter(o -> o.getTrangThai() != 1).collect(Collectors.toList());
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        NhanVien nhanVien = nhanVienRepository.findByEmailOrTaiKhoan(authentication.getName());
        if (ctsp.isEmpty()) {
            attributes.addFlashAttribute("error", "giỏ hàng chưa có sản phẩm");
            return "redirect:/admin/hoa-don/detail?idHD=" + request.getId() + "&idKhachHang=" + request.getIdKhachHang();
        } else {
            if (hoaDon.isPresent()) {
                HoaDon hd = hoaDon.get();
                hd.setNhanVien(nhanVien);
                if (hd.getLoaiHoaDon()) {
                        Double tongTienDonDatHang = tongTienHoaDon.doubleValue() + request.getPhiVanChuyen().doubleValue();
                        hd.setTrangThai(TrangThaiHoaDon.Cho_xac_nhan.getValue());
                        hd.setPhiVanChuyen(request.getPhiVanChuyen());
                        hd.setTongTien(BigDecimal.valueOf(tongTienDonDatHang));
                        hd.setDiaChi(request.getDiaChi());
                        hd.setHoTen(request.getHoTen());
                        hd.setMoTa(request.getMoTa());
                        hd.setSdt(request.getSdt());
                } else {
                    if (request.getSoTienThanhToan().isEmpty() || request.getSoTienThanhToan() == null) {
                        attributes.addFlashAttribute("error", "chưa nhập tiền khách đưa");
                        return "redirect:/admin/hoa-don/detail?idHD=" + request.getId() + "&idKhachHang=" + request.getIdKhachHang();
                    } else if (tongTienHoaDon.doubleValue() > Double.parseDouble(request.getSoTienThanhToan())) {
                        attributes.addFlashAttribute("error", "số tiền khách đưa chưa đủ");
                        attributes.addFlashAttribute("soTienKhachTra" , request.getSoTienThanhToan());
                        return "redirect:/admin/hoa-don/detail?idHD=" + hd.getId() + "&idKhachHang=" + request.getIdKhachHang();
                    } else {
                        Date date = java.util.Calendar.getInstance().getTime();
                        hd.setMoTa(request.getMoTa());
                        hd.setTrangThai(TrangThaiHoaDon.DA_HOAN_THANH.getValue());
                        hd.setTongTien(tongTienHoaDon);
                        hd.setNgayThanhToan(date);
                        hd.setPhuongThucThanhToan(request.getHinhThucThanhToan());
                    }
                }
                if (request.getIdKhachHang() != null && !request.getIdKhachHang().isEmpty()) {
                    Optional<KhachHang> khachHang = khachHangRepository.findById(UUID.fromString(request.getIdKhachHang()));
                    hd.setKhachHang(khachHang.get());
                }
                hoaDonRepository.save(hd);
                return "redirect:/admin/hoa-don/hien-thi-hoa-cho";
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
    public List<HoaDon> getHoaDon() {
        return null;
    }

    @Override
    public HoaDon getOne(String id) {
        return hoaDonRepository.getReferenceById(UUID.fromString(id));
    }

    @Override
    public Page<HoaDonChiTiet> getHoaDonChiTiet(UUID id, Integer pageNo, Integer size) {
        Pageable pageable = PageRequest.of(pageNo, size);
        return hoaDonRepository.getHoaDonChiTiet(id, pageable);
    }

    @Override
    public Page<HoaDon> pagination(Integer pageNo, Integer size) {
        Pageable pageable = PageRequest.of(pageNo, size);
        return hoaDonRepository.findAll3(pageable);
    }

    @Override
    public Page<HoaDon> hoaDonFillter(HoaDonFilter filter, Pageable pageable) {
        return hoaDonRepository.findAll(new Specification<HoaDon>() {
            @SneakyThrows
            @Override
            public Predicate toPredicate(Root<HoaDon> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                List<Predicate> predicates = new ArrayList<>();
                if (!filter.getSearch().isBlank()) {
                    predicates.add(criteriaBuilder.or(criteriaBuilder.equal(root.get("ma"), filter.getSearch()),
                            criteriaBuilder.equal(root.get("sdt"), filter.getSearch()),
                            criteriaBuilder.equal(root.get("hoTen"), filter.getSearch())));
                }
                if (!filter.getTrangThai().isBlank()) {
                    predicates.add(criteriaBuilder.and(criteriaBuilder.equal(root.get("trangThai"), filter.getTrangThai())));
                }
                if (!filter.getDateBegin().isBlank() && !filter.getDateEnd().isBlank()) {
                    System.out.println("ngày" + filter.getDateBegin());
                    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                    Date date1 = dateFormat.parse(filter.getDateBegin());
                    Date date2 = dateFormat.parse(filter.getDateEnd());
                    predicates.add(criteriaBuilder.and(criteriaBuilder.between(root.get("ngayTao"), date1, date2)));
                }
                return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
            }
        }, pageable);
    }

    @Override
    public HoaDon add(HoaDon hoaDon) {
        return hoaDonRepository.save(hoaDon);
    }

    @Override
    public HoaDon updateHoaDonById(HoaDon hoaDon) {
        Optional<HoaDon> hd =hoaDonRepository.findById(hoaDon.getId());
        hd.get().setHoTen(hoaDon.getHoTen());
        hd.get().setDiaChi(hoaDon.getDiaChi());
        hd.get().setSdt(hoaDon.getSdt());
        hd.get().setTongTien(hoaDon.getTongTien());
        return hoaDonRepository.save(hd.get());
    }
}
