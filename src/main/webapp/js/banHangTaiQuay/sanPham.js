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
let modalHienThiSanPham = document.getElementById("modalHienThiSanPham");


function getSanPham(page) {
    modalHienThiSanPham.style.display = "block";
    const value = document.querySelector("#search-input").value;
    data.search = value;
    let url = "/admin/san-pham/api-hien-thi?page=" + page + "&value=" + value;
    if (value == null) {
        url = "/admin/san-pham/api-hien-thi?page=" + page;
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
                        if (e.khuyenMai.trangThai === 1 && e.trangThai === 1) {
                            khuyenMai = e;
                        } else {
                            khuyenMai = null;
                        }
                    })
                } else {
                    khuyenMai = null;
                }

                sanPham += `<tr>
                   <td><img style="width: 60px ; height: 60px" src="/image/${data.content[i].img} "></td>
                    <td>${data.content[i].ma}</td>
                    <td>${data.content[i].ten}</td>
                    <td>
                    ${khuyenMai != null ?
                    `<div><span style="color: #03AA28">${VND.format(khuyenMai.donGiaSauKhiGiam)}</span></div>

                     <div style="display: flex">
                     <strike>${VND.format(data.content[i].giaBan)}</strike>
                     <p style="color: #E43535">${khuyenMai.loaiGiamGia == true ? '-'+khuyenMai.mucGiam+'%' : '-'+VND.format(khuyenMai.mucGiam)}</p>
                     </div>`

                    : `<span style="color: #03AA28">${VND.format(data.content[i].giaBan)}</span>`}
                    </td>
                    
                    ${data.content[i].trangThai != 0 ? `<td style="color: #E43535">ngừng kinh doanh</td>` :
                    `<td>
                   <button  id="myBtn"  onclick="getModal(
                       {idSanPham:'${data.content[i].id}' , 
                       tenSanPham:'${data.content[i].ten}' ,
                       giaBan:'${data.content[i].giaBan}',
                       img:'${data.content[i].img}',
                       donGiaSauKhiGiam:'${khuyenMai != null ? khuyenMai.donGiaSauKhiGiam : null }',
                       loaiGiamGia:'${khuyenMai != null ? khuyenMai.loaiGiamGia : null}',
                       mucGiam:'${khuyenMai != null ? khuyenMai.mucGiam : null}'
                       },
                       )" class="btn btn-warning" >Chọn</button>
                   </td>`}
                   </tr>`
            }
            for (let i = 1; i <= data.totalPages; i++) {
                active = page == i ? "active" : ""
                phanTrang +=
                    `<li class="page-item" >
                                <a class="page-link ${active}" name="${i}" onclick="page(this.name)" >${i}</a>
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
                        if (e.khuyenMai.trangThai === 1 && e.trangThai === 1) {
                            khuyenMai = e;
                        } else {
                            khuyenMai = null;
                        }
                    })
                } else {
                    khuyenMai = null;
                }

                sanPham += `<tr>
                   <td><img style="width: 60px ; height: 60px" src="/image/${data.content[i].img} "></td>
                    <td>${data.content[i].ma}</td>
                    <td>${data.content[i].ten}</td>
                    <td>
                    ${khuyenMai != null ?
                    `<div><span style="color: #03AA28">${VND.format(khuyenMai.donGiaSauKhiGiam)}</span></div>

                     <div style="display: flex">
                     <strike>${VND.format(data.content[i].giaBan)}</strike>
                     <p style="color: #E43535">${khuyenMai.loaiGiamGia == true ? '-'+khuyenMai.mucGiam+'%' : '-'+VND.format(khuyenMai.mucGiam)}</p>
                     </div>`

                    : `<span style="color: #03AA28">${VND.format(data.content[i].giaBan)}</span>`}
                    </td>
                    
                    ${data.content[i].trangThai != 0 ? `<td style="color: #E43535">ngừng kinh doanh</td>` :
                    `<td>
                   <button  id="myBtn"  onclick="getModal(
                       {idSanPham:'${data.content[i].id}' , 
                       tenSanPham:'${data.content[i].ten}' ,
                       giaBan:'${data.content[i].giaBan}',
                       img:'${data.content[i].img}',
                       donGiaSauKhiGiam:'${khuyenMai != null ? khuyenMai.donGiaSauKhiGiam : null }',
                       loaiGiamGia:'${khuyenMai != null ? khuyenMai.loaiGiamGia : null}',
                       mucGiam:'${khuyenMai != null ? khuyenMai.mucGiam : null}'
                       },
                       )" class="btn btn-warning" >Chọn</button>
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

async function tongTien() {
    const tongTienTrongGioHang = document.getElementById("tongTienTrongGioHang");
    const tienHang = document.getElementById("tienHang");
    const tienKhachCanTra = document.getElementById("tienKhachCanTra");
    let url_hoa_don = window.location.href;
    let url_id_hd = new URL(url_hoa_don);
    let paramValueIdHD = url_id_hd.searchParams.get("idHD");
    const apiTongTienHDCT = await fetch(`/admin/hoa-don/tong-tien?idHD=${paramValueIdHD}`)
    const data = await apiTongTienHDCT.json();
    if (tongTienTrongGioHang != null) {
        tongTienTrongGioHang.innerText = `Tổng tiền :${VND.format(data)}`
    }
    if (tienHang != null) {
        tienHang.innerText = `Tiền Hàng :${VND.format(data)}`
    }
    if (tienKhachCanTra != null) {
        tienKhachCanTra.innerText = `Khách cần trả :${VND.format(data)}`
    }
}


function innnerHTMLTrByIdHDCT(data) {
    document.getElementById(data.id).innerHTML = `
                <td>
                    <div class="row">
                        <div class="col l-3">
                            <img src="/image/${data.chiTietSanPham.sanPham.img}"
                                 style="width: 80px; height: 80px">
                        </div>
                        <div class="col l-3">
                            <h5>${data.chiTietSanPham.sanPham.ten}</h5>
                            <p style="color: #03AA28">${VND.format(data.donGia)}</p>
                            <p>size : ${data.chiTietSanPham.size.ten}</p>
                            <p>màu sắc : ${data.chiTietSanPham.mauSac.ten}</p>
                        </div>
                    </div>
                </td>
                <td style="width: 110px ;"><input
                        onchange="updateSoLuong(this.value , {id:\`${data.id}\`,
                                soLuongHDCT:\`${data.soLuong}\`,
                                soLuongCTSP:\`${data.chiTietSanPham.soLuong}\`,
                               }
                                )" type="number"
                        name="soLuong" class="form-control" value="${data.soLuong}" min="1"
                />
                </td>
                <td id="thanhTien">${VND.format(data.soLuong * data.donGia)}</td>
                <td><a onclick="deleteSanPhamTrongGioHang('${data.id}')"
                         class="btn btn-danger">Xoá khỏi giỏ</a></td>
            </tr>
        `
}

async function updateSoLuong(soLuong, sanPham) {
    const soLuongTon = +sanPham.soLuongHDCT + +sanPham.soLuongCTSP;
    if (soLuong <= 0) {
        alert("số lượng phải lớn hơn hoặc 0")
        window.location.reload()
    } else if (soLuong > soLuongTon) {
        alert("số lượng hiện tại trong của hàng chỉ còn : " + sanPham.soLuongCTSP);
        window.location.reload()
    } else {
        const options = {
            method: 'PUT',
            headers: {'Content-Type': 'application/json'},
        };
        const apiUpdateSoLuong = await fetch(`/admin/hoa-don/update-san-pham?idHD=${sanPham.id}&soLuong=${soLuong}`, options)
        const data = await apiUpdateSoLuong.json();
        innnerHTMLTrByIdHDCT(data);
        await tongTien();
    }
}

async function deleteSanPhamTrongGioHang(idHDCT) {
    const options = {
        method: 'PUT',
        headers: {'Content-Type': 'application/json'},
    };
    Swal.fire({
        title: "Bạn có xoá sản phẩm không?",
        showDenyButton: true,
        confirmButtonText: "Có",
        denyButtonText: `Không`
    }).then(async (result) => {
        if (result.isConfirmed) {
            const apiDeleteSanPham = await fetch(`/admin/hoa-don/delete?idHDCT=${idHDCT}`, options)
            const data = await apiDeleteSanPham.json();
            document.getElementById(data.id).remove();
            await tongTien();
        } else if (result.isDenied) {
            return false;
        }
    });
}

window.onclick = function (event) {
    if (event.target == modalHienThiSanPham) {
        modalHienThiSanPham.style.display = "none";
    }
}
