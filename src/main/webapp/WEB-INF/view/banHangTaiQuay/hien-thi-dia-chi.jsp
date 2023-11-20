<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<c:if test="${khachHang != null}">
    <div id="modalDiaChiKhachHang" class="modal">
        <div class="modal-content-1">
            <span class="close" id="close_diaChiKhachHang">&times;</span>
            <div class="row">
                <c:if test="${khachHang.diaChis != null}">
                    <c:forEach items="${khachHang.diaChis}" var="thongTinKhachHang">
                        <div class="row" id="showTongTinKhachHang"
                             onclick="findDiaChi({hoTen:`${thongTinKhachHang.hoTen}` , sdt:`${thongTinKhachHang.sdt}` , diaChi:`${thongTinKhachHang.diaChi}`})">
                            <div><p>Họ tên : ${thongTinKhachHang.hoTen}</p></div>
                            <div><p>Số điện thoại : ${thongTinKhachHang.sdt}</p></div>
                            <div><p>Địa chỉ : ${thongTinKhachHang.diaChi}</p></div>
                        </div>
                    </c:forEach>
                </c:if>
            </div>
        </div>
    </div>
</c:if>
