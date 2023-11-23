<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>


<c:if test="${hd.trangThai == 4}">
    <button class="btn btn-primary rounded-pill">Đã hoàn thành</button>
</c:if>

<c:if test="${hd.trangThai == 1}">
    <button class="btn btn-warning rounded-pill">Chờ xác nhận</button>
</c:if>

<c:if test="${hd.trangThai == 2}">
    <button class="btn btn-success rounded-pill">Đã xác nhận</button>
</c:if>

<c:if test="${hd.trangThai == 3}">
    <button class="btn btn-primary rounded-pill">Đang giao hàng</button>
</c:if>

<c:if test="${hd.trangThai == 5}">
    <button class="btn btn-danger rounded-pill">Đã huỷ</button>
</c:if>

<c:if test="${hd.trangThai == 6}">
    <button class="btn btn-success rounded-pill">Giao hàng thành công</button>
</c:if>




