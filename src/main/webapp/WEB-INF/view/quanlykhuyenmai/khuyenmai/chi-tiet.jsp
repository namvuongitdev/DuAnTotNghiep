<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<div class="row">
    <div class="container">
        <div class="row" style="margin-bottom: 25px">
            <form class="row" action="/admin/khuyen-mai/filter-san-pham-khuyen-mai/${dataKhuyenMai.id}" method="get"
                  modelAttribute="${sanPhamAsKhuyeMai}">
                <div class="col-sm-3" style="margin-top: 25px">
                    <input type="text" class="form-control" name="tenSanPham" id="tenSanPhamKhuyenMai"
                           placeholder="tìm kiếm" value="${sanPhamKhuyenMaiFilter.tenSanPham}"/>
                </div>
                <div class="col-sm-2">
                    <label for="loaiGiamGiaSanPhamKhuyenMai">Loại giảm giá</label>
                    <select class="form-select" name="loaiGiamGia" id="loaiGiamGiaSanPhamKhuyenMai">
                        <option value="">
                            Tất cả
                        </option>
                        <option value="true" ${sanPhamKhuyenMaiFilter.loaiGiamGia == true ? "selected" : ""}>
                            %
                        </option>
                        <option value="false" ${sanPhamKhuyenMaiFilter.loaiGiamGia == false ? "selected" : ""}>
                            VND
                        </option>
                    </select>
                </div>
                <div class="col-sm-2">
                    <label for="trangThaiSanPhamKhuyenMai">Trạng thái</label>
                    <select class="form-select" name="trangThai" id="trangThaiSanPhamKhuyenMai">
                        <option value="">
                            Tất cả
                        </option>
                        <option value="1"  ${sanPhamKhuyenMaiFilter.trangThai == 1 ? "selected" : ""}>
                            Kích hoạt
                        </option>
                        <option value="0"  ${sanPhamKhuyenMaiFilter.trangThai == 0 ? "selected" : ""}>
                            Ngừng kích hoạt
                        </option>
                    </select>
                </div>
                <div class="col-sm-3" style="margin-top: 25px; display: flex">
                    <button class="btn btn-primary" style="margin-right: 6px">Tìm kiếm</button>
                    <a href="/admin/khuyen-mai/detail?id=${dataKhuyenMai.id}" class="btn btn-warning">Làm mới</a>
                </div>
                <c:if test="${dataKhuyenMai.trangThai == 1 || dataKhuyenMai.trangThai == 2}">
                    <div class="col-sm-2" style="margin-top: 26px">
                        <a class="btn btn-primary"
                           name="1" onclick="getSanPham(this.name)">
                            Thêm sản phẩm
                        </a>
                    </div>
                </c:if>
            </form>
        </div>
        <div>
            <table class="table">
                <thead>
                <tr>
                    <th scope="col">Tên sản phẩm</th>
                    <th scope="col">Mã sản phẩm</th>
                    <th scope="col">Giảm giá</th>
                    <th scope="col">Giá ban đầu</th>
                    <th scope="col">Sau khi giảm</th>
                    <th scope="col">Trạng thái</th>
                    <th scope="col">Thao tác</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${listChiTietKhuyenMai.content}" var="km">
                    <tr>
                        <td>${km.sanPhamKM.ten}</td>
                        <td>${km.sanPhamKM.ma}</td>
                        <c:choose>
                            <c:when test="${km.loaiGiamGia}">
                                <td>${km.mucGiam.intValue()} %</td>
                            </c:when>
                            <c:otherwise>
                                <td><fmt:formatNumber pattern="#,###" value="${km.mucGiam}"/> VND</td>
                            </c:otherwise>
                        </c:choose>
                        <td><fmt:formatNumber pattern="#,###" value="${km.sanPhamKM.giaBan}"/></td>
                        <td><fmt:formatNumber pattern="#,###" value="${km.donGiaSauKhiGiam}"/></td>
                        <td style="${km.trangThai == 1 ? 'color: #03AA28' : 'color:red'}">${km.trangThai == 1 ? 'kích hoạt' : 'ngừng hoạt động'}</td>
                        <td>
                            <a onclick="deleteKhuyenMaiCT(`${km.id}` , `${dataKhuyenMai.id}`)"
                               class="btn btn-danger">Xoá</a>

                            <button type="button" class="btn btn-success" title="chi tiết"
                                    onclick="getSanPhamKhuyenMai(`${km.id}`)">
                                Cập nhập
                            </button>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
</div>
<div class="row">
    <div class="container-fluid mt-5">
        <nav aria-label="Page navigation example">
            <ul class="pagination justify-content-center">
                <li class="page-item ${(listChiTietKhuyenMai.number+1)<=1?"disabled":""}"><a class="page-link"
                                                                                             href="${uri}${(listChiTietKhuyenMai.number + 1) - 1}"><</a>
                </li>
                <c:forEach begin="1" end="${listChiTietKhuyenMai.getTotalPages()}" var="i">
                    <li class="page-item"><a
                            class="page-link ${i == (listChiTietKhuyenMai.number + 1) ? 'active ' : ''}"
                            href="${uri}${i}">${i}</a></li>
                </c:forEach>
                <li class="page-item ${listChiTietKhuyenMai.number + 1 >= listChiTietKhuyenMai.getTotalPages() ? "disabled" : ""}">
                    <a
                            class="page-link"
                            href="${uri}${(listChiTietKhuyenMai.number+1) + 1}">></a>
                </li>
            </ul>
        </nav>
    </div>
</div>
<div id="modalSanPhamKhuyenMai" class="modal">
    <div class="modal-content-1">
        <span class="close" id="close_sanPhamKhuyenMai">&times;</span>
        <div class="container">
            <div class="row">
                <div class="col-sm-6">
                    <div class="mb-3 form-floating">
                        <input type="text" class="form-control" id="tenSanPham" disabled>
                        <label for="tenSanPham">Tên Sản phẩm</label>
                    </div>
                </div>
                <div class="col-sm-6">
                    <div class="mb-3 form-floating">
                        <input type="text" class="form-control" id="maSanPham" disabled>
                        <label for="maSanPham">Mã Sản phẩm</label>
                    </div>
                </div>
            </div>
            <form id="updateSanPhamKhuyenMai" method="post" modelAttribute="${sanPhamKhuyenMai}">
                <div class="row">
                    <div class="col-sm-6">
                        <input type="radio" class="btn-check loaiGiamGia" name="loaiGiamGia"
                               id="success-outlined__1" autocomplete="off" value="true"
                               onchange="loaiGiamGiaUpdate(this.value)">
                        <label class="btn btn-outline-secondary" for="success-outlined__1">%</label>

                        <input type="radio" class="btn-check loaiGiamGia" name="loaiGiamGia"
                               onchange="loaiGiamGiaUpdate(this.value)"
                               id="danger-outlined__1" autocomplete="off" value="false">
                        <label class="btn btn-outline-secondary" for="danger-outlined__1">VND</label>
                    </div>
                    <div class="col-sm-6">
                        <div class="mb-3 form-floating">
                            <input type="number" class="form-control" name="mucGiam" id="mucGiamSanPham">
                            <label for="mucGiamSanPham">Mức giảm</label>
                        </div>
                    </div>
                </div>
                <div>
                    <button class="btn btn-primary" onclick="return validateUpdate()">Lưu</button>
                </div>
            </form>
        </div>
    </div>
</div>
