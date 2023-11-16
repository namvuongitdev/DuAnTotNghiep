<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html lang="zxx">
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
    <!-- Google Font -->
    <link href="https://fonts.googleapis.com/css2?family=Nunito+Sans:wght@300;400;600;700;800;900&display=swap"
          rel="stylesheet">

    <!-- Css Styles -->
    <link rel="stylesheet" href="/css/bootstrap.min.css" type="text/css">
    <link rel="stylesheet" href="/css/font-awesome.min.css" type="text/css">
    <link rel="stylesheet" href="/css/elegant-icons.css" type="text/css">
    <link rel="stylesheet" href="/css/magnific-popup.css" type="text/css">
    <link rel="stylesheet" href="/css/nice-select.css" type="text/css">
    <link rel="stylesheet" href="/css/owl.carousel.min.css" type="text/css">
    <link rel="stylesheet" href="/css/slicknav.min.css" type="text/css">
    <link rel="stylesheet" href="/css/style.css" type="text/css">
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
<!-- Header Section Begin -->
<header class="header">
    <div class="header__top">
        <div class="container">
            <div class="row">
                <div class="col-lg-6 col-md-7">
                    <div class="header__top__left">
                        <p>Free shipping, 30-day return or refund guarantee.</p>
                    </div>
                </div>
                <div class="col-lg-6 col-md-5">
                    <div class="header__top__right">
                        <div class="header__top__links">
                            <a href="#">Sign in</a>
                        </div>

                    </div>
                </div>
            </div>
        </div>
    </div>
    <div class="container">
        <div class="row">
            <div class="col-lg-3 col-md-3">
                <div class="header__logo">
                    <a href="./index.html"><img src="/anh/logochu.png" alt=""></a>
                </div>
            </div>
            <div class="col-lg-6 col-md-6">
                <nav class="header__menu mobile-menu">
                    <ul>
                        <li class="active"><a href="/index/home">Home</a></li>
                        <li><a href="">Shop</a></li>
                        <li><a href="">Pages</a>
                            <ul class="dropdown">
                                <li><a href="">About Us</a></li>
                                <li><a href="">Shop Details</a></li>
                                <li><a href="">Shopping Cart</a></li>
                                <li><a href="">Check Out</a></li>
                                <li><a href="">Blog Details</a></li>
                            </ul>
                        </li>
                        <li><a href="">Blog</a></li>
                        <li><a href="">Contacts</a></li>
                    </ul>
                </nav>
            </div>
            <div class="col-lg-3 col-md-3">
                <div class="header__nav__option">
                    <a href="/gio-hang-onl" class="position-relative">
                        <img src="/img/icon/cart.png" alt="" style="width: 30px ; height: 30px">
                        <span class="position-absolute top-0 start-100 translate-middle badge rounded-pill bg-danger" style="color: #FFFFFF">
                        ${count != null ? count : 0}
                    </span>
                    </a>
                </div>
            </div>
        </div>
        <div class="canvas__open"><i class="fa fa-bars"></i></div>
    </div>
</header>
<h4 class="fw-bold mb-0 text-black" style="margin-left: 200px;margin-top: 20px">GIỎ
    HÀNG</h4>
<br>
<div style="border: 2px solid #eae8e8;border-style: ridge;height: 60px;width: 1170px;margin-left: 190px;align-items: center;background-color: white">
    <nav style="padding: 0;margin: 0;list-style: none;">
        <ul style="display: flex;margin-top: 20px">
            <li  style="color: #7a7a7a;display: flex;margin-left: 20px"><a>Sản Phẩm</a></li>
            <li style="color: #7a7a7a;display: flex;margin-left: 400px"><a >Số Lượng</a></li>
            <li style="color: #7a7a7a;display: flex;margin-left: 200px"><a >Số Tiền</a></li>
            <li style="color: #7a7a7a;display: flex;margin-left: 200px"><a >Thao Tác</a></li>
        </ul>
    </nav>
