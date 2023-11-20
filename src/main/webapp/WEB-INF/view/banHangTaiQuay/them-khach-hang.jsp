
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div id="modalThemKhachHang" class="modal">
    <!-- Modal content -->
    <div class="modal-content-1">
        <span class="close" id="close_themKhachHang" accesskey="${khachHang.id}">&times;</span>
        <div class="row" id="themKhachHang">
            <div class="col-sm-6">
                <div>
                    <label for="tenKhachHang">Họ tên</label>
                    <input type="text" class="form-control" name="tenKhachHang" id="tenKhachHang">
                </div>
                <div>
                    <label for="soDienThoaiKhachHang">Số điện thoại</label>
                    <input type="number" class="form-control" name="soDienThoaiKhachHang" id="soDienThoaiKhachHang">
                </div>
                <div>
                    <label for="diaChiKhachHangThemMoi">Địa chỉ</label>
                    <input type="text" class="form-control" name="diaChiKhachHangThemMoi" id="diaChiKhachHangThemMoi">
                </div>
            </div>
            <div class="col-sm-6">
                <div style="margin-bottom: 20px">
                    <label for="emailKhachHang">Email</label>
                    <input type="email" class="form-control" name="emailKhachHang" id="emailKhachHang">
                </div>
                <div class="row">
                    <div class="col-sm-2">
                        <button class="btn btn-primary" onclick="themKhachHang()">Thêm</button>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
