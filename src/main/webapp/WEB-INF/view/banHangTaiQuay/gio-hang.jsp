<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <script type="text/javascript"
            src="https://cdnjs.cloudflare.com/ajax/libs/webrtc-adapter/3.3.3/adapter.min.js"></script>
    <script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/vue/2.1.10/vue.min.js"></script>
    <script type="text/javascript" src="https://rawgit.com/schmich/instascan-builds/master/instascan.min.js"></script>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css"
          rel="stylesheet" integrity="sha384-9ndCyUaIbzAi2FUVXJi0CjmCapSmO7SnpJef0486qhLnuZ2cdeRhO02iuK6FUUVM"
          crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"
            integrity="sha384-geWF76RCwLtnZ8qwWowPQNguL3RmwHVBC9FhGdlKrxdiJJigb/j/68SIy3Te4Bkz"
            crossorigin="anonymous"></script>
    <link rel="stylesheet" href="/css/ban-hang-tai-quay.css">
    <svg xmlns="http://www.w3.org/2000/svg" style="display: none;">
        <symbol id="exclamation-triangle-fill" fill="currentColor" viewBox="0 0 16 16">
            <path d="M8.982 1.566a1.13 1.13 0 0 0-1.96 0L.165 13.233c-.457.778.091 1.767.98 1.767h13.713c.889 0 1.438-.99.98-1.767L8.982 1.566zM8 5c.535 0 .954.462.9.995l-.35 3.507a.552.552 0 0 1-1.1 0L7.1 5.995A.905.905 0 0 1 8 5zm.002 6a1 1 0 1 1 0 2 1 1 0 0 1 0-2z"/>
        </symbol>
    </svg>
</head>
<style>
    label {
        color: #03AA28;
    }

    .pagetitle nav .breadcrumb .breadcrumb-item a:hover {
        box-shadow: 0 3px;
    }
