package com.example.web.controller;
import com.example.web.model.HoaDon;
import com.example.web.model.TrangThaiHoaDon;
import com.example.web.request.HoaDonRequest;
import com.example.web.response.SanPhamFilter;
import com.example.web.service.DanhMucService;
import com.example.web.service.IChatLieuService;
import com.example.web.service.IFormDangService;
import com.example.web.service.IHoaDonChiTietService;
import com.example.web.service.IHoaDonService;
import com.example.web.service.IMauSacService;
import com.example.web.service.ISanPhamService;
import com.example.web.service.SizeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/hoa-don")
public class HoDonController {

    private String url;

    @Autowired
    private IHoaDonService hoaDonService;

    @Autowired
    private IHoaDonChiTietService hoaDonChiTietService;

    @Autowired
    private IFormDangService iFormDangService;

    @Autowired
    private IChatLieuService iChatLieuService;

    @Autowired
    private DanhMucService danhMucService;

    @Autowired
    private ISanPhamService sanPhamService;

    @GetMapping("/hien-thi-hoa-cho")
    public String hoaDon(Model model, @RequestParam(defaultValue = "1") Integer page) {
        if (page < 1) page = 1;
        Pageable pageable = PageRequest.of(page - 1, 5);
        Page<Object[]> hoaDons = hoaDonService.getAllByTrangThai(TrangThaiHoaDon.CHO_XAC_NHAN.getValue(), pageable);
        model.addAttribute("pageNo", page);
        model.addAttribute("hoaDons", hoaDons);
        model.addAttribute("page", page != 1 ? page * 5 - 4 : page);
        return "banHangTaiQuay/hoa-don-cho";
    }

    @PostMapping(value = "/create")
    public String creatHoaDon() {
        url = hoaDonService.addHoaDon();
        return url;
    }

    @GetMapping(value = "/detail")
    public String getHoaDon(Model model, @RequestParam("idHD") String id , @RequestParam(defaultValue = "1") Integer page) {
        model.addAttribute("filter" , new SanPhamFilter());
        model.addAttribute("listChatLieu", iChatLieuService.getAll());
        model.addAttribute("listFromDang", iFormDangService.getAll());
        model.addAttribute("listDanhMuc", danhMucService.getAll());
        model.addAttribute("request", new HoaDonRequest());
        url = hoaDonService.getHoaDonById(model , id);
        return url;
    }

    @GetMapping("/add-san-pham")
    public String addSanPham(@RequestParam("ctsp") String idCTSP, @RequestParam("soLuong") String soLuong, @RequestParam("idHD") String idHD) {
        url = hoaDonChiTietService.addHoaDonChiTiet(idCTSP, idHD, Integer.parseInt(soLuong));
        return url;
    }

    @GetMapping("/delete")
    public String deleteSanPhamHoaDonDonChiTiet(@RequestParam String idHDCT) {
        url = hoaDonChiTietService.deleteSanPhamHoaDon(idHDCT);
        return url;
    }

    @GetMapping("/update-san-pham")
    public String updateSoLuongSanPhamHoaDonChiTiet(@RequestParam("idHD") String idHDCT, @RequestParam String soLuong) {
        url = hoaDonChiTietService.updateHoaDonChiTiet(idHDCT, soLuong);
        return url;
    }

    @GetMapping("/huy")
    public String huyHoaDon(@RequestParam String idHD , @RequestParam(required = false) String ghiChu){
        url = hoaDonService.updateHoaDonTrangThai(idHD , ghiChu);
        return url;
    }

    @PostMapping("/thanh-toan")
    private String xacNhanThoan(@RequestParam String idHD , @ModelAttribute("request") HoaDonRequest hoaDonRequest , RedirectAttributes attributes){
        hoaDonRequest.setHoaDon(idHD);
        url = hoaDonService.thanhToan(hoaDonRequest , attributes);
        return url;
    }
}
