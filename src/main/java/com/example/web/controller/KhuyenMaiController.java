package com.example.web.controller;

import com.example.web.model.KhuyenMai;
import com.example.web.model.SanPham;
import com.example.web.model.SanPhamKhuyenMai;
import com.example.web.request.KhuyenMaiRequest;
import com.example.web.request.KhuyenMaiSanPhamRequest;
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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
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
    public String create(@Valid @ModelAttribute("khuyenMaiRequest") KhuyenMaiRequest khuyenMaiRequest, BindingResult result, Model model  , RedirectAttributes attributes) {
        if (result.hasErrors()) {
            model.addAttribute("dataKhuyenMai", khuyenMaiRequest);
            return "quanlykhuyenmai/khuyenmai/new-khuyen-mai";
        }
        KhuyenMai response = modelMapper.map(khuyenMaiRequest, KhuyenMai.class);
        Integer trangThai = khuyenMaiService.validateTrangThai(response);
        response.setTrangThai(trangThai);
        KhuyenMai km = khuyenMaiService.addKhuyenMai(response);
        if(km != null){
            attributes.addFlashAttribute("success" , "thêm thành công");
        }else{
            attributes.addFlashAttribute("error" , "thêm không thành công");
        }
        return "redirect:/admin/khuyen-mai/detail?id=" + km.getId();
    }

    @PostMapping(value = "/update")
    public String update(@Valid @ModelAttribute("khuyenMaiRequest") KhuyenMaiRequest khuyenMaiRequest, BindingResult result, RedirectAttributes attributes, @RequestParam(required = false) String idKM) {
        if (result.hasErrors()) {
            attributes.addFlashAttribute("dataKhuyenMai", khuyenMaiRequest);
            return "redirect:/admin/khuyen-mai/detail?id=" + idKM;
        } else {
            KhuyenMai khuyenMai = khuyenMaiService.updateKhuyenMai(khuyenMaiRequest, UUID.fromString(idKM));
            attributes.addFlashAttribute("dataKhuyenMai", khuyenMai);
            attributes.addFlashAttribute("success", "update thành công");
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
    @ResponseBody
    public SanPham addSanPhamKhuyenMai(@RequestBody KhuyenMaiSanPhamRequest request) {
        SanPham spkm = khuyenMaiService.addSanPhamKhuyenMai(request);
        return spkm;
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

    @PostMapping("delete")
    public String delete(@RequestParam String idKM , RedirectAttributes attributes){
        KhuyenMai khuyenMai = khuyenMaiService.getKhuyenMaiById(UUID.fromString(idKM));
        if(khuyenMai != null){
            attributes.addFlashAttribute("success" , "xoá thành công");
        }else{
            attributes.addFlashAttribute("error" , "không tìm thấy khuyến mại");
        }
        return "redirect:/admin/khuyen-mai/";
    }

    @PostMapping("/update-san-pham-khuyen-mai")
    public String updateSanPhamKhuyenMai(@RequestParam String idSPKM, @ModelAttribute("sanPhamKhuyenMai") SanPhamKhuyenMai sanPhamKhuyenMai , RedirectAttributes attributes) {
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
        SanPhamKhuyenMai kmsp =  khuyenMaiService.updateSanPhamKhuyenMai(spkm);
        if(kmsp != null){
            attributes.addFlashAttribute("success" , "update thành công");
        }else{
            attributes.addFlashAttribute("error" , "update không thành công");
        }
        if (urlUpdate == null) {
            return "redirect:/admin/khuyen-mai/detail?id=" + spkm.getKhuyenMai().getId();
        } else {
            return "redirect:" + urlUpdate + pager;
        }
    }

    @GetMapping("/delete")
    public String deleteKhuyenMaiCT(@RequestParam UUID idKMCT , @RequestParam UUID idKM , RedirectAttributes attributes){
        Boolean isCheck = khuyenMaiService.deleteKhuyenMaiCT(idKMCT);
        if(isCheck){
            attributes.addFlashAttribute("success" , "xoá thành công");
        }else{
            attributes.addFlashAttribute("error" , "không tìm thấy khuyến mại");
        }
        return "redirect:/admin/khuyen-mai/detail?id="+idKM;
    }
}
