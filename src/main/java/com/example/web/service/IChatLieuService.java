package com.example.web.service;
import com.example.web.model.ChatLieu;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.UUID;

public interface IChatLieuService {

    List<ChatLieu> getAll();

    List<ChatLieu> getAll1();

    ChatLieu getOne(UUID id);

    void add(ChatLieu chatLieu);

    void update(ChatLieu chatLieu);

    void delete(UUID id);

    Page<ChatLieu> page(Integer pageNo, Integer size);
}
