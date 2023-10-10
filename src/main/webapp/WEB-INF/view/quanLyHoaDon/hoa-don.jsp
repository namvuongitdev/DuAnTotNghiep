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
<jsp:include page="../sidebar/navbar.jsp"/>
<%--sidebar--%>
<div class="container-fluid">
    <div class="row flex-nowrap">
        <jsp:include page="../sidebar/sidebar.jsp"/>
        <div class="col py-3">
            <div class="pagetitle">
                <h3>Quản lý hóa đơn</h3>
                <nav>
                    <ol class="breadcrumb">
                        <li class="breadcrumb-item"><a href="/trangchu" style="text-decoration: none; color: black">Trang chủ</a></li>
                        <li class="breadcrumb-item active">Quản lý hóa đơn</li>
                    </ol>
                </nav>
            </div><!-- End Page Title -->
            <section class="section">
                <div class="card">
                    <div class="card-body row">
                        <h5 class="card-title">Danh sách đơn hàng</h5>
                        <br><br>

                        <!-- Bordered Tabs -->
                        <ul class="nav nav-tabs nav-tabs-bordered" id="borderedTab" role="tablist">
                            <li class="nav-item" role="presentation">
                                <button class="nav-link active" id="home-tab" data-bs-toggle="tab" data-bs-target="#bordered-home" type="button" role="tab" aria-controls="home" aria-selected="true">Tìm kiếm</button>
                            </li>
                        </ul>
                        <div class="tab-content pt-2" id="borderedTabContent">
                            <br>
                            <%--tìm kiếm--%>
                            <div class="tab-pane fade show active" id="bordered-home" role="tabpanel" aria-labelledby="home-tab">
                                <%--@elvariable id="hoaDonFillter" type=""--%>
<<<<<<< Updated upstream
                                <form:form action="/hoa-don/fillter" method="get" modelAttribute="hoaDonFillter">
                                    <div class="input-group" style="width: 500px">
                                        <input type="text" class="form-control" name="search" value="${fillter.search}" placeholder="Tìm theo mã hóa đơn" aria-label="Tìm theo mã hóa đơn" aria-describedby="button-addon2"/>
                                        <button class="btn btn-outline-secondary" type="submit" id="button-addon2">Tìm kiếm</button>
                                    </div>
                            </div>
                            <%--tìm kiếm nâng cao--%>
                            <div class="tab-pane fade" id="bordered-profile" role="tabpanel" aria-labelledby="profile-tab">
<<<<<<< Updated upstream
=======
                                <form:form action="/hoa-don/filter" method="get" modelAttribute="hoaDonFillter">
                                <div class="input-group" style="width: 500px">
                                    <input type="text" class="form-control" name="search" value="${fillter.search}" placeholder="Tìm theo mã hóa đơn" aria-label="Tìm theo mã hóa đơn" aria-describedby="button-addon2"/>
                                </div>
                                <%--tìm kiếm nâng cao--%>
>>>>>>> Stashed changes
                                    <div class="row">
                                        <div class="col-4">
                                            <label class="col-form-label">Từ ngày</label>
                                            <input type="date" value="${fillter.dateBegin}" name="dateBegin" class="form-control"/>
                                        </div>
                                        <div class="col-4">
                                            <label class="col-form-label">Đến ngày</label>
                                            <input type="date" value="${fillter.dateEnd}" name="dateEnd"  class="form-control"/>
                                        </div>
                                        <div class="col-4">
                                            <label class="col-form-label">Trạng thái đơn hàng</label>
                                            <select class="form-select" name="trangThai" aria-label="Default select example">
                                                <option value="" selected>Tất cả</option>
                                                <option value="2" ${fillter.trangThai == 2? 'selected':''} class="text-warning">Đang giao hàng</option>
                                                <option value="1" ${fillter.trangThai == 1? 'selected':''} class="text-success">Đã thanh toán</option>
                                                <option value="3" ${fillter.trangThai == 3? 'selected':''} class="text-danger">Đã hủy</option>
                                            </select>
                                    </div><br>
