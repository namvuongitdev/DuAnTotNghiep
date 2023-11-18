package com.example.web.service;

import com.example.web.model.HoaDon;
import com.example.web.model.KhachHang;
import com.example.web.request.CheckoutRequest;
import jakarta.servlet.http.HttpServletRequest;

public interface CheckoutService {

    String createOrder(KhachHang khachHang, CheckoutRequest checkoutRequest, HttpServletRequest request);

    HoaDon saveOrder(CheckoutRequest request, KhachHang khachHang);

    Integer orderReturn(HttpServletRequest request);
}
