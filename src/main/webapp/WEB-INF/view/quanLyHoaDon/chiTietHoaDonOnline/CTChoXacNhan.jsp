<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <link rel="shortcut icon" href="#">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css"
          rel="stylesheet" integrity="sha384-9ndCyUaIbzAi2FUVXJi0CjmCapSmO7SnpJef0486qhLnuZ2cdeRhO02iuK6FUUVM"
          crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"
            integrity="sha384-geWF76RCwLtnZ8qwWowPQNguL3RmwHVBC9FhGdlKrxdiJJigb/j/68SIy3Te4Bkz"
            crossorigin="anonymous"></script>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.1/font/bootstrap-icons.css">
    <link rel="stylesheet" href="../../../../css/banHangTaiQuay/hoaDon/chiTietHoaDon.css">
    <link rel="stylesheet" href="../../../../css/ban-hang-tai-quay.css">
    <style>
        #info span {
            font-weight: 600;
            padding-left: 5px;
        }

        table {
            border-collapse: collapse;
            width: 100%;
        }

        th, td {
            padding: 8px;
            text-align: left;
            border-bottom: 1px solid #ddd;
            font-weight: 600;
        }
    </style>
</head>
<body>
<%--navbar--%>
<jsp:include page="../../sidebar/navbar.jsp"/>
<%--sidebar--%>
<div class="container-fluid">
    <div class="row flex-nowrap">
        <jsp:include page="../../sidebar/sidebar.jsp"/>
        <div class="col py-3">
            <c:if test="${success != null}">
                <jsp:include page="../../notiface/success.jsp"></jsp:include>
            </c:if>

            <c:if test="${error != null}">
                <jsp:include page="../../notiface/error.jsp"></jsp:include>
            </c:if>
            <div class="pagetitle">
                <nav>
                    <ol class="breadcrumb">
                        <li class="breadcrumb-item"><a href="/admin/trang-chu"
                                                       style="text-decoration: none; color: black">Trang chủ</a></li>
                        <li class="breadcrumb-item"><a href="/admin/hoa-don/hien-thi"
                                                       style="text-decoration: none; color: black">Quản lí hóa đơn</a>
                        </li>
                    </ol>
                </nav>
            </div><!-- End Page Title -->
            <div class="container-fluid">
                <%--@elvariable id="hoaDon" type=""--%>
                <%--                <form:form action="/admin/hoa-don/update-hoa-don/${hd.id}" method="post" modelAttribute="hoaDon">--%>
                <div class="row">
                    <div class="card">
                        <div class="card-body row">
                            <div class="row">
                                <div class="col-sm-6">
                                    <jsp:include page=".././trangThai/thay-doi-trang-thai.jsp"></jsp:include>
                                </div>
                                <div class="col-sm-6">
                                    <button class="btn btn-light" style="float: right"
                                            onclick="getAllLichHoaDon(`${hoaDon.id}`)">
                                        Lịch sử hoá đơn
                                    </button>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="card">
                        <div style="margin-top: 15px">
                            <h4>Thông tin hoá đơn</h4>
                        </div>
                        <hr>
                        <div class="card-body row">
                            <jsp:include page="thong-tin-don-hang.jsp"></jsp:include>
                        </div>
                    </div>
                </div>
                <%-- end thông tin hoá đơn--%>
                <%--thông tin sản phẩm--%>
                <div class="row">
                    <div class="card">
                        <div class="card-body row">
                            <jsp:include page="san-pham.jsp"></jsp:include>
                            <jsp:include page="tong-tien.jsp"></jsp:include>
                        </div>
                    </div>
                </div>
                <%-- end sản phẩm--%>
            </div>
        </div>
    </div>
</div>
<!-- Sửa thông tin -->
<c:if test="${hoaDon.loaiHoaDon}">
    <jsp:include page="modal-update-thong-tin-khach-hang.jsp"></jsp:include>
</c:if>
<%--modal lịch sử hoá đơn--%>
<jsp:include page="modal-lich-su-hoa-don.jsp"></jsp:include>
<c:if test="${hoaDon.trangThai == 1 || hoaDon.trangThai == 2}">
    <%--thêm sản phẩm--%>
    <%--chi tiết sản phẩm--%>
    <jsp:include page="../../banHangTaiQuay/modal-hien-thi-san-pham.jsp"></jsp:include>
    <jsp:include page="../../banHangTaiQuay/chi-tiet-san-pham.jsp"></jsp:include>
    <%--modal xoá sản phẩm--%>
    <jsp:include page="modal-xoa-san-pham.jsp"></jsp:include>
    <%--modal update số lượng sản phẩm--%>
    <jsp:include page="modal-update-so-luong.jsp"></jsp:include>
</c:if>
<%--modal xác nhân hoá đơn--%>
<c:if test="${hoaDon.trangThai != 4}">
    <jsp:include page="modal-xac-nhan-hoa-don.jsp"></jsp:include>
</c:if>
<%--modal tra hàng--%>
<c:if test="${hoaDon.trangThai == 4}">
    <jsp:include page="modal-tra-hang.jsp"></jsp:include>
</c:if>
</body>

<%--   javasctipt--%>
<script>
    const VND = new Intl.NumberFormat('vi-VN', {
        style: 'currency',
        currency: 'VND',
    });
</script>

<c:if test="${hoaDon.loaiHoaDon}">
    <script src="/js/quanLyHoaDon/modal-update-thong-tin-khach-hang.js"></script>
</c:if>

<script src="/js/quanLyHoaDon/modal-lich-su-hoa-don.js"></script>
<c:if test="${hoaDon.trangThai == 1 || hoaDon.trangThai == 2}">
    <script>
        let modal = document.getElementById("myModal");

        let btn = document.getElementById("myBtn");

        let span = document.getElementById("close_ctsp");
        span.onclick = function () {
            colorSP.innerHTML = ""
            sizeSP.innerHTML = ""
            document.getElementById("soLuong").innerHTML = "";
            document.getElementById("themVaoGioHang").name = "";
            document.getElementById("soLuongTon").value = 1;
            document.getElementById("sp").innerHTML = "";
            document.getElementById("img").innerHTML = "";
            mauSac = undefined;
            sanPham = undefined;
            kichCo = undefined;
            dataCTSP = undefined;
            modal.style.display = "none";
        }
    </script>
    <script src="/js/quanLyHoaDon/san-pham-hdct.js">
    </script>
    <script src="/js/quanLyHoaDon/chi-tiet-san-pham-hdct.js"></script>
    <script src="/js/quanLyHoaDon/modal-xoa-san-pham.js"></script>
    <script src="/js/quanLyHoaDon/modal-update-so-luong.js"></script>
</c:if>

<c:if test="${hoaDon.trangThai != 4}">
    <script src="/js/quanLyHoaDon/modal-xac-nhan-hoa-don.js"></script>
</c:if>

<c:if test="${hoaDon.trangThai == 4}">
    <script src="/js/quanLyHoaDon/modal-tra-hang.js"></script>
</c:if>
</html>