package com.example.web.response;

import lombok.*;

import java.util.UUID;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class ChiTietOnllineResponse {
    private UUID id;

    private Integer soLuong;

    private  Integer trangThai;
}
