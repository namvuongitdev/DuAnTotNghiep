<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
                    <div class="col-sm-2" style="width: 11%">
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
                            <label for="color">Màu sắc</label>
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
                    <form action="/admin/khuyen-mai/khuyen-mai-san-pham?idKM=${dataKhuyenMai.id}" method="post"
                          modelAttribute="${sanPhamKhuyenMai}">
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
                            <div id="modalKhuyenMai" class="modal">
                                <div class="modal-content-1">
                                    <span class="close" id="close_khuyenMai">&times;</span>
                                    <div class="row">
                                        <div class="col-sm-6" style="width: 25%">
                                            <input type="radio" class="btn-check" name="loaiGiamGia"
                                                   id="success-outlined" autocomplete="off" value="true" checked>
                                            <label class="btn btn-outline-secondary" for="success-outlined">%</label>

                                            <input type="radio" class="btn-check" name="loaiGiamGia"
                                                   id="danger-outlined" autocomplete="off" value="false">
                                            <label class="btn btn-outline-secondary" for="danger-outlined">VND</label>
                                        </div>
                                        <div class="col-sm-6">
                                            <div class="mb-3 form-floating">
                                                <input type="number" class="form-control" name="mucGiam" id="mucGiam">
                                                <label for="mucGiam">Mức giảm</label>
                                            </div>
                                        </div>
                                        <div>
                                            <button class="btn btn-primary">Xác nhận</button>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </table>
                    </form>
                </div>
                <div style="float: right">
                    <button class="btn btn-primary" id="btnKhuyenMai">Xác nhận</button>
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