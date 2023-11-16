package com.example.web.controller;
import com.beust.jcommander.Parameter;
import com.example.web.model.ChatLieu;
import com.example.web.model.ChiTietSanPham;
import com.example.web.model.DanhMuc;
import com.example.web.model.KhuyenMai;
import com.example.web.model.KieuDang;
import com.example.web.model.MauSac;
import com.example.web.model.SanPham;
import com.example.web.model.SanPhamKhuyenMai;
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

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
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
        Pageable pageable = PageRequest.of(page - 1, 10);
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
        Pageable pageable = PageRequest.of(page - 1, 10);
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
    public Page<SanPham> apiSanPham(@RequestParam Integer page , @RequestParam(required = false) String value) {
        Page listSanPham = null;
        Pageable pageable = PageRequest.of(page - 1, 4);
        if(value.isEmpty()){
            listSanPham = iSanPhamService.findAll(pageable);
        }else{
            listSanPham =  iSanPhamService.getAllByTenOrMa(value, page);
        }
        return listSanPham;
    }

    @PostMapping("/api-filter")
    @ResponseBody
    public Page<SanPham> filterSanPham(@RequestParam Integer page , @RequestBody SanPhamFilter filter) {
        Pageable pageable = PageRequest.of(page - 1, 10);
        Page listSanPham = iSanPhamService.sanPhamFilter(filter ,pageable);
        return listSanPham;
    }

    @GetMapping("/new")
    public String viewAddSanPham(Model model, @ModelAttribute("danhMuc") DanhMuc danhMuc, @ModelAttribute("kieuDang")KieuDang kieuDang,
                                 @ModelAttribute("chatLieu")ChatLieu chatLieu, @ModelAttribute("mauSac")MauSac mauSac,
                                 @ModelAttribute("size")Size size) {
        model.addAttribute("title", "Tạo mới");
        danhSachThuocTinhSanPham(model);
        model.addAttribute("sanPham", new SanPham());
        return "quanLySanPham/sanpham/new-san-pham";
    }

    @PostMapping(value = "/add")
    public String addSanPham(@Valid @ModelAttribute("sanPham") SanPham sanPham, BindingResult result, @RequestParam(required = false) String id, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("chatLieu", new ChatLieu());
            model.addAttribute("mauSac", new MauSac());
            model.addAttribute("kieuDang", new KieuDang());
            model.addAttribute("size", new Size());
            model.addAttribute("danhMuc", new DanhMuc());
            model.addAttribute("title", "Tạo mới");
            danhSachThuocTinhSanPham(model);
            return "quanLySanPham/sanpham/new-san-pham";
        } else {
            Date date = java.util.Calendar.getInstance().getTime();
            if (!id.isEmpty()) {
                SanPham sp = iSanPhamService.getOne(UUID.fromString(id));
                sanPham.setId(sp.getId());
                sanPham.setMa(sp.getMa());
                sanPham.setNgayTao(sp.getNgayTao());
                sanPham.setImg(sp.getImg());
                sanPham.setNgaySua(date);
                iSanPhamService.save(sanPham);
                SanPhamKhuyenMai spkm = khuyenMaiService.getSanPhamById(sp.getId());
            } else {
                String maKM = "SP" + (iSanPhamService.getAll().size() + 1);
                sanPham.setMa(maKM);
                sanPham.setNgayTao(date);
                iSanPhamService.save(sanPham);
            }
        }
        return "redirect:/admin/san-pham/hien-thi/" + sanPham.getId();
    }

    @GetMapping(value = "/hien-thi/{id}")
    public String getSanPham(@PathVariable String id, Model model,
                             @ModelAttribute("danhMuc") DanhMuc danhMuc, @ModelAttribute("kieuDang")KieuDang kieuDang,
                             @ModelAttribute("chatLieu")ChatLieu chatLieu, @ModelAttribute("mauSac")MauSac mauSac,
                             @ModelAttribute("size")Size size) {
        SanPham sanPham = iSanPhamService.getOne(UUID.fromString(id));
        danhSachThuocTinhSanPham(model);
        model.addAttribute("title", "Sửa dữ liệu");
        model.addAttribute("listChiTietSanPhamBySP", chiTietSanPhamService.getChiTietSanPham(id));
        model.addAttribute("sanPham", new SanPham());
        model.addAttribute("chiTietSanPham", new ChiTietSanPham());
        model.addAttribute("sp", sanPham);
        model.addAttribute("listMauSacCTSP" , mauSacService.getTheoCTSP(UUID.fromString(id)));
        return "quanLySanPham/sanpham/new-san-pham";
    }

    @GetMapping(value = "/add-anh-mac-dinh")
    @ResponseBody
    public SanPham addAnhMacDinhSanPham(@RequestParam String img, @RequestParam String idSP) {
        SanPham sanPham = iSanPhamService.getOne(UUID.fromString(idSP));
        sanPham.setImg(img);
      return  iSanPhamService.save(sanPham);
    }


    public void danhSachThuocTinhSanPham(Model model) {
        model.addAttribute("listChatLieu", iChatLieuService.getAll1());
        model.addAttribute("listFromDang", iFormDangService.getAll1());
        model.addAttribute("listKichCo", sizeService.getAll1());
        model.addAttribute("listMuaSac", mauSacService.getAll1());
        model.addAttribute("listDanhMuc", danhMucService.getAll1());
    }

    @PostMapping("/modal-add-chat-lieu")
    public String addChatLieu(@ModelAttribute("danhMuc") DanhMuc danhMuc, @ModelAttribute("kieuDang")KieuDang kieuDang,
                              @ModelAttribute("chatLieu")ChatLieu chatLieu, BindingResult result, Model model,
                              @ModelAttribute("mauSac")MauSac mauSac, @ModelAttribute("size")Size size){
        UUID uuid = UUID.randomUUID();
        chatLieu.setId(uuid);
        chatLieu.setTrangThai(1);
        chatLieu.setNgayTao(java.util.Calendar.getInstance().getTime());
        iChatLieuService.add(chatLieu);
        return "redirect:/admin/san-pham/new";
    }

    @PostMapping("/modal-add-danh-muc")
    public String addDanhMuc(@ModelAttribute("danhMuc") DanhMuc danhMuc, @ModelAttribute("kieuDang")KieuDang kieuDang,
                             @ModelAttribute("chatLieu")ChatLieu chatLieu, BindingResult result, Model model,
                             @ModelAttribute("mauSac")MauSac mauSac, @ModelAttribute("size")Size size){
        UUID uuid = UUID.randomUUID();
        danhMuc.setId(String.valueOf(uuid));
        danhMuc.setTrangThai(1);
        danhMuc.setNgayTao(java.util.Calendar.getInstance().getTime());
        danhMucService.add(danhMuc);
        return "redirect:/admin/san-pham/new";
    }

    @PostMapping("/modal-add-kieu-dang")
    public String addKieuDang(@ModelAttribute("danhMuc") DanhMuc danhMuc, @ModelAttribute("kieuDang")KieuDang kieuDang,
                              @ModelAttribute("chatLieu")ChatLieu chatLieu, BindingResult result, Model model,
                              @ModelAttribute("mauSac")MauSac mauSac, @ModelAttribute("size")Size size){
        UUID uuid = UUID.randomUUID();
        kieuDang.setId(uuid);
        kieuDang.setTrangThai(1);
        kieuDang.setNgayTao(java.util.Calendar.getInstance().getTime());
        iFormDangService.add(kieuDang);
        return "redirect:/admin/san-pham/new";
    }

    @PostMapping("/modal-add-size")
    public String addSize(@ModelAttribute("danhMuc") DanhMuc danhMuc, @ModelAttribute("kieuDang")KieuDang kieuDang,
                          @ModelAttribute("chatLieu")ChatLieu chatLieu, BindingResult result, Model model,
                          @ModelAttribute("mauSac")MauSac mauSac, @ModelAttribute("size")Size size){
        UUID id = UUID.randomUUID();
        size.setId(String.valueOf(id));
        size.setNgayTao(java.util.Calendar.getInstance().getTime());
        size.setTrangThai(1);
        sizeService.add(size);
        return "redirect:/admin/san-pham/new";
    }

    @PostMapping("/modal-add-mau-sac")
    public String addMauSac(@ModelAttribute("danhMuc") DanhMuc danhMuc, @ModelAttribute("kieuDang")KieuDang kieuDang,
                            @ModelAttribute("chatLieu")ChatLieu chatLieu, BindingResult result, Model model,
                            @ModelAttribute("mauSac")MauSac mauSac, @ModelAttribute("size")Size size){
        UUID uuid = UUID.randomUUID();
        mauSac.setId(uuid);
        mauSac.setTrangThai(1);
        mauSac.setNgayTao(java.util.Calendar.getInstance().getTime());
        mauSacService.add(mauSac);
        return "redirect:/admin/san-pham/new";
    }

    @GetMapping("/stop/{id}")
    public String stop(@PathVariable("id") UUID id){
        SanPham sp = iSanPhamService.getOne(id);
        sp.setTrangThai(1);
        chiTietSanPhamService.updateTT_0(id);
        sp.setNgaySua(java.util.Calendar.getInstance().getTime());
        iSanPhamService.save(sp);
        return "redirect:/admin/san-pham/hien-thi";
    }

    @GetMapping("/stop-ctsp/{id}")
    public String stopCTSP(@PathVariable("id") String idCt, @RequestParam String idSP, @RequestParam("tt") Integer tt){
        String url= chiTietSanPhamService.save2(idCt,idSP,tt);
        return url;
    }

}