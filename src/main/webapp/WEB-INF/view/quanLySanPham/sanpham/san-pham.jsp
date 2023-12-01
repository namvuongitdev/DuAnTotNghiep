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
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.1/font/bootstrap-icons.css">
    <style>
        .table .tenSP:hover {
            color: #1ab7ea; /* Màu nền khi hover */
        }
        .sale{
            position: relative;
            text-align: center;
            color: white;
        }
        .top-right {
            position: absolute;
            top: 8px;
            right: 16px;
            color: red;
        }
    </style>
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
                <h3>Sản phẩm</h3>
                <nav>
                    <ol class="breadcrumb">
                        <li class="breadcrumb-item"><a href="/admin/trang-chu" style="text-decoration: none; color: black">Trang chủ</a></li>
                        <li class="breadcrumb-item">Quản lý sản phẩm</li>
                        <li class="breadcrumb-item active">Sản phẩm</li>
                    </ol>
                </nav>
            </div><!-- End Page Title -->
            <section class="section">
                <div class="card">
                    <div class="card-body row">
                        <h5 class="card-title col-9">Danh sách sản phẩm</h5>
                        <div class="col l-3">
                            <a href="/admin/san-pham/new" class="btn btn-primary">Tạo Mới</a>
                        </div>
                        <br><br>
                        <%--                filter sản phẩm--%>
                        <div class="row">
                            <div class="col-sm-12">
                                <form  action="/admin/san-pham/filter" modelAttribute="${filterSanPham}">
                                    <div class="row">
                                        <jsp:include page="filter-san-pham.jsp"></jsp:include>
                                    </div>
                                </form>
                            </div>
                        </div>
                        <!-- Table with stripped rows -->
                        <table class="table" style="font-size: 15px">
                            <thead style="text-align: center">
                            <tr>
                                <th scope="col">STT</th>
                                <th scope="col">Ảnh</th>
                                <th scope="col">Mã sản phẩm</th>
                                <th scope="col">Tên sản phẩm</th>
                                <th scope="col">Giá</th>
                                <th scope="col">Giảm giá</th>
                                <th scope="col">Ngày tạo</th>
                                <th scope="col">Ngày sửa</th>
                                <th scope="col">Trang thái</th>
                                <th scope="col">Thao tác</th>
                            </tr>
                            </thead>
                            <tbody style="text-align: center;">
                            <c:forEach items="${listSanPham.content}" var="sanPham" varStatus="i">
                                <tr>
                                    <th scope="row">${i.index + (listSanPham.number + 1 != 1 ? ((listSanPham.number + 1) * listSanPham.size) -(listSanPham.size - 1) : listSanPham.number + 1)}</th>
                                    <td class="sale">
                                        <img src="/image/${sanPham.img}"alt="" style="width: 100px; height: 100px">
                                        <c:if test="${sanPham.sanPhamKhuyenMais.size() > 0}">
                                            <c:forEach items="${sanPham.sanPhamKhuyenMais}" var="km">
                                                <c:if test="${km.khuyenMai.trangThai == 1 && km.trangThai == 1}">
                                                    <div class="top-right border">Sale</div>
                                                </c:if>
                                            </c:forEach>
                                        </c:if>
                                    </td>
                                    <td>${sanPham.ma}</td>
                                    <td class="tenSP" onclick="window.location.href='/admin/san-pham/hien-thi/${sanPham.id}'">${sanPham.ten}</td>
                                    <td>
                                        <fmt:formatNumber pattern="#,###" value="${sanPham.giaBan}"></fmt:formatNumber> ₫
                                    </td>
                                    <td>
                                        <c:forEach items="${sanPham.sanPhamKhuyenMais}" var="sanPhamKhuyenMai">
                                            <c:if test="${sanPhamKhuyenMai != null}">
                                                <c:if test="${sanPhamKhuyenMai.khuyenMai.trangThai == 1}">
                                                    <c:if test="${sanPhamKhuyenMai.trangThai == 1}">
                                                        <p>
                                                            <c:if test="${sanPhamKhuyenMai.loaiGiamGia == true}">
                                                                <span style="color: #E43535">-<fmt:formatNumber pattern="####" value="${sanPhamKhuyenMai.mucGiam}"></fmt:formatNumber>%</span>
                                                            </c:if>
                                                            <c:if test="${sanPhamKhuyenMai.loaiGiamGia == false}">
                                                                <span style="color: #E43535">-<fmt:formatNumber pattern="#,###" value="${sanPhamKhuyenMai.mucGiam}"></fmt:formatNumber> ₫</span>
                                                            </c:if>
                                                        </p>
                                                        <p style="color: red;">
                                                            <fmt:formatNumber pattern="#,###" value="${sanPhamKhuyenMai.donGiaSauKhiGiam}"></fmt:formatNumber> ₫
                                                        </p>
                                                    </c:if>
                                                </c:if>
                                            </c:if>
                                        </c:forEach>
                                    </td>
                                    <td>
                                        <fmt:formatDate value="${sanPham.ngayTao}" pattern="yyyy-MM-dd HH:mm:ss"/>
                                    </td>
                                    <td>
                                        <fmt:formatDate value="${sanPham.ngaySua}" pattern="yyyy-MM-dd HH:mm:ss"/>
                                    </td>
                                    <td>
                                        <button  style="--bs-btn-padding-y: .25rem; --bs-btn-padding-x: .5rem; --bs-btn-font-size: .75rem;" type="button"
                                                 class="${sanPham.trangThai == 0 ? 'btn btn-success' : 'btn btn-danger'}">
                                                ${sanPham.trangThai == 0 ? 'Kinh doanh' : 'Ngừng kinh doanh'}</button>
                                    </td>
                                    <td>
                                        <button type="button" class="btn btn-danger" title="Ngừng kinh doanh" onclick="if(confirm('Bạn có chắc chắn muốn ngừng kinh doanh không?')){window.location.href = '/admin/san-pham/stop/${sanPham.id}';}
                                                else{alert('Ngừng kinh doanh thất bại!')}"><i class="bi bi-sign-stop"></i>
                                        </button>
                                    </td>
                                </tr>
                            </c:forEach>
                            </tbody>
                        </table>
                        <!-- End Table with stripped rows -->
                        <%--                  phân trang--%>
                        <div class="container-fluid mt-5">
                            <nav aria-label="Page navigation example">
                                <ul class="pagination justify-content-center">
                                    <li class="page-item ${listSanPham.number + 1<=1?"disabled":""}"><a class="page-link"
                                                                                                        href="${url}${(listSanPham.number + 1) - 1}"><</a>
                                    </li>
                                    <c:forEach begin="1" end="${listSanPham.getTotalPages()}" var="i">
                                        <li class="page-item"><a class="page-link ${i == listSanPham.number + 1 ? 'active ' : ''}"
                                                                 href="${url}${i}">${i}</a></li>
                                    </c:forEach>
                                    <li class="page-item ${listSanPham.number + 1 >= listSanPham.getTotalPages() ? "disabled":  ""}">
                                        <a class="page-link"
                                           href="${url}${(listSanPham.number + 1) + 1}">></a>
                                    </li>
                                </ul>
                            </nav>
                        </div>
                    </div>
                </div>
            </section>
        </div>
    </div>
</div>

</body>
</html>
