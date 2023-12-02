<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<div class="container">
    <c:if test="${hoaDon.trangThai == 1 || hoaDon.trangThai == 2}">
        <c:if test="${hoaDon.phuongThucThanhToan != 4}">
            <div class="row">
                <div><a style="float: right ; margin-bottom: 20px" class="btn btn-primary" data-bs-toggle="modal"
                        data-bs-target="#extraLargeModal"
                        name="1" onclick="getSanPham(this.name)">
                    Thêm sản phẩm
                </a></div>
            </div>
        </c:if>
    </c:if>
    <div class="row">
        <table class="table">
            <thead>
            <tr>
                <th data-field="state" data-checkbox="true"></th>
                <th scope="col">Tên sản phẩm</th>
                <th scope="col">Đơn giá</th>
                <th scope="col">Số lượng</th>
                <th scope="col">Thành tiền</th>
                <th scope="col">Thao tác</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${listHoaDonChiTiet}" var="hdct">
                   <tr>
                       <td></td>
                       <td>
                           <div style="display: flex; align-items: center;">
                               <img src="/image/${hdct.img}" alt=""
                                    style="margin-right: 10px;" width="100px" height="100px">
                               <div>
                                   <h6>
                                       <strong>
                                           <p style="text-transform: uppercase" class="TenSP">${hdct.tenSanPham}</p>
                                       </strong>
                                   </h6>
                                   <div class="ThongTinSP">
                                       <span>${hdct.tenMauSac}</span>
                                       <span>/</span>
                                       <span>${hdct.tenKichCo}</span>
                                   </div>
                               </div>
                           </div>
                       </td>
                       <td><fmt:formatNumber pattern="#,###" value="${hdct.donGia}"/> đ</td>
                       <td>${hdct.soLuong}</td>
                       <td><fmt:formatNumber pattern="#,###" value="${hdct.soLuong * hdct.donGia}"/> đ
                       </td>
                       <c:if test="${hoaDon.trangThai == 4}">
                           <c:choose>
                               <c:when test="${hdct.trangThai == 2}">
                                   <td>
                                       <button class="btn btn-danger rounded-pill">Đã hoàn trả</button>
                                   </td>
                               </c:when>
                               <c:otherwise>
                                   <c:if test="${trangThaiTraHang == null}">
                                       <td>
                                           <button class="btn btn-outline-danger" onclick="getHDCT(`${hdct.id}` , `${hoaDon.id}`)">Trả
                                               hàng
                                           </button>
                                       </td>
                                   </c:if>
                               </c:otherwise>
                           </c:choose>
                       </c:if>
                       <c:if test="${hoaDon.trangThai == 1 || hoaDon.trangThai == 2}">
                           <c:if test="${hoaDon.phuongThucThanhToan != 4}">
                               <td>
                                   <button class="btn btn-outline-primary" onclick="updateSoLuong(`${hdct.id}`)">Cập nhập
                                   </button>
                                   <button class="btn btn-outline-danger" onclick="xoaSanPham(`${hdct.id}`)">Huỷ</button>
                               </td>

                           </c:if>
                       </c:if>
                   </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</div>