=======
                                <div class="row">
                                    <div class="col-4">
                                        <label class="col-form-label">Từ ngày</label>
                                        <input type="date" value="null" name="dateBegin" class="form-control"/>
                                    </div>
                                    <div class="col-4">
                                        <label class="col-form-label">Đến ngày</label>
                                        <input type="date" value="null" name="dateEnd"  class="form-control"/>
                                    </div>
                                    <div class="col-4">
                                        <label class="col-form-label">Trạng thái đơn hàng</label>
                                        <select class="form-select" name="trangThai" aria-label="Default select example">
                                            <option value="" selected>Tất cả</option>
                                            <option value="2" class="text-warning">Đang giao hàng</option>
                                            <option value="1" class="text-success">Đã thanh toán</option>
                                            <option value="3" class="text-danger">Đã hủy</option>
                                        </select>
                                    </div>
                                </div><br>
>>>>>>> Stashed changes
                                    <%--button--%>
                                    <div style="text-align: center">
                                        <button type="submit" class="btn btn-outline-dark">Tìm kiếm</button>
                                    </div>

                                </form:form>
                            </div>
                            </div>
                            <br>
                        </div><!-- End Bordered Tabs -->
                        <!-- Table with stripped rows -->
                        <table class="table">
                            <thead>
                            <tr>
                                <th scope="col">STT</th>
                                <th scope="col">Mã hóa đơn</th>
                                <th scope="col">Thời gian</th>
                                <th scope="col">Khách hàng</th>
                                <th scope="col">Tổng tiền hàng</th>
                                <th scope="col">Tình trạng</th>
                                <th scope="col">Thao tác</th>
                            </tr>
                            </thead>
                            <tbody>
<<<<<<< Updated upstream
                                <c:forEach items="${lst}" var="hd" varStatus="i">
                                    <tr>
                                        <td>${i.index+1}</td>
                                        <td>${hd.ma}</td>
                                        <td>${hd.ngayTao}</td>
                                        <td>${hd.khachHang.hoTen}</td>
                                        <td>${hd.tongTien}</td>
                                        <td>
                                            <button type="button" class="btn btn-success" title="Xem Chi Tiết" onclick="myFunction()">
                                                <a class="text-white" style="text-decoration: none" href="/hoa-don/detail/${hd.id}"><i class="bi bi-pencil"></i></a>
                                            </button>
                                        </td>
                                    </tr>
                                </c:forEach>
=======
                            <c:forEach items="${lst}" var="hd" varStatus="i">
                                <tr>
                                    <td>${i.index+1}</td>
                                    <td>${hd.ma}</td>
<<<<<<< Updated upstream
=======
                                    <td>${hd.ngayTao}</td>
                                    <td>${hd.khachHang.hoTen}</td>
                                    <td>${hd.tongTien}</td>
                                    <td>${hd.trangThai==1?'Đã thanh toán':(hd.trangThai==2)?'Đang giao hàng':'Đã hủy'}</td>
>>>>>>> Stashed changes
                                    <td>
                                        <fmt:formatDate value="${hd.ngayTao}" pattern="yyyy-MM-dd HH:mm:ss"/>
                                    </td>
                                    <td>${hd.khachHang.hoTen}</td>
                                    <td>
                                        <fmt:formatNumber pattern="#,###" value="${hd.tongTien}"/>
                                    </td>
                                    <td>
                                        <button type="button" class="btn btn-success" title="Xem Chi Tiết" onclick="myFunction()">
                                            <a class="text-white" style="text-decoration: none" href="/hoa-don/detail/${hd.id}"><i class="bi bi-pencil"></i></a>
                                        </button>
                                    </td>
                                </tr>
                            </c:forEach>
>>>>>>> Stashed changes
                            </tbody>
                        </table>
                        <!-- End Table with stripped rows -->
                        <%--                phân trang --%>
                        <div class="container-fluid mt-5">
                            <nav aria-label="Page navigation example">
                                <ul class="pagination justify-content-center">
                                    <li class="page-item ${currentPage<=0?"disabled":""}"><a class="page-link" href="${url}${currentPage-1}"><</a></li>
                                    <c:forEach begin="1" end="${totalPage}" var="i">
                                        <li class="page-item"><a class="page-link" href="${url}${i-1}">${i}</a></li>
                                    </c:forEach>
                                    <li class="page-item ${currentPage>=totalPage-1?"disabled":""}"><a class="page-link" href="${url}${currentPage+1}">></a></li>
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
<script>
    function myFunction() {
        document.getElementById("floatingName").readOnly = false;
        document.getElementById("disabled").disabled = false
    }
</script>
</html>