package com.example.web.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UpdateChiTietSanPham {

    private UUID idCTSP;
    private UUID idSP;
    private String idMS;
    private String idKC;
}
