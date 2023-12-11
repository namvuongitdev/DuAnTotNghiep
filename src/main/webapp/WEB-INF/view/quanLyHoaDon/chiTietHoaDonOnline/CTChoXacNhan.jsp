<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <link rel="shortcut icon" href="#">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css"
          rel="stylesheet" integrity="sha384-9ndCyUaIbzAi2FUVXJi0CjmCapSmO7SnpJef0486qhLnuZ2cdeRhO02iuK6FUUVM"
          crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"
            integrity="sha384-geWF76RCwLtnZ8qwWowPQNguL3RmwHVBC9FhGdlKrxdiJJigb/j/68SIy3Te4Bkz"
            crossorigin="anonymous"></script>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.1/font/bootstrap-icons.css">
    <link rel="stylesheet" href="../../../../css/banHangTaiQuay/hoaDon/chiTietHoaDon.css">
    <link rel="stylesheet" href="../../../../css/ban-hang-tai-quay.css">
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <script src="
              https://cdn.jsdelivr.net/npm/sweetalert2@11.10.0/dist/sweetalert2.all.min.js
              "></script>
    <link href="
          https://cdn.jsdelivr.net/npm/sweetalert2@11.10.0/dist/sweetalert2.min.css
         " rel="stylesheet">
    <style>
        #info span {
            font-weight: 600;
            padding-left: 5px;
        }

        table {
            border-collapse: collapse;
            width: 100%;
        }

        th, td {
            padding: 8px;
            text-align: left;
            border-bottom: 1px solid #ddd;
            font-weight: 600;
        }
    </style>
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
                <nav>
                    <ol class="breadcrumb">
                        <li class="breadcrumb-item"><a href="/admin/trang-chu"
                                                       style="text-decoration: none; color: black">Trang chủ</a></li>
                        <li class="breadcrumb-item"><a href="/admin/hoa-don/hien-thi"
                                                       style="text-decoration: none; color: black">Quản lí hóa đơn</a>
                        </li>
                    </ol>
                </nav>
            </div><!-- End Page Title -->
            <div class="container-fluid">
                <%--@elvariable id="hoaDon" type=""--%>
                <%--                <form:form action="/admin/hoa-don/update-hoa-don/${hd.id}" method="post" modelAttribute="hoaDon">--%>
                <div class="row">
                    <div class="card">
                        <div class="card-body row">
                            <div class="row">
                                <div class="col-sm-6">
                                    <jsp:include page=".././trangThai/thay-doi-trang-thai.jsp"></jsp:include>
                                </div>
                                <div class="col-sm-6">
                                    <button class="btn btn-light" style="float: right"
                                            onclick="getAllLichHoaDon(`${hoaDon.id}`)">
                                        Lịch sử hoá đơn
                                    </button>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="card">
                        <div style="margin-top: 15px">
                            <h4>Thông tin hoá đơn</h4>
                        </div>
                        <hr>
                        <div class="card-body row">
                            <jsp:include page="thong-tin-don-hang.jsp"></jsp:include>
                        </div>
                    </div>
                </div>
                <%-- end thông tin hoá đơn--%>
                <%--thông tin sản phẩm--%>
                <div class="row">
                    <div class="card">
                        <div class="card-body row">
                            <jsp:include page="san-pham.jsp"></jsp:include>
                            <jsp:include page="tong-tien.jsp"></jsp:include>
                        </div>
                    </div>
                </div>
                <%-- end sản phẩm--%>
            </div>
        </div>
    </div>
</div>
<!-- Sửa thông tin -->
<c:if test="${hoaDon.loaiHoaDon}">
    <jsp:include page="modal-update-thong-tin-khach-hang.jsp"></jsp:include>
</c:if>
<%--modal lịch sử hoá đơn--%>
<jsp:include page="modal-lich-su-hoa-don.jsp"></jsp:include>
<c:if test="${hoaDon.trangThai == 1 || hoaDon.trangThai == 2}">
    <%--thêm sản phẩm--%>
    <%--chi tiết sản phẩm--%>
    <jsp:include page="../../banHangTaiQuay/modal-hien-thi-san-pham.jsp"></jsp:include>
    <jsp:include page="../../banHangTaiQuay/chi-tiet-san-pham.jsp"></jsp:include>
    <%--modal xoá sản phẩm--%>
    <jsp:include page="modal-xoa-san-pham.jsp"></jsp:include>
    <%--modal update số lượng sản phẩm--%>
    <jsp:include page="modal-update-so-luong.jsp"></jsp:include>
