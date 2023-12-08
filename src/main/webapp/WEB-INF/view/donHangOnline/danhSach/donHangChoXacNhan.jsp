<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html lang="zxx">
<head>
    <link rel="shortcut icon" href="#">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css"
          rel="stylesheet" integrity="sha384-9ndCyUaIbzAi2FUVXJi0CjmCapSmO7SnpJef0486qhLnuZ2cdeRhO02iuK6FUUVM"
          crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"
            integrity="sha384-geWF76RCwLtnZ8qwWowPQNguL3RmwHVBC9FhGdlKrxdiJJigb/j/68SIy3Te4Bkz"
            crossorigin="anonymous"></script>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.1/font/bootstrap-icons.css">
    <link rel="stylesheet" href="../../../../css/banHangTaiQuay/hoaDon/chiTietHoaDon.css">
    <link rel="stylesheet" href="../../../../css/ban-hang-tai-quay.css">

    <script src="
              https://cdn.jsdelivr.net/npm/sweetalert2@11.10.0/dist/sweetalert2.all.min.js
              "></script>
    <link href="
          https://cdn.jsdelivr.net/npm/sweetalert2@11.10.0/dist/sweetalert2.min.css
         " rel="stylesheet">

    <!-- Css Styles -->
    <link rel="stylesheet" href="../../../../css/bootstrap.min.css" type="text/css">
    <link rel="stylesheet" href="../../../../css/font-awesome.min.css" type="text/css">
    <link rel="stylesheet" href="../../../../css/elegant-icons.css" type="text/css">
    <link rel="stylesheet" href="../../../../css/magnific-popup.css" type="text/css">
    <link rel="stylesheet" href="../../../../css/nice-select.css" type="text/css">
    <link rel="stylesheet" href="../../../../css/owl.carousel.min.css" type="text/css">
    <link rel="stylesheet" href="../../../../css/slicknav.min.css" type="text/css">
    <link rel="stylesheet" href="../../../../css/style.css" type="text/css">

    <link rel="stylesheet" href="../../../../css/banHangOnline/hoaDon/hoaDon.css" type="text/css">

</head>
<body>
<!-- Header Section Begin -->
<jsp:include page="../../layout/header.jsp"/>

