package com.example.web.service;

import org.springframework.http.ResponseEntity;

import java.util.UUID;

public interface InHoaDonService {
    //in hóa đơn
    ResponseEntity<byte[]> generatePdf(UUID hoaDonId);
}
