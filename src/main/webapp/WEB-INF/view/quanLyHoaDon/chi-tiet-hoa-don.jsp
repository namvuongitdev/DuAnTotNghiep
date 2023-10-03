<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
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
<jsp:include page="../sidebar/navbar.jsp"/>
<%--sidebar--%>
<div class="container-fluid">
    <div class="row flex-nowrap">
        <jsp:include page="../sidebar/sidebar.jsp"/>
        <div class="col py-3">
            <div class="pagetitle">
                <h3>Thông tin hóa đơn</h3>
                <nav>
                    <ol class="breadcrumb">
                        <li class="breadcrumb-item"><a href="/trangchu" style="text-decoration: none; color: black">Trang chủ</a></li>
                        <li class="breadcrumb-item">Quản lý hóa đơn</li>
                        <li class="breadcrumb-item active">Thông tin hóa đơn</li>
                    </ol>
                </nav>
            </div><!-- End Page Title -->
            <section class="section">
                <div class="card">
                    <div class="card-body row">
                        <h5 class="card-title">Thông tin chi tiết</h5>
                        <br><br>
                        <form action="" method="post" modelAttribute="">
                            <div class="row">
                                <div class="col-6">
                                    <div class="form-floating mb-3 mt-3">
<<<<<<< HEAD
                                        <input class="form-control" placeholder="" path=""/>
=======
                                        <input class="form-control" value="${hd.ma}" readonly placeholder="" path=""/>
>>>>>>> origin/tien
                                        <label class="form-label" path="">Mã hóa đơn</label>
                                    </div>
                                </div>
                                <div class="col-6">
                                    <div class="form-floating mb-3 mt-3">
<<<<<<< HEAD
                                        <input class="form-control" placeholder="" path=""/>
                                        <label class="form-label" path="">Tên khách hàng</label>
                                    </div>
                                </div>
                                <div class="col-12">
                                    <div class="form-floating mb-3 mt-3">
                                        <textarea class="form-control" path=""></textarea>
                                        <label class="form-label" path="">Địa chỉ</label>
                                    </div>
                                </div>
                                <div style="text-align: center">
                                    <button type="submit" class="btn btn-outline-dark">Cập nhật</button>
                                </div>
=======
                                        <input class="form-control" value="${hd.khachHang.hoTen}" readonly placeholder="" path=""/>
                                        <label class="form-label" path="">Tên khách hàng</label>
                                    </div>
                                </div>
                                <div class="col-6">
                                    <div class="form-floating mb-3 mt-3">
                                        <input class="form-control" value="${hd.ngayTao}" readonly path=""/>
                                        <label class="form-label" path="">Ngày tạo</label>
                                    </div>
                                </div>
                                <div class="col-6">
                                    <div class="form-floating mb-3 mt-3">
                                        <input class="form-control" value="${hd.ngayThanhToan}" readonly path=""/>
                                        <label class="form-label" path="">Ngày thanh toán</label>
                                    </div>
                                </div>
<%--                                <div style="text-align: center">--%>
<%--                                    <button type="submit" class="btn btn-outline-dark">Cập nhật</button>--%>
<%--                                </div>--%>
>>>>>>> origin/tien
                            </div>
                        </form>

                    </div>
                </div><br><br>
                <%--sản phẩm trong hóa đơn--%>
                <div class="card">
                    <div class="card-body row">
                        <h5 class="card-title">Danh sách sản phẩm trong hóa đơn</h5>
                        <br><br>
                        <!-- Table with stripped rows -->
                        <table class="table">
                            <thead>
                            <tr>
                                <th scope="col">STT</th>
                                <th scope="col">Tên sản phẩm</th>
                                <th scope="col">Hình ảnh</th>
                                <th scope="col">Số lượng</th>
                                <th scope="col">Tổng giá</th>
                            </tr>
                            </thead>
                            <tbody>
<<<<<<< HEAD
<%--                                <c:forEach>--%>
<%--                                    <tr>--%>
<%--    --%>
<%--                                    </tr>--%>
<%--                                </c:forEach>--%>
=======
                                <c:forEach items="${lst}" var="hdct" varStatus="i">
                                    <tr>
                                        <td>${i.index+1}</td>
                                        <td>${hdct.sanPham.ten}</td>
                                        <td>${hdct.sanPham.img}</td>
                                        <td>${hdct.sanPham.soLuong}</td>
                                        <td>${hdct.soLuong * hdct.sanPham.giaBan}</td>
                                    </tr>
                                </c:forEach>
>>>>>>> origin/tien
                            </tbody>
                        </table>
                        <!-- End Table with stripped rows -->
                        <%--                phân trang --%>
                        <div class="container-fluid mt-5">
                            <nav aria-label="Page navigation example">
                                <ul class="pagination justify-content-center">
                                    <li class="page-item ${currentPage<=0?"disabled":""}"><a class="page-link" href="/danh-muc/hien-thi/${currentPage-1}"><</a></li>
                                    <c:forEach begin="1" end="${totalPage}" var="i">
                                        <li class="page-item"><a class="page-link" href="/danh-muc/hien-thi/${i-1}">${i}</a></li>
                                    </c:forEach>
                                    <li class="page-item ${currentPage>=totalPage-1?"disabled":""}"><a class="page-link" href="/danh-muc/hien-thi/${currentPage+1}">></a></li>
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