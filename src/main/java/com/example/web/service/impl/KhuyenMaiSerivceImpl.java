package com.example.web.service.impl;

import com.example.web.Config.status.ChiTietKhuyenMaiStatus;
import com.example.web.Config.status.KhuyenMaiStatus;
import com.example.web.model.KhuyenMai;
import com.example.web.model.SanPham;
import com.example.web.model.SanPhamKhuyenMai;
import com.example.web.repository.IKhuyenMaiRepository;
import com.example.web.repository.ISanPhamRepository;
import com.example.web.repository.SanPhamKhuyenMaiRepository;
import com.example.web.request.KhuyenMaiRequest;
import com.example.web.request.KhuyenMaiSanPhamRequest;
import com.example.web.response.FilterKhuyenMai;
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
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class KhuyenMaiSerivceImpl implements IKhuyenMaiService {

    @Autowired
    private IKhuyenMaiRepository repository;

    @Autowired
    private SanPhamKhuyenMaiRepository sanPhamKhuyenMaiRepository;

    @Autowired
    private ISanPhamRepository sanPhamRepository;

    @Override
    public KhuyenMai addKhuyenMai(KhuyenMai khuyenMai) {
        Integer maKhuyenMai = repository.findAll().size() + 1;
        khuyenMai.setMa("KM" + maKhuyenMai);
        return repository.save(khuyenMai);
    }

    @Override
    public Integer validateTrangThai(KhuyenMai khuyenMai) {
        Date thoiGianHienTai = java.util.Calendar.getInstance().getTime();
        if (khuyenMai.getNgayKetThuc().before(thoiGianHienTai)) {
            return KhuyenMaiStatus.NGUNG_KICH_HOAT;
        } else if (khuyenMai.getNgayBatDau().after(thoiGianHienTai)) {
            return KhuyenMaiStatus.CHUA_BAT_DAU;
        } else {
            return KhuyenMaiStatus.KICH_HOAT;
        }
    }

    @Override
    public KhuyenMai getKhuyenMaiById(UUID uuid) {
        Optional<KhuyenMai> khuyenMai = repository.findById(uuid);
        if (khuyenMai.isPresent()) {
            KhuyenMai km = khuyenMai.get();
            km.setTrangThai(KhuyenMaiStatus.HUY);
            return repository.save(km);
        } else {
            return null;
        }
    }

    @Override
    public KhuyenMai updateKhuyenMai(KhuyenMaiRequest request, UUID idKM) {
        Optional<KhuyenMai> km = repository.findById(idKM);
        if (km.isPresent()) {
            KhuyenMai khuyenMai = km.get();
            khuyenMai.setMoTa(request.getMoTa());
            khuyenMai.setNgayBatDau(java.sql.Date.valueOf(request.getNgayBatDau()));
            khuyenMai.setNgayKetThuc(java.sql.Date.valueOf(request.getNgayKetThuc()));
            khuyenMai.setTen(request.getTen());
            Integer trangThai = validateTrangThai(khuyenMai);
            khuyenMai.setTrangThai(trangThai);
            return repository.save(khuyenMai);
        }
        return null;
    }

    @Override
    public Page<SanPhamKhuyenMai> filterSanPhamKhuyeMai(SanPhamAsKhuyenMai filter, Pageable pageable, UUID idKM) {
        return sanPhamKhuyenMaiRepository.findAll(new Specification<SanPhamKhuyenMai>() {
            @Override
            public Predicate toPredicate(Root<SanPhamKhuyenMai> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                List<Predicate> predicateList = new ArrayList<>();
                Join<SanPham, SanPhamKhuyenMai> sanPhamSanPhamKhuyenMaiJoin = root.join("sanPhamKM");
                if (!filter.getTenSanPham().isEmpty() && filter.getTenSanPham() != null) {
                    predicateList.add(criteriaBuilder.or(criteriaBuilder.like(sanPhamSanPhamKhuyenMaiJoin.get("ten"), "%" + filter.getTenSanPham() + "%"),
                            criteriaBuilder.like(sanPhamSanPhamKhuyenMaiJoin.get("ma"), "%" + filter.getTenSanPham() + "%")));
                }
                if (filter.getTrangThai() != null) {
                    predicateList.add(criteriaBuilder.and(criteriaBuilder.equal(root.get("trangThai"), filter.getTrangThai())));
                }
                if (filter.getLoaiGiamGia() != null) {
                    predicateList.add(criteriaBuilder.and(criteriaBuilder.equal(root.get("loaiGiamGia"), filter.getLoaiGiamGia())));
                }
                predicateList.add(criteriaBuilder.and(criteriaBuilder.equal(root.join("khuyenMai").get("id"), idKM)));
                return criteriaBuilder.and(predicateList.toArray(new Predicate[predicateList.size()]));
            }
        }, pageable);
    }

    @Override
    public SanPhamKhuyenMai getSanPhamById(UUID idSP) {
        SanPhamKhuyenMai spkm = repository.findBySanPham_id(idSP);
        if (spkm.getLoaiGiamGia()) {
            Integer donGiaKhiGiamPhanTram = spkm.getSanPhamKM().getGiaBan().intValue() - (spkm.getSanPhamKM().getGiaBan().intValue() / 100) * spkm.getMucGiam().intValue();
            spkm.setDonGiaSauKhiGiam(BigDecimal.valueOf(donGiaKhiGiamPhanTram));
        } else {
            Integer donGiaKhiGiamVND = spkm.getSanPhamKM().getGiaBan().intValue() - spkm.getMucGiam().intValue();
            spkm.setDonGiaSauKhiGiam(BigDecimal.valueOf(donGiaKhiGiamVND));
        }
        sanPhamKhuyenMaiRepository.save(spkm);
        return spkm;
    }

    @Override
    public BigDecimal donGiaSauKhiGiam(List<SanPhamKhuyenMai> sanPhamKhuyenMais) {
        if (!sanPhamKhuyenMais.isEmpty()) {
            for (SanPhamKhuyenMai o : sanPhamKhuyenMais) {
                if (o.getKhuyenMai().getTrangThai() == KhuyenMaiStatus.KICH_HOAT && o.getTrangThai() == ChiTietKhuyenMaiStatus.KICH_HOAT) {
                    if (o.getLoaiGiamGia()) {
                        return o.getDonGiaSauKhiGiam();
                    } else {
                        return o.getDonGiaSauKhiGiam();
                    }
                }
            }
        }
        return null;
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
                    predicateList.add(criteriaBuilder.or(criteriaBuilder.like(root.get("ten"), "%" + filter.getSearch() + "%"),
                            criteriaBuilder.like(root.get("ma"), "%" + filter.getSearch() + "%")));
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
    public SanPham addSanPhamKhuyenMai(KhuyenMaiSanPhamRequest request) {
        Optional<KhuyenMai> khuyenMai = repository.findById(UUID.fromString(request.getIdKM()));
        Optional<SanPham> sanPham = sanPhamRepository.findById(UUID.fromString(request.getIdSanPham()));
        if (khuyenMai.isPresent()) {
            SanPhamKhuyenMai spkm = SanPhamKhuyenMai.builder()
                    .khuyenMai(khuyenMai.get())
                    .sanPhamKM(sanPham.get())
                    .trangThai(KhuyenMaiStatus.KICH_HOAT)
                    .mucGiam(request.getMucGiam())
                    .loaiGiamGia(request.getLoaiGiamGia())
                    .build();
            if (spkm.getLoaiGiamGia()) {
                Integer donGiaKhiGiamPhanTram = spkm.getSanPhamKM().getGiaBan().intValue() - (spkm.getSanPhamKM().getGiaBan().intValue() / 100) * spkm.getMucGiam().intValue();
                spkm.setDonGiaSauKhiGiam(BigDecimal.valueOf(donGiaKhiGiamPhanTram));
            } else {
                Integer donGiaKhiGiamVND = spkm.getSanPhamKM().getGiaBan().intValue() - spkm.getMucGiam().intValue();
                spkm.setDonGiaSauKhiGiam(BigDecimal.valueOf(donGiaKhiGiamVND));
            }
            sanPhamKhuyenMaiRepository.save(spkm);
        }
        return sanPham.get();
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
    public SanPhamAsKhuyenMai getSanPhamAsKhuyenMai(UUID id) {
        SanPhamAsKhuyenMai sanPhamAsKhuyenMai = sanPhamKhuyenMaiRepository.findSanPhamKhuyenMaiById(id);
        return sanPhamAsKhuyenMai;
    }

    @Override
    public SanPhamKhuyenMai updateSanPhamKhuyenMai(SanPhamKhuyenMai sanPhamKhuyenMai) {
        return sanPhamKhuyenMaiRepository.save(sanPhamKhuyenMai);
    }

    @Override
    public SanPhamKhuyenMai getSanPhamKhuyenMaiById(UUID id) {
        Optional<SanPhamKhuyenMai> sanPhamKhuyenMai = sanPhamKhuyenMaiRepository.findById(id);
        if (sanPhamKhuyenMai.isPresent()) {
            return sanPhamKhuyenMai.get();
        }
        return null;
    }

    @Scheduled(cron = "0 0 0 * * *")
    public void updateNgayHetHan() {
        List<KhuyenMai> listKhuyenMai = repository.findKhuyenMaiByHetHan();
        listKhuyenMai.forEach(o -> {
            o.setTrangThai(KhuyenMaiStatus.HET_HAN);
            repository.save(o);
        });
    }

    @Scheduled(cron = "0 0 0 * * *")
    public void updateChuaBatDau() {
        List<KhuyenMai> listKhuyenMai = repository.findKhuyenMaiByChuBatDau();
        listKhuyenMai.forEach(o -> {
            o.setTrangThai(KhuyenMaiStatus.KICH_HOAT);
            repository.save(o);
        });
    }
}
