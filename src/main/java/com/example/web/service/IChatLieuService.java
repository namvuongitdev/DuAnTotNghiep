package com.example.web.service;
import com.example.web.model.ChatLieu;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.UUID;

public interface IChatLieuService {

    List<ChatLieu> getAll();

    List<ChatLieu> getAll1();

    ChatLieu getOne(UUID id);

    void add(ChatLieu chatLieu);

    ChatLieu save(ChatLieu chatLieu);

    Page<ChatLieu> findAll(Pageable pageable);

    String updateStatus(String id,Integer trangThai);

    boolean isExists(String value);
}
