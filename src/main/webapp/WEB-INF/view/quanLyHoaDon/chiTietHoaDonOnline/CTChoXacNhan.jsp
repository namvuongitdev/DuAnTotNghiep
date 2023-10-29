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
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.1/font/bootstrap-icons.css">
    <script type="text/javascript"
            src="https://cdnjs.cloudflare.com/ajax/libs/webrtc-adapter/3.3.3/adapter.min.js"></script>
    <script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/vue/2.1.10/vue.min.js"></script>
    <script type="text/javascript" src="https://rawgit.com/schmich/instascan-builds/master/instascan.min.js"></script>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/sweetalert2@11.1.5/dist/sweetalert2.min.css">
    <script src="https://cdn.jsdelivr.net/npm/sweetalert2@11.1.5/dist/sweetalert2.min.js"></script>
    <link rel="stylesheet" href="../../../../css/banHangTaiQuay/hoaDon/chiTietHoaDon.css">
    <link rel="stylesheet" href="../../../../css/ban-hang-tai-quay.css">
    <style>
        #info span {
            font-weight: 600;
            padding-left: 5px;
        }
        table  {
            border-collapse: collapse;
            width: 100%;
        }

        th, td {
            padding: 8px;
            text-align: left;
            border-bottom: 1px solid #ddd;
            font-weight: 600;
        }
    </style>
</head>
<body>
<%--navbar--%>
<jsp:include page="../../sidebar/navbar.jsp"/>
<%--sidebar--%>
<div class="container-fluid">
    <div class="row flex-nowrap">
        <jsp:include page="../../sidebar/sidebar.jsp"/>
        <div class="col py-3">
            <div class="pagetitle">
                <h3>Thông tin hóa đơn</h3>
                <nav>
                    <ol class="breadcrumb">
                        <li class="breadcrumb-item"><a href="/admin/trang-chu" style="text-decoration: none; color: black">Trang chủ</a></li>
                        <li class="breadcrumb-item"><a href="/admin/hoa-don-onl/cho-xac-nhan/hien-thi" style="text-decoration: none; color: black">Đơn đặt hàng</a></li>
                        <li class="breadcrumb-item active">Thông tin hóa đơn</li>
                    </ol>
                </nav>
            </div><!-- End Page Title -->
            <div class="container-fluid" >
                <%--@elvariable id="hoaDon" type=""--%>
                <%--                <form:form action="/admin/hoa-don/update-hoa-don/${hd.id}" method="post" modelAttribute="hoaDon">--%>
                <div class="col">
                    <div class="card">
                        <div class="card-body row">
                            <h4 class="card-title">Chi tiết hóa đơn đặt hàng</h4>
                                                            <br><br>
                                                            <div id="info">
                                                                <p><b>Mã đơn:</b>  <span  value="${hd.ma}">${hd.ma}</span></p>
                                                                <p><b>Ngày thanh tạo:</b>  <span value="${hd.ngayTao}">${hd.ngayTao}</span></p>
                                                                <p><b>Ngày thanh toán:</b>  <span value="${hd.ngayThanhToan}">${hd.ngayThanhToan}</span></p>
                                                                <p><b>Khách hàng:</b>  <span value="${hd.hoTen==null?'Khách bán lẻ':hd.hoTen}">${hd.hoTen==null?'Khách bán lẻ':hd.hoTen}</span></p>
                                                                <p><b>Nhân viên bán hàng:</b>  <span value="${hd.nhanVien.hoTen}">${hd.nhanVien.hoTen}</span></p>
                                                                <p><b>Trạng thái:</b>  <span>${hd.trangThai==4?'Đã thanh toán':(hd.trangThai==3)?'Đang giao hàng':(hd.trangThai==0)?'Đang chờ':(hd.trangThai==1)?'Chờ xác nhận':(hd.trangThai==2)?'Đã tiếp nhận':(hd.trangThai==6)?'Giao hàng thành công':(hd.trangThai==5)?'Hủy đơn hàng':''}</span></p>
                                                                <br>
                                                                <div style="display: ${hd.trangThai==1?'block':'none'}"><button class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#extraLargeModal"
                                                                             name="1" onclick="getSanPham(this.name)" style="margin-left: 800px; margin-bottom: 20px">Thêm sản phẩm</button></div>
                                                                <div class="table-wrapper">
                                                                    <table class="table">
                                                                        <thead>
                                                                        <tr>
                                                                            <th data-field="state" data-checkbox="true"></th>
                                                                            <th scope="col">Tên sản phẩm</th>
                                                                            <th scope="col">Đơn giá</th>
                                                                            <th scope="col">Số lượng</th>
                                                                            <th scope="col">Thành tiền</th>
                                                                            <th scope="col">Thao tác</th>
                                                                        </tr>
                                                                        </thead>
                                                                        <tbody>
                                                                        <c:forEach items="${lst1}" var="hdct" varStatus="i">
                                                                            <tr>
                                                                                <td></td>
                                                                                <td>
                                                                                    <div style="display: flex; align-items: center;">
                                                                                        <img src="/image/${hdct.chiTietSanPham.sanPham.img}" alt=""
                                                                                             style="margin-right: 10px;" width="100px" height="100px">
                                                                                        <div>
                                                                                            <h6>
                                                                                                <strong>
                                                                                                    <p style="text-transform: uppercase" class="TenSP">${hdct.chiTietSanPham.sanPham.ten}</p>
                                                                                                </strong>
                                                                                            </h6>
                                                                                            <div class="ThongTinSP">
                                                                                                <span>${hdct.chiTietSanPham.mauSac.ten}</span>
                                                                                                <span>/</span>
                                                                                                <span>${hdct.chiTietSanPham.size.ten}</span>
                                                                                            </div>
                                                                                        </div>
                                                                                    </div>
                                                                                </td>
                                                                                <td><input type="text" class="form-control" id="giaBan" readonly value="<fmt:formatNumber pattern="#,###"  value="${hdct.chiTietSanPham.sanPham.giaBan}"/>"/></td>
                                                                                <td><input type="number" class="form-control" id="soLuongSp" ${hd.trangThai==1?'':'disabled'}  onchange="myFunction({idhdct:`${hdct.id}`,soLuong:this.value,idhd:`${hd.id}`})" value="${hdct.soLuong}" min="1" style="width: 40%"></td>
                                                                                <td><input type="text" class="form-control" readonly name="${hdct.id}" id="thanhTien" value="<fmt:formatNumber pattern="#,###"  value="${hdct.soLuong * hdct.chiTietSanPham.sanPham.giaBan}"/>"></td>
                                                                                <td><a style="height: 25px" onclick="xoaSp({idhdct:`${hdct.id}`,idhd:`${hd.id}`})" type="button" class="badge text-bg-danger text-white"><i class="bi bi-trash3-fill"></i></a></td>
                                                                            </tr>
                                                                        </c:forEach>
                                                                        </tbody>
                                                                    </table>
                                                                </div>
                                                                <br>
                                                                <p><b>Tổng giá trị sản phẩm:</b>  <span><fmt:formatNumber pattern="#,###"  value="${hdc}"/> VNĐ</span></p>
                                                                <p><b>Phí vận chuyển:</b>  <span><fmt:formatNumber pattern="#,###"  value="${hd.phiVanChuyen}"/> VNĐ</span></p>
