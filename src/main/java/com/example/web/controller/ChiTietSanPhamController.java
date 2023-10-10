package com.example.web.controller;
import com.example.web.model.Anh;
import com.example.web.model.ChiTietSanPham;
import com.example.web.model.SanPham;
import com.example.web.response.ChiTietSanPhamResponse;
import com.example.web.service.IAnhService;
import com.example.web.service.IChiTietSanPhamService;
import com.example.web.service.ISanPhamService;
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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.UUID;

@Controller
@RequestMapping(value = "/chi-tiet-san-pham")
public class ChiTietSanPhamController {

    @Value("${qrcode.directory}")
    private String qrcodeDirectory;

    @Autowired
    private IChiTietSanPhamService chiTietSanPhamService;

    @Autowired
    private ISanPhamService sanPhamService;

    @Autowired
    private IAnhService anhService;

    @PostMapping(value = "/add")
    public String addCTSP(@ModelAttribute("chiTietSanPham") ChiTietSanPham chiTietSanPham
            , @RequestParam("id") String idSanPham, RedirectAttributes redirectAttributes) throws IOException, WriterException {

        Random random = new Random();
        SanPham sanPham = sanPhamService.getOne(UUID.fromString(idSanPham));
        chiTietSanPham.setSanPham(sanPham);
        chiTietSanPham.setQrCode(String.valueOf(random.nextInt(1000000000)));
        List<ChiTietSanPham> listChiTietSanPham = chiTietSanPhamService.getChiTietSanPham(idSanPham);
        chiTietSanPhamService.save(chiTietSanPham);
        redirectAttributes.addFlashAttribute("listChiTietSanPhamBySP", listChiTietSanPham);

        int width = 300;
        int height = 300;
        String format = "png";
        String fileName = chiTietSanPham.getSanPham().getMa() + "-" + chiTietSanPham.getQrCode() + "." + format;

        Map<EncodeHintType, Object> hints = new HashMap<>();
        hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");

        BitMatrix bitMatrix = new MultiFormatWriter().encode(chiTietSanPham.getQrCode(), BarcodeFormat.QR_CODE, width, height, hints);
        Path filePath = FileSystems.getDefault().getPath(qrcodeDirectory, fileName);

        MatrixToImageWriter.writeToPath(bitMatrix, format, filePath);

        return "redirect:/san-pham/hien-thi/" + idSanPham;
    }

    @PostMapping(value = "/add-anh")
    public String addAnhChiTietSanPham(@RequestParam("file") MultipartFile file, HttpServletRequest request, @RequestParam String idCTSP) throws IOException {
        ChiTietSanPham chiTietSanPham = chiTietSanPhamService.getOne(UUID.fromString(idCTSP));
        anhService.addAnhCtsp(file, request, chiTietSanPham);
        return "redirect:/chi-tiet-san-pham/anh/" + chiTietSanPham.getId() + "?idSP=" + chiTietSanPham.getSanPham().getId();
    }

    @PostMapping(value = "/update-chi-tiet-san-pham")
    public String updateCTSP(@ModelAttribute("chiTietSanPham") ChiTietSanPham chiTietSanPham, @RequestParam String idCTSP, @RequestParam String idSP) {
        chiTietSanPham.setId(UUID.fromString(idCTSP));
        chiTietSanPham.setSanPham(sanPhamService.getOne(UUID.fromString(idSP)));
        chiTietSanPhamService.save(chiTietSanPham);
        return "redirect:/san-pham/hien-thi/" + chiTietSanPham.getSanPham().getId();
    }

    @GetMapping(value = "/anh/{id}")
    public String getAnhByChiTietSanPham_id(RedirectAttributes redirectAttributes, @PathVariable("id") String idCTSP, @RequestParam String idSP) {
        List<Anh> anhs = anhService.getAnh(idCTSP);
        redirectAttributes.addFlashAttribute("listAnhChiTietSanPham_id", anhs);
        return "redirect:/san-pham/hien-thi/" + idSP;
    }

    @GetMapping(value = "/remove-anh")
    public String removeAnhById(@RequestParam String idAnh, @RequestParam String idCTSP, @RequestParam String idSP) {
        anhService.reomveAnhById(idAnh);
        return "redirect:/chi-tiet-san-pham/anh/" + idCTSP + "?idSP=" + idSP;
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

}
