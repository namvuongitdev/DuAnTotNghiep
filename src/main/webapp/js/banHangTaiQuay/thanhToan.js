function getTienKhachDua(value) {
    const tienKhachTra = value.tienKhacDua + '0';
    let xacNhanThanhToan = document.getElementById("xacNhanThanhToan");
    const tienThua = +tienKhachTra - +value.tongTien;
    document.getElementById("tienThuaCuaKhach").innerHTML = `Tiền thừa của khách :` + VND.format(tienThua);
}
const mySwitch  = document.getElementById('mySwitch')
mySwitch.addEventListener('click', (e) => {

    let url_string = window.location.href;
    let url = new URL(url_string);
    let paramValue = url.searchParams.get("idHD");
    let uri = null;
    if (e.target.checked) {
        uri = '/hoa-don/loai-hoa-don?loaiHoaDon=' + e.target.checked+ '&idHD='+paramValue;
        if(mySwitch.name != ""){
            window.location.href = uri + '&idKhachHang='+mySwitch.name;
        }else{
            window.location.href = uri + '&idKhachHang=';
        }

    } else {
        uri = '/hoa-don/loai-hoa-don?loaiHoaDon=' + e.target.checked+ '&idHD='+paramValue;
        if(mySwitch.name != ""){
            window.location.href = uri+ '&idKhachHang='+mySwitch.name;
        }else{
            window.location.href = uri + '&idKhachHang=';
        }
    }
})