<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html>
<head>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css"
          rel="stylesheet" integrity="sha384-9ndCyUaIbzAi2FUVXJi0CjmCapSmO7SnpJef0486qhLnuZ2cdeRhO02iuK6FUUVM"
          crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"
            integrity="sha384-geWF76RCwLtnZ8qwWowPQNguL3RmwHVBC9FhGdlKrxdiJJigb/j/68SIy3Te4Bkz"
            crossorigin="anonymous"></script>
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
            <div class="pagetitle">
                <h3>${title}</h3>
                <nav>
                    <ol class="breadcrumb">
                        <li class="breadcrumb-item"><a href="/trangchu" style="text-decoration: none; color: black">Trang chủ</a></li>
                        <li class="breadcrumb-item">Quản lý sản phẩm</li>
                        <li class="breadcrumb-item"><a href="/san-pham/hien-thi" style="text-decoration: none; color: black">Sản phẩm</a></li>
                        <li class="breadcrumb-item active" >${title}</li>
                    </ol>
                </nav>
            </div><!-- End Page Title -->
            <section class="section">
                <div class="card">
                    <div class="card-body row">
                        <h5 class="card-title">Thông tin sản phẩm</h5>
                        <br><br>
                        <form action="/san-pham/add?id=${sp.id}" method="post" modelAttribute="${sanPham}">
                            <div class="row">
                                <div class="col-6">
                                    <div class="mb-3 form-floating">
                                        <input type="text" class="form-control" name="ten" id="ten"
                                               value="${sp.ten}">
                                        <label for="ten">Tên Sản Phẩm</label>
                                        <form:errors path="ten" cssStyle="color: red"/>
                                    </div>
                                    <div class="mb-3 form-floating">
                                        <input type="text" class="form-control" name="giaNhap" id="giaNhap"
                                               value="${sp.giaNhap}">
                                        <label for="giaNhap">Giá Nhập</label>
                                        <form:errors path="giaNhap" cssStyle="color: red"/>
                                    </div>
                                    <div class="mb-3 form-floating">
                                        <input type="text" class="form-control" name="giaBan" id="giaBan"
                                               value="${sp.giaBan}">
                                        <label for="giaBan">Giá Bán</label>
                                        <form:errors path="giaBan" cssStyle="color: red"/>
                                    </div>
                                    <div class="mb-3 form-floating">
                                        <textarea name="moTa" class="form-control" id="moTa">${sp.moTa}</textarea>
                                        <label for="moTa">Mô Tả</label>
                                        <form:errors path="moTa" cssStyle="color: red"/>
                                    </div>
                                </div>
                                <div class="col-6">
                                    <div class="row mb-3">
                                        <div class="col-11 form-floating">
                                            <select name="chatLieu" class="form-select" id="chatLieu">
                                                <c:forEach items="${listChatLieu}" var="chatLieu">
                                                    <option value="${chatLieu.id}" ${sp.chatLieu.id == chatLieu.id ? 'selected' : '' }>${chatLieu.ten}</option>
                                                </c:forEach>
                                            </select>
                                            <label for="chatLieu">Chất Liệu</label>
                                        </div>
                                        <div class="col-1" style="padding-top: 20px">
                                            <a data-bs-toggle="modal" data-bs-target="#exampleModalChatLieu">
                                                <i class="bi bi-plus-circle"></i>
                                            </a>
                                        </div>
                                    </div>
                                    <div class="row mb-3">
                                        <div class="col-11 form-floating">
                                            <select name="formDang" class="form-select" id="formDang">
                                                <c:forEach items="${listFromDang}" var="dang">
                                                    <option value="${dang.id}" ${sp.formDang.id == dang.id ? 'selected' : '' }>${dang.ten}</option>
                                                </c:forEach>
                                            </select>
                                            <label for="formDang">Kiểu Dáng</label>
                                        </div>
                                        <div class="col-1" style="padding-top: 20px">
                                            <a data-bs-toggle="modal" data-bs-target="#exampleModalKieuDang">
                                                <i class="bi bi-plus-circle"></i>
                                            </a>
                                        </div>
                                    </div>
                                    <div class="row mb-3">
                                        <div class="col-11 form-floating">
                                            <select name="danhMuc" class="form-select   " id="danhMuc">
                                                <c:forEach items="${listDanhMuc}" var="dm">
                                                    <option value="${dm.id}" ${sp.danhMuc.id == dm.id ? 'selected' : '' }>${dm.ten}</option>
                                                </c:forEach>
                                            </select>
                                            <label for="danhMuc">Danh Mục</label>
                                        </div>
                                        <div class="col-1" style="padding-top: 20px">
                                            <a data-bs-toggle="modal" data-bs-target="#exampleModalDanhMuc">
                                                <i class="bi bi-plus-circle"></i>
                                            </a>
                                        </div>
                                    </div>
                                    <div class="mb-3 form-floating">
                                        <select name="trangThai" class="form-select" id="trangThai">
                                            <option value="0" ${sp.trangThai == 0 ? 'selected' : '' }>
                                                Kinh doanh
                                            </option>
                                            <option value="1" ${sp.trangThai == 1 ? 'selected' : '' }>
                                                Ngừng kinh doanh
                                            </option>
                                        </select>
                                        <label for="trangThai">Trạng Thái</label>
                                    </div>
                                </div>
                            </div>
                            <br>
                            <div style="text-align: center">
                                <button class="btn btn-primary">Xác nhận
                                </button>
                                <a href="/san-pham/new" class="btn btn-warning">Làm Mới</a>
                            </div>
                        </form>
                    </div>
                </div><br><br>
                <%-- chi tiết  sản phẩm--%>
                <div class="card">
                    <div class="card-body row">
                        <h5 class="card-title col-9">Thông tin chi tiết sản phẩm</h5>
                        <div class="col l-3">
                            <a type="button" class="btn btn-primary" data-bs-toggle="modal"
                               data-bs-target="#exampleModal">Thêm
                            </a>
                        </div>
                        <br><br>
                        <div class="row">

                            <%--                    Hiển thị ảnh chi tiết sản phẩm--%>
                            <div class="col l-5" style="margin-bottom: 30px">
                                <p class="text-center"><b>Ảnh</b></p>
                                <div class="row">
                                    <c:forEach items="${listAnhChiTietSanPham_id}" var="anh">
                                        <div class="col-6" >
                                            <div>
                                                <img src="/image/${anh.ten}" alt="..." style="width: 200px ; height: 200px">
                                            </div>

                                            <div>
                                                <a href="/chi-tiet-san-pham/remove-anh?idAnh=${anh.id}&idCTSP=${anh.chiTietSanPham.id}&idSP=${anh.chiTietSanPham.sanPham.id} "
                                                   style="margin-right: 10px"> Xoá
                                                </a>
                                                <a href="/san-pham/add-anh-mac-dinh?img=${anh.ten}&idSP=${sp.id}"> Mặc định
                                                </a>
                                            </div>

                                        </div>
                                    </c:forEach>
                                </div>
                            </div>
                            <%--table--%>
                            <div class="col-8" style="padding-left: 50px">
                                <table class="table">
                                    <thead>
                                    <tr style="text-align: center">
                                        <th scope="col" >Kích cỡ
                                            <a data-bs-toggle="modal" data-bs-target="#exampleModalKichCo">
                                                <i class="bi bi-plus-circle"></i>
                                            </a>
                                        </th>
                                        <th scope="col" >Màu sắc
                                            <a data-bs-toggle="modal" data-bs-target="#exampleModalMauSac">
                                                <i class="bi bi-plus-circle"></i>
                                            </a>
                                        </th>
                                        <th scope="col" >Số lượng</th>
                                        <th scope="col" >Trạng thái</th>
                                        <th scope="col" >Thao tác</th>
                                    </tr>
                                    </thead>
                                    <tbody>

                                    <c:forEach items="${listChiTietSanPhamBySP}" var="ctsp">
                                        <form action="/chi-tiet-san-pham/update-chi-tiet-san-pham?idCTSP=${ctsp.id}&idSP=${sp.id}"
                                              method="post" modelAttribute="${chiTietSanPham}">
                                            <tr style="text-align: center">
                                                <td>
                                                    <select name="size" class="form-select">
                                                        <c:forEach items="${listKichCo}" var="kichCo">
                                                            <option value="${kichCo.id}" ${kichCo.id == ctsp.size.id ? 'selected' : ''}>
                                                                    ${kichCo.ten}
                                                            </option>
                                                        </c:forEach>
                                                    </select>
                                                </td>
                                                <td>
                                                    <select name="mauSac" class="form-select">
                                                        <c:forEach items="${listMauSac}" var="mauSac">
                                                            <option value="${mauSac.id}" ${mauSac.id == ctsp.mauSac.id ? 'selected' : ''}>
                                                                    ${mauSac.ten}
                                                            </option>
                                                        </c:forEach>
                                                    </select>
                                                </td>
                                                <td style="width: 110px ;">
                                                    <input type="number" class="form-control" value="${ctsp.soLuong}" min="1" name="soLuong">
                                                </td>
                                                <td>
                                                    <button  style="--bs-btn-padding-y: .56rem; --bs-btn-padding-x: .5rem; --bs-btn-font-size: .75rem;" type="button"
                                                             onclick="if(confirm('Bạn có chắc chắn muốn ngừng kinh doanh không?')){window.location.href = '/san-pham/stop-ctsp/${ctsp.id}?idSP=${ctsp.sanPham.id}';}
                                                                     else{alert('Ngừng kinh doanh thất bại!')}"
                                                             class="${ctsp.trangThai == 0 ? 'btn btn-danger' : 'btn btn-success'}">
                                                            ${ctsp.trangThai == 0 ? 'Ngừng kinh doanh' : 'Kinh doanh'
                                                            }
                                                    </button>
                                                </td>
                                                <td style="text-align: center">
                                                    <button class="btn btn-secondary" onclick="if(confirm('Bạn có muốn update không')){alert('update thành công')
                                                        return true;
                                                        }else{
                                                        return false;}">Sửa
                                                    </button>
                                                    <a name="/chi-tiet-san-pham/add-anh?idCTSP=${ctsp.id}" type="button"
                                                       class="btn btn-secondary"
                                                       data-bs-toggle="modal"
                                                       data-bs-target="#exampleModalAnh" onclick="addAnhCTSP(this.name)">
                                                        Thêm ảnh
                                                    </a>
                                                    <a href="/chi-tiet-san-pham/anh/${ctsp.id}?idSP=${ctsp.sanPham.id}"
                                                       class="btn btn-secondary">
                                                        Xem
                                                    </a>
                                                </td>
                                            </tr>
                                        </form>
                                    </c:forEach>

                                    </tbody>
                                </table>
                            </div>

                            <%--                    thêm ảnh mặc đinh cho sản phẩm--%>
                            <div class="row text-center">
                                <p>Ảnh mặc định của sản phẩm</p>
                                <div class="col l-3">
                                    <img src="/image/${sp.img}" alt="..." style="width: 200px ; height: 200px">
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </section>
            <%--thêm chi tiết sản phẩm--%>
            <div class="modal fade" id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h5 class="modal-title" id="exampleModalLabel">Thêm chi tiết sản phẩm</h5>
                            <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                        </div>
                        <div class="modal-body">
                            <div class="row">
                                <div class="col l-3">
                                    <form action="/chi-tiet-san-pham/add?id=${sp.id}" method="post"
                                          enctype="multipart/form-data"
                                          modelAttribute="${chiTietSanPham}">
                                        <div class="row">
                                            <div class="row">
                                                <div class="mb-3">
                                                    <label>Kích cỡ :</label>
                                                    <select name="size" class="form-select">
                                                        <c:forEach items="${listKichCo}" var="kichCo">
                                                            <option value="${kichCo.id}">${kichCo.ten}</option>
                                                        </c:forEach>
                                                    </select>
                                                </div>
                                            </div>
                                            <div class="row">
                                                <div class="mb-3">
                                                    <label>Màu sắc:</label>
                                                    <select name="mauSac" class="form-select">
                                                        <c:forEach items="${listMauSac}" var="mauSac">
                                                            <option value="${mauSac.id}">
                                                                    ${mauSac.ten}
                                                            </option>
                                                        </c:forEach>
                                                    </select>
                                                </div>
                                            </div>
                                            <div class="row">
                                                <div class="mb-3">
                                                    <label>Số lượng :</label>
                                                    <input type="number" class="form-control" value="1" min="1" name="soLuong" style="width: 20%">
                                                </div>
                                            </div>
                                        </div>
                                        <div class="text-center">
                                            <button class="btn btn-primary">Xác Nhận</button>
                                        </div>
                                    </form>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <%--    thêm ảnh sản phẩm --%>
            <div class="modal fade" id="exampleModalAnh" tabindex="-1" aria-labelledby="exampleModalLabelAnh"
                 aria-hidden="true">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h5 class="modal-title" id="exampleModalLabelAnh">Thêm ảnh</h5>
                            <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                        </div>
                        <div class="modal-body" id="themAnh">

                        </div>
                    </div>
                </div>
            </div>
            <%--thêm nhanh chất liệu--%>
            <div class="modal fade" id="exampleModalChatLieu" tabindex="-1">
                <div class="modal-dialog modal-dialog-scrollable">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h5 class="modal-title">Thêm dữ liệu</h5>
                            <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                        </div>
                        <form:form method="post" action="/san-pham/modal-add-chat-lieu" modelAttribute="chatLieu" class="row g-3">
                            <div class="modal-body">
                                <div class="form-floating">
                                    <form:input type="text" path="ten" class="form-control" id="floatingName" placeholder="Chất liệu"/>
                                    <form:label for="floatingName" path="ten">Chất liệu</form:label>
                                    <form:errors path="ten" cssStyle="color: red"/>
                                </div>
                            </div>
                            <div class="modal-footer">
                                <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Đóng</button>
                                <form:button type="submit" class="btn btn-primary" onclick="if(confirm('Bạn có chắc chắn muốn thêm không?')==true){ }else{ alert('Thêm thất bại');return false; }">
                                    Xác nhận
                                </form:button>
                            </div>
                        </form:form><!-- End floating Labels Form -->
                    </div>
                </div>
            </div><!-- End Modal Dialog Scrollable-->
            <%--thêm nhanh danh mục--%>
            <div class="modal fade" id="exampleModalDanhMuc" tabindex="-1">
                <div class="modal-dialog modal-dialog-scrollable">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h5 class="modal-title">Thêm dữ liệu</h5>
                            <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                        </div>
                        <form:form method="post" action="/san-pham/modal-add-danh-muc" modelAttribute="danhMuc" class="row g-3">
                            <div class="modal-body">
                                <div class="form-floating">
                                    <form:input type="text" path="ten" class="form-control" id="floatingName" placeholder="Tên danh mục"/>
                                    <form:label for="floatingName" path="ten">Tên danh mục</form:label>
                                    <form:errors path="ten" cssStyle="color: red"/>
                                </div>
                            </div>
                            <div class="modal-footer">
                                <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Đóng</button>
                                <form:button type="submit" class="btn btn-primary" onclick="if(confirm('Bạn có chắc chắn muốn thêm không?')==true){ }else{ alert('Thêm thất bại');return false; }">
                                    Xác nhận
                                </form:button>
                            </div>
                        </form:form><!-- End floating Labels Form -->
                    </div>
                </div>
            </div><!-- End Modal Dialog Scrollable-->
            <%--thêm nhanh kiểu dáng--%>
            <div class="modal fade" id="exampleModalKieuDang" tabindex="-1">
                <div class="modal-dialog modal-dialog-scrollable">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h5 class="modal-title">Thêm dữ liệu</h5>
                            <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                        </div>
                        <form:form method="post" action="/san-pham/modal-add-kieu-dang" modelAttribute="kieuDang" class="row g-3">
                            <div class="modal-body">
                                <div class="form-floating">
                                    <form:input type="text" path="ten" class="form-control" id="floatingName" placeholder="Kiểu dáng"/>
                                    <form:label for="floatingName" path="ten">Kiểu dáng</form:label>
                                    <form:errors path="ten" cssStyle="color: red"/>
                                </div>
                            </div>
                            <div class="modal-footer">
                                <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Đóng</button>
                                <form:button type="submit" class="btn btn-primary" onclick="if(confirm('Bạn có chắc chắn muốn thêm không?')==true){ }else{ alert('Thêm thất bại');return false; }">
                                    Xác nhận
                                </form:button>
                            </div>
                        </form:form><!-- End floating Labels Form -->
                    </div>
                </div>
            </div><!-- End Modal Dialog Scrollable-->
            <%--thêm nhanh kích cỡ--%>
            <div class="modal fade" id="exampleModalKichCo" tabindex="-1">
                <div class="modal-dialog modal-dialog-scrollable">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h5 class="modal-title">Thêm dữ liệu</h5>
                            <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                        </div>
                        <form:form method="post" action="/san-pham/modal-add-size" modelAttribute="size" class="row g-3">
                            <div class="modal-body">
                                <div class="form-floating">
                                    <form:input type="text" path="ten" class="form-control" id="floatingName" placeholder="Kích cỡ"/>
                                    <form:label for="floatingName" path="ten">Kích cỡ</form:label>
                                    <form:errors path="ten" cssStyle="color: red"/>
                                </div>
                            </div>
                            <div class="modal-footer">
                                <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Đóng</button>
                                <form:button type="submit" class="btn btn-primary" onclick="if(confirm('Bạn có chắc chắn muốn thêm không?')==true){ }else{ alert('Thêm thất bại');return false; }">
                                    Xác nhận
                                </form:button>
                            </div>
                        </form:form><!-- End floating Labels Form -->
                    </div>
                </div>
            </div><!-- End Modal Dialog Scrollable-->
            <%--thêm nhanh màu sắc--%>
            <div class="modal fade" id="exampleModalMauSac" tabindex="-1">
                <div class="modal-dialog modal-dialog-scrollable">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h5 class="modal-title">Thêm dữ liệu</h5>
                            <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                        </div>
                        <form:form method="post" action="/san-pham/modal-add-mau-sac" modelAttribute="mauSac" class="row g-3">
                            <div class="modal-body">
                                <div class="form-floating">
                                    <form:input type="text" path="ten" class="form-control" id="floatingName" placeholder="Tên màu"/>
                                    <form:label for="floatingName" path="ten">Tên màu</form:label>
                                    <form:errors path="ten" cssStyle="color: red"/>
                                </div>
                            </div>
                            <div class="modal-footer">
                                <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Đóng</button>
                                <form:button type="submit" class="btn btn-primary" onclick="if(confirm('Bạn có chắc chắn muốn thêm không?')==true){ }else{ alert('Thêm thất bại');return false; }">
                                    Xác nhận
                                </form:button>
                            </div>
                        </form:form><!-- End floating Labels Form -->
                    </div>
                </div>
            </div><!-- End Modal Dialog Scrollable-->
        </div>
    </div>
</div>
<script type="text/javascript">
    function addAnhCTSP(id) {
        document.getElementById("themAnh").innerHTML = `<form action=` + id + ` method="post" enctype="multipart/form-data">`
            + `<input type='file' name='file'>` + `  <button class='btn btn-primary'>Xác Nhận</button>` + `</form>`}
</script>
</body>
</html>
