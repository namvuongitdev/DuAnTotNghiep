<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css"
          rel="stylesheet" integrity="sha384-9ndCyUaIbzAi2FUVXJi0CjmCapSmO7SnpJef0486qhLnuZ2cdeRhO02iuK6FUUVM"
          crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"
            integrity="sha384-geWF76RCwLtnZ8qwWowPQNguL3RmwHVBC9FhGdlKrxdiJJigb/j/68SIy3Te4Bkz"
            crossorigin="anonymous"></script>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.1/font/bootstrap-icons.css">

</head>
<body>
<%--navbar--%>
<jsp:include page="../../sidebar/navbar.jsp"/>
<%--sidebar--%>
<div class="container-fluid">
    <div class="row flex-nowrap">
        <jsp:include page="../../sidebar/sidebar.jsp"/>
        <div class="col py-3">
            <div class="pagetitle">
                <h3>Kích cỡ</h3>
                <nav>
                    <ol class="breadcrumb">
                        <li class="breadcrumb-item"><a href="/trangchu" style="text-decoration: none; color: black">Trang chủ</a></li>
                        <li class="breadcrumb-item ">Quản lý sản phẩm</li>
                        <li class="breadcrumb-item active">Kích cỡ</li>
                    </ol>
                </nav>
            </div><!-- End Page Title -->
            <section class="section">
                <div class="card">
                    <div class="card-body">
                        <h5 class="card-title">Sửa dữ liệu</h5>

                        <!-- Floating Labels Form -->
                        <%--@elvariable id="size" type=""--%>
                        <form:form method="post" action="/size/update/${size.id}" modelAttribute="size" class="row g-3">
                            <div class="col-md-12">
                                <div class="form-floating">
                                    <form:input type="text" path="ten" class="form-control" id="floatingName" placeholder="Kích cỡ" readonly="true"/>
                                    <form:label for="floatingName" path="ten">Kích cỡ</form:label>
                                    <span style="color: red">${erro}</span>

                                </div>
                            </div>
                            <div class="text-center">
                                <form:button disabled="true" id="disabled" type="submit" class="btn btn-primary" onclick="if(confirm('Bạn có chắc chắn muốn sửa không?')==true){ }else{ alert('Sửa thất bại');return false; }">
                                    Xác nhận
                                </form:button>
                            </div>
                        </form:form><!-- End floating Labels Form -->
                    </div>
                </div>
                <br><br>
                <%--            Table--%>
                <div class="row">
                    <div class="col-lg-12">

                        <div class="card">
                            <div class="card-body row">
                                <h5 class="card-title col-9">Danh sách</h5>
                                <div class="col-2 card-title" style="margin-left: 72px;">
                                    <button type="button" class="btn btn-primary" title="Thêm dữ liệu" data-bs-toggle="modal" data-bs-target="#modalDialogScrollable">
                                        <i class="bi bi-plus-circle"></i>
                                    </button>
                                </div>

                                <!-- Table with stripped rows -->
                                <table class="table">
                                    <thead>
                                    <tr>
                                        <th scope="col">STT</th>
                                        <th scope="col">Kích cỡ</th>
                                        <th scope="col">Ngày tạo</th>
                                        <th scope="col">Ngày sửa</th>
                                        <th scope="col">Trạng thái</th>
                                        <th scope="col">Thao tác</th>
                                    </tr>
                                    </thead>
                                    <tbody>
                                    <c:forEach items="${list}" var="list" varStatus="i">
                                        <tr>
                                            <th scope="row">${i.index+1}</th>
                                            <td>${list.ten}</td>
                                            <td><fmt:formatDate value="${list.ngayTao}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
                                            <td><fmt:formatDate value="${list.ngaySua}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
                                            <td>
                                                <button style="--bs-btn-padding-y: .25rem; --bs-btn-padding-x: .5rem; --bs-btn-font-size: .75rem;"
                                                        type="button" class="${list.trangThai == 0 ? 'btn btn-danger' : 'btn btn-success'}">
                                                        ${list.trangThai == 0 ? 'Ngừng kích hoạt' : 'Kích hoạt'}
                                                </button>
                                            </td>
                                            <td>
                                                <button type="button" class="btn btn-success" title="Sửa dữ liệu" onclick="myFunction()">
                                                    <a class="text-white" style="text-decoration: none" href="/size/view-update/${list.id}"><i class="bi bi-pencil"></i></a>
                                                </button>
                                                <button type="button" class="btn btn-danger" title="Ngừng kích hoạt" onclick="if(confirm('Bạn có chắc chắn muốn ngừng kích hoạt không?')){window.location.href = '/size/stop/${list.id}';}
                                                        else{alert('Ngừng kích hoạt thất bại!')}"><i class="bi bi-sign-stop"></i>
                                                </button>

                                            </td>
                                        </tr>
                                    </c:forEach>
                                    </tbody>
                                </table>
                                <!-- End Table with stripped rows -->
                                <%--                phân trang --%>
                                <div class="container-fluid mt-5">
                                    <nav aria-label="Page navigation example">
                                        <ul class="pagination justify-content-center">
                                            <li class="page-item ${currentPage<=0?"disabled":""}"><a class="page-link" href="/size/hien-thi/${currentPage-1}"><</a></li>
                                            <c:forEach begin="1" end="${totalPage}" var="i">
                                                <li class="page-item"><a class="page-link" href="/size/hien-thi/${i-1}">${i}</a></li>
                                            </c:forEach>
                                            <li class="page-item ${currentPage>=totalPage-1?"disabled":""}"><a class="page-link" href="/size/hien-thi/${currentPage+1}">></a></li>
                                        </ul>
                                    </nav>
                                </div>
                            </div>
                        </div>

                    </div>
                </div>
            </section>
            <div class="modal fade" id="modalDialogScrollable" tabindex="-1">
                <div class="modal-dialog modal-dialog-scrollable">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h5 class="modal-title">Thêm dữ liệu</h5>
                            <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                        </div>
                        <form:form method="post" action="/size/add" modelAttribute="size" class="row g-3">
                            <div class="modal-body">
                                <div class="form-floating">
                                    <form:input type="text" path="ten" class="form-control" id="floatingName" placeholder="Kích cỡ"/>
                                    <form:label for="floatingName" path="ten">Kích cỡ</form:label>
                                    <form:errors path="ten" cssStyle="color: red"/>
                                </div>
                            </div>
                            <div class="modal-footer">
                                <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Đóng</button>
                                <form:button type="submit" class="btn btn-primary" onclick="if(confirm('Bạn có chắc chắn muốn thêm không?')==true){ }else{ alert('Thêm thất bại');return false; }">
                                    Xác nhận
                                </form:button>
                            </div>
                        </form:form><!-- End floating Labels Form -->
                    </div>
                </div>
            </div><!-- End Modal Dialog Scrollable-->
        </div>
    </div>
</div>
<script>
    function myFunction() {
        document.getElementById("floatingName").readOnly = false;
        document.getElementById("disabled").disabled = false
    }
</script>
</body>
</html>