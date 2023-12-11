<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<form:form action="/admin/hoa-don/filter" method="get" modelAttribute="hoaDonFillter">
    <div class="row" style="margin-bottom: 10px">
        <jsp:include page="filter-trang-thai.jsp"></jsp:include>
    </div>
    <div class="row">
        <div class="col-sm-3" style="margin-top: 25px">
            <label class="col-form-label" for="search"></label>
            <input type="text" class="form-control" id="search" name="search" value="${fillter.search}"
                   placeholder="Tìm hóa đơn"
            />
        </div>
        <div class="col-sm-2">
            <label class="col-form-label">Từ ngày</label>
            <input type="date" value="${fillter.dateBegin}" name="dateBegin"
                   class="form-control"/>
        </div>
        <div class="col-sm-2">
            <label class="col-form-label">Đến ngày</label>
            <input type="date" value="${fillter.dateEnd}" name="dateEnd"
                   class="form-control"/>
        </div>
        <div class="col-sm-2">
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
     <input type="text" name="trangThai" value="${fillter.trangThai}" style="display: none">
        </div>
        <div class="col-sm-3" style="margin-top: 35px">
            <div>
                <button type="submit" class="btn btn-primary" value="${fillter.trangThai}">Tìm kiếm</button>
                <a href="/admin/hoa-don/hien-thi" class="btn btn-warning">Làm mới</a>
            </div>
        </div>
    </div>

</form:form>