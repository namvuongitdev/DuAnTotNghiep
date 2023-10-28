<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <link href="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" rel="stylesheet"
          id="bootstrap-css">
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <!------ Include the above in your HEAD tag ---------->

    <link href="https://fonts.googleapis.com/css?family=Poppins:300,400,500,600,700,800&display=swap" rel="stylesheet">
    <link rel="stylesheet" type="text/css"
          href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.12.1/css/all.min.css">
    <link rel="stylesheet" type="text/css"
          href="https://cdnjs.cloudflare.com/ajax/libs/OwlCarousel2/2.3.4/assets/owl.carousel.min.css">
    <link rel="stylesheet" type="text/css"
          href="https://cdnjs.cloudflare.com/ajax/libs/OwlCarousel2/2.3.4/assets/owl.theme.default.min.css">
    <script src="https://cdnjs.cloudflare.com/ajax/libs/OwlCarousel2/2.3.4/owl.carousel.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"
            integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q"
            crossorigin="anonymous"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"
            integrity="	sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl"
            crossorigin="anonymous"></script>
    <style>
        .pd-wrap {
            padding: 40px 0;
            font-family: 'Poppins', sans-serif;
        }

        .heading-section {
            text-align: center;
            margin-bottom: 20px;
        }

        .sub-heading {
            font-family: 'Poppins', sans-serif;
            font-size: 12px;
            display: block;
            font-weight: 600;
            color: #2e9ca1;
            text-transform: uppercase;
            letter-spacing: 2px;
        }

        .heading-section h2 {
            font-size: 32px;
            font-weight: 500;
            padding-top: 10px;
            padding-bottom: 15px;
            font-family: 'Poppins', sans-serif;
        }

        .user-img {
            width: 80px;
            height: 80px;
            border-radius: 50%;
            position: relative;
            min-width: 80px;
            background-size: 100%;
        }

        .carousel-testimonial .item {
            padding: 30px 10px;
        }

        .quote {
            position: absolute;
            top: -23px;
            color: #2e9da1;
            font-size: 27px;
        }

        .name {
            margin-bottom: 0;
            line-height: 14px;
            font-size: 17px;
            font-weight: 500;
        }

        .position {
            color: #adadad;
            font-size: 14px;
        }

        .owl-nav button {
            position: absolute;
            top: 50%;
            transform: translate(0, -50%);
            outline: none;
            height: 25px;
        }

        .owl-nav button svg {
            width: 25px;
            height: 25px;
        }

        .owl-nav button.owl-prev {
            left: 25px;
        }

        .owl-nav button.owl-next {
            right: 25px;
        }

        .owl-nav button span {
            font-size: 45px;
        }

        .product-thumb .item img {
            height: 100px;
        }

        .product-name {
            font-size: 22px;
            font-weight: 500;
            line-height: 22px;
            margin-bottom: 4px;
        }

        .product-price-discount {
            font-size: 22px;
            font-weight: 400;
            padding: 10px 0;
            clear: both;
        }

        .product-price-discount span.line-through {
            text-decoration: line-through;
            margin-left: 10px;
            font-size: 14px;
            vertical-align: middle;
            color: #a5a5a5;
        }

        .display-flex {
            display: flex;
        }

        .align-center {
            align-items: center;
        }

        .product-info {
            width: 100%;
        }

        .reviews-counter {
            font-size: 13px;
        }

        .reviews-counter span {
            vertical-align: -2px;
        }

        .rate {
            float: left;
            padding: 0 10px 0 0;
        }

        .rate:not(:checked) > input {
            position: absolute;
            top: -9999px;
        }

        .rate:not(:checked) > label {
            float: right;
            width: 15px;
            overflow: hidden;
            white-space: nowrap;
            cursor: pointer;
            font-size: 21px;
            color: #ccc;
            margin-bottom: 0;
            line-height: 21px;
        }

        .rate:not(:checked) > label:before {
            content: '\2605';
        }

        .rate > input:checked ~ label {
            color: #ffc700;
        }

        .rate:not(:checked) > label:hover,
        .rate:not(:checked) > label:hover ~ label {
            color: #deb217;
        }

        .rate > input:checked + label:hover,
        .rate > input:checked + label:hover ~ label,
        .rate > input:checked ~ label:hover,
        .rate > input:checked ~ label:hover ~ label,
        .rate > label:hover ~ input:checked ~ label {
            color: #c59b08;
        }

        .product-dtl p {
            font-size: 14px;
            line-height: 24px;
            color: #7a7a7a;
        }

        .product-dtl .form-control {
            font-size: 15px;
        }

        .product-dtl label {
            line-height: 16px;
            font-size: 15px;
        }

        .form-control:focus {
            outline: none;
            box-shadow: none;
        }

        .product-count {
            margin-top: 15px;
        }

        .product-count .qtyminus,
        .product-count .qtyplus {
            width: 34px;
            height: 34px;
            background: #212529;
            text-align: center;
            font-size: 19px;
            line-height: 36px;
            color: #fff;
            cursor: pointer;
        }

        .product-count .qtyminus {
            border-radius: 3px 0 0 3px;
        }

        .product-count .qtyplus {
            border-radius: 0 3px 3px 0;
        }

        .product-count .qty {
            width: 60px;
            text-align: center;
        }

        .round-black-btn {
            border-radius: 4px;
            background: #212529;
            color: #fff;
            padding: 7px 45px;
            display: inline-block;
            margin-top: 20px;
            border: solid 2px #212529;
            transition: all 0.5s ease-in-out 0s;
        }

        .round-black-btn:hover,
        .round-black-btn:focus {
            background: transparent;
            color: #212529;
            text-decoration: none;
        }

        .product-info-tabs {
            margin-top: 25px;
        }

        .product-info-tabs .nav-tabs {
            border-bottom: 2px solid #d8d8d8;
        }

        .product-info-tabs .nav-tabs .nav-item {
            margin-bottom: 0;
        }

        .product-info-tabs .nav-tabs .nav-link {
            border: none;
            border-bottom: 2px solid transparent;
            color: #323232;
        }

        .product-info-tabs .nav-tabs .nav-item .nav-link:hover {
            border: none;
        }

        .product-info-tabs .nav-tabs .nav-item.show .nav-link,
        .product-info-tabs .nav-tabs .nav-link.active,
        .product-info-tabs .nav-tabs .nav-link.active:hover {
            border: none;
            border-bottom: 2px solid #d8d8d8;
            font-weight: bold;
        }

        .product-info-tabs .tab-content .tab-pane {
            padding: 30px 20px;
            font-size: 15px;
            line-height: 24px;
            color: #7a7a7a;
        }

        .review-form .form-group {
            clear: both;
        }

        .mb-20 {
            margin-bottom: 20px;
        }

        .review-form .rate {
            float: none;
            display: inline-block;
        }

        .review-heading {
            font-size: 24px;
            font-weight: 600;
            line-height: 24px;
            margin-bottom: 6px;
            text-transform: uppercase;
            color: #000;
        }

        .review-form .form-control {
            font-size: 14px;
        }

        .review-form input.form-control {
            height: 40px;
        }

        .review-form textarea.form-control {
            resize: none;
        }

        .review-form .round-black-btn {
            text-transform: uppercase;
            cursor: pointer;
        }
    </style>
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
                </div>
            </div>
        </div>
        <div class="canvas__open"><i class="fa fa-bars"></i></div>
    </div>
