package com.example.web.controller;

import com.beust.jcommander.Parameter;
import com.example.web.model.ChatLieu;
import com.example.web.model.ChiTietSanPham;
import com.example.web.model.DanhMuc;
import com.example.web.model.KieuDang;
import com.example.web.model.MauSac;
import com.example.web.model.SanPham;
import com.example.web.model.Size;
import com.example.web.response.SanPhamFilter;
import com.example.web.service.DanhMucService;
import com.example.web.service.IChatLieuService;
import com.example.web.service.IChiTietSanPhamService;
import com.example.web.service.IFormDangService;
import com.example.web.service.IKhuyenMaiService;
import com.example.web.service.IMauSacService;
import com.example.web.service.ISanPhamService;
import com.example.web.service.SizeService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
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
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Controller
@RequestMapping("/admin/san-pham")
public class SanPhamController {

    @Autowired
    private IFormDangService iFormDangService;

    @Autowired
    private ISanPhamService iSanPhamService;

    @Autowired
    private IChatLieuService iChatLieuService;

    @Autowired
    private SizeService sizeService;

    @Autowired
    private IMauSacService mauSacService;

    @Autowired
    private DanhMucService danhMucService;

    @Autowired
    private IChiTietSanPhamService chiTietSanPhamService;

    @Autowired
    private IKhuyenMaiService khuyenMaiService;

    @Autowired
    private HttpServletRequest request;

    private Page<SanPham> sanPhamPage = null;

    @GetMapping(value = "/hien-thi")
    public String hienThi(Model model, @RequestParam(defaultValue = "1") Integer page) {
        Pageable pageable = PageRequest.of(page - 1, 10, Sort.by("ngayTao").descending());
        sanPhamPage = iSanPhamService.findAll(pageable);
        model.addAttribute("listSanPham", sanPhamPage);
        danhSachThuocTinhSanPham(model);
        model.addAttribute("filterSanPham", new SanPhamFilter());
        model.addAttribute("url", "/admin/san-pham/hien-thi?page=");
        return "quanLySanPham/sanpham/san-pham";
    }

    @GetMapping("/filter")
    public String filterSanPham(@RequestParam(defaultValue = "1") Integer page,
                                @ModelAttribute("filterSanPham") SanPhamFilter filter,
                                Model model) {
        Pageable pageable = PageRequest.of(page - 1, 10, Sort.by("ngayTao").descending());
        sanPhamPage = iSanPhamService.sanPhamFilter(filter, pageable);
        String url = "/admin/san-pham/filter?" + request.getQueryString().replaceAll("[&?]page.*?(?=&|\\?|$)", "") + "&page=";
        model.addAttribute("filter", filter);
        model.addAttribute("listSanPham", sanPhamPage);
        danhSachThuocTinhSanPham(model);
        model.addAttribute("url", url);
        return "quanLySanPham/sanpham/san-pham";
    }

    @GetMapping({"/api-hien-thi"})
    @ResponseBody
    public Page<SanPham> apiSanPham(@RequestParam Integer page, @RequestParam(required = false) String value) {
        Page listSanPham = null;
        Pageable pageable = PageRequest.of(page - 1, 10, Sort.by("ngayTao").descending());
        if (value.isEmpty()) {
            listSanPham = iSanPhamService.findAll(pageable);
        } else {
            listSanPham = iSanPhamService.getAllByTenOrMa(value, page);
        }
        return listSanPham;
    }

    @PostMapping("/api-filter")
    @ResponseBody
    public Page<SanPham> filterSanPham(@RequestParam Integer page, @RequestBody SanPhamFilter filter) {
        Pageable pageable = PageRequest.of(page - 1, 10);
        Page listSanPham = iSanPhamService.sanPhamFilter(filter, pageable);
        return listSanPham;
    }

    @GetMapping("/new")
    public String viewAddSanPham(Model model) {
        model.addAttribute("title", "Tạo mới");
        model.addAttribute("sanPham", new SanPham());
        danhSachThuocTinhSanPham(model);
        return "quanLySanPham/sanpham/new-san-pham";
    }

