<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css"
          rel="stylesheet" integrity="sha384-9ndCyUaIbzAi2FUVXJi0CjmCapSmO7SnpJef0486qhLnuZ2cdeRhO02iuK6FUUVM"
          crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"
            integrity="sha384-geWF76RCwLtnZ8qwWowPQNguL3RmwHVBC9FhGdlKrxdiJJigb/j/68SIy3Te4Bkz"
            crossorigin="anonymous"></script>

</head>
<body>
<%--navbar--%>
<jsp:include page="../../sidebar/navbar.jsp"/>
<%--sidebar--%>
<div class="container-fluid">
    <div class="row flex-nowrap">
        <jsp:include page="../../sidebar/sidebar.jsp"/>
        <div class="col py-3">
            <%--            code giao diện sản phẩm --%>
            <div class="container">
                <nav class="navbar navbar-light" style="height:30px">
                    <div class="container-fluid">
                        <a class="navbar-brand" href="#"><font color="black">Danh Sách</font></a>
                    </div>
                </nav>
                <hr>
                <div style="margin-left: 460px"><font color="#696969"><h5>THÊM CHI TIẾT SẢN PHẨM</h5></font></div>
                <form>
                    <div class="row">
                        <div class="col-sm-3">
                            <div class="well">
                                <p style="margin-left: 10px">Số Lượng :</p>
                                <input name="soluong" class="form-control"
                                       type="text" style="margin-left: 10px;width: 500px" value="${sp.soLuong}">

                                <p style="margin-left: 10px">Trạng thái :</p>
                                <input type="radio" style="margin-left: 60px" name="trangthai" value="1"
                                       checked="checked" ${sp.trangThai==1?"checked":""}>
                                Kích
                                Hoạt
                                <br>
                                <input type="radio" style="margin-left: 60px" name="trangthai"
                                       value="0" ${sp.trangThai==0?"checked":""}> Ngừng Kích
                                Hoạt
                                <br>
                            </div>
                        </div>
                        <div class="col-sm-9">
                            <div class="well" style="margin-left: 250px">
                                <p>Màu Sắc :</p>
                                <select name="mau" onchange="getMau(this.value)">
                                    <option>Màu Sắc</option>
                                    <c:forEach items="${listMau}" var="mau">
                                        <option value="${mau.id}">${mau.ten}</option>
                                    </c:forEach>
                                </select>
                                <div id="mau" style="color: cadetblue"></div>
                                <p>Size :</p>
                                <select name="size" onchange="getSize(this.value)">
                                    <option>Size</option>
                                    <c:forEach items="${listSize}" var="size">
                                        <option value="${size.id}">${size.ten}</option>
                                    </c:forEach>
                                </select>
                                <div id="size" style="color: cadetblue"></div>
                                <p>Danh Mục :</p>
                                <select name="danhmuc" onchange="getDanh(this.value)">
                                    <option>Danh Mục</option>
                                    <c:forEach items="${listDanh}" var="danh">
                                        <option value="${danh.id}">${danh.ten}</option>
                                    </c:forEach>
                                </select>
                                <div id="danh" style="color: cadetblue"></div>

                            </div>
                        </div>
                    </div>

                    <button type="submit" class="btn btn-info" style="margin-left: 900px;border-top-left-radius: 20px;
                            border-bottom-left-radius: 20px;
                            border-bottom-right-radius: 20px;
                            border-top-right-radius: 20px"
                            formmethod="post"
                            formaction="/chi-tiet-san-pham/add?idsp=${idsp}"
                            onclick="alert('Thêm thành công!') ">
                        Xác
                        Nhận
                    </button>

                    <table class="table">
                        <thead>
                        <tr>
                            <th scope="col">STT</th>
                            <th scope="col">Tên sản phẩm</th>
                            <th scope="col">Số lượng</th>
                            <th scope="col">Trạng thái</th>
                            <th scope="col">Màu sắc</th>
                            <th scope="col">Size</th>
                            <th scope="col">Danh mục</th>
                            <th scope="col">Thao tác</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach items="${list.getContent()}" var="sp" varStatus="i">
                            <tr>
                                <th scope="row">${i.index+page}</th>
                                <td width="150px" height="30px">${sp.sanPham.ten}</td>
                                <td>${sp.soLuong}</td>
                                <td>${sp.trangThai==1?"Kích Hoạt":"Ngừng Kích Hoạt"}</td>
                                <td>${sp.mauSac.ten}</td>
                                <td>${sp.size.ten}</td>
                                <td>${sp.danhMuc.ten}</td>
                                <td>
                                    <button type="button" class="btn btn-info"
                                            style="border-top-left-radius: 20px;
                                        border-bottom-left-radius: 20px;
                                        border-bottom-right-radius: 20px;
                                        border-top-right-radius: 20px;background: #03AA28" ><a class="text-white" style="text-decoration: none" href="/chi-tiet-san-pham/viewupdate?id=${sp.id}">Update</a>
                                    </button>

                                </td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </form>

                <%--                phân trang --%>
                <div class="container-fluid mt-5">
                    <nav aria-label="Page navigation example">
                        <ul class="pagination justify-content-center">
                            <li class="page-item ${pageNo<=1?"disabled":""}"><a class="page-link"
                                                                                href="/chi-tiet-san-pham/hien-thi?page=${pageNo-1}">Previous</a>
                            </li>
                            <c:forEach begin="1" end="${list.getTotalPages()}" var="i">
                                <li class="page-item"><a class="page-link ${i == pageNo ? 'active ' : ''}"
                                                         href="/chi-tiet-san-pham/hien-thi?page=${i}">${i}</a></li>
                            </c:forEach>
                            <li class="page-item ${pageNo>=list.getTotalPages()?"disabled":""}"><a class="page-link"
                                                                                                   href="/chi-tiet-san-pham/hien-thi?page=${pageNo+1}">Next</a>
                            </li>
                        </ul>
                    </nav>
                </div>

            </div>
        </div>
    </div>
</body>