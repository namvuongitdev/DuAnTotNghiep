<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<c:if test="${hoaDon.trangThai == 1}">
    <button class="btn btn-primary" id="xacNhanHoaDon">Xác nhận</button>
    <button class="btn btn-danger" id="xacNhanHuyHoaDon">Huỷ</button>
</c:if>

<c:if test="${hoaDon.trangThai == 2}">
    <button class="btn btn-primary" id="xacNhanHoaDon">Giao hàng</button>
</c:if>

<c:if test="${hoaDon.trangThai == 3}">
    <button class="btn btn-primary" id="xacNhanHoaDon">Giao hàng thành công</button>
</c:if>

<c:if test="${hoaDon.trangThai == 6}">
    <button class="btn btn-primary" id="xacNhanHoaDon">Hoàn thành</button>
</c:if>
<c:if test="${hoaDon.trangThai == 4 || hoaDon.trangThai == 6 || hoaDon.trangThai == 3 || hoaDon.trangThai == 2}">
         <button class="btn btn-primary" onclick="inHoaDon(event , `${hoaDon.id}`)">Xuất hoá đơn</button>
</c:if>
<c:if test="${hoaDon.trangThai == 4 && isChecks}">
    <button class="btn btn-danger" id="xacNhanHuyHoaDon">Huỷ</button>
</c:if>