</div>
<br>
<br>
<div style="border: 2px solid #eae8e8;border-style: ridge;width: 1170px;margin-left: 190px">
    <c:if test="${gioHangEmpty != null}">
        <div class="alert alert-danger d-flex align-items-center" role="alert">
            <svg class="bi flex-shrink-0 me-2" width="24" height="24" role="img" aria-label="Danger:">
                <symbol id="exclamation-triangle-fill" fill="currentColor" viewBox="0 0 16 16">
                    <path d="M8.982 1.566a1.13 1.13 0 0 0-1.96 0L.165 13.233c-.457.778.091 1.767.98 1.767h13.713c.889 0 1.438-.99.98-1.767L8.982 1.566zM8 5c.535 0 .954.462.9.995l-.35 3.507a.552.552 0 0 1-1.1 0L7.1 5.995A.905.905 0 0 1 8 5zm.002 6a1 1 0 1 1 0 2 1 1 0 0 1 0-2z"/>
                </symbol>
                <use xlink:href="#exclamation-triangle-fill"/>
            </svg>
            <div>
                    ${gioHangEmpty}
            </div>
        </div>
    </c:if>

    <c:forEach items="${list}" var="gh" varStatus="i">
        <hr class="my-4">
        <div class="card mb-3" style="border: 2px solid #eae8e8;border-style: ridge;height: 100px;width: 1140px;margin-left: 15px">
            <div class="card-body">
                <div class="d-flex justify-content-between">
                    <div class="d-flex flex-row align-items-center">
                        <div>
                            <img
                                    src="/image/${gh.getImg()}"
                                    class="img-fluid rounded-3" alt="Shopping item"
                                    style="width: 65px;">
                        </div>
                        <div class="ms-2">
                            <h5>${gh.getTenSanPham()}</h5>
                            <p class="small mb-0">${gh.getMauSac()},${gh.getKichCo()},
                                <c:choose>
                                <c:when test="${gh.getGiaBanSanPham() != null && gh.trangThaiKMCT == 1 && gh.trangThaiKM ==1}">
                                <font color="#5f9ea0"><fmt:formatNumber pattern="#,###"
                                                                        value="${gh.getDonGiaSauKhiGiam()}"></fmt:formatNumber>
                                    đ</font></p>
                            </c:when>
                            <c:otherwise>
                                <font color="#5f9ea0"><fmt:formatNumber pattern="#,###"
                                                                        value="${gh.getGiaBanSanPham()}"></fmt:formatNumber>
                                    đ</font></p>
                            </c:otherwise>
                            </c:choose>

                        </div>
                    </div>
                    <form>
                        <div class="d-flex flex-row align-items-center">
                            <div style="margin-left: 15px;width: 30px;margin-right: 210px">
                                <input type="number"
                                       style="width: 120px;text-align: center;margin-top: 15px"
                                       name="soLuong" min="1" value="${gh.getSoLuong()}" class="form-control"
                                       onchange="callYourApi(this.value,{idGioHangCT:`${gh.getId()}`})"/>
                            </div>
                            <div style="margin-right:170px;width: 100px;color: #5f9ea0;">
                                <h6 class="mb-0" style="margin-top: 15px"><b><fmt:formatNumber pattern="#,###"
                                                                                               value="${gh.getThanhTien()}"></fmt:formatNumber>
                                    đ</b></h6>
                            </div>
                            <a href="#!" style="color: #cecece;margin-left: 15px">
                                <button formaction="/gio-hang-onl/xoa/${gh.getId()}"
                                        style="background-color:white ;border: none;margin-top: 15px;margin-right: 80px" title="xoá"><i
                                        class="fas fa-trash-alt"></i></button>
                            </a>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </c:forEach>
</div>
<br>
<br>
<!-- Header Section End -->
<div style="border: 2px solid #eae8e8;border-style: ridge;height: 60px;margin-left: 730px;display: flex;width: 600px">
            <h5 class="text-uppercase" style="margin-top: 7px;padding-left: 30px">Tổng đơn
            <font color="red"><fmt:formatNumber pattern="#,###"
                                                    value="${tongTien}"></fmt:formatNumber>
                đ</font></h5>
        <a href="/checkouts" style="margin-left: 50px" class="btn btn-dark btn-block btn-lg"
           data-mdb-ripple-color="dark">Thanh toán
        </a>

