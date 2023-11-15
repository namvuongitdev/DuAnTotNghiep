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
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/sweetalert2@11.1.5/dist/sweetalert2.min.css">
    <script src="https://cdn.jsdelivr.net/npm/sweetalert2@11.1.5/dist/sweetalert2.min.js"></script>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.1/font/bootstrap-icons.css">

</head>
<body>
<div class="container-fluid">
    <div class="row flex-nowrap">
        <div class="col mb-2">
            <h4 class="text-secondary" style="text-align: center;">Tất cả</h4>
        </div>
    </div>
    <div class="row">
        <%--@elvariable id="hoaDonFillter" type=""--%>
        <form:form action="/admin/hoa-don/filter" method="get" modelAttribute="hoaDonFillter">
            <div class="input-group" style="width: 500px">
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
                <div class="col-4">
                    <label class="col-form-label">Loại hóa đơn</label>
                    <select class="form-select" name="loaiHoaDon"
                            aria-label="Default select example">
                        <option value="">Tất cả</option>
                        <option value="true" ${fillter.loaiHoaDon == 'true'? 'selected':''}
                                class="text-warning">Đơn hàng
                        </option>
                        <option value="false" ${fillter.loaiHoaDon == 'false'? 'selected':''}
                                class="text-success">Hóa đơn tại quầy
                        </option>
                    </select>
                </div>
                    <%--button--%>
                <div style="text-align: center" class="mt-3">
                    <button type="submit" class="btn btn-outline-dark">Tìm kiếm</button>
                </div>
            </div>
            <br>

        </form:form>
    </div>
    <div class="row flex-nowrap">
        <!-- Table with stripped rows -->
        <table class="table">
            <thead>
            <tr>
                <th scope="col">STT</th>
                <th scope="col">Mã hóa đơn</th>
                <th scope="col">Loại hóa đơn</th>
                <th scope="col">Thời gian</th>
                <th scope="col">Khách hàng</th>
                <th scope="col">Tổng tiền hàng</th>
                <th scope="col">Tình trạng</th>
                <th scope="col">Thao tác</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${lst}" var="hd" varStatus="i">
                <tr style="font-weight: 600">
                    <td>${i.index+1}</td>
                    <td>${hd.ma}</td>
                    <td>${hd.loaiHoaDon==false?'Đơn tại quầy':'Đơn giao'}</td>
                    <td><fmt:formatDate value="${hd.ngayTao}" pattern="dd/MM/yyyy HH:mm:ss"/></td>
                    <td>${hd.hoTen==null?'Khách bán lẻ':hd.hoTen}</td>
                    <td><fmt:formatNumber pattern="#,###" value="${hd.tongTien}"/></td>
                    <td>${hd.trangThai==4?'Đã thanh toán':(hd.trangThai==3)?'Đang giao hàng':(hd.trangThai==0)?'Đang chờ':(hd.trangThai==1)?'Chờ xác nhận':(hd.trangThai==2)?'Đã tiếp nhận':(hd.trangThai==6)?'Giao hàng thành công':(hd.trangThai==5)?'Hủy đơn hàng':''}</td>
                    <td>
                        <a style="font-size: 15px" title="Xem chi tiết" href="/admin/hoa-don/view-update/${hd.id}" class="badge text-bg-warning text-white"><i class="bi bi-info-circle"></i></a>
                        <a title="${hd.trangThai==1?'Xác nhận đơn?':hd.trangThai==2?'Đơn hàng đã giao cho đơn vị vận chuyển?':hd.trangThai==3?'Đơn hàng đã giao thành công?':''}" name="2" id="trangThai" onclick="xacNhan({idhd:`${hd.id}`,trangThai:`${hd.trangThai}`})" style="text-decoration: none;font-size: 15px;display: ${hd.loaiHoaDon==true?'':'none'};visibility: ${hd.trangThai==5?'hidden':hd.trangThai==6?'hidden':''}" class="badge text-bg-info text-white" ><i class="bi bi-check2"></i></a>
                        <a title="Hủy đơn hàng" onclick="huyDonHang({idhd:`${hd.id}`})" style="text-decoration: none;font-size: 15px;display: ${hd.loaiHoaDon==true?'':'none'};visibility: ${hd.trangThai==5?'hidden':hd.trangThai==6?'hidden':hd.trangThai==3?'hidden':''}" class="badge text-bg-info text-white" ><i class="bi bi-x-square"></i></a>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
        <!-- End Table with stripped rows -->
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
</body>
<script>
    function myFunction() {
        document.getElementById("floatingName").readOnly = false;
        document.getElementById("disabled").disabled = false
    }
    function xacNhan(data) {
        if (confirm("Bạn có chắc chắn xác nhận đơn không ?")===true){
            window.location.href="/admin/hoa-don-onl/xac-nhan/"+data.idhd;
        }else {
            return;
        }
    }
    function huyDonHang(data) {
        if (confirm("Bạn có chắc chắn muốn hủy đơn không ?")===true){
            window.location.href="/admin/hoa-don-onl/huy-don/"+data.idhd;
        }else {
            return;
        }
    }
</script>
</html>