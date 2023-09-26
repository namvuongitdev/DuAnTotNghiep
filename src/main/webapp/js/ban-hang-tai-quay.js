let sizeSP = document.getElementById("kichCo");
let colorSP = document.getElementById("mauSac");
let kichCo;
let mauSac;
let sanPham;
let dataCTSP;

const VND = new Intl.NumberFormat('vi-VN', {
    style: 'currency',
    currency: 'VND',
});

var modal = document.getElementById("myModal");

var btn = document.getElementById("myBtn");

var span = document.getElementsByClassName("close")[0];

function getThongTin(id) {
    fetch('/khach-hang/' + id)
        .then(response => response.json())
        .then(data => {
            document.getElementById("thongTinKhachHang").innerHTML = ` <div>` +
                `Tên khách hàng : ` + data.hoTen +
                `</div>` +
                `<br>` +
                ` <div>` +
                ` Email : ` + data.email +
                `</div>` +
                `<br>`;
            document.getElementById("huyChon").innerHTML = `<a class="btn btn-outline-danger">Huỷ chọn</a>`
        });
}

function timKiemSanPham(page) {
    getSanPham(page);
}

function loadLaiSanPham() {
    const page = "1";
    document.getElementById("sanPham").value = "";
    getSanPham(page);
}

function getSanPham(page) {
    const value = document.getElementById("sanPham").value;
    console.log(value);
    let url = '/san-pham/api-hien-thi/' + page + "?value=" + value;
    if (value == null) {
        url = '/san-pham/api-hien-thi/' + page;
    }
    fetch(url)
        .then(response => response.json())
        .then(data => {
            let pageNo = page <= 1 ? "disabled" : "";
            let active;
            let pageSize = page >= data.totalPages ? "disabled" : "";
            let sanPham = "";
            let phanTrang = "";
            for (let i = 0; i < data.content.length; i++) {
                sanPham += ` <tr>   ` +
                    ` <td>` +
                    `<img style="width: 150px ; height: 150px" src="/image/` + data.content[i][3] + `">` +
                    ` <td>` + data.content[i][6] + `</td>` +
                    ` <td>` + data.content[i][1] + `</td>` +
                    ` <td>` + VND.format(data.content[i][2]) + `</td>` +
                    ` <td><button  id="myBtn" name="` + data.content[i][0] + `" onclick="getModal(this.name)" class="btn btn-warning" >Chọn</button></td> </tr>`
            }

            for (let i = 1; i <= data.totalPages; i++) {
                active = page == i ? "active" : ""
                phanTrang +=
                    `<li class="page-item" >
                                <a class="page-link ` + active + `" name="` + i + `" onclick="page(this.name)" >` + i + `</a>
                                </li>`
            }

            document.getElementById("body").innerHTML = sanPham;
            document.getElementById("phanTrang").innerHTML = `<li class="page-item  ` + pageNo + `">
                                <a class="page-link" name="` + (Number.parseInt(page) - Number.parseInt(1)) + `" onclick="previous(this.name)">Previous</a>
                            </li>` + phanTrang + ` <li class="page-item ` + pageSize + `">
                          <a class="page-link" name="` + (Number.parseInt(page) + Number.parseInt(1)) + `" onclick="next(this.name)" >Next</a></li>`;
        });
}

function previous(page) {
    getSanPham(page);
}

function next(page) {
    getSanPham(page);
}

function page(page) {
    getSanPham(page);
}

