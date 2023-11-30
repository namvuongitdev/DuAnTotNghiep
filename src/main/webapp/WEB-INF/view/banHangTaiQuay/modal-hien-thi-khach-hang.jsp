
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div id="khachHangModal" class="modal">
    <!-- Modal content -->
    <div class="modal-content-1">
        <span class="close" id="close_khachHang">&times;</span>
        <div class="row">
            <div class="row" style="margin-bottom: 30px">
                <div class="col-sm-4">
                    <input class="form-control" type="text" name="search" id="search-khachHang"
                           placeholder="tìm kiếm">
                </div>
                <div class="col-sm-2">
                    <button class="btn btn-primary" onclick="timKiemKhachHang()">Tìm kiếm</button>
                </div>
                <div class="col-sm-2">
                    <button class="btn btn-warning" onclick="lamMoi()">Làm mới</button>
                </div>
            </div>
            <div class="row">
                <table>
                    <thead>
                    <tr>
                        <th scope="col">Họ tên</th>
                        <th scope="col">Email</th>
                        <th scope="col">SĐT</th>
                        <th scope="col">Tài khoản</th>
                        <th scope="col">Thao tác</th>
                    </tr>
                    </thead>
                    <tbody id="khachHang">

                    </tbody>
                </table>
            </div>
            <div class="container-fluid mt-5">
                <nav aria-label="Page navigation example">
                    <ul class="pagination justify-content-center" id="phanTrangKhachHang">
                    </ul>
                </nav>
            </div>
        </div>
    </div>
</div>
