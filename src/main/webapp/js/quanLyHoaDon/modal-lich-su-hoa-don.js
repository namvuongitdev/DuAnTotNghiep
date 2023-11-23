let modalLichSuHoaDon = document.getElementById("modalLichSuHoaDon");
let close_modalLichSuHoaDon = document.getElementById("close_modalLichSuHoaDon");

async function getAllLichHoaDon(idHD) {
    modalLichSuHoaDon.style.display = "block";
    const api = await fetch(`/admin/lich-su-hoa-don?idHD=${idHD}`)
    const data = await api.json();
    for (let i = 0; i < data.length; i++) {
        document.getElementById("bodyLichSu").innerHTML += `
    <tr>
    <td><p>${data[i].thaoTac}</p></td>
    <td><p>${data[i].ngayThaoTac}</p></td>
    <td><p>${data[i].nguoiThaoTac}</p></td>
    <td><p>${data[i].ghiChu}</p></td>
    </tr>
    `
    }
}

close_modalLichSuHoaDon.onclick = function () {
    modalLichSuHoaDon.style.display = "none";
}

