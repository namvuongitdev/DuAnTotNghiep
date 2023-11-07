package com.example.web.controller;
import com.example.web.model.ChiTietSanPham;
import com.example.web.model.KhachHang;
import com.example.web.response.ChiTietResponse;
import com.example.web.response.GioHangOnllineResponse;
import com.example.web.service.IChiTietSanPhamService;
import com.example.web.service.IGioHangOnllineService;
import com.example.web.service.IKhachHangService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.UUID;

@Controller
@RequestMapping("gio-hang-onl")
public class GioHangOnllineController {
    @Autowired
    IGioHangOnllineService iGioHangOnllineService;

    @Autowired
    IChiTietSanPhamService iChiTietSanPhamService;

    @Autowired
    IKhachHangService iKhachHangService;

    @GetMapping()
    public String hienThi(Model model,@RequestParam(defaultValue = "1") Integer page){
        if (page < 1) page = 1;
        Pageable pageable = PageRequest.of(page - 1, 3);
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        KhachHang khachHang = iKhachHangService.findByEmailOrAndTaiKhoan(authentication.getName());
        Page<GioHangOnllineResponse> list = iGioHangOnllineService.findAll(pageable,khachHang.getId());
        String tong = iGioHangOnllineService.getTongTienTrongGio(khachHang.getId());
        model.addAttribute("tongTien",tong);
        model.addAttribute("list", list);
        model.addAttribute("pageNo", page);
        model.addAttribute("page", page != 1 ? page * 5 - 4 : page);
        return "gioHangOnlline/giohang";
    }

    @GetMapping("/cap-nhat-gio-hang/{soLuong}/{idGioHangCT}")
    public String updateSoLuong(@PathVariable(name = "soLuong") String soLuong,
                                @PathVariable(name = "idGioHangCT") String idGioHangCT){
        iGioHangOnllineService.updateSoLuong(Integer.parseInt(soLuong),UUID.fromString(idGioHangCT));
        return "redirect:/gio-hang-onl";
    }

    @GetMapping("/xoa/{idGioHangCT}")
    public String xoa(@PathVariable(name = "idGioHangCT") String idGioHangCT){
        iGioHangOnllineService.delete(UUID.fromString(idGioHangCT));
        return "redirect:/gio-hang-onl";
    }

    @GetMapping("/them-moi-gio-hang/{idSP}/{quantity}/{color}/{size}")
    public String themGioHang(
            @PathVariable(name = "idSP") String idSP,
            @PathVariable(value = "color") String idMau,
            @PathVariable(value = "size") String idSize,
            @PathVariable(name = "quantity") String soLuongThem,Model model){
        if(Integer.parseInt((soLuongThem))<=0){
            model.addAttribute("checkQuantity","Số lượng không hợp lệ.");
        }else {
            ChiTietResponse chiTietSanPham = iChiTietSanPhamService.getChiTietSanPhamByMauSac_IdAndSize_IdAndIdSP(UUID.fromString(idMau),idSize,UUID.fromString(idSP));
            ChiTietSanPham sanPham = new ChiTietSanPham(chiTietSanPham.getId(),chiTietSanPham.getSanPham(),chiTietSanPham.getSoLuong(),chiTietSanPham.getTrangThai(),chiTietSanPham.getQrCode(),
                    chiTietSanPham.getMauSac(),chiTietSanPham.getSize());
            iGioHangOnllineService.addGioHang(sanPham,Integer.parseInt(soLuongThem));
        }

        return "forward:/index/chi-tiet-san-pham-onl?id="+idSP;
    }
}