    @PostMapping(value = "/add")
    public String addSanPham(@Valid @ModelAttribute("sanPham") SanPham sanPham, BindingResult result, @RequestParam(required = false) String id,
                             Model model, @RequestParam("ten") String ten, RedirectAttributes attributes) {
        boolean checkTenSP = iSanPhamService.isProductExists(ten);

        Date date = java.util.Calendar.getInstance().getTime();
        if (!id.isEmpty()) {
            if (result.hasErrors()) {
                attributes.addFlashAttribute("error", "Sửa dữ liệu thất bại");
                return "redirect:/admin/san-pham/hien-thi/" + sanPham.getId();
            } else {
                SanPham sp = iSanPhamService.getOne(UUID.fromString(id));
                sp.setNgaySua(date);
                sp.setChatLieu(sanPham.getChatLieu());
                sp.setGiaBan(sanPham.getGiaBan());
                sp.setMoTa(sanPham.getMoTa());
                sp.setGioiTinh(sanPham.getGioiTinh());
                sp.setTrangThai(sanPham.getTrangThai());
                sp.setDanhMuc(sanPham.getDanhMuc());
                sp.setKieuDang(sanPham.getKieuDang());
                sp.setTen(sanPham.getTen());
                iSanPhamService.save(sp);
                khuyenMaiService.getSanPhamById(sp.getId());
                attributes.addFlashAttribute("success", "Sửa dữ liệu thành công");
            }
        } else {
            if (result.hasErrors()) {
                model.addAttribute("title", "Tạo mới");
                danhSachThuocTinhSanPham(model);
                return "quanLySanPham/sanpham/new-san-pham";
            }else if(checkTenSP) {
                result.rejectValue("ten", "error.sanPham", "Sản phẩm này đã tồn tại.");
                model.addAttribute("title", "Tạo mới");
                danhSachThuocTinhSanPham(model);
                return "quanLySanPham/sanpham/new-san-pham";
            }else{
                String maKM = "SP" + (iSanPhamService.getAll().size() + 1);
                sanPham.setMa(maKM);
                sanPham.setNgayTao(date);
                iSanPhamService.save(sanPham);
                attributes.addFlashAttribute("success", "Thêm dữ liệu thành công");
            }
        }
        return "redirect:/admin/san-pham/hien-thi/" + sanPham.getId();
    }

    @GetMapping(value = "/hien-thi/{id}")
    public String getSanPham(@PathVariable String id, Model model) {
        SanPham sanPham = iSanPhamService.getOne(UUID.fromString(id));
        model.addAttribute("title", "Sửa dữ liệu");
        model.addAttribute("listChiTietSanPhamBySP", chiTietSanPhamService.getChiTietSanPham(id));
        model.addAttribute("sanPham", new SanPham());
        model.addAttribute("chiTietSanPham", new ChiTietSanPham());
        model.addAttribute("sp", sanPham);
        danhSachThuocTinhSanPham(model);
        model.addAttribute("listMauSacCTSP", mauSacService.getTheoCTSP(UUID.fromString(id)));
        return "quanLySanPham/sanpham/new-san-pham";
    }

    @GetMapping(value = "/add-anh-mac-dinh")
    @ResponseBody
    public SanPham addAnhMacDinhSanPham(@RequestParam String img, @RequestParam String idSP) {
        SanPham sanPham = iSanPhamService.getOne(UUID.fromString(idSP));
        sanPham.setImg(img);
        return iSanPhamService.save(sanPham);
    }


    public void danhSachThuocTinhSanPham(Model model) {
        model.addAttribute("listChatLieu", iChatLieuService.getAll1());
        model.addAttribute("listFromDang", iFormDangService.getAll1());
        model.addAttribute("listKichCo", sizeService.getAll1());
        model.addAttribute("listMuaSac", mauSacService.getAll1());
        model.addAttribute("listDanhMuc", danhMucService.getAll1());
    }

    //Call API Chất liệu
    @GetMapping("/hienThi-chatLieu")
    @ResponseBody
    public List<ChatLieu> getChatLieu() {
        return iChatLieuService.getAll1();
    }

    @PostMapping("/add-chatLieu")
    @ResponseBody
    public ChatLieu addChatLieu(@RequestBody @Valid ChatLieu chatLieu, BindingResult result) {
        boolean checkTen = iChatLieuService.isExists(chatLieu.getTen());

        if (result.hasErrors()) {
            List<ObjectError> list = result.getAllErrors();
        }else if(checkTen) {
            return chatLieu;
        }else {
            UUID uuid = UUID.randomUUID();
            chatLieu.setId(uuid);
            chatLieu.setTrangThai(1);
            chatLieu.setNgayTao(java.util.Calendar.getInstance().getTime());
            iChatLieuService.add(chatLieu);
        }
        return chatLieu;
    }

    @GetMapping("/check-tenChatLieu/{ten}")
    @ResponseBody
    public Map<String, Boolean> checkTenChatLieu(@PathVariable String ten) {
        Map<String, Boolean> response = new HashMap<>();
        boolean isDuplicate = iChatLieuService.isExists(ten);
        response.put("isDuplicate", isDuplicate);
        return response;
    }
    //End

    //Call API Danh mục
    @GetMapping("/hienThi-danhMuc")
    @ResponseBody
    public List<DanhMuc> getDanhMuc() {
        return danhMucService.getAll1();
    }

    @PostMapping("/add-danhMuc")
    @ResponseBody
    public DanhMuc addDanhMuc(@RequestBody @Valid DanhMuc danhMuc, BindingResult result) {
        boolean checkTen = danhMucService.isExists(danhMuc.getTen());

        if (result.hasErrors()) {
            List<ObjectError> list = result.getAllErrors();
        }else if(checkTen) {
            return danhMuc;
        }else {
            UUID uuid = UUID.randomUUID();
            danhMuc.setId(String.valueOf(uuid));
            danhMuc.setTrangThai(1);
            danhMuc.setNgayTao(java.util.Calendar.getInstance().getTime());
            danhMucService.add(danhMuc);
        }
        return danhMuc;
    }

