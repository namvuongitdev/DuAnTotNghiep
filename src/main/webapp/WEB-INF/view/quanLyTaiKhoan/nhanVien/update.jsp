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
                        <li class="breadcrumb-item"><a href="/trangchu" style="text-decoration: none; color: black">Trang chủ</a></li>
                        <li class="breadcrumb-item ">Quản lý tài khoản</li>
                        <li class="breadcrumb-item "><a href="/nhan-vien/hien-thi" style="text-decoration: none; color: black">Nhân viên</a></li>
                        <li class="breadcrumb-item active">Sửa dữ liệu</li>
                    </ol>
                </nav>
            </div><!-- End Page Title -->
            <section class="section">
                <div class="card">
                    <div class="card-body row">
                        <h5 class="card-title">Thông tin sản phẩm</h5>
                        <br><br>
                        <%--@elvariable id="nhanVien" type=""--%>
                        <form:form action="/nhan-vien/update/${nhanVien.id}" method="post" modelAttribute="nhanVien">
                            <div class="row">
                                <div class="col-6">
                                    <div class="mb-3">
                                        <label for="taiKhoan" class="form-label ">Tên tài khoản</label>
                                        <form:input type="text" class="form-control" path="taiKhoan" id="taiKhoan"/>
                                        <form:errors path="taiKhoan" cssStyle="color: red"/>
                                    </div>
                                    <div class="mb-3">
                                        <label for="hoTen" class="form-label ">Họ và tên</label>
                                        <form:input type="text" class="form-control" path="hoTen" id="hoTen"/>
                                        <form:errors path="hoTen" cssStyle="color: red"/>
                                    </div>
                                    <div class="mb-3">
                                        <label for="sdt" class="form-label">Số điện thoại</label>
                                        <form:input type="text" class="form-control" path="sdt" id="sdt"/>
                                        <form:errors path="sdt" cssStyle="color: red"/>
                                    </div>
                                    <div class="mb-3">
                                        <label for="email" class="form-label">Email</label>
                                        <form:input type="text" class="form-control" path="email" id="email"/>
                                        <form:errors path="email" cssStyle="color: red"/>
                                    </div>
                                    <div class="mb-3">
                                        <label for="gioiTinh" class="form-label">Giới tính</label>
                                        <select name="gioiTinh" class="form-select" id="gioiTinh">
                                            <option value="true" ${nhanVien.gioiTinh == true ? 'selected' : '' }>
                                                Nam
                                            </option>
                                            <option value="false" ${nhanVien.gioiTinh == false ? 'selected' : '' }>
                                                Nữ
                                            </option>
                                        </select>
                                    </div>
                                </div>
                                <div class="col-6">
                                    <div class="mb-3">
                                        <label for="matKhau" class="form-label">Mật khẩu</label>
                                        <form:input type="password" class="form-control" path="matKhau" id="matKhau"/>
                                        <form:errors path="matKhau" cssStyle="color: red"/>
                                    </div>
                                    <div class="mb-3">
                                        <label for="diaChi" class="form-label">Địa chỉ</label>
                                        <form:textarea style="height: 125px" type="text" class="form-control" path="diaChi" id="diaChi" />
                                        <form:errors path="diaChi" cssStyle="color: red"/>
                                    </div>
                                    <div class="row mb-3">
                                        <div class="col-11">
                                            <label for="chucVu" class="form-label">Chức vụ</label>
                                            <form:select path="chucVu" class="form-control" id="chucVu">
                                                <form:options items="${listChucVu}" itemLabel="ten" itemValue="id"/>
                                            </form:select>
                                        </div>
                                        <div class="col-1" style="padding-top: 40px">
                                            <a data-bs-toggle="modal" data-bs-target="#exampleModalChucVu">
                                                <i class="bi bi-plus-circle"></i>
                                            </a>
                                        </div>
                                    </div>
                                    <div class="mb-3">
                                        <label for="trangThai" class="form-label">Trạng Thái</label>
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
                                <button class="btn btn-primary" onclick="if(confirm('Bạn có chắc chắn muốn sửa thông tin không?')){}
                                        else{alert('Sửa thông tin thất bại!')} clearLocalStorage()">Xác nhận
                                </button>
                                <a href="/nhan-vien/view-add" class="btn btn-warning" onclick="clearLocalStorage()">Làm Mới</a>
                            </div>
                        </form:form>
                    </div>
                </div>
            </section>
            <%--thêm nhanh chức vụ--%>
            <div class="modal fade" id="exampleModalChucVu" tabindex="-1">
                <div class="modal-dialog modal-dialog-scrollable">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h5 class="modal-title">Thêm dữ liệu</h5>
                            <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                        </div>
                        <%--@elvariable id="chucVu" type=""--%>
                        <form:form method="post" action="/nhan-vien/modal-update-chuc-vu?id=${nhanVien.id}" modelAttribute="chucVu" class="row g-3">
                            <div class="modal-body">
                                <div class="form-floating">
                                    <form:input class="form-control" placeholder="" path="ten"/>
                                    <form:label class="form-label" path="ten">Tên chức vụ:</form:label>
                                    <form:errors path="ten" cssStyle="color: #ff0000"/>
                                </div>
                            </div>
                            <div class="modal-footer">
                                <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Đóng</button>
                                <form:button type="submit" class="btn btn-primary" onclick="if(confirm('Bạn có chắc chắn muốn thêm không?')==true){ }else{ alert('Thêm thất bại');return false; } saveInputValues()">
                                    Xác nhận
                                </form:button>
                            </div>
                        </form:form><!-- End floating Labels Form -->
                    </div>
                </div>
            </div><!-- End Modal Dialog Scrollable-->
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
</script>
</body>
</html>