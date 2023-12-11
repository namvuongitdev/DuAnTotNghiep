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
        <c:if test="${hoaDon.trangThai != 1}">
            <button class="action-button inHoaDonChiTiet">In hóa đơn
                <i class="fas fa-receipt"></i>
            </button>
        </c:if>
        <input type="hidden" id="idChiTietHoaDon" value="${hoaDon.id}">
        <div class="modal fade inHoaDonModal" tabindex="-1" aria-labelledby="inHoaDonModalLabel"
             aria-hidden="true">
            <div class="modal-dialog modal-dialog-centered">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="inHoaDonModalLabel">In hóa đơn</h5>
                        <button type="button" class="btn-close" data-bs-dismiss="modal"
                                aria-label="Close"></button>
                    </div>
                    <div class="modal-body">
                        Bạn muốn in hóa đơn?
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-primary btn-dong-y">Đồng ý</button>
                        <button type="button" class="btn btn-secondary btn-khong"
                                data-bs-dismiss="modal">Không
                        </button>
                    </div>
                </div>
            </div>
        </div>
        <p style="float: right"><b>Tổng số tiền :</b> <span style="color: red"><fmt:formatNumber pattern="#,###"
             value="${hoaDon.phiVanChuyen + tongTien}"/> đ</span>
        </p>
    </div>
</div>
