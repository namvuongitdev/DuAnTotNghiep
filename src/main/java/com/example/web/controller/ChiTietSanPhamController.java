package com.example.web.controller;

import com.example.web.model.ChiTietSanPham;
import com.example.web.model.MauSac;
import com.example.web.model.SanPham;
import com.example.web.model.Size;
import com.example.web.request.UpdateChiTietSanPham;
import com.example.web.response.ChiTietSanPhamResponse;
import com.example.web.service.IAnhService;
import com.example.web.service.IChiTietSanPhamService;
import com.example.web.service.IMauSacService;
import com.example.web.service.ISanPhamService;
import com.example.web.service.SizeService;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.UUID;

@Controller
@RequestMapping(value = "/admin/chi-tiet-san-pham")
public class ChiTietSanPhamController {

    @Value("${qrcode.directory}")
    private String qrcodeDirectory;

    @Autowired
    private IChiTietSanPhamService chiTietSanPhamService;

    @Autowired
    private ISanPhamService sanPhamService;

    @Autowired
    private IMauSacService mauSacService;

    @Autowired
    private IAnhService anhService;

    @Autowired
    private SizeService sizeService;


    @PostMapping(value = "/add")
    public String addMultipleCTSP(@ModelAttribute("chiTietSanPham") ChiTietSanPham chiTietSanPham, Model model,
                                  @RequestParam("id") String idSanPham, RedirectAttributes redirectAttributes,
                                  HttpServletRequest request) throws IOException, WriterException {

        String[] kichCoList = request.getParameterValues("size");
        String[] mauSacList = request.getParameterValues("mauSac");

        Random random = new Random();
        SanPham sanPham = sanPhamService.getOne(UUID.fromString(idSanPham));
        if(kichCoList == null || mauSacList == null) {
            redirectAttributes.addFlashAttribute("error", "Thêm dữ liệu thất bại");
        }else{
            for (String kichCo : kichCoList) {
                for (String mauSac : mauSacList) {
                    ChiTietSanPham checkMauSacSize = chiTietSanPhamService.checkSizeMauSac(UUID.fromString(mauSac), kichCo, UUID.fromString(idSanPham));
                    if (checkMauSacSize != null) {
                        redirectAttributes.addFlashAttribute("error", "Dữ liệu chi tiết sản phẩm "+checkMauSacSize.getSize().getTen()+", "+checkMauSacSize.getMauSac().getTen() +" đã tồn tại");
                        return "redirect:/admin/san-pham/hien-thi/" + idSanPham;
                    }
                    ChiTietSanPham newChiTietSanPham = new ChiTietSanPham();
                    newChiTietSanPham.setSanPham(sanPham);
                    newChiTietSanPham.setQrCode(String.valueOf(random.nextInt(1000000000)));
                    newChiTietSanPham.setTrangThai(1);
                    Size size = new Size();
                    size.setId(kichCo);
                    newChiTietSanPham.setSize(size);
                    MauSac mauSac1 = new MauSac();
                    mauSac1.setId(UUID.fromString(mauSac));
                    newChiTietSanPham.setMauSac(mauSac1);
                    if(chiTietSanPham.getSoLuong() == null) {
                        redirectAttributes.addFlashAttribute("error", "Thêm dữ liệu thất bại");
                        return "redirect:/admin/san-pham/hien-thi/" + idSanPham;
                    }else if(chiTietSanPham.getSoLuong() < 1){
                        redirectAttributes.addFlashAttribute("error", "Số lượng phải lớn hơn 0");
                        return "redirect:/admin/san-pham/hien-thi/" + idSanPham;
                    }else{
                        newChiTietSanPham.setSoLuong(chiTietSanPham.getSoLuong());
                    }
                    chiTietSanPhamService.save(newChiTietSanPham);

                    // Tạo QR code và lưu vào thư mục
                    int width = 300;
                    int height = 300;
                    String format = "png";
                    String fileName = newChiTietSanPham.getSanPham().getMa() + "-" + newChiTietSanPham.getMauSac().getTen() + "-" + newChiTietSanPham.getSize().getTen() + "-" + newChiTietSanPham.getQrCode() + "." + format;
                    Map<EncodeHintType, Object> hints = new HashMap<>();
                    hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");
                    BitMatrix bitMatrix = new MultiFormatWriter().encode(newChiTietSanPham.getQrCode(), BarcodeFormat.QR_CODE, width, height, hints);
                    Path filePath = FileSystems.getDefault().getPath(qrcodeDirectory, fileName);
                    MatrixToImageWriter.writeToPath(bitMatrix, format, filePath);

                }
            }
            redirectAttributes.addFlashAttribute("success", "Thêm dữ liệu thành công");
        }
        return "redirect:/admin/san-pham/hien-thi/" + idSanPham;
    }

