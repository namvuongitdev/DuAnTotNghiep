<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="card">
    <div class="card-body row">
        <div class="row" style="margin-bottom: 10px">
            <div class="row">
                <div class="col-sm-6" style="display: flex">
                    <h4 style="margin-right: 10px">Thông tin khách hàng</h4>
                    <button class="btn btn-light" id="btnThemKhachHang">+</button>
                </div>
            </div>
            <div class="col-sm-6" style="display: flex">
                <button type="button" class="btn btn-secondary"
                        id="btnKhachHang"
                >
                    Chọn tài khoản
                </button>
                <c:if test="${khachHang != null}">
                    <button class="btn btn-danger" style="margin-left: 10px"
                            onclick="window.location.href = '/admin/hoa-don/detail?idHD=${hoaDon.id}'">Huỷ
                        chọn
                    </button>
                </c:if>
            </div>
        </div>
        <hr>
        <div class="col l-3" id="thongTinKhachHang" style="margin-bottom: 10px">
            <c:if test="${khachHang != null}">
                <div><p>Họ tên khách hàng : ${khachHang.hoTen}</p></div>
                <div><p>Số điện thoại :${khachHang.sdt}</p></div>
            </c:if>
            <c:if test="${khachHang == null}">
                <div><p>Họ tên khách hàng : Khách bán lẻ</p></div>
            </c:if>
        </div>
    </div>
</div>
