package com.example.web.service.impl;
import com.example.web.model.ChatLieu;
import com.example.web.repository.IChatLieuRepository;
import com.example.web.service.IChatLieuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ChatLieuServiceImpl implements IChatLieuService {

    @Autowired
    private IChatLieuRepository repository;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<ChatLieu> getAll() {
        return repository.findAll();
    }

    @Override
    public List<ChatLieu> getAll1() {
        return repository.getAll1();
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
    public ChatLieu save(ChatLieu chatLieu) {
        return repository.save(chatLieu);
    }

    @Override
    public Page<ChatLieu> findAll(Pageable pageable) {
        return repository.findAll(pageable);
    }

    @Override
    public String updateStatus(String id, Integer trangThai) {
        ChatLieu chatLieu = repository.getReferenceById(UUID.fromString(id));
        Date date = java.util.Calendar.getInstance().getTime();
        if (trangThai==0){
            chatLieu.setTrangThai(1);
        }else {
            chatLieu.setTrangThai(0);
        }
        chatLieu.setNgaySua(date);
        repository.save(chatLieu);
        return "redirect:/admin/chat-lieu/hien-thi";
    }

    @Override
    public boolean isExists(String value) {
        String sql = "SELECT COUNT(*) FROM chat_lieu WHERE chat_lieu.ten = ?";
        int count = jdbcTemplate.queryForObject(sql, Integer.class, value);
        return count > 0;
    }
}
