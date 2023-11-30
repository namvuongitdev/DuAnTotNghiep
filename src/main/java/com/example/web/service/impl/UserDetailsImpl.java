package com.example.web.service.impl;
import com.example.web.model.KhachHang;
import com.example.web.model.NhanVien;
import com.example.web.repository.IKhachHangRepository;
import com.example.web.repository.INhanVienRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserDetailsImpl implements UserDetailsService {

    @Autowired
    private INhanVienRepository nhanVienRepository;

    @Autowired
    private IKhachHangRepository khachHangRepository;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        NhanVien nhanVien = nhanVienRepository.findByEmailOrTaiKhoan(username);
        KhachHang khachHang = khachHangRepository.findByEmailOrAndTaiKhoan(username);
        if (nhanVien != null) {
            List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
            grantedAuthorities.add(new SimpleGrantedAuthority(nhanVien.getChucVu().getTen()));
            return new User(username, nhanVien.getMatKhau(), grantedAuthorities);
        }
        if(khachHang != null) {
            List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
            grantedAuthorities.add(new SimpleGrantedAuthority("CLIENT"));
            return new User(username, khachHang.getMatKhau(), grantedAuthorities);

        }
        throw new UsernameNotFoundException("User with was not be found");
    }
}
