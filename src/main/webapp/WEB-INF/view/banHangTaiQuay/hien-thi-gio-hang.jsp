<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<div class="col l-3" style="background-color: white">
    <table>
        <thead>
        <tr>
            <th scope="col">Sản phẩm</th>
            <th scope="col">Số lượng</th>
            <th scope="col">Thành tiền</th>
            <th scope="col">Thao tác</th>
        </tr>
        </thead>
        <tbody id="hienThiGioHang">
        <c:forEach items="${sanPhamGioHang}" var="sanPham">
            <tr id="${sanPham.id}">
                <td>
                    <div class="row">
                        <div class="col l-3">
                            <img src="/image/${sanPham.img}"
                                 style="width: 80px; height: 80px">
                        </div>
                        <div class="col l-3">
                            <h5>${sanPham.tenSanPham}</h5>
                            <p style="color: #03AA28"><fmt:formatNumber pattern="#,###"
                                                                        value="${sanPham.donGia}"></fmt:formatNumber></p>
                            <p>size : ${sanPham.kichCo}</p>
                            <p>màu sắc : ${sanPham.mauSac}</p>
                        </div>
                    </div>
                </td>
                <td style="width: 110px ;"><input
                        onchange="updateSoLuong(this.value , {id:`${sanPham.id}`,
                                soLuongHDCT:`${sanPham.soLuong}`,
                                soLuongCTSP:`${sanPham.soLuongSanPham}`,}
                                )" type="number"
                        name="soLuong" class="form-control" value="${sanPham.soLuong}" min="1"
                />
                </td>
                <td id="thanhTien"><fmt:formatNumber pattern="#,###"
                                                     value="${sanPham.soLuong * sanPham.donGia}"></fmt:formatNumber></td>
                <td><a onclick="deleteSanPhamTrongGioHang(`${sanPham.id}`)" class="btn btn-danger">Xoá khỏi giỏ</a></td>
            </tr>
        </c:forEach>
        <tr>
            <td style="color: #E43535" id="tongTienTrongGioHang">Tổng tiền :<fmt:formatNumber pattern="#,###"
                                                                    value="${tongTien}"></fmt:formatNumber></td>
        </tr>
        </tbody>
    </table>
</div>