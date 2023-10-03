<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css"
          rel="stylesheet" integrity="sha384-9ndCyUaIbzAi2FUVXJi0CjmCapSmO7SnpJef0486qhLnuZ2cdeRhO02iuK6FUUVM"
          crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"
            integrity="sha384-geWF76RCwLtnZ8qwWowPQNguL3RmwHVBC9FhGdlKrxdiJJigb/j/68SIy3Te4Bkz"
            crossorigin="anonymous"></script>

    <link rel="stylesheet"
          href="https://cdnjs.cloudflare.com/ajax/libs/material-design-iconic-font/2.2.0/css/material-design-iconic-font.min.css">
    <script></script>
    <style>
        body {
            /*margin-top: 20px;*/
            background-color: #f4f7f6;
        }

        .c_review {
            margin-bottom: 0
        }

        .c_review li {
            margin-bottom: 16px;
            padding-bottom: 13px;
            border-bottom: 1px solid #e8e8e8
        }

        .c_review li:last-child {
            margin: 0;
            border: none
        }

        .c_review .avatar {
            float: left;
            width: 80px
        }

        .c_review .comment-action {
            float: left;
            width: calc(100% - 80px)
        }

        .c_review .comment-action .c_name {
            margin: 0
        }

        .c_review .comment-action p {
            text-overflow: ellipsis;
            white-space: nowrap;
            overflow: hidden;
            max-width: 95%;
            display: block
        }

        .product_item:hover .cp_img {
            top: -40px
        }

        .product_item:hover .cp_img img {
            box-shadow: 0 19px 38px rgba(0, 0, 0, 0.3), 0 15px 12px rgba(0, 0, 0, 0.22)
        }

        .product_item:hover .cp_img .hover {
            display: block
        }

        .product_item .cp_img {
            position: absolute;
            top: 0px;
            left: 50%;
            transform: translate(-50%);
            -webkit-transform: translate(-50%);
            -ms-transform: translate(-50%);
            -moz-transform: translate(-50%);
            -o-transform: translate(-50%);
            -khtml-transform: translate(-50%);
            width: 100%;
            padding: 15px;
            transition: all 0.2s ease-in-out
        }

        .product_item .cp_img img {
            transition: all 0.2s ease-in-out;
            border-radius: 6px
        }

        .product_item .cp_img .hover {
            display: none;
            text-align: center;
            margin-top: 10px
        }

        .product_item .product_details {
            padding-top: 110%;
            text-align: center
        }

        .product_item .product_details h5 {
            margin-bottom: 5px
        }

        .product_item .product_details h5 a {
            font-size: 16px;
            color: #444
        }

        .product_item .product_details h5 a:hover {
            text-decoration: none
        }

        .product_item .product_details .product_price {
            margin: 0
        }

        .product_item .product_details .product_price li {
            display: inline-block;
            padding: 0 10px
        }

        .product_item .product_details .product_price .new_price {
            font-weight: 600;
            color: #ff4136
        }

        .product_item_list table tr td {
            vertical-align: middle
        }

        .product_item_list table tr td h5 {
            font-size: 15px;
            margin: 0
        }

        .product_item_list table tr td .btn {
            box-shadow: none !important
        }

        .product-order-list table tr th:last-child {
            width: 145px
        }

        .preview {
            display: -webkit-box;
            display: -webkit-flex;
            display: -ms-flexbox;
            display: flex;
            -webkit-box-orient: vertical;
            -webkit-box-direction: normal;
            -webkit-flex-direction: column;
            -ms-flex-direction: column;
            flex-direction: column
        }

        .preview .preview-pic {
            -webkit-box-flex: 1;
            -webkit-flex-grow: 1;
            -ms-flex-positive: 1;
            flex-grow: 1
        }

        .preview .preview-thumbnail.nav-tabs {
            margin-top: 15px;
            font-size: 0
        }

        .preview .preview-thumbnail.nav-tabs li {
            width: 20%;
            display: inline-block
        }

        .preview .preview-thumbnail.nav-tabs li nav-link img {
            max-width: 100%;
            display: block
        }

        .preview .preview-thumbnail.nav-tabs li a {
            padding: 0;
            margin: 2px;
            border-radius: 0 !important;
            border-top: none !important;
            border-left: none !important;
            border-right: none !important
        }

        .preview .preview-thumbnail.nav-tabs li:last-of-type {
            margin-right: 0
        }

        .preview .tab-content {
            overflow: hidden
        }

        .preview .tab-content img {
            width: 100%;
            -webkit-animation-name: opacity;
            animation-name: opacity;
            -webkit-animation-duration: .3s;
            animation-duration: .3s
        }

        .details {
            display: -webkit-box;
            display: -webkit-flex;
            display: -ms-flexbox;
            display: flex;
            -webkit-box-orient: vertical;
            -webkit-box-direction: normal;
            -webkit-flex-direction: column;
            -ms-flex-direction: column;
            flex-direction: column
        }

        .details .rating .stars {
            display: inline-block
        }

        .details .sizes .size {
            margin-right: 10px
        }

        .details .sizes .size:first-of-type {
            margin-left: 40px
        }

        .details .colors .color {
            display: inline-block;
            vertical-align: middle;
            margin-right: 10px;
            height: 2em;
            width: 2em;
            border-radius: 2px
        }

        .details .colors .color:first-of-type {
            margin-left: 20px
        }

        .details .colors .not-available {
            text-align: center;
            line-height: 2em
        }

        .details .colors .not-available:before {
            font-family: Material-Design-Iconic-Font;
            content: "\f136";
            color: #fff
        }

        @media screen and (max-width: 996px) {
            .preview {
                margin-bottom: 20px
            }
        }

        @-webkit-keyframes opacity {
            0% {
                opacity: 0;
                -webkit-transform: scale(3);
                transform: scale(3)
            }
            100% {
                opacity: 1;
                -webkit-transform: scale(1);
                transform: scale(1)
            }
        }

        @keyframes opacity {
            0% {
                opacity: 0;
                -webkit-transform: scale(3);
                transform: scale(3)
            }
            100% {
                opacity: 1;
                -webkit-transform: scale(1);
                transform: scale(1)
            }
        }

        .cart-page .cart-table tr th:last-child {
            width: 145px
        }

        .cart-table .quantity-grp {
            width: 120px
        }

        .cart-table .quantity-grp .input-group {
            margin-bottom: 0
        }

        .cart-table .quantity-grp .input-group-addon {
            padding: 0 !important;
            text-align: center;
            background-color: #1ab1e3
        }

        .cart-table .quantity-grp .input-group-addon a {
            display: block;
            padding: 8px 10px 10px;
            color: #fff
        }

        .cart-table .quantity-grp .input-group-addon a i {
            vertical-align: middle
        }

        .cart-table .quantity-grp .form-control {
            background-color: #fff
        }

        .cart-table .quantity-grp .form-control + .input-group-addon {
            background-color: #1ab1e3
        }

        .ec-checkout .wizard .content .form-group .btn-group.bootstrap-select.form-control {
            padding: 0
        }

        .ec-checkout .wizard .content .form-group .btn-group.bootstrap-select.form-control .btn-round.btn-simple {
            padding-top: 12px;
            padding-bottom: 12px
        }

        .ec-checkout .wizard .content ul.card-type {
            font-size: 0
        }

        .ec-checkout .wizard .content ul.card-type li {
            display: inline-block;
            margin-right: 10px
        }

        .card {
            background: #fff;
            margin-bottom: 30px;
            transition: .5s;
            border: 0;
            border-radius: .55rem;
            position: relative;
            width: 100%;
            box-shadow: 0 1px 2px 0 rgba(0, 0, 0, 0.1);
        }

        .card .body {
            font-size: 14px;
            color: #424242;
            padding: 20px;
            font-weight: 400;
        }

        /*detail*/

    </style>

