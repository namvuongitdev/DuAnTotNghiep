package com.example.web.controller;
import com.example.web.model.*;
import com.example.web.response.ChiTietOnllineResponse;
import com.example.web.response.ChiTietSanPhamResponse;
import com.example.web.response.SanPhamFilter;
import com.example.web.service.*;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
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


    private Page<SanPham> sanPhamPage = null;

    @GetMapping("/home")
    public String hienThi(Principal principal, HttpSession session, Model model, @RequestParam(defaultValue = "1") int page) {
        if (principal != null){
            String username = principal.getName();
            session.setAttribute("username", username);
        }
        Pageable pageable = PageRequest.of(page - 1, 10);
        sanPhamPage = iSanPhamService.findAll(pageable);
        model.addAttribute("listSanPham", sanPhamPage);
        danhSachThuocTinhSanPham(model);
        model.addAttribute("filterSanPham", new SanPhamFilter());
        model.addAttribute("url", "/san-pham/hien-thi?page=");
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
    public Page<SanPham> apiSanPham(@RequestParam Integer page ,@RequestParam(required = false) String value) {
        Page listSanPham = null;
        Pageable pageable = PageRequest.of(page - 1, 20);
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
        Pageable pageable = PageRequest.of(page - 1, 20);
        Page listSanPham = iSanPhamService.sanPhamFilter(filter ,pageable);
        return listSanPham;
    }

    @GetMapping("/thoi-trang-nam/vi")
    public String thoiTrangNam(Model model, @RequestParam(defaultValue = "1") int page) {
        if (page < 1) page = 1;
        Pageable pageable = PageRequest.of(page - 1, 12);
        boolean gioiTinh = true;
        Page<SanPham> list = iSanPhamService.findAllGender(pageable,gioiTinh);
        model.addAttribute("list", list);
        model.addAttribute("pageNo", page);
        model.addAttribute("page", page != 1 ? page * 5 - 4 : page);
        return "banHangOnlline/index";
    }

    @GetMapping("/thoi-trang-nu")
    public String thoiTrangNu(Model model, @RequestParam(defaultValue = "1") int page) {
        if (page < 1) page = 1;
        Pageable pageable = PageRequest.of(page - 1, 12);
        boolean gioiTinh = false;
        Page<SanPham> list = iSanPhamService.findAllGender(pageable,gioiTinh);
        model.addAttribute("list", list);
        model.addAttribute("pageNo", page);
        model.addAttribute("page", page != 1 ? page * 5 - 4 : page);
        return "banHangOnlline/index";
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

    @GetMapping("/so-luong/{idSP}/{idSize}")
    @ResponseBody
    public ChiTietOnllineResponse getSoLuong(@PathVariable (name = "idSP") String idSP,
                          @PathVariable (name = "idSize") String idSize,
                          @RequestParam(name = "color") String idMau ){
        System.out.println(idSize + "helo");
        ChiTietOnllineResponse listCT = iChiTietSanPhamService.getChiTietSanPhamByMauSac_IdAndSize_IdAndSanPham_Id1(UUID.fromString(idMau),idSize,UUID.fromString(idSP));
        return listCT;
    }


}
