let modalUpdateSoLuongSanPham = document.getElementById("modalUpdateSoLuongSanPham");
let close_modalUpdateSoLuongSanPham = document.getElementById("close_modalUpdateSoLuongSanPham");

async function updateSoLuong(idHDCT) {
    modalUpdateSoLuongSanPham.style.display = "block";
    const api = await fetch(`/admin/hoa-don/chi-tiet-hoa-don/${idHDCT}`)
    const data = await api.json();
    document.getElementById("dataUpdateSoLuong").innerHTML =
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
<form action="/admin/hoa-don-chi-tiet/update?idHDCT=${data.id}" method="post" class="row" onclick="return validateSoLuong('${data.soLuongCTSP}')">
    <div>
          <label for="soLuongUpdate">Số lượng</label>
                <input type="number" value="${data.soLuong}" id="soLuongUpdate" name="soLuongUpdate" style="width: 30%"
                class="form-control"/>
    </div>
    <div class="mb-3">
        <label for="ghiChuKhiUpdate" class="form-label">Ghi chú</label>
        <textarea name="ghiChuUpdate" class="form-control" id="ghiChuKhiUpdate"></textarea>
    </div>
    <div>
        <button class="btn btn-danger">Xác nhận</button>
    </div>
</form>
    `
}

close_modalUpdateSoLuongSanPham.onclick = function () {
    modalUpdateSoLuongSanPham.style.display = "none";
}

function validateSoLuong (soLuongCTSP){
    const soLuongThem = document.getElementById("soLuongUpdate").value;
    if(Number.parseInt(soLuongThem) <= 0){
        alert("số lượng phải không thảo mãn");
        return false;
    }
    else if(Number.parseInt(soLuongThem) > soLuongCTSP){
        alert("số lượng hiện tại không đủ chỉ còn :" + soLuongCTSP);
        return false;
    }else{
        return true;
    }
}