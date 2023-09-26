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

function updateSoLuong(soLuong, sanPham) {
    const soLuongTon  = +sanPham.soLuongHDCT +  +sanPham.soLuongCTSP;
    if (soLuong < 0) {
        alert("số lượng phải  lớn hơn 0");
    } else if (soLuong > soLuongTon) {
        alert("số lượng hiện tại trong của hàng không đủ");
        window.location.reload();
    } else {
        window.location.href = "/hoa-don/update-san-pham?idHD=" + sanPham.id + "&soLuong=" + Number.parseInt(soLuong);
    }
}