</header>
<!-- Header Section End -->
<%--body begin--%>
<div class="pd-wrap">
    <div class="container">
        <div class="heading-section">
            <h2>${sanPham.ten}</h2>
        </div>
        <div class="row">
            <div class="col-md-6">
                <div id="slider" class="owl-carousel product-slider">
                    <c:forEach items="${listAnh}" var="anh">
                        <div class="item">
                            <img src="/anh/${anh.ten}"/>
                        </div>
                    </c:forEach>
                </div>
                <div id="thumb" class="owl-carousel product-thumb">
                    <c:forEach items="${listAnh}" var="anh">
                        <div class="item">
                            <img src="/anh/${anh.ten}"/>
                        </div>

                    </c:forEach>
                </div>
            </div>
            <div class="col-md-6">
                <div class="product-dtl">
                    <div class="product-info">
                        <div class="product-name">${sanPham.ten}</div>
                        <div class="product-price-discount"><span>
                            <c:forEach items="${sanPham.sanPhamKhuyenMais}" var="sanPhamKhuyenMai">
                                    <fmt:formatNumber pattern="#,###" value="${sanPhamKhuyenMai.donGiaSauKhiGiam}"></fmt:formatNumber> đ
                                </c:forEach>
                    </div>
                    <p>Form dáng : <span>${sanPham.kieuDang.ten}</span></p>
                    <p>Chất liệu : <span>${sanPham.chatLieu.ten}</span></p>
                    <span>Đặc tính nổi bật</span>
                    <p>${sanPham.moTa}</p>
                    <font color="green">${tong}</font>
                    <div id="tongSanPham"></div>
                    <form method="get" action="/gio-hang-onl/them-moi-gio-hang/${sanPham.id}">
                        <div class="row" style="margin-top: 10px">
                            <div class="col-md-6">
                                <label>Color</label>
                                <br>
                                <select name="color" class="form-control" id="colorSelector">
                                    <c:forEach items="${listMau}" var="mau">
                                        <option value="${mau.id}">${mau.ten}</option>
                                    </c:forEach>
                                </select>
                            </div>

                            <div class="col-md-6">

                                <label>Size</label>
                                <br>
                                <select name="size" class="form-control" onchange="getCTSP(this.value)">
                                    <c:forEach items="${listSize}" var="size">
                                        <option value="${size.id}">${size.ten}</option>
                                    </c:forEach>
                                </select>
                            </div>
                        </div>
                        <br>
                        <div id="soLuong" style="color: cadetblue">
                        </div>
                        <div id="trangThai" style="color: cadetblue">
                        </div>
                <br>
                    <div class="product-count" >
                        <label>Quantity</label>
                        <div class="display-flex">
                            <div class="qtyminus" style="">-</div>
                            <input type="text" style="height: 35px" name="quantity" value="1" class="qty">
                            <div class="qtyplus">+</div>

                            <button type="submit" id="themVaoGioHang"
                                    class="round-black-btn" style="margin-top: 60px;margin-left: 20px;
                                    ">Add to Cart</button>
                        </div>
                    </div>
                    </form>
                </div>

            </div>
        </div>
        <div class="product-info-tabs">
            <ul class="nav nav-tabs" id="myTab" role="tablist">
                <li class="nav-item">
                    <a class="nav-link active" id="description-tab" data-toggle="tab" href="#description" role="tab"
                       aria-controls="description" aria-selected="true">Chính sách đổi hàng</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" id="review-tab" data-toggle="tab" href="#review" role="tab"
                       aria-controls="review" aria-selected="false">Hướng dẫn chọn size</a>
                </li>
            </ul>
            <div class="tab-content" id="myTabContent">
                <div class="tab-pane fade show active" id="description" role="tabpanel"
                     aria-labelledby="description-tab">
                    <li>Miễn phí đổi hàng cho khách mua ở Shop trong trường hợp bị lỗi từ nhà sản xuất, giao nhầm
                        hàng,
                        nhầm size.
                    </li>
                    <li>Quay video mở sản phẩm khi nhận hàng, nếu không có video unbox, khi phát hiện lỗi phải báo
                        ngay
                        cho Shop trong 1 ngày tính từ ngày giao hàng thành công. Qua 1 ngày chúng mình không giải
                        quyết
                        khi không có video unbox.
                    </li>
                    <li>Sản phẩm đổi trong thời gian 3 ngày kể từ ngày nhận hàng.</li>
                    <li>Sản phẩm còn mới nguyên tem, tags, sản phẩm chưa giặt và không dơ bẩn, hư hỏng bởi những tác
                        nhân bên ngoài cửa hàng sau khi mua hàng.
                    </li>
                </div>
                <div class="tab-pane fade" id="review" role="tabpanel" aria-labelledby="review-tab">
                    Để lấy số đo cỡ áo thun , các bạn cần một cái thước mềm (thường gọi là thước dây, thước vải) để
                    đo.
                    <li>Đo vòng cổ: quấn thước dây vòng quanh cổ, đo vòng quanh thân cổ, chèn thêm 1 ngón tay vào
                        phía
                        trong giữa cổ và thước khi đo.
                    </li>
                    <li>Đo vòng ngực: quấn thước dây vòng qua ngực, đo chỗ kích thước lớn nhất.</li>
                    <li>Đo vòng eo: quấn thước dây qua eo, đo quanh vòng eo, chèn thêm 2 ngón tay vào phía trong
                        giữa eo
                        và thước khi đo.
                    </li>
                    <li>Vòng mông: quấn thước dây vòng ngang mông, đo chỗ kích thước lớn nhất.</li>
                    <li>Chiều cao: đo từ bàn chân đến đỉnh đầu ở tư thế đứng thẳng.</li>
                    <p>Lưu ý khi đo vòng ngực: hãy mặc chiếc áo ngực mà mình cảm thấy thoải mái nhất khi đo vòng
                        ngực,
                        đừng mặc quá chật, đừng mặc quá rộng, cũng đừng đo khi không mặc gì, trừ khi bạn chắc chắn
                        rằng
                        bạn sẽ mặc chiếc áo bạn sắp mua mà không cần áo ngực.</p>
                    <p>Lưu ý khi đo vòng mông: hãy đứng thẳng khi đo, chắc chắn rằng bạn không gập người hay cúi ra
                        phía
                        trước khi đo, nếu bạn không chắc chắn, hãy nhờ người khác đo giùm mình trong khi mình đứng
                        thẳng
                        nhé. Đừng đo vòng eo lúc vừa ăn no hay lúc bụng đang đói, đo vòng eo lúc sắp đi ngủ là tốt
                        nhất.</p>
                    <p>Sau khi bạn đã lấy được số đo của mình, xin mời bạn đối chiếu với bảng dưới đây để biết được
                        size
                        vừa với mình nhất.</p>
                    <img src="/anh/hdsz.png" style="width: 1100px">

                </div>

                <div style="text-align:center;font-size:14px;padding-bottom:20px;">Get free icon packs for your next
                    project at
                    <a href="http://iiicons.in/" target="_blank"
                       style="color:#ff5e63;font-weight:bold;">www.iiicons.in</a>
                </div>
            </div>
        </div>
    </div>
