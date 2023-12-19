<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div id="modalThemKhachHang" class="modal">
    <!-- Modal content -->
    <div class="modal-content-1" style="width: 40%">
        <span class="close" id="close_themKhachHang" accesskey="${khachHang.id}">&times;</span>
        <div class="row" id="themKhachHang">
            <div class="col-sm-12">
                <div>
                    <label for="tenKhachHang">Họ tên</label>
                    <input type="text" class="form-control" name="tenKhachHang" id="tenKhachHang">
                </div>
                <div>
                    <label for="soDienThoaiKhachHang">Số điện thoại</label>
                    <input type="number" class="form-control" name="soDienThoaiKhachHang" id="soDienThoaiKhachHang">
                </div>
                <div>
                    <label for="taiKhoan">Tài khoản</label>
                    <input type="text" class="form-control" name="taiKhoan" id="taiKhoan">
                </div>
                <div>
                    <label for="emailKhachHang">Email</label>
                    <input type="email" class="form-control" name="emailKhachHang" id="emailKhachHang">
                </div>
            </div>
            <div class="row" style="margin-top: 10px">
                <div class="col-sm-2">
                    <button class="btn btn-primary" onclick="themKhachHang()">Thêm</button>
                </div>
            </div>

        </div>
    </div>
</div>
