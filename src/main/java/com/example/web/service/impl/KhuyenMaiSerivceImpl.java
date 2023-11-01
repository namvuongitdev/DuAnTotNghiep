package com.example.web.service.impl;

import com.example.web.Config.status.KhuyenMaiStatus;
import com.example.web.model.KhuyenMai;
import com.example.web.model.SanPham;
import com.example.web.model.SanPhamKhuyenMai;
import com.example.web.repository.IKhuyenMaiRepository;
import com.example.web.repository.SanPhamKhuyenMaiRepository;
import com.example.web.response.FilterKhuyenMai;
import com.example.web.response.KhuyenMaiReponse;
import com.example.web.response.SanPhamAsKhuyenMai;
import com.example.web.service.IKhuyenMaiService;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class KhuyenMaiSerivceImpl implements IKhuyenMaiService {

    @Autowired
    private IKhuyenMaiRepository repository;

    @Autowired
    private SanPhamKhuyenMaiRepository sanPhamKhuyenMaiRepository;

    @Override
    public KhuyenMai addKhuyenMai(KhuyenMai khuyenMai) {
        Integer maKhuyenMai = repository.findAll().size() + 1;
        khuyenMai.setMa("KM" + maKhuyenMai);
        khuyenMai.setTrangThai(KhuyenMaiStatus.KICH_HOAT);
        return repository.save(khuyenMai);
    }

    @Override
    public KhuyenMai updateKhuyenMai(KhuyenMai khuyenMai) {
        return repository.save(khuyenMai);
    }

    @Override
    public Page<SanPhamKhuyenMai> filterSanPhamKhuyeMai(SanPhamAsKhuyenMai filter , Pageable pageable ,UUID idKM) {
        return sanPhamKhuyenMaiRepository.findAll(new Specification<SanPhamKhuyenMai>() {
            @Override
            public Predicate toPredicate(Root<SanPhamKhuyenMai> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                List<Predicate> predicateList = new ArrayList<>();
               Join<SanPham , SanPhamKhuyenMai> sanPhamSanPhamKhuyenMaiJoin =  root.join("sanPhamKM");
                if (!filter.getTenSanPham().isEmpty() && filter.getTenSanPham() != null) {
                    predicateList.add(criteriaBuilder.or(criteriaBuilder.like(sanPhamSanPhamKhuyenMaiJoin.get("ten"), "%"+filter.getTenSanPham()+"%"),
                            criteriaBuilder.like(sanPhamSanPhamKhuyenMaiJoin.get("ma"), "%"+filter.getTenSanPham()+"%")));
                }
                if (filter.getTrangThai() != null) {
                    predicateList.add(criteriaBuilder.and(criteriaBuilder.equal(root.get("trangThai"), filter.getTrangThai())));
                }
                if(filter.getLoaiGiamGia() != null){
                    predicateList.add(criteriaBuilder.and(criteriaBuilder.equal(root.get("loaiGiamGia"), filter.getLoaiGiamGia())));
                }
                predicateList.add(criteriaBuilder.and(criteriaBuilder.equal(root.join("khuyenMai").get("id"),idKM)));
                return criteriaBuilder.and(predicateList.toArray(new Predicate[predicateList.size()]));
            }
        }, pageable );
    }

    @Override
    public Page<KhuyenMai> getAll(Integer page) {
        if (page < 0) {
            return null;
        } else {
            Pageable pageable = PageRequest.of(page - 1, 10);
            Page<KhuyenMai> pageKhuyenMai = repository.findAll(pageable);
            return pageKhuyenMai;
        }
    }

    @Override
    public Page<KhuyenMai> filterKhuyenMai(Integer page, FilterKhuyenMai filter) {
        Pageable pageable = PageRequest.of(page - 1, 10);
        return repository.findAll(new Specification<KhuyenMai>() {
            @Override
            public Predicate toPredicate(Root<KhuyenMai> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                List<Predicate> predicateList = new ArrayList<>();
                if (!filter.getSearch().isEmpty() && filter.getSearch() != null) {
                    predicateList.add(criteriaBuilder.or(criteriaBuilder.like(root.get("ten"),"%"+ filter.getSearch()+"%"),
                            criteriaBuilder.like(root.get("ma"), "%"+filter.getSearch()+"%")));
                }
                if (!filter.getTrangThai().isEmpty() && filter.getTrangThai() != null) {
                    predicateList.add(criteriaBuilder.and(criteriaBuilder.equal(root.get("trangThai"), filter.getTrangThai())));
                }
                return criteriaBuilder.and(predicateList.toArray(new Predicate[predicateList.size()]));
            }
        }, pageable);
    }

    @Override
    public Page<SanPhamKhuyenMai> getKhuyenMaiById(UUID id, Integer page) {
        Pageable pageable = PageRequest.of(page - 1, 10);
        return repository.chiTietKhuyenMaiById(id, pageable);
    }

    @Override
    public Boolean addSanPhamKhuyenMai(SanPhamKhuyenMai sanPhamKhuyenMai, String idKM) {
        Optional<KhuyenMai> khuyenMai = repository.findById(UUID.fromString(idKM));
        if (khuyenMai.isPresent()) {
            sanPhamKhuyenMai.getSanPhams().forEach(o -> {
                if(sanPhamKhuyenMaiRepository.kiemTraDaTonTai(o.getId() , khuyenMai.get().getId()) != null){
                 return;
                }
                SanPhamKhuyenMai spkm = SanPhamKhuyenMai.builder()
                        .khuyenMai(khuyenMai.get())
                        .sanPhamKM(o)
                        .trangThai(KhuyenMaiStatus.KICH_HOAT)
                        .mucGiam(sanPhamKhuyenMai.getMucGiam())
                        .loaiGiamGia(sanPhamKhuyenMai.getLoaiGiamGia())
                        .build();
                if (sanPhamKhuyenMai.getLoaiGiamGia()) {
                    Integer  donGiaKhiGiamPhanTram = spkm.getSanPhamKM().getGiaBan().intValue() - (spkm.getSanPhamKM().getGiaBan().intValue() / 100) * sanPhamKhuyenMai.getMucGiam().intValue();
                    spkm.setDonGiaSauKhiGiam(BigDecimal.valueOf(donGiaKhiGiamPhanTram));
                }else{
                    Integer donGiaKhiGiamVND = spkm.getSanPhamKM().getGiaBan().intValue() - sanPhamKhuyenMai.getMucGiam().intValue();
                    spkm.setDonGiaSauKhiGiam(BigDecimal.valueOf(donGiaKhiGiamVND));
                }
                sanPhamKhuyenMaiRepository.save(spkm);
            });
        } else {
            return false;
        }
        return true;
    }

    @Override
    public KhuyenMai getById(UUID uuid) {
        return repository.findById(uuid).get();
    }

    @Override
    public SanPhamKhuyenMai updateTrangThaiKhuyenMaiChiTiet(Integer trangThai, UUID uuid) {
        Optional<SanPhamKhuyenMai> sanPhamKhuyenMai = sanPhamKhuyenMaiRepository.findById(uuid);
        if (sanPhamKhuyenMai.isPresent()) {
            sanPhamKhuyenMai.get().setTrangThai(trangThai);
            return sanPhamKhuyenMaiRepository.save(sanPhamKhuyenMai.get());
        } else {
            return null;
        }
    }

    @Override
    public KhuyenMai updateTrangThaiKhuyenMai(Integer trangThai, UUID uuid) {
        Optional<KhuyenMai> khuyenMai = repository.findById(uuid);
        if (khuyenMai.isPresent()) {
            khuyenMai.get().setTrangThai(trangThai);
            return repository.save(khuyenMai.get());
        } else {
            return null;
        }
    }

    @Override
    public SanPhamAsKhuyenMai getSanPhamAsKhuyenMai(UUID id) {
    SanPhamAsKhuyenMai sanPhamAsKhuyenMai  = sanPhamKhuyenMaiRepository.findSanPhamKhuyenMaiById(id);
        return sanPhamAsKhuyenMai;
    }

    @Override
    public SanPhamKhuyenMai updateSanPhamKhuyenMai(SanPhamKhuyenMai sanPhamKhuyenMai) {
        return sanPhamKhuyenMaiRepository.save(sanPhamKhuyenMai);
    }

    @Override
    public SanPhamKhuyenMai getSanPhamKhuyenMaiById(UUID id) {
        Optional<SanPhamKhuyenMai> sanPhamKhuyenMai = sanPhamKhuyenMaiRepository.findById(id);
        if(sanPhamKhuyenMai.isPresent()){
            return sanPhamKhuyenMai.get();
        }
        return null;
    }

    @Scheduled(cron = "0 0 0 * * *")
    public void updateNgayHetHan(){
        System.out.println("run...");
    }
}
