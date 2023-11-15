package com.example.web.service;
import com.example.web.model.Anh;
import com.example.web.model.MauSac;
import com.example.web.model.SanPham;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

public interface IAnhService {

    void addAnhMauSac(MultipartFile file , SanPham sanPham , MauSac mauSac) throws IOException;

    void reomveAnhById(String id);

    List<Anh> findAnhBySanPham_idAndMauSac_id(UUID idSP , UUID idMS);

    List<Anh> getAllAnhBySanPham_id(UUID idSP);

}