<%--                                                                <p><b>Giảm giá:</b>  <span >0 VNĐ</span></p>--%>
                                                                <p><b>Tổng tiền thanh toán:</b>  <span><fmt:formatNumber pattern="#,###"  value="${hd.tongTien+hd.phiVanChuyen}"/> VNĐ</span></p>
                                                            </div><br>
<%--                            Lịch sử hóa đơn--%>
                            <h4 class="card-title">Lịch sử hóa đơn</h4>
                            <br><br>
                            <div>
                                <div class="table-wrapper">
                                    <table class="table">
                                        <thead>
                                        <tr>
                                            <th data-field="state" data-checkbox="true"></th>
                                            <th scope="col">Ngày thực hiện</th>
                                            <th scope="col">Người thực hiện</th>
                                            <th scope="col">Thao tác</th>
                                            <th scope="col">Trạng thái</th>
                                        </tr>
                                        </thead>
                                        <tbody>
                                        <%--                                            <c:forEach items="${lst}" var="hdct" varStatus="i">--%>
                                        <%--                                                <tr>--%>
                                        <%--                                                    <td></td>--%>
                                        <%--                                                    <td></td>--%>
                                        <%--                                                    <td></td>--%>
                                        <%--                                                    <td></td>--%>
                                        <%--                                                    <td></td>--%>
                                        <%--                                                </tr>--%>
                                        <%--                                            </c:forEach>--%>
                                        </tbody>
                                    </table>
                                </div>
                                <br>
                            </div>

                            <%--Time line--%>
                            <div class="main-timeline">
                                <div class="timeline">
                                    <div class="icon"></div>
                                    <div class="date-content">
                                        <div class="date-outer">
                                            <span class="date">
                                                <span class="month"></span>
                                                <span class="hour"></span>
                                            </span>
                                        </div>
                                    </div>

                                    <div class="timeline-content">
                                        <h5 class="title">Chờ xác nhận</h5>
                                        <p class="description"><strong>Thao tác: </strong>Tạo đơn hàng</p>
                                        <p><strong>Người tạo đơn: </strong>
                                            <span></span>
                                            <span></span>
                                        </p>
                                    </div>
                                </div>
                                <div class="timeline" style="display: ${hd.trangThai==1?"none":"block"}">
                                    <div class="icon"></div>
                                    <div class="date-content">
                                        <div class="date-outer">
                                                <span class="date">
                                                    <span class="month"></span>
                                                </span>
                                        </div>
                                    </div>
                                    <div class="timeline-content">
                                        <h5 class="title">Chờ giao hàng</h5>
                                        <p class="description"><strong>Thao tác: </strong>Xác nhận đơn, đợi giao hàng cho đơn vị vận chuyển
                                        </p>
                                        <p><strong>Người thao tác: </strong><span></span></p>
                                    </div>
                                </div>
                                <div class="timeline" style="display: ${hd.trangThai==1?"none":hd.trangThai==2?"none":"block"}">
                                    <div class="icon"></div>
                                    <div class="date-content">
                                        <div class="date-outer">
                                            <span class="date">
                                                <span class="month"></span>
                                            </span>
                                        </div>
                                    </div>
                                    <div class="timeline-content">
                                        <h5 class="title">Đang giao hàng</h5>
                                        <p class="description"><strong>Thao tác: </strong>Đã bàn giao cho đơn vị vận chuyển, đang giao đến người nhận</p>
                                        <p><strong>Người thao tác: </strong><span></span></p>
                                    </div>
                                </div>

                                <div class="timeline" style="display: ${hd.trangThai==1?"none":hd.trangThai==2?"none":hd.trangThai==3?"none":"block"}">
                                    <div class="icon"></div>
                                    <div class="date-content">
                                        <div class="date-outer">
                                            <span class="date">
                                                <span class="month"></span>
                                            </span>
                                        </div>
                                    </div>
                                    <div class="timeline-content">
                                        <h5 class="title">Đã giao hàng</h5>
                                        <p class="description"><strong>Thao tác: </strong>Đã giao hàng thành công</p>
                                        <p><strong>Người thao tác: </strong><span></span></p>
                                    </div>
                                </div>
                            </div>
                            </div>

                            <div style="margin-top: 80px">
                                <!--XÁC NHẬN ĐƠN HÀNG-->
                                <button type="button" class="action-button XacNhanDon">
                                    Xác nhận
                                </button>
                                <div class="modal fade xacNhanModal" tabindex="-1" aria-labelledby="xacNhanModalLabel"
                                     aria-hidden="true">
                                    <div class="modal-dialog modal-dialog-centered">
                                        <div class="modal-content">
                                            <div class="modal-header">
                                                <h5 class="modal-title" id="xacNhanModalLabel">Xác nhận đơn</h5>
                                                <button type="button" class="btn-close" data-bs-dismiss="modal"
                                                        aria-label="Close"></button>
                                            </div>
                                            <div class="modal-body">
                                                Xác nhận đơn hàng?
                                            </div>
                                            <div class="modal-footer">
                                                <button type="button" class="btn btn-primary btn-dong-y">Đồng ý</button>
                                                <button type="button" class="btn btn-secondary btn-khong"
                                                        data-bs-dismiss="modal">Không
                                                </button>
                                            </div>
                                        </div>
                                    </div>
                                </div>

                                <!--HỦY ĐƠN HÀNG-->
                                <button class="action-button HuyDon">
                                    Hủy đơn
                                </button>
                                <div class="modal fade huyModal" tabindex="-1" aria-labelledby="huyDonModalLabel"
                                     aria-hidden="true">
                                    <div class="modal-dialog modal-dialog-centered">
                                        <div class="modal-content">
                                            <div class="modal-header">
                                                <h5 class="modal-title" id="huyDonModalLabel">Hủy đơn hàng</h5>
                                                <button type="button" class="btn-close" data-bs-dismiss="modal"
                                                        aria-label="Close"></button>
                                            </div>
                                            <div class="modal-body">
                                                Hủy đơn hàng?
                                            </div>
                                            <div class="modal-footer">
                                                <button type="button" class="btn btn-primary btn-dong-y">Đồng ý</button>
                                                <button type="button" class="btn btn-secondary btn-khong"
                                                        data-bs-dismiss="modal">Không
                                                </button>
                                            </div>
                                        </div>
                                    </div>
                                </div>

                                <button style="margin-left: 15px" type="button" class="action-button inHoaDonChiTiet">
                                    In hóa đơn
                                </button>
                                <input type="hidden" id="idChiTietHoaDon">
                                <div class="modal fade inHoaDonModal" tabindex="-1" aria-labelledby="inHoaDonModalLabel"
                                     aria-hidden="true">
                                    <div class="modal-dialog modal-dialog-centered">
                                        <div class="modal-content">
                                            <div class="modal-header">
                                                <h5 class="modal-title" id="inHoaDonModalLabel">In hóa đơn</h5>
                                                <button type="button" class="btn-close" data-bs-dismiss="modal"
                                                        aria-label="Close"></button>
                                            </div>
                                            <div class="modal-body">
                                                Bạn muốn in hóa đơn?
                                            </div>
                                            <div class="modal-footer">
                                                <button type="button" class="btn btn-primary btn-dong-y">Đồng ý</button>
                                                <button type="button" class="btn btn-secondary btn-khong"
                                                        data-bs-dismiss="modal">Không
                                                </button>
                                            </div>
                                        </div>
                                    </div>
                                </div>

                                <button class="action-button" type="button" onclick="quayLai({trangThai:`${hd.trangThai}`})">Quay lại
                                    <i class="bi bi-arrow-left"></i>
                                </button>
                            </div>
                        </div>
                        <br>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<%--thêm sản phẩm--%>
