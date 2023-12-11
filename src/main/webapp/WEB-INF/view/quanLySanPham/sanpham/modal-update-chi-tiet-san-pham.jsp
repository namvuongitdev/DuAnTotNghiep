<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<div id="modalUpdateCTSP" class="modal">
    <!-- Modal content -->
    <div class="modal-content-1" style="width: 40%">
         <div class="row">
             <div class="col-sm-4">
                 <label for="updateSize">Kích cỡ</label>
                 <select name="size" class="form-select" id="updateSize" onchange="getKichCoUpdate(this.value)">

                 </select>
             </div>
              <div class="col-sm-4">
                  <label for="updateMauSac">Màu sắc</label>
                  <select name="mauSac" class="form-select" id="updateMauSac" onchange="getMauSacUpdate(this.value)">

                  </select>
              </div>
             <div class="col-sm-3" style="margin-top: 22px">
                 <button class="btn btn-primary"  onclick="updateSizeAndMauSac(`${sp.id}`)">Update</button>
             </div>
         </div>
    </div>
</div>