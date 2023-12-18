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
    <link rel="stylesheet" href="/css/banHangOnline/trangchu/trangchu.css" type="text/css">



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
        <div class="hero__items set-bg" data-setbg="/img/hero/nen3.png">
            <div class="container">
                <div class="row">
                    <div class="col-xl-5 col-lg-7 col-md-8">
                        <div class="hero__text">
                            <h6>Summer Collection</h6>
                            <h2>Fall - Winter Collections 2030</h2>
                            <p>A specialist label creating luxury essentials. Ethically crafted with an unwavering
                                commitment to exceptional quality.</p>
                            <a href="" class="primary-btn">Shop now <span class="arrow_right"></span></a>
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
        <div class="hero__items set-bg" data-setbg="/img/hero/slider_2.jpg">
            <div class="container">
                <div class="row">
                    <div class="col-xl-5 col-lg-7 col-md-8">
                        <div class="hero__text">
                            <h6>Summer Collection</h6>
                            <h2>Fall - Winter Collections 2030</h2>
                            <p>A specialist label creating luxury essentials. Ethically crafted with an unwavering
                                commitment to exceptional quality.</p>
                            <a href="" class="primary-btn">Shop now <span class="arrow_right"></span></a>
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

<!-- Men , Woman fashion Begin -->
<section class="banner spad">
    <div class="container">
        <div class="row">
            <div class="col-lg-7 offset-lg-4">
                <div class="banner__item">
                    <div class="banner__item__pic">
                        <img src="/anh/se1.jpg" alt="">
                    </div>
                    <div class="banner__item__text">
                        <h2>Men</h2>
                        <a onclick="getSanPhamThoiTrangNam(1)">Shop now</a>
                    </div>
                </div>
            </div>
            <div class="col-lg-5">
                <div class="banner__item banner__item--middle">
                    <div class="banner__item__pic">
                        <img src="/anh/se2.png" alt="">
                    </div>
                    <div class="banner__item__text">
                        <h2>Woman</h2>
                        <a onclick="getSanPhamThoiTrangNu(1)">Shop now</a>
                    </div>
                </div>
            </div>

        </div>
    </div>
</section>
<!-- Men , Woman fashion End -->
<H4 style="margin-left: 650px"><b>DANH MỤC SẢN PHẨM</b></H4>
<br>
<%-- danh mục sản phẩm begin --%>
<section class="section">
    <!-- Begin Page Content -->
    <div class="container-fluid">
        <!-- Content Row -->
        <div class="row">
            <c:forEach items="${danhMuc}" var="danhMuc">
                <!-- Earnings (Monthly) Card Example -->
                <div class="col-xl-3 col-md-6 mb-4">
                    <div class="card border-left-primary shadow h-100 py-2">
                        <div class="card-body">
                            <div class="row no-gutters align-items-center">
                                <div class="col mr-2">
                                    <div class="text-xs font-weight-bold text-primary text-uppercase mb-1" style="height: 30px">
                                     <a onclick="danhMucFilter('${danhMuc.id}')" style="color: black"> ${danhMuc.ten} </a> </div>
                                </div>
                                <div class="col-auto">
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </c:forEach>


        </div>
    </div>
</section>
<%-- danh mục sản phẩm end--%>
<%--hiển thị sản phẩm begin--%>
<section class="product spad">
    <div class="container">
        <div class="row product__filter">
            <div id="body">

            </div>
        </div>
    </div>
</section>
<%--hiển thị sản phẩm end--%>

<%--phân trang begin--%>
<div class="container-fluid mt-5">
    <nav aria-label="Page navigation example">
        <ul class="pagination justify-content-center" id="phanTrang">
        </ul>
    </nav>
</div>
<%--phân trang end--%>
<br>

<!-- Instagram Section Begin -->
<section class="instagram spad">
    <div class="container">
        <div class="row">
            <div class="col-lg-8">
                <div class="instagram__pic">
                    <div class="instagram__pic__item set-bg" data-setbg="/anh/inta1.png"></div>
                    <div class="instagram__pic__item set-bg" data-setbg="/anh/inta2.png"></div>
                    <div class="instagram__pic__item set-bg" data-setbg="/anh/inta3.png"></div>
                    <div class="instagram__pic__item set-bg" data-setbg="/anh/inta4.png"></div>
                    <div class="instagram__pic__item set-bg" data-setbg="/anh/inta5.png"></div>
                    <div class="instagram__pic__item set-bg" data-setbg="/anh/inta6.png"></div>
                </div>
            </div>
            <div class="col-lg-4">
                <div class="instagram__text">
                    <h2>Greeting</h2>
                    <p>Hopefully Sports Clothing will bring you the best experience.Please always love and support
                        us.Thank you.</p>
                </div>
            </div>
        </div>
    </div>
</section>
<!-- Instagram Section End -->

<!-- Latest Blog Section Begin -->
<section class="latest spad">
    <div class="container">
        <div class="container">
            <div class="row">
                <div class="col-lg-12">
                    <div class="section-title">
                        <span>Latest News</span>
                        <h2>Fashion New Trends</h2>
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="col-lg-4 col-md-6 col-sm-6">
                    <div class="blog__item">
                        <div class="blog__item__pic set-bg" data-setbg="/anh/blog.png"></div>
                        <div class="blog__item__text">
                            <span><img src="/img/icon/calendar.png" alt=""> 16 February 2020</span>
                            <h5>What Curling Irons Are The Best Ones</h5>
                            <a href="#">Read More</a>
                        </div>
                    </div>
                </div>
                <div class="col-lg-4 col-md-6 col-sm-6">
                    <div class="blog__item">
                        <div class="blog__item__pic set-bg" data-setbg="/anh/blog1.png"></div>
                        <div class="blog__item__text">
                            <span><img src="/img/icon/calendar.png" alt=""> 21 February 2020</span>
                            <h5>Eternity Bands Do Last Forever</h5>
                            <a href="#">Read More</a>
                        </div>
                    </div>
                </div>
                <div class="col-lg-4 col-md-6 col-sm-6">
                    <div class="blog__item">
                        <div class="blog__item__pic set-bg" data-setbg="/anh/blog2.png"></div>
                        <div class="blog__item__text">
                            <span><img src="/img/icon/calendar.png" alt=""> 28 February 2020</span>
                            <h5>The Health Benefits Of Sunglasses</h5>
                            <a href="#">Read More</a>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</section>
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
        // sanPhamNhieuNguoiMua()
        // Gọi hàm getSanPham khi trang web được tải
        // getSanPham(1); // Thay số 1 bằng trang mặc định mà bạn muốn hiển thị ban đầu.
        // Các xử lý khác nếu cần
    });

    function active_course() {
        if ($(".active_course").length) {
            $(".active_course").owlCarousel({
                loop: true,
                margin: 20,
                items: 3,
                nav: true,
                autoplay: 2500,
                smartSpeed: 1500,
                dots: false,
                responsiveClass: true,
                thumbs: true,
                thumbsPrerendered: true,
                navText: ["<img src='https://colorlib.com/preview/theme/edustage/img/prev.png'>", "<img src='https://colorlib.com/preview/theme/edustage/img/next.png'>"],
                responsive: {
                    0: {
                        items: 1,
                        margin: 0
                    },
                    991: {
                        items: 2,
                        margin: 30
                    },
                    1200: {
                        items: 3,
                        margin: 30
                    }
                }
            });
        }
    }
    active_course();
</script>
</body>
</html>