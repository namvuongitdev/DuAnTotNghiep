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
    <script type="text/javascript"
            src="https://cdnjs.cloudflare.com/ajax/libs/webrtc-adapter/3.3.3/adapter.min.js"></script>
    <script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/vue/2.1.10/vue.min.js"></script>
    <script type="text/javascript" src="https://rawgit.com/schmich/instascan-builds/master/instascan.min.js"></script>
    <link rel="stylesheet" href="/css/ban-hang-tai-quay.css">
</head>
<body>
<%--navbar--%>
<jsp:include page="../sidebar/navbar.jsp"/>
<%--sidebar--%>
<div class="container-fluid">
    <div class="row flex-nowrap">
        <jsp:include page="../sidebar/sidebar.jsp"/>
        <div class="col py-3">
            <div class="pagetitle">
                <h3>Thông tin hóa đơn</h3>
                <nav>
                    <ol class="breadcrumb">
                        <li class="breadcrumb-item"><a href="/trangchu" style="text-decoration: none; color: black">Trang chủ</a></li>
                        <li class="breadcrumb-item"><a href="/hoa-don/hien-thi" style="text-decoration: none; color: black">Quản lý hóa đơn</a></li>
                        <li class="breadcrumb-item active">Thông tin hóa đơn</li>
                    </ol>
                </nav>
            </div><!-- End Page Title -->
            <section class="section">
                <%--@elvariable id="hoaDon" type=""--%>
                <form:form class="row" action="" method="post" modelAttribute="hoaDon">
                    <div class="col-8">
                        <div class="card">
                            <div class="card-body row">
                                <h5 class="card-title">Thông tin chi tiết</h5>
                                <br><br>
                                <div class="row">
                                    <div class="col-6">
                                        <div class="form-floating mb-3 mt-3">
                                            <form:input class="form-control" value="${hd.ma}" readonly="true" path="ma"/>
                                            <label class="form-label" path="">Mã hóa đơn</label>
                                        </div>
                                        <div class="form-floating mb-3 mt-3">
                                            <form:input class="form-control" value="${hd.ngayTao}" readonly="true" path="ngayTao"/>
                                            <label class="form-label" path="">Ngày tạo</label>
                                        </div>
                                    </div>
                                    <div class="col-6">
                                        <div class="form-floating mb-3 mt-3">
                                            <form:input class="form-control" value="${hd.ngayThanhToan}" readonly="true" path="ngayThanhToan"/>
                                            <label class="form-label" path="">Ngày thanh toán</label>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="col-4">
                        <div class="card">
                            <div class="card-body row">
                                <h5 class="card-title">Thông tin khách hàng</h5>
                                <br><br>
                                <div class="row">
                                    <div class="col-6">
                                        <div class="form-floating mb-3 mt-3">
                                            <form:input class="form-control" value="${hd.hoTen}" readonly="true" path="hoTen"/>
                                            <label class="form-label" path="">Tên khách hàng</label>
                                        </div>
                                        <div class="form-floating mb-3 mt-3">
                                            <form:input class="form-control" value="${hd.sdt}" readonly="true" path="sdt"/>
                                            <label class="form-label" path="">SĐT</label>
                                        </div>
                                    </div>
                                    <div class="col-6">
                                        <div class="form-floating mb-3 mt-3">
                                            <form:input class="form-control" value="${hd.diaChi}" readonly="true" path="diaChi"/>
                                            <label class="form-label" path="">Địa chỉ</label>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div style="text-align: center; margin-top: 20px">
                        <button type="submit" class="btn btn-outline-dark">Cập nhật</button>
                    </div>
                </form:form>
                <br><br>
                <%--sản phẩm trong hóa đơn--%>
                <div class="card">
                    <div class="card-body row">
                        <div class="col-6">
                            <h5 class="card-title">Danh sách sản phẩm trong hóa đơn</h5>
                        </div>
                        <div class="col-6">
                            <button style="float: right" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#extraLargeModal"
                               name="1" onclick="getSanPham(this.name)">
                                Thêm sản phẩm
                            </button>
                        </div>

                        <br><br>
                        <!-- Table with stripped rows -->
                        <table class="table">
                            <thead>
                            <tr>
                                <th scope="col">STT</th>
                                <th scope="col">Tên sản phẩm</th>
                                <th scope="col">Hình ảnh</th>
                                <th scope="col">Số lượng</th>
                                <th scope="col">Gía bán</th>
                                <th scope="col">Trạng thái</th>
                                <th scope="col">Thành tiền</th>
                            </tr>
                            </thead>
                            <tbody>
                            <c:forEach items="${lst}" var="hdct" varStatus="i">
                                <tr>
                                    <td>${i.index+1}</td>
                                    <td>${hdct.chiTietSanPham.sanPham.ten}, ${hdct.chiTietSanPham.mauSac.ten}, ${hdct.chiTietSanPham.size.ten}</td>
                                    <td>
                                        <img src="/image/${hdct.chiTietSanPham.sanPham.img}">
                                    </td>
                                    <td><input type="number" class="form-control" id="soLuongSp" onchange="myFunction()" value="${hdct.soLuong}" min="1" style="width: 40%"></td>
                                   <td>
                                       <input type="text" class="form-control" id="giaBan" readonly value="<fmt:formatNumber pattern="#,###"  value="${hdct.chiTietSanPham.sanPham.giaBan}"/>"/>
                                   </td>
                                    <td>${hdct.trangThai==0?'Đã mua':'Hủy'}</td>
                                    <td><input type="text" class="form-control" readonly id="thanhTien" value="<fmt:formatNumber pattern="#,###"  value="${hdct.soLuong * hdct.chiTietSanPham.sanPham.giaBan}"/>"></td>
                                </tr>
                            </c:forEach>
                            </tbody>
                        </table>
                        <!-- End Table with stripped rows -->
                        <%--                phân trang --%>
                        <div class="container-fluid mt-5">
                            <nav aria-label="Page navigation example">
                                <ul class="pagination justify-content-center">
                                    <li class="page-item ${currentPage<=0?"disabled":""}"><a class="page-link" href="/hoa-don/detail/${hd.id}/${currentPage-1}"><</a></li>
                                    <c:forEach begin="1" end="${totalPage}" var="i">
                                        <li class="page-item"><a class="page-link ${lst1+1==i?'active':''}" href="/hoa-don/detail/${hd.id}/${i-1}" >${i}</a></li>
                                    </c:forEach>
                                    <li class="page-item ${currentPage>=totalPage-1?"disabled":""}"><a class="page-link" href="/hoa-don/detail/${hd.id}/${currentPage+1}">></a></li>
                                </ul>
                            </nav>
                        </div>
                    </div>
                </div>
            </section>
        </div>
    </div>
