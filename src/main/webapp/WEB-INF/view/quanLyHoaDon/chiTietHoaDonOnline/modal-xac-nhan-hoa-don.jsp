<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div id="modalXacNhanHoaDon" class="modal">
    <div class="modal-content-1" style="width: 50%">
        <c:if test="${hoaDon.trangThai == 1}">
            <h5 class="mb-3"><b>Xác nhận đơn hàng</b></h5>
        </c:if>

        <c:if test="${hoaDon.trangThai == 2}">
            <h5 class="mb-3"><b>Xác nhận giao hàng</b></h5>
        </c:if>

        <c:if test="${hoaDon.trangThai == 3}">
            <h5 class="mb-3"><b>Xác nhận giao hàng thành công</b></h5>
        </c:if>

        <c:if test="${hoaDon.trangThai == 6}">
            <h5 class="mb-3"><b>Xác nhận hàng thành</b></h5>
        </c:if>
        <span class="close" id="close_modalXacNhanHoaDon">&times;</span>
        <div class="row">
            <c:if test="${hoaDon.trangThai == 1}">
                <form action="/admin/hoa-don/update-trang-thai?trangThai=2&idHD=${hoaDon.id}" method="post" style="margin-right: 10px">
                    <label>Ghi chú</label>
                    <textarea name="ghiChuXacNhan" class="form-control"></textarea>
                    <br>
                    <button class="btn btn-primary" onclick="return xacDonHang()">Xác nhận</button>
                </form>
            </c:if>

            <c:if test="${hoaDon.trangThai == 2}">
                <form action="/admin/hoa-don/update-trang-thai?trangThai=3&idHD=${hoaDon.id}" method="post" style="margin-right: 10px">
                    <label>Ghi chú</label>
                    <textarea name="ghiChuXacNhan" class="form-control"></textarea>
                    <br>
                    <button class="btn btn-primary" onclick="return xacDonHang()">Xác nhận</button>
                </form>

            </c:if>

            <c:if test="${hoaDon.trangThai == 3}">

                <form action="/admin/hoa-don/update-trang-thai?trangThai=6&idHD=${hoaDon.id}" method="post">
                    <label>Ghi chú</label>
                    <textarea name="ghiChuXacNhan" class="form-control"></textarea>
                    <br>
                    <button class="btn btn-primary" onclick="return xacDonHang()">Xác nhận</button>
                </form>

            </c:if>

            <c:if test="${hoaDon.trangThai == 6}">

                <form action="/admin/hoa-don/update-trang-thai?trangThai=4&idHD=${hoaDon.id}" method="post">
                    <label>Ghi chú</label>
                    <textarea name="ghiChuXacNhan" class="form-control"></textarea>
                    <br>
                    <button class="btn btn-primary" onclick="return xacDonHang()">Xác nhận</button>
                </form>

            </c:if>
        </div>
    </div>
</div>


<div id="modalXacNhanHuyHoaDon" class="modal">
    <div class="modal-content-1" style="width: 50%">
        <h5 class="mb-3"><b>Xác nhận huỷ hoá đơn</b></h5>
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
