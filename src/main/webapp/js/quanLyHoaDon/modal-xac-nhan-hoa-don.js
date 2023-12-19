let modalXacNhanHoaDon = document.getElementById("modalXacNhanHoaDon");
let close_modalXacNhanHoaDon = document.getElementById("close_modalXacNhanHoaDon");

const xacNhan = document.getElementById("xacNhanHoaDon");

//const xacNhanQuayLai = document.getElementById("xacNhanQuayLai");

// const modalQuayLai = document.getElementById("modalquayLaiTrangThaiHoaDon")
// const close_modalquayLaiTrangThaiHoaDon = document.getElementById("close_modalquayLaiTrangThaiHoaDon")

if (xacNhan != null) {
    xacNhan.onclick = function () {
        modalXacNhanHoaDon.style.display = "block";
    }
}
// if(xacNhanQuayLai != null){
//     xacNhanQuayLai.onclick = function () {
//         modalQuayLai.style.display = "block";
//     }
// }
close_modalXacNhanHoaDon.onclick = function () {
    modalXacNhanHoaDon.style.display = "none";
}

// close_modalquayLaiTrangThaiHoaDon.onclick = function () {
//     modalQuayLai.style.display = "none";
// }


let modalXacNhanHuyHoaDon = document.getElementById("modalXacNhanHuyHoaDon");
let close_modalXacNhanHuyHoaDon = document.getElementById("close_modalXacNhanHuyHoaDon");
let xacNhanHuyHoaDon = document.getElementById("xacNhanHuyHoaDon");
if (xacNhanHuyHoaDon != null) {
    xacNhanHuyHoaDon.onclick = function () {
        modalXacNhanHuyHoaDon.style.display = "block"
    }

}
close_modalXacNhanHuyHoaDon.onclick = function () {
    modalXacNhanHuyHoaDon.style.display = "none";
}

function xacDonHang() {
    const ghiChuXacNhan = document.getElementsByName("ghiChuXacNhan")[0].value;
    if(ghiChuXacNhan.trim() === ""){
        message.fire({
            text: "ghi chú không được để trống",
            icon: "error"
        });
        return false;
    }else{
        return true;
    }
}

function vaidateXacNhanHuyHoaDon(){
    const ghiChu = document.getElementById("ghiChuHuyHoaDon").value;
    if(ghiChu.trim() === ""){
        message.fire({
            text: "ghi chú không được để trống",
            icon: "error"
        });
        return false;
    }else{
        return true;
    }
}

// function vaidateXacNhanQuayLai(){
//     const ghiChu = document.getElementsByClassName("form-control 1")[0].value;
//     if(ghiChu.trim() === ""){
//         message.fire({
//             text: "ghi chú không được để trống",
//             icon: "error"
//         });
//         return false;
//     }else{
//         return true;
//     }
// }