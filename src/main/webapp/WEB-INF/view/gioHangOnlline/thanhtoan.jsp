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

    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
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
    <link rel="stylesheet" href="../../../css/banHangOnline/thanhToan/thanhToan.css" type="text/css">

    <script src="../../../js/banHangOnlline/thanhToan/thanhtoan.js"></script>
</head>
<body>
<div class="container-fluid">
    <div class="row">
        <%--        bên trái--%>
        <div class="left-panel">
            <div class="container">
                <div class="pagetitle">
                    <h4><b>Sports Clothing</b></h4>
                    <nav class="breadcrumb" style="background-color: white">
                        <li class="breadcrumb-item"><a class="hover" href="/gio-hang-onl">Giỏ hàng</a></li>
                        <li class="breadcrumb-item">Thông tin giao hàng</li>
                        <li class="breadcrumb-item" onclick="alert('hello')">Địa chỉ</li>
                    </nav>
                </div><!-- End Page Title -->
               <form:form action="/checkouts/order" method="post" modelAttribute="checkoutRequest">
                    <div class="mb-3">
                        <h5>Thông tin giao hàng</h5>
                    </div>
                    <div class="row">
                        <div class="col-12">
                            <div class="form-floating mb-3">
                                <input type="text" class="form-control" id="hoTen" name="hoTen" placeholder="Họ và tên" value="${checkoutRequest.hoTen}">
                                <label>Họ và tên</label>
                                <form:errors path="hoTen" cssStyle="color: red"/>
                            </div>
                        </div>
                        <div class="col-12">
                            <div class="form-floating mb-3">
                                <input type="text" class="form-control" id="soDienThoai" name="soDienThoai" placeholder="Số điện thoại" value="${checkoutRequest.soDienThoai}">
                                <label>Số điện thoại</label>
                                <form:errors path="soDienThoai" cssStyle="color: red"/>
                            </div>
                        </div>
                        <div class="col-12">
                            <div class="form-floating mb-3">
                                <input type="text" class="form-control" id="diaChi" name="diaChi" placeholder="Địa chỉ" value="${checkoutRequest.diaChi}">
                                <label>Địa chỉ</label>
                                <form:errors path="diaChi" cssStyle="color: red"/>
                            </div>
                        </div>
                        <div class="col-4">
                            <div class="form-floating">
                                <select id="province" class="form-select tinhThanhPho" id="thanhPho" name="thanhPho">
                                    <option value="">Chọn tỉnh / thành</option>
                                </select>
                                <label>Tỉnh / thành</label>
                                <form:errors path="thanhPho" cssStyle="color: red"/>
                            </div>
                        </div>
                        <div class="col-4">
                            <div class="form-floating">
                                <select id="district" disabled class="form-select chonQuanHuyen" aria-invalid="quanHuyen" name="quanHuyen">
                                    <option value="">Chọn quận / huyện</option>
                                </select>
                                <label>Quận / huyện</label>
                                <form:errors path="quanHuyen" cssStyle="color: red"/>
                            </div>
                        </div>
                        <div class="col-4">
                            <div class="form-floating">
                                <select id="ward" disabled class="form-select chonPhuongXa" id="phuongXa" name="phuongXa">
                                    <option value="">Chọn phường / xã</option>
                                </select>
                                <label>Phường / xã</label>
                                <form:errors path="phuongXa" cssStyle="color: red"/>
                            </div>
                        </div>
                        <div class="col-12" style="margin-top: 13px">
                            <div class="form-floating mb-3">
                                <textarea type="text" class="form-control" id="ghiChu" name="ghiChu" value="${checkoutRequest.ghiChu}"></textarea>
                                <label>Ghi chú</label>
                            </div>
                        </div>
                    </div>
                    <div class="mt-5">
                        <h5>Phương thức thanh toán</h5>
                    </div>
                    <div class="mt-3 card-radio">
                        <div class="form-check radio">
                            <input class="form-check-input" type="radio" name="phuongThucThanhToan" id="flexRadioDefault2"
                                   checked value="3">
                            <label class="form-check-label" for="flexRadioDefault2">
                                Thanh toán khi giao hàng (COD).
                            </label>
                        </div>
                    </div>
                    <div class="row mt-5">
                        <div class="col-6">
                            <a class="hover" href="/gio-hang-onl">Giỏ hàng</a>
                        </div>
                        <div class="col-6" style="text-align: right">
                            <button class="btn btn-info p-3">Hoàn tất đơn hàng</button>
                        </div>
                    </div>
                </form:form>
            </div>
        </div>

        <%--        bên phải--%>
        <div class="right-panel">
            <div class="container">
                <div class="row">
                    <div class="col-12 table-wrapper">
                        <table>
                            <thead>
                            <tr>
                                <th style="width: 64%">Sản phẩm</th>
                                <th style="width: 17%">Số lượng</th>
                                <th style="width: 19%">Thành tiền</th>
                            </tr>
                            </thead>
                            <tbody>
                            <c:forEach items="${sanPhamTrongGioHang}" var="items">
                                <tr class="product-row">
                                    <td>
                                        <div style="display: flex; align-items: center;">
                                            <img src="/image/${items.getImg()}" alt=""
                                                 style="margin-right: 10px;width: 80px; height: 85px; border:1px solid #ccc;border-radius: 10px">
                                            <div>
                                                <h3>
                                                    <strong>
                                                        <span style="text-transform: none">${items.getTenSanPham()}</span>
                                                    </strong>
                                                </h3>
                                                <div class="thongTinSP">
                                                    <span>${items.getMauSac()}</span>
                                                    <span>/</span>
                                                    <span>${items.getKichCo()}</span>
                                                    <br>
                                                    <c:choose>
                                                        <c:when test="${items.getGiaBanSanPham() != null && items.trangThaiKMCT == 1 && items.trangThaiKM ==1}">
                                                    <span> <fmt:formatNumber pattern="#,###"
                                                                             value="${items.getDonGiaSauKhiGiam()}"></fmt:formatNumber>đ</span>

                                                        </c:when>
                                                        <c:otherwise>
                                                     <span> <fmt:formatNumber pattern="#,###"
                                                                              value="${items.getGiaBanSanPham()}"></fmt:formatNumber>đ</span>

                                                        </c:otherwise>
                                                    </c:choose>
                                                </div>
                                            </div>
                                        </div>
                                    </td>
                                    <td style="text-align: center">${items.getSoLuong()}</td>
                                    <td><span style="text-align: right"><fmt:formatNumber pattern="#,###"
                                                                                          value="${items.getThanhTien()}"></fmt:formatNumber> đ</span>
                                    </td>
                                </tr>
                            </c:forEach>

                            </tbody>
                        </table>
                        <hr>
                    </div>
                    <div class="col-12">
                        <div class="row">
                            <label class="form-label ml">Tạm tính:</label>
                            <label class="form-label mr" id="tamTinh"><fmt:formatNumber pattern="#,###"
                                                                           value="${tongTien}"></fmt:formatNumber>
                                đ</label>
                        </div>
                    </div>

<%--                    <div class="col-12">--%>
<%--                        <div class="row">--%>
<%--                            <label for="shippingFee" class="form-label ml">Phí vận chuyển:</label>--%>
<%--                            <label type="text" id="shippingFee" readonly class="form-label mr">0 đ</label>--%>
<%--                        </div>--%>
<%--                        <hr>--%>
<%--                    </div>--%>

                    <div class="col-12">
                        <div class="row">
                            <h6 class="form-label ml">Tổng cộng:</h6>
                            <h4 class="form-label mr" id="tongTien"><fmt:formatNumber pattern="#,###"
                                                                        value="${tongTien}"></fmt:formatNumber> đ</h4>
                        </div>
                    </div>
                </div>
                <br>
            </div>
        </div>
    </div>
</div>
</body>
</html>