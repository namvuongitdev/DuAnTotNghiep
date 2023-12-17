let modalXoaSanPham = document.getElementById("modalXoaSanPham");
let close_modalXoaSanPham = document.getElementById("close_modalXoaSanPham");
async function xoaSanPham(idHDCT) {
    modalXoaSanPham.style.display = "block";
    const api = await fetch(`/admin/hoa-don/chi-tiet-hoa-don/${idHDCT}`)
    const data = await api.json();
    document.getElementById("dataXoaCTSP").innerHTML =
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
    <div class="col-sm-6" style="text-align: center">
        <p style="color: #E43535">${VND.format(data.donGia * data.soLuong)}</p>
    </div>
    <form action="/admin/hoa-don-chi-tiet/delete?idHDCT=${data.id}" method="post" class="row">
        <div class="mb-3">
            <label for="ghiChuKhiXoa" class="form-label">Ghi chú</label>
            <textarea name="ghiChu" class="form-control" id="ghiChuKhiXoa"></textarea>
        </div>
        <div>
            <button class="btn btn-danger" onclick="return validateHuySanPham()">Xác nhận</button>
        </div>
    </form>
</div>
    `
}

close_modalXoaSanPham.onclick = function () {
    modalXoaSanPham.style.display = "none";
}

function validateHuySanPham(){
    const ghiChuKhiXoa = document.getElementById("ghiChuKhiXoa").value;
    if(ghiChuKhiXoa.trim() === ""){
        message.fire({
            text: "Ghi chú không được để trống",
            icon: "error"
        });
        return false;
    }else{
        return true;
    }
}