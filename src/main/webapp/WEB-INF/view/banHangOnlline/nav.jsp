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
<nav class="navbar navbar-expand-lg navbar-light " style="height:80px;background-color: #f5f5f5">
    <div>
        <img src="/anh/logo.png" style="height:80px ">
    </div>
    <div>
        <form>
            <div class="input-group" style="width:500px;height:38px;padding-top: 10px;margin-left:400px " ,>
                <input type="text" text-while class="form-control"
                       placeholder="Search">
                <button type="submit"
                        class="btn btn-secondary"
                        style="height: 38px;background-color: black;color: white;border: black">
                    Tìm kiếm
                </button>
            </div>
        </form>
    </div>
</nav>
<nav class="navbar navbar-expand-lg navbar-light " style="height:50px;background-color: #363535">
    <ul class="navbar-nav me-auto mb-2 mb-lg-0 ms-lg-4 " style="margin-top: 7px">
        <li class="nav-item" style="margin-left: 70px"><a class="nav-link text-black" href="/sports-clothing/trang-chu"><h6
                style="color: white">TRANG CHỦ</h6></a></li>
        <li class="nav-item"><a class="nav-link text-black" href="#!"><h6 style="color: white">SẢN PHẨM </h6></a></li>
        <li class="nav-item"><a class="nav-link text-black" href="#!"><h6 style="color: white">HÀNG BÁN CHẠY </h6></a>
        </li>
        <li class="nav-item"><a class="nav-link text-black" href="#!"><h6 style="color: white">THỂ THAO NAM </h6></a>
        </li>
        <li class="nav-item"><a class="nav-link text-black" href="#!"><h6 style="color: white">THỂ THAO NỮ </h6></a>
        </li>
    </ul>
    <div class="nav-item">
        <a class="btn btn-outline mt-auto" href="/gio-hang-onl-hien-thi"
           title="Giỏ Hàng" style="margin-top: 20px ;margin-right: 20px;background-color: white ">
            <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16"
                 fill="currentColor" class="bi bi-cart4" viewBox="0 0 16 16">
                <path d="M0 2.5A.5.5 0 0 1 .5 2H2a.5.5 0 0 1 .485.379L2.89 4H14.5a.5.5 0 0 1 .485.621l-1.5 6A.5.5 0 0 1 13 11H4a.5.5 0 0 1-.485-.379L1.61 3H.5a.5.5 0 0 1-.5-.5zM3.14 5l.5 2H5V5H3.14zM6 5v2h2V5H6zm3 0v2h2V5H9zm3 0v2h1.36l.5-2H12zm1.11 3H12v2h.61l.5-2zM11 8H9v2h2V8zM8 8H6v2h2V8zM5 8H3.89l.5 2H5V8zm0 5a1 1 0 1 0 0 2 1 1 0 0 0 0-2zm-2 1a2 2 0 1 1 4 0 2 2 0 0 1-4 0zm9-1a1 1 0 1 0 0 2 1 1 0 0 0 0-2zm-2 1a2 2 0 1 1 4 0 2 2 0 0 1-4 0z"/>
            </svg>
        </a>
    </div>
</nav>
</body>
</html>