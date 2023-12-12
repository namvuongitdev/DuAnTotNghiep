package com.example.web.service.impl;

import com.example.web.Config.status.HoaDonChiTietStatus;
import com.example.web.Config.status.HoaDonStatus;
import com.example.web.Config.status.LoaiHoaDon;
import com.example.web.Config.status.PhuongThucThanhToanStatus;
import com.example.web.model.HoaDon;
import com.example.web.model.HoaDonChiTiet;
import com.example.web.model.KhachHang;
import com.example.web.model.NhanVien;
import com.example.web.model.TrangThaiHoaDon;
import com.example.web.repository.IHoaDonRepository;
import com.example.web.repository.IKhachHangRepository;
import com.example.web.repository.INhanVienRepository;
import com.example.web.request.HoaDonRequest;
import com.example.web.request.ThongTinKhachHang;
import com.example.web.response.HoaDonChiTietReponse;
import com.example.web.response.HoaDonReponse;
import com.example.web.service.IHoaDonService;
import com.example.web.response.HoaDonFilter;
import com.example.web.service.ILichSuHoaDonService;
import com.example.web.service.InHoaDonService;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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

    @Autowired
    private ILichSuHoaDonService lichSuHoaDonService;

    @Autowired
    private InHoaDonService inHoaDonService;

    @Override
    public String addHoaDon() {
        Date date = java.util.Calendar.getInstance().getTime();
        HoaDon hoaDon = HoaDon.builder()
                .trangThai(TrangThaiHoaDon.HOA_DON_CHO.getValue())
                .ngayTao(date)
                .ma("HD" + (hoaDonRepository.findAll().size() + 1))
                .loaiHoaDon(LoaiHoaDon.TAI_QUAY)
                .build();
        hoaDon = hoaDonRepository.save(hoaDon);
        return "redirect:/admin/hoa-don/detail?idHD=" + hoaDon.getId();
    }

    @Override
    public List<Object[]> findByHoaDonCho(Integer trangThai) {
        return hoaDonRepository.findAllByHoaDonCho(trangThai);
    }

    @Override
    public String getHoaDonById(Model model, String id, RedirectAttributes attributes) {
        Optional<HoaDon> hoaDon = hoaDonRepository.findById(UUID.fromString(id));
        if (hoaDon.isPresent()) {
            if (hoaDon.get().getTrangThai() != HoaDonStatus.HOA_DON_CHO) {
                attributes.addFlashAttribute("error", "hoá đơn không phải là hoá đơn chờ");
                return "redirect:/admin/hoa-don/hien-thi-hoa-cho";
            } else {
                List<HoaDonReponse> sanPhams = hoaDonRepository.getSanPhamHD(UUID.fromString(id), HoaDonChiTietStatus.KICH_HOAT);
                model.addAttribute("sanPhamGioHang", sanPhams);
                BigDecimal tongTien = hoaDonRepository.tongTien(hoaDon.get().getId());
                model.addAttribute("tongTien", tongTien);
                model.addAttribute("hoaDon", hoaDon.get());
            }
        }
        return "banHangTaiQuay/gio-hang";
    }

    @Override
    public String updateHoaDonTrangThai(String id) {
        Optional<HoaDon> hoaDon = hoaDonRepository.findById(UUID.fromString(id));
        if (hoaDon.isPresent()) {
            HoaDon hd = hoaDon.get();
            hd.setTrangThai(HoaDonStatus.HUY_HOA_DON_CHO);
            List<HoaDonChiTiet> listHDCT = hd.getHoaDonChiTiets().stream().filter(o -> o.getTrangThai() == HoaDonChiTietStatus.KICH_HOAT).collect(Collectors.toList());
            if (hd.getHoaDonChiTiets().isEmpty()) {
                hoaDonRepository.save(hd);
            } else if (listHDCT.isEmpty()) {
                hoaDonRepository.save(hd);
            } else {
                listHDCT.forEach(hoaDonChiTiet -> {
                    Integer soLuong = hoaDonChiTiet.getSoLuong() + hoaDonChiTiet.getChiTietSanPham().getSoLuong();
                    hoaDonChiTiet.getChiTietSanPham().setSoLuong(soLuong);
                    hoaDonChiTiet.setTrangThai(HoaDonChiTietStatus.XOA);
                    hoaDonRepository.save(hd);
                });
            }
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
            // kiểm tra hoá đơn có tồn tại
            if (hoaDon.isPresent()) {
                HoaDon hd = hoaDon.get();
                hd.setNhanVien(nhanVien);
                // nếu hoá đơn là giao hàng
                if (hd.getLoaiHoaDon() == LoaiHoaDon.GIAO_HANG) {
                    hd.setTrangThai(HoaDonStatus.DA_TIEP_NHAN);
                    hd.setPhiVanChuyen(request.getPhiVanChuyen());
                    hd.setDiaChi(request.getDiaChi());
                    hd.setHoTen(request.getHoTen());
                    hd.setMoTa(request.getMoTa());
                    hd.setSdt(request.getSdt());
                    hd.setPhuongThucThanhToan(PhuongThucThanhToanStatus.THANH_TOAN_KHI_NHAN_HANG);
                } else {
                    // kiểm tra tiền khách đữa
                    if (request.getSoTienThanhToan().isEmpty() || request.getSoTienThanhToan() == null) {
                        attributes.addFlashAttribute("error", "chưa nhập tiền khách đưa");
                        return "redirect:/admin/hoa-don/detail?idHD=" + request.getId() + "&idKhachHang=" + request.getIdKhachHang();
                    } else if (tongTienHoaDon.doubleValue() > Double.parseDouble(request.getSoTienThanhToan())) {
                        attributes.addFlashAttribute("error", "số tiền khách đưa chưa đủ");
                        attributes.addFlashAttribute("soTienKhachTra", request.getSoTienThanhToan());
                        return "redirect:/admin/hoa-don/detail?idHD=" + hd.getId() + "&idKhachHang=" + request.getIdKhachHang();
                    } else {
                        // nếu hoá đơn là tại quầy
                        Date date = java.util.Calendar.getInstance().getTime();
                        hd.setMoTa(request.getMoTa());
                        hd.setTrangThai(TrangThaiHoaDon.DA_HOAN_THANH.getValue());
                        hd.setTongTien(tongTienHoaDon);
                        hd.setNgayNhanHang(date);
                        if (request.getHinhThucThanhToan() == PhuongThucThanhToanStatus.CHUYEN_KHOAN) {
                            if(request.getMaGiaoDich().isEmpty() || request.getMaGiaoDich() == null){
                                attributes.addFlashAttribute("error", "chưa nhập mã giao dịch");
                                return "redirect:/admin/hoa-don/detail?idHD=" + hd.getId() + "&idKhachHang=" + request.getIdKhachHang();
                            }
                            hd.setMaGiaoDich(request.getMaGiaoDich());
                        }
                        hd.setPhuongThucThanhToan(request.getHinhThucThanhToan());
                    }
                }
                // kiểm tra nếu có khách hàng
                if (request.getIdKhachHang() != null && !request.getIdKhachHang().isEmpty()) {
                    Optional<KhachHang> khachHang = khachHangRepository.findById(UUID.fromString(request.getIdKhachHang()));
                    hd.setKhachHang(khachHang.get());
                }
                HoaDon response = hoaDonRepository.save(hd);
                // lịch sử
                lichSuHoaDonService.add(response.getLoaiHoaDon() == LoaiHoaDon.GIAO_HANG ? HoaDonStatus.DA_TIEP_NHAN : HoaDonStatus.DA_THANH_TOAN , response.getId(), "nhân viên tạo hoá đơn cho khách");
                attributes.addFlashAttribute("success", "Hoá đơn " + response.getMa() + " tạo thành công");
                // in hoá đơn ra file pdf
                inHoaDonService.generatePdf(response.getId());
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
    public HoaDon getOne(String id) {
        return hoaDonRepository.getReferenceById(UUID.fromString(id));
    }

    @Override
    public HoaDon updateThoiGianTraHang(String idHD) {
        HoaDon hoaDon = hoaDonRepository.ngayHetHanTraHang(idHD);
        return hoaDon;
    }

    @Override
    public List<HoaDonChiTietReponse> getHoaDonChiTiets(UUID id) {
        List<HoaDonChiTietReponse> response = hoaDonRepository.findAllHoaDonChiTietByHoaDon_id(id);
        return response;
    }

    @Override
    public Boolean kiemTraConTrongHDCT(UUID idHD) {
        List<HoaDonChiTietReponse> isCheck = hoaDonRepository.isCheckSanPhamTrongHoaDon(idHD);
        if (isCheck.isEmpty()) {
            return true;
        }
        return false;
    }

    @Override
    public HoaDonChiTietReponse getHoaDonChiTiet(UUID idHDCT) {
        HoaDonChiTietReponse response = hoaDonRepository.findHoaDonChiTietByHoaDon_id(idHDCT);
        return response;
    }

    @Override
    public Page<HoaDon> getAllHoaDonByTrangThaiKhachHoaDonCho(Integer page) {
        Pageable pageable = PageRequest.of(page - 1, 10, Sort.by("ngayTao").descending());
        return hoaDonRepository.findAllHoaDonByTrangThaiKhacHoaDonCho(pageable);
    }

    @Override
    public Page<HoaDon> hoaDonFillter(HoaDonFilter filter, Pageable pageable) {
        return hoaDonRepository.findAll(new Specification<HoaDon>() {
            @SneakyThrows
            @Override
            public Predicate toPredicate(Root<HoaDon> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                List<Predicate> predicates = new ArrayList<>();
                if (!filter.getSearch().isBlank()) {
                    predicates.add(criteriaBuilder.or(criteriaBuilder.like(root.get("ma"), "%" + filter.getSearch() + "%"),
                            criteriaBuilder.like(root.get("sdt"), "%" + filter.getSearch() + "%"),
                            criteriaBuilder.like(root.get("hoTen"), "%" + filter.getSearch() + "%")));
                }
                if (!filter.getDateBegin().isBlank() && !filter.getDateEnd().isBlank()) {
                    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                    Date ngayBatDau = dateFormat.parse(filter.getDateBegin());
                    Date ngayKetThuc = dateFormat.parse(filter.getDateEnd());
                    predicates.add(criteriaBuilder.and(criteriaBuilder.between(root.get("ngayTao"), ngayBatDau, ngayKetThuc)));
                }
                if (filter.getLoaiHoaDon() != null) {
                    predicates.add(criteriaBuilder.and(criteriaBuilder.equal(root.get("loaiHoaDon"),filter.getLoaiHoaDon())));
                }
                if (filter.getTrangThai() != null && filter.getTrangThai() != 0) {
                    predicates.add(criteriaBuilder.and(criteriaBuilder.equal(root.get("trangThai"), filter.getTrangThai())));
                }
                predicates.add(criteriaBuilder.and(criteriaBuilder.notEqual(root.get("trangThai"), HoaDonStatus.HOA_DON_CHO), criteriaBuilder.notEqual(root.get("trangThai"), HoaDonStatus.HUY_HOA_DON_CHO)));
                return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
            }
        }, pageable);
    }

    @Override
    public HoaDon add(HoaDon hoaDon) {
        return hoaDonRepository.save(hoaDon);
    }

    @Override
    public HoaDon updateThongTinKhachHang(UUID idHD, ThongTinKhachHang thongTinKhachHang) {
        Optional<HoaDon> hoaDon = hoaDonRepository.findById(idHD);
        if (hoaDon.isPresent()) {
            HoaDon hd = hoaDon.get();
            hd.setSdt(thongTinKhachHang.getSdt());
            hd.setDiaChi(thongTinKhachHang.getDiaChi());
            if (thongTinKhachHang.getPhiVanChuyen() != null) {
                hd.setPhiVanChuyen(thongTinKhachHang.getPhiVanChuyen());
            }
            hd.setHoTen(thongTinKhachHang.getHoTen());
            return hoaDonRepository.save(hd);
        }
        return null;
    }

    @Override
    public Page<Object[]> findHoaDonByTaiKhoan(String taiKhoan, Pageable pageable) {
        return hoaDonRepository.findHoaDonByTaiKhoan(taiKhoan, pageable);
    }

    @Override
    public Page<Object[]> findHoaDonByTrangThai(String taiKhoan, Integer trangThai, Pageable pageable) {
        return hoaDonRepository.findHoaDonByTrangThai(taiKhoan, trangThai, pageable);
    }


    @Override
    public HoaDon getHoaDonByKhachHang_idAndHoaDon_id(UUID idKH, UUID idHD) {
        return hoaDonRepository.findHoaDonByKhachHang(idKH, idHD);
    }

    @Override
    public Integer tongHoaDonHuy() {
        return hoaDonRepository.tongHoaDonHuy();
    }

    @Override
    public Integer tongHoaDonChoXacNhan() {
        return hoaDonRepository.tongHoaDonChoXacNhan();
    }

    @Override
    public Integer xacNhanHoaDon(Integer trangThai, UUID idHD, String ghiChu) {
        Optional<HoaDon> hoaDon = hoaDonRepository.findById(idHD);
        if (hoaDon.isPresent()) {
            HoaDon hd = hoaDon.get();
            if (trangThai == HoaDonStatus.DA_TIEP_NHAN) {
                if (hd.getPhiVanChuyen() == null) {
                    return 2;
                }
            }
            if (trangThai == HoaDonStatus.GIAO_HANG) {
                BigDecimal tongTien = hoaDonRepository.tongTien(idHD);
                hd.setTongTien(BigDecimal.valueOf(tongTien.intValue() + hd.getPhiVanChuyen().intValue()));
            }
            if (trangThai == HoaDonStatus.HUY) {
                hd.getHoaDonChiTiets().stream().filter(o -> o.getTrangThai() == HoaDonChiTietStatus.KICH_HOAT).forEach(hoaDonChiTiet -> {
                    Integer soLuong = hoaDonChiTiet.getSoLuong() + hoaDonChiTiet.getChiTietSanPham().getSoLuong();
                    hoaDonChiTiet.getChiTietSanPham().setSoLuong(soLuong);
                });
            }
            if (trangThai == HoaDonStatus.DA_THANH_TOAN) {
                hd.setNgayNhanHang(java.util.Calendar.getInstance().getTime());
            }
            hd.setTrangThai(trangThai);
            lichSuHoaDonService.add(trangThai, hd.getId(), ghiChu);
            return 1;

        } else {
            return -1;
        }
    }

    @Override
    public Integer xacNhanDonHangCuaToi(Integer trangThai, UUID idHD, String ghiChu) {
        Optional<HoaDon> hoaDon = hoaDonRepository.findById(idHD);
        if (hoaDon.isPresent()) {
            HoaDon hd = hoaDon.get();
            if (trangThai == HoaDonStatus.DA_TIEP_NHAN) {
                if (hd.getPhiVanChuyen() == null) {
                    return 2;
                }
            }
            if (trangThai == HoaDonStatus.GIAO_HANG) {
                BigDecimal tongTien = hoaDonRepository.tongTien(idHD);
                hd.setTongTien(BigDecimal.valueOf(tongTien.intValue() + hd.getPhiVanChuyen().intValue()));
            }
            if (trangThai == HoaDonStatus.HUY) {
                hd.getHoaDonChiTiets().stream().filter(o -> o.getTrangThai() == HoaDonChiTietStatus.KICH_HOAT).forEach(hoaDonChiTiet -> {
                    Integer soLuong = hoaDonChiTiet.getSoLuong() + hoaDonChiTiet.getChiTietSanPham().getSoLuong();
                    hoaDonChiTiet.getChiTietSanPham().setSoLuong(soLuong);
                });
                hd.setTrangThai(trangThai);
                lichSuHoaDonService.add(trangThai, hd.getId(), ghiChu);
                return 3;
            }
            if (trangThai == HoaDonStatus.DA_THANH_TOAN) {
                hd.setNgayNhanHang(java.util.Calendar.getInstance().getTime());
            }
            hd.setTrangThai(trangThai);
            lichSuHoaDonService.add(trangThai, hd.getId(), ghiChu);
            return 1;

        } else {
            return -1;
        }
    }

    @Override
    public Integer tongDoanhThu() {
        return hoaDonRepository.tongDoanhThu();
    }

    @Override
    public Page<Object[]> getAllHoaDonHoanTien(Integer page) {
        Pageable pageable = PageRequest.of(page - 1, 10);
        return hoaDonRepository.findHoaDonHoanTien(pageable);
    }

    @Override
    public Double getDoanhThuTrongNgay() {
        return hoaDonRepository.getDoanhThuTrongNgay();
    }

    @Override
    public Integer tongHoaDon() {
        return hoaDonRepository.tongHoaDon();
    }

    @Override
    public Double getDoanhThuTheoThang(Integer thang ,Integer nam) {
        return hoaDonRepository.getDoanhThuTheoThang(thang , nam);
    }

    @Override
    public List<Integer> getNams() {
        return hoaDonRepository.getNam();
    }
}
