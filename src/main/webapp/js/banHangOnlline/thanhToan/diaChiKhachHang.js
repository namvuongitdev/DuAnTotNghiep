let modalDiaChiKhachHang = document.getElementById("modalDiaChiKhachHangOnline");

let close_diaChiKhachHangOnline = document.getElementById("close_diaChiKhachHangOnline");

let modalThemDiaChiKhachHangOnline = document.getElementById("modalThemDiaChiKhachHangOnline");

let close_themDiaChiKhachHangOnline = document.getElementById("close_themDiaChiKhachHangOnline");

function getAllDiaChiKhachHang(){
    modalDiaChiKhachHang.style.display = "block";
}

close_diaChiKhachHangOnline.onclick = function () {
    modalDiaChiKhachHang.style.display = "none";
}

function  themMoiDiaChiOnline(){
    modalThemDiaChiKhachHangOnline.style.display = "block";
    modalDiaChiKhachHang.style.display = "none";

}

close_themDiaChiKhachHangOnline.onclick = function () {
    modalThemDiaChiKhachHangOnline.style.display = "none";
}

function findDiaChiOnline(data){
    document.getElementById("hoTen").value = data.hoTen;
    document.getElementById("sdt").value = data.sdt
    document.getElementById("diaChi").value = data.diaChi
    modalDiaChiKhachHang.style.display = "none";
}