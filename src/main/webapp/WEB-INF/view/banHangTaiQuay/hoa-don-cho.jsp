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
    <svg xmlns="http://www.w3.org/2000/svg" style="display: none;">
        <symbol id="exclamation-triangle-fill" fill="currentColor" viewBox="0 0 16 16">
            <path d="M8.982 1.566a1.13 1.13 0 0 0-1.96 0L.165 13.233c-.457.778.091 1.767.98 1.767h13.713c.889 0 1.438-.99.98-1.767L8.982 1.566zM8 5c.535 0 .954.462.9.995l-.35 3.507a.552.552 0 0 1-1.1 0L7.1 5.995A.905.905 0 0 1 8 5zm.002 6a1 1 0 1 1 0 2 1 1 0 0 1 0-2z"/>
        </symbol>
        <symbol id="info-fill" fill="currentColor" viewBox="0 0 16 16">
            <path d="M8 16A8 8 0 1 0 8 0a8 8 0 0 0 0 16zm.93-9.412-1 4.705c-.07.34.029.533.304.533.194 0 .487-.07.686-.246l-.088.416c-.287.346-.92.598-1.465.598-.703 0-1.002-.422-.808-1.319l.738-3.468c.064-.293.006-.399-.287-.47l-.451-.081.082-.381 2.29-.287zM8 5.5a1 1 0 1 1 0-2 1 1 0 0 1 0 2z"/>
        </symbol>
    </svg>

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
                <c:if test="${error!=null}">
                    <jsp:include page="../notiface/error.jsp"></jsp:include>
                </c:if>
                <c:if test="${success!=null}">
                    <jsp:include page="../notiface/success.jsp"></jsp:include>
                </c:if>

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
                    <c:forEach items="${hoaDons.getContent()}" var="hoaDon" varStatus="i">
                        <tr>
                            <th scope="row">${i.index+page}</th>
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
                <%--                phân trang --%>
                <div class="container-fluid mt-5">
                    <nav aria-label="Page navigation example">
                        <ul class="pagination justify-content-center">
                            <li class="page-item ${pageNo<=1?"disabled":""}"><a class="page-link" href="/hoa-don/hien-thi-hoa-cho?page=${pageNo-1}"><</a></li>
                            <c:forEach begin="1" end="${hoaDons.getTotalPages()}" var="i">
                                <li class="page-item" ><a class="page-link ${i == pageNo ? 'active ' : ''}" href="/hoa-don/hien-thi-hoa-cho?page=${i}">${i}</a></li>
                            </c:forEach>
                          <li class="page-item ${pageNo>=hoaDons.getTotalPages()?"disabled":""}"><a class="page-link" href="/hoa-don/hien-thi-hoa-cho?page=${pageNo+1}"> > </a></li>
                        </ul>
                    </nav>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
<script>
   function  deleteHoaDonCho(idHD) {
       let check = confirm("Bạn có chắc muốn xoá không!");
       if(check){
            window.location.href = "/hoa-don/huy?idHD="+idHD;
       }else{
           return;
       }
   }
</script>
</html>