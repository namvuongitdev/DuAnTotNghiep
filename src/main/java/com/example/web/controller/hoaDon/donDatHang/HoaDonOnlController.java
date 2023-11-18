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
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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

    @Autowired
    private HttpServletRequest request;

    @GetMapping("/cho-xac-nhan/hien-thi")
    public String choXacNhan(Model model,
                             @ModelAttribute("hoaDonFillter") HoaDonFilter filter,
                             @RequestParam(value = "page",defaultValue = "0")Integer page){
        url ="/admin/hoa-don-onl/cho-xac-nhan/hien-thi";
        model.addAttribute("url",url);
        model.addAttribute("lst", hoaDonService.phanTrangOnl("1",page,10).getContent());
        model.addAttribute("currentPage",page);
        model.addAttribute("totalPage",hoaDonService.phanTrangOnl("1",page,10).getTotalPages());
        model.addAttribute("fillter", filter);
        model.addAttribute("contentPage", "choXacNhan.jsp");
        return "quanLyHoaDon/hoaDonOnline/hoa-don-onl";
    }

    @GetMapping("/cho-giao-hang/hien-thi")
    public String choGiaoHang(Model model,
                              @ModelAttribute("hoaDonFillter") HoaDonFilter filter,
                              @RequestParam(value = "page",defaultValue = "0")Integer page){
        model.addAttribute("currentPage",page);
        model.addAttribute("totalPage",hoaDonService.phanTrangOnl("2",page,10).getTotalPages());
        url ="/admin/hoa-don-onl/cho-giao-hang/hien-thi";
        model.addAttribute("url",url);
        model.addAttribute("lst", hoaDonService.phanTrangOnl("2",page,10).getContent());
        model.addAttribute("fillter", filter);
        model.addAttribute("contentPage", "choGiaoHang.jsp");
        return "quanLyHoaDon/hoaDonOnline/hoa-don-onl";
    }

    @GetMapping("/dang-giao/hien-thi")
    public String dangGiao(Model model,
                           @ModelAttribute("hoaDonFillter") HoaDonFilter filter,
                           @RequestParam(value = "page",defaultValue = "0")Integer page){
        model.addAttribute("currentPage",page);
        model.addAttribute("totalPage",hoaDonService.phanTrangOnl("3",page,10).getTotalPages());
        url ="/admin/hoa-don-onl/dang-giao/hien-thi";
        model.addAttribute("url",url);
        model.addAttribute("lst", hoaDonService.phanTrangOnl("3",page,10).getContent());
        model.addAttribute("fillter", filter);
        model.addAttribute("contentPage", "dangGiao.jsp");
        return "quanLyHoaDon/hoaDonOnline/hoa-don-onl";
    }

    @GetMapping("/da-giao/hien-thi")
    public String daGiao(Model model,
                         @ModelAttribute("hoaDonFillter") HoaDonFilter filter,
                         @RequestParam(value = "page",defaultValue = "0")Integer page){
        model.addAttribute("currentPage",page);
        model.addAttribute("totalPage",hoaDonService.phanTrangOnl("6",page,10).getTotalPages());
        url ="/admin/hoa-don-onl/da-giao/hien-thi";
        model.addAttribute("url",url);
        model.addAttribute("lst", hoaDonService.phanTrangOnl("6",page,10).getContent());
        model.addAttribute("fillter", filter);
        model.addAttribute("contentPage", "daGiao.jsp");
        return "quanLyHoaDon/hoaDonOnline/hoa-don-onl";
    }

    @GetMapping("/da-huy/hien-thi")
    public String daHuy(Model model,
                        @ModelAttribute("hoaDonFillter") HoaDonFilter filter,
                        @RequestParam(value = "page",defaultValue = "0")Integer page){
        model.addAttribute("currentPage",page);
        model.addAttribute("totalPage",hoaDonService.phanTrangOnl("5",page,10).getTotalPages());
        url ="/admin/hoa-don-onl/da-huy/hien-thi";
        model.addAttribute("url",url);
        model.addAttribute("lst", hoaDonService.phanTrangOnl("5",page,10).getContent());
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
        Integer tongTien = getTongTien(lst);
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
        Integer tongTien = getTongTien(lst);
        HoaDon hoaDon = hoaDonService.getOne(id);
        hoaDon.setId(UUID.fromString(id));
        if (hoaDon.getPhiVanChuyen()==null){
            hoaDon.setTongTien(BigDecimal.valueOf(tongTien));
        }else {
            hoaDon.setTongTien(BigDecimal.valueOf(tongTien+hoaDon.getPhiVanChuyen().intValue()));
        }
        hoaDonService.updateHoaDonById(hoaDon);
        HoaDonChiTiet hoaDonChiTiet = hoaDonChiTietService.getOne(idHdct);
        lichSuHoaDonService.add(nhanVien.getHoTen(),
                "Chỉnh sửa số lượng sản phẩm "+ hoaDonChiTiet.getChiTietSanPham().getSanPham().getTen()+"["+hoaDonChiTiet.getChiTietSanPham().getSize().getTen()+"-"+hoaDonChiTiet.getChiTietSanPham().getSanPham().getChatLieu().getTen()+"-"+hoaDonChiTiet.getChiTietSanPham().getMauSac().getTen()+"]",
                hoaDon,
                nhanVien.getChucVu().getTen());
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
        Integer tongTien = getTongTien(lst);
        HoaDon hoaDon = hoaDonService.getOne(id);
        hoaDon.setId(UUID.fromString(id));
        if (hoaDon.getPhiVanChuyen()==null){
            hoaDon.setTongTien(BigDecimal.valueOf(tongTien));
        }else {
            hoaDon.setTongTien(BigDecimal.valueOf(tongTien+hoaDon.getPhiVanChuyen().intValue()));
        }
        hoaDonService.updateHoaDonById(hoaDon);
        HoaDonChiTiet hoaDonChiTiet = hoaDonChiTietService.getOne(idHdct);
        lichSuHoaDonService.add(nhanVien.getHoTen(),
                "Xóa sản phẩm " + hoaDonChiTiet.getChiTietSanPham().getSanPham().getTen()+"["+hoaDonChiTiet.getChiTietSanPham().getSize().getTen()+"-"+hoaDonChiTiet.getChiTietSanPham().getSanPham().getChatLieu().getTen()+"-"+hoaDonChiTiet.getChiTietSanPham().getMauSac().getTen()+"]",hoaDon,nhanVien.getChucVu().getTen());
        if (lst.getContent().isEmpty()){
            hoaDonService.updateStatusHoaDonById(hoaDon,String.valueOf(HoaDonStatus.HUY));
        }
        return url;
    }

    private Integer getTongTien(Page<HoaDonChiTiet> lst) {
        Integer tongTien = 0;
        for (int i = 0; i <= lst.getContent().size() - 1; i++) {
            tongTien += lst.getContent().get(i).getSoLuong() * lst.getContent().get(i).getDonGia().intValue();
        }
        return tongTien;
    }

    @GetMapping("xac-nhan/{id}")
    public String xacNhan(
                          @PathVariable("id")String id){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        NhanVien nhanVien = nhanVienRepository.findByEmailOrTaiKhoan(authentication.getName());
        Integer status;
        Date date = java.util.Calendar.getInstance().getTime();
        String thaoTac = null;
        HoaDon hoaDon=hoaDonService.getOne(id);
        if (hoaDon.getTrangThai()==1){
            status= TrangThaiHoaDon.DA_XAC_NHAN.getValue();
            thaoTac="Đơn hàng đã được xác nhận";
        }else if (hoaDon.getTrangThai()==2){
            status=TrangThaiHoaDon.DANG_VAN_CHUYEN.getValue();
            thaoTac="Đơn hàng đã được giao cho đơn vị vận chuyển";
            hoaDon.setNgayShip(date);
        }else if (hoaDon.getTrangThai()==3){
            status=TrangThaiHoaDon.GIAO_THANH_CONG.getValue();
            thaoTac="Đơn hàng đã được giao thành công";
            hoaDon.setNgayThanhToan(date);
        }else if (hoaDon.getTrangThai()==6){
            status=TrangThaiHoaDon.GIAO_THANH_CONG.getValue();
        }else {
            status=TrangThaiHoaDon.HUY_HOA_DON.getValue();
        }
        url=hoaDonService.updateStatusHoaDonById(hoaDon,String.valueOf(status));
        lichSuHoaDonService.add(nhanVien.getHoTen(),thaoTac,hoaDon,nhanVien.getChucVu().getTen());
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
        hoaDon.setTongTien(BigDecimal.valueOf(0));
        url=hoaDonService.updateStatusHoaDonById(hoaDon,String.valueOf(HoaDonStatus.HUY));
        lichSuHoaDonService.add(nhanVien.getHoTen(),"Hủy hóa đơn",hoaDon,nhanVien.getChucVu().getTen());
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
        Page<HoaDonChiTiet> lst = hoaDonService.getHoaDonChiTiet(UUID.fromString(idHD),0,5);
        Integer tongTien = getTongTien(lst);
        hoaDon.setPhiVanChuyen(BigDecimal.valueOf(Long.parseLong(Pvc)));
        hoaDon.setTongTien(BigDecimal.valueOf(tongTien.longValue()+Long.parseLong(Pvc)));
        url=hoaDonService.updatePVC(hoaDon);
        return url;
    }
    @GetMapping("/filter/{number}")
    public String fillter(Model model,
                          @RequestParam(defaultValue = "0") Integer page,
                          @ModelAttribute("hoaDonFillter") HoaDonFilter filter,
                          @PathVariable("number")String number) {
        Pageable pageable = PageRequest.of(page, 10, Sort.by("ngayTao").descending());
        String url = "/admin/hoa-don-onl/filter?" + request.getQueryString().replaceAll("[&?]page.*?(?=&|\\?|$)", "") + "&page=";
        model.addAttribute("lst", hoaDonService.hoaDonFillter2(filter, pageable).getContent());
        model.addAttribute("fillter", filter);
        model.addAttribute("url", url);
        String uri="/admin/hoa-don-onl/filter/"+number;
        if (Integer.parseInt(number)==6){
            model.addAttribute("contentPage", "daGiao.jsp");
        }else if (Integer.parseInt(number)==1){
            model.addAttribute("contentPage", "choXacNhan.jsp");
        }else if (Integer.parseInt(number)==2){
            model.addAttribute("contentPage", "choGiaoHang.jsp");
        }else if (Integer.parseInt(number)==3){
            model.addAttribute("contentPage", "dangGiao.jsp");
        }else if (Integer.parseInt(number)==5){
            model.addAttribute("contentPage", "daHuy.jsp");
        }else {
            model.addAttribute("contentPage", "hoa-don-tai-quay.jsp");
        }
        model.addAttribute("uri", uri);
        return "quanLyHoaDon/hoaDonOnline/hoa-don-onl";
    }
    @GetMapping("/hien-thi/{number}/{page}")
    public String phanTrang(Model model,
                            @PathVariable("page") Integer page,
                            @PathVariable("number") String number) {
        model.addAttribute("hoaDonFillter", new HoaDonFilter());
        model.addAttribute("lst", hoaDonService.phanTrangOnl(number,page,5).getContent());
        model.addAttribute("currentPage", page);
        String uri = "/admin/hoa-don/hien-thi/"+number;
        model.addAttribute("uri",uri);
        model.addAttribute("totalPage", hoaDonService.phanTrangOnl(number,page,5).getTotalPages());
        if (Integer.parseInt(number)==6){
            model.addAttribute("contentPage", "daGiao.jsp");
        }else if (Integer.parseInt(number)==1){
            model.addAttribute("contentPage", "choXacNhan.jsp");
        }else if (Integer.parseInt(number)==2){
            model.addAttribute("contentPage", "choGiaoHang.jsp");
        }else if (Integer.parseInt(number)==3){
            model.addAttribute("contentPage", "dangGiao.jsp");
        }else if (Integer.parseInt(number)==5){
            model.addAttribute("contentPage", "daHuy.jsp");
        }else {
            model.addAttribute("contentPage", "hoa-don-tai-quay.jsp");
        }        return "quanLyHoaDon/hoaDonOnline/hoa-don-onl";
    }
}
