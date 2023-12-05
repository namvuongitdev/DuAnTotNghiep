package com.example.web.service.impl;

import com.example.web.model.Size;
import com.example.web.repository.SizeRepository;
import com.example.web.service.SizeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class SizeServiceImpl implements SizeService {
    @Autowired
    private SizeRepository sizeRepository;

    @Override
    public List<Size> getAll() {
        return sizeRepository.findAll();
    }

    @Override
    public List<Size> getAll1() {
        return sizeRepository.getAll1();
    }

    @Override
    public Size getOne(String id) {
        return sizeRepository.getReferenceById(id);
    }

    @Override
    public void add(Size size) {
        sizeRepository.save(size);
    }

    @Override
    public Size save(Size size) {
        return sizeRepository.save(size);
    }

    @Override
    public Page<Size> findAll(Pageable pageable) {
        return sizeRepository.findAll(pageable);
    }

    @Override
    public List<Size> getTheoCT(UUID idSP) {
        return sizeRepository.getTheoCT(idSP);
    }

    @Override
    public String updateStatus(String id, Integer trangThai) {
        Size size =sizeRepository.getReferenceById(id);
        Date date = java.util.Calendar.getInstance().getTime();
        if (trangThai==0){
            size.setTrangThai(1);
        }else {
            size.setTrangThai(0);
        }
        size.setNgaySua(date);
        sizeRepository.save(size);
        return "redirect:/admin/size/hien-thi";
    }

    @Override
    public boolean isExists(String value) {
        return false;
    }


}
