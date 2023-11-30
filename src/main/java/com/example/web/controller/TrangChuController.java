package com.example.web.controller;

import com.example.web.model.*;
import com.example.web.response.*;
import com.example.web.service.*;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.Calendar;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/index")
public class TrangChuController {

    @Autowired
    private IFormDangService iFormDangService;

    @Autowired
    private IChatLieuService iChatLieuService;

    @Autowired
    private SizeService sizeService;

    @Autowired
    private IMauSacService mauSacService;

    @Autowired
    private DanhMucService danhMucService;

    @Autowired
    private ISanPhamService iSanPhamService;

    @Autowired
    IChiTietSanPhamService iChiTietSanPhamService;

    @Autowired
    IGioHangOnllineService iGioHangOnllineService;

    @Autowired
    private IAnhService anhService;

    @Autowired
    private IKhachHangService khachHangService;

    @Autowired
    private INhanVienService nhanVienService;

    private Page<SanPham> sanPhamPage = null;

    private Page<SanPhamAndKhuyenMai> sanPhamKhuyenMaiPage = null;

    @GetMapping("/shop")
    public String hienThi(Principal principal, HttpSession session, Model model, @RequestParam(defaultValue = "1") int page) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (principal != null) {
            String username = principal.getName();
            session.setAttribute("username", username);
            KhachHang khachHang = khachHangService.findByEmailOrAndTaiKhoan(authentication.getName());
            model.addAttribute("count", iGioHangOnllineService.countSoLuongSPTrongGioHang(khachHang.getId()));
        }
        Pageable pageable = PageRequest.of(page - 1, 10);
        sanPhamPage = iSanPhamService.findAll(pageable);
        sanPhamKhuyenMaiPage = iSanPhamService.getALL(pageable);
        model.addAttribute("listSanPham", sanPhamKhuyenMaiPage);
        danhSachThuocTinhSanPham(model);
        model.addAttribute("filterSanPham", new SanPhamFilter());
        model.addAttribute("url", "/api-hien-thi?page=");
        return "banHangOnlline/index";
    }

    @GetMapping("/home")
    public String trangChu(Principal principal, HttpSession session, Model model, @RequestParam(defaultValue = "1") int page) {
//        if (principal != null) {
//            String username = principal.getName();
//            session.setAttribute("username", username);
//            KhachHang khachHang = khachHangService.findByEmailOrAndTaiKhoan(username);
//            model.addAttribute("count", iGioHangOnllineService.countSoLuongSPTrongGioHang(khachHang.getId()));
//        }
        List<DanhMuc> getAll1 = danhMucService.getAll1();
        model.addAttribute("danhMuc", getAll1);
        List<SanPham> sanPhamNhieuNguoiMua = iSanPhamService.sanPhamNhieuNguoiMua();
        model.addAttribute("listsanpham", sanPhamNhieuNguoiMua);
        return "banHangOnlline/trangchu";
    }

    @GetMapping("/gioi-thieu")
    public String gioiThieu(Principal principal, HttpSession session, Model model, @RequestParam(defaultValue = "1") int page) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (principal != null) {
            String username = principal.getName();
            session.setAttribute("username", username);
            KhachHang khachHang = khachHangService.findByEmailOrAndTaiKhoan(authentication.getName());
            model.addAttribute("count", iGioHangOnllineService.countSoLuongSPTrongGioHang(khachHang.getId()));
        }
        return "banHangOnlline/home";
    }

    public void danhSachThuocTinhSanPham(Model model) {
        model.addAttribute("listChatLieu", iChatLieuService.getAll());
        model.addAttribute("listFormDang", iFormDangService.getAll());
        model.addAttribute("listKichCo", sizeService.getAll());
        model.addAttribute("listMuaSac", mauSacService.getAll());
        model.addAttribute("listDanhMuc", danhMucService.getAll());
    }

    @GetMapping({"/api-hien-thi"})
    @ResponseBody
    public Page<SanPham> apiSanPham(@RequestParam Integer page, @RequestParam(required = false) String value) {
        Page listSanPham = null;
        Pageable pageable = PageRequest.of(page - 1, 20);
        if (value.isEmpty()) {
            listSanPham = iSanPhamService.findAll(pageable);
        } else {
            listSanPham = iSanPhamService.getAllByTenOrMa(value, page);
        }
        return listSanPham;
    }

    @GetMapping("/api-san-pham-nhieu-nguoi-mua")
    @ResponseBody
    public List<SanPham> apiSanPhamNhieuNguoiMua() {
        List<SanPham> sanPhamNhieuNguoiMua = iSanPhamService.sanPhamNhieuNguoiMua();
        return sanPhamNhieuNguoiMua;
    }

    @PostMapping("/api-filter")
    @ResponseBody
    public Page<SanPham> filterSanPham(@RequestParam Integer page, @RequestBody SanPhamFilter filter) {
        Pageable pageable = PageRequest.of(page - 1, 20);
        Page listSanPham = iSanPhamService.sanPhamFilter(filter, pageable);
        return listSanPham;
    }

    @GetMapping("/thoi-trang-nam")
    @ResponseBody
    public Page<SanPham> thoiTrangNam(Model model, @RequestParam(defaultValue = "1") int page) {
        boolean gioiTinh = true;
        Page listSanPham = null;
        Pageable pageable = PageRequest.of(page - 1, 5);
        listSanPham = iSanPhamService.findAllGender(pageable, gioiTinh);
        return listSanPham;
    }

    @GetMapping("/thoi-trang-nu")
    @ResponseBody
    public Page<SanPham> thoiTrangNu(Model model, @RequestParam(defaultValue = "1") int page) {
        boolean gioiTinh = false;
        Page listSanPham = null;
        Pageable pageable = PageRequest.of(page - 1, 5);
        listSanPham = iSanPhamService.findAllGender(pageable, gioiTinh);
        return listSanPham;
    }

    @GetMapping("/danh-muc-san-pham/{id}")
    @ResponseBody
    public Page<SanPham> danhMuc(Model model, @RequestParam(defaultValue = "1") int page, @PathVariable(name = "id") String id) {
        Page listSanPham = null;
        Pageable pageable = PageRequest.of(page - 1, 5);
        listSanPham = iSanPhamService.findAllDanhMuc(pageable, id);
        return listSanPham;
    }

    @GetMapping("/chi-tiet-san-pham-onl")
    public String chiTiet(Model model, @RequestParam(name = "id") String
            idSanPham, Principal principal) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        List<Anh> anhs = anhService.getAllAnhBySanPham_id(UUID.fromString(idSanPham));
        SanPham sanPham = iSanPhamService.getOne(UUID.fromString(idSanPham));
        List<ChiTietSanPham> listCT = iChiTietSanPhamService.listCTSPTheoIdSP(UUID.fromString(idSanPham));
        List<MauSac> listMS = mauSacService.getTheoCTSP(UUID.fromString(idSanPham));
        List<Size> listSize = sizeService.getTheoCT(UUID.fromString(idSanPham));
        if (!sanPham.getSanPhamKhuyenMais().isEmpty()) {
            sanPham.getSanPhamKhuyenMais().forEach(o -> {
                if (o.getTrangThai() == 1 && o.getKhuyenMai().getTrangThai() == 1) {
                    model.addAttribute("sanPhamKhuyenMai", o);
                }
            });
        }
        model.addAttribute("listMau", listMS);
        model.addAttribute("listSize", listSize);
        model.addAttribute("sanPham", sanPham);
        model.addAttribute("listAnh", anhs);
        if (principal != null) {
            KhachHang khachHang = khachHangService.findByEmailOrAndTaiKhoan(authentication.getName());
            model.addAttribute("count", iGioHangOnllineService.countSoLuongSPTrongGioHang(khachHang.getId()));
        }
        return "banHangOnlline/chitiet";
    }

    @GetMapping("/so-luong/{idSize}/{color}")
    @ResponseBody
    public ChiTietOnllineResponse getSoLuong(@RequestParam(name = "id") String idSP,
                                             @PathVariable(name = "idSize") String idSize,
                                             @PathVariable(name = "color") String idMau) {
        ChiTietOnllineResponse listCT = iChiTietSanPhamService.getChiTietSanPhamByMauSac_IdAndSize_IdAndSanPham_Id1(UUID.fromString(idMau), idSize, UUID.fromString(idSP));
        return listCT;
    }

    @GetMapping("/anh-mau-sac")
    @ResponseBody
    public List<Anh> getAnhByMauSac_idAndSanPham_id(@RequestParam String idSP, @RequestParam String idMS) {
        List<Anh> listAnh = anhService.findAnhBySanPham_idAndMauSac_id(UUID.fromString(idSP), UUID.fromString(idMS));
        return listAnh;
    }

    @GetMapping("/kich-co")
    @ResponseBody
    public List<Size> getSizeBySanPham_idAndMauSac_id(@RequestParam String idSP, @RequestParam String idMS) {
        List<Size> listSize = iChiTietSanPhamService.getSizeBySanPham_idAndMauSac_id(UUID.fromString(idSP), UUID.fromString(idMS));
        return listSize;
    }


    @GetMapping("/tinh-trang/{idSP}")
    @ResponseBody
    public String getTinhTrang(@RequestParam(name = "size") String size, @RequestParam(name = "color") String color, @PathVariable(name = "idSP") String idSP) {
        ChiTietOnllineResponse chiTiet = iChiTietSanPhamService.getChiTietSanPhamByMauSac_IdAndSize_IdAndSanPham_Id1(UUID.fromString(color), size, UUID.fromString(idSP));
        return chiTiet.getTrangThai() == 1 ? "Kinh Doanh" : "Ngừng Kinh Doanh";
    }

    @GetMapping(value = "/chi-tiet")
    @ResponseBody
    public List<ChiTietSanPhamResponse> getChiTietSanPham(@RequestParam("id") String id) {
        List<ChiTietSanPhamResponse> chiTietSanPhams = iChiTietSanPhamService.getCTSP(id);
        return chiTietSanPhams;
    }

}