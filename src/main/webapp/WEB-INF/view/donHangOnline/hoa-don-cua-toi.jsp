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
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"
            integrity="sha384-geWF76RCwLtnZ8qwWowPQNguL3RmwHVBC9FhGdlKrxdiJJigb/j/68SIy3Te4Bkz"
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
    <link rel="stylesheet" href="../../../css/bootstrap.min.css" type="text/css">
    <link rel="stylesheet" href="../../../css/font-awesome.min.css" type="text/css">
    <link rel="stylesheet" href="../../../css/elegant-icons.css" type="text/css">
    <link rel="stylesheet" href="../../../css/magnific-popup.css" type="text/css">
    <link rel="stylesheet" href="../../../css/nice-select.css" type="text/css">
    <link rel="stylesheet" href="../../../css/owl.carousel.min.css" type="text/css">
    <link rel="stylesheet" href="../../../css/slicknav.min.css" type="text/css">
    <link rel="stylesheet" href="../../../css/style.css" type="text/css">

    <link rel="stylesheet" href="../../../css/banHangOnline/hoaDon/hoaDon.css" type="text/css">

</head>
<body>
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
                        <li class="active"><a href="">Home</a></li>
                        <li><a href="./shop.html">Shop</a></li>
                        <li><a href="#">Pages</a>
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
                    <a href="/gio-hang-onl"><img src="/img/icon/cart.png" alt=""> <span></span></a>
                    <div class="dropdown-center">
                        <a href="#" class="dropdown" type="button" data-bs-toggle="dropdown" aria-expanded="false">
                            <img src="../../../img/icon/person.svg" alt="">
                        </a>
                        <ul class="dropdown-menu">
                            <li><a class="dropdown-item" href="#">Thông tin của tôi</a></li>
                            <li><a class="dropdown-item" href="/index/hoa-don-cua-toi">Đơn hàng của tôi</a></li>
                            <li><a  class="dropdown-item" href="/logout"
                                    onclick="if(confirm('Bạn có muốn đăng xuất không ?')==true){return true;}else{return false;}">
                                Đăng xuất</a>
                            </li>
                        </ul>
                    </div>
                </div>
            </div>
        </div>
        <div class="canvas__open"><i class="fa fa-bars"></i></div>
    </div>
</header>

<!-- Header Section End -->
<section class="section">
    <div class="container">
        <div class="row">
            <h3 class="h3-title">Đơn hàng của tôi</h3>
        </div>
        <div class="row">
            <nav class="primary-navigation">
                <ul>
                    <li><a href="#">Tất cả đơn hàng</a></li>|
                    <li><a href="#">Chưa thanh toán</a></li>|
                    <li><a href="#">Xử lý</a></li>
                </ul>
            </nav>
        </div>
        <div class="row">
            <table class="table">
                <thead class="thead">
                <tr>
                    <th scope="col">Sản phẩm</th>
                    <th scope="col" style="padding-left: 40px">Tổng tiền</th>
                    <th scope="col" >Hoạt động sản phẩm</th>
                    <th scope="col" style="padding-right: 50px">Trạng thái</th>
                    <th scope="col" style="padding-right: 25px">Thao tác</th>
                </tr>
                </thead>
            </table>
            <%--c:forEach--%>
                <div class="span">
                    27/10/2023 19:38:10; Mã đơn hàng: GHD576HJ6
                </div>
                <table class="table">
                    <tbody>
                    <tr>
                        <td class="td-1">
                            <div>
                                <h6>CC</h6>
                            </div>
                            <div class="div-1">
                                <img src="../../../anh/ao1.jpg" alt="">
                            </div>
                            <div>
                                <h6>1 sản phẩm</h6>
                            </div>
                        </td>
                        <td style="width: 220px">
                            <p>450.000 đ</p>
                        </td>
                        <td class="td-2">
                            <div>
                                <button class="btn btn-outline-secondary"><a style="color: black" href="#">Sản phẩm trả lại</a></button>
                            </div>
                            <div>
                                <a style="margin-top: 100px" class="text-primary" href="#">Mua lại</a>
                            </div>
                            <div>
                                <a class="text-primary" href="#">Theo dõi</a>
                            </div>
                        </td>
                        <td>
                            <div style="margin-top: 30px">Đã giao hàng</div>
                            <div style="margin-top: 10px">
                                <a class="text-primary" href="#">Chi tiết đơn hàng</a>
                            </div>
                        </td>
                        <td>
                            <div style="margin-top: 40px">
                                <button class="btn btn-secondary"><a href="#" style="color: white">Đã nhận</a></button>
                            </div>
                        </td>
                    </tr>
                    </tbody>
                </table>

                <div class="span">
                    27/10/2023 19:38:10 Mã đơn hàng: GHD576HJ6
                </div>
                <table class="table">
                    <tbody>
                    <tr>
                        <td class="td-1">
                            <div>
                                <h6>CC</h6>
                            </div>
                            <div class="div-1">
                                <img src="../../../anh/ao1.jpg" alt="">
                            </div>
                            <div>
                                <h6>1 sản phẩm</h6>
                            </div>
                        </td>
                        <td style="width: 220px">
                            <p>450.000 đ</p>
                        </td>
                        <td class="td-2">
                            <div>
                                <button class="btn btn-outline-secondary"><a style="color: black" href="#">Sản phẩm trả lại</a></button>
                            </div>
                            <div>
                                <a style="margin-top: 100px" class="text-primary" href="#">Mua lại</a>
                            </div>
                            <div>
                                <a class="text-primary" href="#">Theo dõi</a>
                            </div>
                        </td>
                        <td>
                            <div style="margin-top: 30px">Đã giao hàng</div>
                            <div style="margin-top: 10px">
                                <a class="text-primary" href="#">Chi tiết đơn hàng</a>
                            </div>
                        </td>
                        <td>
                            <div style="margin-top: 40px">
                                <button class="btn btn-secondary"><a href="#" style="color: white">Đã nhận</a></button>
                            </div>
                        </td>
                    </tr>
                    </tbody>
                </table>
            <%--end--%>
        </div>
    </div>
</section>

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
                        </script>2020
                        All rights reserved | This template is made with <i class="fa fa-heart-o"
                                                                            aria-hidden="true"></i> by <a href="https://colorlib.com" target="_blank">Colorlib</a>
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
<!-- Search End -->
<!-- Js Plugins -->
<script src="../../../js/jquery-3.3.1.min.js"></script>
<script src="../../../js/bootstrap.min.js"></script>
<script src="../../../js/jquery.nice-select.min.js"></script>
<script src="../../../js/jquery.nicescroll.min.js"></script>
<script src="../../../js/jquery.magnific-popup.min.js"></script>
<script src="../../../js/jquery.countdown.min.js"></script>
<script src="../../../js/jquery.slicknav.js"></script>
<script src="../../../js/mixitup.min.js"></script>
<script src="../../../js/owl.carousel.min.js"></script>
<script src="../../../js/main.js"></script>

</body>
</html>