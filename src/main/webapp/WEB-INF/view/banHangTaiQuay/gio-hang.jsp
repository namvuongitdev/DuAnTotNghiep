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
    <link rel="stylesheet" href="/css/ban-hang-tai-quay.css">

</head>
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
            <div class="container">
                <h4>Tạo hoá đơn</h4>
                <br>
                <%--hiển thị giỏ hàng--%>
                <div class="row">
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
                                <c:forEach items="${sanPhams}" var="sanPham">
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
                                                   name="soLuong"  value="${sanPham.soLuong}" min="1"
                                                   style="width: 25%" />
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
                                    <a type="button" class="btn btn-secondary" data-bs-toggle="modal"
                                       data-bs-target="#extraLargeModalKhachHang" name="${khachHangs}"
                                       onclick="addKhachHang(this.name)">
                                        Chọn tài khoản
                                    </a>
                                </div>
                                <div class="col-2">
                                    <div id="huyChon">

                                    </div>
                                </div>
                            </div>
                            <hr>
                            <div class="col l-3" id="thongTinKhachHang" style="margin-bottom: 10px">
                                <div><label>Họ tên khách hàng : ${hoaDon.hoTen}</label></div>
                            </div>
                        </div>
                    </div>

                    <form class="row" style="background-color: white" action="/hoa-don/thanh-toan?idHD=${hoaDon.id}" method="post" modelAtrribute="${request}">
                        <%-- thông tin thanh toán--%>
                        <div class="col l-3">
                        </div>
                        <div class="col l-3">
                            <h2>Thông tin thanh toán</h2></br>
                            <div>
                                <span>Tiền Hàng : <fmt:formatNumber pattern="#,###"
                                                                    value="${tongTien}"></fmt:formatNumber></span>
                            </div>
                            <div>
                                <h6>Khách cần trả : <fmt:formatNumber pattern="#,###"
                                                                      value="${tongTien}"></fmt:formatNumber></h6>
                            </div>
                            <div>
                                <h6>Hình thức thanh toán : </h6>
                                <select name="hinhThucThanhToan">
                                    <option value="true">Tiền mặt</option>
                                    <option value="false">Chuyển Khoản</option>
                                </select>
                            </div>
                            <br>
                            <div id="hinhThucThanhToan">
                                <div class="mb-3 form-floating">
                                    <input class="form-control" type="number" style="width: 50%" name="soTienThanhToan"
                                           id="tienKhachDua" onkeydown="getTienKhachDua({tienKhacDua:this.value , tongTien:`${tongTien}`})">
                                    <label for="tienKhachDua">Tiền khách đưa</label>
                                    <c:if test="${message != null}">
                                        <span style="color: #E43535">${message}</span>
                                    </c:if>
                                    <span id="tienThuaCuaKhach" name="tienThuaTraKhach"  style="color: #03AA28"></span>
                                </div>
                            </div>

                            <div class="mb-3 form-floating">
                            <textarea name="moTa" class="form-control" id="moTa"
                                      style="width: 50%"></textarea>
                                <label for="moTa">Mô Tả</label>
                            </div>
                            <div>
                                <button class="btn btn-primary" id="xacNhanThanhToan"
                                >Xác nhận thanh toán
                                </button>
                            </div>
                            </br>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>


<%--thêm khách hàng--%>
<div id="extraLargeModalKhachHang" class="modal fade" tabindex="-1" role="dialog">
    <div class="modal-dialog modal-xl">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title">khách hàng</h5>
            </div>
            <div class="modal-body">
                <div class="modal-body">
                    <div class="row" id="khachHang">

                    </div>
                </div>
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
                    <div class="col l-3">

                        <select name="danhMuc" id="danhMuc" class="form-select" onchange="filterDanhMuc(this.value)">
                            <option value="">Tất cả danh mục</option>
                            <c:forEach items="${listDanhMuc}" var="danhMuc">
                                <option value="${danhMuc.id}" ${filter.danhMuc == danhMuc.id ? 'selected' : ''}>
                                        ${danhMuc.ten}
                                </option>
                            </c:forEach>
                        </select>

                        <select name="chatLieu" id="chatLieu" class="form-select" onchange="filterChatLieu(id: this.value)">
                            <option value="">Tất cả chất liệu</option>
                            <c:forEach items="${listChatLieu}" var="chatLieu">
                                <option value="${chatLieu.id}" ${filter.chatLieu == chatLieu.id ? 'selected' : ''}>
                                        ${chatLieu.ten}
                                </option>
                            </c:forEach>
                        </select>

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
        <span class="close">&times;</span>
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
                               aria-describedby="sl" />
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
<div id="modalHinhThucThanhToan" class="modal">
    <!-- Modal content -->
    <div class="modal-content-1">
        <span class="close">&times;</span>
        <div class="row" id="thanhToan">
        </div>
    </div>
</div>

</body>
<script src="/js/banHangTaiQuay/modal.js">
</script>
<script src="/js/banHangTaiQuay/sanPham.js"></script>
<script src="/js/banHangTaiQuay/chiTietSanPham.js"></script>
<script src="/js/banHangTaiQuay/thanhToan.js"></script>
</html>