<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css"
          rel="stylesheet" integrity="sha384-9ndCyUaIbzAi2FUVXJi0CjmCapSmO7SnpJef0486qhLnuZ2cdeRhO02iuK6FUUVM"
          crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"
            integrity="sha384-geWF76RCwLtnZ8qwWowPQNguL3RmwHVBC9FhGdlKrxdiJJigb/j/68SIy3Te4Bkz"
            crossorigin="anonymous"></script>
</head>
<style>
    table {
        border-collapse: collapse;
        width: 100%;
    }

    th, td {
        padding: 8px;
        text-align: left;
        border-bottom: 1px solid #ddd;
    }

    tr:hover {
        background-color: #f5f5f5;
    }

</style>
<body>
<%--navbar--%>
<jsp:include page="../../sidebar/navbar.jsp"/>
<%--sidebar--%>
<div class="container-fluid">
    <div class="row flex-nowrap">
        <jsp:include page="../../sidebar/sidebar.jsp"/>
        <div class="col py-3">
            <%-- Thêm sản phẩm --%>
            <div class="container" style="width: 70%">
                <div class="row">
                    <p class="text-center">THÊM SẢN PHẨM</p>
                    <form action="/san-pham/add?id=${sp.id}" method="post" modelAttribute="${sanPham}">
                        <div class="mb-3 form-floating">
                            <input type="text" class="form-control" name="ten" id="ten"
                                   value="${sp.ten}">
                            <label for="ten">Tên Sản Phẩm</label>
                        </div>
                        <div class="mb-3 form-floating">
                            <textarea name="moTa" class="form-control" id="moTa"
                            >${sp.moTa}</textarea>
                            <label for="moTa">Mô Tả</label>
                        </div>
                        <div class="row">
                            <div class="col l-3">
                                <div class="mb-3 form-floating">
                                    <input type="text" class="form-control" name="giaNhap" id="giaNhap"
                                           value="${sp.giaNhap}">
                                    <label for="giaNhap">Giá Nhập</label>
                                </div>
                            </div>
                            <div class="col l-3">
                                <div class="mb-3 form-floating">
                                    <input type="text" class="form-control" name="giaBan" id="giaBan"
                                           value="${sp.giaBan}">
                                    <label for="giaBan">Giá Bán</label>
                                </div>
                            </div>
                            <div class="col l-3 form-outline" style="width: 22rem;">
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
                        <div class="row">
                            <div class="col l-3">
                                <div class="form-floating">
                                    <select name="chatLieu" class="form-select" id="chatLieu">
                                        <c:forEach items="${listChatLieu}" var="chatLieu">
                                            <option value="${chatLieu.id}" ${sp.chatLieu.id == chatLieu.id ? 'selected' : '' }>${chatLieu.ten}</option>
                                        </c:forEach>
                                    </select>
                                    <label for="chatLieu">Chất Liệu</label>
                                </div>
                            </div>
                            <div class="col l-3">
                                <div class="form-floating">
                                    <select name="kieuDang" class="form-select" id="kieuDang">
                                        <c:forEach items="${listFromDang}" var="dang">
                                                <option value="${dang.id}" ${sp.formDang.id == dang.id ? 'selected' : '' }>${dang.ten}</option>
                                        </c:forEach>
                                    </select>
                                    <label for="kieuDang">Kiểu Dáng</label>
                                </div>
                            </div>
                            <div class="col l-3">
                                <div class="form-floating">
                                <select name="danhMuc" class="form-select   " id="danhMuc">
                                    <c:forEach items="${listDanhMuc}" var="danhMuc">
                                        <option value="${danhMuc.id}">
                                                ${danhMuc.ten}
                                        </option>
                                    </c:forEach>
                                </select>
                                    <label for="danhMuc">Danh Mục</label>
                                </div>
                            </div>
                        </div>
                        <br>
                        <button class="btn btn-primary">Xác nhận
                        </button>
                        <a href="/san-pham/new" class="btn btn-warning">Làm Mới</a>
                    </form>
                </div>
                <%-- hiển thị chi tiết sản phẩm--%>
                <div class="row">
                    <p class="text-center">THÊM CHI TIẾT SẢN PHẨM</p>
                    <div class="row">
                        <table>
                            <thead>
                            <tr>
                                <th scope="col">Kích cỡ</th>
                                <th scope="col">Màu sắc</th>
                                <th scope="col">Danh mục</th>
                                <th scope="col">Số lượng</th>
                                <th scope="col">Thao tác</th>
                            </tr>
                            </thead>
                            <tbody>

                            <c:forEach items="${listChiTietSanPhamBySP}" var="ctsp">
                                <form action="/chi-tiet-san-pham/update-chi-tiet-san-pham?idCTSP=${ctsp.id}&idSP=${sp.id}"
                                      method="post" modelAttribute="${chiTietSanPham}">
                                    <tr>
                                        <td>
                                            <select name="size">
                                                <c:forEach items="${listKichCo}" var="kichCo">
                                                    <option value="${kichCo.id}" ${kichCo.id == ctsp.size.id ? 'selected' : ''}>
                                                            ${kichCo.ten}
                                                    </option>
                                                </c:forEach>
                                            </select>
                                        </td>
                                        <td>
                                            <select name="mauSac">
                                                <c:forEach items="${listMuaSac}" var="mauSac">
                                                    <option value="${mauSac.id}" ${mauSac.id == ctsp.mauSac.id ? 'selected' : ''}>
                                                            ${mauSac.ten}
                                                    </option>
                                                </c:forEach>
                                            </select>
                                        </td>
                                        <td><input type="number" value="${ctsp.soLuong}" min="1" name="soLuong"
                                                   style="width: 50px ; text-align: center"></td>
                                        <td>
                                            <button onclick="if(confirm('Bạn có muốn update không')){
                                                alert('update thành công')
                                                return true;
                                            }else{
                                                return false;
                                            }"
                                                    class="btn btn-secondary">
                                                Update
                                            </button>
                                        </td>
                                        <td>
                                            <a name="/chi-tiet-san-pham/add-anh?idCTSP=${ctsp.id}" type="button"
                                               class="btn btn-secondary"
                                               data-bs-toggle="modal"
                                               data-bs-target="#exampleModalAnh" onclick="addAnhCTSP(this.name)">
                                                Thêm Ảnh
                                            </a>
                                        </td>
                                        <td>
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
                    <div class="row text-center">
                        <div class="col l-3">
                            <a type="button" class="btn btn-secondary" data-bs-toggle="modal"
                               data-bs-target="#exampleModal">
                                Thêm
                            </a>
                        </div>
                    </div>
                    <%--                    Hiển thị ảnh chi tiết sản phẩm--%>
                    <div class="row" style="margin-bottom: 30px">
                        <p class="text-center">Ảnh</p>
                        <div class="row">
                            <c:forEach items="${listAnhChiTietSanPham_id}" var="anh">
                                <div class="col l-3 ">
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
    </div>
</div>
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
                                        <select name="size" class="form-control">
                                            <c:forEach items="${listKichCo}" var="kichCo">
                                                <option value="${kichCo.id}">${kichCo.ten}</option>
                                            </c:forEach>
                                        </select>
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="mb-3">
                                        <label>Màu sắc:</label>
                                        <select name="mauSac" class="form-control">
                                            <c:forEach items="${listMuaSac}" var="mauSac">
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
                                        <input type="number" value="1" min="1" name="soLuong" style="width: 20%">
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
</div>
</div>
<script type="text/javascript">
    function addAnhCTSP(id) {
        document.getElementById("themAnh").innerHTML = `<form action=` + id + ` method="post" enctype="multipart/form-data">`
            + `<input type='file' name='file'>` + `  <button class='btn btn-primary'>Xác Nhận</button>` + `</form>`}
</script>
</body>
</html>