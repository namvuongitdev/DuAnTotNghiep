<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="col-md-6">
    <div>
        <span>Loại hoá đơn :
         <c:choose>
             <c:when test="${hd.loaiHoaDon}">
                 <button type="button" class="btn btn-primary rounded-pill">Giao hàng</button>
             </c:when>
         </c:choose>
        </span>
    </div>
    <br>
    <div>
        Trạng thái:
        <jsp:include page=".././trangThai/trang-thai-hoa-don.jsp"></jsp:include>
    </div>
    <br>
    <div>
        <span>Hình thức thanh toán : ${hd.phuongThucThanhToan==3?'Thanh toán khi nhận hàng':hd.phuongThucThanhToan==2?'Chuyển khoản':hd.phuongThucThanhToan==4?'Thanh toán VNPay':'Tiền mặt'}</span>
    </div>
    <br>
    <div>
        <span value="${hd.ma}">Mã hoá đơn : ${hd.ma}</span>
    </div>
</div>
<div class="col-md-6">
    <div>
        <span>Họ tên : ${hd.loaiHoaDon ? hd.hoTen : hd.khachHang.hoTen}</span>
    </div>
    <br>
    <div>
        <span>Số điện thoại : ${hd.loaiHoaDon ? hd.sdt : hd.khachHang.sdt}</span>
    </div>
    <br>
    <div>
        <span>Địa chỉ : ${hd.diaChi}</span>
        <button style="border: none;margin-left: 10px ; background-color: white"
           onclick="updateThongTinKhachHang()"><i
                class="bi bi-pencil"></i></button>
    </div>
    <br>
    <div>
        <span>Ghí chú : ${hd.moTa == "" ? "không có ghi chú phía khách hàng" : hd.moTa}</span>
    </div>
</div>


