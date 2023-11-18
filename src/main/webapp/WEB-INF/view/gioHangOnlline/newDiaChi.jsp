<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 11/16/2023
  Time: 9:59 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

<div class="col-12">
    <div class="form-floating mb-3">
        <input type="text" class="form-control" id="hoTen" name="hoTen" placeholder="Họ và tên" value="${checkoutRequest.hoTen}">
        <label>Họ và tên</label>
        <form:errors path="hoTen" cssStyle="color: red"/>
    </div>
</div>
<div class="col-12">
    <div class="form-floating mb-3">
        <input type="text" class="form-control" id="soDienThoai" name="soDienThoai" placeholder="Số điện thoại" value="${checkoutRequest.soDienThoai}">
        <label>Số điện thoại</label>
        <form:errors path="soDienThoai" cssStyle="color: red"/>
    </div>
</div>
<div class="col-12">
    <div class="form-floating mb-3">
        <input type="text" class="form-control" id="diaChi" name="diaChi" placeholder="Địa chỉ" value="${checkoutRequest.diaChi}">
        <label>Địa chỉ</label>
        <form:errors path="diaChi" cssStyle="color: red"/>
    </div>
</div>
<div class="col-4">
    <div class="form-floating">
        <select id="province" class="form-select tinhThanhPho" id="thanhPho" name="thanhPho">
            <option value="">Chọn tỉnh / thành</option>
        </select>
        <label>Tỉnh / thành</label>
        <form:errors path="thanhPho" cssStyle="color: red"/>
    </div>
</div>
<div class="col-4">
    <div class="form-floating">
        <select id="district" disabled class="form-select chonQuanHuyen" aria-invalid="quanHuyen" name="quanHuyen">
            <option value="">Chọn quận / huyện</option>
        </select>
        <label>Quận / huyện</label>
        <form:errors path="quanHuyen" cssStyle="color: red"/>
    </div>
</div>
<div class="col-4">
    <div class="form-floating">
        <select id="ward" disabled class="form-select chonPhuongXa" id="phuongXa" name="phuongXa">
            <option value="">Chọn phường / xã</option>
        </select>
        <label>Phường / xã</label>
        <form:errors path="phuongXa" cssStyle="color: red"/>
    </div>
</div>
<div class="col-12" style="margin-top: 13px">
    <div class="form-floating mb-3">
        <textarea type="text" class="form-control" id="ghiChu" name="ghiChu" value="${checkoutRequest.ghiChu}"></textarea>
        <label>Ghi chú</label>
    </div>
</div>
</body>
</html>
