const VND = new Intl.NumberFormat('vi-VN', {
    style: 'currency',
    currency: 'VND',
});

let scanner = new Instascan.Scanner({video:document.getElementById('preview')});

scanner.addListener('scan' , function (c) {
    let url_string = window.location.href;
    let url = new URL(url_string);
    let paramValue = url.searchParams.get("idHD");
    window.location.href = '/hoa-don/add-san-pham?ctsp='+c+'&soLuong=1'+'&idHD='+paramValue;
})

let modal = document.getElementById("myModal");

let btn = document.getElementById("myBtn");

let span = document.getElementById("close_ctsp");

let modalQrcode = document.getElementById("modalQrcode");

let btnQrcode = document.getElementById("myBtnQr");

let spanQrcode = document.getElementById("close_qrcode");

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
spanQrcode.onclick = function () {
    scanner.stop();
    modalQrcode.style.display = "none";
}
btnQrcode.onclick = function () {
    Instascan.Camera.getCameras().then(function (cameras) {
        if(cameras.length > 0){
            scanner.start(cameras[0]);

        }else{
            alert('no cameras');
        }
    }).catch(function (e) {
        console.error(e);
    });
    modalQrcode.style.display = "block";
}



