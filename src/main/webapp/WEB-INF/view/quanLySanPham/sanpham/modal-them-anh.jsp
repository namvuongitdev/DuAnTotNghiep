<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<div class="modal" id="exampleModalAnh" tabindex="-1" aria-labelledby="exampleModalLabelAnh"
     aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="exampleModalLabelAnh">Thêm ảnh</h5>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <div class="modal-body" id="themAnh">
                <input type='file' name='file' id="fileAnh">
                <button class='btn btn-primary' onclick="themAnhMauSac()" data-bs-dismiss="modal">Xác nhận
                </button>
            </div>
        </div>
    </div>
</div>