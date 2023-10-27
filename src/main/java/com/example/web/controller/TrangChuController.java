package com.example.web.controller;
import com.example.web.model.*;
import com.example.web.response.*;
import com.example.web.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/index")
public class TrangChuController {

    @Autowired
    private IFormDangService iFormDangService;

    @Autowired
    private IChatLieuService iChatLieuService;

    @Autowired
    private SizeService sizeService;

    @Autowired
    private IMauSacService mauSacService;

    @Autowired
    private DanhMucService danhMucService;

    @Autowired
    private ISanPhamService iSanPhamService;

    @Autowired
    private IAnhService iAnhService;

    @Autowired IChiTietSanPhamService iChiTietSanPhamService;

    @Autowired
    IGioHangOnllineService iGioHangOnllineService;


    private Page<SanPham> sanPhamPage = null;

    private Page<SanPhamAndKhuyenMai> sanPhamKhuyenMaiPage = null;

    @GetMapping("/home")
    public String hienThi(Model model, @RequestParam(defaultValue = "1") int page) {
        Pageable pageable = PageRequest.of(page - 1, 10);
        sanPhamPage = iSanPhamService.findAll(pageable);
        System.out.println("1t"+sanPhamPage.toList().get(6));
        sanPhamKhuyenMaiPage = iSanPhamService.getALL(pageable);
        System.out.println("222t"+sanPhamKhuyenMaiPage.toList().get(6));
        model.addAttribute("listSanPham", sanPhamKhuyenMaiPage);
        danhSachThuocTinhSanPham(model);
        model.addAttribute("filterSanPham", new SanPhamFilter());
        model.addAttribute("url", "/api-hien-thi?page=");
        return "banHangOnlline/index";
    }

    public void danhSachThuocTinhSanPham(Model model) {
        model.addAttribute("listChatLieu", iChatLieuService.getAll());
        model.addAttribute("listFormDang", iFormDangService.getAll());
        model.addAttribute("listKichCo", sizeService.getAll());
        model.addAttribute("listMuaSac", mauSacService.getAll());
        model.addAttribute("listDanhMuc", danhMucService.getAll());
    }

    @GetMapping({"/api-hien-thi"})
    @ResponseBody
    public Page<SanPhamAndKhuyenMai> apiSanPham(@RequestParam Integer page ,@RequestParam(required = false) String value) {
        Page<SanPhamAndKhuyenMai> listSanPham = null;
        Pageable pageable = PageRequest.of(page - 1, 20);
        if(value.isEmpty()){
            listSanPham = iSanPhamService.getALL(pageable);
        }else{
            listSanPham =  iSanPhamService.getAllSanPhamAndKhuyenMaiByTenOrMa(value, page);
        }
        return listSanPham;
    }

    @PostMapping("/api-filter")
    @ResponseBody
    public Page<SanPham> filterSanPham(@RequestParam Integer page , @RequestBody SanPhamFilter filter) {
        Pageable pageable = PageRequest.of(page - 1, 20);
        Page listSanPham = iSanPhamService.sanPhamFilter(filter ,pageable);
        return listSanPham;
    }

    @GetMapping("/thoi-trang-nam")
    @ResponseBody
    public Page<SanPhamAndKhuyenMai> thoiTrangNam(Model model, @RequestParam(defaultValue = "1") int page) {
        boolean gioiTinh = true;
        Page listSanPham = null;
        Pageable pageable = PageRequest.of(page - 1, 5);
        listSanPham= iSanPhamService.findAllSanPhamKhuyenMaiGender(gioiTinh,page);
        return listSanPham;
    }

    @GetMapping("/thoi-trang-nu")
    @ResponseBody
    public Page<SanPhamAndKhuyenMai> thoiTrangNu(Model model, @RequestParam(defaultValue = "1") int page) {
        boolean gioiTinh = false;
        Page listSanPham = null;
        Pageable pageable = PageRequest.of(page - 1, 5);
        listSanPham = iSanPhamService.findAllSanPhamKhuyenMaiGender(gioiTinh,page);
        return listSanPham;
    }

    @GetMapping("/chi-tiet-san-pham-onl")
    public String chiTiet(Model model,@RequestParam (name = "id") String
                          idSanPham){
        List<Anh> listAnh = iAnhService.getTenAnh(UUID.fromString(idSanPham));
        List<Anh> distinctListAnh = listAnh.stream()
                .collect(Collectors.toMap(Anh::getTen, anh -> anh, (existing, replacement) -> existing))
                .values()
                .stream()
                .collect(Collectors.toList());
        SanPham sanPham = iSanPhamService.getOne(UUID.fromString(idSanPham));
        List<SanPham> listSanPham = iSanPhamService.theoTen(sanPham.getChatLieu().getId(),sanPham.getKieuDang().getId(),sanPham.getDanhMuc().getId());
        List<ChiTietSanPham> listCT = iChiTietSanPhamService.listCTSPTheoIdSP(UUID.fromString(idSanPham));
        List<MauSac> listMS = mauSacService.getTheoCTSP(UUID.fromString(idSanPham));
        List<Size> listSize = sizeService.getTheoCT(UUID.fromString(idSanPham));
        model.addAttribute("listMau",listMS);
        model.addAttribute("listSize",listSize);
        model.addAttribute("sanPham",sanPham);
        model.addAttribute("listAnh",distinctListAnh);
        model.addAttribute("listSanPham",listSanPham);
        return "banHangOnlline/chitiet";
    }

    @GetMapping("/so-luong/{idSize}/{color}")
    @ResponseBody
    public ChiTietOnllineResponse getSoLuong(@RequestParam (name = "id") String idSP,
                          @PathVariable (name = "idSize") String idSize,
                          @PathVariable(name = "color") String idMau ){
        ChiTietOnllineResponse listCT = iChiTietSanPhamService.getChiTietSanPhamByMauSac_IdAndSize_IdAndSanPham_Id1(UUID.fromString(idMau),idSize,UUID.fromString(idSP));
        return listCT;
    }

    @GetMapping("/tinh-trang/{idSP}")
    @ResponseBody
    public String getTinhTrang(@RequestParam(name ="size") String size,@RequestParam(name ="color") String color,@PathVariable (name = "idSP") String idSP){
        ChiTietOnllineResponse chiTiet = iChiTietSanPhamService.getChiTietSanPhamByMauSac_IdAndSize_IdAndSanPham_Id1(UUID.fromString(color),size,UUID.fromString(idSP));
        return chiTiet.getTrangThai()==1 ? "Kinh Doanh" :"Ngừng Kinh Doanh";
    }

    @GetMapping(value = "/chi-tiet")
    @ResponseBody
    public List<ChiTietSanPhamResponse> getChiTietSanPham(@RequestParam("id") String id) {
        List<ChiTietSanPhamResponse> chiTietSanPhams = iChiTietSanPhamService.getCTSP(id);
        return chiTietSanPhams;
    }



}
