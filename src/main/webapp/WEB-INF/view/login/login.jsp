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
                        <h2>Đăng nhập</h2>
                        <p>Vui lòng không chia sẻ bất kỳ thông tin bảo mật cho người khác.</p>

                    </div>
                    <div class="form-input">
                        <h2>Đăng nhập</h2>
                        <c:if test="${param.error != null}">
                            <p style="color: #E43535">Tên tài khoản hoặc mật khẩu không chính xác</p>
                        </c:if>

                        <form action="${pageContext.request.contextPath}/dang-nhap" method="post">
                            <div class="form-group">
                                <input type="text" id="username" name="username" value="${param.username}" required>
                                <label>Tài khoản</label>
                            </div>
                            <div class="form-group">
                                <input type="password" id="password" name="password" value="${param.password}" required>
                                <label>Mật khẩu</label>
                            </div>
                            <div class="myform-button">
                                <button class="myform-btn">Đăng nhập</button>
                            </div><br>
                            <div style="text-align: center">
                                <a href="/quenMatKhau">Quên mật khẩu</a>
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
    function clearLocalStorage() {
        localStorage.clear();
    }

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