package com.example.web.service;

import com.example.web.model.HoaDon;
import com.example.web.model.KhachHang;
import com.example.web.request.CheckoutRequest;

public interface CheckoutService {

     HoaDon createOrder(KhachHang khachHang , CheckoutRequest request);
}
