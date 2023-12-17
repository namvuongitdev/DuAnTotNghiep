<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ page trimDirectiveWhitespaces="true" %>
<!-- Bordered Tabs -->
<%--tìm kiếm--%>
<div>
    <div class="row">
        <div class="col-3">
            <span>Tìm kiếm</span>
            <form action="" method="post">
                <div class="input-group">
                    <input type="text" class="form-control" name="search" placeholder="Tìm kiếm"
                           value="${filter.search}" aria-label="Tìm kiếm"/>
                </div>
            </form>
        </div>

        <div class="col-2">
            <span>Trạng thái</span>
            <select name="trangThai" id="trangThai" class="form-select">
                <option value="">Tất cả</option>
                <option value="0" ${filter.trangThai == 0 ? 'selected' : ''}>
                    Làm việc
                </option>
                <option value="1" ${filter.trangThai == 1 ? 'selected' : ''}>
                    Nghỉ việc
                </option>
            </select>
        </div>
        <div class="col-2">
            <span>Giới tính</span>
            <select name="gioiTinh" id="gioiTinh" class="form-select">
                <option value="">Tất cả</option>
                <option value="true" ${filter.gioiTinh == true ? 'selected' : ''}>
                    Nam
                </option>
                <option value="false" ${filter.gioiTinh == false ? 'selected' : ''}>
                    Nữ
                </option>
            </select>
        </div>
        <div class="col-4" style="margin-top: 22px">
            <div>
                <button type="submit" class="btn btn-primary">Tìm kiếm</button>
                <a class="btn btn-warning" href="/admin/nhan-vien/hien-thi">Làm mới</a>
                <a class="btn btn-success" href="/admin/nhan-vien/view-add">Tạo mới</a>
            </div>
        </div>
    </div>
</div>
<br><!-- End Bordered Tabs -->

<%--....--%>