</div>
<br>
<br>
<div style="margin-top: 40px"></div>
<!-- Footer Section Begin -->
<footer class="footer">
    <div class="container">
        <div class="row">
            <div class="col-lg-3 col-md-6 col-sm-6">
                <div class="footer__about">
                    <div class="footer__logo">
                        <a href="#"><img src="/img/footer-logo.png" alt=""></a>
                    </div>
                    <p>The customer is at the heart of our unique business model, which includes design.</p>
                    <a href="#"><img src="/img/payment.png" alt=""></a>
                </div>
            </div>
            <div class="col-lg-2 offset-lg-1 col-md-3 col-sm-6">
                <div class="footer__widget">
                    <h6>Shopping</h6>
                    <ul>
                        <li><a href="#">Clothing Store</a></li>
                        <li><a href="#">Trending Shoes</a></li>
                        <li><a href="#">Accessories</a></li>
                        <li><a href="#">Sale</a></li>
                    </ul>
                </div>
            </div>
            <div class="col-lg-2 col-md-3 col-sm-6">
                <div class="footer__widget">
                    <h6>Shopping</h6>
                    <ul>
                        <li><a href="#">Contact Us</a></li>
                        <li><a href="#">Payment Methods</a></li>
                        <li><a href="#">Delivary</a></li>
                        <li><a href="#">Return & Exchanges</a></li>
                    </ul>
                </div>
            </div>
            <div class="col-lg-3 offset-lg-1 col-md-6 col-sm-6">
                <div class="footer__widget">
                    <h6>NewLetter</h6>
                    <div class="footer__newslatter">
                        <p>Be the first to know about new arrivals, look books, sales & promos!</p>
                        <form action="#">
                            <input type="text" placeholder="Your email">
                            <button type="submit"><span class="icon_mail_alt"></span></button>
                        </form>
                    </div>
                </div>
            </div>
        </div>
        <div class="row">
            <div class="col-lg-12 text-center">
                <div class="footer__copyright__text">
                    <!-- Link back to Colorlib can't be removed. Template is licensed under CC BY 3.0. -->
                    <p>Copyright ©
                        <script>
                            document.write(new Date().getFullYear());
                        </script>
                        2020
                        All rights reserved | This template is made with <i class="fa fa-heart-o"
                                                                            aria-hidden="true"></i> by <a
                                href="https://colorlib.com" target="_blank">Colorlib</a>
                    </p>
                    <!-- Link back to Colorlib can't be removed. Template is licensed under CC BY 3.0. -->
                </div>
            </div>
        </div>
    </div>
</footer>
<!-- Footer Section End -->

<!-- Search Begin -->
<div class="search-model">
    <div class="h-100 d-flex align-items-center justify-content-center">
        <div class="search-close-switch">+</div>
        <form class="search-model-form">
            <input type="text" id="search-input1" placeholder="Search here.....">
        </form>
    </div>
</div>

<script src="/js/jquery-3.3.1.min.js"></script>
<script src="/js/bootstrap.min.js"></script>
<script src="/js/jquery.nice-select.min.js"></script>
<script src="/js/jquery.nicescroll.min.js"></script>
<script src="/js/jquery.magnific-popup.min.js"></script>
<script src="/js/jquery.countdown.min.js"></script>
<script src="/js/jquery.slicknav.js"></script>
<script src="/js/mixitup.min.js"></script>
<script src="/js/owl.carousel.min.js"></script>
<script src="/js/main.js"></script>
<script>
    function callYourApi(newValue, gioHang) {
        window.location.href = "/gio-hang-onl/cap-nhat-gio-hang/" + newValue + "/"
            + gioHang.idGioHangCT;
    }

</script>
</body>
</html>