<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<div class="row">
    <div>
        <p style="float: right">
            <b>Tiền hàng:</b>
            <span><fmt:formatNumber pattern="#,###" value="${hoaDon.tongTienHang()}"/> đ</span>
        </p>
    </div>
    <div>
        <p style="float: right"><b>Phí vận chuyển:</b>
            <span><fmt:formatNumber pattern="#,###" value="${hoaDon.phiVanChuyen == null ? 0 : hoaDon.phiVanChuyen}"/> đ</span>
        </p>
    </div>
    <c:if test="${hoaDon.tongTienTraHang() != 0}">
        <div>
            <p style="float: right"><b>Hoàn trả :</b> <span style="color: red"><fmt:formatNumber pattern="#,###"
                                                                                                 value="${hoaDon.tongTienTraHang()}"/> đ</span>
            </p>
        </div>
    </c:if>
    <div>
        <p style="float: right"><b>Tổng số tiền :</b> <span style="color: red"><fmt:formatNumber pattern="#,###"
             value="${hoaDon.phiVanChuyen + tongTien}"/> đ</span>
        </p>
    </div>
</div>