</div>
<%--thêm sản phẩm--%>
<div id="extraLargeModal" class="modal fade" tabindex="-1" role="dialog">
    <div class="modal-dialog modal-xl">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title">Thêm sản phẩm</h5>
            </div>
            <div class="modal-body">
                <div class="row">
                    <div class="col-sm-3">
                        <input class="form-control" type="text" name="search" id="search-input"
                               placeholder="tìm kiếm tên , mã sản phẩm">
                    </div>
                    <div class="col-sm-2">
                        <button class="btn btn-primary" onclick="timKiem()">Tìm kiếm</button>
                    </div>
                    <div class="col-sm-2">
                        <button class="btn btn-warning" id="clear">Làm mới</button>
                    </div>
                </div>
                <br>
                <div class="row">
                    <div class="row d-flex justify-content-center" style="margin-bottom: 10px">
                        <div class="col-sm-2">
                            <label for="size">Kích cỡ</label>
                            <select name="kichCo" id="size" class="form-select" onchange="filterSize(this.value)">
                                <option value="">Tất cả</option>
                                <c:forEach items="${listKichCo}" var="kichCo">
                                    <option value="${kichCo.id}">
                                            ${kichCo.ten}
                                    </option>
                                </c:forEach>
                            </select>
                        </div>
                        <div class="col-sm-2">
                            <label for="mauSac">Màu sắc</label>
                            <select name="mauSac" id="color" class="form-select" onchange="filterColor(this.value)">
                                <option value="">Tất cả</option>
                                <c:forEach items="${listMuaSac}" var="mauSac">
                                    <option value="${mauSac.id}">
                                            ${mauSac.ten}
                                    </option>
                                </c:forEach>
                            </select>
                        </div>
                        <div class="col-sm-2">
                            <label for="danhMuc">Danh mục</label>
                            <select name="danhMuc" id="danhMuc" class="form-select"
                                    onchange="filterDanhMuc(this.value)">
                                <option value="">Tất cả</option>
                                <c:forEach items="${listDanhMuc}" var="danhMuc">
                                    <option value="${danhMuc.id}">
                                            ${danhMuc.ten}
                                    </option>
                                </c:forEach>
                            </select>
                        </div>
                        <div class="col-sm-2">
                            <label for="chatLieu">Chất liệu</label>
                            <select name="chatLieu" id="chatLieu" class="form-select"
                                    onchange="filterChatLieu(this.value)">
                                <option value="">Tất cả</option>
                                <c:forEach items="${listChatLieu}" var="chatLieu">
                                    <option value="${chatLieu.id}">
                                            ${chatLieu.ten}
                                    </option>
                                </c:forEach>
                            </select>
                        </div>
                        <div class="col-sm-2">
                            <label for="kieuDang">Kiểu dáng</label>
                            <select name="kieuDang" id="kieuDang" class="form-select"
                                    onchange="filterKieuDang(this.value)">
                                <option value="">Tất cả</option>
                                <c:forEach items="${listFormDang}" var="kieuDang">
                                    <option value="${kieuDang.id}">
                                            ${kieuDang.ten}
                                    </option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>
                    <div class="row d-flex justify-content-center">
                        <div class="col-sm-2">
                            <label for="trangThai">Trang thái</label>
                            <select name="trangThai" id="trangThai" class="form-select"
                                    onchange="filterTrangThai(this.value)">
                                <option value="">Tất cả</option>
                                <option value="0">
                                    Kinh doanh
                                </option>
                                <option value="1">
                                    Ngừng Kinh doanh
                                </option>
                            </select>
                        </div>
                        <div class="col-sm-2">
                            <label for="sapXep">Sắp xếp</label>
                            <select name="sapXep" id="sapXep" class="form-select" onchange="filterSapXep(this.value)">
                                <option value="">Tất cả</option>
                                <option value="ngayTao">Mới nhất</option>
                                <option value="price-asc">
                                    Thứ tự theo giá: thấp đến cao
                                </option>
                                <option value="price-desc">
                                    Thứ tự theo giá: cao xuống thấp
                                </option>
                            </select>
                        </div>
                        <div class="col-sm-2">
                            <label for="gioiTinh">Giới tính</label>
                            <select name="gioiTinh" id="gioiTinh" class="form-select"
                                    onchange="filterGioiTinh(this.value)">
                                <option value="">Tất cả</option>
                                <option value="true">
                                    Nam
                                </option>
                                <option value="false">
                                    Nữ
                                </option>
                            </select>
                        </div>
                    </div>
                </div>
                <br>
                <div class="row">
                    <table>
                        <thead>
                        <tr>
                            <th scope="col">Ảnh</th>
                            <th scope="col">Mã</th>
                            <th scope="col">Tên Sản Phẩm</th>
                            <th scope="col">giá</th>
                            <th scope="col">Thao tác</th>
                        </tr>
                        </thead>
                        <tbody id="body">

                        </tbody>
                    </table>
                </div>
                <div class="container-fluid mt-5">
                    <nav aria-label="Page navigation example">
                        <ul class="pagination justify-content-center" id="phanTrang">
                        </ul>
                    </nav>
                </div>
            </div>
        </div>
    </div>
