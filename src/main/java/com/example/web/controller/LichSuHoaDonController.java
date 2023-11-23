package com.example.web.controller;

import com.example.web.model.LichSuHoaDon;
import com.example.web.service.ILichSuHoaDonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/admin")
public class LichSuHoaDonController {

    @Autowired
    private ILichSuHoaDonService lichSuHoaDonService;

    @GetMapping(value = "/lich-su-hoa-don")
    @ResponseBody
    public List<LichSuHoaDon> getAllLichSuHoaDon(@RequestParam String idHD) {
        List<LichSuHoaDon> list = lichSuHoaDonService.getListById(UUID.fromString(idHD));
        return list;
    }
}
