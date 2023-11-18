package com.example.web.service.impl;

import com.example.web.model.*;
import com.example.web.repository.IGioHangOnllineRepository;
import com.example.web.repository.IGioHangRepository;
import com.example.web.repository.IKhachHangRepository;
import com.example.web.response.GioHangReponse;
import com.example.web.service.IGioHangCTService;
import com.example.web.service.IGioHangOnllineService;
import com.example.web.service.IKhachHangService;
import com.example.web.service.ISanPhamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class GioHangOnllineServiceImpl implements IGioHangOnllineService {
    @Autowired
    IGioHangOnllineRepository iGioHangOnllineRepository;

    @Autowired
    IKhachHangService iKhachHangService;

    @Autowired
    ISanPhamService iSanPhamService;

    @Autowired
    IKhachHangService khachHangService;

    @Autowired
    IKhachHangRepository khachHangRepository;

    @Autowired
    IGioHangCTService iGioHangCTService;

    @Autowired
    IGioHangRepository iGioHangRepository;


    @Override
    public List<GioHangReponse> findAll(UUID idKhachHang) {
        List<GioHangReponse> listGioHangChiTiet = iGioHangOnllineRepository.findAll(idKhachHang);
        return listGioHangChiTiet;
    }


    @Override
    public void updateSoLuong(Integer soLuong, String idGioHangCT) {
        iGioHangOnllineRepository.updateSoLuong(soLuong,idGioHangCT);
    }

    @Override
    public void delete(String idGioHangCT) {
        iGioHangOnllineRepository.delete(idGioHangCT);
    }


    @Override
    public void addGioHang(ChiTietSanPham chiTietSanPham, Integer soLuongThem) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        KhachHang khachHang = iKhachHangService.findByEmailOrAndTaiKhoan(authentication.getName());
        GioHang gioHang = iGioHangRepository.getTheoIdKH(khachHang.getId()); // tìm giỏ hàng của khách hàng
        List<GioHangChiTiet> listGHCT = iGioHangCTService.getListGHCTTheoKhachHang(khachHang.getId()); // lấy danh sách sản phẩm trong giỏ của kh
        // nếu chưa có thì tạo mới 1 giỏ hàng cho kh
        if (gioHang == null) {
            // tạo mới giỏ hàng
            GioHang newGioHang = new GioHang();
            newGioHang.setKhachHang(khachHang);
            GioHang newGioHang1 = iGioHangOnllineRepository.save(newGioHang);
            // lấy ra giỏ hàng vừa tạo
            GioHang gioHangVuaTao = iGioHangRepository.getTheoIdKH(newGioHang.getKhachHang().getId());
            //Lấy Sản phẩm theo chi tiết spham
            SanPham sanPham = iSanPhamService.getSanPhamTheoCTSP(chiTietSanPham.getId());
            //thêm spham vào giỏ
            GioHangChiTiet gioHangChiTiet = new GioHangChiTiet();
            gioHangChiTiet.setGioHang(gioHangVuaTao);
            gioHangChiTiet.setChiTietSanPham(chiTietSanPham);
            gioHangChiTiet.setSoLuong(soLuongThem);
            GioHangChiTiet gioHangChiTiet1 = iGioHangCTService.save(gioHangChiTiet);
        }
        // nếu có rồi thì thêm sp vào giỏ hàng ct
        else if (gioHang != null) {
            int dem = 0;
            for (int i = 0; i < listGHCT.size(); i++) {
                // nếu sản phẩm đã có trong giỏ hàng chi tiết
                if (listGHCT.get(i).getChiTietSanPham().getId().equals(chiTietSanPham.getId())) {
                    Integer soLuongHienTai = listGHCT.get(i).getSoLuong();
                    Integer soLuongMoi = soLuongHienTai + soLuongThem;
                    GioHangChiTiet hangChiTiet = iGioHangCTService.getTheoIdGioHangAndIdCTSP(gioHang.getId(), listGHCT.get(i).getChiTietSanPham().getId());
                    //cập nhật lại số lượng
                    hangChiTiet.setSoLuong(soLuongMoi);
                    GioHangChiTiet gioHangChiTiet = iGioHangCTService.save(hangChiTiet);
                    dem = 1;

                }
            }
            if (dem == 0) {// nếu chưa có thì thêm mới vào giỏ hàng chi tiết
                //Lấy Sản phẩm theo chi tiết spham
                SanPham sanPham = iSanPhamService.getSanPhamTheoCTSP(chiTietSanPham.getId());
                GioHangChiTiet gioHangChiTiet = new GioHangChiTiet();
                gioHangChiTiet.setSoLuong(soLuongThem);
                gioHangChiTiet.setChiTietSanPham(chiTietSanPham);
                gioHangChiTiet.setGioHang(gioHang);
                GioHangChiTiet chiTiet = iGioHangCTService.save(gioHangChiTiet);
            }
        }

    }

    @Override
    public Integer countSoLuongSPTrongGioHang(UUID id) {
        return iGioHangOnllineRepository.countSanPhamTrongGioHangByKhachHang_id(id);
    }

    @Override
    public void clearAllGioHangChiTietByKhachHang_id(UUID idKH) {
        iGioHangOnllineRepository.deleteAllGioHangChiTiet(idKH);
    }

    @Override
    public List<GioHangChiTiet> getGHCTByKhachHang_id(UUID idKh) {
        return iGioHangOnllineRepository.findGioHangChiTietByKhachHang_id(idKh);
    }

    @Override
    public BigDecimal tongTienSanPhamTrongGioHang(UUID idKH) {
        Double tongTien = 0.0;
        List<GioHangReponse> response = iGioHangOnllineRepository.findAll(idKH);
        for (GioHangReponse list : response) {
            tongTien += list.getThanhTien().doubleValue();
        }
        return BigDecimal.valueOf(tongTien);
    }
}
