package com.example.web.controller.hoaDon.donDatHang;

import com.example.web.Config.status.HoaDonStatus;
import com.example.web.model.HoaDon;
import com.example.web.model.HoaDonChiTiet;
import com.example.web.model.LichSuHoaDon;
import com.example.web.model.NhanVien;
import com.example.web.model.TrangThaiHoaDon;
import com.example.web.repository.INhanVienRepository;
import com.example.web.response.HoaDonFilter;
import com.example.web.service.IHoaDonChiTietService;
import com.example.web.service.IHoaDonService;
import com.example.web.service.ILichSuHoaDonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/admin/hoa-don-onl")
public class HoaDonOnlController {

    @Autowired
    private IHoaDonService hoaDonService;

    @Autowired
    private IHoaDonChiTietService hoaDonChiTietService;

    private String url;

    @Autowired
    private INhanVienRepository nhanVienRepository;

    @Autowired
    private ILichSuHoaDonService lichSuHoaDonService;

    @GetMapping("/cho-xac-nhan/hien-thi")
    public String choXacNhan(Model model,@ModelAttribute("hoaDonFillter") HoaDonFilter filter){
        List<HoaDon> lstHd =new ArrayList<>();
        for (int i = 0; i <= hoaDonService.getAll().size()-1; i++) {
            if (hoaDonService.getAll().get(i).getLoaiHoaDon()
                    && hoaDonService.getAll().get(i).getTrangThai()==1){
                lstHd.add(hoaDonService.getAll().get(i));
            }
        }
        url ="/admin/hoa-don-onl/cho-xac-nhan/hien-thi";
        model.addAttribute("url",url);
        model.addAttribute("lst", lstHd);
        model.addAttribute("fillter", filter);
        model.addAttribute("contentPage", "choXacNhan.jsp");
        return "quanLyHoaDon/hoaDonOnline/hoa-don-onl";
    }

    @GetMapping("/cho-giao-hang/hien-thi")
    public String choGiaoHang(Model model,@ModelAttribute("hoaDonFillter") HoaDonFilter filter){
        List<HoaDon> lstHd =new ArrayList<>();
        for (int i = 0; i <= hoaDonService.getAll().size()-1; i++) {
            if (hoaDonService.getAll().get(i).getLoaiHoaDon()
                    && hoaDonService.getAll().get(i).getTrangThai()==2){
                lstHd.add(hoaDonService.getAll().get(i));
            }
        }
        url ="/admin/hoa-don-onl/cho-giao-hang/hien-thi";
        model.addAttribute("url",url);
        model.addAttribute("lst", lstHd);
        model.addAttribute("fillter", filter);
        model.addAttribute("contentPage", "choGiaoHang.jsp");
        return "quanLyHoaDon/hoaDonOnline/hoa-don-onl";
    }

    @GetMapping("/dang-giao/hien-thi")
    public String dangGiao(Model model,@ModelAttribute("hoaDonFillter") HoaDonFilter filter){
        List<HoaDon> lstHd =new ArrayList<>();
        for (int i = 0; i <= hoaDonService.getAll().size()-1; i++) {
            if (hoaDonService.getAll().get(i).getLoaiHoaDon()
                    && hoaDonService.getAll().get(i).getTrangThai()==3){
                lstHd.add(hoaDonService.getAll().get(i));
            }
        }
        url ="/admin/hoa-don-onl/dang-giao/hien-thi";
        model.addAttribute("url",url);
        model.addAttribute("lst", lstHd);
        model.addAttribute("fillter", filter);
        model.addAttribute("contentPage", "dangGiao.jsp");
        return "quanLyHoaDon/hoaDonOnline/hoa-don-onl";
    }

