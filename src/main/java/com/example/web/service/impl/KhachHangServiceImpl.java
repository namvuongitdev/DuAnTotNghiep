package com.example.web.service.impl;

import com.example.web.model.KhachHang;
import com.example.web.repository.IKhachHangRepository;
import com.example.web.response.KhachHangFilter;
import com.example.web.service.IKhachHangService;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class KhachHangServiceImpl implements IKhachHangService {

    @Autowired
    private IKhachHangRepository khachHangRepository;

    @Override
    public Page<KhachHang> getAll(Integer page) {
        Pageable pageable = PageRequest.of(page - 1, 10);
        return khachHangRepository.findAll(pageable);
    }

    @Override
    public KhachHang getKhachHangById(String id) {
        KhachHang khachHang = khachHangRepository.findById(UUID.fromString(id)).orElse(null);
        return khachHang;
    }

    @Override
    public Page<KhachHang> getKhachHangByHoTenOrSdtOrTaiKhoan(String timKiem, Integer page) {
        Pageable pageable = PageRequest.of(page - 1, 10);
        return khachHangRepository.findAllByHoTenLikeOrSdtOrTaiKhoan("%" + timKiem + "%", pageable);
    }

    @Override
    public KhachHang themMoiKhachHang(KhachHang khachHang) {
        return khachHangRepository.save(khachHang);
    }

    @Override
    public KhachHang findByEmailOrAndTaiKhoan(String username) {
        return khachHangRepository.findByEmailOrAndTaiKhoan(username);
    }

    public List<KhachHang> findAll() {
        return khachHangRepository.findAll();
    }

    @Override
    public KhachHang findById(UUID id) {
        return khachHangRepository.getReferenceById(id);
    }

    @Override
    public void add(KhachHang khachHang) {
        khachHangRepository.save(khachHang);
    }

    @Override
    public void update(KhachHang khachHang) {
        khachHangRepository.save(khachHang);
    }

    @Override
    public Page<KhachHang> khachHangFillter(KhachHangFilter filter, Pageable pageable) {
        return khachHangRepository.findAll(new Specification<KhachHang>() {
            @Override
            public Predicate toPredicate(Root<KhachHang> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                List<Predicate> predicates = new ArrayList<>();
                if (!filter.getSearch().isBlank()) {
                    predicates.add(criteriaBuilder.or(criteriaBuilder.equal(root.get("taiKhoan"), filter.getSearch()),
                            criteriaBuilder.equal(root.get("sdt"), filter.getSearch()),
                            criteriaBuilder.equal(root.get("hoTen"), filter.getSearch()),
                            criteriaBuilder.equal(root.get("email"), filter.getSearch())));
                }
                return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
            }
        }, pageable);
    }

    @Override
    public KhachHang findByEmail(String email) {
        return khachHangRepository.findByEmail(email);
    }

    @Override
    public KhachHang findByTaiKhoan(String taiKhoan) {
        return khachHangRepository.findByTaiKhoan(taiKhoan);
    }

    @Override
    public KhachHang findBySdt(String sdt) {
        return khachHangRepository.findBySdt(sdt);
    }

    @Override
    public KhachHang getKhachHangLogin() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        KhachHang khachHang = khachHangRepository.findByEmailOrAndTaiKhoan(authentication.getName());
        return khachHang;
    }
}