</div>
<%--chi tiết sản phẩm--%>
<div id="myModal" class="modal">
    <!-- Modal content -->
    <div class="modal-content-1">
        <span class="close" id="close_ctsp">&times;</span>
        <div class="row" id="chiTietSanPham">
            <div class="col l-3" id="img">

            </div>
            <div class="col l-3">
                <div class="row" id="sp">
                </div>
                <div class="row">
                    <label>Màu sắc : </label>
                    <div class="form-check" id="mauSac">
                    </div>
                </div>
                <br>
                <div class="row">
                    <label>Kích cỡ : </label>
                    <div class="form-check" id="kichCo">
                    </div>
                </div>
                <br>
                <div class="row">
                    <div>
                        <label for="soLuongTon">Số lượng :</label>
                        <input type="number" value="1" min="1" id="soLuongTon" name="${hd.id}" style="width: 20%"
                               aria-describedby="sl"/>
                        <div id="soLuong" class="form-text">
                        </div>
                    </div>
                </div>
                <br>
                <div class="row">
                    <button type="button" id="themVaoGioHang" name="" onclick="themSanPhamVaoGioHang2(this.name)"
                            class="btn btn-primary">Thêm vào giỏ hàng
                    </button>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
<script src="/js/banHangTaiQuay/modal.js">
</script>
<script src="/js/banHangTaiQuay/sanPham.js"></script>
<script>
    let sizeSP = document.getElementById("kichCo");
    let colorSP = document.getElementById("mauSac");
    let kichCo;
    let mauSac;
    let sanPham;
    let dataCTSP;

    function getModal(id) {
        sanPham = id;
        modal.style.display = "block";
        fetch("/chi-tiet-san-pham/" + id)
            .then(response => response.json())
            .then(data => {
                dataCTSP = data;
                for (let i = 0; i < data.length; i++) {
                    document.getElementById("img").innerHTML = `<img src="/image/` + data[i].sanPham.img + `">`
                    document.getElementById("sp").innerHTML = `<div><h3>` + data[i].sanPham.ten + `</h3></div> <div><h5 style="color: #03AA28">` + VND.format(data[i].sanPham.giaBan) + `</h5></div>`
                    break;
                }
                getMauSac(data);
                getSize(data);
            });
    }

    function getMauSac(data) {
        for (let i = 0; i < data.length; i++) {
            let mauSacSP = document.getElementById(data[i].mauSac.id);
            if (mauSacSP != null) {
                continue;
            } else {
                dataColor(data[i].mauSac)
            }
        }
    }

    function getSize(data) {
        for (let i = 0; i < data.length; i++) {
            let kichCoSP = document.getElementById(data[i].size.id);
            if (kichCoSP != null) {
                continue;
            } else {
                dataSize(data[i].size)
            }
        }
    }

    function dataSize(data) {
        sizeSP.innerHTML += `
                       <input type="radio" class="btn-check" onclick="getCTSP({
                         id:this.value,
                         type:'kichCo'
                       })"
                        value="` + data.id + `"   name="success-outlined-1"  id="` + data.id + `" autocomplete="off">
                            <label class="btn btn-outline-secondary"  for="` + data.id + `">` + data.ten + `</label>
                      `
    }

    function dataColor(data) {
        colorSP.innerHTML += `
                        <input type="radio" class="btn-check" onclick="getCTSP({
                          id:this.value,
                          type:'mauSac'
                        })"
                         value="` + data.id + `"   name="success-outlined" id="` + data.id + `" autocomplete="off">
                             <label class="btn btn-outline-secondary"  for="` + data.id + `">` + data.ten + `</label>
                       `
    }

    function getCTSP(id) {
        if (id.type == 'kichCo') {
            kichCo = id.id;
        } else {
            mauSac = id.id;
            sizeSP.innerHTML = "";
            kichCo = undefined;
            document.getElementById("themVaoGioHang").name = "";
            for (var i = 0; i < dataCTSP.length; i++) {
                if (dataCTSP[i].mauSac.id == mauSac && dataCTSP[i].sanPham.id == sanPham) {
                    dataSize(dataCTSP[i].size)
                }

            }
        }
        if (mauSac != undefined && kichCo != undefined) {
            const sp = document.getElementById("sp");
            const themVaoGioHang = document.getElementById("themVaoGioHang");
            for (let i = 0; i < dataCTSP.length; i++) {
                if (dataCTSP[i].mauSac.id == mauSac && dataCTSP[i].size.id == kichCo && dataCTSP[i].sanPham.id == sanPham) {
                    if (dataCTSP[i].soLuong == 0 || dataCTSP[i].soLuong < 0 || dataCTSP.trangThai == 1) {
                        themVaoGioHang.setAttribute("disabled", "");
                        sp.innerHTML += `<h5 id="message" style="color: #e43535">Sản phẩm hết hàng</h5>`
                        return;
                    } else {
                        themVaoGioHang.removeAttribute('disabled');
                        document.getElementById("soLuong").innerText = `số lượng sản phẩm còn ` + dataCTSP[i].soLuong
                        themVaoGioHang.name = dataCTSP[i].id;
                        document.getElementById("soLuongTon").max = dataCTSP[i].soLuong;
                        return;
                    }
                }
            }
        }
    }

    function themSanPhamVaoGioHang2(idCTSP) {
        var soLuong = document.getElementById("soLuongTon").value;
        var idHD = document.getElementById("soLuongTon").name;
        console.log(idHD);
        if (idCTSP == "") {
            alert("lựa chon các thuộc tính sản phẩm")
        } else {
            for (let i = 0; i < dataCTSP.length; i++) {
                if (idCTSP == dataCTSP[i].soLuong) {
                    if (soLuong > dataCTSP[i].soLuong) {
                        alert("số lượng sản phẩm không đủ");
                        return;
                    }
                }
            }
            window.location.href = "/hoa-don/update?ctsp=" + idCTSP + "&soLuong=" + soLuong + "&idHD=" + idHD;
        }
    }
    // --------------------------------------------
    function myFunction(){
        var slSp = document.getElementById("soLuongSp").value;
        var giaSp = Number.parseFloat(document.getElementById("giaBan").value)
        console.log(giaSp)
        document.getElementById("thanhTien").value = slSp*giaSp;
        // return window.location.reload();
    }

</script>
</html>