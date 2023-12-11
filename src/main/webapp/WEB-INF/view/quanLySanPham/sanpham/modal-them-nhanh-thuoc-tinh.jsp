<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%-- thêm nhanh chất liệu--%>
<div class="modal" id="exampleModalChatLieu" tabindex="-1">
    <div class="modal-dialog modal-dialog-scrollable">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title">Thêm dữ liệu</h5>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
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
                    <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Đóng</button>
                    <button ng-click="addChatLieu()" type="submit" class="btn btn-primary">
                        Xác nhận
                    </button>
                </div>
            </form>
        </div>
    </div>
</div>

<%--thêm nhanh danh mục--%>
<div class="modal" id="exampleModalDanhMuc" tabindex="-1">
    <div class="modal-dialog modal-dialog-scrollable">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title">Thêm dữ liệu</h5>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
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
                    <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Đóng</button>
                    <button ng-click="addDanhMuc()" type="submit" class="btn btn-primary">
                        Xác nhận
                    </button>
                </div>
            </form>
        </div>
    </div>
</div>

<%--thêm nhanh kiểu dáng--%>
<div class="modal" id="exampleModalKieuDang" tabindex="-1">
    <div class="modal-dialog modal-dialog-scrollable">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title">Thêm dữ liệu</h5>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
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
                    <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Đóng</button>
                    <button ng-click="addKieuDang()" type="submit" class="btn btn-primary">
                        Xác nhận
                    </button>
                </div>
            </form>
        </div>
    </div>
</div>

<%--thêm nhanh kích cỡ--%>
<div class="modal" id="exampleModalKichCo" tabindex="-1">
    <div class="modal-dialog modal-dialog-scrollable">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title">Thêm dữ liệu</h5>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
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
                    <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Đóng</button>
                    <button ng-click="addKichCo()" type="submit" class="btn btn-primary">
                        Xác nhận
                    </button>
                </div>
            </form>
            <!-- End floating Labels Form -->
        </div>
    </div>
</div>

<%--thêm nhanh màu sắc--%>
<div class="modal" id="exampleModalMauSac" tabindex="-1">
    <div class="modal-dialog modal-dialog-scrollable">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title">Thêm dữ liệu</h5>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
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
                    <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Đóng</button>
                    <button ng-click="addMauSac()" type="submit" class="btn btn-primary">
                        Xác nhận
                    </button>
                </div>
            </form>
        </div>
    </div>
</div>