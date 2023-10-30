package com.example.web.controller;

import com.example.web.model.KhuyenMai;
import com.example.web.model.SanPhamKhuyenMai;
import com.example.web.request.KhuyenMaiRequest;
import com.example.web.response.FilterKhuyenMai;
import com.example.web.response.SanPhamAsKhuyenMai;
import com.example.web.service.IKhuyenMaiService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.UUID;

@Controller
@RequestMapping("/admin/khuyen-mai")
public class KhuyenMaiController {

    @Autowired
    private IKhuyenMaiService khuyenMaiService;

    @Autowired
    private HttpServletRequest request;

    private Page<KhuyenMai> khuyenMais = null;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private SanPhamController sanPhamController;

    private String urlUpdate = null;

    private Integer pager = null;


    @GetMapping("/")
    public String hienThi(Model model, @RequestParam(defaultValue = "1") Integer page) {
        khuyenMais = khuyenMaiService.getAll(page);
        model.addAttribute("khuyenMais", khuyenMais);
        model.addAttribute("khuyenMai", new KhuyenMai());
        model.addAttribute("url", request.getRequestURI() + "?page=");
        return "quanlykhuyenmai/khuyenmai/khuyen-mai";
    }

    @GetMapping("/filter")
    public String filter(Model model, @RequestParam(defaultValue = "1") Integer page, @ModelAttribute("khuyenMai") FilterKhuyenMai filter) {
        khuyenMais = khuyenMaiService.filterKhuyenMai(page, filter);
        String url = request.getRequestURI() + "?" + request.getQueryString().replaceAll("[&?]page.*?(?=&|\\?|$)", "") + "&page=";
        model.addAttribute("khuyenMais", khuyenMais);
        model.addAttribute("filter", filter);
        model.addAttribute("url", url);
        return "quanlykhuyenmai/khuyenmai/khuyen-mai";
    }

    @GetMapping("/filter-san-pham-khuyen-mai/{idKM}")
    public String filterSanPhamKhuyenMai(Model model, @PathVariable String idKM, @RequestParam(defaultValue = "1", required = false) Integer page, @ModelAttribute("sanPhamAsKhuyenMai") SanPhamAsKhuyenMai filter) {
        pager = page;
        urlUpdate = request.getRequestURI() + "?" + request.getQueryString().replaceAll("[&?]page.*?(?=&|\\?|$)", "") + "&page=";
        Pageable pageable = PageRequest.of(page - 1, 10);
        Page<SanPhamKhuyenMai> sanPhamKhuyenMai = khuyenMaiService.filterSanPhamKhuyeMai(filter, pageable, UUID.fromString(idKM));
        KhuyenMai km = khuyenMaiService.getById(UUID.fromString(idKM));
        sanPhamController.danhSachThuocTinhSanPham(model);
        model.addAttribute("dataKhuyenMai", km);
        model.addAttribute("uri", urlUpdate);
        model.addAttribute("url", "/admin/khuyen-mai/update?idKM=" + km.getId());
        model.addAttribute("listChiTietKhuyenMai", sanPhamKhuyenMai);
        model.addAttribute("sanPhamKhuyenMaiFilter", filter);
        return "quanlykhuyenmai/khuyenmai/new-khuyen-mai";
    }

    @GetMapping("/new")
    public String newCreate(Model model) {
        model.addAttribute("khuyenMai", new KhuyenMaiRequest());
        model.addAttribute("url", "/admin/khuyen-mai/create");
        return "quanlykhuyenmai/khuyenmai/new-khuyen-mai";
    }

