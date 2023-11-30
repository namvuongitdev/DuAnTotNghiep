const VND = new Intl.NumberFormat('vi-VN', {
    style: 'currency',
    currency: 'VND',
});

function getSanPham(page) {
    console.log("san pham");
    const value = document.querySelector("#search-input").value;
    let url = `/index/api-hien-thi?page=` + page + `&value=` + value;
    if (value == null) {
        url = `/index/api-hien-thi?page=` + page;
    }
    fetch(url)
        .then(response => response.json())
        .then(data => {
            console.log(data);
            let pageNo = page <= 1 ? "disabled" : "";
            let active;
            let pageSize = page >= data.totalPages ? "disabled" : "";
            let phanTrang = "";
            // Lấy thẻ div với id="body"
            const bodyDiv = document.getElementById("body");
            // Tạo một div chứa danh sách sản phẩm và thêm lớp Bootstrap
            const productDiv = document.createElement("div");
            productDiv.className = "row product__filter"; // Thêm lớp Bootstrap cho div chứa danh sách sản phẩm
            // Lặp qua danh sách sản phẩm và thêm từng sản phẩm vào div sản phẩm
            let giaBanSanPham = null;
            let giaGoc = null;
            let mucGiam = null;
            let loaiGiamGia = null;
            for (let i = 0; i < data.content.length; i++) {
                const sanPhamDiv = document.createElement("div");
                // sanPhamDiv.className = "col-lg-3 col-md-6 col-sm-6 col-md-6 col-sm-6 mix new-arrivals"; // Thêm lớp Bootstrap cho sản phẩm
                // Tạo nội dung sản phẩm và thêm lớp Bootstrap
                if (data.content[i].sanPhamKhuyenMais.length > 0) {
                    data.content[i].sanPhamKhuyenMais.map(function (e) {
                        if (e.khuyenMai.trangThai == 1 && e.trangThai == 1) {
                            giaGoc = data.content[i].giaBan;
                            giaBanSanPham = e.donGiaSauKhiGiam;
                            mucGiam = e.mucGiam;
                            loaiGiamGia = e.loaiGiamGia;

                        } else {
                            giaBanSanPham = data.content[i].giaBan;
                            giaGoc = null;
                            mucGiam = null;
                        }
                    })
                } else {
                    giaBanSanPham = data.content[i].giaBan;
                    giaGoc = null;
                    mucGiam = null;
                }
                sanPhamDiv.innerHTML = `
                        <div class="product__item" style="margin-left: 30px">
                                <div class="product__item__pic">
                                    <img class="img-thumbnail" src="/image/${data.content[i].img}" style="width:265px;height: 270px" >
                                    <ul class="product__hover">
                                    <form method="get" action="/index/chi-tiet-san-pham/${data.content[i].id}">
                                        <li>
                                         <a href="/index/chi-tiet-san-pham-onl?id=${data.content[i].id}" 
   onclick="return checkAvailability(${data.content[i].trangThai})">
    <img src="/anh/eye.png" width="40px" alt="">
</a>
                                    </form>
                                    </ul>
                                </div>
                                <div class="product__item__text">
                                    <h6>${data.content[i].ten}</h6>
                                    <div class="row">
                                      <div class="col-sm-6"> <h5><strike>${giaGoc == null ? "" : VND.format(giaGoc)}</strike></h5></div>
                                      <div class="col-sm-6"> <h5  
                                      style="color: #E43535;
                                      ">${mucGiam != null ? loaiGiamGia == true ? '-' + mucGiam + '%' : '-' + VND.format(mucGiam) : ""}</div>
                                    </div>
                                    <h5 style="color: #005cbf">${VND.format(giaBanSanPham)}</h5> 
                                   </div>
                                
                                </div>
                        </div>`;

                // Thêm sản phẩm vào div chứa danh sách sản phẩm
                productDiv.appendChild(sanPhamDiv);
            }
            // Xóa toàn bộ nội dung hiện tại của div với id="body"
            bodyDiv.innerHTML = "";
            // Thêm div chứa danh sách sản phẩm vào div với id="body"
            bodyDiv.appendChild(productDiv);
            for (let i = 1; i <= data.totalPages; i++) {
                console.log(i);
                active = page == i ? "active" : ""
                phanTrang +=
                    `<li class="page-item" >
                                <a class="page-link ` + active + `" name="` + i + `" onclick="getSanPham(this.name)" >` + i + `</a>
                                </li>`
            }
            document.getElementById("phanTrang").innerHTML = `<li class="page-item  ` + pageNo + `">
                                <a class="page-link" name="` + (Number.parseInt(page) - Number.parseInt(1)) + `" onclick="getSanPham(this.name)"><</a>
                            </li>` + phanTrang + ` <li class="page-item ` + pageSize + `">
                          <a class="page-link" name="` + (Number.parseInt(page) + Number.parseInt(1)) + `" onclick="getSanPham(this.name)" > > </a></li>`;
        });
}
function sanPhamNhieuNguoiMua(){
    console.log("ssss");
    let url = `/index/api-san-pham-nhieu-nguoi-mua`;
    fetch(url)
        .then(response => response.json())
        .then(data => {
            console.log(data);

            // Lấy thẻ div với id="body"
            const bodyDiv = document.getElementById("body1");
            console.log(bodyDiv)
            // Tạo một div chứa danh sách sản phẩm và thêm lớp Bootstrap
            const productDiv = document.createElement("div");
            productDiv.className = "row product__filter"; // Thêm lớp Bootstrap cho div chứa danh sách sản phẩm
            // Lặp qua danh sách sản phẩm và thêm từng sản phẩm vào div sản phẩm
            let giaBanSanPham = null;
            let giaGoc = null;
            let mucGiam = null;
            let loaiGiamGia = null;
            for (let i = 0; i < data.length; i++) {
                const sanPhamDiv = document.createElement("div");
                // sanPhamDiv.className = "col-lg-3 col-md-6 col-sm-6 col-md-6 col-sm-6 mix new-arrivals"; // Thêm lớp Bootstrap cho sản phẩm
                // Tạo nội dung sản phẩm và thêm lớp Bootstrap
                if (data[i].sanPhamKhuyenMais.length > 0) {
                    data[i].sanPhamKhuyenMais.map(function (e) {
                        if (e.khuyenMai.trangThai == 1 && e.trangThai == 1) {
                            giaGoc = data[i].giaBan;
                            giaBanSanPham = e.donGiaSauKhiGiam;
                            mucGiam = e.mucGiam;
                            loaiGiamGia = e.loaiGiamGia;

                        } else {
                            giaBanSanPham = data[i].giaBan;
                            giaGoc = null;
                            mucGiam = null;
                        }
                    })
                } else {
                    giaBanSanPham = data[i].giaBan;
                    giaGoc = null;
                    mucGiam = null;
                }
                sanPhamDiv.innerHTML = `
                        <div class="owl-item " style="width: 350px; margin-right: 30px;">
                            <div class="product__item" style="margin-left: 30px">
                                <div class="product__item__pic">
                                    <img class="img-thumbnail" src="/image/${data[i].img}" style="width:265px;height: 270px" >
                                    <ul class="product__hover">
                                    <form method="get" action="/index/chi-tiet-san-pham/${data[i].id}">
                                        <li>
                                         <a href="/index/chi-tiet-san-pham-onl?id=${data[i].id}" 
   onclick="return checkAvailability(${data[i].trangThai})">
    <img src="/anh/eye.png" width="40px" alt="">
</a>
                                    </form>
                                    </ul>
                                </div>
                                <div class="product__item__text">
                                    <h6>${data[i].ten}</h6>
                                    <div class="row">
                                      <div class="col-sm-6"> <h5><strike>${giaGoc == null ? "" : VND.format(giaGoc)}</strike></h5></div>
                                      <div class="col-sm-6"> <h5  
                                      style="color: #E43535;
                                      ">${mucGiam != null ? loaiGiamGia == true ? '-' + mucGiam + '%' : '-' + VND.format(mucGiam) : ""}</div>
                                    </div>
                                    <h5 style="color: #005cbf">${VND.format(giaBanSanPham)}</h5> 
                                   </div>
                                
                                </div>
                        </div>`;

                // Thêm sản phẩm vào div chứa danh sách sản phẩm
                productDiv.appendChild(sanPhamDiv);
            }
            // Xóa toàn bộ nội dung hiện tại của div với id="body"
            bodyDiv.innerHTML = "";
            // Thêm div chứa danh sách sản phẩm vào div với id="body"
            bodyDiv.appendChild(productDiv);
        })
        .catch(error => {
            console.error("Error during JSON parsing:", error);
        });
}

