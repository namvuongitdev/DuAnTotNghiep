<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

<div class="col-12">
    <div class="form-floating mb-3">
        <input type="text" class="form-control" id="hoTen" name="hoTen" placeholder="Họ và tên" value="${error != null ? checkoutRequest.hoTen : diaChiMacDinh.hoTen}">
        <label>Họ và tên</label>
        <form:errors path="hoTen" cssStyle="color: red"/>
    </div>
</div>
<div class="col-12">
    <div class="form-floating mb-3">
        <input type="number" class="form-control" id="sdt" name="sdt" placeholder="Số điện thoại" value="${error != null ? checkoutRequest.sdt : diaChiMacDinh.sdt}">
        <label>Số điện thoại</label>
        <form:errors path="sdt" cssStyle="color: red"/>
    </div>
</div>
<div class="col-12">
    <div class="form-floating mb-3">
        <input type="text" class="form-control" id="diaChi" name="diaChi" placeholder="Địa chỉ" value="${error != null ? checkoutRequest.diaChi : diaChiMacDinh.diaChi}">
        <label>Địa chỉ</label>
        <form:errors path="diaChi" cssStyle="color: red"/>
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