    @PostMapping(value = "/add-anh", consumes = "multipart/form-data")
    @ResponseBody
    public void addAnhChiTietSanPham(@RequestParam MultipartFile file, @RequestParam String idSP, @RequestParam String idMS) throws IOException {
        SanPham sanPham = sanPhamService.getOne(UUID.fromString(idSP));
        MauSac mauSac = mauSacService.getOne(idMS);
        anhService.addAnhMauSac(file, sanPham, mauSac);
    }

    @PostMapping(value = "/update-chi-tiet-san-pham")
    public String updateCTSP(@RequestParam(value = "chiTietSanPham" ,defaultValue = "") List<UUID> chiTietSanPham,
                             @RequestParam(value = "soLuong" , defaultValue = "") List<Integer> soLuong,
                             @RequestParam String idSP , RedirectAttributes attributes) {
        Integer update = chiTietSanPhamService.updateChiTietSanPham(chiTietSanPham , soLuong ,UUID.fromString(idSP));
        if(update == 1){
            attributes.addFlashAttribute("error" , "chưa lựa chọn chi tiết sản phẩm");
        }
        if(update == 2){
            attributes.addFlashAttribute("success" , "update chi tiết sản phẩm thành công");
        }
        return "redirect:/admin/san-pham/hien-thi/" + idSP;
    }

    @PutMapping(value = "/update-size-mau-sac")
    @ResponseBody
    public Map<String , Boolean> updateSizeAndMauSac(@RequestBody UpdateChiTietSanPham request){
        return chiTietSanPhamService.updateMauSacAndKichCo(request ,
                mauSacService.getOne(request.getIdMS()),
                        sizeService.getOne(request.getIdKC())
                );
    }

    @GetMapping(value = "/remove-anh")
    @ResponseBody
    public void removeAnhById(@RequestParam String idAnh) {
        anhService.reomveAnhById(idAnh);
    }

    @GetMapping(value = "/{id}")
    @ResponseBody
    public List<ChiTietSanPhamResponse> getChiTietSanPham(@PathVariable("id") String id) {
        List<ChiTietSanPhamResponse> chiTietSanPhams = chiTietSanPhamService.getCTSP(id);
        return chiTietSanPhams;
    }

    @GetMapping(value = "/so-luong")
    @ResponseBody
    public ChiTietSanPhamResponse getByMuaSacAnhKichCoAndSanPham(@RequestParam(value = "mauSac", required = false) String idMS, @RequestParam(value = "kichCo", required = false) String idKC, @RequestParam(value = "sanPham", required = false) String idSP) {
        ChiTietSanPhamResponse ctspResponse = chiTietSanPhamService.getByMauSacAndKichCoAndSanPham(idMS, idKC, idSP);
        return ctspResponse;
    }

    @GetMapping(value = "/api-hien-thi-qrcode/{id}")
    @ResponseBody
    public Object[] getQrCode(@PathVariable String id) {
        Object[] qrcode = chiTietSanPhamService.getQrCodeById(UUID.fromString(id));
        return qrcode;
    }
}
