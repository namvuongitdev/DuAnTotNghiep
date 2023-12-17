<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ page trimDirectiveWhitespaces="true" %>
<!-- Bordered Tabs -->
<%--tìm kiếm--%>
<div>
    <div class="row">
        <span>Tìm kiếm</span>
        <div class="col-5">
            <form action="" method="post">
                <div class="input-group">
                    <input type="text" class="form-control" name="search" placeholder="Tìm kiếm" value="${filter.search}" aria-label="Tìm kiếm"/>
                </div>
            </form>
        </div>
        <div class="col-7">
            <button type="submit" class="btn btn-primary">Tìm kiếm</button>
            <a class="btn btn-warning" href="/admin/khach-hang/hien-thi">Làm mới</a>
            <a class="btn btn-success" href="/admin/khach-hang/view-add">Tạo mới</a>
        </div>
    </div>
    <br>
</div><!-- End Bordered Tabs -->

<%--....--%>