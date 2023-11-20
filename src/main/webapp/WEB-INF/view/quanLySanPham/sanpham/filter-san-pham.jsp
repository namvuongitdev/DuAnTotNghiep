<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ page trimDirectiveWhitespaces="true" %>
<!-- Bordered Tabs -->
<%--tìm kiếm--%>
<div>

    <div class="row">
        <div class="col l-3">
            <span>Tìm kiếm</span>
            <form action="" method="post">
                <div class="input-group">
                    <input type="text" class="form-control" name="search" placeholder="Tìm kiếm" value="${filter.search}" aria-label="Tìm kiếm"/>
                </div>
            </form>
        </div>
        <div class="col l-3">
            <label for="danhMucFilter">Danh mục</label>
            <select name="danhMuc" id="danhMucFilter" class="form-select">
                <option value="">Tất cả</option>
                <c:forEach items="${listDanhMuc}" var="danhMuc">
                    <option value="${danhMuc.id}" ${filter.danhMuc == danhMuc.id ? 'selected' : ''}>
                            ${danhMuc.ten}
                    </option>
                </c:forEach>
            </select>
        </div>
        <div class="col l-3">
            <label for="chatLieuFilter">Chất liệu</label>
            <select name="chatLieu" id="chatLieuFilter" class="form-select">
                <option value="">Tất cả</option>
                <c:forEach items="${listChatLieu}" var="chatLieu">
                    <option value="${chatLieu.id}" ${filter.chatLieu == chatLieu.id ? 'selected' : ''}>
                            ${chatLieu.ten}
                    </option>
                </c:forEach>
            </select>
        </div>
        <div class="col l-3">
            <label for="kieuDangFilter">Kiểu dáng</label>
            <select name="kieuDang" id="kieuDangFilter" class="form-select">
                <option value="">Tất cả</option>
                <c:forEach items="${listFromDang}" var="kieuDang" >
                    <option value="${kieuDang.id}" ${filter.kieuDang == kieuDang.id ? 'selected' : ''}>
                            ${kieuDang.ten}
                    </option>
                </c:forEach>
            </select>
        </div>
        <div class="col l-3">
            <label for="mauSacFilter">Màu sắc</label>
            <select name="mauSac" id="mauSacFilter" class="form-select">
                <option value="">Tất cả</option>
                <c:forEach items="${listMuaSac}" var="mauSac" >
                    <option value="${mauSac.id}" ${filter.mauSac == mauSac.id ? 'selected' : ''}>
                            ${mauSac.ten}
                    </option>
                </c:forEach>
            </select>
        </div>
    </div><br>
    <%--        row2--%>
    <div class="row">
        <div class="col l-3">
            <label for="kichCoFilter">Kích cỡ</label>
            <select name="kichCo" id="kichCoFilter" class="form-select">
                <option value="">Tất cả</option>
                <c:forEach items="${listKichCo}" var="kichCo" >
                    <option value="${kichCo.id}" ${filter.kichCo == kichCo.id ? 'selected' : ''}>
                            ${kichCo.ten}
                    </option>
                </c:forEach>
            </select>
        </div>
        <div class="col l-3">
            <label for="trangThaiFilter">Trạng thái</label>
            <select name="trangThai" id="trangThaiFilter" class="form-select">
                <option value="">Tất cả</option>
                <option value="0" ${filter.trangThai == 0 ? 'selected' : ''}>
                    Kinh doanh
                </option>
                <option value="1" ${filter.trangThai == 1 ? 'selected' : ''}>
                    Ngừng Kinh doanh
                </option>
            </select>
        </div>
        <div class="col l-3">
            <label for="sapXepFilter">Sắp xếp theo giá</label>
            <select name="sapXep" id="sapXepFilter" class="form-select">
                <option value="">Tất cả</option>
                <option value="ngayTao" ${filter.sapXep == 'ngayTao' ? 'selected' : ''}>Mới nhất</option>
                <option value="price-asc" ${filter.sapXep == 'price-asc' ? 'selected' : ''}>
                    Thứ tự theo giá: thấp đến cao
                </option>
                <option value="price-desc" ${filter.sapXep == 'price-desc' ? 'selected' : ''}>
                    Thứ tự theo giá: cao xuống thấp
                </option>
            </select>
        </div>
        <div class="col l-3">
            <label for="gioiTinhFilter">Giới tính</label>
            <select name="gioiTinh" id="gioiTinhFilter" class="form-select">
                <option value="">Tất cả</option>
                <option value="true" ${filter.gioiTinh == true ? 'selected' : ''}>
                    Dành cho nam
                </option>
                <option value="false" ${filter.gioiTinh == false ? 'selected' : ''}>
                    Dành cho nữ
                </option>
            </select>
        </div>
    </div><br>
    <div style="text-align: center">
        <button class="btn btn-primary">Tìm kiếm</button>
        <a class="btn btn-warning" href="/admin/san-pham/hien-thi">Làm mới</a>
    </div>
    <br>
</div><!-- End Bordered Tabs -->

<%--....--%>