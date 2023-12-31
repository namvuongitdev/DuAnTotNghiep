<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
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
    <!-- Styles -->
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css"/>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/select2@4.1.0-rc.0/dist/css/select2.min.css"/>
    <link rel="stylesheet"
          href="https://cdn.jsdelivr.net/npm/select2-bootstrap-5-theme@1.3.0/dist/select2-bootstrap-5-theme.min.css"/>
    <!-- Or for RTL support -->
    <link rel="stylesheet"
          href="https://cdn.jsdelivr.net/npm/select2-bootstrap-5-theme@1.3.0/dist/select2-bootstrap-5-theme.rtl.min.css"/>
    <link rel="stylesheet" href="/css/modal.css">

    <!-- Latest compiled and minified CSS -->
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-select@1.13.14/dist/css/bootstrap-select.min.css">

    <!-- Latest compiled and minified JavaScript -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap-select@1.13.14/dist/js/bootstrap-select.min.js"></script>

    <!-- (Optional) Latest compiled and minified JavaScript translation files -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap-select@1.13.14/dist/js/i18n/defaults-*.min.js"></script>

    <script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.6.9/angular.min.js"></script>
    <script src="
              https://cdn.jsdelivr.net/npm/sweetalert2@11.10.0/dist/sweetalert2.all.min.js
              "></script>
    <link href="
          https://cdn.jsdelivr.net/npm/sweetalert2@11.10.0/dist/sweetalert2.min.css
         " rel="stylesheet">

</head>
<body ng-app="app" ng-controller="controller">
<%--navbar--%>
<jsp:include page="../../sidebar/navbar.jsp"/>
<%--sidebar--%>
<div class="container-fluid">
    <div class="row flex-nowrap">
        <jsp:include page="../../sidebar/sidebar.jsp"/>
        <div class="col py-3">
            <div class="pagetitle">
                <h3>Sản phẩm</h3>
                <nav>
                    <ol class="breadcrumb">
                        <li class="breadcrumb-item"><a href="/admin/trang-chu"
                                                       style="text-decoration: none; color: black">Trang chủ</a></li>
                        <li class="breadcrumb-item">Quản lý sản phẩm</li>
                        <li class="breadcrumb-item"><a href="/admin/san-pham/hien-thi"
                                                       style="text-decoration: none; color: black"
                                                       onclick="clearLocalStorage()">Sản phẩm</a></li>
                        <li class="breadcrumb-item active">${title}</li>
                    </ol>
                </nav>
            </div><!-- End Page Title -->
            <section class="section">
                <div class="card">
                    <div class="card-body row">
                        <h5 class="card-title">Thông tin sản phẩm</h5>
                        <br><br>
                        <jsp:include page="form-them-san-pham.jsp"></jsp:include>
                    </div>
                </div>
                <br><br>
                <%--                 chi tiết  sản phẩm--%>
                <div class="card" style="display: ${sp.id==null?'none' : 'block'}">
                    <div class="card-body row">
                        <h5 class="card-title col-9">Thông tin chi tiết sản phẩm</h5>
                        <br><br>
                        <div class="row">
                            <%-- Hiển thị ảnh chi tiết sản phẩm--%>
                            <jsp:include page="chi-tiet-san-pham.jsp"></jsp:include>
                        </div>
                    </div>
                </div>
            </section>
            <%--            thêm chi tiết sản phẩm--%>
            <jsp:include page="modal-them-chi-tiet-san-pham.jsp"></jsp:include>
            <%--            thêm ảnh sản phẩm --%>
            <jsp:include page="modal-them-anh.jsp"></jsp:include>
            <%--            modal thêm nhanh thuộc tính--%>
            <jsp:include page="modal-them-nhanh-thuoc-tinh.jsp"></jsp:include>
        </div>
    </div>
