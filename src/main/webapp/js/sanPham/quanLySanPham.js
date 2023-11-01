let idSanPham = null;
let idMauSac = null;

function findAnhMauSac(idSP, idMS) {
    if (idMS == "") {
        window.location.href = `/admin/san-pham/hien-thi/${idSP}`;
    } else {
        idSanPham = idSP;
        idMauSac = idMS;
        fetch(`/admin/anh/anh-mau-sac?idSP=${idSP}&idMS=${idMS}`)
            .then(response => response.json())
            .then(data => {
                document.getElementById("hienThiAnhMauSac").innerHTML =
                    `<div class="card" style="display: block">
                    <div class="card-body row">
                          <div class="row" id="anhs">
                          </div>
                          <div class="row">
                          <div class="col-sm-2">
                            <a type="button"
                            class="btn btn-primary"
                            data-bs-toggle="modal"
                            data-bs-target="#exampleModalAnh">
                            Thêm ảnh
                             </a>
                          </div>
                     </div>
                </div>`
                for (let i = 0; i < data.length; i++) {
                    document.getElementById("anhs").innerHTML += `
                          <div class="col l-3" >
                                            <div>
                                                <img src="/image/${data[i].ten}" alt="..." style="width: 150px ; height: 150px">
                                            </div>
                                        </div>
                      `
                }
            })
    }
}

async function themAnhMauSac() {
    let input = document.querySelector('input[type="file"]')
    let fileAnh = new FormData();
    fileAnh.append('file', input.files[0])
    const options = {
        method: 'POST',
        body: fileAnh
    };
    const api = await fetch(`/admin/chi-tiet-san-pham/add-anh?idSP=${idSanPham}&idMS=${idMauSac}`, options)
    findAnhMauSac(idSanPham, idMauSac);
}


