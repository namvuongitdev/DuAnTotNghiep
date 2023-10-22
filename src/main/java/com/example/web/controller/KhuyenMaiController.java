package com.example.web.controller;

import com.example.web.model.KhuyenMai;
import com.example.web.model.SanPhamKhuyenMai;
import com.example.web.request.KhuyenMaiRequest;
import com.example.web.response.FilterKhuyenMai;
import com.example.web.response.KhuyenMaiReponse;
import com.example.web.response.SanPhamAsKhuyenMai;
import com.example.web.service.IKhuyenMaiService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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
import java.math.BigDecimal;
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

    @GetMapping("/new")
    public String newCreate(Model model) {
        model.addAttribute("khuyenMai", new KhuyenMaiRequest());
        return "quanlykhuyenmai/khuyenmai/new-khuyen-mai";
    }

    @PostMapping("/create")
    public String create(@Valid @ModelAttribute("khuyenMai") KhuyenMaiRequest khuyenMai, BindingResult result) {
        if(result.hasErrors()){
            return "quanlykhuyenmai/khuyenmai/new-khuyen-mai";
        }
        KhuyenMai km = khuyenMaiService.addKhuyenMai(modelMapper.map(khuyenMai , KhuyenMai.class));
        return "redirect:/admin/khuyen-mai/detail?id=" + km.getId();
    }

    @GetMapping("/detail")
    public String getChiTietKhuuyenMai(@RequestParam String id, @RequestParam(defaultValue = "1") Integer page, Model model) {
        Page<KhuyenMaiReponse> list = khuyenMaiService.getKhuyenMaiById(UUID.fromString(id), page);
        sanPhamController.danhSachThuocTinhSanPham(model);
        model.addAttribute("dataKhuyenMai", khuyenMaiService.getById(UUID.fromString(id)));
        model.addAttribute("listChiTietKhuyenMai", list);
        model.addAttribute("sanPhamKhuyenMai", new SanPhamKhuyenMai());
        return "quanlykhuyenmai/khuyenmai/new-khuyen-mai";
    }

    @PostMapping("/khuyen-mai-san-pham")
    public String addSanPhamKhuyenMai(@ModelAttribute("sanPhamKhuyenMai") SanPhamKhuyenMai sanPhamKhuyenMai, @RequestParam String idKM) {
        Boolean spkm = khuyenMaiService.addSanPhamKhuyenMai(sanPhamKhuyenMai, idKM);
        return "redirect:/admin/khuyen-mai/detail?id=" + idKM;
    }

    @GetMapping("/update-trang-thai-san-pham")
    public String updateTrangThaiSanPhamKhuyenMai(@RequestParam String idSPKM, @RequestParam Integer trangThai) {
        SanPhamKhuyenMai sanPhamKhuyenMai = khuyenMaiService.updateTrangThaiKhuyenMaiChiTiet(trangThai, UUID.fromString(idSPKM));
        return "redirect:/admin/khuyen-mai/detail?id=" + sanPhamKhuyenMai.getKhuyenMai().getId();
    }

    @GetMapping("/update-trang-thai")
    public String updateTrangThaiKhuyenMai(@RequestParam String idKM, @RequestParam Integer trangThai) {
        KhuyenMai khuyenMai = khuyenMaiService.updateTrangThaiKhuyenMai(trangThai, UUID.fromString(idKM));
        return "redirect:/admin/khuyen-mai/";
    }

    @GetMapping("/api-san-pham-khuyen-mai/{id}")
    @ResponseBody
    public SanPhamAsKhuyenMai sanPhamKhuyenMai(@PathVariable String id) {
        SanPhamAsKhuyenMai sanPhamKhuyenMai = khuyenMaiService.getSanPhamAsKhuyenMai(UUID.fromString(id));
        return sanPhamKhuyenMai;
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
        return "redirect:/admin/khuyen-mai/detail?id=" + spkm.getKhuyenMai().getId();
    }
}
