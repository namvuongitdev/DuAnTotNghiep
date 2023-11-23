package com.example.web.service.impl;

import com.example.web.Config.status.HoaDonChiTietStatus;
import com.example.web.Config.status.HoaDonStatus;
import com.example.web.Config.status.PhuongThucThanhToanStatus;
import com.example.web.model.HoaDon;
import com.example.web.model.HoaDonChiTiet;
import com.example.web.model.KhachHang;
import com.example.web.model.LichSuHoaDon;
import com.example.web.model.NhanVien;
import com.example.web.model.TrangThaiHoaDon;
import com.example.web.repository.IHoaDonRepository;
import com.example.web.repository.IKhachHangRepository;
import com.example.web.repository.ILichSuHoaDonRepository;
import com.example.web.repository.INhanVienRepository;
import com.example.web.request.HoaDonRequest;
import com.example.web.request.ThongTinKhachHang;
import com.example.web.response.HoaDonChiTietReponse;
import com.example.web.response.HoaDonReponse;
import com.example.web.service.IHoaDonService;
import com.example.web.response.HoaDonFilter;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.SneakyThrows;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
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
import org.springframework.util.ResourceUtils;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.File;
import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
                    hd.setTrangThai(TrangThaiHoaDon.DA_XAC_NHAN.getValue());
                    hd.setPhiVanChuyen(request.getPhiVanChuyen());
                    hd.setDiaChi(request.getDiaChi());
                    hd.setHoTen(request.getHoTen());
                    hd.setMoTa(request.getMoTa());
                    hd.setSdt(request.getSdt());
                    hd.setPhuongThucThanhToan(PhuongThucThanhToanStatus.THANH_TOAN_KHI_NHAN_HANG);
                } else {
                    if (request.getSoTienThanhToan().isEmpty() || request.getSoTienThanhToan() == null) {
                        attributes.addFlashAttribute("error", "chưa nhập tiền khách đưa");
                        return "redirect:/admin/hoa-don/detail?idHD=" + request.getId() + "&idKhachHang=" + request.getIdKhachHang();
                    } else if (tongTienHoaDon.doubleValue() > Double.parseDouble(request.getSoTienThanhToan())) {
                        attributes.addFlashAttribute("error", "số tiền khách đưa chưa đủ");
                        attributes.addFlashAttribute("soTienKhachTra", request.getSoTienThanhToan());
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
                HoaDon response = hoaDonRepository.save(hd);
                attributes.addFlashAttribute("success", "Hoá đơn " + response.getMa() + " tạo thành công");
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
    public List<HoaDonChiTietReponse> getHoaDonChiTiets(UUID id) {
        List<HoaDonChiTietReponse> response = hoaDonRepository.findAllHoaDonChiTietByHoaDon_id(id);
        return response;
    }

    @Override
    public HoaDonChiTietReponse getHoaDonChiTiet(UUID idHDCT) {
        HoaDonChiTietReponse response = hoaDonRepository.findHoaDonChiTietByHoaDon_id(idHDCT);
        return response;
    }

    @Override
    public Page<HoaDonChiTiet> getHoaDonHuyChiTiet(UUID id, Integer pageNo, Integer size) {
        Pageable pageable = PageRequest.of(pageNo, size);
        return hoaDonRepository.getHoaDonHuyChiTiet(id, pageable);
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
                if (!filter.getLoaiHoaDon().isBlank()) {
                    predicates.add(criteriaBuilder.and(criteriaBuilder.equal(root.get("loaiHoaDon"), Boolean.valueOf(filter.getLoaiHoaDon()))));
                }
                if (!filter.getTrangThai().isEmpty() && filter.getTrangThai() != null) {
                    predicates.add(criteriaBuilder.and(criteriaBuilder.equal(root.get("trangThai"), Integer.parseInt(filter.getTrangThai()))));
                }
                predicates.add(criteriaBuilder.and(criteriaBuilder.notEqual(root.get("trangThai"), HoaDonStatus.HOA_DON_CHO)));
                return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
            }
        }, pageable);
    }

    @Override
    public HoaDon add(HoaDon hoaDon) {
        return hoaDonRepository.save(hoaDon);
    }

    @Override
    public String updateHoaDonById(HoaDon hoaDon) {
        Optional<HoaDon> hd = hoaDonRepository.findById(hoaDon.getId());
        hd.get().setTongTien(hoaDon.getTongTien());
        hoaDonRepository.save(hd.get());
        return "redirect:/admin/hoa-don-onl/detail/" + hoaDon.getId();
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
    public String updatePVC(HoaDon hoaDon) {
        hoaDonRepository.save(hoaDon);
        return "redirect:/admin/hoa-don-onl/detail/" + hoaDon.getId();
    }

    @Override
    public String inHoaDon(String id, Page<HoaDonChiTiet> hoaDonChiTiets) {
        HoaDon hoaDon = getOne(id);
        Date date = java.util.Calendar.getInstance().getTime();
        SimpleDateFormat dateFormatter = new SimpleDateFormat("HH:mm:ss");
        SimpleDateFormat dateFormatter2 = new SimpleDateFormat("dd-MM-yyyy");
        String time = String.valueOf(dateFormatter.format(date));
        String day = String.valueOf(dateFormatter2.format(date));
        try {
            // Load báo cáo JasperReports từ tệp .jasper
            File file = ResourceUtils.getFile("C:\\Users\\DELL\\Desktop\\DATN2\\DuAnTotNghiep\\src\\main\\resources\\HoaDon.jrxml");
            JasperReport jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());
            JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(hoaDonChiTiets.getContent());
            //Tổng tiền
            Integer tongTien = 0;
            for (int i = 0; i <= hoaDonChiTiets.getContent().size() - 1; i++) {
                tongTien += hoaDonChiTiets.getContent().get(i).getSoLuong() * hoaDonChiTiets.getContent().get(i).getChiTietSanPham().getSanPham().getGiaBan().intValue();
            }
            // Tạo dữ liệu cho báo cáo (đây là nơi bạn cung cấp thông tin hóa đơn)
            Map<String, Object> parameters = new HashMap<>();
            parameters.put("ngayTao", day);
            parameters.put("nhanVien", hoaDon.getNhanVien().getHoTen());
            parameters.put("khachHang", hoaDon.getKhachHang() == null ? "Khách bán lẻ" : hoaDon.getKhachHang().getHoTen());
            parameters.put("maHd", hoaDon.getMa());
            parameters.put("inVaoLuc", time);
            parameters.put("tongTien", tongTien);
            parameters.put("hoaDonChiTiet", dataSource);

            // Tạo báo cáo JasperPrint sử dụng dữ liệu và mẫu
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, new JREmptyDataSource());

            // Lưu tệp PDF hóa đơn vào máy chủ
            String pdfFileName = hoaDon.getMa() + ".pdf";
            String filePath = "C:\\Users\\DELL\\Desktop\\DATN2\\DuAnTotNghiep\\hoa_don-pdf\\" + pdfFileName;
            JasperExportManager.exportReportToPdfFile(jasperPrint, filePath);

        } catch (JRException | FileNotFoundException e) {
            e.printStackTrace();
        }
        return "redirect:/admin/hoa-don/view-update/" + hoaDon.getId();
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

}
