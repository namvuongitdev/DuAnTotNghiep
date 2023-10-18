package com.example.web.service.impl;
import com.example.web.model.MauSac;
import com.example.web.repository.IMauSacRepository;
import com.example.web.service.IMauSacService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class MauSacServiceImpl implements IMauSacService {
    @Autowired
    private IMauSacRepository mauSacRepository;

    @Override
    public List<MauSac> getAll() {
        return mauSacRepository.findAll();
    }

    @Override
    public MauSac getOne(String id) {
        return mauSacRepository.getReferenceById(UUID.fromString(id));
    }

    @Override
    public void add(MauSac mauSac) {
        mauSacRepository.save(mauSac);
    }

    @Override
    public void update(MauSac mauSac) {
        mauSacRepository.save(mauSac);
    }

    @Override
    public void delete(String id) {
        mauSacRepository.deleteById(UUID.fromString(id));
    }

    @Override
    public Page<MauSac> page(Integer pageNo, Integer size) {
        Pageable pageable = PageRequest.of(pageNo, size);
        return mauSacRepository.findAll(pageable);
    }

    @Override
    public List<MauSac> getAll1() {
        return mauSacRepository.getAll1();
    }

    @Override
    public List<MauSac> getTheoCTSP(UUID idSP) {
        return mauSacRepository.getTheoCTSP(idSP);
    }


}
