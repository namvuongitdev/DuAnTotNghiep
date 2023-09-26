<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
                    <div class="col-sm-5">
                        <div class="mb-3 form-floating">
                            <input type="text" class="form-control" name="ten" id="ten">
                            <label for="ten">Tên giảm giá</label>
                        </div>
                        <div class="row">
                            <div class="col l-3">
                                <div class="form-check">
                                    <input type="radio" class="form-check-input" id="%" name="loaiGiamGia" value="true"
                                           checked>
                                    <label class="form-check-label" for="%">%</label>
                                </div>
                                <div class="form-check">
                                    <input type="radio" class="form-check-input" id="VND" name="loaiGiamGia"
                                           value="false">
                                    <label class="form-check-label" for="VND">VND</label>
                                </div>
                            </div>
                            <div class="col l-3">
                                <div class="mb-3 form-floating">
                                    <input type="text" class="form-control" name="mucGiamGia" id="mucGiamGia">
                                    <label for="mucGiamGia">Mức giảm giá</label>
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col l-3">
                                <div class="mb-3 form-floating">
                                    <input type="date" class="form-control" name="ngayBatDau" id="ngayBatDau">
                                    <label for="ngayBatDau">Ngày bắt đầu</label>
                                </div>
                            </div>
                            <div class="col l-3">
                                <div class="mb-3 form-floating">
                                    <input type="date" class="form-control" name="ngayKetThuc" id="ngayKetThuc">
                                    <label for="ngayKetThuc">Ngày kết thúc</label>
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div>
                                <button class="btn btn-primary">Xác nhận</button>
                            </div>
                        </div>

                    </div>
                    <div class="col-sm-7">
                        <table>
                            <thead>
                            <tr>
                                <th scope="col">STT</th>
                                <th scope="col">Chọn</th>
                                <th scope="col">Mã</th>
                                <th scope="col">Tên Sản Phẩm</th>
                                <th scope="col">Giá</th>
                                <th scope="col">Trang Thái</th>
                            </tr>
                            </thead>
                            <tbody>
                            <c:forEach items="${listCtspSanPham.content}" var="ctsp" varStatus="i">
                                <tr>
                                    <th scope="row">${i.index+page}</th>
                                    <td>
                                        <input type="checkbox" name="chiTietSanPhams" value="${ctsp.id}">
                                    </td>
                                    <td>${ctsp.sanPham.ma}</td>
                                    <td>${ctsp.sanPham.ten} [${ctsp.mauSac.ten}] [${ctsp.size.ten}]</td>
                                    <td><fmt:formatNumber pattern="#,###" value="${ctsp.sanPham.giaBan}">
                                    </fmt:formatNumber></td>
                                    <td>
                                        <button style="--bs-btn-padding-y: .25rem; --bs-btn-padding-x: .5rem; --bs-btn-font-size: .75rem;"
                                                type="button"
                                                class="${ctsp.sanPham.trangThai == 0 ? 'btn btn-success' : 'btn btn-danger'}">
                                                ${ctsp.sanPham.trangThai == 0 ? 'Kinh doanh' : 'Ngừng kinh doanh'}</button>
                                    </td>
                                </tr>
                            </c:forEach>
                            </tbody>
                        </table>
                    </div>
            </form>
            <div class="row">

            </div>
        </div>
    </div>
</body>