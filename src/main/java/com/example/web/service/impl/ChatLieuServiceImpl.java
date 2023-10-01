package com.example.web.service.impl;
import com.example.web.model.ChatLieu;
import com.example.web.repository.IChatLieuRepository;
import com.example.web.service.IChatLieuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ChatLieuServiceImpl implements IChatLieuService {

    @Autowired
    private IChatLieuRepository repository;

    @Override
    public List<ChatLieu> getAll() {
        return repository.findAll();
    }

    @Override
    public ChatLieu getOne(UUID id) {
        return repository.getReferenceById(id);
    }

    @Override
    public void add(ChatLieu chatLieu) {
        repository.save(chatLieu);
    }

    @Override
    public void update(ChatLieu chatLieu) {
        repository.save(chatLieu);
    }

    @Override
    public void delete(UUID id) {
        repository.deleteById(id);
    }

    @Override
    public Page<ChatLieu> page(Integer pageNo, Integer size) {
        Pageable pageable = PageRequest.of(pageNo, size);
        return repository.findAll(pageable);
    }
}
