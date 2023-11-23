package com.example.web.service.impl;

import com.example.web.Config.status.HoaDonStatus;
import com.example.web.Config.status.PhuongThucThanhToanStatus;
import com.example.web.model.DiaChi;
import com.example.web.model.GioHangChiTiet;
import com.example.web.model.HoaDon;
import com.example.web.model.KhachHang;
import com.example.web.repository.IHoaDonRepository;
import com.example.web.request.CheckoutRequest;
import com.example.web.service.CheckoutService;
import com.example.web.service.IDiaChiService;
import com.example.web.service.IGioHangOnllineService;
import com.example.web.service.IHoaDonChiTietService;
import com.example.web.service.ILichSuHoaDonService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;

import com.example.web.Config.VNPAY.Config;

@Service
public class CheckoutServiceImpl implements CheckoutService {

    @Autowired
    private IGioHangOnllineService gioHangOnllineService;

    @Autowired
    private IHoaDonRepository hoaDonRepository;

    @Autowired
    private IHoaDonChiTietService hoaDonChiTietService;

    @Autowired
    private ILichSuHoaDonService lichSuHoaDonService;

    @Autowired
    private IDiaChiService diaChiService;


    @Override
    public String createOrder(KhachHang khachHang, CheckoutRequest checkoutRequest, HttpServletRequest request) {
        BigDecimal tongTien = gioHangOnllineService.tongTienSanPhamTrongGioHang(khachHang.getId());
        if (checkoutRequest.getPhuongThucThanhToan() == PhuongThucThanhToanStatus.VNPAY) {
            String vnp_Version = "2.1.0";
            String vnp_Command = "pay";
            String orderType = "other";
            long amount = tongTien.intValue() * 100;
            String bankCode = request.getParameter("bankCode");

            String vnp_TxnRef = Config.getRandomNumber(8);
            String vnp_IpAddr = Config.getIpAddress(request);

            String vnp_TmnCode = Config.vnp_TmnCode;

            Map<String, String> vnp_Params = new HashMap<>();
            vnp_Params.put("vnp_Version", vnp_Version);
            vnp_Params.put("vnp_Command", vnp_Command);
            vnp_Params.put("vnp_TmnCode", vnp_TmnCode);
            vnp_Params.put("vnp_Amount", String.valueOf(amount));
            vnp_Params.put("vnp_CurrCode", "VND");

            if (bankCode != null && !bankCode.isEmpty()) {
                vnp_Params.put("vnp_BankCode", bankCode);
            }
            vnp_Params.put("vnp_TxnRef", vnp_TxnRef);
            vnp_Params.put("vnp_OrderInfo", "Thanh toan don hang:" + vnp_TxnRef);
            vnp_Params.put("vnp_OrderType", orderType);

            String locate = request.getParameter("language");
            if (locate != null && !locate.isEmpty()) {
                vnp_Params.put("vnp_Locale", locate);
            } else {
                vnp_Params.put("vnp_Locale", "vn");
            }
            vnp_Params.put("vnp_ReturnUrl", Config.vnp_ReturnUrl);
            vnp_Params.put("vnp_IpAddr", vnp_IpAddr);

            Calendar cld = Calendar.getInstance(TimeZone.getTimeZone("Etc/GMT+7"));
            SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
            String vnp_CreateDate = formatter.format(cld.getTime());
            vnp_Params.put("vnp_CreateDate", vnp_CreateDate);

            cld.add(Calendar.MINUTE, 15);
            String vnp_ExpireDate = formatter.format(cld.getTime());
            vnp_Params.put("vnp_ExpireDate", vnp_ExpireDate);

            List fieldNames = new ArrayList(vnp_Params.keySet());
            Collections.sort(fieldNames);
            StringBuilder hashData = new StringBuilder();
            StringBuilder query = new StringBuilder();
            Iterator itr = fieldNames.iterator();
            while (itr.hasNext()) {
                String fieldName = (String) itr.next();
                String fieldValue = (String) vnp_Params.get(fieldName);
                if ((fieldValue != null) && (fieldValue.length() > 0)) {
                    //Build hash data
                    hashData.append(fieldName);
                    hashData.append('=');
                    try {
                        hashData.append(URLEncoder.encode(fieldValue, StandardCharsets.US_ASCII.toString()));
                        //Build query
                        query.append(URLEncoder.encode(fieldName, StandardCharsets.US_ASCII.toString()));
                        query.append('=');
                        query.append(URLEncoder.encode(fieldValue, StandardCharsets.US_ASCII.toString()));
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    }
                    if (itr.hasNext()) {
                        query.append('&');
                        hashData.append('&');
                    }
                }
            }
            String queryUrl = query.toString();
            String vnp_SecureHash = Config.hmacSHA512(Config.secretKey, hashData.toString());
            queryUrl += "&vnp_SecureHash=" + vnp_SecureHash;
            String paymentUrl = Config.vnp_PayUrl + "?" + queryUrl;
            return paymentUrl;
        }
        if (checkoutRequest.getPhuongThucThanhToan() == PhuongThucThanhToanStatus.THANH_TOAN_KHI_NHAN_HANG) {
            HoaDon hoaDon = saveOrder(checkoutRequest, khachHang);
            return "/checkouts/success?idHD=" + hoaDon.getId();
        }
        return null;
    }

