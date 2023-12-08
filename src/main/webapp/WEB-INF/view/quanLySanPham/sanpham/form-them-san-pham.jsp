<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<form:form action="/admin/san-pham/add?id=${sp.id}" method="post" modelAttribute="sanPham"
           id="yourForm" onsubmit="return false;">
    <div class="row">
        <div class="col-6">
            <div class="mb-3">
                <label for="ten" class="form-label ">Tên sản phẩm <span
                        style="color: red">(*)</span></label>
                <input type="text" class="form-control" name="ten" id="ten" value="${sp.ten}">
                <form:errors path="ten" cssStyle="color: red"/>
            </div>
            <div class="mb-3">
                <label for="gioiTinh" class="form-label">Giới tính <span
                        style="color: red">(*)</span></label>
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
                <label for="giaBan" class="form-label">Giá bán <span
                        style="color: red">(*)</span></label>
                <input type="text" class="form-control" name="giaBan" id="giaBan"
                       value="${sp.giaBan}">
                <form:errors path="giaBan" cssStyle="color: red"/>
            </div>
            <div class="mb-3">
                <label for="moTa" class="form-label">Mô tả <span
                        style="color: red">(*)</span></label>
                <textarea name="moTa" class="form-control" id="moTa">${sp.moTa}</textarea>
                <form:errors path="moTa" cssStyle="color: red"/>
            </div>
        </div>
        <div class="col-6">
            <div class="row mb-3">
                <div class="col-11">
                    <label for="chatLieu" class="form-label">Chất liệu <span style="color: red">(*)</span></label>
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
                    <label for="kieuDang" class="form-label">Kiểu dáng <span style="color: red">(*)</span></label>
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
                    <label for="danhMuc" class="form-label">Danh mục <span style="color: red">(*)</span></label>
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
                <label for="trangThai" class="form-label">Trạng Thái <span style="color: red">(*)</span></label>
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
        <button class="btn btn-success" onclick="add(event)">Xác nhận
            </c:if>
            <c:if test="${sp.id != null}">
            <button class="btn btn-primary" onclick="update(event)">Cập nhật
                </c:if>
            </button>
            <c:if test="${sp.id == null}">
            <a href="/admin/san-pham/new" class="btn btn-warning" onclick="clearLocalStorage()">Làm
                Mới</a>
            </c:if>
    </div>
</form:form>