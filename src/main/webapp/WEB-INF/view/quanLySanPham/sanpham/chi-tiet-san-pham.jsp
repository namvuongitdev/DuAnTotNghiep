<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<c:if test="${listChiTietSanPhamBySP != null}">
    <div class="col l-5" style="margin-bottom: 30px">
        <p class="text-center"><b>Thêm ảnh theo màu sắc</b></p>
        <div class="row">
            <select name="mauSacAnh" class="form-select"
                    style="width: 50%;margin-left: 90px"
                    onchange="findAnhMauSac(`${sp.id}` , this.value)">
                <option value="">Màu sắc</option>
                <c:forEach items="${listMauSacCTSP}" var="mauSacCTSP">
                    <option value="${mauSacCTSP.id}">
                            ${mauSacCTSP.ten}
                    </option>
                </c:forEach>
            </select>
        </div>
    </div>
</c:if>
<%--table--%>
<div class="col-8" style="padding-left: 50px">
    <table class="table">
        <thead>
        <tr style="text-align: center">
            <th></th>
            <th scope="col">Kích cỡ
                <a data-bs-toggle="modal" data-bs-target="#exampleModalKichCo">
                    <i class="bi bi-plus-circle"></i>
                </a>
            </th>
            <th scope="col">Màu sắc
                <a data-bs-toggle="modal" data-bs-target="#exampleModalMauSac">
                    <i class="bi bi-plus-circle"></i>
                </a>
            </th>
            <th scope="col">Số lượng</th>
            <th scope="col">Trạng thái</th>
            <th scope="col">Thao tác</th>
        </tr>
        </thead>
        <tbody>
        <form action="/admin/chi-tiet-san-pham/update-chi-tiet-san-pham?idSP=${sp.id}"
              method="post" modelAttribute="${chiTietSanPham}">
            <c:forEach items="${listChiTietSanPhamBySP}" var="ctsp">
                <tr style="text-align: center">
                    <td style="padding-top: 20px">
                        <input type="checkbox" id="checkbox${ctsp.id}" value="${ctsp.id}"
                               name="chiTietSanPhams" class="checkbox">
                    </td>
                    <td id="kichCoCTSP">
                        <p id="pKichCo${ctsp.id}"
                        >${ctsp.size.ten}</p>
                        <select name="s" class="form-select" id="selectKichCo${ctsp.id}" style="display: none;">
                            <c:forEach items="${listKichCo}" var="kichCo" >
                                <option value="${kichCo.id}" ${ctsp.size.id==kichCo.id?'selected':''}>${kichCo.ten}</option>
                            </c:forEach>
                        </select>
                    </td>
                    <td>
                        <p id="pMauSac${ctsp.id}"
                           style="display: block;">${ctsp.mauSac.ten}</p>
                        <select name="ms" class="form-select"
                                id="selectMauSac${ctsp.id}" style="display: none;">
                            <c:forEach items="${listMuaSac}" var="mauSac">
                                <option value="${mauSac.id}" ${ctsp.mauSac.id==mauSac.id?'selected':''}>${mauSac.ten}</option>
                            </c:forEach>
                        </select>
                    </td>
                    <td style="width: 110px">
                        <p id="pSoLuong${ctsp.id}"
                           style="display: block;">${ctsp.soLuong}</p>
                        <input style="display: none;" type="number" class="form-control"
                               id="soLuongCTSP${ctsp.id}" value="${ctsp.soLuong}"
                               name="sl" min="1" required/>
                    </td>
                    <td>
                        <button style="--bs-btn-padding-y: .56rem; --bs-btn-padding-x: .5rem; --bs-btn-font-size: .75rem;"
                                type="button" value="${ctsp.trangThai}"
                                onclick="myfunction({trangThai:`${ctsp.trangThai}`,idctsp:`${ctsp.id}`,idsp:`${ctsp.sanPham.id}`})"
                                class="${ctsp.trangThai == 0 ? 'btn btn-danger' : 'btn btn-success'}">
                                ${ctsp.trangThai == 0 ? 'Ngừng kinh doanh' : 'Kinh doanh'}
                        </button>
                    </td>
                    <td style="text-align: center">
                        <a class="btn btn-secondary" onclick="modalQrCode(`${ctsp.id}`)">
                            QR
                        </a>
                    </td>
                </tr>
            </c:forEach>
            <div style="float: right;display: flex">
                <a type="button" class="btn btn-primary" onclick="modalThemChiTietSanPham()" style="margin-right: 10px">Thêm
                </a>
                <button class="btn btn-secondary">
                    update
                </button>
            </div>
        </form>
        </tbody>
    </table>
</div>
<%--hiển thị ảnh theo màu sắc--%>
<div class="row" id="hienThiAnhMauSac">
</div>
<div class="row" id="anhMacDinhSanPham" style="margin-top: 20px">
    <div class="pagination justify-content-center">
        <img src="/image/${sp.img}" alt="" class="img-thumbnail"
             style="width: 200px;height: 200px">
    </div>
</div>