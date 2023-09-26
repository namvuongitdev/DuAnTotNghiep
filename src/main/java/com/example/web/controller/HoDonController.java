package com.example.web.controller;
import com.example.web.model.TrangThaiHoaDon;
import com.example.web.service.IHoaDonChiTietService;
import com.example.web.service.IHoaDonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/hoa-don")
public class HoDonController {

    private String url;

    @Autowired
    private IHoaDonService hoaDonService;

    @Autowired
    private IHoaDonChiTietService hoaDonChiTietService;

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
    public String getHoaDon(Model model, @RequestParam("idHD") String id) {
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
}
