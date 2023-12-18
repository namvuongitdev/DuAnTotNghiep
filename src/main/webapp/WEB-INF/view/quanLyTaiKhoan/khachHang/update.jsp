<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-GLhlTQ8iRABdZLl6O3oVMWSktQOp6b7In1Zl3/Jr59b6EGGoI1aFkw7cmDA6j6gD" crossorigin="anonymous">
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
                <h3>Khách hàng</h3>
                <nav>
                    <ol class="breadcrumb">
                        <li class="breadcrumb-item"><a href="/admin/trangchu"
                                                       style="text-decoration: none; color: black">Trang chủ</a></li>
                        <li class="breadcrumb-item ">Quản lý tài khoản</li>
                        <li class="breadcrumb-item "><a href="/admin/khach-hang/hien-thi"
                                                        style="text-decoration: none; color: black">Khách hàng</a></li>
                        <li class="breadcrumb-item active">Sửa dữ liệu</li>
                    </ol>
                </nav>
            </div><!-- End Page Title -->
            <section class="section">
                <div class="card">
                    <div class="card-body row">
                        <h5 class="card-title">Thông tin khách hàng</h5>
                        <br><br>
                        <%--@elvariable id="khachHang" type=""--%>
                        <form:form action="/admin/khach-hang/update/${khachHang.id}" method="post"
                                   modelAttribute="khachHang" id="yourForm">
                            <div class="row">
                                <div class="col-6">
                                    <div class="mb-3">
                                        <label for="taiKhoan" class="form-label ">Tên tài khoản <span
                                                style="color: red">(*)</span></label>
                                        <form:input type="text" class="form-control" path="taiKhoan" id="taiKhoan"/>
                                        <form:errors path="taiKhoan" cssStyle="color: red"/>
                                    </div>
                                    <div class="mb-3">
                                        <label for="hoTen" class="form-label ">Họ và tên <span
                                                style="color: red">(*)</span></label>
                                        <form:input type="text" class="form-control" path="hoTen" id="hoTen"/>
                                        <form:errors path="hoTen" cssStyle="color: red"/>
                                    </div>
                                    <div class="mb-3">
                                        <label for="diaChi" class="form-label">Địa chỉ <span
                                                style="color: red">(*)</span></label>
                                        <form:textarea style="height: 125px" type="text" class="form-control"
                                                       path="diaChi" id="diaChi"/>
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
                                        <label for="sdt" class="form-label">Số điện thoại <span
                                                style="color: red">(*)</span></label>
                                        <form:input type="text" class="form-control" path="sdt" id="sdt"/>
                                        <form:errors path="sdt" cssStyle="color: red"/>
                                    </div>
                                    <div class="mb-3">
                                        <div class="row">
                                            <div class="col-8">
                                                <label class="form-label">Danh sách địa chỉ nhận hàng</label><br>
                                            </div>
                                            <div class="col-4">
                                                <button type="button" class="btn btn-success" data-bs-toggle="modal"
                                                        data-bs-target="#exampleModal">
                                                    +
                                                </button>
                                            </div>
                                        </div>
                                        <table class="table">
                                            <thead>
                                            <tr>
                                                <th scope="col">STT</th>
                                                <th scope="col">Thông tin địa chỉ</th>
                                                <th scope="col">Thao tác</th>
                                            </tr>
                                            </thead>
                                            <tbody>
                                            <c:forEach items="${lstdChi}" var="diaChi" varStatus="i">
                                                <tr>
                                                    <th scope="row">${i.index+1}</th>
                                                    <td>
                                                        <div style="display: flex; align-items: center;">
                                                            <div>
                                                                <span style="font-size: 16px">
                                                                    <strong>
                                                                        <span style="text-transform: none">${diaChi.diaChi}</span>
                                                                    </strong>
                                                                </span><br>
                                                                <div style="font-size: 13px">
                                                                    <span>${diaChi.sdt}</span><br>
                                                                    <span>${diaChi.hoTen}</span>
                                                                </div>
                                                            </div>
                                                        </div>
                                                    </td>
                                                    <td>
                                                        <a class="btn btn-danger" title="xóa địa chỉ"
                                                           style="color: whitesmoke"
                                                           onclick="xoaDiaChi({id:`${diaChi.id}`,idKH:`${khachHang.id}`})">
                                                            Xóa
                                                        </a>
                                                    </td>
                                                </tr>
                                            </c:forEach>
                                            </tbody>
                                        </table>
                                    </div>
                                </div>
                            </div>
                            <br>
                            <div style="text-align: center">
                                <button class="btn btn-primary" onclick="add(event)">Xác nhận
                                </button>
                            </div>
                        </form:form>
                    </div>
                </div>
                <!-- Modal DiaChi-->
                <div class="modal" id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel"
                     aria-hidden="true">
                    <div class="modal-dialog">
                        <div class="modal-content">
                            <div class="modal-header">
                                <h5 class="modal-title" id="exampleModalLabel">Thêm địa chỉ giao hàng</h5>
                                <button type="button" class="btn-close" data-bs-dismiss="modal"
                                        aria-label="Close"></button>
                            </div>
                            <div class="modal-body">
                                <div class="form-floating mb-3">
                                    <input type="text" name="hoTen" class="form-control" id="floatingHoTenNhan"
                                           placeholder="Họ và tên" required>
                                    <label for="floatingHoTenNhan">Họ và tên</label>
                                </div>
                                <div class="form-floating mb-3">
                                    <input type="text" name="sdtNhan" class="form-control" id="floatingSDTNhan"
                                           placeholder="Số điện thoại" required>
                                    <label for="floatingSDTNhan">Số điện thoại</label>
                                </div>
                                <div class="form-floating mb-3">
                                    <input type="text" name="diaChiNhan" class="form-control" id="floatingDiaChiNhan"
                                           placeholder="Địa chỉ" required>
                                    <label for="floatingDiaChiNhan">Địa chỉ</label>
                                </div>
                            </div>
                            <div class="modal-footer">
                                <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Đóng</button>
                                <button type="submit" onclick="themDiaChi({idkh:`${khachHang.id}`})"
                                        class="btn btn-primary">Xác nhận
                                </button>
                            </div>
                        </div>
                    </div>
                </div>
            </section>
        </div>
    </div>
