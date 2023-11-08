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
            let giaBanSanPham = null;
            for (let i = 0; i < data.content.length; i++) {
                if (data.content[i].sanPhamKhuyenMais.length > 0) {
                    data.content[i].sanPhamKhuyenMais.map(function (e) {
                        if (e.khuyenMai.trangThai == 1 && e.trangThai == 1) {
                            giaBanSanPham = e.donGiaSauKhiGiam;
                        } else {
                            giaBanSanPham = data.content[i].giaBan;
                        }
                    })
                } else {
                    giaBanSanPham = data.content[i].giaBan;
                }
                sanPham += `<tr>
                   <td><img style="width: 60px ; height: 60px" src="/image/${data.content[i].img} "></td>
                    <td>${data.content[i].ma}</td>
                    <td>${data.content[i].ten}</td>
                    <td style="color: #03AA28">${VND.format(giaBanSanPham)}</td>
                    ${data.content[i].trangThai != 0 ? `<td style="color: #E43535">ngừng kinh doanh</td>` : `<td>
                   <button  id="myBtn"  onclick="getModal({idSanPham:'${data.content[i].id}' , 
                    tenSanPham:'${data.content[i].ten}' , giaSanPham:${giaBanSanPham} , img:'${data.content[i].img}'})" class="btn btn-warning" >Chọn</button>
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
            let giaBanSanPham = null;
            for (let i = 0; i < data.content.length; i++) {
                if (data.content[i].sanPhamKhuyenMais.length > 0) {
                    data.content[i].sanPhamKhuyenMais.map(function (e) {
                        if (e.khuyenMai.trangThai == 1 && e.trangThai == 1) {
                            giaBanSanPham = e.donGiaSauKhiGiam;
                        } else {
                            giaBanSanPham = data.content[i].giaBan;
                        }
                    })

                } else {
                    giaBanSanPham = data.content[i].giaBan;
                }
                sanPham += `<tr>
                   <td><img style="width: 60px ; height: 60px" src="/image/${data.content[i].img} "></td>
                    <td>${data.content[i].ma}</td>
                    <td>${data.content[i].ten}</td>
                    <td style="color: #03AA28">${VND.format(giaBanSanPham)}</td>
                    <td> <button  id="myBtn"  onclick="getModal({idSanPham:'${data.content[i].id}' , 
                    tenSanPham:'${data.content[i].ten}' , giaSanPham:${giaBanSanPham} , img:'${data.content[i].img}'})" class="btn btn-warning" >Chọn</button></td>
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
    if (soLuong < 0) {
        alert("số lượng phải  lớn hơn 0");
    } else if (soLuong > soLuongTon) {
        alert("số lượng hiện tại trong của hàng không đủ");
        window.location.reload();
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
        method: 'DELETE',
        headers: {'Content-Type': 'application/json'},
    };
    if (confirm("Bạn có muốn xoá sản phẩm ra khỏi giỏ hàng không") == true) {
        const apiDeleteSanPham = await fetch(`/admin/hoa-don/delete?idHDCT=${idHDCT}`, options)
        const data = await apiDeleteSanPham.json();
        document.getElementById(data.id).remove();
        await tongTien();
    } else {
        return;
    }
}
