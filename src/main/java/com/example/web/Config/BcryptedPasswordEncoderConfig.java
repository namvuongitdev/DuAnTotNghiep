package com.example.web.Config;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class BcryptedPasswordEncoderConfig extends BCryptPasswordEncoder {
    public BCryptPasswordEncoder passwordEncorder()
    {
        return new BCryptPasswordEncoder();
    }


}
