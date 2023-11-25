<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div id="modalXacNhanHoaDon" class="modal">
    <div class="modal-content-1" style="width: 50%">
        <c:if test="${hoaDon.trangThai == 1}">
            <h6>xác nhận đơn hàng</h6>
        </c:if>

        <c:if test="${hoaDon.trangThai == 2}">
            <h6>xác nhận giao hàng</h6>
        </c:if>

        <c:if test="${hoaDon.trangThai == 3}">
            <h6>xác nhận giao hàng thành công</h6>
        </c:if>

        <c:if test="${hoaDon.trangThai == 6}">
            <h6>xác nhận hàng thành</h6>
        </c:if>
        <span class="close" id="close_modalXacNhanHoaDon">&times;</span>
        <div class="row">
            <c:if test="${hoaDon.trangThai == 1}">
                <form action="/admin/hoa-don/update-trang-thai?trangThai=2&idHD=${hoaDon.id}" method="post" style="margin-right: 10px">
                    <label>Ghi chú</label>
                    <textarea name="ghiChuXacNhan" class="form-control"></textarea>
                    <br>
                    <button class="btn btn-primary">Xác nhận</button>
                </form>
            </c:if>

            <c:if test="${hoaDon.trangThai == 2}">

                <form action="/admin/hoa-don/update-trang-thai?trangThai=3&idHD=${hoaDon.id}" method="post" style="margin-right: 10px">
                    <label>Ghi chú</label>
                    <textarea name="ghiChuXacNhan" class="form-control"></textarea>
                    <br>
                    <button class="btn btn-primary">Xác nhận</button>
                </form>

            </c:if>

            <c:if test="${hoaDon.trangThai == 3}">

                <form action="/admin/hoa-don/update-trang-thai?trangThai=6&idHD=${hoaDon.id}" method="post">
                    <label>Ghi chú</label>
                    <textarea name="ghiChuXacNhan" class="form-control"></textarea>
                    <br>
                    <button class="btn btn-primary">Xác nhận</button>
                </form>

            </c:if>

            <c:if test="${hoaDon.trangThai == 6}">

                <form action="/admin/hoa-don/update-trang-thai?trangThai=4&idHD=${hoaDon.id}" method="post">
                    <label>Ghi chú</label>
                    <textarea name="ghiChuXacNhan" class="form-control"></textarea>
                    <br>
                    <button class="btn btn-primary">Xác nhận</button>
                </form>

            </c:if>
        </div>
    </div>
</div>


<div id="modalXacNhanHuyHoaDon" class="modal">
    <div class="modal-content-1" style="width: 50%">
            <h6>xác nhận huỷ hoá đơn</h6>
        <span class="close" id="close_modalXacNhanHuyHoaDon">&times;</span>
        <div class="row">
                <form action="/admin/hoa-don/update-trang-thai?trangThai=5&idHD=${hoaDon.id}" method="post">
                    <label>Ghi chú</label>
                    <textarea name="ghiChuXacNhan" class="form-control"></textarea>
                    <br>
                    <button class="btn btn-primary">Xác nhận</button>
                </form>
        </div>
    </div>
</div>
