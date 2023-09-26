function getTienKhachDua(tienKhachDua) {
    let xacNhanThanhToan = document.getElementById("xacNhanThanhToan");

    const tienThua = Number.parseInt(tienKhachDua + "0") - 300000;
    document.getElementById("tienThuaCuaKhach").innerHTML = `Tiền thừa của khách :` + VND.format(tienThua);

    if (tienKhachDua + "0" < 300000) {
        xacNhanThanhToan.setAttribute("disabled", "");
    } else {
        xacNhanThanhToan.removeAttribute("disabled");
    }
}

function hinhThucThanhToan(value) {

    let html = document.getElementById("hinhThucThanhToan");

    const tienMat = ` <div class="mb-3 form-floating">
                                    <input class="form-control" type="number" style="width: 50%" id="tienKhachDuaTienMat" onkeydown="getTienKhachDua(this.value)">
                                    <label for="tienKhachDuaTienMat">Khách đưa tiền mặt</label>
                                </div>`;

    const chuyenKhoan = `<div class="mb-3 form-floating">
              <input class="form-control" type="number" style="width: 50%" id="tienKhachDuaChuyenKhoa" onkeydown="getTienKhachDua(this.value)">
                 <label for="tienKhachDuaChuyenKhoa">Khách chuyển khoản</label> <span id="tienThuaCuaKhach" style="color: #03AA28"></span>
                </div>`;

    if (value == 0) {
        html.innerHTML = tienMat;
    } else if (value == 1) {
        html.innerHTML = chuyenKhoan;
    } else {
        html.innerHTML = tienMat + chuyenKhoan;
    }

}
