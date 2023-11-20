package com.example.web.controller;

import com.example.web.model.HoaDon;
import com.example.web.model.HoaDonChiTiet;
import com.example.web.service.IHoaDonChiTietService;
import com.example.web.service.IHoaDonService;
import com.example.web.service.ILichSuHoaDonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
    private ILichSuHoaDonService lichSuHoaDonService;


    @GetMapping("/donHangAll")
    public String donHangAll(Principal principal, Model model, @RequestParam(defaultValue = "1") Integer page){
        Sort sort = Sort.by("ngayTao").descending();
        Pageable pageable = PageRequest.of(page - 1, 10, sort);
        model.addAttribute("listHd", hoaDonService.findHoaDonByTaiKhoan(principal.getName(), pageable));
        model.addAttribute("url", "/cuaToi/donHangAll?page=");
        return "donHangOnline/danhSach/donHangAll";
    }

    @GetMapping("/donHangChoXacNhan")
    public String donHangChoXacNhan(Principal principal, Model model, @RequestParam(defaultValue = "1") Integer page){
        Sort sort = Sort.by("ngayTao").descending();
        Pageable pageable = PageRequest.of(page - 1, 10, sort);
        model.addAttribute("listHd", hoaDonService.findHoaDonByTrangThai(principal.getName(), 1, pageable));
        model.addAttribute("url", "/cuaToi/donHangChoXacNhan?page=");
        return "donHangOnline/danhSach/donHangChoXacNhan";
    }

    @GetMapping("/donHangChoGiaoHang")
    public String donHangChoGiaoHang(Principal principal, Model model, @RequestParam(defaultValue = "1") Integer page){
        Sort sort = Sort.by("ngayTao").descending();
        Pageable pageable = PageRequest.of(page - 1, 10, sort);
        model.addAttribute("listHd", hoaDonService.findHoaDonByTrangThai(principal.getName(), 2, pageable));
        model.addAttribute("url", "/cuaToi/donHangChoGiaoHang?page=");
        return "donHangOnline/danhSach/donHangChoGiaoHang";
    }

    @GetMapping("/donHangDangGiao")
    public String donHangDangGiao(Principal principal, Model model, @RequestParam(defaultValue = "1") Integer page){
        Sort sort = Sort.by("ngayTao").descending();
        Pageable pageable = PageRequest.of(page - 1, 10, sort);
        model.addAttribute("listHd", hoaDonService.findHoaDonByTrangThai(principal.getName(), 3, pageable));
        model.addAttribute("url", "/cuaToi/donHangDangGiao?page=");
        return "donHangOnline/danhSach/donHangDangGiao";
    }

    @GetMapping("/donHangDaNhan")
    public String donHangDaNhan(Principal principal, Model model, @RequestParam(defaultValue = "1") Integer page){
        Sort sort = Sort.by("ngayTao").descending();
        Pageable pageable = PageRequest.of(page - 1, 10, sort);
        model.addAttribute("listHd", hoaDonService.findHoaDonByTrangThai(principal.getName(), 6, pageable));
        model.addAttribute("url", "/cuaToi/donHangDaNhan?page=");
        return "donHangOnline/danhSach/donHangDaNhan";
    }

    @GetMapping("/donHangDaHuy")
    public String donHangDaHuy(Principal principal, Model model, @RequestParam(defaultValue = "1") Integer page){
        Sort sort = Sort.by("ngayTao").descending();
        Pageable pageable = PageRequest.of(page - 1, 10, sort);
        model.addAttribute("listHd", hoaDonService.findHoaDonByTrangThai(principal.getName(), 5, pageable));
        model.addAttribute("url", "/cuaToi/donHangDaHuy?page=");
        return "donHangOnline/danhSach/donHangDaHuy";
    }

    @GetMapping("/chiTietDonHang/{idHD}")
    public String chiTietDonHang(@PathVariable UUID idHD, Model model){
        List<HoaDonChiTiet> hoaDonChiTiet = hoaDonChiTietService.getAllByIdHoaDon(idHD);
        Double thanhTien = 0.0;
        for (int i = 0; i < hoaDonChiTietService.getAllByIdHoaDon(idHD).size(); i++) {
            thanhTien += hoaDonChiTiet.get(i).getSoLuong() * hoaDonChiTiet.get(i).getChiTietSanPham().getSanPham().getGiaBan().doubleValue();
        }
        model.addAttribute("thanhTien", thanhTien);
        model.addAttribute("listHDCT", hoaDonChiTiet);

        model.addAttribute("hd", hoaDonService.getOne(String.valueOf(idHD)));
        return "donHangOnline/chiTiet/chiTietDonHang";
    }

    @GetMapping("/xac-nhan/{id}")
    public String xacNhan(Principal principal, @PathVariable("id")String id){
        HoaDon hoaDon=hoaDonService.getOne(id);
        hoaDon.setTrangThai(6);
        hoaDonService.updateStatusHoaDonById(hoaDon,"6");
        lichSuHoaDonService.add(principal.getName(),"Đơn hàng đã được giao thành công",hoaDon,null,"ok");
        return "redirect:/cuaToi/donHangAll";
    }

    @GetMapping("/huy-don/{id}")
    public String huyDon(Principal principal, @RequestParam(defaultValue = "0") Integer page,
                         @PathVariable("id")String id){
        Page<HoaDonChiTiet> lst = hoaDonService.getHoaDonChiTiet(UUID.fromString(id),page,5);
        for (int i = 0; i <= lst.getContent().size()-1; i++) {
            hoaDonChiTietService.deleteSanPhamHoaDon2(String.valueOf(lst.getContent().get(i).getId()));
        }
        HoaDon hoaDon=hoaDonService.getOne(id);
        hoaDon.setTrangThai(5);
        hoaDonService.updateStatusHoaDonById(hoaDon,"5");
        lichSuHoaDonService.add(principal.getName(),"Hủy hóa đơn",hoaDon,null,"ok");
        return "redirect:/cuaToi/donHangAll";
    }
}
