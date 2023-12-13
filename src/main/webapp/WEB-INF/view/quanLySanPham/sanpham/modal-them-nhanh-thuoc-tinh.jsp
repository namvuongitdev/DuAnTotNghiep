<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%-- thêm nhanh chất liệu--%>
<div id="exampleModalChatLieu" class="modal">
    <!-- Modal content -->
    <div class="modal-content-1" style="width: 40%">
        <div class="modal-header">
            <h5 class="modal-title">Thêm dữ liệu</h5>
        </div>
        <form method="post" class="row g-3">
            <div class="modal-body">
                <div class="form-floating">
                    <input ng-model="chatLieu.ten" type="text" name="ten" class="form-control"
                           id="floatingName"
                           placeholder="Chất liệu"/>
                    <label for="floatingName">Chất liệu</label>
                </div>
            </div>
            <div class="modal-footer">
                <button ng-click="addChatLieu()" type="submit" class="btn btn-primary">
                    Xác nhận
                </button>
            </div>
        </form>
    </div>
</div>

<%--thêm nhanh danh mục--%>
<div id="exampleModalDanhMuc" class="modal">
    <!-- Modal content -->
    <div class="modal-content-1" style="width: 40%">
        <div class="modal-header">
            <h5 class="modal-title">Thêm dữ liệu</h5>
        </div>
        <form method="post" class="row g-3">
            <div class="modal-body">
                <div class="form-floating">
                    <input ng-model="danhMuc.ten" type="text" name="ten" class="form-control"
                           id="floatingDanhMuc"
                           placeholder="Danh mục"/>
                    <label for="floatingDanhMuc">Danh mục</label>
                </div>
            </div>
            <div class="modal-footer">
                <button ng-click="addDanhMuc()" type="submit" class="btn btn-primary">
                    Xác nhận
                </button>
            </div>
        </form>
    </div>
</div>

<%--thêm nhanh kiểu dáng--%>
<div id="exampleModalKieuDang" class="modal">
    <!-- Modal content -->
    <div class="modal-content-1" style="width: 40%">
        <div class="modal-header">
            <h5 class="modal-title">Thêm dữ liệu</h5>
        </div>
        <form method="post" class="row g-3">
            <div class="modal-body">
                <div class="form-floating">
                    <input ng-model="kieuDang.ten" type="text" name="ten" class="form-control"
                           id="floatingKieuDang"
                           placeholder="Kiểu dáng"/>
                    <label for="floatingKieuDang">Kiểu dáng</label>
                </div>
            </div>
            <div class="modal-footer">
                <button ng-click="addKieuDang()" type="submit" class="btn btn-primary">
                    Xác nhận
                </button>
            </div>
        </form>
    </div>
</div>

<%--thêm nhanh kích cỡ--%>
<div id="exampleModalKichCo" class="modal">
    <!-- Modal content -->
    <div class="modal-content-1" style="width: 40%">
        <div class="modal-header">
            <h5 class="modal-title">Thêm dữ liệu</h5>
        </div>
        <form method="post" class="row g-3">
            <div class="modal-body">
                <div class="form-floating">
                    <input ng-model="kichCo.ten" type="text" name="ten" class="form-control"
                           id="floatingKichCo"
                           placeholder="Kích cỡ"/>
                    <label for="floatingKichCo">Kích cỡ</label>
                </div>
            </div>
            <div class="modal-footer">
                <button ng-click="addKichCo()" type="submit" class="btn btn-primary">
                    Xác nhận
                </button>
            </div>
        </form>
        <!-- End floating Labels Form -->
    </div>
</div>

<%--thêm nhanh màu sắc--%>
<div id="exampleModalMauSac" class="modal">
    <!-- Modal content -->
    <div class="modal-content-1" style="width: 40%">
        <div class="modal-header">
            <h5 class="modal-title">Thêm dữ liệu</h5>
        </div>
        <form method="post" class="row g-3">
            <div class="modal-body">
                <div class="form-floating">
                    <input ng-model="mauSac.ten" type="text" name="ten" class="form-control"
                           id="floatingMauSac"
                           placeholder="Màu sắc"/>
                    <label for="floatingMauSac">Màu sắc</label>
                </div>
            </div>
            <div class="modal-footer">
                <button ng-click="addMauSac()" type="submit" class="btn btn-primary">
                    Xác nhận
                </button>
            </div>
        </form>
    </div>
</div>