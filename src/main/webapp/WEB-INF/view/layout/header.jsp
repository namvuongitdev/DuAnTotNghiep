<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html lang="zxx">
<head>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"
            integrity="sha384-geWF76RCwLtnZ8qwWowPQNguL3RmwHVBC9FhGdlKrxdiJJigb/j/68SIy3Te4Bkz"
            crossorigin="anonymous"></script>

    <!-- Google Font -->
    <link href="https://fonts.googleapis.com/css2?family=Nunito+Sans:wght@300;400;600;700;800;900&display=swap"
          rel="stylesheet">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.1/font/bootstrap-icons.css">

    <!-- Css Styles -->
    <link rel="stylesheet" href="/css/bootstrap.min.css" type="text/css">
    <link rel="stylesheet" href="/css/font-awesome.min.css" type="text/css">
    <link rel="stylesheet" href="/css/elegant-icons.css" type="text/css">
    <link rel="stylesheet" href="/css/magnific-popup.css" type="text/css">
    <link rel="stylesheet" href="/css/nice-select.css" type="text/css">
    <link rel="stylesheet" href="/css/owl.carousel.min.css" type="text/css">
    <link rel="stylesheet" href="/css/slicknav.min.css" type="text/css">
    <link rel="stylesheet" href="/css/style.css" type="text/css">
</head>
<body style="background-color: #f5f5f5">
<!-- Header Section Begin -->
<header class="header">

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
                        <img src="/img/icon/cart.png" alt="" style="width: 20px ; height: 20px">
                        <span class="position-absolute top-0 start-100 translate-middle badge rounded-pill bg-danger" style="color: #FFFFFF">
                            ${count != null ? count : 0}
                        </span>
                    </a>
                    <c:if test="${empty sessionScope.username}">
                        <div class="dropdown-center">
                            <a href="/login">
                                <img src="../../../img/icon/person.svg" alt="">
                            </a>
                        </div>
                    </c:if>
                    <c:if test="${not empty sessionScope.username}">
                        <div class="dropdown-center">
                            <a href="#" class="dropdown" type="button" data-bs-toggle="dropdown" aria-expanded="false">
                                <img src="../../../img/icon/person.svg" alt="">
                            </a>
                            <ul class="dropdown-menu">
                                <li><b style="margin-left: 25px">${sessionScope.username}</b>
                                    <hr>
                                </li>
                                <li><a class="dropdown-item" href="/cuaToi/thongTin">Thông tin của tôi</a></li>
                                <li><a class="dropdown-item" href="/cuaToi/donHangAll">Đơn hàng của tôi</a></li>
                                <li><a class="dropdown-item" href="/logout"
                                       onclick="if(confirm('Bạn có muốn đăng xuất không ?')==true){return true;}else{return false;}">
                                    Đăng xuất</a>
                                </li>
                            </ul>
                        </div>
                    </c:if>
                </div>
            </div>
        </div>
        <div class="canvas__open"><i class="fa fa-bars"></i></div>
    </div>
</header>

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
</body>
</html>