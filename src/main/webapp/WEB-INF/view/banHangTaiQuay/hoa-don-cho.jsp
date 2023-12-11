<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html>
<head>
    <link rel="shortcut icon" href="#">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css"
          rel="stylesheet" integrity="sha384-9ndCyUaIbzAi2FUVXJi0CjmCapSmO7SnpJef0486qhLnuZ2cdeRhO02iuK6FUUVM"
          crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"
            integrity="sha384-geWF76RCwLtnZ8qwWowPQNguL3RmwHVBC9FhGdlKrxdiJJigb/j/68SIy3Te4Bkz"
            crossorigin="anonymous"></script>
    <script src="
              https://cdn.jsdelivr.net/npm/sweetalert2@11.10.0/dist/sweetalert2.all.min.js
              "></script>
    <link href="
          https://cdn.jsdelivr.net/npm/sweetalert2@11.10.0/dist/sweetalert2.min.css
         " rel="stylesheet">
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>


</head>
    <style>
        table {
            border-collapse: collapse;
            width: 100%;
        }

        th, td {
            padding: 8px;
            text-align: left;
            border-bottom: 1px solid #ddd;
        }

        tr:hover {
            background-color: #f5f5f5;
        }
    </style>
    </style>
<body>
<%--navbar--%>
<jsp:include page="../sidebar/navbar.jsp"/>
<%--sidebar--%>
<div class="container-fluid">
    <div class="row flex-nowrap">
        <jsp:include page="../sidebar/sidebar.jsp"/>
        <div class="col py-3">
            <%--            code giao diện chất liệu --%>
            <div class="container">
                <div class="row">
                    <div class="col l-3">
                        <div>
                            <form action="/admin/hoa-don/create" method="post">
                                <button class="btn btn-primary">Tạo hoá đơn</button>
                            </form>
                        </div>
                    </div>
                </div>
                <table>
                    <thead>
                    <tr>
                        <th scope="col">STT</th>
                        <th scope="col">Mã</th>
                        <th scope="col">Ngày tạo</th>
                        <th scope="col">Trạng thái</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${hoaDons}" var="hoaDon" varStatus="i">
                        <tr>
                            <th scope="row">${i.index+ 1}</th>
                            <td>${hoaDon[1]}</td>
                            <td><fmt:formatDate value="${hoaDon[2]}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
                            <td>
                                <button style="--bs-btn-padding-y: .25rem; --bs-btn-padding-x: .5rem; --bs-btn-font-size: .75rem;"
                                        type="button"
                                        class="${hoaDon[3] == 0 ? 'btn btn-primary' : ''}">
                                        ${hoaDon[3] == 0 ? 'Đang chờ' : ''}</button>
                            </td>
                            <td>
                                <a href="/admin/hoa-don/detail?idHD=${hoaDon[0]}" class="btn btn-outline-success">Chi tiết</a>
                                <button name="${hoaDon[0]}" onclick="deleteHoaDonCho(this.name)" type="button" class="btn btn-outline-danger">Xoá</button>
                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
</div>
</body>
<script>
   function  deleteHoaDonCho(idHD) {
       console.log(idHD)
       let check = confirm("Bạn có chắc muốn xoá không!");
       if(check){
            window.location.href = "/admin/hoa-don/huy?idHD="+idHD;
       }else{
           return;
       }
   }
   if (${error != null}) {
       Swal.fire({
           title: "lỗi!",
           text: "${error}",
           icon: "error"
       });
   }

   if (${success != null}) {
       Swal.fire({
           title: "thành công!",
           text: "${success}",
           icon: "success"
       });
   }
</script>
</html>