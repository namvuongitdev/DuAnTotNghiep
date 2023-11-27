<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="col-md-6">
    <div>
        <span>Loại hoá đơn :
         <c:choose>
             <c:when test="${hoaDon.loaiHoaDon}">
                Giao hàng
             </c:when>
             <c:otherwise>
                 Tại quầy
             </c:otherwise>
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
        <span>Hình thức thanh toán : ${hoaDon.phuongThucThanhToan==3?'Thanh toán khi nhận hàng':hoaDon.phuongThucThanhToan==2?'Chuyển khoản':hoaDon.phuongThucThanhToan==4?'Thanh toán VNPay':'Tiền mặt'}</span>
    </div>
    <br>
    <c:if test="${hoaDon.phuongThucThanhToan == 2 || hoaDon.phuongThucThanhToan == 4}">
        <div>
            <span>Mã giao dịch : ${hoaDon.maGiaoDich}</span>
        </div>
        <br>
    </c:if>
    <div>
        <span value="${hoaDon.ma}">Mã hoá đơn : ${hoaDon.ma}</span>
    </div>
</div>
<div class="col-md-6">
    <div>
        <span>Họ tên : ${hoaDon.loaiHoaDon ? hoaDon.hoTen : hoaDon.khachHang.hoTen}</span>
    </div>
    <br>
    <div>
        <span>Số điện thoại : ${hoaDon.loaiHoaDon ? hoaDon.sdt : hoaDon.khachHang.sdt}</span>
    </div>
    <br>
        <c:if test="${hoaDon.loaiHoaDon}">
            <c:if test="${hoaDon.trangThai == 1 || hoaDon.trangThai == 2}">
                <div>
                    <span>Địa chỉ : ${hoaDon.diaChi}</span>
                    <button style="border: none;margin-left: 10px ; background-color: white"
                            onclick="updateThongTinKhachHang()"><i
                            class="bi bi-pencil"></i></button>
                </div>
                <br>
            </c:if>
        </c:if>
    <div>
        <span>Ghí chú : ${hoaDon.moTa == "" ? "không có ghi chú phía khách hàng" : hoaDon.moTa}</span>
    </div>
</div>


