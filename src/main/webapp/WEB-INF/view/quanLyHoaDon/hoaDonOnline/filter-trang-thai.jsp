<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<nav class="navbar navbar-expand-lg navbar-light bg-light">
    <div class="container-fluid">
        <div>
            <label class="btn btn-outline-secondary" for="tatCa" ${fillter.trangThai == null || fillter.trangThai == "0" ? 'style="background: #95999c ; color: white"':''}><b>Tất cả</b></label>
            <input type="submit" name="trangThai" value="0" id="tatCa" class="btn-check">
        </div>
        <div>
            <label class="btn btn-outline-secondary" for="choXacNhan" ${fillter.trangThai == '1'? 'style="background: #95999c ; color: white"':''}><b>Chờ xác nhận</b></label>
            <input type="submit" name="trangThai" value="1" id="choXacNhan" class="btn-check">
        </div>
        <div>
            <label class="btn btn-outline-secondary" for="daXacNhan" ${fillter.trangThai == '2'? 'style="background: #95999c ; color: white"':''}><b>Đã xác nhận</b></label>
            <input type="submit" name="trangThai" value="2" id="daXacNhan" class="btn-check">
        </div>
        <div>
            <label class="btn btn-outline-secondary" for="giaoHang" ${fillter.trangThai == '3'? 'style="background: #95999c ; color: white"':''}><b>Giao Hàng</b></label>
            <input type="submit" name="trangThai" value="3" id="giaoHang" class="btn-check">
        </div>
        <div>
            <label class="btn btn-outline-secondary" for="huy" ${fillter.trangThai == '5'? 'style="background: #95999c ; color: white"':''}><b>Huỷ</b></label>
            <input type="submit" name="trangThai" value="5" id="huy" class="btn-check">
        </div>
        <div>
            <label class="btn btn-outline-secondary" for="giaoThanhCong" ${fillter.trangThai == '6'? 'style="background: #95999c ; color: white"':''}><b>Giao thành công</b></label>
            <input type="submit" name="trangThai" value="6" id="giaoThanhCong" class="btn-check">
        </div>
        <div>
            <label class="btn btn-outline-secondary" for="daHoanThanh" ${fillter.trangThai == '4'? 'style="background: #95999c ; color: white"':''}><b>Đã hoàn thành</b></label>
            <input type="submit" name="trangThai" value="4" id="daHoanThanh" class="btn-check">
        </div>
    </div>
</nav>
