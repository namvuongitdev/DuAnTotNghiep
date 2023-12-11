let idSanPham = null;
let idMauSac = null;
let modalThemCTSP = document.getElementById("modalThemChiTietSanPham");


function modalThemChiTietSanPham(){
    modalThemCTSP.style.display = "block";
}


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
                          <div class="row" id="anhs" style="margin-bottom: 20px">
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
                                                <img class="img-thumbnail" src="/image/${data[i].ten}" alt="..." style="width: 150px ; height: 150px">
                                            </div>
                                            <div>
                                               ${data[i].sanPham.img === data[i].ten ? "" :
                        `   <a type="button" class="btn btn-secondary" onclick="anhMacDinhSanPham('${idSP}','${data[i].ten}')">Mặc định</a>
                                               <a type="button" class="btn btn-danger" onclick="xoaAnh('${data[i].id}')">Xoá</a>`
                    }
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

async function anhMacDinhSanPham(idSP, tenAnh) {
    const anhMacDinh = document.getElementById("anhMacDinhSanPham");
    anhMacDinh.replaceChildren();
    const api = await fetch(`/admin/san-pham/add-anh-mac-dinh?img=${tenAnh}&idSP=${idSP}`);
    const dataSanPham = await api.json();
    anhMacDinh.innerHTML = ` <div class="pagination justify-content-center">
                                    <img src="/image/${dataSanPham.img}" alt="" class="img-thumbnail" style="width: 200px;height: 200px">
                                </div>`
    findAnhMauSac(idSP, idMauSac);
}

async function xoaAnh(idAnh) {
    const api = await fetch(`/admin/chi-tiet-san-pham/remove-anh?idAnh=${idAnh}`);
    findAnhMauSac(idSanPham, idMauSac);
}

let host = "http://localhost:8080/admin/san-pham";
const app = angular.module("app", []);