</div>
<!-- Scripts -->
<script src="https://cdn.jsdelivr.net/npm/jquery@3.5.0/dist/jquery.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/select2@4.1.0-rc.0/dist/js/select2.min.js"></script>
<script>

    function xoaDiaChi(data) {
        Swal.fire({
            title: "Bạn có muốn xóa địa chỉ không ?",
            showDenyButton: true,
            confirmButtonText: "Có",
            denyButtonText: `Không`
        }).then((result) => {
            /* Read more about isConfirmed, isDenied below */
            if (result.isConfirmed) {
                window.location.href = "/admin/khach-hang/xoa-dia-chi/" + data.id + "?idKH=" + data.idKH;
            } else if (result.isDenied) {
                return fasle;
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
            } else if (result.isDenied) {
                return false;
            }
        });
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

        var hoTen = document.getElementById("floatingHoTenNhan").value;
        var sdt = document.getElementById("floatingSDTNhan").value;
        var diaChi = document.getElementById("floatingDiaChiNhan").value;
        var regexSdt = /(03|05|07|08|09|01[2|6|8|9])+([0-9]{8})\b/;
        if (hoTen == "" || sdt == "" || diaChi == "") {
            Swal.fire({
                title: "Lỗi!",
                icon: "error"
            });
        } else if (!regexSdt.test(sdt)) {
            Swal.fire({
                title: "Sai định dạng số điện thoại!",
                icon: "error"
            });
        } else {
            window.location.href = "/admin/khach-hang/them-dia-chi/" + data.idkh + "?hoTen=" + hoTen + "&sdt=" + sdt + "&diaChi=" + diaChi;
        }
    }
</script>
</body>
</html>