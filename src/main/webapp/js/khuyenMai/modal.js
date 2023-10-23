let modalKhuyenMai = document.getElementById("modalKhuyenMai");

let btnKhuyenMai = document.getElementById("btnKhuyenMai");

let spanKhuyenMai = document.getElementById("close_khuyenMai");


let modalSanPhamKhuyenMai = document.getElementById("modalSanPhamKhuyenMai");

let btnSanPhamKhuyenMai = document.getElementById("btnSanPhamKhuyenMai");

let spanSanPhamKhuyenMai = document.getElementById("close_sanPhamKhuyenMai");

spanKhuyenMai.onclick = function () {
    modalKhuyenMai.style.display = "none";
}

btnKhuyenMai.onclick = function () {
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
            const loaiGiamGiaSanPham = document.getElementsByName("loaiGiamGia");
            if (data.loaiGiamGia) {
                loaiGiamGiaSanPham[0].checked = "checked";
            } else {
                loaiGiamGiaSanPham[1].checked = "checked";
            }
            document.getElementById("tenSanPham").value = data.tenSanPham;
            document.getElementById("maSanPham").value = data.maSanPham;
            document.getElementById("mucGiamSanPham").value = data.mucGiam;
            document.getElementById("updateSanPhamKhuyenMai").action="/admin/khuyen-mai/update-san-pham-khuyen-mai?idSPKM="+id;

        })
}
