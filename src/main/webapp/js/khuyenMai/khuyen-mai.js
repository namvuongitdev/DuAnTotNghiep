const VND = new Intl.NumberFormat('vi-VN', {
    style: 'currency',
    currency: 'VND',
});
let modalSanPham = document.getElementById("modalSanPham");
const message = Swal.mixin({
    toast: true,
    position: "top-end",
    showConfirmButton: false,
    timer: 3000,
    timerProgressBar: true,
    didOpen: (toast) => {
        toast.onmouseenter = Swal.stopTimer;
        toast.onmouseleave = Swal.resumeTimer;
    }
});
let data = {
    search: "",
    danhMuc: "",
    chatLieu: "",
    kieuDang: "",
    trangThai: "",
    sapXep: "",
    mauSac: "",
    kichCo: "",
    gioiTinh: ""

};

function getSanPham(page) {
    modalSanPham.style.display = "block";
    const value = document.querySelector("#search-input").value;
    data.search = value;
    let url = `/admin/san-pham/api-hien-thi?page=${page}&value=${value}`;
    if (value == null) {
        url = `/admin/san-pham/api-hien-thi?page=${page}`;
    }
    fetch(url)
        .then(response => response.json())
        .then(data => {
            let pageNo = page <= 1 ? "disabled" : "";
            let active;
            let pageSize = page >= data.totalPages ? "disabled" : "";
            let sanPham = "";
            let phanTrang = "";
            let khuyenMai = null;
            for (let i = 0; i < data.content.length; i++) {
                if (data.content[i].sanPhamKhuyenMais.length > 0) {
                    data.content[i].sanPhamKhuyenMais.map(function (e) {
                        if (e.khuyenMai.trangThai === 1 || e.khuyenMai.trangThai === 2) {
                            if(e.trangThai === 1){
                                khuyenMai = e;
                            }
                        }
                    })
                } else {
                    khuyenMai = null;
                }

                sanPham += `<tr id="${data.content[i].ma}"><td><img style="width: 60px ; height: 60px" src="/image/${data.content[i].img}"></td>
                  <td>${data.content[i].ma}</td>
                  <td>${data.content[i].ten}</td>
                  <td>
                    ${khuyenMai !== null ?
                    `<div><span style="color: #03AA28">${VND.format(khuyenMai.donGiaSauKhiGiam)}</span></div>

                     <div style="display: flex">
                     <strike>${VND.format(data.content[i].giaBan)}</strike>
                     <p style="color: #E43535">${khuyenMai.loaiGiamGia === true ? '-' + khuyenMai.mucGiam + '%' : '-' + VND.format(khuyenMai.mucGiam)}</p>
                     </div>`

                    : `<span style="color: #03AA28">${VND.format(data.content[i].giaBan)}</span>`}
                    </td>
           
                   ${khuyenMai !== null ? `<td style="color: #03AA28"> Đã được áp dụng ${khuyenMai.khuyenMai.ma}</td>` :
                    `<td>
                      <button type="button" class="btn btn-primary" onclick="modalThemSanPhamKhuyenMai('${data.content[i].id}' ,
                    '${data.content[i].ma}',
                     '${data.content[i].ten}',
                     '${data.content[i].giaBan}'
                     )">
                     <i class="bi bi-plus-circle"></i>
                     </button>
                     </td>`}
                
                  </tr>`
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
            let khuyenMai = null;
            for (let i = 0; i < data.content.length; i++) {

                if (data.content[i].sanPhamKhuyenMais.length > 0) {
                    data.content[i].sanPhamKhuyenMais.map(function (e) {
                        if (e.khuyenMai.trangThai === 1 || e.khuyenMai.trangThai === 2 && e.trangThai === 1) {
                            khuyenMai = e;
                        } else {
                            khuyenMai = null;
                        }
                    })
                } else {
                    khuyenMai = null;
                }

                sanPham += `<tr><td><img style="width: 60px ; height: 60px" src="/image/${data.content[i].img}"></td>
                  <td>${data.content[i].ma}</td>
                  <td>${data.content[i].ten}</td>
                  <td>
                      ${khuyenMai !== null ?
                    `<div><span style="color: #03AA28">${VND.format(khuyenMai.donGiaSauKhiGiam)}</span></div>

                     <div style="display: flex">
                     <strike>${VND.format(data.content[i].giaBan)}</strike>
                     <p style="color: #E43535">${khuyenMai.loaiGiamGia === true ? '-' + khuyenMai.mucGiam + '%' : '-' + VND.format(khuyenMai.mucGiam)}</p>
                     </div>`

                    : `<span style="color: #03AA28">${VND.format(data.content[i].giaBan)}</span>`}
                  </td>
                 ${khuyenMai != null ? `<td style="color: #03AA28"> Đã được áp dụng ${khuyenMai.khuyenMai.ma}</td>` :
                    `<td>
                      <button type="button" class="btn btn-primary" onclick="modalThemSanPhamKhuyenMai('${data.content[i].id}' ,
                    '${data.content[i].ma}',
                     '${data.content[i].ten}',
                     '${data.content[i].giaBan}'
                     )">
                     <i class="bi bi-plus-circle"></i>
                     </button>
                     </td>`}
                   </tr>`
            }

            for (let i = 1; i <= data.totalPages; i++) {
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
    api(1, data);
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

window.onclick = function (event) {
    if (event.target == modalSanPham) {
        modalSanPham.style.display = "none";
        window.location.reload();
    }
}

