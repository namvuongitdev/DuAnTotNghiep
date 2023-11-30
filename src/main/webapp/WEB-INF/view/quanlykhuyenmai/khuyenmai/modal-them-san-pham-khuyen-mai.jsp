
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div id="modalKhuyenMai" class="modal">
    <div class="modal-content-1">
        <span class="close" id="close_khuyenMai">&times;</span>
        <div class="row">
            <div class="row">
                <div class="col-sm-6">
                    <div class="mb-3 form-floating">
                        <input type="text" class="form-control" name="tenSanPhamThem" disabled
                               id="tenSanPhamThem">
                        <label for="tenSanPhamThem">Tên sản phẩm</label>
                    </div>
                </div>
                <div class="col-sm-6">
                    <div class="mb-3 form-floating">
                        <input type="text" class="form-control" name="maSanPhamThem" disabled
                               id="maSanPhamThem">
                        <label for="maSanPhamThem">Mã sản phẩm</label>
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="col-sm-6">
                    <input type="radio" class="btn-check" name="loaiGiamGia"
                           id="success-outlined" onchange="selectLoaiGiamGia(this.value)" autocomplete="off" value="true" checked>
                    <label class="btn btn-outline-secondary"
                           for="success-outlined">%</label>

                    <input type="radio" class="btn-check" name="loaiGiamGia"
                           id="danger-outlined" autocomplete="off" value="false" onchange="selectLoaiGiamGia(this.value)">
                    <label class="btn btn-outline-secondary"
                           for="danger-outlined">VND</label>
                </div>
                <div class="col-sm-6">
                    <div class="mb-3 form-floating">
                        <input type="number" class="form-control" name="mucGiam"
                               id="mucGiam">
                        <label for="mucGiam">Mức giảm</label>
                    </div>
                </div>
            </div>
            <div>
                <button class="btn btn-primary" onclick="addKhuyenMaiCT(`${dataKhuyenMai.id}`)">Xác nhận</button>
            </div>
        </div>
    </div>
</div>
