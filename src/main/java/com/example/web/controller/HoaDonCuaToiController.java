package com.example.web.controller;

import com.example.web.model.HoaDon;
import com.example.web.model.HoaDonChiTiet;
import com.example.web.model.KhachHang;
import com.example.web.service.IGioHangOnllineService;
import com.example.web.service.IHoaDonChiTietService;
import com.example.web.service.IHoaDonService;
import com.example.web.service.IKhachHangService;
import com.example.web.service.ILichSuHoaDonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.Principal;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/cuaToi")
public class HoaDonCuaToiController {

    @Autowired
    private IHoaDonService hoaDonService;

    @Autowired
    private IHoaDonChiTietService hoaDonChiTietService;

    @Autowired
    private IGioHangOnllineService iGioHangOnllineService;

    @Autowired
    private IKhachHangService iKhachHangService;

    private KhachHang khachHang;


    @GetMapping("/donHangAll")
    public String donHangAll(Principal principal, Model model, @RequestParam(defaultValue = "1") Integer page){
        khachHang = iKhachHangService.findByEmailOrAndTaiKhoan(principal.getName());
        Sort sort = Sort.by("ngayTao").descending();
        Pageable pageable = PageRequest.of(page - 1, 10, sort);
        model.addAttribute("count", iGioHangOnllineService.countSoLuongSPTrongGioHang(khachHang.getId()));
        model.addAttribute("listHd", hoaDonService.findHoaDonByTaiKhoan(principal.getName(), pageable));
        model.addAttribute("url", "/cuaToi/donHangAll?page=");
        return "donHangOnline/danhSach/donHangAll";
    }

    @GetMapping("/donHangChoXacNhan")
    public String donHangChoXacNhan(Principal principal, Model model, @RequestParam(defaultValue = "1") Integer page){
        Sort sort = Sort.by("ngayTao").descending();
        Pageable pageable = PageRequest.of(page - 1, 10, sort);
        model.addAttribute("count", iGioHangOnllineService.countSoLuongSPTrongGioHang(khachHang.getId()));
        model.addAttribute("listHd", hoaDonService.findHoaDonByTrangThai(principal.getName(), 1, pageable));
        model.addAttribute("url", "/cuaToi/donHangChoXacNhan?page=");
        return "donHangOnline/danhSach/donHangChoXacNhan";
    }

    @GetMapping("/donHangChoGiaoHang")
    public String donHangChoGiaoHang(Principal principal, Model model, @RequestParam(defaultValue = "1") Integer page){
        Sort sort = Sort.by("ngayTao").descending();
        model.addAttribute("count", iGioHangOnllineService.countSoLuongSPTrongGioHang(khachHang.getId()));
        Pageable pageable = PageRequest.of(page - 1, 10, sort);
        model.addAttribute("listHd", hoaDonService.findHoaDonByTrangThai(principal.getName(), 2, pageable));
        model.addAttribute("url", "/cuaToi/donHangChoGiaoHang?page=");
        return "donHangOnline/danhSach/donHangChoGiaoHang";
    }

    @GetMapping("/donHangDangGiao")
    public String donHangDangGiao(Principal principal, Model model, @RequestParam(defaultValue = "1") Integer page){
        Sort sort = Sort.by("ngayTao").descending();
        Pageable pageable = PageRequest.of(page - 1, 10, sort);
        model.addAttribute("count", iGioHangOnllineService.countSoLuongSPTrongGioHang(khachHang.getId()));
        model.addAttribute("listHd", hoaDonService.findHoaDonByTrangThai(principal.getName(), 3, pageable));
        model.addAttribute("url", "/cuaToi/donHangDangGiao?page=");
        return "donHangOnline/danhSach/donHangDangGiao";
    }

    @GetMapping("/donHangDaNhan")
    public String donHangDaNhan(Principal principal, Model model, @RequestParam(defaultValue = "1") Integer page){
        Sort sort = Sort.by("ngayTao").descending();
        Pageable pageable = PageRequest.of(page - 1, 10, sort);
        model.addAttribute("count", iGioHangOnllineService.countSoLuongSPTrongGioHang(khachHang.getId()));
        model.addAttribute("listHd", hoaDonService.findHoaDonByTrangThai(principal.getName(), 6, pageable));
        model.addAttribute("url", "/cuaToi/donHangDaNhan?page=");
        return "donHangOnline/danhSach/donHangDaNhan";
    }

    @GetMapping("/donHangDaHuy")
    public String donHangDaHuy(Principal principal, Model model, @RequestParam(defaultValue = "1") Integer page){
        Sort sort = Sort.by("ngayTao").descending();
        Pageable pageable = PageRequest.of(page - 1, 10, sort);
        model.addAttribute("count", iGioHangOnllineService.countSoLuongSPTrongGioHang(khachHang.getId()));
        model.addAttribute("listHd", hoaDonService.findHoaDonByTrangThai(principal.getName(), 5, pageable));
        model.addAttribute("url", "/cuaToi/donHangDaHuy?page=");
        return "donHangOnline/danhSach/donHangDaHuy";
    }

    @GetMapping("/donHangDaThanhToan")
    public String donHangDaThanhToan(Principal principal, Model model, @RequestParam(defaultValue = "1") Integer page){
        Sort sort = Sort.by("ngayTao").descending();
        Pageable pageable = PageRequest.of(page - 1, 10, sort);
        model.addAttribute("count", iGioHangOnllineService.countSoLuongSPTrongGioHang(khachHang.getId()));
        model.addAttribute("listHd", hoaDonService.findHoaDonByTrangThai(principal.getName(), 4, pageable));
        model.addAttribute("url", "/cuaToi/donHangDaThanhToan?page=");
        return "donHangOnline/danhSach/donHangDaThanhToan";
    }

    @GetMapping("/chiTietDonHang/{idHD}")
    public String chiTietDonHang(@PathVariable UUID idHD, Model model){
        model.addAttribute("count", iGioHangOnllineService.countSoLuongSPTrongGioHang(khachHang.getId()));
        List<HoaDonChiTiet> hoaDonChiTiet = hoaDonChiTietService.getAllByIdHoaDon(idHD);
        model.addAttribute("listHDCT", hoaDonChiTiet);
        model.addAttribute("hd", hoaDonService.getOne(String.valueOf(idHD)));
        return "donHangOnline/chiTiet/chiTietDonHang";
    }

    @PostMapping("/update-trang-thai")
    public String updateTrangThaiDonHang(@RequestParam Integer trangThai , @RequestParam String idHD , @RequestParam String ghiChuXacNhan , RedirectAttributes attributes){
        Integer isCheck = hoaDonService.xacNhanDonHangCuaToi(trangThai , UUID.fromString(idHD) , ghiChuXacNhan);
        if(isCheck == 1){
            attributes.addFlashAttribute("success", "Xác nhận đơn hàng thành công");
        }else if (isCheck == 2){
            attributes.addFlashAttribute("error", "Bạn chưa nhập phí vận chuyển");
        }else if(isCheck == 3){
            attributes.addFlashAttribute("error", "Hủy đơn hàng thành công");
        }else{
            attributes.addFlashAttribute("error", "Không tìm thấy đơn hàng");
        }
        return "redirect:/cuaToi/donHangAll";
    }
}
