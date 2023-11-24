<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="zxx">
<head>
    <meta charset="UTF-8">
    <meta name="description" content="Male_Fashion Template">
    <meta name="keywords" content="Male_Fashion, unica, creative, html">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title> Sports Clothing </title>

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
    <style>
    </style>

</head>
<body>
<!-- Page Preloder -->
<div id="preloder">
    <div class="loader"></div>
</div>

<!-- Offcanvas Menu Begin -->
<div class="offcanvas-menu-overlay"></div>
<div class="offcanvas-menu-wrapper">
    <div class="offcanvas__option">
        <div class="offcanvas__links">
            <a href="#!">Sign in</a>
            <a href="#!">FAQs</a>
        </div>
        <div class="offcanvas__top__hover">
            <span>Usd <i class="arrow_carrot-down"></i></span>
            <ul>
                <li>USD</li>
                <li>EUR</li>
                <li>USD</li>
            </ul>
        </div>
    </div>
    <div class="offcanvas__nav__option">


        <a href="#!"><img src="/img/icon/cart.png" alt=""> <span>0</span></a>

    </div>
    <div id="mobile-menu-wrap"></div>
    <div class="offcanvas__text">
        <p>Free shipping, 30-day return or refund guarantee.</p>
    </div>
</div>
<!-- Offcanvas Menu End -->

<!-- Header Section Begin -->
<jsp:include page="../layout/header.jsp"/>
<!-- Header Section End -->

<!-- Hero Section Begin -->
<section class="hero">
    <div class="hero__slider owl-carousel">
        <div class="hero__items set-bg" data-setbg="/anh/logogioithieu.jpg" style="height: 100%">
            <div class="container">
                <div class="row">
                    <div class="col-xl-5 col-lg-7 col-md-8">
                        <div class="hero__text">
                            <h2>About us</h2>
                            <p>Sports Clothing wants to bring products that are invested in design, transparent about goods transaction information and provide customers with complete after-sales service.</p>
                            <div class="hero__social">
                                <a href=""><i class="fa fa-facebook"></i></a>
                                <a href=""><i class="fa fa-twitter"></i></a>
                                <a href=""><i class="fa fa-pinterest"></i></a>
                                <a href=""><i class="fa fa-instagram"></i></a>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</section>
<!-- Hero Section End -->

<jsp:include page="gioithieu.jsp"></jsp:include>

<div class="row featurette" style="width: 100%;">
    <div class="col-md-8">
        <h4 class="featurette-heading" style="color: #7a7a7a;margin-top: 170px;margin-left: 90px">DEDICATED AFTER-SALES SERVICE</h4>
        <br>
        <p class="lead" style="margin-left: 90px;font-size: 18px;font-weight: 500; color: rgba(0, 0, 0, 0.7);line-height: 1.72222;">Each product and transaction at Sports Clothing is committed to transparency and complete invoices for the entire transaction. Sports Clothing provides lifetime care and after-sales service with attractive privileges so customers can have the most complete shopping experience.</p>
    </div>

    <div class="col-md-4">
        <img src="/anh/gioithieu1.jpg" style="height: 550px;width: 550px;margin-left: 30px">

    </div>
</div>


<div class="row featurette" style="width: 100%;">
    <div class="col-md-4">
        <img src="/anh/gioithieu5.jpg" style="height: 550px;width: 550px;margin-left: 30px">

    </div>
    <div class="col-md-8">
        <h4 class="featurette-heading" style="color: #7a7a7a;margin-top: 170px;margin-left: 90px">DEDICATED AFTER-SALES SERVICE</h4>
        <br>
        <p class="lead" style="margin-left: 90px;font-size: 18px;font-weight: 500; color: rgba(0, 0, 0, 0.7);line-height: 1.72222;">Each product and transaction at Sports Clothing is committed to transparency and complete invoices for the entire transaction. Sports Clothing provides lifetime care and after-sales service with attractive privileges so customers can have the most complete shopping experience.</p>
    </div>


</div>

<hr class="featurette-divider">


<jsp:include page="../layout/footer.jsp"/>

<!-- Js Plugins -->
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

<script src="/js/banHangOnlline/sanPham.js"></script>
<script>
    document.addEventListener("DOMContentLoaded", function () {
        // Gọi hàm getSanPham khi trang web được tải
        getSanPham(1); // Thay số 1 bằng trang mặc định mà bạn muốn hiển thị ban đầu.
        // Các xử lý khác nếu cần
    });
</script>
</body>
</html>