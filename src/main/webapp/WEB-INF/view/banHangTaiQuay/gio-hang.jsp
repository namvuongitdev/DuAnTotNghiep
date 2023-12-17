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
    <script src="
              https://cdn.jsdelivr.net/npm/sweetalert2@11.10.0/dist/sweetalert2.all.min.js
              "></script>
    <link href="
          https://cdn.jsdelivr.net/npm/sweetalert2@11.10.0/dist/sweetalert2.min.css
         " rel="stylesheet">
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
                    <div class="row" style="margin-bottom: 20px">
                        <jsp:include page="hien-thi-gio-hang.jsp"></jsp:include>
                    </div>
                    <div class="row" style="margin-bottom: 20px">
                        <jsp:include page="hien-thi-khach-hang.jsp"></jsp:include>
                    </div>
                    <%-- thông tin thanh toán--%>
                    <form class="row"
                          action="/admin/hoa-don/thanh-toan?idHD=${hoaDon.id}&idKhachHang=${khachHang.id}"
                          method="post"
                          modelAtrribute="${request}" id="xacNhanThanhToan" onsubmit="return false;">
                        <div class="card">
                            <div class="card-body row">

                                <%-- đặt hàng--%>
                                <c:if test="${hoaDon.loaiHoaDon == 2}">
                                    <jsp:include page="dat-hang.jsp"></jsp:include>
                                </c:if>
                                <%--tại quầy--%>
                                <c:if test="${hoaDon.loaiHoaDon == 0}">
                                    <jsp:include page="tai-quay.jsp"></jsp:include>
                                </c:if>

                            </div>
                        </div>
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
<script>
    if (${error != null}) {
        Toast.fire({
            icon: "error",
            title: "${error}"
        });
    }

    if (${success != null}) {
        Toast.fire({
            icon: "success",
            title: "${success}"
        });
    }

    function xacNhan(event) {
        event.preventDefault(); // Prevent the default form submission behavior
        Swal.fire({
            title: "Bạn có muốn thanh toán ?",
            showDenyButton: true,
            confirmButtonText: "Có",
            denyButtonText: "Không"
        }).then((result) => {
            /* Read more about isConfirmed, isDenied below */
            if (result.isConfirmed) {
                document.getElementById('xacNhanThanhToan').submit();
            } else if (result.isDenied) {
                return false;
            }
        });
    }
</script>
</html>