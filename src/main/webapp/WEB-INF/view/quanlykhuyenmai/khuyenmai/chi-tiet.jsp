<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<div class="row">
    <div style="float: left">
        <button style="float: right" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#extraLargeModal"
                name="1" onclick="getSanPham(this.name)">
            Thêm sản phẩm
        </button>
    </div>
    <div>
        <table>
            <thead>
            <tr>
                <th scope="col">Tên sản phẩm</th>
                <th scope="col">Mã sản phẩm</th>
                <th scope="col">Giảm giá</th>
                <th scope="col">Giá ban đầu</th>
                <th scope="col">Sau khi giảm</th>
                <th scope="col">Trạng thái</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${listChiTietKhuyenMai.content}" var="km">
                <tr onclick="getSanPhamKhuyenMai(`${km.id}`)">
                    <td>${km.tenSanPham}</td>
                    <td>${km.maSanPham}</td>
                    <c:choose>
                        <c:when test="${km.loaiGiamGia}">
                            <td>${km.mucGiam.intValue()} %</td>
                        </c:when>
                        <c:otherwise>
                            <td><fmt:formatNumber pattern="#,###" value="${km.mucGiam}"/> VND</td>
                        </c:otherwise>
                    </c:choose>
                    <td><fmt:formatNumber pattern="#,###" value="${km.giaBanDau}"/></td>
                    <td><fmt:formatNumber pattern="#,###" value="${km.donGiaKhiGiam}"/></td>
                    <td style="${km.trangThai == 1 ? 'color: #03AA28' : 'color:red'}">${km.trangThai == 1 ? 'kích hoạt' : 'ngừng hoạt động'}</td>
                    <td><c:choose>
                        <c:when test="${km.trangThai == 1}">
                            <a href="/admin/khuyen-mai/update-trang-thai-san-pham?idSPKM=${km.id}&trangThai=${km.trangThai == 1 ? 0 : 1}"
                               class="btn btn-danger">huỷ</a>
                        </c:when>
                        <c:otherwise>
                            <a href="/admin/khuyen-mai/update-trang-thai-san-pham?idSPKM=${km.id}&trangThai=${km.trangThai == 1 ? 0 : 1}"
                               class="btn btn-success">Kích hoạt</a>
                        </c:otherwise>
                    </c:choose></td>
                </tr>
            </c:forEach>
            </tbody>
        </table>

    </div>
</div>
<div class="row">
    <div class="container-fluid mt-5">
        <nav aria-label="Page navigation example">
            <ul class="pagination justify-content-center">
                <li class="page-item ${(listChiTietKhuyenMai.number+1)<=1?"disabled":""}"><a class="page-link"
                                                                                             href="${url}${(listChiTietKhuyenMai.number + 1) - 1}"><</a>
                </li>
                <c:forEach begin="1" end="${listChiTietKhuyenMai.getTotalPages()}" var="i">
                    <li class="page-item"><a
                            class="page-link ${i == (listChiTietKhuyenMai.number + 1) ? 'active ' : ''}"
                            href="${url}${i}">${i}</a></li>
                </c:forEach>
                <li class="page-item ${listChiTietKhuyenMai.number + 1 >= listChiTietKhuyenMai.getTotalPages() ? "disabled" : ""}">
                    <a
                            class="page-link"
                            href="${url}${(listChiTietKhuyenMai.number+1) + 1}">></a>
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
                        <input type="radio" class="btn-check" name="loaiGiamGia"
                               id="success-outlined__1" autocomplete="off" value="true">
                        <label class="btn btn-outline-secondary" for="success-outlined__1">%</label>

                        <input type="radio" class="btn-check" name="loaiGiamGia"
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
                    <button class="btn btn-primary">Lưu</button>
                </div>
            </form>
        </div>
    </div>
</div>