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
    <svg xmlns="http://www.w3.org/2000/svg" style="display: none;">
        <symbol id="exclamation-triangle-fill" fill="currentColor" viewBox="0 0 16 16">
            <path d="M8.982 1.566a1.13 1.13 0 0 0-1.96 0L.165 13.233c-.457.778.091 1.767.98 1.767h13.713c.889 0 1.438-.99.98-1.767L8.982 1.566zM8 5c.535 0 .954.462.9.995l-.35 3.507a.552.552 0 0 1-1.1 0L7.1 5.995A.905.905 0 0 1 8 5zm.002 6a1 1 0 1 1 0 2 1 1 0 0 1 0-2z"/>
        </symbol>
        <symbol id="info-fill" fill="currentColor" viewBox="0 0 16 16">
            <path d="M8 16A8 8 0 1 0 8 0a8 8 0 0 0 0 16zm.93-9.412-1 4.705c-.07.34.029.533.304.533.194 0 .487-.07.686-.246l-.088.416c-.287.346-.92.598-1.465.598-.703 0-1.002-.422-.808-1.319l.738-3.468c.064-.293.006-.399-.287-.47l-.451-.081.082-.381 2.29-.287zM8 5.5a1 1 0 1 1 0-2 1 1 0 0 1 0 2z"/>
        </symbol>
    </svg>
</head>
<style>
    table {
        border-collapse: collapse;
        width: 100%;
    }

    th, td {
        padding: 8px;
        text-align: left;
        border-bottom: 1px solid #ddd;
    }

    tr:hover {
        background-color: #f5f5f5;
    }

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
<div class="row flex-nowrap">
    <jsp:include page="../../sidebar/sidebar.jsp"/>
    <div class="col py-3">
        <div class="container">
            <c:if test="${success != null}">
                <jsp:include page="../../notiface/success.jsp"></jsp:include>
            </c:if>

            <c:if test="${error != null}">
                <jsp:include page="../../notiface/error.jsp"></jsp:include>
            </c:if>
            <form:form class="row" action="${url}" method="post" modelAttribute="khuyenMaiRequest">
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
                                <input type="text" class="form-control" name="moTa" id="moTa" ${dataKhuyenMai.moTa}>
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
                            <button class="btn btn-primary">Xác nhận</button>
                        </div>
                    </div>
                    <div class="col-sm-3">
                    </div>
                </div>
            </form:form>
            <c:if test="${listChiTietKhuyenMai.content != null}">
                <jsp:include page="chi-tiet.jsp"></jsp:include>
            </c:if>
        </div>
    </div>
    <c:if test="${dataKhuyenMai != null}">
        <jsp:include page="modal-san-pham.jsp"></jsp:include>
    <script src="/js/khuyenMai/khuyen-mai.js"></script>
    <script src="/js/khuyenMai/modal.js"></script>
    <script src="/js/khuyenMai/validateForm.js"></script>
    </c:if>
</body>