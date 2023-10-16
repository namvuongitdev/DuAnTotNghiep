function getSanPham(page) {
    const value = document.querySelector("#search-input").value;
    let url = `/admin/san-pham/api-hien-thi?page=` + page + `&value=` + value;
    if (value == null) {
        url = `/admin/san-pham/api-hien-thi?page=` + page;
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
                    `<img style="width: 150px ; height: 150px" src="/image/` + data.content[i].img + `">` +
                    ` <td>` + data.content[i].ma + `</td>` +
                    ` <td>` + data.content[i].ten + `</td>` +
                    ` <td>` + VND.format(data.content[i].giaBan) + `</td>` +
                    ` <td><button  id="myBtn" name="` + data.content[i].id + `" onclick="getModal(this.name)" class="btn btn-warning" >Chọn</button></td> </tr>`
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
                                <a class="page-link" name="` + (Number.parseInt(page) - Number.parseInt(1)) + `" onclick="previous(this.name)"><</a>
                            </li>` + phanTrang + ` <li class="page-item ` + pageSize + `">
                          <a class="page-link" name="` + (Number.parseInt(page) + Number.parseInt(1)) + `" onclick="next(this.name)" > > </a></li>`;
        });
}

let data = {
    search: "",
    danhMuc: "",
    chatLieu: "",
    kieuDang: "",
    trangThai: "",
    sapXep: "",
    mauSac: "",
    kichCo: "",
    gioiTinh:""

};

function api(page, data) {
    const options = {
        method: 'POST',
        headers: {'Content-Type': 'application/json'},
        body: JSON.stringify(data)
    };
    fetch('/admin/san-pham/api-filter?page=' + page, options)
        .then(response => response.json())
        .then(data => {
            let pageNo = page <= 1 ? "disabled" : "";
            let active;
            let pageSize = page >= data.totalPages ? "disabled" : "";
            let sanPham = "";
            let phanTrang = "";
            for (let i = 0; i < data.content.length; i++) {
                sanPham += ` <tr>  ` +
                    ` <td>` +
                    `<img style="width: 100px ; height: 100px" src="/image/` + data.content[i].img + `">` +
                    ` <td>` + data.content[i].ma + `</td>` +
                    ` <td>` + data.content[i].ten + `</td>` +
                    ` <td>` + VND.format(data.content[i].giaBan) + `</td>` +
                    ` <td><button  id="myBtn" name="` + data.content[i].id + `" onclick="getModal(this.name)" class="btn btn-warning" >Chọn</button></td> </tr>`
            }

            for (let i = 1; i <= data.totalPages; i++) {
                console.log(i);
                active = page == i ? "active" : ""
                phanTrang +=
                    `<li class="page-item" >
                                <a class="page-link ` + active + `" name="` + i + `" onclick="pageFilter(this.name)" >` + i + `</a>
                                </li>`
            }

            document.getElementById("body").innerHTML = sanPham;
            document.getElementById("phanTrang").innerHTML = `<li class="page-item  ` + pageNo + `">
                                <a class="page-link" name="` + (Number.parseInt(page) - Number.parseInt(1)) + `" onclick="previousFilter(this.name)"><</a>
                            </li>` + phanTrang + ` <li class="page-item ` + pageSize + `">
                          <a class="page-link" name="` + (Number.parseInt(page) + Number.parseInt(1)) + `" onclick="nextFilter(this.name)" > > </a></li>`;
        });
}

function filterDanhMuc(id) {
    data.danhMuc = id;
    api(1, data);
    console.log(data)
}

const filterChatLieu = (id) => {
    data.chatLieu = id
    api(1, data);
    console.log(data)
}

const filterKieuDang = (id) => {
    data.kieuDang = id
    api(1, data);
}

const filterTrangThai = (id) => {
    data.trangThai = id
    api(1, data);
}

const filterSapXep = (value) => {
    data.sapXep = value
    api(1, data);
}
const filterColor = (id) => {
    data.mauSac = id;
    api(1, data);
}

const filterSize = (id) => {
    data.kichCo = id;
    api(1, data);
}

const filterGioiTinh = (id) => {
    data.gioiTinh = id;
    api(1 , data);
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

function previousFilter(page) {
    api(page, data)
}

function nextFilter(page) {
    api(page, data)
}

function pageFilter(page) {
    api(page, data)
}

document.getElementById('clear').addEventListener('click', () => {
   document.getElementById('search-input').value = "";
    getSanPham(1);
})

function timKiem() {
    getSanPham(1);
}

function updateSoLuong(soLuong, sanPham) {
    const soLuongTon = +sanPham.soLuongHDCT + +sanPham.soLuongCTSP;
    if (soLuong < 0) {
        alert("số lượng phải  lớn hơn 0");
    } else if (soLuong > soLuongTon) {
        alert("số lượng hiện tại trong của hàng không đủ");
        window.location.reload();
    } else {
        window.location.href = "/admin/hoa-don/update-san-pham?idHD=" + sanPham.id + "&soLuong=" + Number.parseInt(soLuong) + "&idKhachHang="+sanPham.idKhachHang;
    }
}
