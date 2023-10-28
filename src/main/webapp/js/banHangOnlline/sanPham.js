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
            for (let i = 0; i < data.content.length; i++) {
                const sanPhamDiv = document.createElement("div");
                // sanPhamDiv.className = "col-lg-3 col-md-6 col-sm-6 col-md-6 col-sm-6 mix new-arrivals"; // Thêm lớp Bootstrap cho sản phẩm
                // Tạo nội dung sản phẩm và thêm lớp Bootstrap
                sanPhamDiv.innerHTML = null;
                if(data.content[i].donGiaSauKhiGiam == null){
                    sanPhamDiv.innerHTML =  `
                        <div class="product__item" style="margin-left: 30px">
                                <div class="product__item__pic">
                                    <img src="/anh/${data.content[i].img}" style="width:265px;height: 270px" >
                                    <ul class="product__hover">
                                    <form method="get" action="/index/chi-tiet-san-pham-onl?id=${data.content[i].id}">
                                        <li><a href="/index/chi-tiet-san-pham-onl?id=${data.content[i].id}"><img src="/anh/eye.png" width="40px" alt=""></a></li>
                                    </form>
                                    </ul>
                                </div>
                                <div class="product__item__text">
                                    <h6>${data.content[i].ten}</h6>
                                    <a href="#" class="add-cart">+ Add To Cart</a> 
                                    <h5>${data.content[i].giaFormat} đ</h5> 
                                </div> 
                        </div>`;
                }else {
                    sanPhamDiv.innerHTML =  `
                        <div class="product__item" style="margin-left: 30px">
                                <div class="product__item__pic">
                                    <img src="/anh/${data.content[i].img}" style="width:265px;height: 270px" >
                                    <ul class="product__hover">
                                    <form method="get" action="/index/chi-tiet-san-pham-onl?${data.content[i].id}">
                                        <li><a href="/index/chi-tiet-san-pham-onl?id=${data.content[i].id}"><img src="/anh/eye.png" width="40px" alt=""></a></li>
                                    </form>
                                    </ul>
                                </div>
                                <div class="product__item__text">
                                    <h6>${data.content[i].ten}</h6>
                                    <a href="#" class="add-cart">+ Add To Cart</a> 
                                    <h5><strike>${data.content[i].giaFormat} đ</strike></h5> 
                                    <h5 style="color: #E43535">${data.content[i].donGiaSauKhiGiam1}</h5>
                                </div> 
                        </div>`;
                }
                // Thêm sản phẩm vào div chứa danh sách sản phẩm
                productDiv.appendChild(sanPhamDiv);
            }
                // Xóa toàn bộ nội dung hiện tại của div với id="body"
                bodyDiv.innerHTML = "";
                // Thêm div chứa danh sách sản phẩm vào div với id="body"
                bodyDiv.appendChild(productDiv);

            for (let i = 1; i <= data.totalPages; i++) {
                active = page == i ? "background-color:#4e555b" : ""
                phanTrang +=
                    `<li class="page-item" >
                                <a class="page-link" style="`+active+`" name="` + i + `" onclick="page(this.name)" >` + i + `</a>
                                </li>`
            }
            // document.getElementById("body").innerHTML = sanPham;
            document.getElementById("phanTrang").innerHTML = `<li class="page-item  ` + pageNo + `">
                                <a class="page-link" name="` + (Number.parseInt(page) - Number.parseInt(1)) + `" onclick="previous(this.name)"><</a>
                            </li>` + phanTrang + ` <li class="page-item ` + pageSize + `">
                          <a class="page-link" name="` + (Number.parseInt(page) + Number.parseInt(1)) + `" onclick="next(this.name)" > > </a></li>`;
        });
}

