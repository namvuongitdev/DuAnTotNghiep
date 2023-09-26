const VND = new Intl.NumberFormat('vi-VN', {
    style: 'currency',
    currency: 'VND',
});

var modal = document.getElementById("myModal");

var btn = document.getElementById("myBtn");

var span = document.getElementsByClassName("close")[0];

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
