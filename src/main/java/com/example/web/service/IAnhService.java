package com.example.web.service;
import com.example.web.model.Anh;
import com.example.web.model.ChiTietSanPham;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.util.List;

public interface IAnhService {

    void addAnhCtsp(MultipartFile file , HttpServletRequest request , ChiTietSanPham chiTietSanPham) throws IOException;

    List<Anh> getAnh(String id);

    void reomveAnhById(String id);
}
