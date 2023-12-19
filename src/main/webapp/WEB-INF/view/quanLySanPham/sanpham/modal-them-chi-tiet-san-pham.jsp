<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>

<div id="modalThemChiTietSanPham" class="modal">
    <!-- Modal content -->
    <div class="modal-content-1">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="exampleModalLabel">Thêm chi tiết sản phẩm</h5>
            </div>
            <div class="modal-body">
                <div class="row">
                    <div class="col l-3">
                        <form action="/admin/chi-tiet-san-pham/add?id=${sp.id}" method="post"
                              enctype="multipart/form-data" modelAttribute="${chiTietSanPham}"
                              id="yourFormId" onsubmit="return false;">
                            <div class="row">
                                <div class="row">
                                    <div class="mb-3">
                                        <label class="form-label">Kích cỡ :</label>
                                        <select id="size" name="size" class="selectpicker" multiple>
                                            <option ng-repeat="kichCo in lstKichCo" value="{{kichCo.id}}">
                                                {{kichCo.ten}}
                                            </option>
                                        </select>
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="mb-3">
                                        <label class="form-label">Màu sắc:</label>
                                        <select id="mauSac" name="mauSac" class="selectpicker" multiple>
                                            <option ng-repeat="mauSac in lstMauSac" value="{{mauSac.id}}">
                                                {{mauSac.ten}}
                                            </option>
                                        </select>
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="mb-3">
                                        <label>Số lượng :</label>
                                        <input type="number" class="form-control" value="1" min="1"
                                               name="soLuong" style="width: 20%">
                                    </div>
                                </div>
                            </div>
                            <div class="text-center">
                                <button class="btn btn-primary" onclick="confirmCTSP(event)">Xác Nhận
                                </button>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
