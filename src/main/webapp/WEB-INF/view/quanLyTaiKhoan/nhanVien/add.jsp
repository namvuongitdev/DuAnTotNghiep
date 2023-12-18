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

    <!-- Styles -->
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" />
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/select2@4.1.0-rc.0/dist/css/select2.min.css" />
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/select2-bootstrap-5-theme@1.3.0/dist/select2-bootstrap-5-theme.min.css" />
    <!-- Or for RTL support -->
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/select2-bootstrap-5-theme@1.3.0/dist/select2-bootstrap-5-theme.rtl.min.css" />

    <script src="
              https://cdn.jsdelivr.net/npm/sweetalert2@11.10.0/dist/sweetalert2.all.min.js
              "></script>
    <link href="
          https://cdn.jsdelivr.net/npm/sweetalert2@11.10.0/dist/sweetalert2.min.css
         " rel="stylesheet">
</head>
<body>
<%--navbar--%>
<jsp:include page="../../sidebar/navbar.jsp"/>
<%--sidebar--%>
<div class="container-fluid">
    <div class="row flex-nowrap">
        <jsp:include page="../../sidebar/sidebar.jsp"/>
        <div class="col py-3">
            <div class="pagetitle">
                <h3>Nhân viên</h3>
                <nav>
                    <ol class="breadcrumb">
                        <li class="breadcrumb-item"><a href="/admin/trang-chu" style="text-decoration: none; color: black">Trang chủ</a></li>
                        <li class="breadcrumb-item ">Quản lý tài khoản</li>
                        <li class="breadcrumb-item "><a href="/admin/nhan-vien/hien-thi" style="text-decoration: none; color: black" onclick="clearLocalStorage()">Nhân viên</a></li>
                        <li class="breadcrumb-item active">Thêm dữ liệu</li>
                    </ol>
                </nav>
            </div><!-- End Page Title -->
            <section class="section">
                <div class="card">
                    <div class="card-body row">
                        <h5 class="card-title">Thông tin sản phẩm</h5>
                        <br><br>
                        <%--@elvariable id="nhanVien" type=""--%>
                        <form:form action="/admin/nhan-vien/add" method="post" modelAttribute="nhanVien" id="yourForm">
                            <div class="row">
                                <div class="col-6">
                                    <div class="mb-3">
                                        <label for="taiKhoan" class="form-label ">Tên tài khoản <span style="color: red">(*)</span></label>
                                        <form:input type="text" class="form-control" path="taiKhoan" id="taiKhoan"/>
                                        <form:errors path="taiKhoan" cssStyle="color: red"/>
                                    </div>
                                    <div class="mb-3">
                                        <label for="hoTen" class="form-label ">Họ và tên <span style="color: red">(*)</span></label>
                                        <form:input type="text" class="form-control" path="hoTen" id="hoTen"/>
                                        <form:errors path="hoTen" cssStyle="color: red"/>
                                    </div>
                                    <div class="mb-3">
                                        <label for="gioiTinh" class="form-label">Giới tính <span style="color: red">(*)</span></label>
                                        <select name="gioiTinh" class="form-select" id="gioiTinh">
                                            <option value="true" ${nhanVien.gioiTinh == true ? 'selected' : '' }>
                                                Nam
                                            </option>
                                            <option value="false" ${nhanVien.gioiTinh == false ? 'selected' : '' }>
                                                Nữ
                                            </option>
                                        </select>
                                    </div>
                                    <div class="mb-3">
                                        <label for="diaChi" class="form-label">Địa chỉ thường trú <span style="color: red">(*)</span></label>
                                        <form:textarea style="height: 125px" type="text" class="form-control" path="diaChi" id="diaChi" />
                                        <form:errors path="diaChi" cssStyle="color: red"/>
                                    </div>
                                </div>
                                <div class="col-6">
                                    <div class="mb-3">
                                        <label for="email" class="form-label">Email <span style="color: red">(*)</span></label>
                                        <form:input type="text" class="form-control" path="email" id="email"/>
                                        <form:errors path="email" cssStyle="color: red"/>
                                    </div>
                                    <div class="mb-3">
                                        <label for="sdt" class="form-label">Số điện thoại <span style="color: red">(*)</span></label>
                                        <form:input type="text" class="form-control" path="sdt" id="sdt"/>
                                        <form:errors path="sdt" cssStyle="color: red"/>
                                    </div>
                                    <div class="mb-3">
                                        <label for="cccd" class="form-label">Số CCCD <span style="color: red">(*)</span></label>
                                        <form:input type="text" class="form-control" path="cccd" id="cccd"/>
                                        <form:errors path="cccd" cssStyle="color: red"/>
                                    </div>
                                    <div class="row mb-3">
                                        <div class="col-11">
                                            <div class="col-12">
                                                <label for="chucVu" class="form-label">Chức vụ <span style="color: red">(*)</span></label>
                                                <form:select path="chucVu" class="form-control" id="chucVu">
                                                    <form:options items="${listChucVu}" itemLabel="ten" itemValue="id"/>
                                                </form:select>
                                            </div>
                                        </div>
                                        <div class="col-1" style="padding-top: 40px">
                                            <a data-bs-toggle="modal" data-bs-target="#exampleModalChucVu">
                                                <i class="bi bi-plus-circle"></i>
                                            </a>
                                        </div>
                                    </div>
                                    <div class="mb-3">
                                        <label for="trangThai" class="form-label">Trạng Thái <span style="color: red">(*)</span></label>
                                        <select name="trangThai" class="form-select" id="trangThai">
                                            <option value="0" ${nhanVien.trangThai == 0 ? 'selected' : '' }>
                                                Làm việc
                                            </option>
                                            <option value="1" ${nhanVien.trangThai == 1 ? 'selected' : '' }>
                                                Nghỉ làm
                                            </option>
                                        </select>
                                    </div>
                                </div>
                            </div>
                            <br>
                            <div style="text-align: center">
                                <button class="btn btn-primary" onclick="add(event)">Xác nhận
                                </button>
                                <a href="/admin/nhan-vien/view-add" class="btn btn-warning" onclick="clearLocalStorage()">Làm Mới</a>
                            </div>
                        </form:form>
                    </div>
                </div>
                <%--thêm nhanh chức vụ--%>
                <div class="modal" id="exampleModalChucVu" tabindex="-1">
                    <div class="modal-dialog modal-dialog-scrollable">
                        <div class="modal-content">
                            <div class="modal-header">
                                <h5 class="modal-title">Thêm dữ liệu</h5>
                                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                            </div>
                            <%--@elvariable id="chucVu" type=""--%>
                            <form:form method="post" action="/admin/nhan-vien/modal-add-chuc-vu" modelAttribute="chucVu" class="row g-3" id="yourFormCV">
                                <div class="modal-body">
                                    <div class="form-floating">
                                        <form:input class="form-control" placeholder="" path="ten"/>
                                        <form:label class="form-label" path="ten">Tên chức vụ:</form:label>
                                        <form:errors path="ten" cssStyle="color: #ff0000"/>
                                    </div>
                                </div>
                                <div class="modal-footer">
                                    <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Đóng</button>
                                    <form:button type="submit" class="btn btn-primary" onclick="addCV(event)">
                                        Xác nhận
                                    </form:button>
                                </div>
                            </form:form><!-- End floating Labels Form -->
                        </div>
                    </div>
                </div><!-- End Modal Dialog Scrollable-->
            </section>
        </div>
    </div>
