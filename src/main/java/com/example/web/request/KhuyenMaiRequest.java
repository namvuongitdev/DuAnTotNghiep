package com.example.web.request;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class KhuyenMaiRequest {

    private UUID id;

    @NotBlank(message = "không để trống tên khuyến mại")
    private String ten;

    private String moTa;

    @NotBlank(message = "không để trống ngày bắt đầu khuyến mại")
    private String ngayBatDau;

    @NotBlank(message = "không để trống ngày kết thúc khuyến mại")
    private String ngayKetThuc;
}
