<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ page trimDirectiveWhitespaces="true" %>
<!-- Bordered Tabs -->
    <%--tìm kiếm--%>
<div>
    <div class="row">
        <form action="" method="post">
            <div class="input-group" style="width: 1100px">
                <input type="text" class="form-control" name="search" placeholder="Tìm kiếm" value="${filter.search}" aria-label="Tìm kiếm"/>
            </div>
        </form>
    </div>
    <br>
    <div class="row">
        <div class="col l-3">
            <span style="color: #03AA28">Danh mục</span>
            <select name="danhMuc" class="form-select">
                <option value="">Tất cả</option>
                <c:forEach items="${listDanhMuc}" var="danhMuc">
                    <option value="${danhMuc.id}" ${filter.danhMuc == danhMuc.id ? 'selected' : ''}>
                            ${danhMuc.ten}
                    </option>
                </c:forEach>
            </select>
        </div>
        <div class="col l-3">
            <span style="color: #03AA28">Chất liệu</span>
            <select name="chatLieu" id="chatLieu" class="form-select">
                <option value="">Tất cả</option>
                <c:forEach items="${listChatLieu}" var="chatLieu">
                    <option value="${chatLieu.id}" ${filter.chatLieu == chatLieu.id ? 'selected' : ''}>
                            ${chatLieu.ten}
                    </option>
                </c:forEach>
            </select>
        </div>
        <div class="col l-3">
            <span style="color: #03AA28">Kiểu dáng</span>
            <select name="kieuDang" id="kieuDang" class="form-select">
                <option value="">Tất cả</option>
                <c:forEach items="${listFromDang}" var="kieuDang" >
                    <option value="${kieuDang.id}" ${filter.kieuDang == kieuDang.id ? 'selected' : ''}>
                            ${kieuDang.ten}
                    </option>
                </c:forEach>
            </select>
        </div>
        <div class="col l-3">
            <span style="color: #03AA28">Màu sắc</span>
            <select name="mauSac" id="mauSac" class="form-select">
                <option value="">Tất cả</option>
                <c:forEach items="${listMauSac}" var="mauSac" >
                    <option value="${mauSac.id}" ${filter.mauSac == mauSac.id ? 'selected' : ''}>
                            ${mauSac.ten}
                    </option>
                </c:forEach>
            </select>
        </div>
    </div><br>
    <%--        row2--%>
    <div class="row">
        <div class="col l-4">
            <span style="color: #03AA28">Kích cỡ</span>
            <select name="kichCo" id="kichCo" class="form-select">
                <option value="">Tất cả</option>
                <c:forEach items="${listKichCo}" var="kichCo" >
                    <option value="${kichCo.id}" ${filter.kichCo == kichCo.id ? 'selected' : ''}>
                            ${kichCo.ten}
                    </option>
                </c:forEach>
            </select>
        </div>
        <div class="col l-4">
            <span style="color: #03AA28">Trạng thái</span>
            <select name="trangThai" id="trangThai" class="form-select">
                <option value="">Tất cả</option>
                <option value="0" ${filter.trangThai == 0 ? 'selected' : ''}>
                    Kinh doanh
                </option>
                <option value="1" ${filter.trangThai == 1 ? 'selected' : ''}>
                    Ngừng Kinh doanh
                </option>
            </select>
        </div>
        <div class="col l-4">
            <span style="color: #03AA28">Sắp xếp theo giá</span>
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
    </div><br>
<div style="text-align: center">
    <button class="btn btn-primary">Tìm kiếm</button>
    <a class="btn btn-warning" href="/san-pham/hien-thi">Làm mới</a>
</div>
    <br>
</div><!-- End Bordered Tabs -->

<%--....--%>


