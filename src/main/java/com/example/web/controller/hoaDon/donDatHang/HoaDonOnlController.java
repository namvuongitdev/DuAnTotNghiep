package com.example.web.controller.hoaDon.donDatHang;

import com.example.web.model.HoaDon;
import com.example.web.model.HoaDonChiTiet;
import com.example.web.response.HoaDonFilter;
import com.example.web.service.IHoaDonChiTietService;
import com.example.web.service.IHoaDonService;
import com.oracle.wls.shaded.org.apache.xpath.operations.Number;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/admin/hoa-don-onl")
public class HoaDonOnlController {

    @Autowired
    private IHoaDonService hoaDonService;

    @Autowired
    private IHoaDonChiTietService hoaDonChiTietService;

    private String url;

    @GetMapping("/cho-xac-nhan/hien-thi")
    public String choXacNhan(Model model,@ModelAttribute("hoaDonFillter") HoaDonFilter filter){
        List<HoaDon> lstHd =new ArrayList<>();
        for (int i = 0; i <= hoaDonService.getAll().size()-1; i++) {
            if (hoaDonService.getAll().get(i).getLoaiHoaDon()
                    && hoaDonService.getAll().get(i).getTrangThai()==1){
                lstHd.add(hoaDonService.getAll().get(i));
            }
        }
        model.addAttribute("lst", lstHd);
        model.addAttribute("fillter", filter);
        model.addAttribute("contentPage", "choXacNhan.jsp");
        return "quanLyHoaDon/hoaDonOnline/hoa-don-onl";
    }

    @GetMapping("/cho-giao-hang/hien-thi")
    public String choGiaoHang(Model model,@ModelAttribute("hoaDonFillter") HoaDonFilter filter){
        List<HoaDon> lstHd =new ArrayList<>();
        for (int i = 0; i <= hoaDonService.getAll().size()-1; i++) {
            if (hoaDonService.getAll().get(i).getLoaiHoaDon()
                    && hoaDonService.getAll().get(i).getTrangThai()==2){
                lstHd.add(hoaDonService.getAll().get(i));
            }
        }
        model.addAttribute("lst", lstHd);
        model.addAttribute("fillter", filter);
        model.addAttribute("contentPage", "choGiaoHang.jsp");
        return "quanLyHoaDon/hoaDonOnline/hoa-don-onl";
    }

    @GetMapping("/dang-giao/hien-thi")
    public String dangGiao(Model model,@ModelAttribute("hoaDonFillter") HoaDonFilter filter){
        List<HoaDon> lstHd =new ArrayList<>();
        for (int i = 0; i <= hoaDonService.getAll().size()-1; i++) {
            if (hoaDonService.getAll().get(i).getLoaiHoaDon()
                    && hoaDonService.getAll().get(i).getTrangThai()==3){
                lstHd.add(hoaDonService.getAll().get(i));
            }
        }
        model.addAttribute("lst", lstHd);
        model.addAttribute("fillter", filter);
        model.addAttribute("contentPage", "dangGiao.jsp");
        return "quanLyHoaDon/hoaDonOnline/hoa-don-onl";
    }

    @GetMapping("/da-giao/hien-thi")
    public String daGiao(Model model,@ModelAttribute("hoaDonFillter") HoaDonFilter filter){
        List<HoaDon> lstHd =new ArrayList<>();
        for (int i = 0; i <= hoaDonService.getAll().size()-1; i++) {
            if (hoaDonService.getAll().get(i).getLoaiHoaDon()
                    && hoaDonService.getAll().get(i).getTrangThai()==6){
                lstHd.add(hoaDonService.getAll().get(i));
            }
        }
        model.addAttribute("lst", lstHd);
        model.addAttribute("fillter", filter);
        model.addAttribute("contentPage", "daGiao.jsp");
        return "quanLyHoaDon/hoaDonOnline/hoa-don-onl";
    }

    @GetMapping("/da-huy/hien-thi")
    public String daHuy(Model model,@ModelAttribute("hoaDonFillter") HoaDonFilter filter){
        List<HoaDon> lstHd =new ArrayList<>();
        for (int i = 0; i <= hoaDonService.getAll().size()-1; i++) {
            if (hoaDonService.getAll().get(i).getLoaiHoaDon()
                    && hoaDonService.getAll().get(i).getTrangThai()==5){
                lstHd.add(hoaDonService.getAll().get(i));
            }
        }
        model.addAttribute("lst", lstHd);
        model.addAttribute("fillter", filter);
        model.addAttribute("contentPage", "daHuy.jsp");
        return "quanLyHoaDon/hoaDonOnline/hoa-don-onl";
    }
    @GetMapping("/detail/{id}")
    public String detail(Model model,
                         @RequestParam(defaultValue = "0") Integer page,
                         @PathVariable("id") String id){
        Page<HoaDonChiTiet> lst = hoaDonService.getHoaDonChiTiet(UUID.fromString(id),page,5);
        Integer tongTien = 0;
        for (int i = 0; i <= lst.getContent().size()-1; i++) {
            tongTien+=lst.getContent().get(i).getSoLuong()*lst.getContent().get(i).getChiTietSanPham().getSanPham().getGiaBan().intValue();
        }
        model.addAttribute("hdc",tongTien);
        model.addAttribute("hd",hoaDonService.getOne(id));
        model.addAttribute("lst1",lst.getContent());
        return "quanLyHoaDon/chiTietHoaDonOnline/CTChoXacNhan";
    }