function getSanPhamThoiTrangNam(page) {
    const value = document.querySelector("#search-input").value;
    let url = `/index/thoi-trang-nam?page=` + page + `&value=` + value;
    if (value == null) {
        url = `/index/thoi-trang-nam?page=` + page;
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
            for (let i = 0; i < data.content.length; i++) {
                const sanPhamDiv = document.createElement("div");
                // sanPhamDiv.className = "col-lg-3 col-md-6 col-sm-6 col-md-6 col-sm-6 mix new-arrivals"; // Thêm lớp Bootstrap cho sản phẩm
                // Tạo nội dung sản phẩm và thêm lớp Bootstrap
                sanPhamDiv.innerHTML = null;
                if(data.content[i].donGiaSauKhiGiam == null){
                    sanPhamDiv.innerHTML =  `
                        <div class="product__item" style="margin-left: 30px">
                                <div class="product__item__pic">
                                    <img src="/anh/${data.content[i].img}" style="width:265px;height: 270px" >
                                    <ul class="product__hover">
                                    <form method="get" action="/index/chi-tiet-san-pham/${data.content[i].id}">
                                        <li><a href="/index/chi-tiet-san-pham-onl?id=${data.content[i].id}"><img src="/anh/eye.png" width="40px" alt=""></a></li>
                                    </form>
                                    </ul>
                                </div>
                                <div class="product__item__text">
                                    <h6>${data.content[i].ten}</h6>
                                    <a href="#" class="add-cart">+ Add To Cart</a> 
                                    <h5>${data.content[i].giaFormat} đ</h5> 
                                </div> 
                        </div>`;
                }else {
                    sanPhamDiv.innerHTML =  `
                        <div class="product__item" style="margin-left: 30px">
                                <div class="product__item__pic">
                                    <img src="/anh/${data.content[i].img}" style="width:265px;height: 270px" >
                                    <ul class="product__hover">
                                    <form method="get" action="/index/chi-tiet-san-pham/${data.content[i].id}">
                                        <li><a href="/index/chi-tiet-san-pham-onl?id=${data.content[i].id}"><img src="/anh/eye.png" width="40px" alt=""></a></li>
                                    </form>
                                    </ul>
                                </div>
                                <div class="product__item__text">
                                    <h6>${data.content[i].ten}</h6>
                                    <a href="#" class="add-cart">+ Add To Cart</a> 
                                    <h5><strike>${data.content[i].giaFormat} đ</strike></h5> 
                                    <h5 style="color: #E43535">${data.content[i].donGiaSauKhiGiam1}</h5>
                                </div> 
                        </div>`;
                }
                // Thêm sản phẩm vào div chứa danh sách sản phẩm
                productDiv.appendChild(sanPhamDiv);
            }
            // Xóa toàn bộ nội dung hiện tại của div với id="body"
            bodyDiv.innerHTML = "";
            // Thêm div chứa danh sách sản phẩm vào div với id="body"
            bodyDiv.appendChild(productDiv);

            for (let i = 1; i <= data.totalPages; i++) {
                active = page == i ? "background-color:#4e555b" : ""
                phanTrang +=
                    `<li class="page-item" >
                                <a class="page-link" style="`+active+`" name="` + i + `" onclick="getSanPhamThoiTrangNam(this.name)" >` + i + `</a>
                                </li>`
            }
            // document.getElementById("body").innerHTML = sanPham;
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
            for (let i = 0; i < data.length; i++) {
                const sanPhamDiv = document.createElement("div");
                // sanPhamDiv.className = "col-lg-3 col-md-6 col-sm-6 col-md-6 col-sm-6 mix new-arrivals"; // Thêm lớp Bootstrap cho sản phẩm
                // Tạo nội dung sản phẩm và thêm lớp Bootstrap
                sanPhamDiv.innerHTML = null;
                if(data.content[i].donGiaSauKhiGiam == null){
                    sanPhamDiv.innerHTML =  `
                        <div class="product__item" style="margin-left: 30px">
                                <div class="product__item__pic">
                                    <img src="/anh/${data.content[i].img}" style="width:265px;height: 270px" >
                                    <ul class="product__hover">
                                    <form method="get" action="/index/chi-tiet-san-pham/${data.content[i].id}">
                                        <li><a href="/index/chi-tiet-san-pham-onl?id=${data.content[i].id}"><img src="/anh/eye.png" width="40px" alt=""></a></li>
                                    </form>
                                    </ul>
                                </div>
                                <div class="product__item__text">
                                    <h6>${data.content[i].ten}</h6>
                                    <a href="#" class="add-cart">+ Add To Cart</a> 
                                    <h5>${data.content[i].giaFormat} đ</h5> 
                                </div> 
                        </div>`;
                }else {
                    sanPhamDiv.innerHTML =  `
                        <div class="product__item" style="margin-left: 30px">
                                <div class="product__item__pic">
                                    <img src="/anh/${data.content[i].img}" style="width:265px;height: 270px" >
                                    <ul class="product__hover">
                                    <form method="get" action="/index/chi-tiet-san-pham/${data.content[i].id}">
                                        <li><a href="/index/chi-tiet-san-pham-onl?id=${data.content[i].id}"><img src="/anh/eye.png" width="40px" alt=""></a></li>
                                    </form>
                                    </ul>
                                </div>
                                <div class="product__item__text">
                                    <h6>${data.content[i].ten}</h6>
                                    <a href="#" class="add-cart">+ Add To Cart</a> 
                                    <h5><strike>${data.content[i].giaFormat} đ</strike></h5> 
                                    <h5 style="color: #E43535">${data.content[i].donGiaSauKhiGiam1}</h5>
                                </div> 
                        </div>`;
                }
                // Thêm sản phẩm vào div chứa danh sách sản phẩm
                productDiv.appendChild(sanPhamDiv);
            }
            // Xóa toàn bộ nội dung hiện tại của div với id="body"
            bodyDiv.innerHTML = "";
            // Thêm div chứa danh sách sản phẩm vào div với id="body"
            bodyDiv.appendChild(productDiv);

            for (let i = 1; i <= data.totalPages; i++) {
                active = page == i ? "background-color:#4e555b" : ""
                phanTrang +=
                    `<li class="page-item" >
                                <a class="page-link" style="`+active+`" name="` + i + `" onclick="getSanPhamThoiTrangNu(this.name)" >` + i + `</a>
                                </li>`
            }
            // document.getElementById("body").innerHTML = sanPham;
            document.getElementById("phanTrang").innerHTML = `<li class="page-item  ` + pageNo + `">
                                <a class="page-link" name="` + (Number.parseInt(page) - Number.parseInt(1)) + `" onclick="getSanPhamThoiTrangNu(this.name)"><</a>
                            </li>` + phanTrang + ` <li class="page-item ` + pageSize + `">
                          <a class="page-link" name="` + (Number.parseInt(page) + Number.parseInt(1)) + `" onclick="getSanPhamThoiTrangNu(this.name)" > > </a></li>`;
        });
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
            let giaBanSanPham =null;
            for (let i = 0; i < data.content.length; i++) {
                const sanPhamDiv = document.createElement("div");
                // sanPhamDiv.className = "col-lg-3 col-md-6 col-sm-6 col-md-6 col-sm-6 mix new-arrivals"; // Thêm lớp Bootstrap cho sản phẩm
                // Tạo nội dung sản phẩm và thêm lớp Bootstrap
                if(data.content[i].sanPhamKhuyenMais.length>0){
                    data.content[i].sanPhamKhuyenMais.map(function (e){
                        if(e.khuyenMai.trangThai==1 && e.trangThai==1){
                            giaBanSanPham=e.donGiaSauKhiGiam;

                        }else {
                            giaBanSanPham=data.content[i].giaBan;
                        }
                    })
                }else {
                    giaBanSanPham=data.content[i].giaBan;
                }
                    sanPhamDiv.innerHTML =  `
                        <div class="product__item" style="margin-left: 30px">
                                <div class="product__item__pic">
                                    <img src="/anh/${data.content[i].img}" style="width:265px;height: 270px" >
                                    <ul class="product__hover">
                                    <form method="get" action="/index/chi-tiet-san-pham/${data.content[i].id}">
                                        <li><a href="/index/chi-tiet-san-pham-onl?id=${data.content[i].id}"><img src="/anh/eye.png" width="40px" alt=""></a></li>
                                    </form>
                                    </ul>
                                </div>
                                <div class="product__item__text">
                                    <h6>${data.content[i].ten}</h6>
                                    <a href="#" class="add-cart">+ Add To Cart</a> 
                                    <h5>${giaBanSanPham} đ</h5> 
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

document.getElementById('clear').addEventListener('click', () => {
   document.getElementById('search-input').value = "";
    getSanPham(1);
})

function timKiem() {
    getSanPham(1);
}


