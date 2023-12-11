package com.example.web.service.impl;

import com.example.web.Config.status.HoaDonStatus;
import com.example.web.model.HoaDon;
import com.example.web.model.HoaDonChiTiet;
import com.example.web.repository.IHoaDonRepository;
import com.example.web.service.IHoaDonService;
import com.example.web.service.InHoaDonService;
import com.itextpdf.html2pdf.ConverterProperties;
import com.itextpdf.html2pdf.HtmlConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Optional;
import java.util.UUID;

@Service
public class InHoaDonServiceImpl implements InHoaDonService {
    @Autowired
    private IHoaDonRepository hoaDonRepository;

    @Autowired
    private IHoaDonService hoaDonService;

    @Override
    public ResponseEntity<byte[]> generatePdf(UUID hoaDonId) {
        Optional<HoaDon> optHoaDon = hoaDonRepository.findById(hoaDonId);
        if (optHoaDon.isPresent()) {
            HoaDon hoaDon = optHoaDon.get();
            // Tạo nội dung HTML cho hóa đơn (thay đổi cho phù hợp với mẫu HTML của bạn)
            StringBuilder htmlContentBuilder = new StringBuilder();
            htmlContentBuilder.append("<html><head>");
            htmlContentBuilder.append("<meta charset=\"UTF-8\">");
            htmlContentBuilder.append("<title>Hóa đơn</title>");
            htmlContentBuilder.append("<style>");
            htmlContentBuilder.append("body {\n" +
                    "    font-family: Arial, sans-serif;\n" +
                    "    line-height: 1.6;\n" +
                    "    background-color: #f9f9f9;\n" +
                    "    padding: 20px;\n" +
                    "}\n" +
                    "\n" +
                    "h1 {\n" +
                    "    color: #338dbc;" +
                    "    text-align: center;\n" +
                    "    font-size: 24px;\n" +
                    "    margin-bottom: 10px;\n" +
                    "}\n" +
                    "\n" +
                    "p {\n" +
                    "    margin-bottom: 10px;\n" +
                    "    color: #333;" +
                    "}\n" +
                    "\n" +
                    "h3 {\n" +
                    "    margin-bottom: 10px;\n" +
                    "    color: #333;\n" +
                    "    text-align: center;" +
                    "}\n" +
                    "\n" +
                    "table {\n" +
                    "    width: 100%;\n" +
                    "    border-collapse: collapse;\n" +
                    "    margin-top: 20px;\n" +
                    "    margin-bottom: 30px;\n" +
                    "}\n" +
                    "\n" +
                    "th, td {\n" +
                    "    padding: 12px 15px;\n" +
                    "    border-bottom: 1px solid #ddd;\n" +
                    "}\n" +
                    "\n" +
                    "th {\n" +
                    "    background-color: #f2f2f2;\n" +
                    "}\n" +
                    "\n" +
                    "tr:hover {\n" +
                    "    background-color: #f5f5f5;\n" +
                    "}\n" +
                    "\n" +
                    "h1.order-details-title {\n" +
                    "    margin-top: 40px;\n" +
                    "}\n" +
                    "\n" +
                    "p.footer-text {\n" +
                    "    margin-top: 30px;\n" +
                    "    text-align: center;\n" +
                    "    color: #888;\n" +
                    "}\n" +
                    "\n" +
                    ".container {\n" +
                    "    max-width: 600px;\n" +
                    "    margin: 0 auto;\n" +
                    "}\n" +
                    "\n" +
                    ".header {\n" +
                    "    text-align: center;\n" +
                    "    margin-bottom: 30px;\n" +
                    "}\n" +
                    "\n" +
                    ".footer {\n" +
                    "    text-align: center;\n" +
                    "    margin-top: 50px;\n" +
                    "    padding-top: 20px;\n" +
                    "    border-top: 1px solid #ddd;\n" +
                    "    color: #888;\n" +
                    "}\n" +
                    "\n" +
                    ".logo {\n" +
                    "    width: 100px;\n" +
                    "    height: auto;\n" +
                    "}\n" +
                    "\n" +
                    ".product-table {\n" +
                    "    border: 1px solid #ddd;\n" +
                    "}\n" +
                    "\n" +
                    ".product-table th, .product-table td {\n" +
                    "    text-align: left;\n" +
                    "}\n" +
                    "\n" +
                    ".total-amount {\n" +
                    "    font-weight: bold;\n" +
                    "}\n" +
                    "\n" +
                    "/* Add more styles as needed */\n");
            htmlContentBuilder.append("</style>");
            htmlContentBuilder.append("<body>");

            //Các nội dung của html
            htmlContentBuilder.append("<h1>").append("SPORTS CLOTHING").append("</h1>");

            NumberFormat numberFormat = NumberFormat.getCurrencyInstance(new Locale("vi", "VN"));
            // Thêm thông tin đơn hàng
            Date ngayTao = hoaDon.getNgayTao();
            String trangThaiHoaDon;
            if (hoaDon.getTrangThai().equals(HoaDonStatus.CHO_XAC_NHAN)) {
                trangThaiHoaDon = "Chờ xác nhận";
            } else if (hoaDon.getTrangThai().equals(HoaDonStatus.DA_TIEP_NHAN)){
                trangThaiHoaDon = "Đã tiếp nhận";
            }else if (hoaDon.getTrangThai().equals(HoaDonStatus.GIAO_HANG)){
                trangThaiHoaDon = "Giao hàng";
            }else if (hoaDon.getTrangThai().equals(HoaDonStatus.DA_THANH_TOAN)){
                trangThaiHoaDon = "Đã thanh toán";
            }else if (hoaDon.getTrangThai().equals(HoaDonStatus.HUY)){
                trangThaiHoaDon = "Đã hủy";
            }else if (hoaDon.getTrangThai().equals(HoaDonStatus.GIAO_HANG_THANH_CONG)){
                trangThaiHoaDon = "Giao hàng thành công";
            }else if (hoaDon.getTrangThai().equals(HoaDonStatus.CHINH_SUA)){
                trangThaiHoaDon = "Chỉnh sửa";
            }else if (hoaDon.getTrangThai().equals(HoaDonStatus.HOA_DON_CHO)){
                trangThaiHoaDon = "Hóa đơn chờ";
            }else{
                trangThaiHoaDon = "Hoàn trả";
            }

            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            String formattedNgayTao = dateFormat.format(ngayTao);

            htmlContentBuilder.append("<h3>").append("Thông tin đơn hàng").append("</h1>");
            htmlContentBuilder.append("<p>Mã đơn hàng: ").append(hoaDon.getMa()).append("</p>");
            htmlContentBuilder.append("<p>Ngày mua: ").append(formattedNgayTao).append("</p>");
            if(hoaDon.getHoTen() != null){
                htmlContentBuilder.append("<p>Khách hàng: ").append(hoaDon.getHoTen()).append("</p>");
                htmlContentBuilder.append("<p>Số điện thoại khách hàng: ").append(hoaDon.getSdt()).append("</p>");
            }else if(hoaDon.getKhachHang() != null){
                htmlContentBuilder.append("<p>Khách hàng: ").append(hoaDon.getKhachHang().getHoTen()).append("</p>");
                htmlContentBuilder.append("<p>Số điện thoại khách hàng: ").append(hoaDon.getKhachHang().getSdt()).append("</p>");
            }else{
                htmlContentBuilder.append("<p>Khách hàng: Khách lẻ</p>");
            }
            htmlContentBuilder.append("<p>Trạng thái đơn: ").append(trangThaiHoaDon).append("</p>");
            if(hoaDon.getNhanVien() != null){
                htmlContentBuilder.append("<p>Nhân viên bán hàng: ").append(hoaDon.getNhanVien().getHoTen()).append("</p>");
            }


            HoaDon hd = hoaDonService.getOne(String.valueOf(hoaDonId));
            String formattedTongTienDonHang = numberFormat.format(hd.tongTienHang());
            String formattedTongTienHoaDon = numberFormat.format(hoaDon.tongTien());
            // Thêm chi tiết đơn hàng
            htmlContentBuilder.append("<h3>").append("Chi tiết đơn hàng").append("</h1>");
            htmlContentBuilder.append("<table>");
            htmlContentBuilder.append("<tr><th>Sản phẩm</th><th>Số lượng</th><th>Thành tiền</th></tr>");
            for (HoaDonChiTiet hoaDonChiTiet : hoaDon.getHoaDonChiTiets()) {
                NumberFormat fomatTien = NumberFormat.getCurrencyInstance(new Locale("vi", "VN"));
                String fomatTienSanPham = fomatTien.format(hoaDonChiTiet.getDonGia());
                htmlContentBuilder.append("<tr>");
                htmlContentBuilder.append("<td>").append(hoaDonChiTiet.getChiTietSanPham().getSanPham().getTen())
                        .append(" (").append(hoaDonChiTiet.getChiTietSanPham().getSize().getTen())
                        .append("/").append(hoaDonChiTiet.getChiTietSanPham().getMauSac().getTen()).append(")")
                        .append("</td>");

                htmlContentBuilder.append("<td>").append(hoaDonChiTiet.getSoLuong()).append("</td>");
                htmlContentBuilder.append("<td>").append(fomatTienSanPham).append("</td>");
                htmlContentBuilder.append("</tr>");
            }
            htmlContentBuilder.append("</table>");

            // Thêm tổng tiền và các thông tin khác của hóa đơn nếu cần
            htmlContentBuilder.append("<p>Tổng giá trị đơn hàng: ").append(formattedTongTienDonHang).append("</p>");
            if(hoaDon.getPhiVanChuyen() != null){
                htmlContentBuilder.append("<p>Phí giao hàng: ").append(numberFormat.format(hoaDon.getPhiVanChuyen())).append("</p>");
            }
            htmlContentBuilder.append("<p>Tổng tiền thanh toán: ").append(formattedTongTienHoaDon).append("</p>");

            htmlContentBuilder.append("<h3>Xin chân thành cảm ơn sự ủng hộ của bạn dành cho SPORTS CLOTHING!</h3>");
            htmlContentBuilder.append("</body></html>");

            // Gọi phương thức tạo file PDF từ nội dung HTML, sử dụng thư viện iText
            byte[] pdfBytes = createPdfFromHtml(htmlContentBuilder);

            // Lưu file PDF vào thư mục dự án
            String filePath = "D:\\hoaDon\\hoa_don_" + hoaDonId + ".pdf";
            try (FileOutputStream fileOutputStream = new FileOutputStream(filePath)) {
                fileOutputStream.write(pdfBytes);
            } catch (IOException e) {
                e.printStackTrace();
                // Xử lý lỗi nếu cần thiết
            }

            // Thiết lập thông tin phản hồi
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_PDF);
            headers.setContentDispositionFormData("attachment", "hoa_don.pdf");

            // Trả về file PDF dưới dạng byte[]
            return ResponseEntity.ok().headers(headers).body(pdfBytes);
        }
        return ResponseEntity.notFound().build();
    }

    // Phương thức tạo file PDF từ nội dung HTML sử dụng thư viện iText
    private byte[] createPdfFromHtml(StringBuilder htmlContent) {
        try {
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            ConverterProperties converterProperties = new ConverterProperties();
            HtmlConverter.convertToPdf(htmlContent.toString(), outputStream, converterProperties);
            return outputStream.toByteArray();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
