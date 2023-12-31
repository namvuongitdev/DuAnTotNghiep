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
 <br> <br>
<%--Filter Begin--%>
<div class="modal-body">
<%--    <div class="row">--%>
<%--        <div class="col-sm-3">--%>
<%--            <input class="form-control" type="text" name="search" id="search-input"--%>
<%--                   style="width: 915px;margin-left: 160px" placeholder="Tìm kiếm ....">--%>
<%--        </div>--%>
<%--        <div class="col-sm-2">--%>
<%--            <button style="margin-left: 740px;width: 100px" class="btn btn-light" onclick="timKiem()">Tìm kiếm</button>--%>
<%--        </div>--%>
<%--        <div class="col-sm-2">--%>
<%--            <button style="margin-left: 630px;width: 100px" class="btn btn-secondary" id="clear">Làm mới</button>--%>
<%--        </div>--%>
<%--    </div>--%>
<%--    <br>--%>
    <div class="row" style="margin-left: 140px">
        <div class="row d-flex justify-content-center" style="margin-bottom: 10px;margin-left: 175px">
            <div class="col-sm-1.5">
                <label for="size">Kích cỡ</label><br>
                <select name="kichCo" id="size" class="form-select" onchange="filterSize(this.value)">
                    <option value="">Tất cả</option>
                    <c:forEach items="${listKichCo}" var="kichCo">
                        <option value="${kichCo.id}">
                                ${kichCo.ten}
                        </option>
                    </c:forEach>
                </select>
            </div>
            <div class="col-sm-1.5" style="margin-left: 30px">
                <%--@declare id="mausac"--%><label for="mauSac">Màu sắc</label><br>
                <select name="mauSac" id="color" class="form-select" onchange="filterColor(this.value)">
                    <option value="">Tất cả</option>
                    <c:forEach items="${listMuaSac}" var="mauSac">
                        <option value="${mauSac.id}">
                                ${mauSac.ten}
                        </option>
                    </c:forEach>
                </select>
            </div>
            <div class="col-sm-1.5" style="margin-left: 30px">
                <label for="danhMuc">Danh mục</label><br>
                <select name="danhMuc" id="danhMuc" class="form-select" onchange="filterDanhMuc(this.value)">
                    <option value="">Tất cả</option>
                    <c:forEach items="${listDanhMuc}" var="danhMuc">
                        <option value="${danhMuc.id}">
                                ${danhMuc.ten}
                        </option>
                    </c:forEach>
                </select>
            </div>
            <div class="col-sm-1.5" style="margin-left: 30px">
                <label for="chatLieu">Chất liệu</label><br>
                <select name="chatLieu" id="chatLieu" class="form-select" onchange="filterChatLieu(this.value)">
                    <option value="">Tất cả</option>
                    <c:forEach items="${listChatLieu}" var="chatLieu">
                        <option value="${chatLieu.id}">
                                ${chatLieu.ten}
                        </option>
                    </c:forEach>
                </select>
            </div>
            <div class="col-sm-1.5" style="margin-left: 30px">
                <label for="kieuDang">Kiểu dáng</label><br>
                <select name="kieuDang" id="kieuDang" class="form-select" onchange="filterKieuDang(this.value)">
                    <option value="">Tất cả</option>
                    <c:forEach items="${listFormDang}" var="kieuDang">
                        <option value="${kieuDang.id}">
                                ${kieuDang.ten}
                        </option>
                    </c:forEach>
                </select>
            </div>
            <div class="col-sm-1.5" style="margin-left: 30px">
                <label for="sapXep">Sắp xếp</label><br>
                <select name="sapXep" id="sapXep" class="form-select" onchange="filterSapXep(this.value)">
                    <option value="">Tất cả</option>
                    <option value="ngayTao">Mới nhất</option>
                    <option value="price-asc">
                        Thứ tự theo giá: thấp đến cao
                    </option>
                    <option value="price-desc">
                        Thứ tự theo giá: cao xuống thấp
                    </option>
                </select>
            </div>
            <div class="col-sm-1.5" style="margin-left: 30px">
                <label for="gioiTinh">Giới tính</label><br>
                <select name="gioiTih" id="gioiTinh" class="form-select" onchange="filterTrangThai(this.value)">
                    <option value="">Tất cả</option>
                    <option value="0">
                        Nam
                    </option>
                    <option value="1">
                        Nữ
                    </option>
                </select>
            </div>
        </div>
    </div>
    <br>
</div>
<%--Filter End--%>

<%--hiển thị sản phẩm begin--%>
<section class="product spad">
    <div class="container">
        <div class="row">
            <div class="col-lg-12">
                <ul class="filter__controls">
                    <li class="active" data-filter="*">ALL</li>
                </ul>
            </div>
        </div>
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
        // Gọi hàm getSanPham khi trang web được tải
        getSanPham(1); // Thay số 1 bằng trang mặc định mà bạn muốn hiển thị ban đầu.
        // Các xử lý khác nếu cần
    });
</script>
</body>
</html>