</style>
<body>
<%--navbar--%>
<jsp:include page="../sidebar/navbar.jsp"/>
<%--sidebar--%>
<div class="container-fluid" style="background-color: #dddddd">
    <div class="row flex-nowrap">
        <jsp:include page="../sidebar/sidebar.jsp"/>
        <div class="col py-3">
            <%--code giao diện chất liệu --%>
            <a style="float: right" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#extraLargeModal"
               name="1" onclick="getSanPham(this.name)">
                Thêm sản phẩm
            </a>
            <button style="float: right ; margin-right: 10px" id="myBtnQr" class="btn btn-primary">QR Code</button>

            <div class="container">
                <h4>Tạo hoá đơn</h4>
                <div class="pagetitle">
                    <nav>
                        <ol class="breadcrumb">
                            <li class="breadcrumb-item"><a
                                    href="/hoa-don/hien-thi-hoa-cho" <%= request.getRequestURI().contains("hoa-don-cho") ? "class=\"link-primary\"" : ""  %>
                                    style="text-decoration: none; color: black">Hoá đơn chờ</a></li>
                            <li class="breadcrumb-item"><a
                                    href="/hoa-don/detail?idHD=${hoaDon.id}" <%= request.getRequestURI().contains("gio-hang") ? "class=\"link-primary\"" : ""  %>
                                    style="text-decoration: none; color: black ; ">Tạo hoá đơn</a></li>
                        </ol>
                    </nav>
                </div><!-- End Page Title -->
                <br>
                <%--hiển thị giỏ hàng--%>
                <div class="row">
                    <c:if test="${error != null}">
                        <div class="alert alert-danger d-flex align-items-center" role="alert">
                            <svg class="bi flex-shrink-0 me-2" width="24" height="24" role="img" aria-label="Danger:">
                                <use xlink:href="#exclamation-triangle-fill"/>
                            </svg>
                            <div>

                                    ${error}
                            </div>
                        </div>
                    </c:if>
                    <div class="row" style="margin-bottom: 50px">
                        <div class="col l-3" style="background-color: white">
                            <table>
                                <thead>
                                <tr>
                                    <th scope="col">Sản phẩm</th>
                                    <th scope="col">Số lượng</th>
                                    <th scope="col">Thành tiền</th>
                                    <th scope="col">Thao tác</th>
                                </tr>
                                </thead>
                                <tbody>
                                <c:forEach items="${sanPhamGioHang}" var="sanPham">
                                    <tr>
                                        <td>
                                            <div class="row">
                                                <div class="col l-3">
                                                    <img src="/image/${sanPham.img}"
                                                         style="width: 150px; height: 150px">
                                                </div>
                                                <div class="col l-3">
                                                    <h5>${sanPham.tenSanPham}</h5>
                                                    <p style="color: #03AA28"><fmt:formatNumber pattern="#,###"
                                                                                                value="${sanPham.donGia}"></fmt:formatNumber></p>
                                                    <p>size : ${sanPham.kichCo}</p>
                                                    <p>màu sắc : ${sanPham.mauSac}</p>
                                                </div>
                                            </div>
                                        </td>
                                        <td><input onchange="updateSoLuong(this.value , {id:`${sanPham.id}`,
                                                soLuongHDCT:`${sanPham.soLuong}`,
                                                soLuongCTSP:`${sanPham.soLuongSanPham}`}
                                                )" type="number"
                                                   name="soLuong" value="${sanPham.soLuong}" min="1"
                                                   style="width: 25%"/>
                                        </td>
                                        <td id="thanhTien"><fmt:formatNumber pattern="#,###"
                                                                             value="${sanPham.soLuong * sanPham.donGia}"></fmt:formatNumber></td>
                                        <td><a onclick="if(confirm('Bạn có muốn xoá không') == true){
                                                window.location.href = '/hoa-don/delete?idHD=${idHD}&idHDCT=${sanPham.id}';
                                                }" class="btn btn-danger">Xoá khỏi giỏ</a></td>
                                    </tr>
                                </c:forEach>
                                <tr>
                                    <td style="color: #E43535">Tổng tiền :<fmt:formatNumber pattern="#,###"
                                                                                            value="${tongTien}"></fmt:formatNumber></td>
                                </tr>
                                </tbody>
                            </table>
                        </div>

                    </div>
                    <div class="row" style="margin-bottom: 20px">
                        <div class="col l-3" style="background-color: white">
                            <br>
                            <div class="row">
                                <h4>Thông tin khách hàng</h4>
                                <div class="col-2">
                                    <a type="button" class="btn btn-secondary"
                                       id="btnKhachHang"
                                    >
                                        Chọn tài khoản
                                    </a>
                                </div>
                                <c:if test="${khachHang != null}">
                                    <div class="col-2">
                                        <button class="btn btn-danger"
                                                onclick="window.location.href = '/hoa-don/detail?idHD=${hoaDon.id}'">Huỷ
                                            chọn
                                        </button>
                                    </div>
                                </c:if>
                            </div>
                            <hr>
                            <div class="col l-3" id="thongTinKhachHang" style="margin-bottom: 10px">
                                <c:if test="${khachHang != null}">
                                    <div><p>Họ tên khách hàng : ${khachHang.hoTen}</p></div>
                                    <div><p>Số điện thoại :${khachHang.sdt}</p></div>
                                </c:if>
                                <c:if test="${khachHang == null}">
                                    <div><p>Họ tên khách hàng : Khách bán lẻ</p></div>
                                </c:if>
                            </div>
                        </div>
                    </div>
                    <%-- thông tin thanh toán--%>
                    <form class="row" style="background-color: white"
                          action="/hoa-don/thanh-toan?idHD=${hoaDon.id}&idKhachHang=${khachHang.id}" method="post"
                          modelAtrribute="${request}">
                        <c:choose>
                            <%-- đặt hàng--%>
                            <c:when test="${hoaDon.loaiHoaDon}">
                              <jsp:include page="dat-hang.jsp"></jsp:include>
                            </c:when>
                            <%--                                tại quầy--%>
                            <c:otherwise>
                               <jsp:include page="tai-quay.jsp"></jsp:include>
                            </c:otherwise>
                        </c:choose>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>
</div>