</div>
<!-- Scripts -->
<script src="https://cdn.jsdelivr.net/npm/jquery@3.5.0/dist/jquery.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/select2@4.1.0-rc.0/dist/js/select2.min.js"></script>
<script>
    $(document).ready(function () {
        $('#chucVu').select2({
            theme: "bootstrap-5"
        });

    });
    // Lấy giá trị từ tất cả các ô input và textarea, và lưu chúng vào localStorage
    function saveInputValues() {
        var allInputs = document.querySelectorAll('input, textarea');

        for (var i = 0; i < allInputs.length; i++) {
            var inputId = allInputs[i].id;
            var inputValue = allInputs[i].value;

            localStorage.setItem(inputId, inputValue);
        }
    }

    // Khôi phục giá trị từ localStorage vào các ô input và textarea
    window.onload = function() {
        var allInputs = document.querySelectorAll('input, textarea');

        for (var i = 0; i < allInputs.length; i++) {
            var inputId = allInputs[i].id;
            var savedValue = localStorage.getItem(inputId);

            if (savedValue) {
                allInputs[i].value = savedValue;
            }
        }
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

    function addCV(event) {
        event.preventDefault(); // Prevent the default form submission behavior
        Swal.fire({
            title: "Bạn có muốn thêm không ?",
            showDenyButton: true,
            confirmButtonText: "Có",
            denyButtonText: `Không`
        }).then((result) => {
            /* Read more about isConfirmed, isDenied below */
            if (result.isConfirmed) {
                document.getElementById('yourFormCV').submit();
                saveInputValues();
            } else if (result.isDenied) {
                return false;
            }
        });
    }

    function clearLocalStorage() {
        localStorage.clear();
    }

    if (${error != null}) {
        Swal.fire({
            title: "${error}",
            icon: "error"
        });
    }

    if (${success != null}) {
        Swal.fire({
            title: "${success}",
            icon: "success"
        });
        clearLocalStorage();
    }
</script>
</body>
</html>