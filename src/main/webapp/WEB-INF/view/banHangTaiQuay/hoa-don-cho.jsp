<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html>
<head>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css"
          rel="stylesheet" integrity="sha384-9ndCyUaIbzAi2FUVXJi0CjmCapSmO7SnpJef0486qhLnuZ2cdeRhO02iuK6FUUVM"
          crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"
            integrity="sha384-geWF76RCwLtnZ8qwWowPQNguL3RmwHVBC9FhGdlKrxdiJJigb/j/68SIy3Te4Bkz"
            crossorigin="anonymous"></script>

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
                           <input type="text" >
                           <a href="#" class="btn btn-primary">Tìm kiếm</a>
                       </div>
                     </div>
                    <div class="col l-3">
                        <div>
                            <form action="/hoa-don/create" method="post">
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
                        <th scope="col">Số sản phẩm</th>
                        <th scope="col">Tổng tiền</th>
                        <th scope="col">Ngày tạo</th>
                        <th scope="col">Trạng thái</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${hoaDons.getContent()}" var="hoaDon" varStatus="i">
                        <tr>
                            <th scope="row">${i.index+page}</th>
                            <td>${hoaDon[1]}</td>
                            <td><fmt:formatNumber pattern="#,###" value="${hoaDon[2]}">

                            </fmt:formatNumber></td>
                            <td>${hoaDon[3]}</td>
                            <td><fmt:formatDate value="${hoaDon[5]}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
                            <td>
                                <button style="--bs-btn-padding-y: .25rem; --bs-btn-padding-x: .5rem; --bs-btn-font-size: .75rem;"
                                        type="button"
                                        class="${hoaDon[4] == 0 ? 'btn btn-primary' : ''}">
                                        ${hoaDon[4] == 0 ? 'Đang chờ' : ''}</button>
                            </td>
                            <td>
                                <a href="/hoa-don/detail?idHD=${hoaDon[0]}" class="btn btn-outline-success">Chi tiết</a>
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
                            <li class="page-item ${pageNo<=1?"disabled":""}"><a class="page-link" href="/hoa-don/hien-thi-hoa-cho?page=${pageNo-1}">Previous</a></li>
                            <c:forEach begin="1" end="${hoaDons.getTotalPages()}" var="i">
                                <li class="page-item" ><a class="page-link ${i == pageNo ? 'active ' : ''}" href="/hoa-don/hien-thi-hoa-cho?page=${i}">${i}</a></li>
                            </c:forEach>
                          <li class="page-item ${pageNo>=hoaDons.getTotalPages()?"disabled":""}"><a class="page-link" href="/hoa-don/hien-thi-hoa-cho?page=${pageNo+1}">Next</a></li>
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
       let ghiChu = prompt("Ghí chú");
       if(ghiChu != null){
            window.location.href = "/hoa-don/huy?idHD="+idHD+"&ghiChu="+ghiChu;
       }else{
           alert("nhập lý do huỷ hoá đơn");
       }
   }
</script>
</html>