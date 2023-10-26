
let kichCo;
let mauSac;
let sanPham;
let dataCTSP;
fetch("/index/chi-tiet" )
    .then(response => response.json())
    .then(data => {
        alert("lựa chon các thuộc tính sản phẩm")
        dataCTSP = data;
        getMauSac(data);
        getSize(data);
    });

function getMauSac(data) {
    for (let i = 0; i < data.length; i++) {
        let mauSacSP = document.getElementById(data[i].mauSac.id);
        if (mauSacSP != null) {
            continue;
        } else {
            dataColor(data[i].mauSac)
        }
    }
}

function getSize(data) {
    for (let i = 0; i < data.length; i++) {
        let kichCoSP = document.getElementById(data[i].size.id);
        if (kichCoSP != null) {
            continue;
        } else {
            dataSize(data[i].size)
        }
    }
}

function dataSize(data) {
    sizeSP.innerHTML += `
                       <input type="radio" class="btn-check" onclick="getCTSP({
                         id:this.value,
                         type:'kichCo'
                       })"
                        value="` + data.id + `"   name="success-outlined-1"  id="` + data.id + `" autocomplete="off">
                            <label class="btn btn-outline-secondary"  for="` + data.id + `">` + data.ten + `</label>
                      `
}

function dataColor(data) {
    colorSP.innerHTML += `
                        <input type="radio" class="btn-check" onclick="getCTSP({
                          id:this.value,
                          type:'mauSac'
                        })"
                         value="` + data.id + `"   name="success-outlined" id="` + data.id + `" autocomplete="off">
                             <label class="btn btn-outline-secondary"  for="` + data.id + `">` + data.ten + `</label>
                       `
}

function getCTSP(id) {
    if (id.type == 'kichCo') {
        kichCo = id.id;
    } else {
        mauSac = id.id;
        sizeSP.innerHTML = "";
        kichCo = undefined;
        document.getElementById("themVaoGioHang").name = "";
        for (var i = 0; i < dataCTSP.length; i++) {
            if (dataCTSP[i].mauSac.id == mauSac && dataCTSP[i].sanPham.id == sanPham) {
                dataSize(dataCTSP[i].size)
            }

        }
    }
    if (mauSac != undefined && kichCo != undefined) {
        console.log('trang thai : ' + dataCTSP.trangThai);
        const sp = document.getElementById("sp");
        const themVaoGioHang = document.getElementById("themVaoGioHang");
        for (let i = 0; i < dataCTSP.length; i++) {
            if (dataCTSP[i].mauSac.id == mauSac && dataCTSP[i].size.id == kichCo && dataCTSP[i].sanPham.id == sanPham) {
                if (dataCTSP[i].soLuong == 0 || dataCTSP[i].soLuong < 0) {
                    console.log("null")
                    themVaoGioHang.setAttribute("disabled", "");
                    sp.innerHTML += `<h5 id="message" style="color: #e43535">Sản phẩm hết hàng</h5>`
                    return;
                } else {
                    themVaoGioHang.removeAttribute('disabled');
                    document.getElementById("soLuong").innerText = `số lượng sản phẩm còn ` + dataCTSP[i].soLuong
                    themVaoGioHang.name = dataCTSP[i].id;
                    document.getElementById("soLuongTon").max = dataCTSP[i].soLuong;
                    return;
                }
            }
        }
    }

}