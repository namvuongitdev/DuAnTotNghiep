let modalQrcode = document.getElementById("modalQrcode");
let modalUpdateChiTietSanPham = document.getElementById("modalUpdateCTSP");
let idCTSP = null;
let idMSac = null
let idKCo = null;

function getMauSacUpdate(id) {
    idMSac = id
}

function getKichCoUpdate(id) {
    idKCo = id;
}

function modalUpdateCTSP(htmlMauSac, htmlKichCo, idctsp, soLuong) {
    modalUpdateChiTietSanPham.style.display = "block";
    idCTSP = idctsp;
    document.getElementById("updateMauSac").innerHTML = `
     ${htmlMauSac}
     `
    document.getElementById("updateSize").innerHTML = `
     ${htmlKichCo}
     `
}

async function updateSizeAndMauSac(idSP) {
    const soLuong = document.getElementById("updateSoLuongCTSP").value;
    if (idMSac === null) {
        const mauSac = document.getElementById("updateMauSac")
        idMSac = mauSac.value;
    }
    if (idKCo === null) {
        const kichCo = document.getElementById("updateSize")
        idKCo = kichCo.value;
    }
        const data = {
            idCTSP: idCTSP,
            idSP: idSP,
            idMS: idMSac,
            idKC: idKCo,
            soLuong: soLuong
        }
        const options = {
            method: 'PUT',
            headers: {'Content-Type': 'application/json'},
            body: JSON.stringify(data)
        };
        const api = await fetch(`/admin/chi-tiet-san-pham/update-size-mau-sac`, options);
        const response = await api.json();
        if (response.success) {
            await Swal.fire("update thành công", "", "success");
            window.location.reload();
        } else {
            message.fire({
                icon: "error",
                text: "dữ liệu đã tồn tại"
            });
        }
}

async function modalQrCode(idctsp) {
    modalQrcode.style.display = "block";
    const api = await fetch(`/admin/chi-tiet-san-pham/api-hien-thi-qrcode/${idctsp}`)
    const data = await api.json();
    console.log(data[0][0]);
    document.getElementById("modalHienThiQrcode").innerHTML = `
         <img src="/qr/${data[0][0]}-${data[0][1]}.png" alt="" style="width: 200px;height: 200px">
       <p style="text-align: center">${data[0][1]}</p> 
      `
}

window.onclick = function (event) {
    if (event.target === modalQrcode) {
        document.getElementById("modalHienThiQrcode").replaceChildren();
        modalQrcode.style.display = "none";
    }
    if (event.target === modalThemCTSP) {
        modalThemCTSP.style.display = "none";
    }
    if (event.target === modalUpdateChiTietSanPham) {
        modalUpdateChiTietSanPham.style.display = "none";
    }
    if (event.target === modalMauSac) {
        modalMauSac.style.display = "none"
    }
    if (event.target === modalKichCo) {
        modalKichCo.style.display = "none"
    }
    if (event.target === modalChatLieu) {
        modalChatLieu.style.display = "none"
    }
    if (event.target === modalKieuDang) {
        modalKieuDang.style.display = "none"
    }
    if (event.target === modalDanhMuc) {
        modalDanhMuc.style.display = "none"
    }
    if (event.target === modalAnh) {
        modalAnh.style.display = "none"
    }
}