function getSanPhamThoiTrangNam(page) {
    console.log("nam");
    const value = document.querySelector("#search-input").value;
    let url = `/index/thoi-trang-nam?page=` + page + `&value=` + value;
    if (value == null) {
        url = `/index/thoi-trang-nam?page=` + page;
    }
    fetch(url)
        .then(response => response.json())
        .then(data => {
            console.log(data);
            let pageNo = page <= 1 ? "disabled" : "";
            let active;
            let pageSize = page >= data.totalPages ? "disabled" : "";
            let phanTrang = "";
            // Lấy thẻ div với id="body"
            const bodyDiv = document.getElementById("body");
            // Tạo một div chứa danh sách sản phẩm và thêm lớp Bootstrap
            const productDiv = document.createElement("div");
            productDiv.className = "row product__filter"; // Thêm lớp Bootstrap cho div chứa danh sách sản phẩm
            // Lặp qua danh sách sản phẩm và thêm từng sản phẩm vào div sản phẩm
            let giaBanSanPham = null;
            let giaGoc = null;
            let mucGiam = null;
            let loaiGiamGia = null;
            for (let i = 0; i < data.content.length; i++) {
                const sanPhamDiv = document.createElement("div");
                // sanPhamDiv.className = "col-lg-3 col-md-6 col-sm-6 col-md-6 col-sm-6 mix new-arrivals"; // Thêm lớp Bootstrap cho sản phẩm
                // Tạo nội dung sản phẩm và thêm lớp Bootstrap
                if (data.content[i].sanPhamKhuyenMais.length > 0) {
                    data.content[i].sanPhamKhuyenMais.map(function (e) {
                        if (e.khuyenMai.trangThai == 1 && e.trangThai == 1) {
                            giaGoc = data.content[i].giaBan;
                            giaBanSanPham = e.donGiaSauKhiGiam;
                            mucGiam = e.mucGiam;
                            loaiGiamGia = e.loaiGiamGia;

                        } else {
                            giaBanSanPham = data.content[i].giaBan;
                            giaGoc = null;
                            mucGiam = null;
                        }
                    })
                } else {
                    giaBanSanPham = data.content[i].giaBan;
                    giaGoc = null;
                    mucGiam = null;
                }
                sanPhamDiv.innerHTML = `
                        <div class="product__item" style="margin-left: 30px">
                                <div class="product__item__pic">
                                    <img class="img-thumbnail" src="/image/${data.content[i].img}" style="width:265px;height: 270px" >
                                    <ul class="product__hover">
                                    <form method="get" action="/index/chi-tiet-san-pham/${data.content[i].id}">
                                        <li>
                                         <a href="/index/chi-tiet-san-pham-onl?id=${data.content[i].id}" 
   onclick="return checkAvailability(${data.content[i].trangThai})">
    <img src="/anh/eye.png" width="40px" alt="">
</a>
                                    </form>
                                    </ul>
                                </div>
                                <div class="product__item__text">
                                    <h6>${data.content[i].ten}</h6>
                                    <div class="row">
                                      <div class="col-sm-6"> <h5><strike>${giaGoc == null ? "" : VND.format(giaGoc)}</strike></h5></div>
                                      <div class="col-sm-6"> <h5  
                                      style="color: #E43535;
                                      ">${mucGiam != null ? loaiGiamGia == true ? '-' + mucGiam + '%' : '-' + VND.format(mucGiam) : ""}</div>
                                    </div>
                                    <h5 style="color: #005cbf">${VND.format(giaBanSanPham)}</h5> 
                                   </div>
                                
                                </div>
                        </div>`;

                // Thêm sản phẩm vào div chứa danh sách sản phẩm
                productDiv.appendChild(sanPhamDiv);
            }
            // Xóa toàn bộ nội dung hiện tại của div với id="body"
            bodyDiv.innerHTML = "";
            // Thêm div chứa danh sách sản phẩm vào div với id="body"
            bodyDiv.appendChild(productDiv);
            for (let i = 1; i <= data.totalPages; i++) {
                console.log(i);
                active = page == i ? "active" : ""
                phanTrang +=
                    `<li class="page-item" >
                                <a class="page-link ` + active + `" name="` + i + `" onclick="getSanPhamThoiTrangNam(this.name)" >` + i + `</a>
                                </li>`
            }
            document.getElementById("phanTrang").innerHTML = `<li class="page-item  ` + pageNo + `">
                                <a class="page-link" name="` + (Number.parseInt(page) - Number.parseInt(1)) + `" onclick="getSanPhamThoiTrangNam(this.name)"><</a>
                            </li>` + phanTrang + ` <li class="page-item ` + pageSize + `">
                          <a class="page-link" name="` + (Number.parseInt(page) + Number.parseInt(1)) + `" onclick="getSanPhamThoiTrangNam(this.name)" > > </a></li>`;
        });
}

