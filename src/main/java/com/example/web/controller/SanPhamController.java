package com.example.web.controller;
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
import java.util.Date;
import java.util.UUID;

@Controller
@RequestMapping("/san-pham")
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
    private HttpServletRequest request;

    private Page<SanPham> sanPhamPage = null;

    @GetMapping(value = "/hien-thi")
    public String hienThi(Model model, @RequestParam(defaultValue = "1") Integer page) {
        Pageable pageable = PageRequest.of(page - 1, 10);
        sanPhamPage = iSanPhamService.findAll(pageable);
        model.addAttribute("listSanPham", sanPhamPage);
        danhSachThuocTinhSanPham(model);
        model.addAttribute("filterSanPham", new SanPhamFilter());
        model.addAttribute("url", "/san-pham/hien-thi?page=");
        return "quanLySanPham/sanpham/san-pham";
    }

    @GetMapping("/filter")
    public String filterSanPham(@RequestParam(defaultValue = "1") Integer page,
                                @ModelAttribute("filterSanPham") SanPhamFilter filter,
                                Model model) {
        Pageable pageable = PageRequest.of(page - 1, 10);
        sanPhamPage = iSanPhamService.sanPhamFilter(filter, pageable);
        String url = "/san-pham/filter?" + request.getQueryString().replaceAll("[&?]page.*?(?=&|\\?|$)", "") + "&page=";
        model.addAttribute("filter", filter);
        model.addAttribute("listSanPham", sanPhamPage);
        danhSachThuocTinhSanPham(model);
        model.addAttribute("url", url);
        return "quanLySanPham/sanpham/san-pham";
    }

    @GetMapping({"/api-hien-thi"})
    @ResponseBody
    public Page<SanPham> apiSanPham(@RequestParam Integer page ,@RequestParam(required = false) String value) {
        Page listSanPham = null;
        Pageable pageable = PageRequest.of(page - 1, 10);
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
        model.addAttribute("listKichCo", sizeService.getAll());
        model.addAttribute("listMauSac", mauSacService.getAll());
        model.addAttribute("listChatLieu", iChatLieuService.getAll());
        model.addAttribute("listFromDang", iFormDangService.getAll());
        model.addAttribute("listDanhMuc", danhMucService.getAll());
        model.addAttribute("sanPham", new SanPham());
        return "quanLySanPham/sanpham/new-san-pham";
    }

    @PostMapping(value = "/add")
    public String addSanPham(@Valid @ModelAttribute("sanPham") SanPham sanPham, BindingResult result, @RequestParam(required = false) String id) {
        if (result.hasErrors()) {
            return "redirect:/san-pham/new";
        } else {
            Date date = java.util.Calendar.getInstance().getTime();
            if (!id.isEmpty()) {
                SanPham sp = iSanPhamService.getOne(UUID.fromString(id));
                sanPham.setId(sp.getId());
                sanPham.setMa(sp.getMa());
                sanPham.setNgayTao(sp.getNgayTao());
                sanPham.setNgaySua(date);
                iSanPhamService.save(sanPham);
            } else {
                String maKM = "SP" + (iSanPhamService.getAll().size() + 1);
                sanPham.setMa(maKM);
                sanPham.setNgayTao(date);
                iSanPhamService.save(sanPham);
            }
        }
        return "redirect:/san-pham/hien-thi/" + sanPham.getId();
    }

    @GetMapping(value = "/hien-thi/{id}")
    public String getSanPham(@PathVariable String id, Model model,
                             @ModelAttribute("danhMuc") DanhMuc danhMuc, @ModelAttribute("kieuDang")KieuDang kieuDang,
                             @ModelAttribute("chatLieu")ChatLieu chatLieu, @ModelAttribute("mauSac")MauSac mauSac,
                             @ModelAttribute("size")Size size) {
        SanPham sanPham = iSanPhamService.getOne(UUID.fromString(id));
        danhSachThuocTinhSanPham(model);
        model.addAttribute("listChiTietSanPhamBySP", chiTietSanPhamService.getChiTietSanPham(id));
        model.addAttribute("sanPham", new SanPham());
        model.addAttribute("chiTietSanPham", new ChiTietSanPham());
        model.addAttribute("sp", sanPham);
        return "quanLySanPham/sanpham/new-san-pham";
    }

    @GetMapping(value = "/add-anh-mac-dinh")
    public String addAnhMacDinhSanPham(@RequestParam String img, @RequestParam String idSP) {
        SanPham sanPham = iSanPhamService.getOne(UUID.fromString(idSP));
        sanPham.setImg(img);
        iSanPhamService.save(sanPham);
        return "redirect:/san-pham/hien-thi/" + sanPham.getId();
    }


    public void danhSachThuocTinhSanPham(Model model) {
        model.addAttribute("listChatLieu", iChatLieuService.getAll());
        model.addAttribute("listFromDang", iFormDangService.getAll());
        model.addAttribute("listKichCo", sizeService.getAll());
        model.addAttribute("listMuaSac", mauSacService.getAll());
        model.addAttribute("listDanhMuc", danhMucService.getAll());
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
        return "redirect:/san-pham/new";
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
        return "redirect:/san-pham/new";
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
        return "redirect:/san-pham/new";
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
        return "redirect:/san-pham/new";
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
        return "redirect:/san-pham/new";
    }

    @GetMapping("/stop/{id}")
    public String stop(@PathVariable("id") UUID id){
        SanPham sp = iSanPhamService.getOne(id);
        sp.setTrangThai(1);
        sp.setNgaySua(java.util.Calendar.getInstance().getTime());
        iSanPhamService.save(sp);
        return "redirect:/san-pham/hien-thi";
    }

    @GetMapping("/stop-ctsp/{id}")
    public String stopCTSP(@PathVariable("id") UUID id, @RequestParam String idSP){
        ChiTietSanPham ctsp = chiTietSanPhamService.getOne(id);
        ctsp.setTrangThai(0);
        chiTietSanPhamService.save(ctsp);
        return "redirect:/san-pham/hien-thi/" + idSP;
    }

}
