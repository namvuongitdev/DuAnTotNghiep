<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<form:form action="/admin/hoa-don/filter" method="get" modelAttribute="hoaDonFillter">
    <div class="row d-flex justify-content-center">
        <div class="col-sm-3">
            <label class="col-form-label">Tìm kiếm hoá đơn</label>
            <input type="text" class="form-control" name="search" value="${fillter.search}"
                   placeholder="Tìm hóa đơn"
            />
        </div>
        <div class="col-sm-3">
            <label class="col-form-label">Từ ngày</label>
            <input type="date" value="${fillter.dateBegin}" name="dateBegin"
                   class="form-control"/>
        </div>
        <div class="col-sm-3">
            <label class="col-form-label">Đến ngày</label>
            <input type="date" value="${fillter.dateEnd}" name="dateEnd"
                   class="form-control"/>
        </div>
    </div>
    <div class="row d-flex justify-content-center">
        <div class="col-sm-3">
            <label class="col-form-label">Loại hóa đơn</label>
            <select class="form-select" name="loaiHoaDon"
                    aria-label="Default select example">
                <option value="">Tất cả</option>
                <option value="true" ${fillter.loaiHoaDon == 'true'? 'selected':''}
                        class="text-warning">Đơn hàng
                </option>
                <option value="false" ${fillter.loaiHoaDon == 'false'? 'selected':''}
                        class="text-success">Tại quầy
                </option>
            </select>
        </div>
        <div class="col-sm-3">
            <label class="col-form-label">Trạng thái</label>
            <select class="form-select" name="trangThai"
                    aria-label="Default select example">
                <option value="">Tất cả</option>
                <option value="1" ${fillter.trangThai == '1'? 'selected':''}
                        >Chờ xác nhận
                </option>
                <option value="2" ${fillter.trangThai == '2'? 'selected':''}
                       >Đã xác nhận
                </option>
                <option value="3" ${fillter.trangThai == '3'? 'selected':''}
                        >Giao Hàng
                </option>
                <option value="5" ${fillter.trangThai == '5'? 'selected':''}
                       >Huỷ
                </option>
                <option value="6" ${fillter.trangThai == '6'? 'selected':''}
                        >Giao thành công
                </option>
                <option value="4" ${fillter.trangThai == '4'? 'selected':''}
                       >Đã hoàn thành
                </option>
            </select>
        </div>
        <div style="text-align: center" class="mt-3">
            <button type="submit" class="btn btn-primary">Tìm kiếm</button>
            <a href="/admin/hoa-don/hien-thi" class="btn btn-warning">Tìm kiếm</a>
        </div>

    </div>
    <br>
</form:form>