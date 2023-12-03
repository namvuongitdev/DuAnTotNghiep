<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <link rel="shortcut icon" href="#">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css"
          rel="stylesheet" integrity="sha384-9ndCyUaIbzAi2FUVXJi0CjmCapSmO7SnpJef0486qhLnuZ2cdeRhO02iuK6FUUVM"
          crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"
            integrity="sha384-geWF76RCwLtnZ8qwWowPQNguL3RmwHVBC9FhGdlKrxdiJJigb/j/68SIy3Te4Bkz"
            crossorigin="anonymous"></script>
</head>
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
            <form class="row" action="/admin/khuyen-mai/filter" modelAttrubute="${khuyenMai}">
                <div class="col-sm-2">
                    <div>
                        <label for="search">Tìm kiếm</label>
                        <input class="form-control" type="text" name="search" id="search"
                               placeholder="Tìm kiếm mã , tên" value="${filter.search}">
                    </div>
                </div>
                <div class="col-sm-2">
                    <label for="trangThai">Trang thái</label>
                    <select name="trangThai" id="trangThai" class="form-select">
                        <option value="">Tất cả</option>
                        <option value="1" ${filter.trangThai == "1" ? 'selected' : ''}>
                            Kích hoạt
                        </option>
                        <option value="0" ${filter.trangThai == "0" ? 'selected' : ''}>
                            Ngừng kích hoạt
                        </option>
                        <option value="2" ${filter.trangThai == "2" ? 'selected' : ''}>
                            Chưa được kích hoạt
                        </option>
                        <option value="3" ${filter.trangThai == "3" ? 'selected' : ''}>
                            Hết hạn
                        </option>
                    </select>
                </div>
                <div class="col-sm-3" style="margin-top: 21px ; display: flex">
                    <div>
                        <button class="btn btn-primary">Tìm kiếm</button>
                    </div>
                    <div style="margin-left: 6px">
                        <a class="btn btn-warning" href="/admin/khuyen-mai/">
                            Làm mới
                        </a>
                    </div>
                </div>
                <div class="col-sm-2">
                    <div style="margin-top: 21px">
                        <a href="/admin/khuyen-mai/new" class="btn btn-primary"> + Tạo mới</a>
                    </div>
                </div>
            </form>
            <div class="row" style="margin-top: 60px">
                <table class="table">
                    <thead>
                    <tr>
                        <th scope="col">STT</th>
                        <th scope="col">Mã</th>
                        <th scope="col">Tên Khuyến mãi</th>
                        <th scope="col">Ngày bắt đầu</th>
                        <th scope="col">Ngày kết thúc</th>
                        <th scope="col">Trang Thái</th>
                        <th scope="col">Thao tác</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${khuyenMais.content}" var="khuyenMai" varStatus="i">
                        <tr>
                            <th scope="row">${i.index+ (khuyenMais.number + 1 != 1 ? ((khuyenMais.number + 1) * khuyenMais.size) -(khuyenMais.size - 1) : khuyenMais.number + 1)}</th>
                            <td>
                                    ${khuyenMai.ma}
                            </td>
                            <td>${khuyenMai.ten}</td>
                            <td>
                                <fmt:formatDate value="${khuyenMai.ngayBatDau}" pattern="yyyy-MM-dd"/>
                            </td>
                            <td>
                                <fmt:formatDate value="${khuyenMai.ngayKetThuc}" pattern="yyyy-MM-dd"/>
                            </td>

                            <c:choose>
                                <c:when test="${khuyenMai.trangThai == 1}">
                                   <td> <button class="btn btn-success rounded-pill">kích hoạt</button></td>
                                </c:when>
                                <c:when test="${khuyenMai.trangThai == 2}">
                                   <td> <button class="btn btn-warning rounded-pill">chưa bắt đầu</button></td>
                                </c:when>
                                <c:when test="${khuyenMai.trangThai == 4}">
                                    <td style="color: red">Đã xoá</td>
                                </c:when>
                                <c:otherwise>
                                    <td> <button class="btn btn-danger rounded-pill">hết hạn</button></td>
                                </c:otherwise>
                            </c:choose>
                            <td style="display: flex">
                                <div>
                                    <button style="margin-right: 10px" type="button" class="btn btn-success" title="chi tiết"
                                            onclick="getKhuyenMaiById(`${khuyenMai.id}`)">
                                        chi tiết
                                    </button>
                                </div>
                                <div>
                                    <c:if test="${khuyenMai.trangThai != 4}">
                                        <form action="/admin/khuyen-mai/delete?idKM=${khuyenMai.id}" method="post">
                                            <button class="btn btn-danger" onclick="return deleteKhuyenMai()">Huỷ</button>
                                        </form>
                                    </c:if>
                                </div>
                            </td>

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
                                    <li class="page-item"><a
                                            class="page-link ${i == (khuyenMais.number + 1) ? 'active ' : ''}"
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
</div>
</body>
<script>
    function getKhuyenMaiById(id) {
        window.location.href = '/admin/khuyen-mai/detail?id=' + id;
    }

    function deleteKhuyenMai() {
        if (confirm('bạn có chắc muốn xoá không nếu xoá sẽ không khôi phục được')) {
            return true;
        } else {
            return false;
        }
    }
</script>