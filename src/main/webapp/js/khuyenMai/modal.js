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

function modalThemSanPhamKhuyenMai(idSanPham, maSanPham, tenSanPham, giaBanSP) {
    idSP = idSanPham;
    giaBan = giaBanSP;
    document.getElementById("tenSanPhamThem").value = tenSanPham;
    document.getElementById("maSanPhamThem").value = maSanPham;
    document.getElementById("giaBanSanPham").value = VND.format(giaBanSP);
    modalKhuyenMai.style.display = "block";
}

spanSanPhamKhuyenMai.onclick = function () {
    modalSanPhamKhuyenMai.style.display = "none";
}
let updateLoaiGiamGia = null;
let giaBanSanPhamUpdateCTKM = null;

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
            giaBanSanPhamUpdateCTKM = data.giaBanSanPham;
            updateLoaiGiamGia = data.loaiGiamGia.toString();
            document.getElementById("tenSanPham").value = data.tenSanPham;
            document.getElementById("maSanPham").value = data.maSanPham;
            document.getElementById("mucGiamSanPham").value = data.mucGiam;
            document.getElementById("updateSanPhamKhuyenMai").action = "/admin/khuyen-mai/update-san-pham-khuyen-mai?idSPKM=" + id;
        })
}


function loaiGiamGiaUpdate(valueLoaiGiamGia) {
    if (valueLoaiGiamGia != null) {
        updateLoaiGiamGia = valueLoaiGiamGia;
    }
}

function validateUpdate() {
    const mucGiam = document.getElementById("mucGiamSanPham").value.trim();
    if (mucGiam === "") {
        message.fire({
            text: "Mức giá không được để trống",
            icon: "error"
        });
        return false;
    } else if (mucGiam <= 0) {
        message.fire({
            text: "Mức giá không thoả mãn",
            icon: "error"
        });
        return false;
    } else {
        if (updateLoaiGiamGia === "true") {
            if (mucGiam >= 100) {
                message.fire({
                    text: "Mức giá phải nhỏ hơn 100%",
                    icon: "error"
                });
                return false;
            }
        } else {

            if (mucGiam >= Number.parseInt(giaBanSanPhamUpdateCTKM)) {
                message.fire({
                    text: "Mức giảm đang lớn hơn hoặc bằng giá bạn hiện tại : " + giaBanSanPhamUpdateCTKM,
                    icon: "error"
                });
                return false;
            }
        }
        return true;
    }
}


function selectLoaiGiamGia(value) {
    if (value != null) {
        loaiGiamGiaSP = value;
    }
}

async function addKhuyenMaiCT(idKM) {
    const mucGiam = document.getElementById("mucGiam").value.trim();
    if (mucGiam === "") {
        message.fire({
            text: "Mức giá không được để trống",
            icon: "error"
        });
        return false;
    } else if (mucGiam <= 0) {
        message.fire({
            text: "Mức giảm không thoả mãn",
            icon: "error"
        });
        return false;
    } else {
        if (loaiGiamGiaSP === "true") {
            if (mucGiam >= 100) {
                message.fire({
                    text: "Mức giảm phải nhỏ hơn 100%",
                    icon: "error"
                });
                return false;
            }
        } else {
            if (mucGiam >= Number.parseInt(giaBan)) {
                message.fire({
                    text: "Mức giảm đang lớn hơn hoặc bằng giá bạn hiện tại : " + giaBan,
                    icon: "error"
                });
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
            message.fire({
                text: "thêm thành công",
                icon: "success"
            })
            let khuyenMai = null;
            if (response.sanPhamKhuyenMais.length > 0) {
                response.sanPhamKhuyenMais.map(function (e) {
                    if (e.khuyenMai.trangThai === 1 || e.khuyenMai.trangThai === 2) {
                        if (e.trangThai === 1) {
                            khuyenMai = e;
                        }else{
                            khuyenMai = null;
                        }
                    }else{
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
                    <td>
                          ${khuyenMai !== null ?
                    `<div><span style="color: #03AA28">${VND.format(khuyenMai.donGiaSauKhiGiam)}</span></div>

                     <div style="display: flex">
                     <strike>${VND.format(response.giaBan)}</strike>
                     <p style="color: #E43535">${khuyenMai.loaiGiamGia === true ? '-' + khuyenMai.mucGiam + '%' : '-' + VND.format(khuyenMai.mucGiam)}</p>
                     </div>`

                    : `<span style="color: #03AA28">${VND.format(response.giaBan)}</span>`} 
                    </td>
                     ${khuyenMai != null ?
                    `<td style="color: #03AA28"> Đã được áp dụng ${khuyenMai.khuyenMai.ma}</td>` :
                    `<td>
                    <button type="button" class="btn btn-primary" onclick="modalThemSanPhamKhuyenMai('${response.id}' ,
                   '${response.ma}',
                   '${response.ten}',
                   '${response.giaBan}',
                    )">
                    <i class="bi bi-plus-circle"></i>
                  </button>
                  </td>`
                }
             </tr>`
            modalKhuyenMai.style.display = "none";
        }
    }
}


function deleteKhuyenMaiCT(idKMCT, idKM) {
    Swal.fire({
        title: "Bạn có muốn xoá không?",
        showDenyButton: true,
        confirmButtonText: "Có",
        denyButtonText: `Không`
    }).then((result) => {
        /* Read more about isConfirmed, isDenied below */
        if (result.isConfirmed) {
            window.location.href = `/admin/khuyen-mai/delete?idKMCT=${idKMCT}&idKM=${idKM}`
        } else {
            return false;
        }
    });
}