    @GetMapping("/check-tenDanhMuc/{ten}")
    @ResponseBody
    public Map<String, Boolean> checkTenDanhMuc(@PathVariable String ten) {
        Map<String, Boolean> response = new HashMap<>();
        boolean isDuplicate = danhMucService.isExists(ten);
        response.put("isDuplicate", isDuplicate);
        return response;
    }
    //End

    //Call API Kiểu dáng
    @GetMapping("/hienThi-kieuDang")
    @ResponseBody
    public List<KieuDang> getKieuDang() {
        return iFormDangService.getAll1();
    }

    @PostMapping("/add-kieuDang")
    @ResponseBody
    public KieuDang addKieuDang(@RequestBody @Valid KieuDang kieuDang, BindingResult result) {
        boolean checkTen = iFormDangService.isExists(kieuDang.getTen());

        if (result.hasErrors()) {
            List<ObjectError> list = result.getAllErrors();
        }else if(checkTen) {
            return kieuDang;
        }else {
            UUID uuid = UUID.randomUUID();
            kieuDang.setId(uuid);
            kieuDang.setTrangThai(1);
            kieuDang.setNgayTao(java.util.Calendar.getInstance().getTime());
            iFormDangService.add(kieuDang);
        }

        return kieuDang;
    }

    @GetMapping("/check-tenKieuDang/{ten}")
    @ResponseBody
    public Map<String, Boolean> checkTenKieuDang(@PathVariable String ten) {
        Map<String, Boolean> response = new HashMap<>();
        boolean isDuplicate = iFormDangService.isExists(ten);
        response.put("isDuplicate", isDuplicate);
        return response;
    }
    //End

    //Call API Kích cỡ
    @GetMapping("/hienThi-kichCo")
    @ResponseBody
    public List<Size> getKichCo() {
        return sizeService.getAll1();
    }

    @PostMapping("/add-kichCo")
    @ResponseBody
    public Size addSize(@RequestBody @Valid Size size, BindingResult result) {
        boolean checkTen = sizeService.isExists(size.getTen());
        if (result.hasErrors()) {
            List<ObjectError> list = result.getAllErrors();
        }else if(checkTen) {
            return size;
        }else {
            UUID uuid = UUID.randomUUID();
            size.setId(String.valueOf(uuid));
            size.setNgayTao(java.util.Calendar.getInstance().getTime());
            size.setTrangThai(1);
            sizeService.add(size);
        }

        return size;
    }

    @GetMapping("/check-tenKichCo/{ten}")
    @ResponseBody
    public Map<String, Boolean> checkTenKichCo(@PathVariable String ten) {
        Map<String, Boolean> response = new HashMap<>();
        boolean isDuplicate = sizeService.isExists(ten);
        response.put("isDuplicate", isDuplicate);
        return response;
    }
    //End

    //Call API Màu sắc
    @GetMapping("/hienThi-mauSac")
    @ResponseBody
    public List<MauSac> getMauSac() {
        return mauSacService.getAll1();
    }

    @PostMapping("/add-mauSac")
    @ResponseBody
    public MauSac addMauSac(@RequestBody @Valid MauSac mauSac, BindingResult result) {
        boolean checkTen = mauSacService.isExists(mauSac.getTen());
        if (result.hasErrors()) {
            List<ObjectError> list = result.getAllErrors();
        }else if(checkTen) {
            return mauSac;
        }else {
            UUID uuid = UUID.randomUUID();
            mauSac.setId(uuid);
            mauSac.setTrangThai(1);
            mauSac.setNgayTao(java.util.Calendar.getInstance().getTime());
            mauSacService.add(mauSac);
        }

        return mauSac;
    }

    @GetMapping("/check-tenMauSac/{ten}")
    @ResponseBody
    public Map<String, Boolean> checkTenMauSac(@PathVariable String ten) {
        Map<String, Boolean> response = new HashMap<>();
        boolean isDuplicate = mauSacService.isExists(ten);
        response.put("isDuplicate", isDuplicate);
        return response;
    }
    //End

    @GetMapping("/stop/{id}")
    public String stop(@PathVariable("id") UUID id, RedirectAttributes attributes) {
        SanPham sp = iSanPhamService.getOne(id);
        sp.setTrangThai(1);
        sp.setNgaySua(java.util.Calendar.getInstance().getTime());
        iSanPhamService.save(sp);
        attributes.addFlashAttribute("success", "Ngừng kinh doanh thành công");
        return "redirect:/admin/san-pham/hien-thi";
    }

    @GetMapping("/stop-ctsp/{id}")
    public String stopCTSP(@PathVariable("id") String idCt, @RequestParam String idSP, @RequestParam("tt") Integer tt,
                           RedirectAttributes attributes) {
        String url = chiTietSanPhamService.save2(idCt, idSP, tt);
        attributes.addFlashAttribute("success", "Cập nhật thành công");
        return url;
    }

}