<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css"
          rel="stylesheet" integrity="sha384-9ndCyUaIbzAi2FUVXJi0CjmCapSmO7SnpJef0486qhLnuZ2cdeRhO02iuK6FUUVM"
          crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"
            integrity="sha384-geWF76RCwLtnZ8qwWowPQNguL3RmwHVBC9FhGdlKrxdiJJigb/j/68SIy3Te4Bkz"
            crossorigin="anonymous"></script>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/sweetalert2@11.1.5/dist/sweetalert2.min.css">
    <script src="https://cdn.jsdelivr.net/npm/sweetalert2@11.1.5/dist/sweetalert2.min.js"></script>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.1/font/bootstrap-icons.css">
</head>
<body>
<%--navbar--%>
<jsp:include page="../../sidebar/navbar.jsp"/>
<%--sidebar--%>
<div class="container-fluid">
    <div class="row flex-nowrap">
        <jsp:include page="../../sidebar/sidebar.jsp"/>
        <div class="col py-3">
            <div class="row">
                 <jsp:include page="filter-hoa-don.jsp"></jsp:include>
            </div>
            <div class="row">
                <!-- Table with stripped rows -->
                <table class="table">
                    <thead>
                    <tr>
                        <th scope="col">STT</th>
                        <th scope="col">Mã hóa đơn</th>
                        <th scope="col">Loại hóa đơn</th>
                        <th scope="col">Ngày tạo</th>
                        <th scope="col">Tên Khách hàng</th>
                        <th scope="col">Tổng tiền</th>
                        <th scope="col">Tình trạng</th>
                        <th scope="col">Thao tác</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${hoaDons.content}" var="hd" varStatus="i">
                        <tr>
                            <td>${i.index+ (khuyenMais.number + 1 != 1 ? ((khuyenMais.number + 1) * khuyenMais.size) -(khuyenMais.size - 1) : khuyenMais.number + 1)}</td>
                            <td>${hd.ma}</td>
                            <td>${hd.loaiHoaDon==false?'Đơn tại quầy':'Đơn giao'}</td>
                            <td><fmt:formatDate value="${hd.ngayTao}" pattern="dd/MM/yyyy HH:mm:ss"/></td>
                                <c:choose>
                                    <c:when test="${hd.khachHang == null}">
                                        <td>
                                            <button class="btn btn-secondary rounded-pill">khách lẻ</button>
                                        </td>
                                    </c:when>
                                    <c:otherwise>
                                        <td>
                                            ${hd.khachHang.hoTen}
                                        </td>
                                    </c:otherwise>
                                </c:choose>
                            <td style="color:red;">
                                 <fmt:formatNumber pattern="#,###" value="${hd.tongTien()}"/> đ
                            </td>
                            <td>
                                <c:if test="${hd.trangThai == 4}">
                                    <button class="btn btn-primary rounded-pill">Đã hoàn thành</button>
                                </c:if>

                                <c:if test="${hd.trangThai == 1}">
                                    <button class="btn btn-warning rounded-pill">Chờ xác nhận</button>
                                </c:if>

                                <c:if test="${hd.trangThai == 2}">
                                    <button class="btn btn-success rounded-pill">Đã xác nhận</button>
                                </c:if>

                                <c:if test="${hd.trangThai == 3}">
                                    <button class="btn btn-primary rounded-pill">Đang giao hàng</button>
                                </c:if>

                                <c:if test="${hd.trangThai == 5}">
                                    <button class="btn btn-danger rounded-pill">Đã huỷ</button>
                                </c:if>

                                <c:if test="${hd.trangThai == 6}">
                                    <button class="btn btn-success rounded-pill">Giao hàng thành công</button>
                                </c:if>
                            </td>
                            <td>
                                <a href="/admin/hoa-don/chi-tiet-hoa-dons/${hd.id}" class="btn btn-warning"><i class="bi bi-eye-fill"></i></a>
                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
            <%--  phân trang --%>
            <div class="container-fluid mt-5">
                <nav aria-label="Page navigation example">
                    <ul class="pagination justify-content-center">
                        <li class="page-item ${(hoaDons.number+1)<=1?"disabled":""}"><a class="page-link"
                                                                                        href="${url}${(hoaDons.number + 1) - 1}"><</a>
                        </li>
                        <c:forEach begin="1" end="${hoaDons.getTotalPages()}" var="i">
                            <li class="page-item"><a
                                    class="page-link ${i == (hoaDons.number + 1) ? 'active ' : ''}"
                                    href="${url}${i}">${i}</a></li>
                        </c:forEach>
                        <li class="page-item ${hoaDons.number + 1 >= hoaDons.getTotalPages() ? "disabled" : ""}">
                            <a
                                    class="page-link"
                                    href="${url}${(hoaDons.number+1) + 1}">></a>
                        </li>
                    </ul>
                </nav>
            </div>
        </div>
    </div>
</div>
</body>
<script>
    function myFunction() {
        document.getElementById("floatingName").readOnly = false;
        document.getElementById("disabled").disabled = false
    }

    function xacNhan(data) {
        var ghiChu = document.getElementById('ghiChu').value;
        window.location.href = "/admin/hoa-don-onl/xac-nhan/" + data.idhd + "?ghiChu=" + ghiChu;
    }

    function huyDonHang(data) {
        var ghiChu = document.getElementById('ghiChu2').value;
        window.location.href = "/admin/hoa-don-onl/huy-don/" + data.idhd + "?ghiChu=" + ghiChu;
    }
</script>
</html>