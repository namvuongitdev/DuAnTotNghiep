<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <link rel="shortcut icon" href="#">
    <script type="text/javascript"
            src="https://cdnjs.cloudflare.com/ajax/libs/webrtc-adapter/3.3.3/adapter.min.js"></script>
    <script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/vue/2.1.10/vue.min.js"></script>
    <script type="text/javascript" src="https://rawgit.com/schmich/instascan-builds/master/instascan.min.js"></script>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css"
          rel="stylesheet" integrity="sha384-9ndCyUaIbzAi2FUVXJi0CjmCapSmO7SnpJef0486qhLnuZ2cdeRhO02iuK6FUUVM"
          crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"
            integrity="sha384-geWF76RCwLtnZ8qwWowPQNguL3RmwHVBC9FhGdlKrxdiJJigb/j/68SIy3Te4Bkz"
            crossorigin="anonymous"></script>
    <link rel="stylesheet" href="/css/ban-hang-tai-quay.css">
    <script src="https://code.jquery.com/jquery-latest.js"></script>
    <svg xmlns="http://www.w3.org/2000/svg" style="display: none;">
        <symbol id="exclamation-triangle-fill" fill="currentColor" viewBox="0 0 16 16">
            <path d="M8.982 1.566a1.13 1.13 0 0 0-1.96 0L.165 13.233c-.457.778.091 1.767.98 1.767h13.713c.889 0 1.438-.99.98-1.767L8.982 1.566zM8 5c.535 0 .954.462.9.995l-.35 3.507a.552.552 0 0 1-1.1 0L7.1 5.995A.905.905 0 0 1 8 5zm.002 6a1 1 0 1 1 0 2 1 1 0 0 1 0-2z"/>
        </symbol>
    </svg>
</head>
<body>

<%--navbar--%>
<jsp:include page="../sidebar/navbar.jsp"/>
<%--sidebar--%>
<div class="container-fluid" style="background-color: #dddddd">
    <div class="row flex-nowrap">
        <jsp:include page="../sidebar/sidebar.jsp"/>
        <div class="col py-3">
            <%--code giao diện chất liệu --%>
            <a style="float: right" class="btn btn-primary"
               name="1" onclick="getSanPham(this.name)">
                Thêm sản phẩm
            </a>
            <button style="float: right ; margin-right: 10px" id="myBtnQr" class="btn btn-primary">QR Code</button>

            <div class="container">
                <h4>Tạo hoá đơn</h4>
                <div class="pagetitle">
                    <nav>
                        <ol class="breadcrumb">
                            <li class="breadcrumb-item"><a
                                    href="/admin/hoa-don/hien-thi-hoa-cho" <%= request.getRequestURI().contains("hoa-don-cho") ? "class=\"link-primary\"" : ""  %>
                                    style="text-decoration: none; color: black">Hoá đơn chờ</a></li>
                            <li class="breadcrumb-item"><a
                                    href="/admin/hoa-don/detail?idHD=${hoaDon.id}" <%= request.getRequestURI().contains("gio-hang") ? "class=\"link-primary\"" : ""  %>
                                    style="text-decoration: none; color: black ; ">Tạo hoá đơn - ${hoaDon.ma}</a></li>
                        </ol>
                    </nav>
                </div><!-- End Page Title -->
                <br>
                <%--hiển thị giỏ hàng--%>
                <div class="row">
                    <c:if test="${error != null}">
                        <div class="alert alert-danger d-flex align-items-center" role="alert">
                            <svg class="bi flex-shrink-0 me-2" width="24" height="24" role="img" aria-label="Danger:">
                                <use xlink:href="#exclamation-triangle-fill"/>
                            </svg>
                            <div>

                                    ${error}
                            </div>
                        </div>
                    </c:if>
                    <div class="row" style="margin-bottom: 50px">
                         <jsp:include page="hien-thi-gio-hang.jsp"></jsp:include>
                    </div>
                    <div class="row" style="margin-bottom: 20px">
                        <div class="col l-3" style="background-color: white">
                            <br>
                            <div class="row">
                                <div class="row">
                                    <div class="col-sm-3">
                                        <h4>Thông tin khách hàng</h4>
                                    </div>
                                    <div class="col-sm-1">
                                        <button class="btn btn-light" id="btnThemKhachHang">+</button>
                                    </div>
                                </div>
                                <div class="col-sm-2" style="width: 16%">
                                    <button type="button" class="btn btn-secondary"
                                            id="btnKhachHang"
                                    >
                                        Chọn tài khoản
                                    </button>
                                </div>
                                <c:if test="${khachHang != null}">
                                    <div class="col-sm-2" style="width: 11%">
                                        <button class="btn btn-danger"
                                                onclick="window.location.href = '/admin/hoa-don/detail?idHD=${hoaDon.id}'">Huỷ
                                            chọn
                                        </button>
                                    </div>
                                </c:if>
                            </div>
                            <hr>
                            <div class="col l-3" id="thongTinKhachHang" style="margin-bottom: 10px">
                                <c:if test="${khachHang != null}">
                                    <div><p>Họ tên khách hàng : ${khachHang.hoTen}</p></div>
                                    <div><p>Số điện thoại :${khachHang.sdt}</p></div>
                                </c:if>
                                <c:if test="${khachHang == null}">
                                    <div><p>Họ tên khách hàng : Khách bán lẻ</p></div>
                                </c:if>
                            </div>
                        </div>
                    </div>
                    <%-- thông tin thanh toán--%>
                    <form class="row" style="background-color: white"
                          action="/admin/hoa-don/thanh-toan?idHD=${hoaDon.id}&idKhachHang=${khachHang.id}" method="post"
                          modelAtrribute="${request}">
                        <c:choose>
                            <%-- đặt hàng--%>
                            <c:when test="${hoaDon.loaiHoaDon}">
                                <jsp:include page="dat-hang.jsp"></jsp:include>
                            </c:when>
                            <%--tại quầy--%>
                            <c:otherwise>
                                <jsp:include page="tai-quay.jsp"></jsp:include>
                            </c:otherwise>
                        </c:choose>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>
</div>
<%--hiển thị khách hàng--%>
<jsp:include page="modal-hien-thi-khach-hang.jsp"></jsp:include>
<%--thêm sản phẩm--%>
<jsp:include page="modal-hien-thi-san-pham.jsp"></jsp:include>
<%--chi tiết sản phẩm--%>
<jsp:include page="chi-tiet-san-pham.jsp"></jsp:include>
<div id="modalQrcode" class="modal">
    <!-- Modal content -->
    <div class="modal-content-1" style="width: 40%">
        <span class="close" id="close_qrcode" accesskey="${khachHang.id}">&times;</span>
        <div class="row" id="qrcode">
            <video id="preview"></video>
        </div>
    </div>
</div>
<%-- địa chỉ khách hàng--%>
<%--<jsp:include page="hien-thi-dia-chi.jsp"></jsp:include>--%>
<%--thêm khách hàng--%>
<jsp:include page="them-khach-hang.jsp"></jsp:include>
</body>
<script src="/js/banHangTaiQuay/modal.js">
</script>
<script src="/js/banHangTaiQuay/sanPham.js"></script>
<script src="/js/banHangTaiQuay/chiTietSanPham.js"></script>
<script src="/js/banHangTaiQuay/thanhToan.js"></script>
<script src="/js/banHangTaiQuay/khachHang.js"></script>
<%--<script src="/js/banHangTaiQuay/diaChiKhachHang.js"></script>--%>
</html>