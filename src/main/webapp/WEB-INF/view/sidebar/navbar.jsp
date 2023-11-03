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
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.1/font/bootstrap-icons.css">
</head>
<body>
<nav class="navbar navbar-light" style="height:80px;background-color: #f5f5f5; border-bottom: 1px #cbd3da groove">
    <div class="container-fluid">
        <h4 style="margin-left: 20px">Sports Clothing</h4>
        <div class="dropdown">
            <a href="#" id="dropdownMenuLink" style="text-decoration: none; color: black" data-bs-toggle="dropdown" aria-expanded="false">
                <span>${sessionScope.username}</span>
                <svg xmlns="http://www.w3.org/2000/svg" width="25" height="25" fill="currentColor" class="bi bi-person-circle" viewBox="0 0 16 16">
                    <path d="M11 6a3 3 0 1 1-6 0 3 3 0 0 1 6 0z"/>
                    <path fill-rule="evenodd" d="M0 8a8 8 0 1 1 16 0A8 8 0 0 1 0 8zm8-7a7 7 0 0 0-5.468 11.37C3.242 11.226 4.805 10 8 10s4.757 1.225 5.468 2.37A7 7 0 0 0 8 1z"/>
                </svg>
            </a>
            <ul class="dropdown-menu dropdown-menu-end" aria-labelledby="dropdownMenuLink">
                <li><a class="bi bi-lock dropdown-item" style="font-size: 14px" href="#"> Đổi mật khẩu</a></li>
                <li><a class="bi bi-box-arrow-right dropdown-item" style="font-size: 14px" href="/logout"
                       onclick="if(confirm('Bạn có muốn đăng xuất không ?')==true){return true;}else{return false;}"> Đăng xuất</a></li>
            </ul>
        </div>
    </div>
</nav>
</body>
</html>