<%--thêm khách hàng--%>
<div id="khachHangModal" class="modal">
    <!-- Modal content -->
    <div class="modal-content-1">
        <span class="close" id="close_khachHang">&times;</span>
        <div class="row">
            <div class="row" style="margin-bottom: 30px">
                <div class="col-sm-4">
                    <input class="form-control" type="text" name="search" id="search-khachHang"
                           placeholder="tìm kiếm tên ,sđt,tài khoản">
                </div>
                <div class="col-sm-2">
                    <button class="btn btn-primary" onclick="timKiemKhachHang()">Tìm kiếm</button>
                </div>
                <div class="col-sm-2">
                    <button class="btn btn-warning" id="lamMoi">Làm mới</button>
                </div>
            </div>
            <div class="row">
                <table>
                    <thead>
                    <tr>
                        <th scope="col">Họ tên</th>
                        <th scope="col">Email</th>
                        <th scope="col">SĐT</th>
                        <th scope="col">Tài khoản</th>
                        <th scope="col">Thao tác</th>
                    </tr>
                    </thead>
                    <tbody id="khachHang">

                    </tbody>
                </table>
            </div>
            <div class="container-fluid mt-5">
                <nav aria-label="Page navigation example">
                    <ul class="pagination justify-content-center" id="phanTrangKhachHang">
                    </ul>
                </nav>
            </div>
        </div>
    </div>
</div>
<%--thêm sản phẩm--%>
<div id="extraLargeModal" class="modal fade" tabindex="-1" role="dialog">
    <div class="modal-dialog modal-xl">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title">Thêm sản phẩm</h5>
            </div>
            <div class="modal-body">
                <div class="row">
                    <div class="col-sm-3">
                        <input class="form-control" type="text" name="search" id="search-input"
                               placeholder="tìm kiếm tên , mã sản phẩm">
                    </div>
                    <div class="col-sm-2">
                        <button class="btn btn-primary" onclick="timKiem()">Tìm kiếm</button>
                    </div>
                    <div class="col-sm-2">
                        <button class="btn btn-warning" id="clear">Làm mới</button>
                    </div>
                </div>
                <br>
                <div class="row">
                    <div class="row d-flex justify-content-center" style="margin-bottom: 10px">
                        <div class="col-sm-2">
                            <label for="size">Kích cỡ</label>
                            <select name="kichCo" id="size" class="form-select" onchange="filterSize(this.value)">
                                <option value="">Tất cả</option>
                                <c:forEach items="${listKichCo}" var="kichCo">
                                    <option value="${kichCo.id}">
                                            ${kichCo.ten}
                                    </option>
                                </c:forEach>
                            </select>
                        </div>
                        <div class="col-sm-2">
                            <label for="mauSac">Màu sắc</label>
                            <select name="mauSac" id="color" class="form-select" onchange="filterColor(this.value)">
                                <option value="">Tất cả</option>
                                <c:forEach items="${listMuaSac}" var="mauSac">
                                    <option value="${mauSac.id}">
                                            ${mauSac.ten}
                                    </option>
                                </c:forEach>
                            </select>
                        </div>
                        <div class="col-sm-2">
                            <label for="danhMuc">Danh mục</label>
                            <select name="danhMuc" id="danhMuc" class="form-select"
                                    onchange="filterDanhMuc(this.value)">
                                <option value="">Tất cả</option>
                                <c:forEach items="${listDanhMuc}" var="danhMuc">
                                    <option value="${danhMuc.id}">
                                            ${danhMuc.ten}
                                    </option>
                                </c:forEach>
                            </select>
                        </div>
                        <div class="col-sm-2">
                            <label for="chatLieu">Chất liệu</label>
                            <select name="chatLieu" id="chatLieu" class="form-select"
                                    onchange="filterChatLieu(this.value)">
                                <option value="">Tất cả</option>
                                <c:forEach items="${listChatLieu}" var="chatLieu">
                                    <option value="${chatLieu.id}">
                                            ${chatLieu.ten}
                                    </option>
                                </c:forEach>
                            </select>
                        </div>
                        <div class="col-sm-2">
                            <label for="kieuDang">Kiểu dáng</label>
                            <select name="kieuDang" id="kieuDang" class="form-select"
                                    onchange="filterKieuDang(this.value)">
                                <option value="">Tất cả</option>
                                <c:forEach items="${listFormDang}" var="kieuDang">
                                    <option value="${kieuDang.id}">
                                            ${kieuDang.ten}
                                    </option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>
                    <div class="row d-flex justify-content-center">
                        <div class="col-sm-2">
                            <label for="trangThai">Trang thái</label>
                            <select name="trangThai" id="trangThai" class="form-select"
                                    onchange="filterTrangThai(this.value)">
                                <option value="">Tất cả</option>
                                <option value="0">
                                    Kinh doanh
                                </option>
                                <option value="1">
                                    Ngừng Kinh doanh
                                </option>
                            </select>
                        </div>
                        <div class="col-sm-2">
                            <label for="sapXep">Sắp xếp</label>
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
                        <div class="col-sm-2">
                            <label for="gioiTinh">Giới tính</label>
                            <select name="gioiTinh" id="gioiTinh" class="form-select"
                                    onchange="filterGioiTinh(this.value)">
                                <option value="">Tất cả</option>
                                <option value="true">
                                    Nam
                                </option>
                                <option value="false">
                                    Nữ
                                </option>
                            </select>
                        </div>
                    </div>
                </div>
                <br>
                <div class="row">
                    <table>
                        <thead>
                        <tr>
                            <th scope="col">Ảnh</th>
                            <th scope="col">Mã</th>
                            <th scope="col">Tên Sản Phẩm</th>
                            <th scope="col">giá</th>
                            <th scope="col">Thao tác</th>
                        </tr>
                        </thead>
                        <tbody id="body">

                        </tbody>
                    </table>
                </div>
                <div class="container-fluid mt-5">
                    <nav aria-label="Page navigation example">
                        <ul class="pagination justify-content-center" id="phanTrang">
                        </ul>
                    </nav>
                </div>
            </div>
        </div>
    </div>
