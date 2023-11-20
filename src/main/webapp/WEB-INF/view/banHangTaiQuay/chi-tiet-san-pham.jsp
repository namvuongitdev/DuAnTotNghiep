
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div id="myModal" class="modal">
    <!-- Modal content -->
    <div class="modal-content-1">
        <span class="close" id="close_ctsp">&times;</span>
        <div class="row" id="chiTietSanPham">
            <div class="col l-3" id="img">

            </div>
            <div class="col l-3">
                <div class="row" id="sp">
                </div>
                <div class="row">
                    <label>Mùa sắc : </label>
                    <div class="form-check" id="mauSac">
                    </div>
                </div>
                <br>
                <div class="row">
                    <label>Kích cỡ : </label>
                    <div class="form-check" id="kichCo">
                    </div>
                </div>
                <br>
                <div class="row">
                    <div style="width: 110px ;">
                        <label for="soLuongTon">Số lượng :</label>
                        <input type="number" value="1" min="1" class="form-control" id="soLuongTon" name="${hoaDon.id}"
                        >
                    </div>
                    <div id="soLuong" class="form-text">
                    </div>
                </div>
                <br>
                <div class="row">
                    <button type="button" id="themVaoGioHang" name=""
                            onclick="themSanPhamVaoGioHang({idCTSP:this.name , idKhachHang:`${khachHang.id}`})"
                            class="btn btn-primary">Thêm vào giỏ hàng
                    </button>
                </div>
            </div>
        </div>
    </div>
</div>
