const isChecked = document.getElementById("checkeds");
const checkboxes = document.querySelectorAll('.checkbox');
isChecked.addEventListener("change", function () {
    if (isChecked.checked) {
        checked(checkboxes);
    } else {
        unChecked(checkboxes);
    }
})

function checked(data) {
    data.forEach(function (checkbox) {
        let idctsp = checkbox.id.replace('checkbox', '');
        let pSoLuong = document.getElementById('pSoLuong' + idctsp);
        let soLuongCTSP = document.getElementById('soLuongCTSP' + idctsp);
        checkbox.setAttribute("checked", "checked");
        soLuongCTSP.style.display = 'block';
        soLuongCTSP.setAttribute('name', 'soLuong');
        pSoLuong.style.display = 'none';
    })
}

function unChecked(unChecked) {
    unChecked.forEach(function (checkbox) {
        let idctsp = checkbox.id.replace('checkbox', '');
        let pSoLuong = document.getElementById('pSoLuong' + idctsp);
        let soLuongCTSP = document.getElementById('soLuongCTSP' + idctsp);
        checkbox.removeAttribute("checked");
        soLuongCTSP.style.display = 'none';
        soLuongCTSP.setAttribute('name', 'sl');
        pSoLuong.style.display = 'block';
    })
}

document.addEventListener('DOMContentLoaded', function () {
    let checkboxes = document.querySelectorAll('.checkbox');
    checkboxes.forEach(function (checkbox) {
        checkbox.addEventListener('change', function () {
            let ctspId = this.id.replace('checkbox', '');
            let pSoLuong = document.getElementById('pSoLuong' + ctspId);
            let soLuongCTSP = document.getElementById('soLuongCTSP' + ctspId);
            if (this.checked) {
                soLuongCTSP.style.display = 'block';
                soLuongCTSP.setAttribute('name', 'soLuong');
                pSoLuong.style.display = 'none';
            } else {
                soLuongCTSP.style.display = 'none';
                soLuongCTSP.setAttribute('name', 'sl');
                pSoLuong.style.display = 'block';
            }
        });
    });
});
