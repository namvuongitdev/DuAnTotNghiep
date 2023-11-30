<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="/css/modal.css">
</head>
<style>
    #showTongTinKhachHang {
        padding: 8px;
        text-align: left;
        border-bottom: 1px solid #ddd;
    }

    #showTongTinKhachHang:hover {
        background-color: #f5f5f5;
    }
</style>
<body>
<div id="modalDiaChiKhachHangOnline" class="modal">
    <div class="modal-content-1" style="width: 35%">
        <span class="close" id="close_diaChiKhachHangOnline">&times;</span>
        <div class="row">
            <c:if test="${diaChis != null}">
                <div><span style="color: #005cbf" onclick="themMoiDiaChiOnline()">+ Thêm mới</span></div>
                <c:forEach items="${diaChis}" var="thongTinKhachHang">
                    <div class="row" id="showTongTinKhachHang">
                        <div>
                            <div><p>Họ tên : ${thongTinKhachHang.hoTen}</p></div>
                            <div><p>Số điện thoại : ${thongTinKhachHang.sdt}</p></div>
                            <div><p>Địa chỉ : ${thongTinKhachHang.diaChi}</p></div>
                            <c:if test="${!thongTinKhachHang.diaChiMacDinh}">
                                <div>
                                    <button class="btn btn-danger">Mặc đinh</button>
                                </div>
                            </c:if>
                        </div>
                     <div>
                         <button style="float: right" class="btn btn-warning"
                                 onclick="findDiaChiOnline({hoTen:`${thongTinKhachHang.hoTen}` ,
                                         sdt:`${thongTinKhachHang.sdt}` , diaChi:`${thongTinKhachHang.diaChi}`})">
                             Chọn
                         </button>
                     </div>
                    </div>
                </c:forEach>
            </c:if>
        </div>
    </div>
</div>
</body>
</html>
