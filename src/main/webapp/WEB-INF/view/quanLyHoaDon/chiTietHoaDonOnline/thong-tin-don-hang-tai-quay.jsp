<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 11/21/2023
  Time: 10:27 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="col-md-6">
    <span>Trạng thái : </span>
    <span >Loại : ${hd.loaiHoaDon ? "Giao hàng" : "Tại quầy"}</span>
    <span class="font" value="${hd.ma}">Mã hoá đơn : ${hd.ma}</span>

</div>
<div class="col-md-6">
     <span class="font">Họ tên : ${hd.khachHang == null ? "Khách bán lẻ" : hd.khachHang.hoTen}</span>
</div>
