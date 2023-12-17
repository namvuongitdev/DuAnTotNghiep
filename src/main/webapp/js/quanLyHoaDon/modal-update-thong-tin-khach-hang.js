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
    const ghiChu = document.getElementById("ghiChu").value;

    if (hoTen.trim() === "") {
        message.fire({
            text: "họ tên khách hàng không được để trống",
            icon: "error"
        });
        return false;
    } else if (sdt.trim() === "") {
        message.fire({
            text: "số điện thoại khách hàng không được để trống",
            icon: "error"
        });
        return false;
    } else if (phiVanChuyen.trim() === "") {
        message.fire({
            text: "phí vận chuyển khách hàng không được để trống",
            icon: "error"
        });
        return false;
    } else if (diaChi.trim() === "") {
        message.fire({
            text: "địa chỉ chuyển khách hàng không được để trống",
            icon: "error"
        });

        return false;
    }else if(ghiChu.trim() === ""){
        message.fire({
            text: "ghi chú không được để trống",
            icon: "error"
        });
        return false;
    } else {
        if (Number.parseInt(phiVanChuyen) <= 0) {
            message.fire({
                text: "phí vận chuyển không được nhỏ hơn hoặc bằng 0",
                icon: "error"
            });
            return false;
        }
        return true;
    }
}

