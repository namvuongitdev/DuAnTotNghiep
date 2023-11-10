//Api Giao Hàng Nhanh
let provincesIDValue = null;
let districtsIDValue = null;
let ship = null;
$(document).ready(function () {
    $.ajax({
        url: "https://online-gateway.ghn.vn/shiip/public-api/master-data/province",
        type: "POST",
        dataType: "json",
        headers: {
            "Content-Type": "application/json",
            Token: "847c9bb7-6e42-11ee-a59f-a260851ba65c",
        },
        success: function (response) {
            const provinces = response.data;
            const provinceSelect = $("#province");

            provinces.forEach(function (province) {
                provinceSelect.append(
                    "<option value='" +
                    province.ProvinceID +
                    "'>" +
                    province.ProvinceName +
                    "</option>"
                );
            });
        },
        error: function (xhr, status, error) {
            console.log("API Request Failed:", error);
        },
    });

    $("#province").change(function () {
        const selectedProvinceId = $(this).val();
        const province = $("#province option:selected")
        province.val(province.text())

        $("#district")
            .prop("disabled", true)
            .empty()
            .append("<option value=''>Chọn Quận/Huyện</option>");
        $("#ward")
            .prop("disabled", true)
            .empty()
            .append("<option value=''>Chọn Phường/Xã</option>");

        if (selectedProvinceId) {
            $.ajax({
                url: "https://online-gateway.ghn.vn/shiip/public-api/master-data/district",
                type: "GET",
                dataType: "json",
                headers: {
                    "Content-Type": "application/json",
                    Token: "847c9bb7-6e42-11ee-a59f-a260851ba65c",
                },
                data: {
                    province_id: selectedProvinceId,
                },
                success: function (response) {
                    const districts = response.data;
                    const districtSelect = $("#district");
                    districts.forEach(function (district) {
                        districtSelect.append(
                            "<option value='" +
                            district.DistrictID +
                            "'>" +
                            district.DistrictName +
                            "</option>"
                        );
                    });

                    districtSelect.prop("disabled", false);
                },
                error: function (xhr, status, error) {
                    console.log("API Request Failed:", error);
                },
            });
        }
    });

    $("#district").change(function () {
        const selectedDistrictId = $(this).val();
        districtsIDValue = selectedDistrictId;
        const district = $("#district option:selected")
        district.val(district.text())

        $("#ward")
            .prop("disabled", true)
            .empty()
            .append("<option value=''>Chọn Phường/Xã</option>");

        if (selectedDistrictId) {
            // Populate wards based on selected district
            $.ajax({
                url: "https://online-gateway.ghn.vn/shiip/public-api/master-data/ward",
                type: "GET",
                dataType: "json",
                headers: {
                    "Content-Type": "application/json",
                    Token: "847c9bb7-6e42-11ee-a59f-a260851ba65c",
                },
                data: {
                    district_id: selectedDistrictId,
                },
                success: function (response) {
                    const wards = response.data;
                    const wardSelect = $("#ward");
                    wards.forEach(function (ward) {
                        wardSelect.append(
                            "<option value='" +
                            ward.WardCode +
                            "'>" +
                            ward.WardName +
                            "</option>"
                        );
                    });

                    // Enable ward selection
                    wardSelect.prop("disabled", false);
                },
                error: function (xhr, status, error) {
                    console.log("API Request Failed:", error);
                },
            });
        }
    });

    $("#province, #district, #ward").change(function () {
        calculateShippingFee();
    });

    function calculateShippingFee() {
        const toDistrictId = parseInt(districtsIDValue);
        const toWardCode = $("#ward").val();
        const ward = $("#ward option:selected")
        ward.val(ward.text())

        if (toDistrictId && toWardCode) {
            $.ajax({
                url: "https://online-gateway.ghn.vn/shiip/public-api/v2/shipping-order/available-services",
                type: "POST",
                dataType: "json",
                headers: {
                    "Content-Type": "application/json",
                    Token: "847c9bb7-6e42-11ee-a59f-a260851ba65c",
                },
                data: JSON.stringify({
                    shop_id: 4642718,
                    from_district: 1454,
                    to_district: toDistrictId,
                }),
                success: function (response) {
                    const availableServices = response.data;
                    if (availableServices.length > 0) {
                        const serviceId = availableServices[0].service_id;

                        $.ajax({
                            url: "https://online-gateway.ghn.vn/shiip/public-api/v2/shipping-order/fee",
                            type: "POST",
                            dataType: "json",
                            headers: {
                                "Content-Type": "application/json",
                                Token: "847c9bb7-6e42-11ee-a59f-a260851ba65c",
                                ShopId: 4642718,
                            },
                            data: JSON.stringify({
                                from_district_id: 1454,
                                from_ward_code: "21211",
                                service_id: serviceId,
                                to_district_id: toDistrictId,
                                to_ward_code: toWardCode,
                                weight: 200,
                            }),
                            success: function (response) {
                                const shippingFee = response.data.total;
                                ship = shippingFee;
                                const tamTinh = $('#tamTinh').text().replace(/[^\d]/g, "");

                                // Format the shipping fee with commas and "VNĐ" before updating the label
                                const formattedShippingFee = shippingFee.toLocaleString("vi-VN", {
                                    style: "currency",
                                    currency: "VND",
                                });

                                // Update shipping fee in the label
                                $("#shippingFee").text(formattedShippingFee);
                                $("#tienShip").text(formattedShippingFee);
                                const tongTien = Number.parseInt(tamTinh) + Number.parseInt(shippingFee);
                                $("#tongTien").text(tongTien.toLocaleString("vi-VN", {
                                    style: "currency",
                                    currency: "VND",
                                }))

                                // calculateTotal();
                            },
                            error: function (xhr, status, error) {
                                console.log("API Request Failed:", error);
                            },
                        });
                    } else {
                        console.log("No available services.");
                    }
                },
                error: function (xhr, status, error) {
                    console.log("API Request Failed:", error);
                },
            });
        }
    }
});


async function saveOrder(){
    const hoTen = document.getElementById("hoTen").value;
    const sdt = document.getElementById("soDienThoai").value;
    const adress = document.getElementById("diaChi").value;
    const node = document.getElementById("ghiChu").value;
    const province = document.getElementById("province").value;
    const district = document.getElementById("district").value;
    const ward = document.getElementById("ward").value;
    const hinhThucThanhToan = document.getElementById("flexRadioDefault2").value;

    const data = {
        hoTen : hoTen,
        soDienThoai : sdt,
        ghiChu:node,
        diaChi : adress,
        thanhPho : province,
        quanHuyen : district,
        phuongXa : ward,
        phiGiaoHang:ship,
        phuongThucThanhToan :hinhThucThanhToan
    }
    console.log(data);

    // const options = {
    //     method: 'POST',
    //     headers: {'Content-Type': 'application/json'},
    //     body: JSON.stringify(data)
    // };
    // const api  = await fetch("/checkouts/order" , options);
    // const response = await api.json();
    // console.log(response);

}