    @PostMapping("/create")
    public String create(@Valid @ModelAttribute("khuyenMai") KhuyenMaiRequest khuyenMaiRequest, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("dataKhuyenMai", khuyenMaiRequest);
            return "quanlykhuyenmai/khuyenmai/new-khuyen-mai";
        }
        if (khuyenMaiRequest.getNgayBatDau().compareTo(khuyenMaiRequest.getNgayKetThuc()) >= 0) {
            model.addAttribute("dataKhuyenMai", khuyenMaiRequest);
            model.addAttribute("errorNgay", "ngày không hợp lệ");
            return "quanlykhuyenmai/khuyenmai/new-khuyen-mai";
        } else {
            KhuyenMai km = khuyenMaiService.addKhuyenMai(modelMapper.map(khuyenMaiRequest, KhuyenMai.class));
            return "redirect:/admin/khuyen-mai/detail?id=" + km.getId();

        }
    }

    @PostMapping(value = "/update")
    public String update(@Valid @ModelAttribute("khuyenMai") KhuyenMaiRequest khuyenMaiRequest, BindingResult result, RedirectAttributes attributes, @RequestParam(required = false) String idKM) {
        if (result.hasErrors()) {
            return "quanlykhuyenmai/khuyenmai/new-khuyen-mai";
        }
        if (khuyenMaiRequest.getNgayBatDau().compareTo(khuyenMaiRequest.getNgayKetThuc()) >= 0) {
            attributes.addFlashAttribute("errorNgay", "ngày không hợp lệ");
            return "redirect:/admin/khuyen-mai/detail?id=" + idKM;
        } else {
            KhuyenMai khuyenMai = khuyenMaiService.getById(UUID.fromString(idKM));
            khuyenMai.setMoTa(khuyenMaiRequest.getMoTa());
            khuyenMai.setNgayBatDau(Date.valueOf(khuyenMaiRequest.getNgayBatDau()));
            khuyenMai.setNgayKetThuc(Date.valueOf(khuyenMaiRequest.getNgayKetThuc()));
            khuyenMai.setTen(khuyenMaiRequest.getTen());
            khuyenMaiService.updateKhuyenMai(khuyenMai);
            return "redirect:/admin/khuyen-mai/detail?id=" + khuyenMai.getId();
        }

    }

    @GetMapping("/detail")
    public String getChiTietKhuuyenMai(@RequestParam String id, @RequestParam(defaultValue = "1") Integer page, Model model) {
        KhuyenMai km = khuyenMaiService.getById(UUID.fromString(id));
        Page<SanPhamKhuyenMai> list = khuyenMaiService.getKhuyenMaiById(UUID.fromString(id), page);
        sanPhamController.danhSachThuocTinhSanPham(model);
        model.addAttribute("listChiTietKhuyenMai", list);
        model.addAttribute("dataKhuyenMai", km);
        model.addAttribute("khuyenMai", new KhuyenMaiRequest());
        model.addAttribute("url", "/admin/khuyen-mai/update?idKM=" + km.getId());
        model.addAttribute("sanPhamKhuyenMai", new SanPhamKhuyenMai());
        model.addAttribute("sanPhamAsKhuyeMai", new SanPhamAsKhuyenMai());
        return "quanlykhuyenmai/khuyenmai/new-khuyen-mai";
    }

    @GetMapping("/api-san-pham-khuyen-mai/{id}")
    @ResponseBody
    public SanPhamAsKhuyenMai sanPhamKhuyenMai(@PathVariable String id) {
        SanPhamAsKhuyenMai sanPhamKhuyenMai = khuyenMaiService.getSanPhamAsKhuyenMai(UUID.fromString(id));
        return sanPhamKhuyenMai;
    }

    @PostMapping("/khuyen-mai-san-pham")
    public String addSanPhamKhuyenMai(@ModelAttribute("sanPhamKhuyenMai") SanPhamKhuyenMai sanPhamKhuyenMai, @RequestParam String idKM) {
        Boolean spkm = khuyenMaiService.addSanPhamKhuyenMai(sanPhamKhuyenMai, idKM);
        return "redirect:/admin/khuyen-mai/detail?id=" + idKM;
    }

    @GetMapping("/update-trang-thai-san-pham")
    public String updateTrangThaiSanPhamKhuyenMai(@RequestParam String idSPKM, @RequestParam Integer trangThai) {
        SanPhamKhuyenMai sanPhamKhuyenMai = khuyenMaiService.updateTrangThaiKhuyenMaiChiTiet(trangThai, UUID.fromString(idSPKM));
        if (urlUpdate == null) {
            return "redirect:/admin/khuyen-mai/detail?id=" + sanPhamKhuyenMai.getKhuyenMai().getId();
        } else {
            return "redirect:" + urlUpdate + pager;
        }
    }


    @GetMapping("/update-trang-thai")
    public String updateTrangThaiKhuyenMai(@RequestParam String idKM, @RequestParam Integer trangThai) {
        KhuyenMai khuyenMai = khuyenMaiService.updateTrangThaiKhuyenMai(trangThai, UUID.fromString(idKM));
        return "redirect:/admin/khuyen-mai/";
    }


    @PostMapping("/update-san-pham-khuyen-mai")
    public String updateSanPhamKhuyenMai(@RequestParam String idSPKM, @ModelAttribute("sanPhamKhuyenMai") SanPhamKhuyenMai sanPhamKhuyenMai) {
        SanPhamKhuyenMai spkm = khuyenMaiService.getSanPhamKhuyenMaiById(UUID.fromString(idSPKM));
        if (sanPhamKhuyenMai.getLoaiGiamGia()) {
            Integer donGiaKhiGiamPhanTram = spkm.getSanPhamKM().getGiaBan().intValue() - (spkm.getSanPhamKM().getGiaBan().intValue() / 100) * sanPhamKhuyenMai.getMucGiam().intValue();
            spkm.setDonGiaSauKhiGiam(BigDecimal.valueOf(donGiaKhiGiamPhanTram));
        } else {
            Integer donGiaKhiGiamVND = spkm.getSanPhamKM().getGiaBan().intValue() - sanPhamKhuyenMai.getMucGiam().intValue();
            spkm.setDonGiaSauKhiGiam(BigDecimal.valueOf(donGiaKhiGiamVND));
        }
        spkm.setLoaiGiamGia(sanPhamKhuyenMai.getLoaiGiamGia());
        spkm.setMucGiam(sanPhamKhuyenMai.getMucGiam());
        khuyenMaiService.updateSanPhamKhuyenMai(spkm);
        if (urlUpdate == null) {
            return "redirect:/admin/khuyen-mai/detail?id=" + spkm.getKhuyenMai().getId();
        } else {
         return "redirect:"+urlUpdate+pager;
        }
    }
}