</div>
<jsp:include page="modal-hien-thi-qrcode.jsp"></jsp:include>
<jsp:include page="modal-update-chi-tiet-san-pham.jsp"></jsp:include>
<script src="https://cdn.jsdelivr.net/npm/jquery@3.5.0/dist/jquery.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/select2@4.1.0-rc.0/dist/js/select2.min.js"></script>
<script src="../../../../js/sanPham/quanLySanPham.js"></script>
<script src="../../../../js/sanPham/modalHienThiQrCode.js"></script>
<script type="text/javascript">
    // Lấy giá trị từ tất cả các ô input và textarea, và lưu chúng vào localStorage
    function saveInputValues() {
        var tenSP = document.getElementById('tenSP');
        var giaBan = document.getElementById('giaBan');
        var moTa = document.getElementById('moTa');
        localStorage.setItem('tenSPValue', tenSP.value);
        localStorage.setItem('giaBanValue', giaBan.value);
        localStorage.setItem('moTaValue', moTa.value);
    }

    // Khôi phục giá trị từ localStorage vào các ô input và textarea
    window.onload = function () {
        var tenSP = document.getElementById('tenSP');
        var giaBan = document.getElementById('giaBan');
        var moTa = document.getElementById('moTa');
        var savedTenSP = localStorage.getItem('tenSPValue');
        var savedGiaBan = localStorage.getItem('giaBanValue');
        var savedMoTa = localStorage.getItem('moTaValue');
        if (savedTenSP) {
            tenSP.value = savedTenSP;
        }
        if (savedMoTa) {
            moTa.value = savedMoTa;
        }
        if (savedGiaBan) {
            giaBan.value = savedGiaBan;
        }
    }

    function clearLocalStorage() {
        localStorage.clear();
    }

    $(document).ready(function () {
        $('#chatLieu').select2({
            theme: "bootstrap-5"
        });
        $('#kieuDang').select2({
            theme: 'bootstrap-5'
        });
        $('#danhMuc').select2({
            theme: 'bootstrap-5'
        });
        $('#size').select2({
            theme: 'bootstrap-5'
        });
        $('#mauSac').select2({
            theme: 'bootstrap-5'
        });
    });

    function myfunction(data) {
        Swal.fire({
            title: "Bạn có muốn cập nhật trạng thái không ?",
            showDenyButton: true,
            confirmButtonText: "Có",
            denyButtonText: `Không`
        }).then((result) => {
            /* Read more about isConfirmed, isDenied below */
            if (result.isConfirmed) {
                window.location.href = '/admin/san-pham/stop-ctsp/' + data.idctsp + "?idSP=" + data.idsp + "&tt=" + data.trangThai;
            } else if (result.isDenied) {
                return false;
            }
        });
    }

    function add(event) {
        event.preventDefault(); // Prevent the default form submission behavior
        Swal.fire({
            title: "Bạn có muốn thêm không ?",
            showDenyButton: true,
            confirmButtonText: "Có",
            denyButtonText: `Không`
        }).then((result) => {
            /* Read more about isConfirmed, isDenied below */
            if (result.isConfirmed) {
                document.getElementById('yourForm').submit();
                saveInputValues();
            } else if (result.isDenied) {
                return false;
            }
        });
    }

    function update(event) {
        event.preventDefault(); // Prevent the default form submission behavior
        Swal.fire({
            title: "Bạn có muốn sửa không ?",
            showDenyButton: true,
            confirmButtonText: "Có",
            denyButtonText: `Không`
        }).then((result) => {
            /* Read more about isConfirmed, isDenied below */
            if (result.isConfirmed) {
                document.getElementById('yourForm').submit();
            } else if (result.isDenied) {
                return false;
            }
        });
    }

    function updateCTSP(event) {
        event.preventDefault(); // Prevent the default form submission behavior
        Swal.fire({
            title: "Bạn có muốn sửa không ?",
            showDenyButton: true,
            confirmButtonText: "Có",
            denyButtonText: `Không`
        }).then((result) => {
            if (result.isConfirmed) {
                document.getElementById('formUpdateCtsp').submit();
            } else if (result.isDenied) {
                return false;
            }
        });
    }

    function confirmCTSP(event) {
        event.preventDefault(); // Prevent the default form submission behavior
        Swal.fire({
            title: "Bạn có muốn thêm không?",
            showDenyButton: true,
            confirmButtonText: "Có",
            denyButtonText: `Không`
        }).then((result) => {
            /* Read more about isConfirmed, isDenied below */
            if (result.isConfirmed) {
                document.getElementById("yourFormId").submit();
            } else {
                return false;
            }
        });
    }

    if (${error != null}) {
        message.fire({
            title: "${error}",
            icon: "error"
        });
    }

    if (${success != null}) {
        message.fire({
            text: "${success}",
            icon: "success"
        });
        clearLocalStorage();
    }
</script>
<script src="../../../../js/sanPham/checked.js"></script>
<script src="../../../../js/sanPham/modalThemNhanhThuocTinh.js"></script>
</body>
</html>