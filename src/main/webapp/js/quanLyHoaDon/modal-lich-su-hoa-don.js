let modalLichSuHoaDon = document.getElementById("modalLichSuHoaDon");
let close_modalLichSuHoaDon = document.getElementById("close_modalLichSuHoaDon");

async function getAllLichHoaDon(idHD) {
    modalLichSuHoaDon.style.display = "block";
    const api = await fetch(`/admin/lich-su-hoa-don?idHD=${idHD}`)
    const data = await api.json();
    for (let i = 0; i < data.length; i++) {

        document.getElementById("bodyLichSu").innerHTML += `
    <tr>
    <td>${thaoTacHoaDon(data[i].thaoTac)}</td>
    <td>${new Date(data[i].ngayThaoTac).toLocaleString("en-US")}</td>
    <td>${data[i].nguoiThaoTac}</td>
    <td>${data[i].ghiChu}</td>
    </tr>
    `
    }
}
close_modalLichSuHoaDon.onclick = function () {
    document.getElementById("bodyLichSu").replaceChildren();
    modalLichSuHoaDon.style.display = "none";
}
function thaoTacHoaDon(thaoTac){
    if (thaoTac === 2) {
       return "xác nhận hoá đơn";
    }
    if (thaoTac === 3) {
        return "xác nhận giao hàng";
    }
    if (thaoTac === 6) {
        return "xác nhận giao hàng thành công";
    }
    if (thaoTac === 4) {
        return "xác nhận hoàn thành";
    }
    if (thaoTac === 7) {
        return "chỉnh sửa hoá đơn";
    }
    if (thaoTac === 11) {
        return "khách hàng tạo hoá đơn";
    }
    if (thaoTac === 5) {
        return "huỷ hoá đơn";
    }
    if (thaoTac === 2) {
        return "hoàn trả";
    }
}
