let modalDiaChiKhachHang = document.getElementById("modalDiaChiKhachHang");

let btnDiaChiKhachHang = document.getElementById("diaChiKhachHang");

let spanDiaChiKhachHang = document.getElementById("close_diaChiKhachHang");

if (modalDiaChiKhachHang != null && btnDiaChiKhachHang != null && spanDiaChiKhachHang != null) {
    spanDiaChiKhachHang.onclick = function () {
        modalDiaChiKhachHang.style.display = "none";
    }

    btnDiaChiKhachHang.onclick = function () {
        modalDiaChiKhachHang.style.display = "block";
    }


    function findDiaChi(data) {
        document.getElementById('hoTen').value = data.hoTen;
        document.getElementById('sdt').value = data.sdt;
        document.getElementById('diaChi').value = data.diaChi;
        modalDiaChiKhachHang.style.display = "none";

    }
}
