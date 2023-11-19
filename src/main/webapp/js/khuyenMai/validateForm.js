
const formCreate = () => {
    const mucGiam = document.forms["myForm"]["mucGiam"].value.trim();
    const loaiGiamGia = document.forms["myForm"]["loaiGiamGia"].value.trim();
    if (mucGiam === "") {
        alert("mức giảm không được để trống")
        return false;
    } else if (loaiGiamGia === "") {
        alert("loại giảm giá không được để trống")
        return false;
    } else if (mucGiam <= 0) {
        alert("mức giảm không thoả mãn")
        return false;
    } else if (mucGiam) {
        if(mucGiam >= 100){
            alert("mức giảm giá phải nhỏ hơn 100%")
            return false;
        }
    } else {
        return true;
    }
}
