<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html lang="zxx">
<head>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.5.0/font/bootstrap-icons.css" rel="stylesheet"/>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"
            integrity="sha384-geWF76RCwLtnZ8qwWowPQNguL3RmwHVBC9FhGdlKrxdiJJigb/j/68SIy3Te4Bkz"
            crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/jquery@3.6.3/dist/jquery.slim.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>

    <script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.8.2/angular.min.js"></script>
    <script src="https://kit.fontawesome.com/35a8b342cd.js" crossorigin="anonymous"></script>
    <!-- Google Font -->
    <link href="https://fonts.googleapis.com/css2?family=Nunito+Sans:wght@300;400;600;700;800;900&display=swap"
          rel="stylesheet">

    <!-- UniIcon CDN Link  -->
    <link rel="stylesheet" href="https://unicons.iconscout.com/release/v4.0.0/css/line.css">

    <!-- Css Styles -->
    <link rel="stylesheet" href="../../../../css/bootstrap.min.css" type="text/css">
    <link rel="stylesheet" href="../../../../css/font-awesome.min.css" type="text/css">
    <link rel="stylesheet" href="../../../../css/elegant-icons.css" type="text/css">
    <link rel="stylesheet" href="../../../../css/magnific-popup.css" type="text/css">
    <link rel="stylesheet" href="../../../../css/nice-select.css" type="text/css">
    <link rel="stylesheet" href="../../../../css/owl.carousel.min.css" type="text/css">
    <link rel="stylesheet" href="../../../../css/slicknav.min.css" type="text/css">
    <link rel="stylesheet" href="../../../../css/style.css" type="text/css">

    <link rel="stylesheet" href="../../../../css/banHangOnline/hoaDon/chiTietDonHang.css" type="text/css">

</head>
<body>
<!-- Header Section Begin -->
<jsp:include page="../../layout/header.jsp"/>

<!-- Header Section End -->
<section class="section">
    <div class="container">
        <div>
            <h3 class="h3-title">Chi tiết đơn hàng</h3>
        </div>
        <div class="row">
            <div class="span-1">
                <span><a href="javascript:history.back()">< Quay lại</a></span>
                <span class="maDH">Mã đơn hàng: ${hd.ma} | ${hd.trangThai==4?'Đã thanh toán':(hd.trangThai==3)?'Đang giao hàng':(hd.trangThai==0)?'Đang chờ':(hd.trangThai==1)?'Chờ xác nhận':(hd.trangThai==2)?'Chờ giao hàng':(hd.trangThai==6)?'Đã nhận hàng':(hd.trangThai==5)?'Hủy đơn hàng':(hd.trangThai==9)?'Hoàn trả':''}</span>
            </div>
            <table class="table">
                <thead class="thead">
                <tr>
                    <th scope="col">Sản phẩm</th>
                    <th scope="col">Đơn giá</th>
                    <th scope="col">Số lượng</th>
                    <th scope="col">Thành tiền</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${listHDCT}" var="hdct">
                    <tr>
                        <td class="td-1">
                            <div style="display: flex; align-items: center;">
                                <img src="/image/${hdct.chiTietSanPham.sanPham.img}" alt=""
                                     style="margin-right: 10px;" width="80px" height="80px">
                                <div>
                                    <h5>
                                        <span style="font-weight: bold" class="TenSP">${hdct.chiTietSanPham.sanPham.ten}</span>
                                    </h5>
                                    <div class="ThongTinSP">
                                        <span>${hdct.chiTietSanPham.mauSac.ten}</span>
                                        <span>/</span>
                                        <span>${hdct.chiTietSanPham.size.ten}</span>
                                    </div>
                                </div>
                            </div>
                        </td>
                        <td style="width: 210px">
                            <p><b style="color: red"><fmt:formatNumber pattern="#,###" value="${hdct.chiTietSanPham.sanPham.giaBan}"/> đ</b></p>
                        </td>
                        <td class="td-2">
                            <p><b>${hdct.soLuong}</b></p>
                        </td>
                        <td>
                            <p><b style="color: red"><fmt:formatNumber pattern="#,###" value="${hdct.soLuong * hdct.chiTietSanPham.sanPham.giaBan}"/> đ</b></p>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
            <%--end--%>

            <div class="container">
                <div class="row" style="padding-top: 10px;">
                    <div class="trai">
                        <h5 style="padding-bottom: 15px"><b>Thông tin giao hàng</b></h5>
                        <p><b>Họ tên:</b> ${hd.hoTen}</p>
                        <p><b>Số điện thoại:</b> ${hd.sdt}</p>
                        <p><b>Địa chỉ:</b> ${hd.diaChi}</p>
                    </div>
                    <div class="phai">
                        <h5 style="padding-bottom: 15px"><b>Phương thức thanh toán</b></h5>
                        <p>${hd.phuongThucThanhToan==1?'Tiền mặt':(hd.phuongThucThanhToan==2)?'Chuyển khoản':(hd.phuongThucThanhToan==3)?'Thanh toán khi nhận hàng':(hd.phuongThucThanhToan==4)?'Thanh toán qua ví VNPAY':''}</p>
                    </div>
                </div>
                <hr class="hr-1">
            </div>

            <%--tính tổng tiền--%>
            <div class="container">
                <div class="row">
                    <div class="left">
                        <p>Tiền hàng:</p>
                        <p>Phí vận chuyển:</p>
                        <p><b>Tổng cộng:</b></p>
                    </div>
                    <div class="right">
                        <p>
                            <fmt:formatNumber pattern="#,###" value="${hd.tongTienHang()}"/>
                            <c:if test="${hd.tongTienHang()==null}">
                                <span>0</span>
                            </c:if>
                            <span>đ</span>
                        </p>
                        <p>
                            <fmt:formatNumber pattern="#,###" value="${hd.phiVanChuyen}"/>
                            <c:if test="${hd.phiVanChuyen==null}">
                                <span>0</span>
                            </c:if>
                            <span>đ</span>
                        </p>
                        <p>
                            <b>
                                <fmt:formatNumber pattern="#,###" value="${hd.tongTien()}"/>
                                <c:if test="${hd.tongTien()==null}">
                                    <span>0</span>
                                </c:if>
                                <span>đ</span>
                            </b>
                        </p>
                    </div>
                </div><br>
            </div>
        </div

    </div>
</section>

<!-- Footer Section Begin -->
<jsp:include page="../../layout/footer.jsp"/>
<!-- Search End -->
<!-- Js Plugins -->
<script src="../../../../js/jquery-3.3.1.min.js"></script>
<script src="../../../../js/bootstrap.min.js"></script>
<script src="../../../../js/jquery.nice-select.min.js"></script>
<script src="../../../../js/jquery.nicescroll.min.js"></script>
<script src="../../../../js/jquery.magnific-popup.min.js"></script>
<script src="../../../../js/jquery.countdown.min.js"></script>
<script src="../../../../js/jquery.slicknav.js"></script>
<script src="../../../../js/mixitup.min.js"></script>
<script src="../../../../js/owl.carousel.min.js"></script>
<script src="../../../../js/main.js"></script>
</body>
</html>