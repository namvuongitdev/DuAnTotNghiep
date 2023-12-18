<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <link rel="stylesheet" href="/css/login.css">
    <script src="
              https://cdn.jsdelivr.net/npm/sweetalert2@11.10.0/dist/sweetalert2.all.min.js
              "></script>
    <link href="
          https://cdn.jsdelivr.net/npm/sweetalert2@11.10.0/dist/sweetalert2.min.css
         " rel="stylesheet">
</head>
<body>
<section class="myform-area">
    <div class="container">
        <div class="row justify-content-center">
            <div class="col-lg-8">
                <div class="form-area login-form">
                    <div class="form-content">
                        <h2>Quên mật khẩu</h2>
                        <p>Vui lòng nhập email để được cấp lại mật khẩu mới. Sau khi gửi vui lòng kiểm tra mail.</p><br>

                    </div>
                    <div class="form-input">
                        <h2>Quên mật khẩu</h2>
                        <p style="color: #E43535">${erroEmail}</p>
                        <p style="color: #1e7e34">${thongBao}</p>

                        <form action="/quenMatKhau" method="post">
                            <div class="form-group">
                                <input type="email" id="email" name="email" required>
                                <label>Email</label>
                            </div>
                            <div class="myform-button">
                                <button type="submit" class="myform-btn">Gửi</button>
                            </div><br>
                            <div style="text-align: center">
                                <a href="/login" onclick="clearLocalStorage()">Đăng nhập</a>
                            </div><br>
                            <div style="text-align: center">
                                <a href="/dangKy" onclick="clearLocalStorage()">Bạn chưa có tài khoản? Đăng ký</a>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
</section>
<script>
    if (${error != null}) {
        Swal.fire({
            title: "${error}",
            icon: "error"
        });
    }

    if (${success != null}) {
        Swal.fire({
            title: "${success}",
            icon: "success"
        });
        clearLocalStorage();
    }
</script>
</body>
</html>