</div>
<%--chi tiết sản phẩm--%>
<div id="myModal" class="modal">
    <!-- Modal content -->
    <div class="modal-content-1">
        <span class="close" id="close_ctsp">&times;</span>
        <div class="row" id="chiTietSanPham">
            <div class="col l-3" id="img">

            </div>
            <div class="col l-3">
                <div class="row" id="sp">
                </div>
                <div class="row">
                    <label>Mùa sắc : </label>
                    <div class="form-check" id="mauSac">
                    </div>
                </div>
                <br>
                <div class="row">
                    <label>Kích cỡ : </label>
                    <div class="form-check" id="kichCo">
                    </div>
                </div>
                <br>
                <div class="row">
                    <div>
                        <label for="soLuongTon">Số lượng :</label>
                        <input type="number" value="1" min="1" id="soLuongTon" name="${hoaDon.id}" style="width: 20%"
                               aria-describedby="sl"/>
                        <div id="soLuong" class="form-text">
                        </div>
                    </div>
                </div>
                <br>
                <div class="row">
                    <button type="button" id="themVaoGioHang" name="" onclick="themSanPhamVaoGioHang(this.name)"
                            class="btn btn-primary">Thêm vào giỏ hàng
                    </button>
                </div>
            </div>
        </div>
    </div>
</div>
<%--hinh thức thanh toán--%>
<div id="modalQrcode" class="modal">
    <!-- Modal content -->
    <div class="modal-content-1" style="width: 40%">
        <span class="close" id="close_qrcode">&times;</span>
        <div class="row" id="qrcode">
            <video id="preview"></video>
        </div>
    </div>
</div>

</body>
<script src="/js/banHangTaiQuay/modal.js">
</script>
<script src="/js/banHangTaiQuay/sanPham.js"></script>
<script src="/js/banHangTaiQuay/chiTietSanPham.js"></script>
<script src="/js/banHangTaiQuay/thanhToan.js"></script>
<script src="/js/banHangTaiQuay/khachHang.js"></script>
</html>