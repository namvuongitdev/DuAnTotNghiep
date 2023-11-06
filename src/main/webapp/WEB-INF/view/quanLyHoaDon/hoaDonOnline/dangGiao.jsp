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
            <h4 class="text-warning" style="text-align: center;">Đang giao</h4>
        </div>
    </div>
    <div class="row">
        <%--@elvariable id="hoaDonFillter" type=""--%>
        <form:form action="/admin/hoa-don/filter" method="get" modelAttribute="hoaDonFillter">
            <div class="input-group" style="width: 300px">
                <input type="text" class="form-control" name="search" value="${fillter.search}"
                       placeholder="Tìm theo mã hóa đơn" aria-label="Tìm theo mã hóa đơn"
                       aria-describedby="button-addon2"/>
            </div>
            <%--tìm kiếm nâng cao--%>
            <div class="row">
                <div class="col-4">
                    <label class="col-form-label">Từ ngày</label>
                    <input type="date" value="${fillter.dateBegin}" name="dateBegin"
                           class="form-control"/>
                </div>
                <div class="col-4">
                    <label class="col-form-label">Đến ngày</label>
                    <input type="date" value="${fillter.dateEnd}" name="dateEnd"
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
                    <c:forEach items="${lst}" var="xn" varStatus="i">
                        <tr>
                            <td>${i.index+1}</td>
                            <td>${xn.ma}</td>
                            <td>${xn.hoTen}</td>
                            <td>${xn.sdt}</td>
                            <td><fmt:formatDate value="${xn.ngayTao}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
                            <td><fmt:formatNumber pattern="#,###"  value="${xn.tongTien+xn.phiVanChuyen}"/> VNĐ</td>
                            <td>${xn.moTa}</td>
                            <td>
                                <a name="6" id="trangThai" onclick="xacNhan({idhd:`${xn.id}`})" style="text-decoration: none;font-size: 15px" class="badge text-bg-info text-white" ><i class="bi bi-check2"></i></a>
                                <a href="/admin/hoa-don-onl/detail/${xn.id}" style="font-size: 15px" class="badge text-bg-warning text-white"><i class="bi bi-info-circle"></i></a>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
        </table>
    </div>
</div>
</body>
<script>
    function xacNhan(data) {
        let trangThai = document.getElementById('trangThai').name;
        if (confirm("Đơn hàng đã được giao thành công?")===true){
            window.location.href="/admin/hoa-don-onl/xac-nhan/"+data.idhd+"?trangThai="+trangThai;
        }else {
            return;
        }
    }
</script>
</html>