<div id="extraLargeModal" class="modal fade" tabindex="-1" role="dialog" >
    <div class="modal-dialog modal-xl">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" style="padding-left: 350px" id="modalAdd">Thêm sản phẩm</h5>
            </div>
            <div class="modal-body">
                <div class="row">
                    <div class="col-sm-3">
                        <input class="form-control" type="text" name="search" id="search-input"
                               placeholder="tìm kiếm tên, mã sản phẩm">
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
                    <label>Màu sắc : </label>
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
                        <input type="number" value="1" min="1" id="soLuongTon" name="${hd.id}" style="width: 20%"
                               aria-describedby="sl"/>
                        <div id="soLuong" class="form-text">
                        </div>
                    </div>
                </div>
                <br>
                <div class="row">
                    <button type="button" id="themVaoGioHang" name=""  onclick="themSanPhamVaoGioHang2(this.name)"
                            class="btn btn-primary">Thêm vào giỏ hàng
                    </button>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
<script src="/js/banHangTaiQuay/modal.js">
</script>
<script src="/js/banHangTaiQuay/sanPham.js"></script>
<script>
    let sizeSP = document.getElementById("kichCo");
    let colorSP = document.getElementById("mauSac");
    let kichCo;
    let mauSac;
    let sanPham;
    let dataCTSP;

    function getModal(dataSanPham) {
        sanPham = dataSanPham.idSanPham;
        modal.style.display = "block";
        fetch("/admin/chi-tiet-san-pham/" + dataSanPham.idSanPham)
            .then(response => response.json())
            .then(data => {
                dataCTSP = data;
                for (let i = 0; i < data.length; i++) {
                    document.getElementById("img").innerHTML = `<img src="/image/${dataSanPham.img}">`
                    document.getElementById("sp").innerHTML = `<div><h3>${dataSanPham.tenSanPham}</h3></div> <div><h5 style="color: #03AA28"> ${VND.format(dataSanPham.giaSanPham)} </h5></div>`
                    break;
                }
                getMauSac(data);
                getSize(data);
            });
    }

    function getMauSac(data) {
        for (let i = 0; i < data.length; i++) {
            let mauSacSP = document.getElementById(data[i].mauSac.id);
            if (mauSacSP != null) {
                continue;
            } else {
                dataColor(data[i].mauSac)
            }
        }
    }

    function getSize(data) {
        for (let i = 0; i < data.length; i++) {
            let kichCoSP = document.getElementById(data[i].size.id);
            if (kichCoSP != null) {
                continue;
            } else {
                dataSize(data[i].size)
            }
        }
    }

    function dataSize(data) {
        sizeSP.innerHTML += `
                       <input type="radio" class="btn-check" onclick="getCTSP({
                         id:this.value,
                         type:'kichCo'
                       })"
                        value="` + data.id + `"   name="success-outlined-1"  id="` + data.id + `" autocomplete="off">
                            <label class="btn btn-outline-secondary"  for="` + data.id + `">` + data.ten + `</label>
                      `
    }

    function dataColor(data) {
        colorSP.innerHTML += `
                        <input type="radio" class="btn-check" onclick="getCTSP({
                          id:this.value,
                          type:'mauSac'
                        })"
                         value="` + data.id + `"   name="success-outlined" id="` + data.id + `" autocomplete="off">
                             <label class="btn btn-outline-secondary"  for="` + data.id + `">` + data.ten + `</label>
                       `
    }

    function getCTSP(id) {
        if (id.type == 'kichCo') {
            kichCo = id.id;
        } else {
            mauSac = id.id;
            sizeSP.innerHTML = "";
            kichCo = undefined;
            document.getElementById("themVaoGioHang").name = "";
            for (var i = 0; i < dataCTSP.length; i++) {
                if (dataCTSP[i].mauSac.id == mauSac && dataCTSP[i].sanPham.id == sanPham) {
                    dataSize(dataCTSP[i].size)
                }

            }
        }
        if (mauSac != undefined && kichCo != undefined) {
            const sp = document.getElementById("sp");
            const themVaoGioHang = document.getElementById("themVaoGioHang");
            for (let i = 0; i < dataCTSP.length; i++) {
                if (dataCTSP[i].mauSac.id == mauSac && dataCTSP[i].size.id == kichCo && dataCTSP[i].sanPham.id == sanPham) {
                    if (dataCTSP[i].soLuong == 0 || dataCTSP[i].soLuong < 0 || dataCTSP.trangThai == 1) {
                        themVaoGioHang.setAttribute("disabled", "");
                        sp.innerHTML += `<h5 id="message" style="color: #e43535">Sản phẩm hết hàng</h5>`
                        return;
                    } else {
                        themVaoGioHang.removeAttribute('disabled');
                        document.getElementById("soLuong").innerText = `số lượng sản phẩm còn ` + dataCTSP[i].soLuong
                        themVaoGioHang.name = dataCTSP[i].id;
                        document.getElementById("soLuongTon").max = dataCTSP[i].soLuong;
                        return;
                    }
                }
            }
        }
    }

    function themSanPhamVaoGioHang2(idCTSP) {
        var soLuong = document.getElementById("soLuongTon").value;
        var idHD = document.getElementById("soLuongTon").name;
        if (idCTSP == "") {
            alert("lựa chon các thuộc tính sản phẩm")
        } else {
            for (let i = 0; i < dataCTSP.length; i++) {
                if (idCTSP == dataCTSP[i].soLuong) {
                    if (soLuong > dataCTSP[i].soLuong) {
                        alert("số lượng sản phẩm không đủ");
                        return;
                    }
                }
            }
            window.location.href = "/admin/hoa-don-onl/update?ctsp=" + idCTSP + "&soLuong=" + soLuong + "&idHD=" + idHD;
        }
    }
    // --------------------------------------------
    function myFunction(data){
        if (confirm("Bạn có muốn thay đổi số lượng không")===true){
            window.location.href = "/admin/hoa-don-onl/update-so-luong/"+data.idhd+"?hdct=" + data.idhdct + "&soLuong=" + data.soLuong;
        }else {
            window.location.reload();
            return;
        }
    }
    function quayLai(data) {
        let tt=data.trangThai;
        if (confirm("Bạn có muốn quay lại trang trước không?")===true){
            if (tt==1){
                 window.location.href="/admin/hoa-don-onl/cho-xac-nhan/hien-thi";
                 return;
            } if (tt==2){
                 window.location.href="/admin/hoa-don-onl/cho-giao-hang/hien-thi";
                return;
            }else if (tt==3){
                 window.location.href="/admin/hoa-don-onl/dang-giao/hien-thi";
                return;
            }else if (tt==6){
                 window.location.href="/admin/hoa-don-onl/da-giao/hien-thi";
                return;
            }else {
                 window.location.href="/admin/hoa-don-onl/cho-giao-hang/hien-thi";
                return;
            }
        }else {
            return;
        }
    }
    function xoaSp(data){
        if (confirm("Bạn có muốn xóa sản phẩm không?")===true){
            window.location.href="/admin/hoa-don-onl/delete/"+data.idhd+"?idHdct="+data.idhdct;
        }else {
            return;
        }
    }

</script>
</html>