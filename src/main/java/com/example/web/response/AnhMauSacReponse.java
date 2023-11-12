package com.example.web.response;

import org.springframework.beans.factory.annotation.Value;

public interface AnhMauSacReponse {

    @Value("#{target.tenAnh}")
    String getTenAnh();
}
