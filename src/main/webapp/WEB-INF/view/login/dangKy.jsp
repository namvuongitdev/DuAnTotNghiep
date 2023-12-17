<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
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
                <div class="form-area login-form" style="height: 100%">
                    <div class="form-content">
                        <h2>Đăng ký tài khoản.</h2>
                        <p>Bạn cần đăng ký tài khoản để có thể mua sản phẩm.</p><br>

                    </div>
                    <div class="form-input">
                        <h2>Đăng ký</h2>
                        <p style="color: #E43535">${erroEmail}</p>
                        <p style="color: #1e7e34">${thongBao}</p>

                        <%--@elvariable id="khachHang" type=""--%>
                        <form action="/dangKy" method="post" id="yourForm">
                            <div class="form-floating">
                                <input type="email" id="email" name="email" placeholder="Email"/>
                                <label for="email">Email</label>
                            </div>
                            <div class="form-floating">
                                <input type="text" id="taiKhoan" name="taiKhoan" placeholder="Tên tài khoản"/>
                                <label for="taiKhoan">Tên tài khoản</label>
                            </div>
                            <div class="form-floating">
                                <input type="text" id="hoTen" name="hoTen" placeholder="Họ và tên"/>
                                <label for="hoTen">Họ và tên</label>
                            </div>
                            <div class="form-floating">
                                <input type="text" id="sdt" name="sdt" placeholder="Số điện thoại"/>
                                <label for="sdt">Số điện thoại</label>
                            </div>
                            <div class="myform-button">
                                <button type="submit" class="myform-btn" onclick="saveInputValues()">Đăng ký</button>
                            </div><br>
                        </form>
                        <div style="text-align: center">
                            <a href="/login" onclick="clearLocalStorage()">Đăng nhập</a>
                        </div><br>
                        <div style="text-align: center">
                            <a href="/quenMatKhau" onclick="clearLocalStorage()">Quên mật khẩu</a>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</section>
<script>
    // Lấy giá trị từ tất cả các ô input và textarea, và lưu chúng vào localStorage
    function saveInputValues() {
        var allInputs = document.querySelectorAll('input, textarea');

        for (var i = 0; i < allInputs.length; i++) {
            var inputId = allInputs[i].id;
            var inputValue = allInputs[i].value;

            localStorage.setItem(inputId, inputValue);
        }
    }

    // Khôi phục giá trị từ localStorage vào các ô input và textarea
    window.onload = function() {
        var allInputs = document.querySelectorAll('input, textarea');

        for (var i = 0; i < allInputs.length; i++) {
            var inputId = allInputs[i].id;
            var savedValue = localStorage.getItem(inputId);

            if (savedValue) {
                allInputs[i].value = savedValue;
            }
        }
    }

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