let modalKhuyenMai = document.getElementById("modalKhuyenMai");

let spanKhuyenMai = document.getElementById("close_khuyenMai");

let modalSanPhamKhuyenMai = document.getElementById("modalSanPhamKhuyenMai");

let spanSanPhamKhuyenMai = document.getElementById("close_sanPhamKhuyenMai");

spanKhuyenMai.onclick = function () {
    modalKhuyenMai.style.display = "none";
}
let idSP = null;
let loaiGiamGiaSP = "true";
let giaBan = null;

function modalThemSanPhamKhuyenMai(idSanPham, maSanPham, tenSanPham , giaBanSP) {
    idSP = idSanPham;
    giaBan = giaBanSP;
    document.getElementById("tenSanPhamThem").value = tenSanPham;
    document.getElementById("maSanPhamThem").value = maSanPham;
    modalKhuyenMai.style.display = "block";
}

spanSanPhamKhuyenMai.onclick = function () {
    modalSanPhamKhuyenMai.style.display = "none";
}

function getSanPhamKhuyenMai(id) {
    modalSanPhamKhuyenMai.style.display = "block";
    fetch('/admin/khuyen-mai/api-san-pham-khuyen-mai/' + id)
        .then(response => response.json())
        .then(data => {
            const loaiGiamGiaSanPham = document.getElementsByClassName("btn-check loaiGiamGia");
            if (data.loaiGiamGia) {
                loaiGiamGiaSanPham[0].checked = "checked";
            } else {
                loaiGiamGiaSanPham[1].checked = "checked";
            }
            document.getElementById("tenSanPham").value = data.tenSanPham;
            document.getElementById("maSanPham").value = data.maSanPham;
            document.getElementById("mucGiamSanPham").value = data.mucGiam;
            document.getElementById("updateSanPhamKhuyenMai").action = "/admin/khuyen-mai/update-san-pham-khuyen-mai?idSPKM=" + id;
        })
}

function selectLoaiGiamGia(value) {
    if (value != null) {
        loaiGiamGiaSP = value;
    }
}

async function addKhuyenMaiCT(idKM) {
    const mucGiam = document.getElementById("mucGiam").value.trim();
    if (mucGiam === "") {
        alert("mức giảm không được để trống")
        return false;
    } else if (mucGiam <= 0) {
        alert("mức giảm không thoả mãn")
        return false;
    } else {
        if (loaiGiamGiaSP === "true") {
            if (mucGiam >= 100) {
                alert("mức giảm giá phải nhỏ hơn 100%")
                return false;
            }
        }else{
            if(mucGiam >= giaBan){
                alert("mức giảm giá đang lớn hơn giá bán hiện tại")
                return false;
            }
        }
        const data = {
            idSanPham: idSP,
            loaiGiamGia: loaiGiamGiaSP,
            mucGiam: mucGiam,
            idKM: idKM

        }
        const options = {
            method: 'POST',
            headers: {'Content-Type': 'application/json'},
            body: JSON.stringify(data)
        };
        const api = await fetch("/admin/khuyen-mai/khuyen-mai-san-pham", options);
        const response = await api.json();
        if (api.status === 200) {
            let khuyenMai = null;
            if (response.sanPhamKhuyenMais.length > 0) {
                response.sanPhamKhuyenMais.map(function (e) {
                    if (e.khuyenMai.trangThai == 1 && e.trangThai == 1) {
                        khuyenMai = e;
                    } else {
                        khuyenMai = null;
                    }
                })
            } else {
                khuyenMai = null;
            }
            document.getElementById(response.ma).innerHTML =
                `<tr id="${response.ma}">
                    <td><img style="width: 60px ; height: 60px" src="/image/${response.img}"></td>
                     <td>${response.ma}</td>
                     <td>${response.ten}</td>
                    <td>${VND.format(response.giaBan)}</td>
                     ${khuyenMai != null ?
                    `<td style="color: #03AA28"> Đã được áp dụng</td>` :
                    `<td>
                    <button type="button" class="btn btn-primary" onclick="modalThemSanPhamKhuyenMai('${response.id}' ,
                   '${response.ma}',
                   '${response.ten}'
                    )">
                    <i class="bi bi-plus-circle"></i>
                  </button>
                  </td>`
                }
             </tr>`
            alert("thêm thành công");
            modalKhuyenMai.style.display = "none";
        }
    }

}
