<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>


<html>
<head>
    <link rel="shortcut icon" href="#">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css"
          rel="stylesheet" integrity="sha384-9ndCyUaIbzAi2FUVXJi0CjmCapSmO7SnpJef0486qhLnuZ2cdeRhO02iuK6FUUVM"
          crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"
            integrity="sha384-geWF76RCwLtnZ8qwWowPQNguL3RmwHVBC9FhGdlKrxdiJJigb/j/68SIy3Te4Bkz"
            crossorigin="anonymous"></script>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.1/font/bootstrap-icons.css">
    <!-- Styles -->
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css"/>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/select2@4.1.0-rc.0/dist/css/select2.min.css"/>
    <link rel="stylesheet"
          href="https://cdn.jsdelivr.net/npm/select2-bootstrap-5-theme@1.3.0/dist/select2-bootstrap-5-theme.min.css"/>
    <!-- Or for RTL support -->
    <link rel="stylesheet"
          href="https://cdn.jsdelivr.net/npm/select2-bootstrap-5-theme@1.3.0/dist/select2-bootstrap-5-theme.rtl.min.css"/>
    <link rel="stylesheet" href="/css/modal.css">

    <script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.6.9/angular.min.js"></script>
    <script src="
              https://cdn.jsdelivr.net/npm/sweetalert2@11.10.0/dist/sweetalert2.all.min.js
              "></script>
     <link href="
          https://cdn.jsdelivr.net/npm/sweetalert2@11.10.0/dist/sweetalert2.min.css
         " rel="stylesheet">