</div>
<%--body end--%>
<%-- sản phẩm liên quan begin--%>
<div class="container-fluid bg-3 text-center">
    <h2>Sản Phẩm Liên Quan </h2><br>
    <div class="row">
        <c:forEach items="${listSanPham}" var="sp">
            <div class="col-sm-3">
                        <div class="product__item" style="margin-left: 30px">
                            <div class="product__item__pic">
                                <img src="/anh/${sp.img}" style="swidth:265px;height: 270px" >
                                <ul class="product__hover">
                                    <form method="get" action="/index/chi-tiet-san-pham/${sp.id}">
                                        <li><a href="/index/chi-tiet-san-pham-onl?id=${sp.id}"><img src="/anh/eye.png"style="margin-right: 20px;width:40px" alt=""></a></li>
                                    </form>
                                </ul>
                            </div>
                            <div class="product__item__text">
                                <h6>${sp.ten}</h6>
                                    <%--                    <a href="#" class="add-cart">+ Add To Cart</a>--%>

                                <c:forEach items="${sp.sanPhamKhuyenMais}" var="sanPhamKhuyenMai">
                                    <strike><fmt:formatNumber pattern="#,###"
                                                              value="${sanPhamKhuyenMai.donGiaSauKhiGiam == null || sanPhamKhuyenMai.donGiaSauKhiGiam==0 ? '' : sp.giaBan }"></fmt:formatNumber></strike>
                                    <fmt:formatNumber pattern="#,###"
                                                      value="${sanPhamKhuyenMai.donGiaSauKhiGiam != null  || sanPhamKhuyenMai.donGiaSauKhiGiam!=0 ? sp.giaBan : '' }"></fmt:formatNumber>
                                    <p>  <fmt:formatNumber pattern="#,###" value="${sanPhamKhuyenMai.donGiaSauKhiGiam}"></fmt:formatNumber> đ </p>
                                </c:forEach>
                            </div>
                        </div>
            </div>
        </c:forEach>

    </div>
