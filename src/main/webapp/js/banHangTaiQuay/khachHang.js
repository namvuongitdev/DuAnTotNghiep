function getKhachHang(page) {
    const value = document.querySelector("#search-khachHang").value;
    let url = `/admin/khach-hang/api-hien-thi?page=` + page + `&value=` + value;
    if (value == null) {
        url = `/admin/khach-hang/api-hien-thi?page=` + page;
    }
    fetch(url)
        .then(response => response.json())
        .then(data => {
            let pageNo = page <= 1 ? "disabled" : "";
            let active;
            let pageSize = page >= data.totalPages ? "disabled" : "";
            let khachHang = "";
            let phanTrang = "";
            for (let i = 0; i < data.content.length; i++) {
                khachHang += ` <tr>   ` +
                    ` <td>` +
                    data.content[i].hoTen + `</td>` +
                    ` <td>` + data.content[i].email + `</td>` +
                    ` <td>` + data.content[i].sdt + `</td>` +
                    ` <td>` + data.content[i].taiKhoan + `</td>` +
                    ` <td><button class="btn btn-warning" name="` + data.content[i].id + `" onclick="getKhachHangById(this.name)" >Chọn</button></td> </tr>`
            }

            for (let i = 1; i <= data.totalPages; i++) {
                active = page == i ? "active" : ""
                phanTrang +=
                    `<li class="page-item" >
                                <a class="page-link ` + active + `" name="` + i + `" onclick="pageKhachHang(this.name)" >` + i + `</a>
                                </li>`
            }

            document.getElementById("khachHang").innerHTML = khachHang;
            document.getElementById("phanTrangKhachHang").innerHTML = `<li class="page-item  ` + pageNo + `">
                                <a class="page-link" name="` + (Number.parseInt(page) - Number.parseInt(1)) + `" onclick="previousKhachHang(this.name)"><</a>
                            </li>` + phanTrang + ` <li class="page-item ` + pageSize + `">
                          <a class="page-link" name="` + (Number.parseInt(page) + Number.parseInt(1)) + `" onclick="nextKhachHang(this.name)" > > </a></li>`;
        });
}


let modalKhachHang = document.getElementById("khachHangModal");

let btnKhachHang = document.getElementById("btnKhachHang");

let spanKhachHang = document.getElementById("close_khachHang");

spanKhachHang.onclick = function () {
    modalKhachHang.style.display = "none";
}
btnKhachHang.onclick = function () {
    getKhachHang(1);
    modalKhachHang.style.display = "block";
}


function previousKhachHang(page) {
    getKhachHang(page);
}

function nextKhachHang(page) {
    getKhachHang(page);
}

function pageKhachHang(page) {
    getKhachHang(page);
}

function timKiemKhachHang() {
    getKhachHang(1);
}

function lamMoi() {
    document.getElementById('search-khachHang').value = "";
    getKhachHang(1);
}

function getKhachHangById(id) {
    let url_string = window.location.href;
    let url = new URL(url_string);
    let paramValue = url.searchParams.get("idHD");
    window.location.href = '/admin/khach-hang/detail?id=' + id + '&idHD=' + paramValue;
}

let modalThemKhachHang = document.getElementById("modalThemKhachHang");

let btnThemKhachHang = document.getElementById("btnThemKhachHang");

let spanThemKhachHang = document.getElementById("close_themKhachHang");


spanThemKhachHang.onclick = function () {
    modalThemKhachHang.style.display = "none";
}
btnThemKhachHang.onclick = function () {
    modalThemKhachHang.style.display = "block";
}

async function themKhachHang() {
    const tenKhachHang = document.getElementById('tenKhachHang')
    const sdt = document.getElementById('soDienThoaiKhachHang')
    const diaChi = document.getElementById('diaChiKhachHangThemMoi')
    const email = document.getElementById('emailKhachHang')

    const data = {
        hoTen: tenKhachHang.value,
        sdt: sdt.value,
        diaChi: diaChi.value,
        email: email.value
    }

    const options = {
        method: 'POST',
        headers: {'Content-Type': 'application/json'},
        body: JSON.stringify(data)
    };
   await fetch('/admin/khach-hang/create', options)
        .then(response => response.json())

    modalThemKhachHang.style.display = "none";
}