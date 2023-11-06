<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css"
          rel="stylesheet" integrity="sha384-9ndCyUaIbzAi2FUVXJi0CjmCapSmO7SnpJef0486qhLnuZ2cdeRhO02iuK6FUUVM"
          crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"
            integrity="sha384-geWF76RCwLtnZ8qwWowPQNguL3RmwHVBC9FhGdlKrxdiJJigb/j/68SIy3Te4Bkz"
            crossorigin="anonymous"></script>

</head>
<body>
<%--navbar--%>
<jsp:include page="../sidebar/navbar.jsp"/>
<%--sidebar--%>
<div class="container-fluid">
    <div class="row flex-nowrap">
        <jsp:include page="../sidebar/sidebar.jsp"/>
        <div class="col-sm-10 col-md-8 col-lg-6 mx-auto d-table h-100">
            <div class="d-table-cell align-middle">
                <div class="text-center mt-5">
                    <h1 class="h2">Đặt lại mật khẩu</h1>
                    <p class="lead">
                        Nhập email của bạn để thiết lập lại mật khẩu của bạn.
                    </p>
                </div>
                <div class="m-sm-4">
                    <form action="/admin/doiMatKhau/${myEmail}" method="post">
                        <div class="form-floating mb-3">
                            <input value="${myEmail}" class="form-control form-control-lg" type="email" name="email" placeholder="Email của bạn" disabled>
                            <label class="form-label">Email của bạn</label>
                        </div>
                        <div class="form-floating mb-3">
                            <input class="form-control form-control-lg" type="password" name="password" placeholder="Mật khẩu mới">
                            <label class="form-label">Mật khẩu mới</label>
                            <p style="color: red">${pass}</p>
                        </div>
                        <div class="form-floating mb-3">
                            <input class="form-control form-control-lg" type="password" name="confirmPassword" placeholder="Nhập lại mật khẩu">
                            <label class="form-label">Nhập lại mật khẩu</label>
                            <p style="color: red">${nhapLai}</p>
                        </div>
                        <div class="text-center mt-4">
                            <button type="submit" class="btn btn-lg btn-primary">Đổi mật khẩu</button>
                            <!-- <button type="submit" class="btn btn-lg btn-primary">Reset password</button> -->
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>
