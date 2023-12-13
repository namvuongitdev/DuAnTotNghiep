<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 11/19/2023
  Time: 11:33 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<div id="modalThemDiaChiKhachHangOnline" class="modal">
    <div class="modal-content-1" style="width: 40%">
        <span class="close" id="close_themDiaChiKhachHangOnline">&times;</span>
        <div class="row">
            <form action="/checkouts/new-dia-chi?idKH=${KhachHang}" method="post" modelAttribute="${newDiaChiOnline}">
                <div class="col-12">
                    <div class="form-floating mb-3">
                        <input type="text" class="form-control"  name="hoTen">
                        <label>Họ và tên</label>
                    </div>
                </div>
                <div class="col-12">
                    <div class="form-floating mb-3">
                        <input type="number" class="form-control"  name="sdt">
                        <label>Số điện thoại</label>
                    </div>
                </div>
                <div class="col-12">
                    <div class="form-floating mb-3">
                        <input type="text" class="form-control" name="diaChi">
                        <label>Địa chỉ</label>
                    </div>
                </div>
                <div class="col-12" style="margin-left: 22px">
                    <div class="form-check form-switch">
                        <input class="form-check-input" type="checkbox" id="mySwitch"
                               name="diaChiMacDinh" value="false">
                        <label class="form-check-label" for="mySwitch">Địa chỉ mặc định</label>
                    </div>
                </div>
                <div class="col-4" style="margin-top: 20px">
                    <button class="btn btn-primary">Thêm mới</button>
                </div>
            </form>
        </div>
    </div>
</div>
