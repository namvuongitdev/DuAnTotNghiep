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
    <script src="
              https://cdn.jsdelivr.net/npm/sweetalert2@11.10.0/dist/sweetalert2.all.min.js
              "></script>
    <link href="
          https://cdn.jsdelivr.net/npm/sweetalert2@11.10.0/dist/sweetalert2.min.css
         " rel="stylesheet">
</head>
<style>
    /* The Modal (background) */
    .modal {
        display: none; /* Hidden by default */
        position: fixed; /* Stay in place */
        z-index: 1; /* Sit on top */
        padding-top: 100px; /* Location of the box */
        left: 0;
        top: 0;
        width: 100%; /* Full width */
        height: 100%; /* Full height */
        overflow: auto; /* Enable scroll if needed */
        background-color: rgb(0, 0, 0); /* Fallback color */
        background-color: rgba(0, 0, 0, 0.4); /* Black w/ opacity */
    }

    /* Modal Content */
    .modal-content-1 {
        background-color: #fefefe;
        margin: auto;
        padding: 20px;
        border: 1px solid #888;
        width: 50%;
    }

    /* The Close Button */
    .close {
        color: #aaaaaa;
        float: right;
        font-size: 28px;
        font-weight: bold;
    }

    .close:hover,
    .close:focus {
        color: #000;
        text-decoration: none;
        cursor: pointer;
    }
</style>
<body>
<%--navbar--%>
<jsp:include page="../../sidebar/navbar.jsp"/>
<%--sidebar--%>
<div class="row flex-nowrap" style="background-color: #dddddd">
    <jsp:include page="../../sidebar/sidebar.jsp"/>
    <div class="col py-3">
        <div class="pagetitle">
            <nav>
                <ol class="breadcrumb">
                    <li class="breadcrumb-item"><a href="/admin/thong-ke"
                                                   style="text-decoration: none; color: black">Trang chủ</a>
                    </li>
                    <li class="breadcrumb-item"><a href="/admin/khuyen-mai/"
                                                   style="text-decoration: none; color: black">Quản lý Khuyến mại</a>
                    </li>
                    <li class="breadcrumb-item">Chi tiết</li>
                    <li class="breadcrumb-item" style="color: #1da1f2">${dataKhuyenMai.ma}</li>
                </ol>
            </nav>
        </div>
        <div class="card">
            <div class="card-body row">
                <form:form class="row" action="${url}" method="post" modelAttribute="khuyenMaiRequest"
                           id="formKhuyenMai" onsubmit="return false">
                    <div class="col-sm-3">
                    </div>
                    <div class="col-sm-6">
                        <div class="mb-3 form-floating">
                            <input type="text" class="form-control" name="ten" id="ten" value="${dataKhuyenMai.ten}">
                            <label for="ten">Tên giảm giá</label>
                            <form:errors path="ten" cssStyle="color: red"/>
                        </div>
                        <div class="row">
                            <div class="col l-3">
                                <div class="mb-3 form-floating">
                                    <input type="text" class="form-control" name="moTa" id="moTa"
                                           value="${dataKhuyenMai.moTa}">
                                    <label for="moTa">Mô tả</label>
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col l-3">
                                <div class="mb-3 form-floating">
                                    <input type="date" class="form-control" name="ngayBatDau" id="ngayBatDau"
                                           value="${dataKhuyenMai.ngayBatDau}">
                                    <label for="ngayBatDau">Ngày bắt đầu</label>
                                    <form:errors path="ngayBatDau" cssStyle="color: red"/>
                                    <c:if test="${errorNgay != null}">
                                        <p style="color: #E43535">${errorNgay}</p>
                                    </c:if>
                                </div>
                            </div>
                            <div class="col l-3">
                                <div class="mb-3 form-floating">
                                    <input type="date" class="form-control" name="ngayKetThuc" id="ngayKetThuc"
                                           value="${dataKhuyenMai.ngayKetThuc}">
                                    <label for="ngayKetThuc">Ngày kết thúc</label>
                                    <form:errors path="ngayKetThuc" cssStyle="color: red"/>
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div>
                                <button class="btn btn-primary"
                                        onclick="confirmKhuyenMai(event , `${dataKhuyenMai.ma != null ? 'update' : 'Thêm'}`)">${dataKhuyenMai.ma != null ? "Cập nhập" : "Thêm"}</button>
                            </div>
                        </div>
                        <div class="col-sm-3">
                        </div>
                    </div>
                </form:form>
            </div>
        </div>

        <c:if test="${listChiTietKhuyenMai.content != null}">
            <div class="card" style="margin-top: 10px">
                <div class="card-body row">
                    <jsp:include page="chi-tiet.jsp"></jsp:include>
                </div>
            </div>
        </c:if>
    </div>
    <c:if test="${dataKhuyenMai != null}">
        <jsp:include page="modal-san-pham.jsp"></jsp:include>
    <script src="/js/khuyenMai/khuyen-mai.js"></script>
    <script src="/js/khuyenMai/modal.js"></script>
    </c:if>
    <script>

        function confirmKhuyenMai(event, message) {
            event.preventDefault(); // Prevent the default form submission behavior
            Swal.fire({
                title: "Bạn có muốn " + message + " không?",
                showDenyButton: true,
                confirmButtonText: "Có",
                denyButtonText: `Không`
            }).then((result) => {
                /* Read more about isConfirmed, isDenied below */
                if (result.isConfirmed) {
                    document.getElementById("formKhuyenMai").submit();
                } else {
                    return false;
                }
            });
        }

        if (${error != null}) {
            message.fire({
                text: "${error}",
                icon: "error"
            });
        }

        if (${success != null}) {
            message.fire({
                text: "${success}",
                icon: "success"
            });
        }
    </script>
</body>