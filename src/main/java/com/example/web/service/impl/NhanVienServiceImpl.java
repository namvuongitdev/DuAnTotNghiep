package com.example.web.service.impl;
import com.example.web.model.NhanVien;
import com.example.web.repository.IKhachHangRepository;
import com.example.web.repository.INhanVienRepository;
import com.example.web.response.NhanVienFilter;
import com.example.web.service.INhanVienService;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import jakarta.transaction.Transactional;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class NhanVienServiceImpl implements INhanVienService  {
    @Autowired
    private INhanVienRepository iNhanVienRepository;

    @Autowired
    private IKhachHangRepository iKhachHangRepository;

    @Override
    public Page<NhanVien> getAll(Pageable pageable) {
        return iNhanVienRepository.findAll(pageable);
    }

    @Override
    public List<NhanVien> findAll() {
        return iNhanVienRepository.findAll();
    }

    @Override
    public NhanVien findById(UUID id) {
        return iNhanVienRepository.findById(id).orElse(null);
    }

    @Override
    @Transactional
    public NhanVien add(NhanVien nhanVien) {
        UUID id = UUID.randomUUID();
        Date date = java.util.Calendar.getInstance().getTime();
        iKhachHangRepository.insertKhachHang(id, nhanVien.getHoTen(), nhanVien.getEmail(), nhanVien.getTaiKhoan(), nhanVien.getMatKhau(),
                nhanVien.getSdt(), date, nhanVien.getDiaChi(), 0);
        return iNhanVienRepository.save(nhanVien);
    }

    @Override
    public NhanVien update(UUID id, NhanVien nhanVien) {
        if(id != null){
            NhanVien nv = iNhanVienRepository.findById(id).orElse(null);
            if(nv != null){
                BeanUtils.copyProperties(nhanVien, nv);
                iNhanVienRepository.save(nv);
            }
        }
        return null;
    }

    @Override
    public Page<NhanVien> nhanVienFilter(NhanVienFilter filter , Pageable pageable) {
        return iNhanVienRepository.findAll(new Specification<NhanVien>(){
            @Override
            public Predicate toPredicate(Root<NhanVien> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder){
                List<Predicate> predicates = new ArrayList<>();
                if (!filter.getSearch().isBlank()) {
                    predicates.add(criteriaBuilder.or(criteriaBuilder.like(root.get("taiKhoan"), "%"+filter.getSearch()+"%"),
                            criteriaBuilder.like(root.get("sdt"), "%"+filter.getSearch()+"%"),
                            criteriaBuilder.like(root.get("hoTen"), "%"+filter.getSearch()+"%"),
                            criteriaBuilder.like(root.get("email"), "%"+filter.getSearch()+"%")));
                }
                if(filter.getTrangThai() != null){
                    predicates.add(criteriaBuilder.and(criteriaBuilder.equal(root.get("trangThai"), filter.getTrangThai())));
                }
                if(filter.getGioiTinh() != null){
                    predicates.add(criteriaBuilder.and(criteriaBuilder.equal(root.get("gioiTinh"), filter.getGioiTinh())));
                }
                return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
            }
        }, pageable);
    }

    @Override
    public NhanVien checkTaiKhoan(String taiKhoan) {
        return iNhanVienRepository.findByTaiKhoan(taiKhoan);
    }

    @Override
    public NhanVien checkEmail(String email) {
        return iNhanVienRepository.findByEmail(email);
    }

    @Override
    public NhanVien findBySDT(String sdt) {
        return iNhanVienRepository.findBySDT(sdt);
    }

    @Override
    public UUID findIdByEmail(String email) {
        return iNhanVienRepository.findIdByEmail(email);
    }

    @Override
    public String findEmailToPass(String taiKhoan) {
        return iNhanVienRepository.findEmailToPass(taiKhoan);
    }

    @Override
    public String updateStatus(String id, Integer trangThai) {
        NhanVien nhanVien = iNhanVienRepository.getReferenceById(UUID.fromString(id));
        Date date = java.util.Calendar.getInstance().getTime();
        if (trangThai==0){
            nhanVien.setTrangThai(1);
        }else {
            nhanVien.setTrangThai(0);
        }
        nhanVien.setNgaySua(date);
        iNhanVienRepository.save(nhanVien);
        return "redirect:/admin/nhan-vien/hien-thi";
    }

    @Override
    public NhanVien getNhanVienLogin() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        NhanVien nhanVien = iNhanVienRepository.findByEmailOrTaiKhoan(authentication.getName());
        return nhanVien;
    }

    @Override
    public NhanVien getByEmailOrTaiKhoan(String usename) {
        return iNhanVienRepository.findByEmailOrTaiKhoan(usename);
    }

}