    @GetMapping("/da-giao/hien-thi")
    public String daGiao(Model model,@ModelAttribute("hoaDonFillter") HoaDonFilter filter){
        List<HoaDon> lstHd =new ArrayList<>();
        for (int i = 0; i <= hoaDonService.getAll().size()-1; i++) {
            if (hoaDonService.getAll().get(i).getLoaiHoaDon()
                    && hoaDonService.getAll().get(i).getTrangThai()==6){
                lstHd.add(hoaDonService.getAll().get(i));
            }
        }
        url ="/admin/hoa-don-onl/da-giao/hien-thi";
        model.addAttribute("url",url);
        model.addAttribute("lst", lstHd);
        model.addAttribute("fillter", filter);
        model.addAttribute("contentPage", "daGiao.jsp");
        return "quanLyHoaDon/hoaDonOnline/hoa-don-onl";
    }

    @GetMapping("/da-huy/hien-thi")
    public String daHuy(Model model,@ModelAttribute("hoaDonFillter") HoaDonFilter filter){
        List<HoaDon> lstHd =new ArrayList<>();
        for (int i = 0; i <= hoaDonService.getAll().size()-1; i++) {
            if (hoaDonService.getAll().get(i).getLoaiHoaDon()
                    && hoaDonService.getAll().get(i).getTrangThai()==5){
                lstHd.add(hoaDonService.getAll().get(i));
            }
        }
        url ="/admin/hoa-don-onl/da-huy/hien-thi";
        model.addAttribute("url",url);
        model.addAttribute("lst", lstHd);
        model.addAttribute("fillter", filter);
        model.addAttribute("contentPage", "daHuy.jsp");
        return "quanLyHoaDon/hoaDonOnline/hoa-don-onl";
    }
    @GetMapping("/detail/{id}")
    public String detail(Model model,
                         @RequestParam(defaultValue = "0") Integer page,
                         @PathVariable("id") String id){
        Page<HoaDonChiTiet> lst = hoaDonService.getHoaDonChiTiet(UUID.fromString(id),page,5);
        Page<HoaDonChiTiet> lst2 = hoaDonService.getHoaDonHuyChiTiet(UUID.fromString(id),page,5);
        Integer tongTien = 0;
        List<LichSuHoaDon> lshd =lichSuHoaDonService.getListById(UUID.fromString(id));
        model.addAttribute("hd",hoaDonService.getOne(id));
        if (hoaDonService.getOne(id).getTrangThai()==5){
            for (int i = 0; i <= lst2.getContent().size()-1; i++) {
                tongTien+=lst2.getContent().get(i).getSoLuong()*lst2.getContent().get(i).getDonGia().intValue();
            }
            model.addAttribute("hdc",tongTien);
            model.addAttribute("lst1",lst2.getContent());
        }else {
            for (int i = 0; i <= lst.getContent().size()-1; i++) {
                tongTien+=lst.getContent().get(i).getSoLuong()*lst.getContent().get(i).getDonGia().intValue();
            }
            model.addAttribute("hdc",tongTien);
            model.addAttribute("lst1",lst.getContent());
        }
        model.addAttribute("xacNhan",lichSuHoaDonService.getOne(id,"xác"));
        model.addAttribute("choGiao",lichSuHoaDonService.getOne(id,"ể"));
        model.addAttribute("daGiao",lichSuHoaDonService.getOne(id,"ô"));
        model.addAttribute("taoDon",lichSuHoaDonService.getOneTao(id,"Tạo đơn hàng"));
        model.addAttribute("lshd",lshd);
        return "quanLyHoaDon/chiTietHoaDonOnline/CTChoXacNhan";
    }

