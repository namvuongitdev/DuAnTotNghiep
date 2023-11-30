<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<div class="col l-3" style="margin-top: 45px; margin-bottom: 20px">
    <div class="row">
        <div class="col-sm-12"><label for="hoTen">Họ tên</label>
            <input type="text" class="form-control" name="hoTen" id="hoTen" value="${khachHang != null ? khachHang.hoTen : datHang.hoTen}"></div>
        <c:if test="${hoTen != null}">
            <p style="color: #E43535">${hoTen}</p>
        </c:if>
    </div>
    <div class="row">
        <div class="col-sm-5">
            <label for="sdt">Số điện thoại</label>
            <input type="number" class="form-control" name="sdt" id="sdt" value="${khachHang != null ? khachHang.sdt : datHang.sdt}" >
            <c:if test="${sdt != null}">
                <p style="color: #E43535">${sdt}</p>
            </c:if>
        </div>

        <div class="col-sm-7">
            <label for="diaChi">Địa chỉ</label>
            <input type="text" class="form-control" name="diaChi" id="diaChi" value="${datHang.diaChi}">
            <c:if test="${diaChi != null}">
                <p style="color: #E43535">${diaChi}</p>
            </c:if>
        </div>
    </div>
    <div class="row">
        <div class="col-sm-5">
            <label for="phiVanChuyen">Phí vận chuyến</label>
            <input type="number" class="form-control" name="phiVanChuyen"
                   id="phiVanChuyen" value="${datHang.phiVanChuyen}">
            <c:if test="${phiVanChuyen != null}">
                <p style="color: #E43535">${phiVanChuyen}</p>
            </c:if>
        </div>
        <div class="col-sm-7">
            <label for="ghiChu">Ghi chú</label>
            <input type="text" class="form-control" name="moTa" id="ghiChu">
        </div>
    </div>
</div>
<div class="col l-3">
    <h4>Thông tin thanh toán</h4>
    <div class="form-check form-switch">
        <input class="form-check-input" type="checkbox" id="mySwitch"
               name="${khachHang.id}" ${hoaDon.loaiHoaDon == true? 'checked' : ''}>
        <label class="form-check-label" for="mySwitch">Đặt hàng</label>
    </div>
    <div>
     <span id="tienHang">Tiền Hàng : <fmt:formatNumber pattern="#,###"
      value="${tongTien}"></fmt:formatNumber></span>
    </div>
    <br>
    <div>
        <button class="btn btn-primary" id="xacNhanDatHang"
        >Đặt hàng
        </button>
    </div>
</div>