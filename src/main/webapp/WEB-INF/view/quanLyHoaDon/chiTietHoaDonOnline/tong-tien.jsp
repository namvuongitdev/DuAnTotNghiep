<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<div class="row">
    <div>
        <p style="float: right">
            <b>Tiền hàng:</b>
            <span><fmt:formatNumber pattern="#,###" value="${hdc}"/> đ</span>
        </p>
    </div>
    <div>
        <p style="float: right"><b>Phí vận chuyển:</b> <input id="PVC" type="text"
                                                              style="border: none;width: 100px;display: ${hd.trangThai==5?'none':''}" ${hd.trangThai==1?'':hd.trangThai==2?'':'disabled'}
                                                              onblur="doiPhiShip({ship:this.value,idhd:`${hd.id}`})"
                                                              value="<fmt:formatNumber pattern="#,###"  value="${hd.phiVanChuyen}"/>"/> đ
        </p>
    </div>
    <div>
        <p style="float: right"><b>Tổng số tiền :</b> <span style="color: red"><fmt:formatNumber pattern="#,###" value="${hd.tongTien}"/> đ</span>
        </p>
    </div>
</div>
