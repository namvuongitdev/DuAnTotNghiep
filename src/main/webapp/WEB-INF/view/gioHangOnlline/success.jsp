<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 11/10/2023
  Time: 3:47 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet"
      integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM"
        crossorigin="anonymous"></script>

<body>
<section class="h-100 gradient-custom">
    <div class="container py-5 h-100">
        <div class="row d-flex justify-content-center align-items-center h-100">
            <div class="col-lg-10 col-xl-8">
                <div class="card" style="border-radius: 10px;">
                    <div class="card-header px-4 py-5">
                        <h5 class="text-muted mb-0">Đặt hàng thành công, <span
                                style="color: #a8729a;">Mã đơn hàng: ${hoaDon.ma}</span>
                        </h5>
                    </div>
                    <c:forEach items="${hoaDon.hoaDonChiTiets}" var="item">
                    <div class="card-body p-4">
                        <div class="card shadow-0 border mb-4">
                            <div class="card-body">
                                <div class="row">
                                    <div class="col-md-2">
                                        <img src="/image/${item.chiTietSanPham.sanPham.img}"
                                             class="img-fluid" alt="Phone">
                                    </div>
                                    <div class="col-md-2 text-center d-flex justify-content-center align-items-center">
                                        <p class="text-muted mb-0">${item.chiTietSanPham.sanPham.ten}</p>
                                    </div>
                                    <div class="col-md-2 text-center d-flex justify-content-center align-items-center">
                                        <p class="text-muted mb-0 small">Màu sắc: ${item.chiTietSanPham.mauSac.ten}</p>
                                    </div>
                                    <div class="col-md-2 text-center d-flex justify-content-center align-items-center">
                                        <p class="text-muted mb-0 small">Size: ${item.chiTietSanPham.size.ten}</p>
                                    </div>
                                    <div class="col-md-2 text-center d-flex justify-content-center align-items-center">
                                        <p class="text-muted mb-0 small">Số lượng: ${item.soLuong}</p>
                                    </div>
                                    <div class="col-md-2 text-center d-flex justify-content-center align-items-center">
                                        <p class="text-muted mb-0 small"><fmt:formatNumber pattern="#,###" value="${item.soLuong.intValue() * item.donGia}"></fmt:formatNumber></p>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </c:forEach>

                        <div class="card shadow-0 border mb-4">
                            <div class="col-sm-6" style="margin: 20px 20px 20px 20px">
                                <h4 style="color: #95999c">Thông tin giao hàng</h4>
                                <div>
                                    <p class="text-muted mb-0 big">${hoaDon.hoTen}</p>
                                    <p class="text-muted mb-0 big">${hoaDon.sdt}</p>
                                    <p class="text-muted mb-0 big">${hoaDon.diaChi}</p>
                                </div>
                                <div style="margin-top: 15px">
                                    <h5 style="color: #95999c">Thông tin giao hàng</h5>
                                    <p class="text-muted mb-0 big">${hoaDon.phuongThucThanhToan == 3 ? "Thanh toán khi nhận hàng (COD)" : ""}</p>
                                </div>
                                <div style="margin-top: 15px">
                                    <h5 style="color: #95999c">Thông tin thanh toán</h5>
                                    <p class="text-muted mb-0 big">Tạm tính : <fmt:formatNumber pattern="#,###" value="${hoaDon.tongTien}"></fmt:formatNumber></p>
                                </div>
                            </div>

                        </div>
                        <div class="card-footer border-0 px-4 py-5"
                             >
                           <a href="/index/home" class="btn btn-secondary">Tiếp tục mua hàng</a>
                        </div>
                    </div>
                </div>
            </div>
        </div>
</section>
</body>
</html>