</head>
<body>
<jsp:include page="../banHangOnlline/nav.jsp"></jsp:include>
<div id="carouselExampleControls" class="carousel slide" data-bs-ride="carousel">
    <div class="carousel-inner">
        <div class="carousel-item active">
            <img src="/anh/nen1.jpg"
                 style="width:1540px;height:590px">

        </div>
        <div class="carousel-item">
            <img src="/anh/slider_2.jpg"
                 style="width:1540px;height:590px">
        </div>
        <div class="carousel-item">
            <img src="/anh/nen3.png"
                 style="width:1540px;height:590px">
        </div>
    </div>

    <button class="carousel-control-prev" type="button" data-bs-target="#carouselExampleControls" data-bs-slide="prev">
        <span class="carousel-control-prev-icon" aria-hidden="true"></span>
        <span class="visually-hidden">Previous</span>
    </button>
    <button class="carousel-control-next" type="button" data-bs-target="#carouselExampleControls" data-bs-slide="next">
        <span class="carousel-control-next-icon" aria-hidden="true"></span>
        <span class="visually-hidden">Next</span>
    </button>
</div>
<nav class="navbar navbar-expand-lg navbar-light" style="height:40px;background-color: whitesmoke">
</nav>
<div style="margin-left: 700px;margin-top: 40px">
    <h4><b>SẢN PHẨM BÁN CHẠY</b></h4>
</div>
<div class="container">
    <div class="row clearfix">
        <c:forEach items="${list.getContent()}" var="anh">
            <div class="col-lg-3 col-md-4 col-sm-12">
                <div class="card product_item">
                    <div class="body">
                        <div class="cp_img" style="height:300px">
                            <a  href="/sports-clothing/chi-tiet?idSP=${anh.sanPham.id}"><img src="/anh/anh13.png" style="height:250px;margin-left: 14px" class="img-fluid"></a>
                            <div class="hover">
                                <a href="javascript:void(0);" class="btn btn-primary btn-sm waves-effect"><i
                                        class="zmdi zmdi-shopping-cart"></i></a>
                            </div>
                        </div>
                        <div class="product_details">
                            <h5 style="height: 40px;"><a style="text-decoration:none;"
                                    href="/sports-clothing/chi-tiet?idSP=${anh.sanPham.id}">${anh.sanPham.ten}</a></h5>
                            <ul class="product_price list-unstyled">
                                <li class="old_price"><font color="red">${anh.sanPham.getGiaBan1()}đ</font></li>
                            </ul>
                        </div>
                    </div>
                </div>
            </div>
        </c:forEach>
    </div>
</div>
<%--                phân trang --%>
<div class="container-fluid mt-5">
    <nav aria-label="Page navigation example">
        <ul class="pagination justify-content-center">
            <li class="page-item ${pageNo<=1?"disabled":""}"><a class="page-link"
                                                                href="/sports-clothing/trang-chu?page=${pageNo-1}">Previous</a>
            </li>
            <c:forEach begin="1" end="${list.getTotalPages()}" var="i">
                <li class="page-item"><a class="page-link ${i == pageNo ? 'active ' : ''}"
                                         href="/sports-clothing/trang-chu?page=${i}">${i}</a></li>
            </c:forEach>
            <li class="page-item ${pageNo>=list.getTotalPages()?"disabled":""}"><a class="page-link"
                                                                                   href="/sports-clothing/trang-chu?page=${pageNo+1}">Next</a>
            </li>
        </ul>
    </nav>
</div>
</body>
</html>