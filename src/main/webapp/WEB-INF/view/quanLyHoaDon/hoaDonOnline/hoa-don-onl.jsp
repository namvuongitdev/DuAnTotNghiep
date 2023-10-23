<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css"
          rel="stylesheet" integrity="sha384-9ndCyUaIbzAi2FUVXJi0CjmCapSmO7SnpJef0486qhLnuZ2cdeRhO02iuK6FUUVM"
          crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"
            integrity="sha384-geWF76RCwLtnZ8qwWowPQNguL3RmwHVBC9FhGdlKrxdiJJigb/j/68SIy3Te4Bkz"
            crossorigin="anonymous"></script>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.1/font/bootstrap-icons.css">
    <link rel="stylesheet" href="../../../../css/banHangTaiQuay/hoaDon/trangThai/hoaDonOnl.css">
</head>
<body>
<%--navbar--%>
<jsp:include page="../../sidebar/navbar.jsp"/>
<%--sidebar--%>
<div class="container-fluid">
    <div class="row flex-nowrap">
        <jsp:include page="../../sidebar/sidebar.jsp"/>
        <div class="col py-3">
            <div class="tab-buttons">
                <button class="tab-button" onclick="window.location.href='/admin/hoa-don-onl/cho-xac-nhan/hien-thi'">Chờ xác nhận</button>
                <button class="tab-button" onclick="window.location.href='/admin/hoa-don-onl/cho-giao-hang/hien-thi'">Chờ giao hàng</button>
                <button class="tab-button" onclick="window.location.href='/admin/hoa-don-onl/dang-giao/hien-thi'">Đang giao</button>
                <button class="tab-button" onclick="window.location.href='/admin/hoa-don-onl/da-giao/hien-thi'">Đã giao</button>
                <button class="tab-button" onclick="window.location.href='/admin/hoa-don-onl/da-huy/hien-thi'">Đã hủy</button>
            </div><br>
            <jsp:include page="${contentPage}"/>
        </div>
    </div>
</div>
</body>
</html>