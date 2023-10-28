package com.example.web.service.impl;
import com.example.web.model.Anh;
import com.example.web.model.ChiTietSanPham;
import com.example.web.repository.IAnhRepository;
import com.example.web.response.AnhResponse;
import com.example.web.service.IAnhService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;
import java.util.UUID;

@Service
public class AnhServiceImpl implements IAnhService {

    @Autowired
    private IAnhRepository anhRepository;

    @Override
    public void addAnhCtsp(MultipartFile file , HttpServletRequest request , ChiTietSanPham chiTietSanPham) {
        InputStream inputStream = null;
        OutputStream outputStream = null;
        String fileName = file.getOriginalFilename();
        Anh anh = Anh.builder().
                chiTietSanPham(chiTietSanPham).
                build();
        anhRepository.save(anh);
        File newFile = new File("D:C:/Users/Admin/Pictures/img/" + fileName + anh.getId());
        anh.setTen(fileName + anh.getId());
        anhRepository.save(anh);
        try {
            inputStream = file.getInputStream();

            if (!newFile.exists()) {
                newFile.createNewFile();
            }
            outputStream = new FileOutputStream(newFile);
            int read = 0;
            byte[] bytes = new byte[1024];

            while ((read = inputStream.read(bytes)) != -1) {
                outputStream.write(bytes, 0, read);
            }

        } catch (Exception e) {
            System.out.println("ErrorAddGrammar:" + e);
        }
    }

    @Override
    public List<Anh> getAnh(String id) {
        List<Anh> anhs = anhRepository.findByChiTietSanPham_Id(UUID.fromString(id));
        return anhs;
    }

    @Override
    public void reomveAnhById(String id) {
        anhRepository.deleteById(UUID.fromString(id));
    }

    @Override
    public List<Anh> getTenAnh(UUID idSanPham) {
        return anhRepository.getTenAnh(idSanPham);
    }

}
