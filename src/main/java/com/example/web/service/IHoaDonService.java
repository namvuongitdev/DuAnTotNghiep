package com.example.web.service;

import com.example.web.request.HoaDonRequest;
import com.example.web.model.HoaDon;
import com.example.web.model.HoaDonChiTiet;
import com.example.web.request.ThongTinKhachHang;
import com.example.web.response.HoaDonChiTietReponse;
import com.example.web.response.HoaDonFilter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.ui.Model;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

public interface IHoaDonService {

    String addHoaDon();

    List<Object[]> findByHoaDonCho(Integer trangThaiHD);

    String getHoaDonById(Model model, String id, RedirectAttributes attributes);

    String updateHoaDonTrangThai(String id);

    String thanhToan(HoaDonRequest request, RedirectAttributes attributes);

    List<HoaDon> getAll();

    HoaDon getOne(String id);

    HoaDon updateThoiGianTraHang(String idHD);

    List<HoaDonChiTietReponse> getHoaDonChiTiets(UUID id);

    Boolean kiemTraConTrongHDCT(UUID idHD);

    HoaDonChiTietReponse getHoaDonChiTiet(UUID uuid);

    Page<HoaDon> getAllHoaDonByTrangThaiKhachHoaDonCho(Integer page);

    Page<HoaDon> hoaDonFillter(HoaDonFilter filter, Pageable pageable);

    HoaDon add(HoaDon hoaDon);


    HoaDon updateThongTinKhachHang(UUID idHD, ThongTinKhachHang thongTinKhachHang);

    void inHoaDon(String id, List<HoaDonChiTiet> hoaDonChiTiets);

    Page<Object[]> findHoaDonByTaiKhoan(String taiKhoan, Pageable pageable);

    Page<Object[]> findHoaDonByTrangThai(String taiKhoan, Integer trangThai, Pageable pageable);

    HoaDon getHoaDonByKhachHang_idAndHoaDon_id(UUID idKH, UUID idHD);

    Integer tongHoaDonHuy();

    Integer tongHoaDonChoXacNhan();

    Integer xacNhanHoaDon(Integer trangThai, UUID idHD, String ghiChu);

    Integer xacNhanDonHangCuaToi(Integer trangThai, UUID idHD, String ghiChu);

    Integer tongDoanhThu();

    Page<Object[]> getAllHoaDonHoanTien(Integer page);

    Double getDoanhThuTrongNgay();

    Integer tongHoaDon();

    Double getDoanhThuTheoThang(Integer thang ,Integer nam);

    List<Integer> getNams();

}