</head>
<body ng-app="app" ng-controller="controller">
<%--navbar--%>
<jsp:include page="../../sidebar/navbar.jsp"/>
<%--sidebar--%>
<div class="container-fluid">
    <div class="row flex-nowrap">
        <jsp:include page="../../sidebar/sidebar.jsp"/>
        <div class="col py-3">
            <div class="pagetitle">
                <h3>Sản phẩm</h3>
                <nav>
                    <ol class="breadcrumb">
                        <li class="breadcrumb-item"><a href="/admin/trang-chu"
                                                       style="text-decoration: none; color: black">Trang chủ</a></li>
                        <li class="breadcrumb-item">Quản lý sản phẩm</li>
                        <li class="breadcrumb-item"><a href="/admin/san-pham/hien-thi"
                                                       style="text-decoration: none; color: black"
                                                       onclick="clearLocalStorage()">Sản phẩm</a></li>
                        <li class="breadcrumb-item active">${title}</li>
                    </ol>
                </nav>
            </div><!-- End Page Title -->
            <section class="section">
                <div class="card">
                    <div class="card-body row">
                        <h5 class="card-title">Thông tin sản phẩm</h5>
                        <br><br>
                        <form:form action="/admin/san-pham/add?id=${sp.id}" method="post" modelAttribute="sanPham">
                            <div class="row">
                                <div class="col-6">
                                    <div class="mb-3">
                                        <label for="ten" class="form-label ">Tên sản phẩm</label>
                                        <input type="text" class="form-control" name="ten" id="ten" value="${sp.ten}">
                                        <form:errors path="ten" cssStyle="color: red"/>
                                    </div>
                                    <div class="mb-3">
                                        <label for="gioiTinh" class="form-label">Giới tính</label>
                                        <select name="gioiTinh" class="form-select" id="gioiTinh">
                                            <option value="true" ${sp.gioiTinh == true ? 'selected' : '' }>
                                                Dành cho nam
                                            </option>
                                            <option value="false" ${sp.gioiTinh == false ? 'selected' : '' }>
                                                Dành cho nữ
                                            </option>
                                        </select>
                                    </div>
                                    <div class="mb-3">
                                        <label for="giaBan" class="form-label">Giá bán</label>
                                        <input type="text" class="form-control" name="giaBan" id="giaBan"
                                               value="${sp.giaBan}">
                                        <form:errors path="giaBan" cssStyle="color: red"/>
                                    </div>
                                    <div class="mb-3">
                                        <label for="moTa" class="form-label">Mô tả</label>
                                        <textarea name="moTa" class="form-control" id="moTa">${sp.moTa}</textarea>
                                        <form:errors path="moTa" cssStyle="color: red"/>
                                    </div>
                                </div>
                                <div class="col-6">
                                    <div class="row mb-3">
                                        <div class="col-11">
                                            <label for="chatLieu" class="form-label">Chất liệu</label>
                                            <select name="chatLieu" class="form-select" id="chatLieu">
                                                <c:forEach items="${listChatLieu}" var="chatLieu">
                                                    <option value="${chatLieu.id}" ${sp.chatLieu.id==chatLieu.id?'selected':''}>${chatLieu.ten}</option>
                                                </c:forEach>
                                            </select>
                                        </div>
                                        <div class="col-1" style="padding-top: 40px">
                                            <a data-bs-toggle="modal" data-bs-target="#exampleModalChatLieu">
                                                <i class="bi bi-plus-circle"></i>
                                            </a>
                                        </div>
                                    </div>
                                    <div class="row mb-3">
                                        <div class="col-11">
                                            <label for="kieuDang" class="form-label">Kiểu dáng</label>
                                            <select name="kieuDang" class="form-select" id="kieuDang">
                                                <c:forEach items="${listFromDang}" var="kieuDang">
                                                    <option value="${kieuDang.id}" ${sp.kieuDang.id==kieuDang.id?'selected':''}>${kieuDang.ten}</option>
                                                </c:forEach>
                                            </select>
                                        </div>
                                        <div class="col-1" style="padding-top: 40px">
                                            <a data-bs-toggle="modal" data-bs-target="#exampleModalKieuDang">
                                                <i class="bi bi-plus-circle"></i>
                                            </a>
                                        </div>
                                    </div>
                                    <div class="row mb-3">
                                        <div class="col-11">
                                            <label for="danhMuc" class="form-label">Danh mục</label>
                                            <select name="danhMuc" class="form-select" id="danhMuc">
                                                <c:forEach items="${listDanhMuc}" var="danhMuc">
                                                    <option value="${danhMuc.id}" ${sp.danhMuc.id==danhMuc.id?'selected':''}>${danhMuc.ten}</option>
                                                </c:forEach>
                                            </select>
                                        </div>
                                        <div class="col-1" style="padding-top: 40px">
                                            <a data-bs-toggle="modal" data-bs-target="#exampleModalDanhMuc">
                                                <i class="bi bi-plus-circle"></i>
                                            </a>
                                        </div>
                                    </div>
                                    <div class="mb-3">
                                        <label for="trangThai" class="form-label">Trạng Thái</label>
                                        <select name="trangThai" class="form-select" id="trangThai">
                                            <option value="0" ${sp.trangThai == 0 ? 'selected' : '' }>
                                                Kinh doanh
                                            </option>
                                            <option value="1" ${sp.trangThai == 1 ? 'selected' : '' }>
                                                Ngừng kinh doanh
                                            </option>
                                        </select>
                                    </div>
                                </div>
                            </div>
                            <br>
                            <div style="text-align: center">
                                <c:if test="${sp.id == null}">
                                <button class="btn btn-success" onclick="saveInputValues()">Xác nhận
                                    </c:if>
                                    <c:if test="${sp.id != null}">
                                    <button class="btn btn-primary" onclick="saveInputValues()">Cập nhật
                                        </c:if>
                                    </button>
                                    <a href="/admin/san-pham/new" class="btn btn-warning" onclick="clearLocalStorage()">Làm
                                        Mới</a>
                            </div>
                        </form:form>
                    </div>
                </div>
                <br><br>
                <%--                 chi tiết  sản phẩm--%>
                <div class="card" style="display: ${sp.id==null?'none' : 'block'}">
                    <div class="card-body row">
                        <h5 class="card-title col-9">Thông tin chi tiết sản phẩm</h5>
                        <div class="col l-3">
                            <a type="button" class="btn btn-primary" data-bs-toggle="modal"
                               data-bs-target="#exampleModal">Thêm
                            </a>
                            <p style="color: red">${loiAdd}</p>
                        </div>
                        <br><br>
                        <div class="row">

                            <%--                         Hiển thị ảnh chi tiết sản phẩm--%>
                            <c:if test="${listChiTietSanPhamBySP != null}">
                                <div class="col l-5" style="margin-bottom: 30px">
                                    <p class="text-center"><b>Thêm ảnh theo màu sắc</b></p>
                                    <div class="row">
                                        <select name="mauSacAnh" class="form-select"
                                                style="width: 50%;margin-left: 90px"
                                                onchange="findAnhMauSac(`${sp.id}` , this.value)">
                                            <option value="">Màu sắc</option>
                                            <c:forEach items="${listMauSacCTSP}" var="mauSacCTSP">
                                                <option value="${mauSacCTSP.id}">
                                                        ${mauSacCTSP.ten}
                                                </option>
                                            </c:forEach>
                                        </select>
                                    </div>
                                </div>
                            </c:if>
                            <%--table--%>
                            <div class="col-8" style="padding-left: 50px">
                                <table class="table">
                                    <thead>
                                    <tr style="text-align: center">
                                        <th scope="col">Kích cỡ
                                            <a data-bs-toggle="modal" data-bs-target="#exampleModalKichCo">
                                                <i class="bi bi-plus-circle"></i>
                                            </a>
                                        </th>
                                        <th scope="col">Màu sắc
                                            <a data-bs-toggle="modal" data-bs-target="#exampleModalMauSac">
                                                <i class="bi bi-plus-circle"></i>
                                            </a>
                                        </th>
                                        <th scope="col">Số lượng</th>
                                        <th scope="col">Trạng thái</th>
                                        <th scope="col">Thao tác</th>
                                    </tr>
                                    </thead>
                                    <tbody>
                                    <c:forEach items="${listChiTietSanPhamBySP}" var="ctsp">
                                        <form action="/admin/chi-tiet-san-pham/update-chi-tiet-san-pham?idCTSP=${ctsp.id}&idSP=${sp.id}"
                                              method="post" modelAttribute="${chiTietSanPham}">
                                            <tr style="text-align: center">
                                                <td>
                                                    <select name="size" class="form-select">
                                                        <c:forEach items="${listKichCo}" var="kichCo">
                                                            <option value="${kichCo.id}" ${ctsp.size.id==kichCo.id?'selected':''}>${kichCo.ten}</option>
                                                        </c:forEach>
                                                    </select>
                                                </td>
                                                <td>
                                                    <select name="mauSac" class="form-select">
                                                        <c:forEach items="${listMuaSac}" var="mauSac">
                                                            <option value="${mauSac.id}" ${ctsp.mauSac.id==mauSac.id?'selected':''}>${mauSac.ten}</option>
                                                        </c:forEach>
                                                    </select>
                                                </td>
                                                <td style="width: 110px">
                                                    <label for="soLuongCTSP"></label>
                                                    <input type="number" class="form-control" value="${ctsp.soLuong}"
                                                           id="soLuongCTSP" name="soLuong" min="1" required/>
                                                </td>
                                                <td>
                                                    <button style="--bs-btn-padding-y: .56rem; --bs-btn-padding-x: .5rem; --bs-btn-font-size: .75rem;"
                                                            type="button" value="${ctsp.trangThai}"
                                                            onclick="myfunction({trangThai:`${ctsp.trangThai}`,idctsp:`${ctsp.id}`,idsp:`${ctsp.sanPham.id}`})"
                                                            class="${ctsp.trangThai == 0 ? 'btn btn-danger' : 'btn btn-success'}">
                                                            ${ctsp.trangThai == 0 ? 'Ngừng kinh doanh' : 'Kinh doanh'}
                                                    </button>
                                                </td>
                                                <td style="text-align: center">
                                                    <button class="btn btn-secondary" onclick="if(confirm('Bạn có muốn sửa không')){
                                                        return true;
                                                        }else{
                                                        return false;}">Sửa
                                                    </button>
                                                </td>
                                            </tr>
                                        </form>
                                    </c:forEach>
                                    </tbody>
                                </table>
                            </div>
                            <%--hiển thị ảnh theo màu sắc--%>
                            <div class="row" id="hienThiAnhMauSac">
                            </div>
                            <div class="row" id="anhMacDinhSanPham" style="margin-top: 20px">
                                <div class="pagination justify-content-center">
                                    <img src="/image/${sp.img}" alt="" class="img-thumbnail"
                                         style="width: 200px;height: 200px">
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </section>
            <%--            thêm chi tiết sản phẩm--%>
            <div class="modal" id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel"
                 aria-hidden="true">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h5 class="modal-title" id="exampleModalLabel">Thêm chi tiết sản phẩm</h5>
                            <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                        </div>
                        <div class="modal-body">
                            <div class="row">
                                <div class="col l-3">
                                    <form action="/admin/chi-tiet-san-pham/add?id=${sp.id}" method="post"
                                          enctype="multipart/form-data" modelAttribute="${chiTietSanPham}">
                                        <div class="row">
                                            <div class="row">
                                                <div class="mb-3">
                                                    <label class="form-label">Kích cỡ :</label>
                                                    <select id="size" name="size" class="form-select">
                                                        <option ng-repeat="kichCo in lstKichCo" value="{{kichCo.id}}">
                                                            {{kichCo.ten}}
                                                        </option>
                                                    </select>
                                                </div>
                                            </div>
                                            <div class="row">
                                                <div class="mb-3">
                                                    <label class="form-label">Màu sắc:</label>
                                                    <select id="mauSac" name="mauSac" class="form-select">
                                                        <option ng-repeat="mauSac in lstMauSac" value="{{mauSac.id}}">
                                                            {{mauSac.ten}}
                                                        </option>
                                                    </select>
                                                </div>
                                            </div>
                                            <div class="row">
                                                <div class="mb-3">
                                                    <label>Số lượng :</label>
                                                    <input type="number" class="form-control" value="1" min="1"
                                                           name="soLuong" style="width: 20%">
                                                </div>
                                            </div>
                                        </div>
                                        <div class="text-center">
                                            <button class="btn btn-primary" onclick="return checkMauSacKichCo()">Xác
                                                Nhận
                                            </button>
                                        </div>
                                    </form>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <%--                thêm ảnh sản phẩm --%>
            <div class="modal" id="exampleModalAnh" tabindex="-1" aria-labelledby="exampleModalLabelAnh"
                 aria-hidden="true">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h5 class="modal-title" id="exampleModalLabelAnh">Thêm ảnh</h5>
                            <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                        </div>
                        <div class="modal-body" id="themAnh">
                            <input type='file' name='file' id="fileAnh">
                            <button class='btn btn-primary' onclick="themAnhMauSac()" data-bs-dismiss="modal">Xác nhận
                            </button>
                        </div>
                    </div>
                </div>
            </div>
            <%-- thêm nhanh chất liệu--%>
            <div class="modal" id="exampleModalChatLieu" tabindex="-1">
                <div class="modal-dialog modal-dialog-scrollable">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h5 class="modal-title">Thêm dữ liệu</h5>
                            <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                        </div>
                        <form method="post" class="row g-3">
                            <div class="modal-body">
                                <div class="form-floating">
                                    <input ng-model="chatLieu.ten" type="text" name="ten" class="form-control"
                                           id="floatingName"
                                           placeholder="Chất liệu"/>
                                    <label for="floatingName">Chất liệu</label>
                                </div>
                            </div>
                            <div class="modal-footer">
                                <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Đóng</button>
                                <button ng-click="addChatLieu()" type="submit" class="btn btn-primary">
                                    Xác nhận
                                </button>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
            <%--thêm nhanh danh mục--%>
            <div class="modal" id="exampleModalDanhMuc" tabindex="-1">
                <div class="modal-dialog modal-dialog-scrollable">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h5 class="modal-title">Thêm dữ liệu</h5>
                            <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                        </div>
                        <form method="post" class="row g-3">
                            <div class="modal-body">
                                <div class="form-floating">
                                    <input ng-model="danhMuc.ten" type="text" name="ten" class="form-control"
                                           id="floatingDanhMuc"
                                           placeholder="Danh mục"/>
                                    <label for="floatingDanhMuc">Danh mục</label>
                                </div>
                            </div>
                            <div class="modal-footer">
                                <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Đóng</button>
                                <button ng-click="addDanhMuc()" type="submit" class="btn btn-primary"
                                        onclick="if(confirm('Bạn có chắc chắn muốn thêm không?')==true){}else{return false; }">
                                    Xác nhận
                                </button>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
            <%--thêm nhanh kiểu dáng--%>
            <div class="modal" id="exampleModalKieuDang" tabindex="-1">
                <div class="modal-dialog modal-dialog-scrollable">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h5 class="modal-title">Thêm dữ liệu</h5>
                            <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                        </div>
                        <form method="post" class="row g-3">
                            <div class="modal-body">
                                <div class="form-floating">
                                    <input ng-model="kieuDang.ten" type="text" name="ten" class="form-control"
                                           id="floatingKieuDang"
                                           placeholder="Kiểu dáng"/>
                                    <label for="floatingKieuDang">Kiểu dáng</label>
                                </div>
                            </div>
                            <div class="modal-footer">
                                <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Đóng</button>
                                <button ng-click="addKieuDang()" type="submit" class="btn btn-primary"
                                        onclick="if(confirm('Bạn có chắc chắn muốn thêm không?')==true){}else{return false; }">
                                    Xác nhận
                                </button>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
            <%--thêm nhanh kích cỡ--%>
            <div class="modal" id="exampleModalKichCo" tabindex="-1">
                <div class="modal-dialog modal-dialog-scrollable">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h5 class="modal-title">Thêm dữ liệu</h5>
                            <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                        </div>
                        <form method="post" class="row g-3">
                            <div class="modal-body">
                                <div class="form-floating">
                                    <input ng-model="kichCo.ten" type="text" name="ten" class="form-control"
                                           id="floatingKichCo"
                                           placeholder="Kích cỡ"/>
                                    <label for="floatingKichCo">Kích cỡ</label>
                                </div>
                            </div>
                            <div class="modal-footer">
                                <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Đóng</button>
                                <button ng-click="addKichCo()" type="submit" class="btn btn-primary"
                                        onclick="if(confirm('Bạn có chắc chắn muốn thêm không?')==true){}else{return false; }">
                                    Xác nhận
                                </button>
                            </div>
                        </form>
                        <!-- End floating Labels Form -->
                    </div>
                </div>
            </div>
            <%--thêm nhanh màu sắc--%>
            <div class="modal" id="exampleModalMauSac" tabindex="-1">
                <div class="modal-dialog modal-dialog-scrollable">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h5 class="modal-title">Thêm dữ liệu</h5>
                            <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                        </div>
                        <form method="post" class="row g-3">
                            <div class="modal-body">
                                <div class="form-floating">
                                    <input ng-model="mauSac.ten" type="text" name="ten" class="form-control"
                                           id="floatingMauSac"
                                           placeholder="Màu sắc"/>
                                    <label for="floatingMauSac">Màu sắc</label>
                                </div>
                            </div>
                            <div class="modal-footer">
                                <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Đóng</button>
                                <button ng-click="addMauSac()" type="submit" class="btn btn-primary"
                                        onclick="if(confirm('Bạn có chắc chắn muốn thêm không?')==true){}else{return false; }">
                                    Xác nhận
                                </button>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<script src="https://cdn.jsdelivr.net/npm/jquery@3.5.0/dist/jquery.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/select2@4.1.0-rc.0/dist/js/select2.min.js"></script>