</c:if>
<%--modal xác nhân hoá đơn--%>
<jsp:include page="modal-xac-nhan-hoa-don.jsp"></jsp:include>
<%--modal tra hàng--%>
<c:if test="${hoaDon.trangThai == 4}">
    <jsp:include page="modal-tra-hang.jsp"></jsp:include>
</c:if>
</body>

<%--   javasctipt--%>
<script>
    const VND = new Intl.NumberFormat('vi-VN', {
        style: 'currency',
        currency: 'VND',
    });
    const modalHienThiSanPham = document.getElementById("modalHienThiSanPham");
    window.onclick = function (event) {
        if (event.target == modalHienThiSanPham) {
            modalHienThiSanPham.style.display = "none";
        }
    }
</script>

<c:if test="${hoaDon.loaiHoaDon}">
    <script src="/js/quanLyHoaDon/modal-update-thong-tin-khach-hang.js"></script>
</c:if>

<script src="/js/quanLyHoaDon/modal-lich-su-hoa-don.js"></script>
<c:if test="${hoaDon.trangThai == 1 || hoaDon.trangThai == 2}">
    <script>
        let modal = document.getElementById("myModal");

        let btn = document.getElementById("myBtn");

        let span = document.getElementById("close_ctsp");
        span.onclick = function () {
            colorSP.innerHTML = ""
            sizeSP.innerHTML = ""
            document.getElementById("soLuong").innerHTML = "";
            document.getElementById("themVaoGioHang").name = "";
            document.getElementById("soLuongTon").value = 1;
            document.getElementById("sp").innerHTML = "";
            document.getElementById("img").innerHTML = "";
            mauSac = undefined;
            sanPham = undefined;
            kichCo = undefined;
            dataCTSP = undefined;
            modal.style.display = "none";
        }
    </script>
    <script src="/js/quanLyHoaDon/san-pham-hdct.js">
    </script>
    <script src="/js/quanLyHoaDon/chi-tiet-san-pham-hdct.js"></script>
    <script src="/js/quanLyHoaDon/modal-xoa-san-pham.js"></script>
    <script src="/js/quanLyHoaDon/modal-update-so-luong.js"></script>
</c:if>
<script src="/js/quanLyHoaDon/modal-xac-nhan-hoa-don.js"></script>
<c:if test="${hoaDon.trangThai == 4}">
    <script src="/js/quanLyHoaDon/modal-tra-hang.js"></script>
</c:if>
<script>
    if (${error != null}) {
        Swal.fire({
            title: "lỗi!",
            text: "${error}",
            icon: "error"
        });
    }
    if (${success != null}) {
        Swal.fire({
            title: "thành công!",
            text: "${success}",
            icon: "success"
        });
    }

    $(document).ready(function () {
        $(".inHoaDonChiTiet").click(function () {
            $('.inHoaDonModal').modal('show');
            $('.inHoaDonModal .btn-dong-y').click(function () {
                let hoaDonID = $("#idChiTietHoaDon").val();
                printHoaDon(hoaDonID);
                $('.inHoaDonModal').modal('hide');
            });

            $('.inHoaDonModal .btn-khong').click(function () {
                $('.inHoaDonModal').modal('hide');
            });
        });
    });

    function printHoaDon(hoaDonId) {
        // Tạo tên file PDF mới bằng UUID
        let pdfFileName = generateUuid() + '.pdf';

        // Gọi API để in hóa đơn và lưu file PDF vào thư mục dự án
        fetch('/admin/in-hoa-don/' + hoaDonId + '?pdfFileName=' + pdfFileName)
            .then(response => response.blob())
            .then(pdfBlob => {
                let pdfUrl = URL.createObjectURL(pdfBlob);
                let newWindow = window.open(pdfUrl, '_blank'); // Mở trang mới chứa file PDF
                if (newWindow) {
                    newWindow.document.title = 'Hóa đơn của bạn';
                } else {
                    alert('Vui lòng cho phép trình duyệt mở popup để xem và lưu hóa đơn.');
                }
            })
            .catch(error => console.error('Lỗi khi tạo file PDF:', error));
    }

    function generateUuid() {
        let uuid = '', i, random;
        for (i = 0; i < 32; i++) {
            random = Math.random() * 36 | 0; // Thay đổi thành toString(36)
            if (i === 8 || i === 12 || i === 16 || i === 20) {
                uuid += '-';
            }
            uuid += (i === 12 ? 4 : (i === 16 ? (random & 3 | 8) : random))
                .toString(36);
        }
        return uuid;
    }
</script>
</html>