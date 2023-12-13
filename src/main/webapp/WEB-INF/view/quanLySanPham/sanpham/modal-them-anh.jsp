<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<div id="exampleModalAnh" class="modal">
    <!-- Modal content -->
    <div class="modal-content-1" style="width: 40%">
            <div class="modal-header">
                <h5 class="modal-title" id="exampleModalLabelAnh">Thêm ảnh</h5>
            </div>
            <div class="modal-body row" id="themAnh">
                <div class="col-9">
                    <input class="form-control" type="file" name="file" id="fileAnh">
                </div>
                <div class="col-3">
                    <button class='btn btn-primary' onclick="themAnhMauSac()" data-bs-dismiss="modal">Xác nhận
                    </button>
                </div>
            </div>
    </div>
</div>