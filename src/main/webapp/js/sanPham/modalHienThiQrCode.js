let modalQrcode = document.getElementById("modalQrcode");

 async  function  modalQrCode (idctsp){
    modalQrcode.style.display = "block";
    const api = await fetch(`/admin/chi-tiet-san-pham/api-hien-thi-qrcode/${idctsp}`)
    const data = await  api.json();
    console.log(data[0][0]);
    document.getElementById("modalHienThiQrcode").innerHTML = `
         <img src="/qr/${data[0][0]}-${data[0][2]}-${data[0][3]}-${data[0][1]}.png" alt="" style="width: 200px;height: 200px">
       <p style="text-align: center">${data[0][1]}</p> 
      `
}
window.onclick = function(event) {
    if (event.target == modalQrcode) {
        document.getElementById("modalHienThiQrcode").replaceChildren();
        modalQrcode.style.display = "none";
    }
}