function getSanPhamThoiTrangNu(page) {
    const value = document.querySelector("#search-input").value;
    let url = `/index/thoi-trang-nu?page=` + page + `&value=` + value;
    if (value == null) {
        url = `/index/thoi-trang-nu?page=` + page;
    }
    fetch(url)
        .then(response => response.json())
        .then(data => {
            console.log(data);
            let pageNo = page <= 1 ? "disabled" : "";
            let active;
            let pageSize = page >= data.totalPages ? "disabled" : "";
            let phanTrang = "";
            // Lấy thẻ div với id="body"
            const bodyDiv = document.getElementById("body");
            // Tạo một div chứa danh sách sản phẩm và thêm lớp Bootstrap
            const productDiv = document.createElement("div");
            productDiv.className = "row product__filter"; // Thêm lớp Bootstrap cho div chứa danh sách sản phẩm
            // Lặp qua danh sách sản phẩm và thêm từng sản phẩm vào div sản phẩm
            let giaBanSanPham = null;
            let giaGoc = null;
            let mucGiam = null;
            let loaiGiamGia = null;
            for (let i = 0; i < data.content.length; i++) {
                const sanPhamDiv = document.createElement("div");
                // sanPhamDiv.className = "col-lg-3 col-md-6 col-sm-6 col-md-6 col-sm-6 mix new-arrivals"; // Thêm lớp Bootstrap cho sản phẩm
                // Tạo nội dung sản phẩm và thêm lớp Bootstrap
                if (data.content[i].sanPhamKhuyenMais.length > 0) {
                    data.content[i].sanPhamKhuyenMais.map(function (e) {
                        if (e.khuyenMai.trangThai == 1 && e.trangThai == 1) {
                            giaGoc = data.content[i].giaBan;
                            giaBanSanPham = e.donGiaSauKhiGiam;
                            mucGiam = e.mucGiam;
                            loaiGiamGia = e.loaiGiamGia;

                        } else {
                            giaBanSanPham = data.content[i].giaBan;
                            giaGoc = null;
                            mucGiam = null;
                        }
                    })
                } else {
                    giaBanSanPham = data.content[i].giaBan;
                    giaGoc = null;
                    mucGiam = null;
                }
                sanPhamDiv.innerHTML = `
                        <div class="product__item" style="margin-left: 30px">
                                <div class="product__item__pic">
                                    <img class="img-thumbnail" src="/image/${data.content[i].img}" style="width:265px;height: 270px" >
                                    <ul class="product__hover">
                                    <form method="get" action="/index/chi-tiet-san-pham/${data.content[i].id}">
                                        <li>
                                         <a href="/index/chi-tiet-san-pham-onl?id=${data.content[i].id}" 
   onclick="return checkAvailability(${data.content[i].trangThai})">
    <img src="/anh/eye.png" width="40px" alt="">
</a>
                                    </form>
                                    </ul>
                                </div>
                                <div class="product__item__text">
                                    <h6>${data.content[i].ten}</h6>
                                    <div class="row">
                                      <div class="col-sm-6"> <h5><strike>${giaGoc == null ? "" : VND.format(giaGoc)}</strike></h5></div>
                                      <div class="col-sm-6"> <h5  
                                      style="color: #E43535;
                                      ">${mucGiam != null ? loaiGiamGia == true ? '-' + mucGiam + '%' : '-' + VND.format(mucGiam) : ""}</div>
                                    </div>
                                    <h5 style="color: #005cbf">${VND.format(giaBanSanPham)}</h5> 
                                   </div>
                                
                                </div>
                        </div>`;

                // Thêm sản phẩm vào div chứa danh sách sản phẩm
                productDiv.appendChild(sanPhamDiv);
            }
            // Xóa toàn bộ nội dung hiện tại của div với id="body"
            bodyDiv.innerHTML = "";
            // Thêm div chứa danh sách sản phẩm vào div với id="body"
            bodyDiv.appendChild(productDiv);
            for (let i = 1; i <= data.totalPages; i++) {
                console.log(i);
                active = page == i ? "active" : ""
                phanTrang +=
                    `<li class="page-item" >
                                <a class="page-link ` + active + `" name="` + i + `" onclick="getSanPhamThoiTrangNu(this.name)" >` + i + `</a>
                                </li>`
            }
            document.getElementById("phanTrang").innerHTML = `<li class="page-item  ` + pageNo + `">
                                <a class="page-link" name="` + (Number.parseInt(page) - Number.parseInt(1)) + `" onclick="getSanPhamThoiTrangNu(this.name)"><</a>
                            </li>` + phanTrang + ` <li class="page-item ` + pageSize + `">
                          <a class="page-link" name="` + (Number.parseInt(page) + Number.parseInt(1)) + `" onclick="getSanPhamThoiTrangNu(this.name)" > > </a></li>`;
        });
}

