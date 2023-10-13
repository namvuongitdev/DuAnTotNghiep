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
                        <li class="breadcrumb-item active">Nhân viên</li>
                    </ol>
                </nav>
            </div><!-- End Page Title -->
            <section class="section">
                <%--            Table--%>
                <div class="row">
                    <div class="col-lg-12">

                        <div class="card">
                            <div class="card-body row">
                                <h5 class="card-title col-10">Danh sách nhân viên</h5>
                                <div class="col-2 card-title">
                                    <button type="button" class="btn btn-primary" title="Thêm dữ liệu">
                                        <a href="/nhan-vien/view-add" style="color: white"><i class="bi bi-plus-circle"></i></a>
                                    </button>
                                </div>

                                <div class="row">
                                    <div class="col-sm-12">
                                        <form method="get"  action="/nhan-vien/filter" modelAttribute="${filterNhanVien}">
                                            <jsp:include page="../nhanVien/filter.jsp"/>
                                        </form>
                                    </div>
                                </div>

                                <!-- Table with stripped rows -->
                                <table class="table">
                                    <thead>
                                    <tr>
                                        <th scope="col">STT</th>
                                        <th scope="col">Họ và tên</th>
                                        <th scope="col">SĐT</th>
                                        <th scope="col">Email</th>
                                        <th scope="col">Tài khoản</th>
                                        <th scope="col">Ngày tạo</th>
                                        <th scope="col">Ngày sửa</th>
                                        <th scope="col">Trạng thái</th>
                                        <th scope="col">Thao tác</th>
                                    </tr>
                                    </thead>
                                    <tbody>
                                    <c:forEach items="${listNhanVien.content}" var="nv" varStatus="i">
                                        <tr>
                                            <th scope="row">${i.index + (listNhanVien.number + 1 != 1 ? ((listNhanVien.number + 1) * listNhanVien.size) -(listNhanVien.size - 1) : listNhanVien.number + 1)}</th>
                                            <td>${nv.hoTen}</td>
                                            <td>${nv.sdt}</td>
                                            <td>${nv.email}</td>
                                            <td>${nv.taiKhoan}</td>
                                            <td><fmt:formatDate value="${nv.ngayTao}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
                                            <td><fmt:formatDate value="${nv.ngaySua}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
                                            <td>
                                                <button  style="--bs-btn-padding-y: .25rem; --bs-btn-padding-x: .5rem; --bs-btn-font-size: .75rem;" type="button"
                                                         class="${nv.trangThai == 0 ? 'btn btn-success' : 'btn btn-danger'}">
                                                        ${nv.trangThai == 0 ? 'Đang làm việc' : 'Đã nghỉ việc'}</button>
                                            </td>
                                            <td>
                                                <button type="button" class="btn btn-success" title="Sửa dữ liệu" onclick="myFunction()">
                                                    <a class="text-white" style="text-decoration: none" href="/nhan-vien/view-update/${nv.id}"><i class="bi bi-pencil"></i></a>
                                                </button>
                                                <button type="button" class="btn btn-danger" title="Dừng làm việc" onclick="if(confirm('Bạn có chắc chắn muốn dừng làm việc không?')){window.location.href = '/nhan-vien/stop/${nv.id}';}
                                                        else{alert('Dừng làm việc thất bại!')}"><i class="bi bi-sign-stop"></i>
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
                                            <li class="page-item ${listNhanVien.number + 1<=1?"disabled":""}"><a class="page-link"
                                                                                                                href="${url}${(listNhanVien.number + 1) - 1}"><</a>
                                            </li>
                                            <c:forEach begin="1" end="${listNhanVien.getTotalPages()}" var="i">
                                                <li class="page-item"><a class="page-link ${i == listNhanVien.number + 1 ? 'active ' : ''}"
                                                                         href="${url}${i}">${i}</a></li>
                                            </c:forEach>
                                            <li class="page-item ${listNhanVien.number + 1 >= listNhanVien.getTotalPages() ? "disabled":  ""}">
                                                <a class="page-link"
                                                   href="${url}${(listNhanVien.number + 1) + 1}">></a>
                                            </li>
                                        </ul>
                                    </nav>
                                </div>
                            </div>
                        </div>

                    </div>
                </div>
            </section>
        </div>
    </div>
</div>
</body>
</html>