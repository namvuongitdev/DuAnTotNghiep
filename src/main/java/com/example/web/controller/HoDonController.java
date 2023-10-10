package com.example.web.controller;
import com.example.web.model.HoaDon;
import com.example.web.model.TrangThaiHoaDon;
import com.example.web.service.IHoaDonChiTietService;
import com.example.web.service.IHoaDonService;
import com.example.web.model.HoaDonChiTiet;
import com.example.web.request.HoaDonRequest;
import com.example.web.response.HoaDonFilter;
import com.example.web.response.SanPhamFilter;
import com.example.web.service.ISanPhamService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/hoa-don")
public class HoDonController {

    private String url;

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private IHoaDonService hoaDonService;

    @Autowired
    private IHoaDonChiTietService hoaDonChiTietService;

    @Autowired
    private SanPhamController sanPhamController;

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
        sanPhamController.danhSachThuocTinhSanPham(model);
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

    @GetMapping("/loai-hoa-don")
    public String loaiHoaDon(@RequestParam String idHD , @RequestParam Boolean loaiHoaDon){
       HoaDon hoaDon =  hoaDonService.getOne(idHD);
       hoaDon.setLoaiHoaDon(loaiHoaDon);
       hoaDonService.add(hoaDon);
        return "redirect:/hoa-don/detail?idHD="+idHD;
    }

    @PostMapping("/thanh-toan")
    private String xacNhanThanhToan(@RequestParam String idHD , @RequestParam String  idKhachHang , @ModelAttribute("request") HoaDonRequest hoaDonRequest , RedirectAttributes attributes){
        hoaDonRequest.setHoaDon(idHD);
        hoaDonRequest.setIdKhachHang(idKhachHang);
        url = hoaDonService.thanhToan(hoaDonRequest , attributes);
        return url;
    }
    //    -----------------------------------------------------------
    @GetMapping("/hien-thi")
    public String hienThi(Model model, @RequestParam(defaultValue = "0") Integer page) {
        model.addAttribute("hoaDonFillter",new HoaDonFilter());
        model.addAttribute("lst",hoaDonService.pagination(page,10).getContent());
        model.addAttribute("currentPage",page);
        model.addAttribute("totalPage",hoaDonService.pagination(page,10).getTotalPages());
        return "quanLyHoaDon/hoa-don";
    }
    @GetMapping("/hien-thi/{page}")
    public String phanTrang(Model model, @PathVariable("page") Integer page) {
        model.addAttribute("hoaDonFillter",new HoaDonFilter());
        model.addAttribute("lst",hoaDonService.pagination(page,10).getContent());
        model.addAttribute("currentPage",page);
        model.addAttribute("totalPage",hoaDonService.pagination(page,10).getTotalPages());
        return "quanLyHoaDon/hoa-don";
    }
    @GetMapping("/filter")
    public String fillter(Model model,
                          @RequestParam(defaultValue = "0") Integer page,
                          @ModelAttribute("hoaDonFillter") HoaDonFilter filter) {
        Pageable pageable = PageRequest.of(page, 10);
        String url = "/hoa-don/filter?" + request.getQueryString().replaceAll("[&?]page.*?(?=&|\\?|$)", "") + "&page=";
        model.addAttribute("lst",hoaDonService.hoaDonFillter(filter,pageable).getContent());
        model.addAttribute("fillter",filter);
        System.out.println(filter.getDateBegin());
        return "quanLyHoaDon/hoa-don";
    }
    @GetMapping("/detail/{id}")
    public String detail(Model model,
                         @RequestParam(defaultValue = "0") Integer page,
                         @PathVariable("id") String id) {
        Page<HoaDonChiTiet> lst = hoaDonService.getHoaDonChiTiet(UUID.fromString(id),page,5);
        model.addAttribute("hd",hoaDonService.getOne(id));
        model.addAttribute("lst",lst.getContent());
        model.addAttribute("hoaDon",new HoaDon());
        model.addAttribute("currentPage",page);
        model.addAttribute("totalPage",lst.getTotalPages());
        return "quanLyHoaDon/chi-tiet-hoa-don";
    }


}
