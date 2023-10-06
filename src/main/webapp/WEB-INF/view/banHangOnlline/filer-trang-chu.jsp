<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<div class="col l-3">
    <select name="danhMuc" id="danhMuc" class="form-select">
        <option value="">Tất cả danh mục</option>
        <c:forEach items="${listDanhMuc}" var="danhMuc">
            <option value="${danhMuc.id}" ${filter.danhMuc == danhMuc.id ? 'selected' : ''}>
                    ${danhMuc.ten}
            </option>
        </c:forEach>
    </select>
</div>
<div class="col l-3">
    <select name="chatLieu" id="chatLieu" class="form-select">
        <option value="">Tất cả chất liệu</option>
        <c:forEach items="${listChatLieu}" var="chatLieu">
            <option value="${chatLieu.id}" ${filter.chatLieu == chatLieu.id ? 'selected' : ''}>
                    ${chatLieu.ten}
            </option>
        </c:forEach>
    </select>
</div>
<div class="col l-3">
    <select name="kieuDang" id="kieuDang" class="form-select">
        <option value="">Tất cả kiểu dáng</option>
        <c:forEach items="${listFormDang}" var="kieuDang" >
            <option value="${kieuDang.id}" ${filter.kieuDang == kieuDang.id ? 'selected' : ''}>
                    ${kieuDang.ten}
            </option>
        </c:forEach>
    </select>
</div>
<div class="col l-3">
    <select name="trangThai" id="trangThai" class="form-select">
        <option value="">Tất cả trang thái</option>
        <option value="0" ${filter.trangThai == 0 ? 'selected' : ''}>
            Kinh doanh
        </option>
        <option value="1" ${filter.trangThai == 1 ? 'selected' : ''}>
            Ngừng Kinh doanh
        </option>
    </select>
</div>
<div class="col l-3">
    <select name="sapXep" id="sapXep" class="form-select">
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
    <button class="btn btn-primary" id="timKiem">Tìm kiếm</button>
</div>
<div class="col l-3">
    <a class="btn btn-primary" href="/san-pham/hien-thi">
        <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-arrow-clockwise" viewBox="0 0 16 16">
            <path fill-rule="evenodd" d="M8 3a5 5 0 1 0 4.546 2.914.5.5 0 0 1 .908-.417A6 6 0 1 1 8 2v1z"/>
            <path d="M8 4.466V.534a.25.25 0 0 1 .41-.192l2.36 1.966c.12.1.12.284 0 .384L8.41 4.658A.25.25 0 0 1 8 4.466z"/>
        </svg>
    </a>
</div>

