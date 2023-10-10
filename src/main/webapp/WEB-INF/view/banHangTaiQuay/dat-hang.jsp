<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<div class="col l-3" style="margin-top: 45px; margin-bottom: 20px">
    <div class="row">
        <div class="col-sm-12"><label for="hoTen">Họ tên</label>
            <input type="text" class="form-control" name="hoTen" id="hoTen"></div>
    </div>
    <div class="row">

        <div class="col-sm-5">
            <label for="sdt">Số điện thoại</label>
            <input type="number" class="form-control" name="sdt" id="sdt">
        </div>

        <div class="col-sm-7">
            <label for="diaChi">Địa chỉ</label>
            <input type="text" class="form-control" name="diaChi" id="diaChi">
        </div>
    </div>
    <div class="row">
        <div class="col-sm-5">
            <label for="phiVanChuyen">Phí vận chuyến</label>
            <input type="number" class="form-control" name="phiVanChuyen"
                   id="phiVanChuyen">
        </div>
        <div class="col-sm-7">
            <label for="ghiChu">Ghi chú</label>
            <input type="text" class="form-control" name="moTa" id="ghiChu">
        </div>
    </div>
</div>
<div class="col l-3">
    <h2>Thông tin thanh toán</h2>
    <div class="form-check form-switch">
        <input class="form-check-input" type="checkbox" id="mySwitch"
               name="darkmode" ${hoaDon.loaiHoaDon == true? 'checked' : ''}>
        <label class="form-check-label" for="mySwitch">Đặt hàng</label>
    </div>
    <div>
                                <span>Tiền Hàng : <fmt:formatNumber pattern="#,###"
                                                                    value="${tongTien}"></fmt:formatNumber></span>
    </div>
    <div>
        <button class="btn btn-primary" id="xacNhanDatHang"
        >Đặt hàng
        </button>
    </div>
</div>