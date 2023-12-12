package com.example.web.service.impl;

import com.example.web.Config.status.HoaDonChiTietStatus;
import com.example.web.Config.status.LoaiHoaDon;
import com.example.web.Config.status.PhuongThucThanhToanStatus;
import com.example.web.model.HoaDon;
import com.example.web.repository.IHoaDonRepository;
import com.example.web.response.HoaDonChiTietReponse;
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
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.UUID;

@Service
public class InHoaDonServiceImpl implements InHoaDonService {
    @Autowired
    private IHoaDonRepository hoaDonRepository;

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
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            String formattedNgayTao = dateFormat.format(ngayTao);

            htmlContentBuilder.append("<h3>").append("Thông tin đơn hàng").append("</h1>");
            htmlContentBuilder.append("<p>Mã đơn hàng: ").append(hoaDon.getMa()).append("</p>");
            htmlContentBuilder.append("<p>Ngày mua: ").append(formattedNgayTao).append("</p>");
            if (hoaDon.getLoaiHoaDon() == LoaiHoaDon.GIAO_HANG || hoaDon.getLoaiHoaDon() == LoaiHoaDon.ONLINE) {
                htmlContentBuilder.append("<p>Loại đơn: ").append(
                        hoaDon.getLoaiHoaDon() == LoaiHoaDon.ONLINE ? "Online":"Giao hàng"
                ).append("</p>");
                htmlContentBuilder.append("<p>Họ tên: ").append(hoaDon.getHoTen()).append("</p>");
                htmlContentBuilder.append("<p>Số điện thoại: ").append(hoaDon.getSdt()).append("</p>");
                htmlContentBuilder.append("<p>Địa chỉ: ").append(hoaDon.getDiaChi()).append("</p>");
            }
            if (hoaDon.getLoaiHoaDon() == LoaiHoaDon.TAI_QUAY) {
                htmlContentBuilder.append("<p>Loại đơn: ").append("Tại quầy").append("</p>");
                if (hoaDon.getHoTen() != null) {
                    htmlContentBuilder.append("<p>Khách hàng: ").append(hoaDon.getHoTen()).append("</p>");
                    htmlContentBuilder.append("<p>Số điện thoại: ").append(hoaDon.getSdt()).append("</p>");
                } else if (hoaDon.getKhachHang() != null) {
                    htmlContentBuilder.append("<p>Khách hàng: ").append(hoaDon.getKhachHang().getHoTen()).append("</p>");
                    htmlContentBuilder.append("<p>Số điện thoại khách hàng: ").append(hoaDon.getKhachHang().getSdt()).append("</p>");
                } else {
                    htmlContentBuilder.append("<p>Khách hàng: Khách lẻ</p>");
                }
            }
            // phương thức thanh toán
            String phuongThucThanhToan = "";
            if (hoaDon.getLoaiHoaDon() == LoaiHoaDon.ONLINE || hoaDon.getLoaiHoaDon() == LoaiHoaDon.GIAO_HANG) {
                if (hoaDon.getPhuongThucThanhToan() == PhuongThucThanhToanStatus.VNPAY) {
                    phuongThucThanhToan = "Đã thanh toán tiền hàng bằng VNPAY";
                }
                if (hoaDon.getPhuongThucThanhToan() == PhuongThucThanhToanStatus.THANH_TOAN_KHI_NHAN_HANG) {
                    phuongThucThanhToan = "Thanh toán khi nhận hàng";
                }
            } else {
                if (hoaDon.getPhuongThucThanhToan() == PhuongThucThanhToanStatus.TIEN_MAT) {
                    phuongThucThanhToan = "Tiền mặt";
                }
                if (hoaDon.getPhuongThucThanhToan() == PhuongThucThanhToanStatus.CHUYEN_KHOAN) {
                    phuongThucThanhToan = "Chuyển khoản";
                }
            }
            htmlContentBuilder.append("<p>Phương thức thanh toán: ").append(
                    phuongThucThanhToan
            ).append("</p>");
            // phương thức thanh toán
            if (hoaDon.getNhanVien() != null) {
                htmlContentBuilder.append("<p>Nhân viên bán hàng: ").append(hoaDon.getNhanVien().getHoTen()).append("</p>");
            }
            String formattedTongTienDonHang = numberFormat.format(hoaDon.tongTienHang());
            String formattedTongTienHoaDon = numberFormat.format(hoaDon.tongTien());
            // Thêm chi tiết đơn hàng
            htmlContentBuilder.append("<h3>").append("Chi tiết đơn hàng").append("</h1>");
            htmlContentBuilder.append("<table>");
            htmlContentBuilder.append("<tr><th>Sản phẩm</th><th>Số lượng</th><th>Đơn giá</th><th>Thành tiền</th><th>Trạng thái</th></tr>");
            List<HoaDonChiTietReponse> hoaDonChiTiets = hoaDonRepository.findAllHoaDonChiTietByHoaDon_id(hoaDon.getId());
            for (HoaDonChiTietReponse hoaDonChiTiet : hoaDonChiTiets) {
                NumberFormat fomatTien = NumberFormat.getCurrencyInstance(new Locale("vi", "VN"));
                String fomatTienSanPham = fomatTien.format(hoaDonChiTiet.getDonGia());
                htmlContentBuilder.append("<tr>");
                htmlContentBuilder.append("<td>").append(hoaDonChiTiet.getTenSanPham())
                        .append(" (").append(hoaDonChiTiet.getTenKichCo())
                        .append("/").append(hoaDonChiTiet.getTenMauSac()).append(")")
                        .append("</td>");

                htmlContentBuilder.append("<td>").append(hoaDonChiTiet.getSoLuong()).append("</td>");
                htmlContentBuilder.append("<td>").append(fomatTienSanPham).append("</td>");
                htmlContentBuilder.append("<td>").append(fomatTien.format(hoaDonChiTiet.getDonGia().intValue() * hoaDonChiTiet.getSoLuong())).append("</td>");
                htmlContentBuilder.append("<td>").append(hoaDonChiTiet.getTrangThai() == HoaDonChiTietStatus.TRA_HANG ? "Đã hoàn trả" : "Mua hàng").append("</td>");
                htmlContentBuilder.append("</tr>");
            }
            htmlContentBuilder.append("</table>");

            // Thêm tổng tiền và các thông tin khác của hóa đơn nếu cần
            htmlContentBuilder.append("<p>Tổng giá trị đơn hàng: ").append(formattedTongTienDonHang).append("</p>");
            if (hoaDon.getPhiVanChuyen() != null) {
                htmlContentBuilder.append("<p>Phí giao hàng: ").append(numberFormat.format(hoaDon.getPhiVanChuyen())).append("</p>");
            }
            if (hoaDon.tongTienTraHang() != null) {
                htmlContentBuilder.append("<p>Hoàn tiền: ").append(numberFormat.format(hoaDon.tongTienTraHang())).append("</p>");
            }
            htmlContentBuilder.append("<p>Tổng số tiền cần trả: ").append(formattedTongTienHoaDon).append("</p>");
            if (hoaDon.getLoaiHoaDon() == LoaiHoaDon.ONLINE && hoaDon.getPhuongThucThanhToan() == 4) {
                htmlContentBuilder.append("<p>Đã thanh toán: ").append(numberFormat.format(hoaDon.tongTienHang())).append("</p>");
                htmlContentBuilder.append("<p>Cần thanh toán khi nhận hàng: ").append(numberFormat.format(hoaDon.getPhiVanChuyen())).append("</p>");
            }
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