</div><br>

<%-- sản phẩm liên quan end--%>
<!-- Footer Section begin -->
<footer class="footer">
    <div class="container">
        <div class="row">
            <div class="col-lg-3 col-md-6 col-sm-6">
                <div class="footer__about">
                    <div class="footer__logo">
                        <a href="#"><img src="img/footer-logo.png" alt=""></a>
                    </div>
                    <p>The customer is at the heart of our unique business model, which includes design.</p>
                    <a href="#"><img src="img/payment.png" alt=""></a>
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
    let mauSacSP = document.getElementById("colorSelector");
    var myVariable = new MyCustomType('${sanPham.id}');
    const themVaoGioHang = document.getElementById("themVaoGioHang");
    function MyCustomType(data) {
        this.data = data;
    }
    function getCTSP(id) {
        fetch('/index/so-luong/' + id + '/' + mauSacSP.value + '?id='+ myVariable.data )
            .then(response => response.json())
            .then(data => {

                document.getElementById("soLuong").innerHTML = `Số Lượng :` + data.soLuong +`.`;
                if(data.trangThai==1){
                    themVaoGioHang.removeAttribute('disabled');
                    document.getElementById("trangThai").innerHTML = `Đang Kinh Doanh.`;
                    themVaoGioHang.style.backgroundColor = "black";
                }else {
                    themVaoGioHang.setAttribute("disabled", "");
                    document.getElementById("trangThai").innerHTML = `Ngừng Kinh Doanh.`;
                    themVaoGioHang.style.backgroundColor = "#CCCCCC";
                }
            });
    }

    $(document).ready(function () {
        var slider = $("#slider");
        var thumb = $("#thumb");
        var colorSelector = $("#colorSelector");
        var slidesPerPage = 4; //globaly define number of elements per page
        var syncedSecondary = true;
        slider.owlCarousel({
            items: 1,
            slideSpeed: 2000,
            nav: false,
            autoplay: false,
            dots: false,
            loop: true,
            responsiveRefreshRate: 200
        }).on('changed.owl.carousel', syncPosition);

        thumb.on('initialized.owl.carousel', function () {
                thumb.find(".owl-item").eq(0).addClass("current");
            })
            .owlCarousel({
                items: slidesPerPage,
                dots: false,
                nav: true,
                item: 4,
                smartSpeed: 200,
                slideSpeed: 500,
                slideBy: slidesPerPage,
                navText: ['<svg width="18px" height="18px" viewBox="0 0 11 20"><path style="fill:none;stroke-width: 1px;stroke: #000;" d="M9.554,1.001l-8.607,8.607l8.607,8.606"/></svg>', '<svg width="25px" height="25px" viewBox="0 0 11 20" version="1.1"><path style="fill:none;stroke-width: 1px;stroke: #000;" d="M1.054,18.214l8.606,-8.606l-8.606,-8.607"/></svg>'],
                responsiveRefreshRate: 100
            }).on('changed.owl.carousel', syncPosition2);

        function syncPosition(el) {
            var count = el.item.count - 1;
            var current = Math.round(el.item.index - (el.item.count / 2) - .5);
            if (current < 0) {
                current = count;
            }
            if (current > count) {
                current = 0;
            }
            thumb
                .find(".owl-item")
                .removeClass("current")
                .eq(current)
                .addClass("current");
            var onscreen = thumb.find('.owl-item.active').length - 1;
            var start = thumb.find('.owl-item.active').first().index();
            var end = thumb.find('.owl-item.active').last().index();
            if (current > end) {
                thumb.data('owl.carousel').to(current, 100, true);
            }
            if (current < start) {
                thumb.data('owl.carousel').to(current - onscreen, 100, true);
            }
        }

        function syncPosition2(el) {
            if (syncedSecondary) {
                var number = el.item.index;
                slider.data('owl.carousel').to(number, 100, true);
            }
        }

        thumb.on("click", ".owl-item", function (e) {
            e.preventDefault();
            var number = $(this).index();
            slider.data('owl.carousel').to(number, 300, true);
        });


        $(".qtyminus").on("click", function () {
            var now = $(".qty").val();
            if ($.isNumeric(now)) {
                if (parseInt(now) - 1 > 0) {
                    now--;
                }
                $(".qty").val(now);
            }
        })
        $(".qtyplus").on("click", function () {
            var now = $(".qty").val();
            if ($.isNumeric(now)) {
                $(".qty").val(parseInt(now) + 1);
            }
        });
    });

</script>

<script src="/js/banHangOnlline/sanPham.js"></script>
</body>
</html>