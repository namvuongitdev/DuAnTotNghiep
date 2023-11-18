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
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.1/font/bootstrap-icons.css">
</head>
<body>
<div class="container-fluid">
    <div class="row flex-nowrap">
        <div class="col mb-2">
            <h4 class="text-success" style="text-align: center;">Đã giao</h4>
        </div>
    </div>
    <div class="row">
        <%--@elvariable id="hoaDonFillter" type=""--%>
        <form:form action="/admin/hoa-don-onl/filter/6" method="get" modelAttribute="hoaDonFillter">
            <div class="input-group" style="width: 300px">
                <input type="text" class="form-control" name="search" value="${fillter.search}"
                       placeholder="Tìm theo mã hóa đơn" aria-label="Tìm theo mã hóa đơn"
                       aria-describedby="button-addon2"/>
            </div>
            <%--tìm kiếm nâng cao--%>
            <div class="row">
                <div class="col-4">
                    <label class="col-form-label">Từ ngày</label>
                    <input type="date" pattern="dd/MM/yyyy" value="${fillter.dateBegin}" name="dateBegin"
                           class="form-control"/>
                </div>
                <div class="col-4">
                    <label class="col-form-label">Đến ngày</label>
                    <input type="date" pattern="dd/MM/yyyy" value="${fillter.dateEnd}" name="dateEnd"
                           class="form-control"/>
                </div>
                <br>
                    <%--button--%>
                <div class="mt-3" style="text-align: center">
                    <button type="submit" class="btn btn-outline-dark">Tìm kiếm</button>
                </div>
            </div>
            <br>
        </form:form>
    </div>
    <div class="row flex-nowrap">
        <table class="table">
            <thead>
            <tr>
                <th>STT</th>
                <th scope="col">Mã đơn</th>
                <th scope="col">Họ tên</th>
                <th scope="col">Số điện thoại</th>
                <th scope="col">Ngày tạo đơn</th>
                <th scope="col">Tổng tiền</th>
                <th scope="col">Ghi chú</th>
                <th scope="col">Thao tác</th>
            </tr>
            </thead>
            <tbody>
                <c:forEach items="${lst}" var="dg" varStatus="i">
                    <tr>
                        <td>${i.index+1}</td>
                        <td>${dg.ma}</td>
                        <td>${dg.hoTen}</td>
                        <td>${dg.sdt}</td>
                        <td><fmt:formatDate value="${dg.ngayTao}" pattern="dd/MM/yyyy HH:mm:ss"/></td>
                        <td><fmt:formatNumber pattern="#,###"  value="${dg.tongTien}"/> VNĐ</td>
                        <td>${dg.moTa}</td>
                        <td>
                            <a title="Xem chi tiết" href="/admin/hoa-don/view-update/${dg.id}" style="font-size: 15px" class="badge text-bg-success text-white"><i class="bi bi-eye-fill"></i></a>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </div>
    <%--  phân trang --%>
    <div class="container-fluid mt-5">
        <nav aria-label="Page navigation example">
            <ul class="pagination justify-content-center">
                <li class="page-item ${currentPage<=0?"disabled":""}"><a class="page-link"
                                                                         href="/admin/hoa-don-onl/hien-thi/6/${currentPage-1}"><</a>
                </li>
                <c:forEach begin="1" end="${totalPage}" var="i">
                    <li class="page-item"><a class="page-link ${currentPage+1==i?'active':''}"
                                             href="/admin/hoa-don-onl/hien-thi/6/${i-1}">${i}</a></li>
                </c:forEach>
                <li class="page-item ${currentPage>=totalPage-1?"disabled":""}"><a class="page-link" href="/admin/hoa-don-onl/hien-thi/6/${currentPage+1}">></a>
                </li>
            </ul>
        </nav>
    </div>
</div>
</div>
</body>
</html>