<%@ page language="java" contentType="text/html; charset=UTF-8" %>
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
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"
            integrity="sha384-geWF76RCwLtnZ8qwWowPQNguL3RmwHVBC9FhGdlKrxdiJJigb/j/68SIy3Te4Bkz"
            crossorigin="anonymous"></script>

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
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css"
          rel="stylesheet" integrity="sha384-9ndCyUaIbzAi2FUVXJi0CjmCapSmO7SnpJef0486qhLnuZ2cdeRhO02iuK6FUUVM"
          crossorigin="anonymous">
    <style>
        .pd-wrap {
            padding-top: 40px;
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
                    <a href="/gio-hang-onl" class="position-relative">
                        <img src="/img/icon/cart.png" alt="" style="width: 30px ; height: 30px">
                        <span class="position-absolute top-0 start-100 translate-middle badge rounded-pill bg-danger"
                              style="color: #FFFFFF">
                            ${count != null ? count : 0}
                        </span>
                    </a>
                </div>
            </div>
            <c:choose>
                <c:when test="${error != null}">
                    <jsp:include page="../notiface/error.jsp"></jsp:include>
                </c:when>
                <c:otherwise>
                    <c:if test="${success != null}">
                        <jsp:include page="../notiface/success.jsp"></jsp:include>
                    </c:if>
                </c:otherwise>
            </c:choose>
        </div>
        <div class="canvas__open"><i class="fa fa-bars"></i></div>
    </div>
</header>
<!-- Header Section End -->
<%--body begin--%>
<div class="pd-wrap">
    <div class="container">
        <div class="row">

            <div class="col-md-6" id="anhMauSac">
                <div id="slider" class="owl-carousel product-slider">
                    <c:forEach items="${listAnh}" var="anh">
                        <div class="item">
                            <img src="/image/${anh.ten}"/>
                        </div>
                    </c:forEach>
                </div>
                <div id="thumb" class="owl-carousel product-thumb">
                    <c:forEach items="${listAnh}" var="anh">
                        <div class="item">
                            <img src="/image/${anh.ten}"/>
                        </div>

                    </c:forEach>
                </div>
            </div>
            <div class="col-md-6">
                <div class="product-dtl">
                    <div class="product-info">
                        <div class="product-name">${sanPham.ten}</div>
                        <div class="product-price-discount">
                            <div class="row">
                                <c:choose>
                                    <c:when test="${sanPhamKhuyenMai != null}">
                                        <div class="col-sm-3">
                                            <strike> <fmt:formatNumber pattern="#,###"
                                                                       value="${sanPham.giaBan}"></fmt:formatNumber>đ</strike>
                                        </div>
                                        <div class="col-sm-3">
                                            <c:choose>
                                                <c:when test="${sanPhamKhuyenMai.loaiGiamGia}">
                                                 <span style="color: #E43535">
                                                   -${sanPhamKhuyenMai.mucGiam.intValue()}%
                                                </span>
                                                </c:when>
                                                <c:otherwise>
                                                 <span style="color: #E43535">
                                                   -<fmt:formatNumber pattern="#,###"
                                                                      value="${sanPhamKhuyenMai.mucGiam.intValue()}"></fmt:formatNumber>đ
                                                </span>
                                                </c:otherwise>
                                            </c:choose>
                                        </div>
                                        <span style="color: #005cbf">
                                    <fmt:formatNumber pattern="#,###"
                                                      value="${sanPhamKhuyenMai.donGiaSauKhiGiam}"></fmt:formatNumber>đ
                                     </span>
                                    </c:when>
                                    <c:otherwise>
                                          <span style="color: #005cbf">
                                    <fmt:formatNumber pattern="#,###"
                                                      value="${sanPham.giaBan}"></fmt:formatNumber>đ
                                     </span>
                                    </c:otherwise>
                                </c:choose>
                            </div>
                        </div>
                        <p>Form dáng : <span>${sanPham.kieuDang.ten}</span></p>
                        <p>Chất liệu : <span>${sanPham.chatLieu.ten}</span></p>
                        <span>Đặc tính nổi bật</span>
                        <p>${sanPham.moTa}</p>
                        <font color="green">${tong}</font>
                        <div id="tongSanPham"></div>
                        <form method="post" action="/gio-hang-onl/them-moi-gio-hang/${sanPham.id}">
                            <div class="row" style="margin-top: 10px">
                                <div class="row">
                                    <div class="form-check">
                                        <label>Color</label>
                                        <br>
                                        <c:forEach items="${listMau}" var="mau">
                                            <input type="radio" class="btn-check" onclick="getCTSP({
                                       id:this.value,
                                        type:'mauSac'
                                                 })"
                                                   value="${mau.id}" ${dataRequest.idMS == mau.id ? "checked" : ""} name="color" id="${mau.id}"
                                                   autocomplete="off">
                                            <label class="btn btn-outline-secondary" for="${mau.id}">${mau.ten}
                                            </label>
                                        </c:forEach>
                                    </div>

                                </div>

                                <div class="row">

                                    <label>Size</label>
                                    <br>
                                    <div class="form-check" id="kichCo">
                                        <c:forEach items="${listSize}" var="size">
                                            <input type="radio" class="btn-check" onclick="getCTSP({
                                       id:this.value,
                                        type:'size'
                                                 })"
                                                   value="${size.id}" name="size" id="${size.id}"
                                                   autocomplete="off" ${dataRequest.idKC == size.id ? "checked" : ""}>
                                            <label class="btn btn-outline-secondary" for="${size.id}">${size.ten}
                                            </label>
                                        </c:forEach>
                                    </div>
                                </div>
                            </div>
                            <div id="soLuong" style="color: cadetblue">
                            </div>
                            <div id="trangThai" style="color: cadetblue">
                            </div>
                            <div class="product-count">
                                <label for="quantity">Quantity</label>
                                <div class="display-flex">
                                    <input type="number" style="height: 35px ;width: 120px  " id="quantity" name="quantity"
                                           class="form-control" value="${dataRequest.soLuong != null ? dataRequest.soLuong : 1}">
                                    <button type="submit" id="themVaoGioHang"
                                            class="round-black-btn" style="
                                    margin-top: 60px;margin-left: 20px;
                                    ">Add to Cart
                                    </button>
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
    <%-- sản phẩm liên quan end--%>
    <%--    <!-- Footer Section begin -->--%>
    <jsp:include page="../layout/footer.jsp"/>
    <%--    <!-- Js Plugins -->--%>
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
        let mauSacSP = undefined;
        let kichCoSP = undefined;
        let myVariable = new MyCustomType('${sanPham.id}');
        const themVaoGioHang = document.getElementById("themVaoGioHang");
        const quantity = document.getElementById("quantity");
        const anhMauSac = document.getElementById("anhMauSac");
        const kichCo = document.getElementById("kichCo");

        function MyCustomType(data) {
            this.data = data;
        }

        async function getCTSP(value) {
            if (value.type == 'size') {
                kichCoSP = value.id;
            }
            if (value.type == 'mauSac') {
                mauSacSP = value.id;
                kichCoSP = undefined;
                kichCo.replaceChildren();
                const responseSize = await fetch('/index/kich-co?idSP=' + myVariable.data + '&idMS=' + mauSacSP)
                const data = await responseSize.json();
                data.map(function (size) {
                    kichCo.innerHTML += ` <input type="radio" class="btn-check" onclick="getCTSP({
                                                id:this.value,
                                                 type:'size'
                                                         })"
                              value="` + size.id + `" name="size" id="` + size.id + `"
                             autoComplete="off">
                          <label class="btn btn-outline-secondary" for="` + size.id + `">` + size.ten + `
                          </label>`
                })
                const responseAnh = await fetch('/index/anh-mau-sac?idSP=' + myVariable.data + '&idMS=' + mauSacSP)
                const dataAnh = await responseAnh.json()
                anhMauSac.replaceChildren();
                anhMauSac.innerHTML = `<div class="row" id='anhs'></div>`
                dataAnh.map(function (anh) {
                    document.getElementById("anhs").innerHTML += `
                             <div class="col-sm-5">
                                <img src="/image/` + anh.ten + `" alt="">
                             </div>
                        `
                })
            }
            if (mauSacSP != undefined && kichCoSP != undefined) {
                fetch('/index/so-luong/' + kichCoSP + '/' + mauSacSP + '?id=' + myVariable.data)
                    .then(response => response.json())
                    .then(data => {
                        document.getElementById("soLuong").innerHTML = `Số Lượng :` + data.soLuong + `.`;
                        if (data.trangThai == 1) {
                            themVaoGioHang.removeAttribute('disabled');
                            document.getElementById("trangThai").innerHTML = `Đang Kinh Doanh.`;
                            themVaoGioHang.style.backgroundColor = "black";
                        } else if (data.trangThai == 0) {
                            document.getElementById("trangThai").innerHTML = `Ngừng Kinh Doanh.`;
                            themVaoGioHang.setAttribute("disabled", "");
                            themVaoGioHang.style.backgroundColor = "#CCCCCC";
                        }
                        if (data.soLuong == 0) {
                            themVaoGioHang.setAttribute("disabled", "");
                            themVaoGioHang.style.backgroundColor = "#CCCCCC";
                        }

                    });
            }

        }

        // async function themVaoGiohang(sanPham) {
        //     if (mauSacSP == null || kichCoSP == null) {
        //         alert("chưa lựa chọn thuộc tính sản phẩm");
        //     } else if (quantity.value <= 0) {
        //         alert("Số lượng không hợp lệ.");
        //     } else {
        //         const options = {
        //             method: 'POST',
        //             headers: {'Content-Type': 'application/json'},
        //         }
        //
        //     }
        // }

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
</body>
</html>