function getModal(id) {
    sanPham = id;
    modal.style.display = "block";
    fetch("/chi-tiet-san-pham/" + id)
        .then(response => response.json())
        .then(data => {
            dataCTSP = data;
            for (let i = 0; i < data.length; i++) {
                document.getElementById("img").innerHTML = `<img src="/image/` + data[i].sanPham.img + `">`
                document.getElementById("sp").innerHTML = `<div><h3>` + data[i].sanPham.ten + `</h3></div> <div><h5 style="color: #03AA28">` + VND.format(data[i].sanPham.giaBan) + `</h5></div>`
                break;
            }
            getMauSac(data);
            getSize(data);
        });
}

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
        for (let i = 0; i < dataCTSP.length; i++) {
            if (dataCTSP[i].mauSac.id == mauSac && dataCTSP[i].size.id == kichCo && dataCTSP[i].sanPham.id == sanPham) {
                if (dataCTSP[i].soLuong == 0 || dataCTSP[i].soLuong < 0) {
                    document.getElementById("themVaoGioHang").setAttribute("disabled", "");
                    document.getElementById("sp").innerHTML += `<h5 style="color: #E43535">Sản phẩm hết hàng</h5>`
                    return;
                } else {
                    document.getElementById("soLuong").innerText = `số lượng sản phẩm còn ` + dataCTSP[i].soLuong
                    document.getElementById("themVaoGioHang").name = dataCTSP[i].id;
                    document.getElementById("soLuongTon").max = dataCTSP[i].soLuong;
                    return;
                }
            }
        }
    }
}

function themSanPhamVaoGioHang(idCTSP) {
    var soLuong = document.getElementById("soLuongTon").value;
    var idHD = document.getElementById("soLuongTon").name;
    if (idCTSP == "") {
        alert("lựa chon các thuộc tính sản phẩm")
    } else {
        for (let i = 0; i < dataCTSP.length; i++) {
            if (idCTSP == dataCTSP[i].soLuong) {
                if (soLuong > dataCTSP[i].soLuong) {
                    alert("số lượng sản phẩm không đủ");
                    return;
                }
            }
        }
        window.location.href = "/hoa-don/add-san-pham?ctsp=" + idCTSP + "&soLuong=" + soLuong + "&idHD=" + idHD;
    }
}

function updateSoLuong(soLuong, id) {
    if (soLuong < 0) {
        alert("số lượng phải  lớn hơn 0");
        return;
    } else {
        window.location.href = "/hoa-don/update-san-pham?idHD=" + id + "&soLuong=" + Number.parseInt(soLuong);
    }
}

span.onclick = function () {
    colorSP.innerHTML = ""
    sizeSP.innerHTML = ""
    document.getElementById("soLuong").innerHTML = "";
    document.getElementById("themVaoGioHang").name = "";
    document.getElementById("soLuongTon").value = 1;
    document.getElementById("sp").innerHTML = "";
    document.getElementById("img").innerHTML = "";
    mauSac = undefined;
    sanPham = undefined;
    kichCo = undefined;
    dataCTSP = undefined;
    modal.style.display = "none";
}


function getTienKhachDua(tienKhachDua) {
    let xacNhanThanhToan = document.getElementById("xacNhanThanhToan");

    const tienThua = Number.parseInt(tienKhachDua + "0") - 300000;
    document.getElementById("tienThuaCuaKhach").innerHTML = `Tiền thừa của khách :` + VND.format(tienThua);

    if (tienKhachDua + "0" < 300000) {
        xacNhanThanhToan.setAttribute("disabled", "");
    }else if(tienKhachDua instanceof String){
        xacNhanThanhToan.setAttribute("disabled", "");
    }
    else {
        xacNhanThanhToan.removeAttribute("disabled");
    }
}


document.getElementById('tienMat').onclick = function (e) {
    if (this.checked) {
        document.getElementById("khachDuaTienMat").innerHTML =
            `<input class="form-control" type="text" style="width: 50%" id="tienKhachDuaTienMat" onkeypress="getTienKhachDua(this.value)">
           <label for="tienKhachDuaTienMat">Khách đưa tiền mặt</label>
            <span id="tienThuaCuaKhach" style="color: #03AA28"></span>`
    } else {
        document.getElementById("khachDuaTienMat").innerHTML = ""
    }
}

document.getElementById('chuyenKhoan').onclick = function (e) {
    if (this.checked) {
        document.getElementById("khachDuaChuyenKhoan").innerHTML =
            `<input class="form-control" type="text" style="width: 50%" id="tienKhachDuaChuyenKhoa" onkeypress="getTienKhachDua(this.value)">
           <label for="tienKhachDuaChuyenKhoa">Khách chuyển khoản</label> <span id="tienThuaCuaKhach" style="color: #03AA28"></span>`
    } else {
        document.getElementById("khachDuaChuyenKhoan").innerHTML = ""
    }
}









