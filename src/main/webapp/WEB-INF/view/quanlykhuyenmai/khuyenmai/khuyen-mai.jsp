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
            <form class="row" action="/admin/khuyen-mai/filter" modelAttrubute="${khuyenMai}">
                <div class="col-sm-3">
                    <div>
                        <input class="form-control" type="text" name="search" placeholder="Tìm kiếm mã , tên" value="${filter.search}">
                    </div>
                </div>
                <div class="col-sm-2">
                    <select name="loaiGiamGia" class="form-select">
                        <option value="">Loại</option>
                        <option value="true" ${filter.loaiGiamGia == "true" ? 'selected' : ''}>
                            Phần trăm
                        </option>
                        <option value="false" ${filter.loaiGiamGia == "false" ? 'selected' : ''}>
                            VND
                        </option>
                    </select>
                </div>
                <div class="col-sm-2">
                    <select name="trangThai" class="form-select">
                        <option value="">Trang thái</option>
                        <option value="0" ${filter.trangThai == "0" ? 'selected' : ''}>
                            Kích hoạt
                        </option>
                        <option value="1" ${filter.trangThai == "1"? 'selected' : ''}>
                            Chờ
                        </option>
                        <option value="2" ${filter.trangThai == "2" ? 'selected' : ''}>
                            Ngừng kích hoạt
                        </option>
                    </select>
                </div>
                <div class="col-sm-2">
                    <div>
                        <button class="btn btn-primary">Tìm kiếm</button>
                        <a class="btn btn-primary" href="/admin/khuyen-mai/">
                            <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-arrow-clockwise" viewBox="0 0 16 16">
                                <path fill-rule="evenodd" d="M8 3a5 5 0 1 0 4.546 2.914.5.5 0 0 1 .908-.417A6 6 0 1 1 8 2v1z"/>
                                <path d="M8 4.466V.534a.25.25 0 0 1 .41-.192l2.36 1.966c.12.1.12.284 0 .384L8.41 4.658A.25.25 0 0 1 8 4.466z"/>
                            </svg>
                        </a>
                    </div>
                </div>
                <div class="col-sm-2">
                    <div>
                        <a href="/admin/khuyen-mai/new" class="btn btn-primary"> + Tạo mới</a>
                    </div>
                </div>
            </form>
            <div class="row">
                <table>
                    <thead>
                    <tr>
                        <th scope="col">STT</th>
                        <th scope="col">Mã</th>
                        <th scope="col">Tên Khuyến mãi</th>
                        <th scope="col">Giá trị</th>
                        <th scope="col">Ngày bắt đầu</th>
                        <th scope="col">Ngày kết thúc</th>
                        <th scope="col">Trang Thái</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${khuyenMais.content}" var="khuyenMai" varStatus="i">
                        <tr onclick="window.location.href='/san-pham/hien-thi/${khuyenMai.id}'">
                            <th scope="row">${i.index+ (khuyenMais.number + 1 != 1 ? ((khuyenMais.number + 1) * khuyenMais.size) -(khuyenMais.size - 1) : khuyenMais.number + 1)}</th>
                            <td>
                               ${khuyenMai.ma}
                            </td>
                            <td>${khuyenMai.ten}</td>
                            <td>${khuyenMai.mucGiamGia}${khuyenMai.loaiGiamGia}</td>
                            <td>
                                <fmt:formatDate value="${khuyenMai.ngayBatDau}" pattern="yyyy-MM-dd HH:mm:ss"/>
                            </td>
                            <td>
                                <fmt:formatDate value="${khuyenMai.ngayKetThuc}" pattern="yyyy-MM-dd HH:mm:ss"/>
                            </td>
                            <td>
                               ${khuyenMai.trangThai}
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
                <div class="row">
                    <div class="container-fluid mt-5">
                        <nav aria-label="Page navigation example">
                            <ul class="pagination justify-content-center">
                                <li class="page-item ${(khuyenMais.number+1)<=1?"disabled":""}"><a class="page-link"
                                                                                    href="${url}${(khuyenMais.number + 1) - 1}"><</a>
                                </li>
                                <c:forEach begin="1" end="${khuyenMais.getTotalPages()}" var="i">
                                    <li class="page-item"><a class="page-link ${i == (khuyenMais.number + 1) ? 'active ' : ''}"
                                                             href="${url}${i}">${i}</a></li>
                                </c:forEach>
                                <li class="page-item ${khuyenMais.number + 1 >= khuyenMais.getTotalPages() ? "disabled" : ""}">
                                    <a
                                        class="page-link"
                                        href="${url}${(khuyenMais.number+1) + 1}">></a>
                                </li>
                            </ul>
                        </nav>
                    </div>
                </div>
            </div>
        </div>
    </div>
</body>