    @GetMapping("/update")
    public String updateHoaDonChiTiet(@RequestParam("ctsp") String idCTSP,
                                      @RequestParam("soLuong") String soLuong,
                                      @RequestParam("idHD") String idHD,
                                      @RequestParam(defaultValue = "0") Integer page) {
        url = hoaDonChiTietService.addSanPhamHoaDonChiTietKhiUpdate(idCTSP,idHD,Integer.parseInt(soLuong));
        Page<HoaDonChiTiet> lst = hoaDonService.getHoaDonChiTiet(UUID.fromString(idHD),page,5);
        Integer tongTien = 0;
        for (int i = 0; i <= lst.getContent().size()-1; i++) {
            tongTien+=lst.getContent().get(i).getSoLuong()*lst.getContent().get(i).getChiTietSanPham().getSanPham().getGiaBan().intValue();
        }
        HoaDon hoaDon = hoaDonService.getOne(idHD);
        hoaDon.setId(UUID.fromString(idHD));
        hoaDon.setTongTien(BigDecimal.valueOf(tongTien));
        hoaDonService.updateHoaDonById(hoaDon);
        return url;
    }
    @GetMapping("/update-so-luong/{id}")
    public String updateSoLuongSanPhamHoaDonChiTiet2(@RequestParam("hdct") String idHdct, @RequestParam("soLuong") String soLuong,
                                                     @PathVariable("id") String id,
                                                     @RequestParam(defaultValue = "0") Integer page) {
        url = hoaDonChiTietService.updateSoLuongSanPhamHoaDonChiTietKhiUpdate(idHdct,soLuong);
        Page<HoaDonChiTiet> lst = hoaDonService.getHoaDonChiTiet(UUID.fromString(id),page,5);
        Integer tongTien = 0;
        for (int i = 0; i <= lst.getContent().size()-1; i++) {
            tongTien+=lst.getContent().get(i).getSoLuong()*lst.getContent().get(i).getChiTietSanPham().getSanPham().getGiaBan().intValue();
        }
        HoaDon hoaDon = hoaDonService.getOne(id);
        hoaDon.setId(UUID.fromString(id));
        hoaDon.setTongTien(BigDecimal.valueOf(tongTien));
        hoaDonService.updateHoaDonById(hoaDon);
        return url;
    }

    @GetMapping("/delete/{id}")
    public String deleteSanPhamHoaDonDonChiTiet(@PathVariable("id") String id,
                                                @RequestParam("idHdct")String idHdct,
                                                @RequestParam(defaultValue = "0") Integer page) {
        url = hoaDonChiTietService.deleteSanPhamHoaDon2(idHdct);
        Page<HoaDonChiTiet> lst = hoaDonService.getHoaDonChiTiet(UUID.fromString(id),page,5);
        Integer tongTien = 0;
        for (int i = 0; i <= lst.getContent().size()-1; i++) {
            tongTien+=lst.getContent().get(i).getSoLuong()*lst.getContent().get(i).getChiTietSanPham().getSanPham().getGiaBan().intValue();
        }
        HoaDon hoaDon = hoaDonService.getOne(id);
        hoaDon.setId(UUID.fromString(id));
        hoaDon.setTongTien(BigDecimal.valueOf(tongTien));
        hoaDonService.updateHoaDonById(hoaDon);
        return url;
    }
    @GetMapping("xac-nhan/{id}")
    public String xacNhan(Model model,
                          @PathVariable("id")String id,
                          @RequestParam("trangThai")String tt){
        Integer status=0;
        HoaDon hoaDon=hoaDonService.getOne(id);
        if (hoaDon.getTrangThai()==1){
            status=1;
        }else if (hoaDon.getTrangThai()==2){
            status=2;
        }else if (hoaDon.getTrangThai()==3){
            status=3;
        }else if (hoaDon.getTrangThai()==6){
            status=6;
        }else {
            status=5;
        }
        hoaDon.setTrangThai(Integer.parseInt(tt));
        url=hoaDonService.updateStatusHoaDonById(hoaDon,String.valueOf(status));
        return url;
    }
}
