let modalUpdateThongTinKhachHang = document.getElementById("modalUpdateThongTinKhachHang");

let close_modalUpdateThongTinKhachHang = document.getElementById("close_modalUpdateThongTinKhachHang");

function updateThongTinKhachHang() {
    modalUpdateThongTinKhachHang.style.display = "block";
}

close_modalUpdateThongTinKhachHang.onclick = function () {
    modalUpdateThongTinKhachHang.style.display = "none";
}

function validateThongTinKhachHang() {

    const hoTen = document.getElementById("hoTenKhach").value;
    const sdt = document.getElementById("sdtKhach").value;
    const phiVanChuyen = document.getElementById("phiVanChuyen").value;
    const diaChi = document.getElementById("diaChiMoi").value;

    if (hoTen.trim() === "") {
        Swal.fire({
            title: "lỗi!",
            text: "họ tên khách hàng không được để trống",
            icon: "error"
        });
        return false;
    } else if (sdt.trim() === "") {
        Swal.fire({
            title: "lỗi!",
            text: "số điện thoại khách hàng không được để trống",
            icon: "error"
        });
    } else if (phiVanChuyen.trim() === "") {
        Swal.fire({
            title: "lỗi!",
            text: "phí vận chuyển khách hàng không được để trống",
            icon: "error"
        });
        return false;
    } else if (diaChi.trim() === "") {
        Swal.fire({
            title: "lỗi!",
            text: "địa chỉ chuyển khách hàng không được để trống",
            icon: "error"
        });
        return false;
    } else {
        if (Number.parseInt(phiVanChuyen) <= 0) {
            Swal.fire({
                title: "lỗi!",
                text: "phí vận chuyển không được nhỏ hơn hoặc bằng 0",
                icon: "error"
            });
            return false;
        }
        return true;
    }
}

