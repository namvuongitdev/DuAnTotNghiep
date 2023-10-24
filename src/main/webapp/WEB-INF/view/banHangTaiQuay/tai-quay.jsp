<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<div class="col l-3"></div>
<div class="col l-3">
    <h4>Thông tin thanh toán</h4>
    <div class="form-check form-switch">
        <input class="form-check-input" type="checkbox" id="mySwitch"
               name="${khachHang.id}" ${hoaDon.loaiHoaDon == false ? '' : 'checked'}>
        <label class="form-check-label" for="mySwitch">Đặt hàng</label>
    </div>
    <div>
      <span>Tiền Hàng : <fmt:formatNumber pattern="#,###"
                                                                    value="${tongTien}"></fmt:formatNumber></span>
    </div>
    <div>
        <h6>Khách cần trả : <fmt:formatNumber pattern="#,###"
                                              value="${tongTien}"></fmt:formatNumber></h6>
    </div>
    <div>
        <h6>Hình thức thanh toán : </h6>
        <select class="form-select" name="hinhThucThanhToan" style="width: 200px">
            <option value="true">Tiền mặt</option>
            <option value="false">Chuyển Khoản</option>
        </select>
    </div>
    <br>
    <div id="hinhThucThanhToan">
        <div class="mb-3 form-floating">
            <input class="form-control" type="number" style="width: 50%"
                   name="soTienThanhToan"
                   id="tienKhachDua"
                   onkeydown="getTienKhachDua({tienKhacDua:this.value , tongTien:`${tongTien}`})" value="${soTienKhachTra}">
            <label for="tienKhachDua">Tiền khách đưa</label>
            <c:if test="${message != null}">
                <span style="color: #E43535">${message}</span>
            </c:if>
            <span id="tienThuaCuaKhach" name="tienThuaTraKhach"
                  style="color: #03AA28"></span>
        </div>
    </div>

    <div class="mb-3 form-floating">
                            <textarea name="moTa" class="form-control" id="moTa"
                                      style="width: 50%"></textarea>
        <label for="moTa">Mô Tả</label>
    </div>
    <div>
        <button class="btn btn-primary" id="xacNhanThanhToan"
        >Xác nhận thanh toán
        </button>
    </div>
</div>