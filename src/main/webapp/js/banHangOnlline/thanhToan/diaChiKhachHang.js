let modalDiaChiKhachHang = document.getElementById("modalDiaChiKhachHangOnline");

let close_diaChiKhachHangOnline = document.getElementById("close_diaChiKhachHangOnline");

let modalThemDiaChiKhachHangOnline = document.getElementById("modalThemDiaChiKhachHangOnline");

let close_themDiaChiKhachHangOnline = document.getElementById("close_themDiaChiKhachHangOnline");
const message = Swal.mixin({
    toast: true,
    position: "top-end",
    showConfirmButton: false,
    timer: 3000,
    timerProgressBar: true,
    didOpen: (toast) => {
        toast.onmouseenter = Swal.stopTimer;
        toast.onmouseleave = Swal.resumeTimer;
    }
});
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


function validateNewDiaChi(){
    const diaChi = document.getElementById("newDiaChi").value;
    const hoTen = document.getElementById("newHoTen").value;
    const sdt = document.getElementById("newSDT").value;
    if(hoTen.trim() === ""){
        message.fire({
            text:"họ tên không để trống",
            icon:"error"
        })
        return false;
    }else if(diaChi.trim() === ""){
        message.fire({
            text:"địa chỉ không để trống",
            icon:"error"
        })
        return false;
    }else if(sdt.trim() === ""){
        message.fire({
            text:"số điện không để trống",
            icon:"error"
        })
        return false;
    }else{
        const rexge = /(03|05|07|08|09|01[2|6|8|9])+([0-9]{8})\b/;
        if(!rexge.test(rexge)){
            message.fire({
                text:"số điện không đúng định dạng",
                icon:"error"
            })
            return false;
        }
       return true;
    }
}