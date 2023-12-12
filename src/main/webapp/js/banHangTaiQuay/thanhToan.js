function getTienKhachDua(value) {
    const tienKhachTra = value.tienKhacDua.trim() + '0';
    let xacNhanThanhToan = document.getElementById("xacNhanThanhToan");
    const tienThua = +tienKhachTra - +value.tongTien;
    document.getElementById("tienThuaCuaKhach").innerHTML = `Tiền thừa của khách :` + VND.format(tienThua);
}

const mySwitch = document.getElementById('mySwitch')
mySwitch.addEventListener('click', (e) => {

    let url_string = window.location.href;
    let url = new URL(url_string);
    let paramValue = url.searchParams.get("idHD");
    let uri = null;
    if (e.target.checked) {
        uri = '/admin/hoa-don/loai-hoa-don?loaiHoaDon=' + 2 + '&idHD=' + paramValue;
        if (mySwitch.name != "") {
            window.location.href = uri + '&idKhachHang=' + mySwitch.name;
        } else {
            window.location.href = uri + '&idKhachHang=';
        }

    } else {
        uri = '/admin/hoa-don/loai-hoa-don?loaiHoaDon=' + 0 + '&idHD=' + paramValue;
        if (mySwitch.name != "") {
            window.location.href = uri + '&idKhachHang=' + mySwitch.name;
        } else {
            window.location.href = uri + '&idKhachHang=';
        }
    }
})

function phuongThucThanhToan(value , tongTien) {
    const maGiaDich = document.getElementById("maGiaDichCK");
    if (value === "1") {
        if (maGiaDich != null) {
            maGiaDich.replaceChildren();
            return;
        }
        return;
    } else if (value === "2") {
        if(tongTien.trim() != ""){
            document.getElementById("tienKhachDua").value = Number.parseInt(tongTien);
        }
        maGiaDich.innerHTML = ` <input class="form-control" type="text" style="width: 50%"
                   name="maGiaoDich"
                   id="maGiaoDich"
                  >
            <label for="maGiaoDich">Mã giao dịch</label>`
    } else {
        return;
    }
}