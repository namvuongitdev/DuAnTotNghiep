package com.example.web.controller;

import com.example.web.service.InHoaDonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
public class InHoaDonRestController {
    @Autowired
    InHoaDonService inHoaDonService;

    @RequestMapping("/admin/in-hoa-don/{hoaDonId}")
    public ResponseEntity<byte[]> generatePdf(@PathVariable UUID hoaDonId) {
        return inHoaDonService.generatePdf(hoaDonId);
    }
}
