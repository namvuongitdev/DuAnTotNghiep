package com.example.web.controller;

import com.example.web.response.GioHangOnllineResponse;
import com.example.web.service.IGioHangOnllineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Controller
@RequestMapping("gio-hang-onl")
public class GioHangOnllineController {
    @Autowired
    IGioHangOnllineService iGioHangOnllineService;

    @GetMapping()
    public String hienThi(Model model,@RequestParam(defaultValue = "1") Integer page){
        if (page < 1) page = 1;
        Pageable pageable = PageRequest.of(page - 1, 3);
        String idKH= "E1B9D3F3-A802-4E18-B36D-A338CC2366A2";
        Page<GioHangOnllineResponse> list = iGioHangOnllineService.findAll(pageable,UUID.fromString(idKH));
        String tong = iGioHangOnllineService.getTongTienTrongGio(UUID.fromString(idKH));
        model.addAttribute("tongTien",tong);
        model.addAttribute("list", list);
        model.addAttribute("pageNo", page);
        model.addAttribute("page", page != 1 ? page * 5 - 4 : page);
        return "gioHangOnlline/giohang";
    }

    @GetMapping("/cap-nhat-gio-hang/{idGioHangCT}")
    public String updateSoLuong(@RequestParam(name = "soLuong") String soLuong,
                                @PathVariable(name = "idGioHangCT") String idGioHangCT){
        iGioHangOnllineService.updateSoLuong(Integer.parseInt(soLuong),UUID.fromString(idGioHangCT));
        return "redirect:/gio-hang-onl";
    }

    @GetMapping("/xoa/{idGioHangCT}")
    public String xoa(@PathVariable(name = "idGioHangCT") String idGioHangCT){
        iGioHangOnllineService.delete(UUID.fromString(idGioHangCT));
        return "redirect:/gio-hang-onl";
    }
}