<!-- Header Section End -->
<section class="section">
    <div class="container">
        <div>
            <h3 class="h3-title">Đơn hàng của tôi</h3>
        </div>
        <div>
            <nav class="primary-navigation">
                <ul>
                    <li><a href="/cuaToi/donHangAll">Tất cả đơn hàng</a></li>|
                    <li class="active"><a href="/cuaToi/donHangChoXacNhan">Chờ xác nhận</a></li>|
                    <li><a href="/cuaToi/donHangChoGiaoHang">Đã xác nhận</a></li>|
                    <li><a href="/cuaToi/donHangDangGiao">Đang giao</a></li>|
                    <li><a href="/cuaToi/donHangDaNhan">Đã nhận</a></li>|
                    <li><a href="/cuaToi/donHangDaThanhToan">Đã thanh toán</a></li>|
                    <li><a href="/cuaToi/donHangDaHuy">Đã hủy</a></li>
                </ul>
            </nav>
        </div>
        <div class="row">
            <table class="table">
                <thead class="thead">
                <tr>
                    <th scope="col">Tổng sản phẩm</th>
                    <th scope="col" style="padding-right: 70px">Tổng tiền</th>
                    <th scope="col" style="padding-right: 60px">Trạng thái</th>
                    <th scope="col" style="padding-right: 50px">Thao tác</th>
                </tr>
                </thead>
            </table>
            <c:forEach items="${listHd.content}" var="hd" >
                <div class="span">
                    <fmt:formatDate value="${hd[2]}" pattern="dd-MM-yyyy HH:mm:ss"/> | Mã đơn hàng: ${hd[1]}
                </div>
                <table class="table">
                    <tbody>
                    <tr>
                        <td class="td-1">
                            <div>
                                <h6>${hd[4]} sản phẩm</h6>
                            </div>
                        </td>
                        <td style="width: 300px">
                            <p>
                                <b style="color: red">
                                    <c:choose>
                                        <c:when test="${hd[6] != null}">
                                            <fmt:formatNumber pattern="#,###" value="${hd[3] + hd[6]}"/>
                                        </c:when>
                                        <c:otherwise>
                                            <fmt:formatNumber pattern="#,###" value="${hd[3]}"/>
                                        </c:otherwise>

                                    </c:choose>
                                    <c:if test="${hd[3]==null}">
                                        <span>0</span>
                                    </c:if>
                                    <span>đ</span>
                                </b>
                            </p>
                        </td>
                        <td>
                            <div style="margin-top: 30px">
                                <c:if test="${hd[5] == 4}">
                                    Đã thanh toán
                                </c:if>

                                <c:if test="${hd[5] == 1}">
                                    Chờ xác nhận
                                </c:if>

                                <c:if test="${hd[5] == 2}">
                                    Đã xác nhận
                                </c:if>

                                <c:if test="${hd[5] == 3}">
                                    Đang giao hàng
                                </c:if>

                                <c:if test="${hd[5] == 5}">
                                    Đã huỷ
                                </c:if>

                                <c:if test="${hd[5] == 6}">
                                    Đã nhận
                                </c:if>

                                <c:if test="${hd[5] == 9}">
                                    Hoàn trả
                                </c:if>
                            </div>
                            <div style="margin-top: 10px">
                                <a class="text-primary" href="/cuaToi/chiTietDonHang/${hd[0]}">Chi tiết đơn hàng</a>
                            </div>
                        </td>
                        <td>
                            <c:if test="${hd[5]==1}">
                                <div>
                                    <button class="xacNhanHuyHoaDon" id="xacNhanHuyHoaDon${hd[0]}" style="border: none; background-color: white">
                                        <a class="btn btn-danger text-light">Huỷ</a>
                                    </button>
                                </div>
                            </c:if>
                            <div>
                                <button class="xacNhanHoaDon" id="xacNhanHoaDon" style="visibility: hidden;border: none; background-color: white">
                                    <a class="btn btn-success text-light">Đã nhận</a>
                                </button>
                            </div>
                        </td>
                    </tr>
                    </tbody>
                </table>
                <%--hủy đơn--%>
                <div id="modalXacNhanHuyHoaDon${hd[0]}" class="modal">
                    <div class="modal-content-1" style="width: 50%">
                        <h5 class="mb-3"><b>Xác nhận huỷ hoá đơn</b></h5>
                        <span class="close" id="close_modalXacNhanHuyHoaDon${hd[0]}">&times;</span>
                        <div class="row">
                            <form action="/cuaToi/update-trang-thai?trangThai=5&idHD=${hd[0]}" method="post">
                                <label>Ghi chú</label>
                                <textarea name="ghiChuXacNhan" class="form-control"></textarea>
                                <br>
                                <button class="btn btn-primary">Xác nhận</button>
                            </form>
                        </div>
                    </div>
                </div>
            </c:forEach>
            <%--end--%>
            <%--                  phân trang--%>
            <div class="container-fluid mt-5">
                <nav aria-label="Page navigation example">
                    <ul class="pagination justify-content-center">
                        <li class="page-item ${listHd.number + 1<=1?"disabled":""}"><a class="page-link"
                                                                                       href="${url}${(listHd.number + 1) - 1}"><</a>
                        </li>
                        <c:forEach begin="1" end="${listHd.getTotalPages()}" var="i">
                            <li class="page-item"><a class="page-link ${i == listHd.number + 1 ? 'active ' : ''}"
                                                     href="${url}${i}">${i}</a></li>
                        </c:forEach>
                        <li class="page-item ${listHd.number + 1 >= listHd.getTotalPages() ? "disabled":  ""}">
                            <a class="page-link"
                               href="${url}${(listHd.number + 1) + 1}">></a>
                        </li>
                    </ul>
                </nav>
                <br><br>
            </div>
        </div>
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
<script>
    document.addEventListener('DOMContentLoaded', function() {

        var xacNhanHuyHoaDons = document.querySelectorAll('.xacNhanHuyHoaDon');

        xacNhanHuyHoaDons.forEach(function (xacNhanHuyHoaDon){
            xacNhanHuyHoaDon.addEventListener('click', function (){
                var idHD = this.id.replace('xacNhanHuyHoaDon', '');
                let modalXacNhanHuyHoaDon = document.getElementById("modalXacNhanHuyHoaDon" + idHD);
                let close_modalXacNhanHuyHoaDon = document.getElementById("close_modalXacNhanHuyHoaDon" + idHD);

                if (document.getElementById("xacNhanHuyHoaDon" + idHD) != null) {
                    document.getElementById("xacNhanHuyHoaDon" + idHD).onclick = function () {
                        modalXacNhanHuyHoaDon.style.display = "block"
                    }
                }
                if (close_modalXacNhanHuyHoaDon != null) {
                    close_modalXacNhanHuyHoaDon.onclick = function () {
                        modalXacNhanHuyHoaDon.style.display = "none";
                    }
                }
            });
        });
    });

    if (${success != null}) {
        Swal.fire({
            title: "${success}",
            icon: "success"
        });
    }
    if (${error != null}) {
        Swal.fire({
            title: "${error}!",
            icon: "error"
        });
    }
</script>

</body>
</html>