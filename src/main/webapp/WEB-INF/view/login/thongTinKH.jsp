<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html lang="zxx">
<head>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.5.0/font/bootstrap-icons.css" rel="stylesheet"/>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-GLhlTQ8iRABdZLl6O3oVMWSktQOp6b7In1Zl3/Jr59b6EGGoI1aFkw7cmDA6j6gD" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"
            integrity="sha384-geWF76RCwLtnZ8qwWowPQNguL3RmwHVBC9FhGdlKrxdiJJigb/j/68SIy3Te4Bkz"
            crossorigin="anonymous"></script>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css">
    <script src="https://cdn.jsdelivr.net/npm/jquery@3.6.3/dist/jquery.slim.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>

    <script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.8.2/angular.min.js"></script>
    <script src="https://kit.fontawesome.com/35a8b342cd.js" crossorigin="anonymous"></script>
    <!-- Google Font -->
    <link href="https://fonts.googleapis.com/css2?family=Nunito+Sans:wght@300;400;600;700;800;900&display=swap"
          rel="stylesheet">

    <!-- Css Styles -->
    <link rel="stylesheet" href="../../../css/bootstrap.min.css" type="text/css">
    <link rel="stylesheet" href="../../../css/font-awesome.min.css" type="text/css">
    <link rel="stylesheet" href="../../../css/elegant-icons.css" type="text/css">
    <link rel="stylesheet" href="../../../css/magnific-popup.css" type="text/css">
    <link rel="stylesheet" href="../../../css/nice-select.css" type="text/css">
    <link rel="stylesheet" href="../../../css/owl.carousel.min.css" type="text/css">
    <link rel="stylesheet" href="../../../css/slicknav.min.css" type="text/css">
    <link rel="stylesheet" href="../../../css/style.css" type="text/css">

    <link rel="stylesheet" href="../../../css/banHangOnline/hoaDon/hoaDon.css" type="text/css">

</head>
<body>
<!-- Header Section Begin -->
<jsp:include page="../layout/header.jsp"/>

<!-- Header Section End -->
<section class="section">
    <div class="container">
        <div>
            <h3 class="h3-title">Thông tin của tôi</h3>
        </div>
        <div class="card">
            <div class="card-body">
                <div>
                    <h5>Thông tin cá nhân</h5>
                    <!-- Button trigger modal -->
                    Đỗ Anh Tuấn
                </div>

                <div>
                    <h5>Thông tin bảo mật</h5>
                    <!-- Button trigger modal -->
                    <button type="button" class="btn btn-success" data-toggle="modal" data-target="#exampleModalCenter">
                        Đổi mật khẩu
                    </button>
                </div>
            </div>
        </div>
    </div>
</section>
<!-- Modal -->
<div class="modal fade" id="exampleModalCenter" tabindex="-1" role="dialog" aria-labelledby="exampleModalCenterTitle" aria-hidden="true">
    <div class="modal-dialog modal-dialog-centered" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="exampleModalLongTitle">Đổi mật khẩu</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <form action="/cuaToi/doiMatKhau/${sessionScope.username}" method="post">
                <div class="modal-body">
                    <div class="row">
                        <div style="width: 90%">
                            <div class="form-floating mb-3">
                                <input type="password" class="form-control" id="matKhauMoi" name="matKhauMoi" placeholder="Mật khẩu mới">
                                <label for="matKhauMoi">Mật khẩu mới</label>
                            </div>
                        </div>
                        <div style="width: 10%; padding-top: 20px">
                            <i onclick="matKhauMoi()" class="bi bi-eye-slash"></i>
                        </div>
                    </div>
                    <div class="row">
                        <div style="width: 90%">
                            <div class="form-floating">
                                <input type="password" class="form-control" id="nhapLai" name="nhapLai" placeholder="Nhập lại mật khẩu">
                                <label for="nhapLai">Nhập lại mật khẩu</label>
                            </div>
                        </div>
                        <div style="width: 10%; padding-top: 20px">
                            <i onclick="nhapLai()" class="bi bi-eye-slash"></i>
                        </div>
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-dismiss="modal">Đóng</button>
                    <button type="submit" onclick="reload()" class="btn btn-primary">Xác nhận</button>
                </div>
            </form>
        </div>
    </div>
</div>

<!-- Footer Section Begin -->
<jsp:include page="../layout/footer.jsp"/>
<!-- Js Plugins -->
<script src="../../../js/jquery-3.3.1.min.js"></script>
<script src="../../../js/bootstrap.min.js"></script>
<script src="../../../js/jquery.nice-select.min.js"></script>
<script src="../../../js/jquery.nicescroll.min.js"></script>
<script src="../../../js/jquery.magnific-popup.min.js"></script>
<script src="../../../js/jquery.countdown.min.js"></script>
<script src="../../../js/jquery.slicknav.js"></script>
<script src="../../../js/mixitup.min.js"></script>
<script src="../../../js/owl.carousel.min.js"></script>
<script src="../../../js/main.js"></script>

<script>
    function matKhauMoi(){
        let password = document.getElementById('matKhauMoi');
        if(password.type == 'text'){
            password.type = 'password';
        }else{
            password.type = 'text';
        }
    }

    function nhapLai(){
        let password = document.getElementById('nhapLai');
        if(password.type == 'text'){
            password.type = 'password';
        }else{
            password.type = 'text';
        }
    }

    function reload() {
        var matKhauMoi = document.getElementById('matKhauMoi');
        var nhapLai = document.getElementById('nhapLai');
        if (matKhauMoi.value.trim() === '' || nhapLai.value.trim() === '') {
            alert('Vui lòng điền đủ thông tin.');
            location.reload();
        }else if (nhapLai.value.trim() !== matKhauMoi.value.trim()) {
            alert('Mật khẩu không trùng khớp.');
            location.reload();
        }else{
            alert('Đổi mật khẩu thành công.')
        }
    }
</script>

</body>
</html>