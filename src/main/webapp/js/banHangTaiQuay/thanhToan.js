function getTienKhachDua(value) {
    const tienKhachTra = value.tienKhacDua + '0';
    let xacNhanThanhToan = document.getElementById("xacNhanThanhToan");
    const tienThua = +tienKhachTra  -  +value.tongTien;
    document.getElementById("tienThuaCuaKhach").innerHTML = `Tiền thừa của khách :` + VND.format(tienThua);
}