app.controller("controller", function ($scope, $http) {
    //Chất liệu
    $scope.chatLieu = {}
    $scope.lstChatLieu = {}
    $scope.loadChatLieu = function () {
        var url = `${host}/hienThi-chatLieu`;
        $http.get(url).then(resp => {
            $scope.lstChatLieu = resp.data;
            console.log("Success", resp)
        }).catch(error => {
            console.log("Error", error)
        });
    }
    $scope.addChatLieu = function () {
        Swal.fire({
            title: "Bạn có muốn thêm không?",
            showDenyButton: true,
            confirmButtonText: "Có",
            denyButtonText: `Không`
        }).then((result) => {
            if (result.isConfirmed) {
                if ($scope.chatLieu.ten == null) {
                    Swal.fire("Thêm dữ liệu thất bại", "", "error");
                } else {
                    // Kiểm tra trùng tên dữ liệu
                    $http.get(`${host}/check-tenChatLieu/` + $scope.chatLieu.ten).then(response => {
                        if (response.data.isDuplicate) {
                            Swal.fire("Tên dữ liệu đã tồn tại", "", "error");
                        } else {
                            var item = angular.copy($scope.chatLieu);
                            var url = `${host}/add-chatLieu`;
                            $http.post(url, item).then(resp => {
                                $scope.lstChatLieu.push(item);
                                Swal.fire("Thêm dữ liệu thành công", "", "success");
                                location.reload();
                                saveInputValues();
                                console.log("Success", resp);
                            }).catch(error => {
                                console.log("Error", error);
                            });
                        }
                    });
                }
            } else {
                return false;
            }
        });
    }

    //Danh mục
    $scope.danhMuc = {}
    $scope.lstDanhMuc = {}
    $scope.loadDanhMuc = function () {
        var url = `${host}/hienThi-danhMuc`;
        $http.get(url).then(resp => {
            $scope.lstDanhMuc = resp.data;
            console.log("Success", resp)
        }).catch(error => {
            console.log("Error", error)
        });
    }
    $scope.addDanhMuc = function () {
        Swal.fire({
            title: "Bạn có muốn thêm không?",
            showDenyButton: true,
            confirmButtonText: "Có",
            denyButtonText: `Không`
        }).then((result) => {
            /* Read more about isConfirmed, isDenied below */
            if (result.isConfirmed) {
                if ($scope.danhMuc.ten == null) {
                    Swal.fire("Thêm dữ liệu thất bại", "", "error");
                } else {
                    // Kiểm tra trùng tên dữ liệu
                    $http.get(`${host}/check-tenDanhMuc/` + $scope.danhMuc.ten).then(response => {
                        if (response.data.isDuplicate) {
                            Swal.fire("Tên dữ liệu đã tồn tại", "", "error");
                        } else {
                            var item = angular.copy($scope.danhMuc);
                            var url = `${host}/add-danhMuc`;
                            $http.post(url, item).then(resp => {
                                $scope.lstDanhMuc.push(item);
                                Swal.fire("Thêm dữ liệu thành công", "", "success");
                                location.reload();
                                saveInputValues();
                                console.log("Success", resp)
                            }).catch(error => {
                                console.log("Error", error)
                            });
                        }
                    });
                }
            }else{
                return false;
            }
        });
    }

    //Kiểu dáng
    $scope.kieuDang = {}
    $scope.lstKieuDang = {}
    $scope.loadKieuDang = function () {
        var url = `${host}/hienThi-kieuDang`;
        $http.get(url).then(resp => {
            $scope.lstKieuDang = resp.data;
            console.log("Success", resp)
        }).catch(error => {
            console.log("Error", error)
        });
    }
    $scope.addKieuDang = function () {
        Swal.fire({
            title: "Bạn có muốn thêm không?",
            showDenyButton: true,
            confirmButtonText: "Có",
            denyButtonText: `Không`
        }).then((result) => {
            /* Read more about isConfirmed, isDenied below */
            if (result.isConfirmed) {
                if ($scope.kieuDang.ten == null) {
                    Swal.fire("Thêm dữ liệu thất bại", "", "error");
                } else {
                    $http.get(`${host}/check-tenKieuDang/` + $scope.kieuDang.ten).then(response => {
                        if (response.data.isDuplicate) {
                            Swal.fire("Tên dữ liệu đã tồn tại", "", "error");
                        } else {
                            var item = angular.copy($scope.kieuDang);
                            var url = `${host}/add-kieuDang`;
                            $http.post(url, item).then(resp => {
                                $scope.lstKieuDang.push(item);
                                Swal.fire("Thêm dữ liệu thành công", "", "success");
                                location.reload();
                                saveInputValues();
                                console.log("Success", resp)
                            }).catch(error => {
                                console.log("Error", error)
                            });
                        }
                    });
                }
            }else{
                return false;
            }
        });
    }

    //Kich cỡ
    $scope.kichCo = {}
    $scope.lstKichCo = {}
    $scope.loadKichCo = function () {
        var url = `${host}/hienThi-kichCo`;
        $http.get(url).then(resp => {
            $scope.lstKichCo = resp.data;
            console.log("Success", resp)
        }).catch(error => {
            console.log("Error", error)
        });
    }
    $scope.addKichCo = function () {
        Swal.fire({
            title: "Bạn có muốn thêm không?",
            showDenyButton: true,
            confirmButtonText: "Có",
            denyButtonText: `Không`
        }).then((result) => {
            /* Read more about isConfirmed, isDenied below */
            if (result.isConfirmed) {
                if ($scope.kichCo.ten == null) {
                    Swal.fire("Thêm dữ liệu thất bại", "", "error");
                } else {
                    $http.get(`${host}/check-tenKichCo/` + $scope.kichCo.ten).then(response => {
                        if (response.data.isDuplicate) {
                            Swal.fire("Tên dữ liệu đã tồn tại", "", "error");
                        } else {
                            var item = angular.copy($scope.kichCo);
                            var url = `${host}/add-kichCo`;
                            $http.post(url, item).then(resp => {
                                $scope.lstKichCo.push(item);
                                Swal.fire("Thêm dữ liệu thành công", "", "success");
                                location.reload();
                                console.log("Success", resp)
                            }).catch(error => {
                                console.log("Error", error)
                            });
                        }
                    });
                }
            }else{
                return false;
            }
        });
    }

    //Màu sắc
    $scope.mauSac = {}
    $scope.lstMauSac = {}
    $scope.loadMauSac = function () {
        var url = `${host}/hienThi-mauSac`;
        $http.get(url).then(resp => {
            $scope.lstMauSac = resp.data;
            console.log("Success", resp)
        }).catch(error => {
            console.log("Error", error)
        });
    }
    $scope.addMauSac = function () {
        Swal.fire({
            title: "Bạn có muốn thêm không?",
            showDenyButton: true,
            confirmButtonText: "Có",
            denyButtonText: `Không`
        }).then((result) => {
            /* Read more about isConfirmed, isDenied below */
            if (result.isConfirmed) {
                if ($scope.mauSac.ten == null) {
                    Swal.fire("Thêm dữ liệu thất bại", "", "error");
                } else {
                    $http.get(`${host}/check-tenMauSac/` + $scope.mauSac.ten).then(response => {
                        if (response.data.isDuplicate) {
                            Swal.fire("Tên dữ liệu đã tồn tại", "", "error");
                        } else {
                            var item = angular.copy($scope.mauSac);
                            var url = `${host}/add-mauSac`;
                            $http.post(url, item).then(resp => {
                                $scope.lstMauSac.push(item);
                                Swal.fire("Thêm dữ liệu thành công", "", "success");
                                location.reload();
                                console.log("Success", resp)
                            }).catch(error => {
                                console.log("Error", error)
                            });
                        }
                    });
                }
            }else{
                return false;
            }
        });
    }

    //Thực hiện tải toàn bộ
    $scope.loadChatLieu();
    $scope.loadDanhMuc();
    $scope.loadKieuDang();
    $scope.loadKichCo();
    $scope.loadMauSac();
});
