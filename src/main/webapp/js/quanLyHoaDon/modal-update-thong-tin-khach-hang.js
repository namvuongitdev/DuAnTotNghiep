let modalUpdateThongTinKhachHang = document.getElementById("modalUpdateThongTinKhachHang");

let close_modalUpdateThongTinKhachHang = document.getElementById("close_modalUpdateThongTinKhachHang");

function  updateThongTinKhachHang(){
    modalUpdateThongTinKhachHang.style.display = "block";
}
close_modalUpdateThongTinKhachHang.onclick = function () {
    modalUpdateThongTinKhachHang.style.display = "none";
}
