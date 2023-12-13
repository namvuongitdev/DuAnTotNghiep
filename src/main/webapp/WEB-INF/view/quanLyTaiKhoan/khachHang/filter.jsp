<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ page trimDirectiveWhitespaces="true" %>
<!-- Bordered Tabs -->
<%--tìm kiếm--%>
<div>
    <div class="row">
        <div class="col-6">
            <span>Tìm kiếm</span>
            <form action="" method="post">
                <div class="input-group">
                    <input type="text" class="form-control" name="search" placeholder="Tìm kiếm" value="${filter.search}" aria-label="Tìm kiếm"/>
                </div>
            </form>
        </div>
    </div><br>
    <div style="text-align: center">
        <button type="submit" class="btn btn-primary">Tìm kiếm</button>
        <a class="btn btn-warning" href="/admin/khach-hang/hien-thi">Làm mới</a>
    </div>
    <br>
</div><!-- End Bordered Tabs -->

<%--....--%>