function getDanhMuc(page,id) {
    const value = document.querySelector("#search-input").value;
    let url = `/index/danh-muc-san-pham/` + id + `?page=` + page + `&value=` + value;
    if (value == null) {
        url = `/index/danh-muc-san-pham/` + id  + `?page=` + page;
    }
    fetch(url)
        .then(response => response.json())
        .then(data => {
            let pageNo = page <= 1 ? "disabled" : "";
            let active;
            let pageSize = page >= data.totalPages ? "disabled" : "";
            let phanTrang = "";
            // Lấy thẻ div với id="body"
            const bodyDiv = document.getElementById("body");
            // Tạo một div chứa danh sách sản phẩm và thêm lớp Bootstrap
            const productDiv = document.createElement("div");
            productDiv.className = "row product__filter"; // Thêm lớp Bootstrap cho div chứa danh sách sản phẩm
            // Lặp qua danh sách sản phẩm và thêm từng sản phẩm vào div sản phẩm
            let giaBanSanPham = null;
            let giaGoc = null;
            let mucGiam = null;
            let loaiGiamGia = null;
            for (let i = 0; i < data.content.length; i++) {
                const sanPhamDiv = document.createElement("div");
                // sanPhamDiv.className = "col-lg-3 col-md-6 col-sm-6 col-md-6 col-sm-6 mix new-arrivals"; // Thêm lớp Bootstrap cho sản phẩm
                // Tạo nội dung sản phẩm và thêm lớp Bootstrap
                if (data.content[i].sanPhamKhuyenMais.length > 0) {
                    data.content[i].sanPhamKhuyenMais.map(function (e) {
                        if (e.khuyenMai.trangThai == 1 && e.trangThai == 1) {
                            giaGoc = data.content[i].giaBan;
                            giaBanSanPham = e.donGiaSauKhiGiam;
                            mucGiam = e.mucGiam;
                            loaiGiamGia = e.loaiGiamGia;

                        } else {
                            giaBanSanPham = data.content[i].giaBan;
                            giaGoc = null;
                            mucGiam = null;
                        }
                    })
                } else {
                    giaBanSanPham = data.content[i].giaBan;
                    giaGoc = null;
                    mucGiam = null;
                }
                sanPhamDiv.innerHTML = `
                        <div class="product__item" style="margin-left: 30px">
                                <div class="product__item__pic">
                                    <img class="img-thumbnail" src="/image/${data.content[i].img}" style="width:265px;height: 270px" >
                                    <ul class="product__hover">
                                    <form method="get" action="/index/chi-tiet-san-pham/${data.content[i].id}">
                                        <li>
                                         <a href="/index/chi-tiet-san-pham-onl?id=${data.content[i].id}" 
                                            onclick="return checkAvailability(${data.content[i].trangThai})">
                                            <img src="/anh/eye.png" width="40px" alt="">
                                        </a>
                                    </form>
                                    </ul>
                                </div>
                                <div class="product__item__text">
                                    <h6>${data.content[i].ten}</h6>
                                    <div class="row">
                                      <div class="col-sm-6"> <h5><strike>${giaGoc == null ? "" : VND.format(giaGoc)}</strike></h5></div>
                                      <div class="col-sm-6"> <h5  
                                      style="color: #E43535;
                                      ">${mucGiam != null ? loaiGiamGia == true ? '-' + mucGiam + '%' : '-' + VND.format(mucGiam) : ""}</div>
                                    </div>
                                    <h5 style="color: #005cbf">${VND.format(giaBanSanPham)}</h5> 
                                   </div>
                                
                                </div>
                        </div>`;

                // Thêm sản phẩm vào div chứa danh sách sản phẩm
                productDiv.appendChild(sanPhamDiv);
            }
            // Xóa toàn bộ nội dung hiện tại của div với id="body"
            bodyDiv.innerHTML = "";
            // Thêm div chứa danh sách sản phẩm vào div với id="body"
            bodyDiv.appendChild(productDiv);
            for (let i = 1; i <= data.totalPages; i++) {
                console.log(i);
                active = page == i ? "active" : ""
                phanTrang +=
                    `<li class="page-item" >
                                <a class="page-link ` + active + `" name="` + i + `" onclick="pageFilterDM(this.name)" >` + i + `</a>
                                </li>`
            }
            document.getElementById("phanTrang").innerHTML = `<li class="page-item  ` + pageNo + `">
                                <a class="page-link" name="` + (Number.parseInt(page) - Number.parseInt(1)) + `" onclick="previousFilterDM(this.name)"><</a>
                            </li>` + phanTrang + ` <li class="page-item ` + pageSize + `">
                          <a class="page-link" name="` + (Number.parseInt(page) + Number.parseInt(1)) + `" onclick="nextFilterDM(this.name)" > > </a></li>`;
        });
}
function checkAvailability(trangThai) {
    if (trangThai == 1) {
        alert('Hiện sản phẩm này ngừng hoạt động');
        return false; // Ngăn chặn hành động mặc định của liên kết
    }
    return true; // Cho phép thực hiện hành động mặc định của liên kết
}
let data = {
    search: "",
    danhMuc: "",
    chatLieu: "",
    kieuDang: "",
    trangThai: "",
    sapXep: "",
    mauSac: "",
    kichCo: ""

};
let idDanhMuc = "";
function api(page, data) {
    const options = {
        method: 'POST',
        headers: {'Content-Type': 'application/json'},
        body: JSON.stringify(data)
    };
    fetch('/index/api-filter?page=' + page, options)
        .then(response => response.json())
        .then(data => {
            let pageNo = page <= 1 ? "disabled" : "";
            let active;
            let pageSize = page >= data.totalPages ? "disabled" : "";
            let phanTrang = "";
            // Lấy thẻ div với id="body"
            const bodyDiv = document.getElementById("body");
            // Tạo một div chứa danh sách sản phẩm và thêm lớp Bootstrap
            const productDiv = document.createElement("div");
            productDiv.className = "row product__filter"; // Thêm lớp Bootstrap cho div chứa danh sách sản phẩm
// Lặp qua danh sách sản phẩm và thêm từng sản phẩm vào div sản phẩm
            let giaBanSanPham = null;
            let giaGoc = null;
            let mucGiam = null;
            let loaiGiamGia = null;
            for (let i = 0; i < data.content.length; i++) {
                const sanPhamDiv = document.createElement("div");
                // sanPhamDiv.className = "col-lg-3 col-md-6 col-sm-6 col-md-6 col-sm-6 mix new-arrivals"; // Thêm lớp Bootstrap cho sản phẩm
                // Tạo nội dung sản phẩm và thêm lớp Bootstrap
                if (data.content[i].sanPhamKhuyenMais.length > 0) {
                    data.content[i].sanPhamKhuyenMais.map(function (e) {
                        if (e.khuyenMai.trangThai == 1 && e.trangThai == 1) {
                            giaGoc = data.content[i].giaBan;
                            giaBanSanPham = e.donGiaSauKhiGiam;
                            mucGiam = e.mucGiam;
                            loaiGiamGia = e.loaiGiamGia;

                        } else {
                            giaBanSanPham = data.content[i].giaBan;
                            giaGoc = null;
                            mucGiam = null;
                        }
                    })
                } else {
                    giaBanSanPham = data.content[i].giaBan;
                    giaGoc = null;
                    mucGiam = null;
                }
                sanPhamDiv.innerHTML = `
                        <div class="product__item" style="margin-left: 30px">
                                <div class="product__item__pic">
                                    <img class="img-thumbnail" src="/image/${data.content[i].img}" style="width:265px;height: 270px" >
                                    <ul class="product__hover">
                                    <form method="get" action="/index/chi-tiet-san-pham/${data.content[i].id}">
                                        <li><a href="/index/chi-tiet-san-pham-onl?id=${data.content[i].id}"><img src="/anh/eye.png" width="40px" alt=""></a></li>
                                    </form>
                                    </ul>
                                </div>
                                <div class="product__item__text">
                                    <h6>${data.content[i].ten}</h6>
                                    <div class="row">
                                      <div class="col-sm-6"> <h5><strike>${giaGoc == null ? "" : VND.format(giaGoc)}</strike></h5></div>
                                      <div class="col-sm-6"> <h5  
                                      style="color: #E43535;
                                      ">${mucGiam != null ? loaiGiamGia == true ? '-' + mucGiam + '%' : '-' + VND.format(mucGiam) : ""}</div>
                                    </div>
                                    <h5 style="color: #005cbf">${VND.format(giaBanSanPham)}</h5> 
                                   </div>
                                
                                </div>
                        </div>`;

                // Thêm sản phẩm vào div chứa danh sách sản phẩm
                productDiv.appendChild(sanPhamDiv);
            }
            // Xóa toàn bộ nội dung hiện tại của div với id="body"
            bodyDiv.innerHTML = "";
            // Thêm div chứa danh sách sản phẩm vào div với id="body"
            bodyDiv.appendChild(productDiv);
            for (let i = 1; i <= data.totalPages; i++) {
                console.log(i);
                active = page == i ? "active" : ""
                phanTrang +=
                    `<li class="page-item" >
                                <a class="page-link ` + active + `" name="` + i + `" onclick="pageFilter(this.name)" >` + i + `</a>
                                </li>`
            }
            document.getElementById("phanTrang").innerHTML = `<li class="page-item  ` + pageNo + `">
                                <a class="page-link" name="` + (Number.parseInt(page) - Number.parseInt(1)) + `" onclick="previousFilter(this.name)"><</a>
                            </li>` + phanTrang + ` <li class="page-item ` + pageSize + `">
                          <a class="page-link" name="` + (Number.parseInt(page) + Number.parseInt(1)) + `" onclick="nextFilter(this.name)" > > </a></li>`;
        });
}

