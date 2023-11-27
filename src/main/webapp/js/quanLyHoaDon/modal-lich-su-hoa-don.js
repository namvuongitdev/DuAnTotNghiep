let modalLichSuHoaDon = document.getElementById("modalLichSuHoaDon");
let close_modalLichSuHoaDon = document.getElementById("close_modalLichSuHoaDon");

async function getAllLichHoaDon(idHD) {
    modalLichSuHoaDon.style.display = "block";
    const api = await fetch(`/admin/lich-su-hoa-don?idHD=${idHD}`)
    const data = await api.json();
    console.log(data);
    for (let i = 0; i < data.length; i++) {
        document.getElementById("bodyLichSu").innerHTML += `
    <tr>
    <td>${data[i].thaoTac}</td>
    <td>${new Date(data[i].ngayThaoTac).toLocaleString("en-US")}</td>
    <td>${data[i].nguoiThaoTac}</td>
    <td>${data[i].ghiChu}</td>
    </tr>
    `
    }
}
close_modalLichSuHoaDon.onclick = function () {
    modalLichSuHoaDon.style.display = "none";
}

