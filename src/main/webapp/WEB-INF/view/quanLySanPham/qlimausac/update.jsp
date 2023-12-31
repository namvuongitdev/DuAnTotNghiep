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

    <script src="
              https://cdn.jsdelivr.net/npm/sweetalert2@11.10.0/dist/sweetalert2.all.min.js
              "></script>
    <link href="
          https://cdn.jsdelivr.net/npm/sweetalert2@11.10.0/dist/sweetalert2.min.css
         " rel="stylesheet">
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
                <h3>Màu sắc</h3>
                <nav>
                    <ol class="breadcrumb">
                        <li class="breadcrumb-item"><a href="/admin/trang-chu" style="text-decoration: none; color: black">Trang chủ</a></li>
                        <li class="breadcrumb-item ">Quản lý sản phẩm</li>
                        <li class="breadcrumb-item active">Màu sắc</li>
                    </ol>
                </nav>
            </div><!-- End Page Title -->
            <section class="section">
                <!-- Update -->
                <div class="card" id="cardUpdate">
                    <div class="card-body row">
                        <h5 class="card-title col-10">Sửa dữ liệu</h5>
                        <div class="col-2 card-title">
                            <a class="btn btn-danger text-white" title="Sửa dữ liệu" style="text-decoration: none" href="/admin/mau-sac/hien-thi"><i class="bi bi-x"></i></a>
                        </div>
                        <!-- Floating Labels Form -->
                        <form id="colorForm" method="post" action="/admin/mau-sac/update/${dl.id}" modelAttribute="mauSac" class="row g-3" onsubmit="return false;">
                            <div class="col-md-12">
                                <div class="form-floating">
                                    <input type="text" name="ten" class="form-control" id="floating" placeholder="Tên màu" value="${dl.ten}"/>
                                    <label for="floating">Tên màu <span style="color: red">(*)</span></label>
                                    <div id="nameError" style="color: red"></div>
                                </div>
                            </div>
                            <div class="text-center">
                                <button  type="submit" class="btn btn-success" onclick="return validateForm(event)">
                                    Cập nhật
                                </button>
                            </div>
                        </form>
                        <!-- End floating Labels Form -->
                    </div>
                </div>
                <br><br>
                <%--            Table--%>
                <div class="row">
                    <div class="col-lg-12">

                        <div class="card">
                            <div class="card-body row">
                                <h5 class="card-title col-10">Danh sách</h5>

                                <!-- Table with stripped rows -->
                                <table class="table">
                                    <thead>
                                    <tr style="text-align: center">
                                        <th scope="col">STT</th>
                                        <th scope="col">Tên màu</th>
                                        <th scope="col">Ngày tạo</th>
                                        <th scope="col">Ngày sửa</th>
                                        <th scope="col">Trạng thái</th>
                                        <th scope="col">Thao tác</th>
                                    </tr>
                                    </thead>
                                    <tbody>
                                    <c:forEach items="${list.content}" var="lst" varStatus="i">
                                        <tr style="text-align: center">
                                            <th scope="row">${i.index + (list.number + 1 != 1 ? ((list.number + 1) * list.size) -(list.size - 1) : list.number + 1)}</th>
                                            <td>${lst.ten}</td>
                                            <td><fmt:formatDate value="${lst.ngayTao}" pattern="dd/MM/yyyy HH:mm:ss"/></td>
                                            <td><fmt:formatDate value="${lst.ngaySua}" pattern="dd/MM/yyyy HH:mm:ss"/></td>
                                            <td>
                                                <button style="--bs-btn-padding-y: .25rem; --bs-btn-padding-x: .5rem; --bs-btn-font-size: .75rem;"
                                                        type="button" class="${lst.trangThai == 0 ? 'btn btn-danger' : 'btn btn-success'}">
                                                        ${lst.trangThai == 0 ? 'Ngừng kích hoạt' : 'Kích hoạt'}
                                                </button>
                                            </td>
                                            <td>
                                                <a id="showButton" class="btn btn-success text-white" title="Sửa dữ liệu" style="text-decoration: none" href="/admin/mau-sac/hien-thi/${lst.id}"><i class="bi bi-pencil"></i></a>
                                                <button type="button" class="btn btn-warning" title="Cập nhật trạng thái" onclick="updateStatus({id:`${lst.id}`,trangThai:`${lst.trangThai}`})">
                                                    <i class="bi bi-arrow-repeat"></i>
                                                </button>
                                            </td>
                                        </tr>
                                    </c:forEach>
                                    </tbody>
                                </table>
                                <!-- End Table with stripped rows -->
                                <%--phân trang--%>
                                <div class="container-fluid mt-5">
                                    <nav aria-label="Page navigation example">
                                        <ul class="pagination justify-content-center">
                                            <li class="page-item ${list.number + 1<=1?"disabled":""}"><a class="page-link"
                                                                                                         href="${url}${(list.number + 1) - 1}"><</a>
                                            </li>
                                            <c:forEach begin="1" end="${list.getTotalPages()}" var="i">
                                                <li class="page-item"><a class="page-link ${i == list.number + 1 ? 'active ' : ''}"
                                                                         href="${url}${i}">${i}</a></li>
                                            </c:forEach>
                                            <li class="page-item ${list.number + 1 >= list.getTotalPages() ? "disabled":  ""}">
                                                <a class="page-link"
                                                   href="${url}${(list.number + 1) + 1}">></a>
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
<script>
    function updateStatus(data) {
        Swal.fire({
            title: "Bạn có muốn cập nhật trạng thái không ?",
            showDenyButton: true,
            confirmButtonText: "Có",
            denyButtonText: `Không`
        }).then((result) => {
            /* Read more about isConfirmed, isDenied below */
            if (result.isConfirmed) {
                window.location.href="/admin/mau-sac/update-status/"+data.id+"?trangThai="+data.trangThai;
            } else if (result.isDenied) {
                return false;
            }
        });
    }

    function validateForm(event) {
        var input = document.getElementById('floating').value.trim();
        var errorDiv = document.getElementById('nameError');
        var regex = /^[^\d!"@#\$%\^&\*\(\)_\+=\[\]\{\}\|;:'",<>\?\/\\`~]+$/; // Chỉ chấp nhận chữ cái và khoảng trắng

        event.preventDefault(); // Prevent the default form submission behavior

        Swal.fire({
            title: "Bạn có muốn sửa dữ liệu không ?",
            showDenyButton: true,
            confirmButtonText: "Có",
            denyButtonText: `Không`
        }).then((result) => {
            if (result.isConfirmed) {
                if (!regex.test(input)) {
                    errorDiv.textContent = 'Tên màu không hợp lệ';
                } else {
                    errorDiv.textContent = ''; // Clear error message
                    document.getElementById('colorForm').submit(); // Submit the form
                }
            } else if (result.isDenied) {
                return false;
            }
        });
    }
    if (${success != null}) {
        Swal.fire({
            title: "${success}",
            icon: "success"
        });
    }

    if (${error != null}) {
        Swal.fire({
            title: "${error}",
            icon: "error"
        });
    }
</script>

</body>
</html>