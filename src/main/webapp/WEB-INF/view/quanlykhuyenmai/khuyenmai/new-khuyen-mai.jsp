<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css"
          rel="stylesheet" integrity="sha384-9ndCyUaIbzAi2FUVXJi0CjmCapSmO7SnpJef0486qhLnuZ2cdeRhO02iuK6FUUVM"
          crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"
            integrity="sha384-geWF76RCwLtnZ8qwWowPQNguL3RmwHVBC9FhGdlKrxdiJJigb/j/68SIy3Te4Bkz"
            crossorigin="anonymous"></script>

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
    .modal-content-1{
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
            <form class="row" action="/admin/khuyen-mai/create" method="post" modelAttribute="${khuyenMai}">
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
                                    <input type="date" class="form-control" name="ngayBatDau" id="ngayBatDau" value="${dataKhuyenMai.ngayBatDau}">
                                    <label for="ngayBatDau">Ngày bắt đầu</label>
                                    <form:errors path="ngayBatDau" cssStyle="color: red"/>
                                </div>
                            </div>
                            <div class="col l-3">
                                <div class="mb-3 form-floating">
                                    <input type="date" class="form-control" name="ngayKetThuc" id="ngayKetThuc" value="${dataKhuyenMai.ngayKetThuc}">
                                    <label for="ngayKetThuc">Ngày kết thúc</label>
                                    <form:errors path="ngayKetThuc" cssStyle="color: red"/>
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <c:choose>
                                <c:when test="${dataKhuyenMai == null}">
                                    <div>
                                        <button class="btn btn-primary">Xác nhận</button>
                                    </div>
                                </c:when>
                                <c:otherwise>
                                    <div>
                                        <button class="btn btn-primary">Update</button>
                                    </div>
                                </c:otherwise>
                            </c:choose>
                        </div>
                        <div class="col-sm-3">
                        </div>
                    </div>
            </form>
              <c:if test="${listChiTietKhuyenMai.content != null}">
                  <jsp:include page="chi-tiet.jsp"></jsp:include>
              </c:if>
        </div>
    </div>
        <c:if test="${dataKhuyenMai != null}">
            <jsp:include page="modal-san-pham.jsp"></jsp:include>
        <script src="/js/khuyenMai/khuyen-mai.js"></script>
        <script src="/js/khuyenMai/modal.js"></script>
        </c:if>

</body>