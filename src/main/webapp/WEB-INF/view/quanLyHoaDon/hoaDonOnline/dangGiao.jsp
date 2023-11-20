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
        <form:form action="/admin/hoa-don-onl/filter/3" method="get" modelAttribute="hoaDonFillter">
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
                            <td><fmt:formatDate value="${xn.ngayTao}" pattern="dd/MM/yyyy HH:mm:ss"/></td>
                            <td><fmt:formatNumber pattern="#,###"  value="${xn.tongTien}"/> VNĐ</td>
                            <td>${xn.moTa}</td>
                            <td>
                                <a data-bs-toggle="modal" data-bs-target="#exampleModal" title="Đã giao đến người nhận?" name="6" id="trangThai" style="text-decoration: none;font-size: 15px" class="badge text-bg-info text-white" ><i class="bi bi-check2"></i></a>
                                <a title="Xem chi tiết" href="/admin/hoa-don/view-update/${xn.id}" style="font-size: 15px" class="badge text-bg-success text-white"><i class="bi bi-eye-fill"></i></a>
                            </td>
                        </tr>
                        <%--    Modal Xác nhận--%>
                        <div id="exampleModal" class="modal fade xacNhanModal" tabindex="-1" aria-labelledby="xacNhanModalLabel"
                             aria-hidden="true">
                            <div class="modal-dialog modal-dialog-centered">
                                <div class="modal-content">
                                    <div class="modal-header">
                                        <h5 class="modal-title" id="xacNhanModalLabel">Xác nhận đơn</h5>
                                        <button type="button" class="btn-close" data-bs-dismiss="modal"
                                                aria-label="Close"></button>
                                    </div>
                                    <div class="modal-body">
                                        <b>${hd.trangThai==1?'Xác nhận đơn?':hd.trangThai==2?'Đơn hàng đã giao cho đơn vị vận chuyển?':hd.trangThai==3?'Đơn hàng đã giao thành công?':''}</b>
                                        <div class="mt-3">
                                            <label for="ghiChu" class="form-label">Ghi chú</label>
                                            <textarea class="form-control" id="ghiChu" name="ghiChu" rows="3"></textarea>
                                        </div>
                                    </div>
                                    <div class="modal-footer">
                                        <button type="submit" onclick="xacNhan({trangThai:`${xn.trangThai}`,idhd:`${xn.id}`})" class="btn btn-primary btn-dong-y">Đồng ý</button>
                                        <button type="submit" class="btn btn-secondary btn-khong" data-bs-dismiss="modal">Không</button>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </c:forEach>
                </tbody>
        </table>
    </div>
    <%--  phân trang --%>
    <div class="container-fluid mt-5">
        <nav aria-label="Page navigation example">
            <ul class="pagination justify-content-center">
                <li class="page-item ${currentPage<=0?"disabled":""}"><a class="page-link"
                                                                         href="/admin/hoa-don/hien-thi/${currentPage-1}"><</a>
                </li>
                <c:forEach begin="1" end="${totalPage}" var="i">
                    <li class="page-item"><a class="page-link ${lst1+1==i?'active':''}"
                                             href="/admin/hoa-don/hien-thi/${i-1}">${i}</a></li>
                </c:forEach>
                <li class="page-item ${currentPage>=totalPage-1?"disabled":""}"><a class="page-link" href="/admin/hoa-don/hien-thi/${currentPage+1}">></a>
                </li>
            </ul>
        </nav>
    </div>
</div>
</div>
</body>
<script>
    function xacNhan(data) {
        let trangThai = document.getElementById('trangThai').name;
        var ghiChu = document.getElementById('ghiChu').value;
            window.location.href="/admin/hoa-don-onl/xac-nhan/"+data.idhd+"?trangThai="+trangThai+"&ghiChu="+ghiChu;
    }
</script>
</html>