function filterDanhMuc(id) {
    data.danhMuc = id;
    api(1, data);
    console.log(data)
}

const danhMucFilter = (id) => {
    idDanhMuc = id
    getDanhMuc(1,idDanhMuc);
}
const filterChatLieu = (id) => {
    data.chatLieu = id
    api(1, data);
    console.log(data)
}

const filterKieuDang = (id) => {
    data.kieuDang = id
    api(1, data);
}

const filterTrangThai = (id) => {
    data.trangThai = id
    api(1, data);
}

const filterSapXep = (value) => {
    data.sapXep = value
    api(1, data);
}

const filterColor = (id) => {
    data.mauSac = id;
    api(1, data);
}

const filterSize = (id) => {
    data.kichCo = id;
    api(1, data);
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

function previousFilter(page) {
    api(page, data)
}

function nextFilter(page) {
    api(page, data)
}

function pageFilter(page) {
    api(page, data)
}

function previousFilterDM(page) {
    getDanhMuc(page, idDanhMuc)
}

function nextFilterDM(page) {
    getDanhMuc(page, idDanhMuc)
}

function pageFilterDM(page) {
    getDanhMuc(page, idDanhMuc)
}

document.getElementById('clear').addEventListener('click', () => {
    document.getElementById('search-input').value = "";
    getSanPham(1);
})

function timKiem() {
    getSanPham(1);
}