package com.example.web.response;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ChatLieuResponse {

    private UUID id;
    private String ten;
    private Integer trangThai;
}