    @Override
    public HoaDon saveOrder(CheckoutRequest request, KhachHang khachHang) {
        BigDecimal tongTien = gioHangOnllineService.tongTienSanPhamTrongGioHang(khachHang.getId());
        Date date = java.util.Calendar.getInstance().getTime();
        //+ "," + request.getPhuongXa() + "," + request.getQuanHuyen() + "," + request.getThanhPho()
        if (khachHang.getDiaChis().isEmpty()) {
            DiaChi dc = DiaChi.builder()
                    .khachHang(khachHang)
                    .sdt(request.getSdt())
                    .hoTen(request.getHoTen())
                    .diaChi(request.getDiaChi())
                    .diaChiMacDinh(true)
                    .build();
            diaChiService.add(dc);
        }
        String diaChi = request.getDiaChi();
        HoaDon hoaDon = HoaDon.builder()
                .trangThai(HoaDonStatus.CHO_XAC_NHAN)
                .ngayTao(date)
                .ma("HD" + (hoaDonRepository.findAll().size() + 1))
                .loaiHoaDon(true)
                .hoTen(request.getHoTen())
                .khachHang(khachHang)
                .diaChi(diaChi)
                .sdt(request.getSdt())
                .moTa(request.getGhiChu())
                .phuongThucThanhToan(request.getPhuongThucThanhToan())
                .tongTien(tongTien)
                .build();
        if (request.getPhuongThucThanhToan() == PhuongThucThanhToanStatus.VNPAY) {
            hoaDon.setNgayThanhToan(date);
        }
        hoaDonRepository.save(hoaDon);
        List<GioHangChiTiet> response = gioHangOnllineService.getGHCTByKhachHang_id(khachHang.getId());
        response.forEach(o -> {
            hoaDonChiTietService.addHoaDonChiTiet(o.getChiTietSanPham().getId(), hoaDon.getId(), o.getSoLuong());
        });
        gioHangOnllineService.clearAllGioHangChiTietByKhachHang_id(khachHang.getId());
        return hoaDon;
    }

    @Override
    public Integer orderReturn(HttpServletRequest request) {
        Map fields = new HashMap();
        for (Enumeration params = request.getParameterNames(); params.hasMoreElements(); ) {
            String fieldName = null;
            String fieldValue = null;
            try {
                fieldName = URLEncoder.encode((String) params.nextElement(), StandardCharsets.US_ASCII.toString());
                fieldValue = URLEncoder.encode(request.getParameter(fieldName), StandardCharsets.US_ASCII.toString());
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            if ((fieldValue != null) && (fieldValue.length() > 0)) {
                fields.put(fieldName, fieldValue);
            }
        }
        if (fields.containsKey("vnp_SecureHashType")) {
            fields.remove("vnp_SecureHashType");
        }
        if (fields.containsKey("vnp_SecureHash")) {
            fields.remove("vnp_SecureHash");
        }
        if ("00".equals(request.getParameter("vnp_TransactionStatus"))) {
            return 1;
        } else {
            return 0;
        }
    }
}
