<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
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
                <h3>Kiểu dáng</h3>
                <nav>
                    <ol class="breadcrumb">
                        <li class="breadcrumb-item"><a href="/trangchu" style="text-decoration: none; color: black">Trang chủ</a></li>
                        <li class="breadcrumb-item ">Quản lý sản phẩm</li>
                        <li class="breadcrumb-item active">Kiểu dáng</li>
                    </ol>
                </nav>
            </div><!-- End Page Title -->
            <section class="section">
                <div class="card">
                    <div class="card-body">
                        <h5 class="card-title">Sửa dữ liệu</h5>

                        <!-- Floating Labels Form -->
                        <%--@elvariable id="kieuDang" type=""--%>
                        <form:form method="post" action="/kieu-dang/update/${kieuDang.id}" modelAttribute="kieuDang" class="row g-3">
                            <div class="col-md-12">
                                <div class="form-floating">
                                    <form:input type="text" path="ten" class="form-control" id="floatingName" placeholder="Kiểu dáng" readonly="true"/>
                                    <form:label for="floatingName" path="ten">Kiểu dáng</form:label>
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
                                <div class="col-2 card-title" style="margin-left: 80px">
                                    <button type="button" class="btn btn-primary" title="Thêm dữ liệu" data-bs-toggle="modal" data-bs-target="#modalDialogScrollable">
                                        <i class="bi bi-plus-circle"></i>
                                    </button>
                                </div>

                                <!-- Table with stripped rows -->
                                <table class="table">
                                    <thead>
                                    <tr>
                                        <th scope="col">STT</th>
                                        <th scope="col">Kiểu dáng</th>
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
                                            <td>${list.ngayTao}</td>
                                            <td>${list.ngaySua}</td>
                                            <td>${list.trangThai==0?"Ngừng kích hoạt":"Kích hoạt"}</td>
                                            <td>
                                                <button type="button" class="btn btn-success" title="Sửa dữ liệu" onclick="myFunction()">
                                                    <a class="text-white" style="text-decoration: none" href="/kieu-dang/view-update/${list.id}"><i class="bi bi-pencil"></i></a>
                                                </button>
                                                <button type="button" class="btn btn-danger" title="Ngừng kích hoạt" onclick="if(confirm('Bạn có chắc chắn muốn ngừng kích hoạt không?')){window.location.href = '/kieu-dang/stop/${list.id}';}
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
                                            <li class="page-item ${currentPage<=0?"disabled":""}"><a class="page-link" href="/kieu-dang/hien-thi/${currentPage-1}"><</a></li>
                                            <c:forEach begin="1" end="${totalPage}" var="i">
                                                <li class="page-item"><a class="page-link" href="/kieu-dang/hien-thi/${i-1}">${i}</a></li>
                                            </c:forEach>
                                            <li class="page-item ${currentPage>=totalPage-1?"disabled":""}"><a class="page-link" href="/kieu-dang/hien-thi/${currentPage+1}">></a></li>
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
                        <form:form method="post" action="/kieu-dang/add" modelAttribute="kieuDang" class="row g-3">
                            <div class="modal-body">
                                <div class="form-floating">
                                    <form:input type="text" path="ten" class="form-control" id="floatingName" placeholder="Kiểu dáng"/>
                                    <form:label for="floatingName" path="ten">Kiểu dáng</form:label>
                                    <form:errors path="ten" cssStyle="color: red"/>
                                </div>
                            </div>
                            <div class="modal-footer">
                                <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Đóng</button>
                                <form:button type="submit" class="btn btn-primary" onclick="if(confirm('Bạn có chắc chắn muốn thêm không?')==true){ }else{ alert('Sửa thất bại');return false; }">
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