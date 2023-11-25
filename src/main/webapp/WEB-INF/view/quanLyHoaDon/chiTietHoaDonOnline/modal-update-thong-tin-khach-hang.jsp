<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<div id="modalUpdateThongTinKhachHang" class="modal">
    <div class="modal-content-1" style="width: 50%">
        <span class="close" id="close_modalUpdateThongTinKhachHang">&times;</span>
        <div class="row">
            <form:form action="/admin/hoa-don/update-thong-tin-khach-hang?idHD=${hoaDon.id}" method="post" modelAttribute="thongTinKhachHang">
                <div class="row">
                    <div class="col-md-4">
                        <label for="hoTenKhach">Họ tên khách hàng</label>
                        <input id="hoTenKhach" value="${hoaDon.hoTen}" type="text" name="hoTen" class="form-control"/>
                    </div>
                    <div class="col-md-4">
                        <label for="sdtKhach">Số điện thoại</label>
                        <input id="sdtKhach" value="${hoaDon.sdt}" type="number" name="sdt" class="form-control"/>
                    </div>
                </div>
                <div class="row">
                    <div class="col-sm-4">
                        <label for="phiVanChuyen">Phí vận chuyển</label>
                        <input id="phiVanChuyen" style="height: 60px" value="${hoaDon.phiVanChuyen}" name="phiVanChuyen" type="number" class="form-control"/>
                    </div>
                    <div class="col-md-6">
                        <label for="diaChiMoi">Địa chỉ</label>
                        <textarea id="diaChiMoi" name="diaChi"  class="form-control">${hoaDon.diaChi}</textarea>
                    </div>
                </div>
                <div class="row">
                    <div class="col-md-10">
                        <label for="ghiChu">Ghi chú</label>
                        <textarea id="ghiChu" name="ghiChu"  class="form-control"></textarea>
                    </div>
                </div>
                <div style="margin-top: 20px">
                    <button class="btn btn-primary">
                        update
                    </button>
                </div>
            </form:form>
        </div>
    </div>
</div>