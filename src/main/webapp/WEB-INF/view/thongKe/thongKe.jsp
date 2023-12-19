<%@ page language="java" contentType="text/html; charset=UTF-8" %>
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
    <!-- Custom fonts for this template-->
    <link href="../../../admin/vendor/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css">
    <link href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i"
          rel="stylesheet">

    <!-- Custom styles for this template-->
    <link href="../../../admin/css/sb-admin-2.min.css" rel="stylesheet">
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
                <h3>Thống kê</h3>
                <nav>
                    <ol class="breadcrumb">
                        <li class="breadcrumb-item"><a href="/admin/trang-chu"
                                                       style="text-decoration: none; color: black">Trang chủ</a></li>
                        <li class="breadcrumb-item active">Thống kê</li>
                    </ol>
                </nav>
            </div><!-- End Page Title -->
            <section class="section">
                <!-- Begin Page Content -->
                <div class="container-fluid">
                    <!-- Content Row -->
                    <div class="row">

                        <!-- Earnings (Monthly) Card Example -->
                        <div class="col-xl-3 col-md-6 mb-4">
                            <div class="card border-left-primary shadow h-100 py-2">
                                <div class="card-body">
                                    <div class="row no-gutters align-items-center">
                                        <div class="col mr-2">
                                            <div class="text-xs font-weight-bold text-primary text-uppercase mb-1">
                                                Tổng doanh thu
                                            </div>
                                            <div class="h5 mb-0 font-weight-bold text-gray-800">
                                                <fmt:formatNumber pattern="#,###"
                                                                  value="${tongDoanhThu == 0 ? 0 : tongDoanhThu}"></fmt:formatNumber>
                                                đ
                                            </div>
                                        </div>
                                        <div class="col-auto">
                                            <i class="bi bi-cash-coin"></i>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>

                        <!-- Earnings (Monthly) Card Example -->
                        <div class="col-xl-3 col-md-6 mb-4">
                            <div class="card border-left-success shadow h-100 py-2">
                                <div class="card-body">
                                    <div class="row no-gutters align-items-center">
                                        <div class="col mr-2">
                                            <div class="text-xs font-weight-bold text-success text-uppercase mb-1">
                                                Doanh thu hôm nay

                                            </div>
                                            <div class="h5 mb-0 font-weight-bold text-gray-800" id="datetim"><fmt:formatNumber
                                                    pattern="#,###"
                                                    value="${doanhThuTrongNgay == null ? 0 : doanhThuTrongNgay}"></fmt:formatNumber>
                                                đ

                                            </div>

                                        </div>
                                        <div class="col-auto">
                                            <input  type="date" id="date" oninput="displaySelectedDate()">
                                            <i class="bi bi-cash-coin"></i>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>

                        <!-- Earnings (Monthly) Card Example -->
                        <div class="col-xl-3 col-md-6 mb-4">
                            <div class="card border-left-info shadow h-100 py-2">
                                <div class="card-body">
                                    <div class="row no-gutters align-items-center">
                                        <div class="col mr-2">
                                            <div class="text-xs font-weight-bold text-info text-uppercase mb-1">
                                                Số lượng sản phẩm đã bán tháng này
                                            </div>
                                            <div class="h5 mb-0 font-weight-bold text-gray-800">${soLuongDaBan== null ? 0 : soLuongDaBan}</div>
                                        </div>
                                        <div class="col-auto">
                                            <i class="bi bi-box-seam"></i>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>

                        <!-- Pending Requests Card Example -->
                        <div class="col-xl-3 col-md-6 mb-4">
                            <div class="card border-left-warning shadow h-100 py-2">
                                <div class="card-body">
                                    <div class="row no-gutters align-items-center">
                                        <div class="col mr-2">
                                            <div class="text-xs font-weight-bold text-success text-uppercase mb-1">
                                                Tổng số hóa đơn thành công hôm nay
                                            </div>
                                            <div class="h5 mb-0 font-weight-bold text-gray-800" id="date1input">${tongHoaDon}</div>
                                        </div>
                                        <div class="col-auto">
                                            <input  type="date" id="date1" oninput="date1()">
                                            <i class="bi bi-receipt"></i>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>

                        <!-- Earnings (Monthly) Card Example -->
                        <div class="col-xl-3 col-md-6 mb-4">
                            <div class="card border-left-info shadow h-100 py-2">
                                <div class="card-body">
                                    <div class="row no-gutters align-items-center">
                                        <div class="col mr-2">
                                            <div class="text-xs font-weight-bold text-info text-uppercase mb-1">
                                                <p style="color: red"> Đơn huỷ trong hôm nay</p></div>
                                            <div class="h5 mb-0 font-weight-bold text-gray-800" id="huy1">${tongHDHuy}</div>
                                        </div>
                                        <div class="col-auto">
                                            <input  type="date" id="huy" oninput="huy()">
                                            <i class="bi bi-box-seam"></i>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>

                        <div class="col-xl-3 col-md-6 mb-4">
                            <div class="card border-left-info shadow h-100 py-2">
                                <div class="card-body">
                                    <div class="row no-gutters align-items-center">
                                        <div class="col mr-2">
                                            <div class="text-xs font-weight-bold text-info text-uppercase mb-1">
                                                <p style="color: slateblue">Đơn chờ xác nhận</p></div>
                                            <div class="h5 mb-0 font-weight-bold text-gray-800">${tongHoaDonChoXacNhan}</div>
                                        </div>
                                        <div class="col-auto">
                                            <i class="bi bi-box-seam"></i>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>

                    <!-- Content Row -->

                    <div class="row">
                        <!-- Area Chart -->
                        <div class="col-xl-12 col-lg-12">
                            <div class="card shadow mb-4">
                                <!-- Card Header - Dropdown -->
                                <div
                                        class="card-header py-3 d-flex flex-row align-items-center justify-content-between">
                                    <h6 class="m-0 font-weight-bold text-primary" id="bieuDoTheoNam">Biểu đồ thống kê
                                        tổng doanh thu hàng
                                        tháng năm ${nam}.</h6>
                                    <div class="dropdown no-arrow">
                                        <a class="dropdown-toggle" href="#" role="button" id="dropdownMenuLink"
                                           data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                            <i class="bi bi-caret-down-fill"></i>
                                        </a>
                                        <div class="dropdown-menu dropdown-menu-right shadow animated--fade-in"
                                             aria-labelledby="dropdownMenuLink">
                                            <c:forEach items="${getNams}" var="n">
                                                <a class="dropdown-item" onclick="get(`${n}`)">${n}</a>
                                            </c:forEach>
                                        </div>
                                    </div>
                                </div>
                                <!-- Card Body -->
                                <div class="card-body">
                                    <div class="chart-area">
                                        <canvas id="myAreaChart"></canvas>
                                    </div>
                                </div>
                            </div>
                        </div>

                    </div>

                    <!-- Content Row -->
                    <div class="row">

                        <!-- Content Column -->
                        <div class="col-xl-12 col-lg-12">

                            <!-- Project Card Example -->
                            <div class="card shadow mb-4 h-100">
                                <div class="card-header py-3">
                                    <h6 class="m-0 font-weight-bold text-primary">Top sản phẩm bán chạy trong
                                        tháng.</h6>
                                </div>
                                <div class="card-body">
                                    <table class="table">
                                        <thead>
                                        <tr>
                                            <th scope="col">STT</th>
                                            <th scope="col">Ảnh</th>
                                            <th scope="col">Tên Sản Phẩm</th>
                                            <th scope="col">Giá</th>
                                            <th scope="col">Số sản phẩn đã bán</th>
                                        </tr>
                                        </thead>
                                        <tbody>
                                        <c:forEach items="${sanPhamBanChay}" var="sp" varStatus="i">
                                            <tr>
                                                <th scope="row">${i.index+1}</th>
                                                <td><img class="img-thumbnail" src="/image/${sp[0]}"
                                                         style="width:50px;height: 50px"></td>
                                                <td>${sp[1]}</td>
                                                <td><fmt:formatNumber pattern="#,###" value="${sp[2]}"/>đ</td>
                                                <td>${sp[3]}</td>
                                            </tr>
                                        </c:forEach>
                                        </tbody>
                                    </table>
                                </div>
                            </div>
                        </div>
                    </div>

                </div>
                <!-- /.container-fluid -->
            </section>
        </div>

    </div>