<script src="../../../../js/sanPham/quanLySanPham.js"></script>
<script type="text/javascript">
    // Lấy giá trị từ tất cả các ô input và textarea, và lưu chúng vào localStorage
    function saveInputValues() {
        var allInputs = document.querySelectorAll('input, textarea');

        for (var i = 0; i < allInputs.length; i++) {
            var inputId = allInputs[i].id;
            var inputValue = allInputs[i].value;

            localStorage.setItem(inputId, inputValue);
        }
    }

    // Khôi phục giá trị từ localStorage vào các ô input và textarea
    window.onload = function () {
        var allInputs = document.querySelectorAll('input, textarea');

        for (var i = 0; i < allInputs.length; i++) {
            var inputId = allInputs[i].id;
            var savedValue = localStorage.getItem(inputId);

            if (savedValue) {
                allInputs[i].value = savedValue;
            }
        }
    }

    function clearLocalStorage() {
        localStorage.clear();
    }

    $(document).ready(function () {
        $('#chatLieu').select2({
            theme: "bootstrap-5"
        });
        $('#kieuDang').select2({
            theme: 'bootstrap-5'
        });
        $('#danhMuc').select2({
            theme: 'bootstrap-5'
        });
        $('#size').select2({
            theme: 'bootstrap-5'
        });
        $('#mauSac').select2({
            theme: 'bootstrap-5'
        });

    });

    function myfunction(data) {
        if (confirm("Bạn có muốn cập nhật trạng thái không ?") == true) {
            window.location.href = '/admin/san-pham/stop-ctsp/' + data.idctsp + "?idSP=" + data.idsp + "&tt=" + data.trangThai;
        } else {
            alert('Cập nhật thất bại!')
            return;
        }
    }

    function checkMauSacKichCo() {
        let check = '${checkError}';
        console.log(check);
        if (check !== null) {
            alert('Thêm dữ liệu thành công.');
            return true;
        } else {
            alert('Thêm thất bại! Dữ liệu đã tồn tại.');
            return false;
        }
    }
    if(${success != null}){
        Swal.fire({
            title: "thêm mới!",
            text: "${success}!",
            icon: "success"
        });
    }
</script>
</body>
</html>