<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<html>
<head>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.5.0/font/bootstrap-icons.css" rel="stylesheet"/>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-GLhlTQ8iRABdZLl6O3oVMWSktQOp6b7In1Zl3/Jr59b6EGGoI1aFkw7cmDA6j6gD" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js"
            integrity="sha384-IQsoLXl5PILFhosVNubq5LC7Qb9DXgDA9i+tQ8Zj3iwWAwPtgFTxbJ8NT4GN1R8p"
            crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.min.js"
            integrity="sha384-cVKIPhGWiC2Al4u+LWgxfKTRIcfu0JTxR+EQDz/bgldoEyl4H0zUF0QKbrJ0EcQF"
            crossorigin="anonymous"></script>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css">
    <script src="https://cdn.jsdelivr.net/npm/jquery@3.6.3/dist/jquery.slim.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>

    <script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.8.2/angular.min.js"></script>
    <script src="https://kit.fontawesome.com/35a8b342cd.js" crossorigin="anonymous"></script>
    <style>
        @media (min-width: 1025px) {
            .h-custom {
                height: 100vh !important;
            }
        }

        .card-registration .select-input.form-control[readonly]:not([disabled]) {
            font-size: 1rem;
            line-height: 2.15;
            padding-left: .75em;
            padding-right: .75em;
        }

        .card-registration .select-arrow {
            top: 13px;
        }

        .bg-grey {
            background-color: #eae8e8;
        }

        @media (min-width: 992px) {
            .card-registration-2 .bg-grey {
                border-top-right-radius: 16px;
                border-bottom-right-radius: 16px;
            }
        }

        @media (max-width: 991px) {
            .card-registration-2 .bg-grey {
                border-bottom-left-radius: 16px;
                border-bottom-right-radius: 16px;
            }
        }
    </style>
</head>
<body style="background-color: #f5f5f5">
<jsp:include page="../banHangOnlline/nav.jsp"></jsp:include>
<section class="h-100 h-custom" style="background-color: #f5f5f5 ">
    <div class="container py-5 h-100">
        <div class="row d-flex justify-content-center align-items-center h-100">
            <div class="col-12">
                <div class="card card-registration card-registration-2" style="border-radius: 15px;height: 600px">
                    <div class="card-body p-0">
                        <div class="row g-0">
                            <div class="col-lg-8">
                                <div class="p-2">
                                    <h3 class="fw-bold mb-0 text-black">GIỎ HÀNG</h3>
                                    <br>
                                    <form method="">
                                        <c:forEach items="${listGH.getContent()}" var="gh" varStatus="i">
                                        <hr class="my-4">
                                        <div class="card mb-3">
                                            <div class="card-body">
                                                <div class="d-flex justify-content-between">
                                                    <div class="d-flex flex-row align-items-center">
                                                        <div>
                                                            <img
                                                                    src="/anh/${gh.anh}"
                                                                    class="img-fluid rounded-3" alt="Shopping item"
                                                                    style="width: 65px;">
                                                        </div>
                                                        <div class="ms-2">
                                                            <h5>${gh.ten}</h5>
                                                            <p class="small mb-0">${gh.tenMau}, ${gh.tenSize},   <font color="#5f9ea0">${gh.donGia} đ</font></p>
                                                        </div>
                                                    </div>
                                                    <div class="d-flex flex-row align-items-center">
                                                        <div style="margin-left: 15px">
                                                            <input type="text" name="soLuong" value="${gh.soLuong}"
                                                                   style="width: 40px">
                                                        </div>
                                                        <div style="margin-left:15px;width: 100px;color: #5f9ea0">
                                                            <h6 class="mb-0">${gh.thanhTien} đ</h6>
                                                        </div>
                                                        <a href="#!" style="color: #cecece;margin-left: 15px"><i
                                                                class="fas fa-trash-alt"></i></a>
                                                        <a href="#!" style="color: #cecece;margin-left: 15px">
                                                            <svg xmlns="http://www.w3.org/2000/svg" width="16"
                                                                 height="16"
                                                                 fill="currentColor"
                                                                 viewBox="0 0 16 16">
                                                                <path d="M11 5.466V4H5a4 4 0 0 0-3.584 5.777.5.5 0 1 1-.896.446A5 5 0 0 1 5 3h6V1.534a.25.25 0 0 1 .41-.192l2.36 1.966c.12.1.12.284 0 .384l-2.36 1.966a.25.25 0 0 1-.41-.192Zm3.81.086a.5.5 0 0 1 .67.225A5 5 0 0 1 11 13H5v1.466a.25.25 0 0 1-.41.192l-2.36-1.966a.25.25 0 0 1 0-.384l2.36-1.966a.25.25 0 0 1 .41.192V12h6a4 4 0 0 0 3.585-5.777.5.5 0 0 1 .225-.67Z"/>
                                                            </svg>
                                                        </a>

                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                        </c:forEach>
                                        <hr class="my-4">
                                </div>
                            </div>
                            <div class="col-lg-4 bg-grey" style="height: 600px">
                                <div class="p-5">

                                    <div class="d-flex justify-content-between mb-5">
                                        <h5 class="text-uppercase">Tổng đơn</h5>
                                        <h5><font color="red">${tongThanhTien} đ</font></h5>
                                    </div>

                                    <button formaction="/giohang/muahang" class="btn btn-dark btn-block btn-lg"
                                            data-mdb-ripple-color="dark">ĐẶT HÀNG
                                    </button>

                                </div>
                                </form>

                            </div>

                        </div>

                        <h6 class="mb-0"><a href="/vongtay/trangchu" class="text-body"><i
                                class="fas fa-long-arrow-alt-left me-2"></i>Quay lại</a></h6>
                    </div>
                </div>
            </div>
        </div>
    </div>

</section>
<%--                phân trang --%>
<div class="" style="background-color: #f5f5f5">
    <nav aria-label="Page navigation example">
        <ul class="pagination justify-content-center">
            <li class="page-item ${pageNo<=1?"disabled":""}"><a class="page-link"
                                                                href="/sports-clothing/gio-hang-onl/hien-thi?page=${pageNo-1}">Previous</a>
            </li>
            <c:forEach begin="1" end="${listGH.getTotalPages()}" var="i">
                <li class="page-item"><a class="page-link ${i == pageNo ? 'active ' : ''}"
                                         href="/sports-clothing/gio-hang-onl/hien-thi?page=${i}">${i}</a></li>
            </c:forEach>
            <li class="page-item ${pageNo>=listGH.getTotalPages()?"disabled":""}"><a class="page-link"
                                                                                     href="/sports-clothing/gio-hang-onl/hien-thi?page=${pageNo+1}">Next</a>
            </li>
        </ul>
    </nav>
</div>
</body>
</html>