    @GetMapping("/update")
    public String updateHoaDonChiTiet(@RequestParam("ctsp") String idCTSP,
                                      @RequestParam("soLuong") String soLuong,
                                      @RequestParam("idHD") String idHD,
                                      @RequestParam(defaultValue = "0") Integer page) {
        url = hoaDonChiTietService.addSanPhamHoaDonChiTietKhiUpdate(idCTSP,idHD,Integer.parseInt(soLuong));
        Page<HoaDonChiTiet> lst = hoaDonService.getHoaDonChiTiet(UUID.fromString(idHD),page,5);
        Integer tongTien = 0;
        for (int i = 0; i <= lst.getContent().size()-1; i++) {
            tongTien+=lst.getContent().get(i).getSoLuong()*lst.getContent().get(i).getDonGia().intValue();
        }
        HoaDon hoaDon = hoaDonService.getOne(idHD);
        hoaDon.setId(UUID.fromString(idHD));
        hoaDon.setTongTien(BigDecimal.valueOf(tongTien));
        hoaDonService.updateHoaDonById(hoaDon);
        return url;
    }
    @GetMapping("/update-so-luong/{id}")
    public String updateSoLuongSanPhamHoaDonChiTiet2(@RequestParam("hdct") String idHdct, @RequestParam("soLuong") String soLuong,
                                                     @PathVariable("id") String id,
                                                     @RequestParam(defaultValue = "0") Integer page) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        NhanVien nhanVien = nhanVienRepository.findByEmailOrTaiKhoan(authentication.getName());
        url = hoaDonChiTietService.updateSoLuongSanPhamHoaDonChiTietKhiUpdate(idHdct,soLuong);
        Page<HoaDonChiTiet> lst = hoaDonService.getHoaDonChiTiet(UUID.fromString(id),page,5);
        Integer tongTien = 0;
        for (int i = 0; i <= lst.getContent().size()-1; i++) {
            tongTien+=lst.getContent().get(i).getSoLuong()*lst.getContent().get(i).getDonGia().intValue();
        }
        HoaDon hoaDon = hoaDonService.getOne(id);
        hoaDon.setId(UUID.fromString(id));
        hoaDon.setTongTien(BigDecimal.valueOf(tongTien+hoaDon.getPhiVanChuyen().intValue()));
        hoaDonService.updateHoaDonById(hoaDon);
        Date date = java.util.Calendar.getInstance().getTime();
        LichSuHoaDon lshd = LichSuHoaDon.builder()
                .hoaDon(hoaDon)
                .nguoiThaoTac(nhanVien.getHoTen()+" ("+nhanVien.getChucVu().getTen()+")")
                .thaoTac("Chỉnh sửa số lượng sản phẩm trong hóa đơn")
                .ngayThaoTac(date)
                .build();
        lichSuHoaDonService.add(lshd);
        return url;
    }

    @GetMapping("/delete/{id}")
    public String deleteSanPhamHoaDonDonChiTiet(@PathVariable("id") String id,
                                                @RequestParam("idHdct")String idHdct,
                                                @RequestParam(defaultValue = "0") Integer page) {
        url = hoaDonChiTietService.deleteSanPhamHoaDon2(idHdct);
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        NhanVien nhanVien = nhanVienRepository.findByEmailOrTaiKhoan(authentication.getName());
        Page<HoaDonChiTiet> lst = hoaDonService.getHoaDonChiTiet(UUID.fromString(id),page,5);
        Integer tongTien = 0;
        for (int i = 0; i <= lst.getContent().size()-1; i++) {
            tongTien+=lst.getContent().get(i).getSoLuong()*lst.getContent().get(i).getDonGia().intValue();
        }
        HoaDon hoaDon = hoaDonService.getOne(id);
        hoaDon.setId(UUID.fromString(id));
        hoaDon.setTongTien(BigDecimal.valueOf(tongTien+hoaDon.getPhiVanChuyen().intValue()));
        hoaDonService.updateHoaDonById(hoaDon);
        Date date = java.util.Calendar.getInstance().getTime();
        LichSuHoaDon lshd = LichSuHoaDon.builder()
                .hoaDon(hoaDon)
                .nguoiThaoTac(nhanVien.getHoTen()+" ("+nhanVien.getChucVu().getTen()+")")
                .thaoTac("Xóa sản phẩm khỏi hóa đơn")
                .ngayThaoTac(date)
                .build();
        lichSuHoaDonService.add(lshd);
        if (lst.getContent().isEmpty()){
            hoaDonService.updateStatusHoaDonById(hoaDon,String.valueOf(HoaDonStatus.HUY));
        }
        return url;
    }
    @GetMapping("xac-nhan/{id}")
    public String xacNhan(
                          @PathVariable("id")String id){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        NhanVien nhanVien = nhanVienRepository.findByEmailOrTaiKhoan(authentication.getName());
        Integer status;
        Date date = java.util.Calendar.getInstance().getTime();
        String thaoThac = null;
        HoaDon hoaDon=hoaDonService.getOne(id);
        if (hoaDon.getTrangThai()==1){
            status= TrangThaiHoaDon.DA_XAC_NHAN.getValue();
            thaoThac="Đơn hàng đã được xác nhận";
        }else if (hoaDon.getTrangThai()==2){
            status=TrangThaiHoaDon.DANG_VAN_CHUYEN.getValue();
            thaoThac="Đơn hàng đã được giao cho đơn vị vận chuyển";
            hoaDon.setNgayShip(date);
        }else if (hoaDon.getTrangThai()==3){
            status=TrangThaiHoaDon.GIAO_THANH_CONG.getValue();
            thaoThac="Đơn hàng đã được giao thành công";
            hoaDon.setNgayThanhToan(date);
        }else if (hoaDon.getTrangThai()==6){
            status=TrangThaiHoaDon.GIAO_THANH_CONG.getValue();
        }else {
            status=TrangThaiHoaDon.HUY_HOA_DON.getValue();
        }
        url=hoaDonService.updateStatusHoaDonById(hoaDon,String.valueOf(status));
        LichSuHoaDon lshd = LichSuHoaDon.builder()
                .hoaDon(hoaDon)
                .nguoiThaoTac(nhanVien.getHoTen()+" ("+nhanVien.getChucVu().getTen()+")")
                .thaoTac(thaoThac)
                .ngayThaoTac(date)
                .build();
        lichSuHoaDonService.add(lshd);
        return url;
    }
    @GetMapping("huy-don/{id}")
    public String huyDon(@RequestParam(defaultValue = "0") Integer page,
                          @PathVariable("id")String id){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        NhanVien nhanVien = nhanVienRepository.findByEmailOrTaiKhoan(authentication.getName());
        Page<HoaDonChiTiet> lst = hoaDonService.getHoaDonChiTiet(UUID.fromString(id),page,5);
        for (int i = 0; i <= lst.getContent().size()-1; i++) {
            hoaDonChiTietService.deleteSanPhamHoaDon2(String.valueOf(lst.getContent().get(i).getId()));
        }
        HoaDon hoaDon=hoaDonService.getOne(id);
        url=hoaDonService.updateStatusHoaDonById(hoaDon,String.valueOf(HoaDonStatus.HUY));
        Date date = java.util.Calendar.getInstance().getTime();
        LichSuHoaDon lshd = LichSuHoaDon.builder()
                .hoaDon(hoaDon)
                .nguoiThaoTac(nhanVien.getHoTen()+" ("+nhanVien.getChucVu().getTen()+")")
                .thaoTac("Hủy hóa đơn")
                .ngayThaoTac(date)
                .build();
        lichSuHoaDonService.add(lshd);
        return url;
    }
    @GetMapping("/update-thong-tin/{id}")
    public String updateThongTin(@PathVariable("id") String idHD,
                                 @RequestParam("hoTenKhach")String hoTen,
                                 @RequestParam("sdtKhach")String sdt,
                                 @RequestParam("diaChiMoi")String diaChi) {
        HoaDon hoaDon = hoaDonService.getOne(idHD);
        hoaDon.setSdt(sdt);
        hoaDon.setHoTen(hoTen);
        hoaDon.setDiaChi(diaChi);
        url=hoaDonService.updateThongTin(hoaDon);
        return url;
    }
    @GetMapping("/update-phi-ship/{id}")
    public String updatePVC(@PathVariable("id") String idHD,
                                 @RequestParam("phiVanChuyen")String Pvc) {
        HoaDon hoaDon = hoaDonService.getOne(idHD);
        hoaDon.setPhiVanChuyen(BigDecimal.valueOf(Long.parseLong(Pvc)));
        hoaDon.setTongTien(BigDecimal.valueOf(hoaDon.getTongTien().longValue()+Long.parseLong(Pvc)));
        url=hoaDonService.updatePVC(hoaDon);
        return url;
    }

}
