let modalTranHang = document.getElementById("modalTranHang");
let close_modalTranHang = document.getElementById("close_modalTranHang");

async function getHDCT(idHDCT, idHD) {
    modalTranHang.style.display = "block";
    const api = await fetch(`/admin/hoa-don/chi-tiet-hoa-don/${idHDCT}`)
    const data = await api.json();
    document.getElementById("dataTraHang").innerHTML =
        `
<div class="row">
    <div class="col-sm-6">
        <div class="row">
            <div class="col-sm-6">
                <img src="/image/${data.img}" alt="" width="100px" height="100px">
            </div>
            <div class="col-sm-6">
                <h4>${data.tenSanPham}</h4>
                <p style="color: #E43535">${VND.format(data.donGia)}</p>
                <p>${data.tenMauSac} / ${data.tenKichCo}</p>
                <p>x ${data.soLuong}</p>
               
            </div>
        </div>
    </div>
    <div class="col-sm-6">
        <p style="color: #E43535">${VND.format(data.donGia * data.soLuong)}</p>
    </div>
</div>
<form action="/admin/hoa-don-chi-tiet/tra-hang?idHDCT=${data.id}&idHD=${idHD}" method="post" class="row">
    <div>
          <label for="soLuongUpdate">Số lượng</label>
                <input type="number" value="1"  id="soLuongUpdate" min="1" name="soLuong" style="width: 30%"
                class="form-control"/>
    </div>
    <div class="mb-3">
        <label for="ghiChuKhiTraHang" class="form-label">Ghi chú</label>
        <textarea name="ghiChuKhiTraHang" class="form-control" id="ghiChuKhiTraHang"></textarea>
    </div>
    <div>
        <button class="btn btn-danger" onclick="return checkSoLuongKhiTraHang('${data.soLuong}')">Xác nhận</button>
    </div>
</form>
    `
}

close_modalTranHang.onclick = function () {
    modalTranHang.style.display = "none"
}

function checkSoLuongKhiTraHang(soLuong) {
    const soLuongUpdate = document.getElementById("soLuongUpdate").value;
    const ghiChuKhiTraHang = document.getElementById("ghiChuKhiTraHang").value;
    if (soLuongUpdate.trim() == "") {
        message.fire({
            text: "không được để trống số lượng trả",
            icon: "error"
        });
        return false;
    } else if (soLuong < soLuongUpdate) {
        message.fire({
            text: "số lượng đổi không phù hợp",
            icon: "error"
        });
        return false;
    } else if (ghiChuKhiTraHang.trim() == "") {
        message.fire({
            text: "không được để trống ghi chú",
            icon: "error"
        });
        return false;
    } else {
        return true;
    }
}