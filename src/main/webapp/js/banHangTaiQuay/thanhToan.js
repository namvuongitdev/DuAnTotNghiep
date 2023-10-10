function getTienKhachDua(value) {
    const tienKhachTra = value.tienKhacDua + '0';
    let xacNhanThanhToan = document.getElementById("xacNhanThanhToan");
    const tienThua = +tienKhachTra - +value.tongTien;
    document.getElementById("tienThuaCuaKhach").innerHTML = `Tiền thừa của khách :` + VND.format(tienThua);
}


document.getElementById('mySwitch').addEventListener('click', (e) => {

    let url_string = window.location.href;
    let url = new URL(url_string);
    let paramValue = url.searchParams.get("idHD");
    if (e.target.checked) {
        window.location.href = '/hoa-don/loai-hoa-don?loaiHoaDon=' + e.target.checked+ '&idHD='+paramValue;
    } else {
        window.location.href = '/hoa-don/loai-hoa-don?loaiHoaDon=' + e.target.checked + '&idHD='+paramValue;
    }
})