</div>
<!-- Bootstrap core JavaScript-->
<script src="../../../admin/vendor/jquery/jquery.min.js"></script>
<script src="../../../admin/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

<!-- Core plugin JavaScript-->
<script src="../../../admin/vendor/jquery-easing/jquery.easing.min.js"></script>

<!-- Custom scripts for all pages-->
<script src="../../../admin/js/sb-admin-2.min.js"></script>

<!-- Page level plugins -->
<script src="../../../admin/vendor/chart.js/Chart.min.js"></script>

<!-- Page level custom scripts -->
<script src="../../../admin/js/demo/chart-area-demo.js"></script>
<script src="../../../admin/js/demo/chart-pie-demo.js"></script>
<script>
    const VND = new Intl.NumberFormat('vi-VN', {
        style: 'currency',
        currency: 'VND',
    });
    function displaySelectedDate() {
        var inputDate = document.getElementById("date").value;

        // Check if a date is selected
        if (inputDate) {
            var selectedDate = new Date(inputDate);

            var day = selectedDate.getDate();
            var month = selectedDate.getMonth() + 1; // Month is zero-based
            var year = selectedDate.getFullYear();

            // Format the date as DD/MM/YYYY
            var formattedDate = day + '/' + month + '/' + year;
            console.log(formattedDate);
            fetch('/admin/thong-ke/ngay/' + day +'/' + month + '/' + year)
                .then(response => response.json())
                .then(data => {
                    console.log("snsss");
                    document.getElementById("datetim").innerText = VND.format(data[0]);
                })

        } else {
            document.getElementById("datetim").innerText = ""; // Clear the content if no date is selected
        }
    }
    function date1() {
        var inputDate = document.getElementById("date1").value;

        // Check if a date is selected
        if (inputDate) {
            var selectedDate = new Date(inputDate);

            var day = selectedDate.getDate();
            var month = selectedDate.getMonth() + 1; // Month is zero-based
            var year = selectedDate.getFullYear();

            // Format the date as DD/MM/YYYY
            var formattedDate = day + '/' + month + '/' + year;
            fetch('/admin/thong-ke/date1/' + day +'/' + month + '/' + year)
                .then(response => response.json())
                .then(data => {
                    document.getElementById("date1input").innerText = data[0];
                })

        } else {
            document.getElementById("date1input").innerText = ""; // Clear the content if no date is selected
        }
    }
    function huy() {
        var inputDate = document.getElementById("huy").value;

        // Check if a date is selected
        if (inputDate) {
            var selectedDate = new Date(inputDate);

            var day = selectedDate.getDate();
            var month = selectedDate.getMonth() + 1; // Month is zero-based
            var year = selectedDate.getFullYear();

            // Format the date as DD/MM/YYYY
            var formattedDate = day + '/' + month + '/' + year;
            fetch('/admin/thong-ke/huy/' + day +'/' + month + '/' + year)
                .then(response => response.json())
                .then(data => {
                    document.getElementById("huy1").innerText = data[0];
                })

        } else {
            document.getElementById("huy1").innerText = ""; // Clear the content if no date is selected
        }
    }
</script>
</body>
</html>
