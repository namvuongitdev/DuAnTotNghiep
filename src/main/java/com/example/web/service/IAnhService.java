package com.example.web.service;
import com.example.web.model.Anh;
import com.example.web.model.ChiTietSanPham;
import com.example.web.response.AnhResponse;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

public interface IAnhService {

    void addAnhCtsp(MultipartFile file , HttpServletRequest request , ChiTietSanPham chiTietSanPham) throws IOException;

    List<Anh> getAnh(String id);

    void reomveAnhById(String id);

    List<Anh> getTenAnh(UUID idSanPham);

}
