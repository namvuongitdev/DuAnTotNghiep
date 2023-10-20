package com.example.web.service.impl;
import com.example.web.model.ChatLieu;
import com.example.web.model.DanhMuc;
import com.example.web.model.KieuDang;
import com.example.web.model.MauSac;
import com.example.web.model.SanPham;
import com.example.web.model.Size;
import com.example.web.repository.ISanPhamRepository;
import com.example.web.response.SanPhamAsKhuyenMai;
import com.example.web.response.SanPhamFilter;
import com.example.web.service.ISanPhamService;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.JoinType;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class SanPhamServiceImpl implements ISanPhamService {

    @Autowired
    private ISanPhamRepository iSanPhamRepository;

    @Override
    public Page<SanPham> findAll(Pageable pageable) {
        return iSanPhamRepository.findAll(pageable);
    }

    @Override
    public SanPham save(SanPham sanPham) {
        return iSanPhamRepository.save(sanPham);
    }

    @Override
    public SanPham getOne(UUID id) {
        Optional<SanPham> sanPham =  iSanPhamRepository.findById(id);
        if(sanPham.isPresent()){
            return sanPham.get();
        }else{
            return null;
        }
    }

    @Override
    public List<SanPham> getAll() {
        return iSanPhamRepository.findAll();
    }

    @Override
    public Page<SanPham> sanPhamFilter(SanPhamFilter filter , Pageable pageable) {
        String sapXep = filter.getSapXep();
        return iSanPhamRepository.findAll(new Specification<SanPham>() {
            @Override
            public Predicate toPredicate(Root<SanPham> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                List<Predicate> predicates = new ArrayList<>();
                if(!filter.getSearch().isEmpty() && filter.getSearch() != null){
                    predicates.add(criteriaBuilder.or(criteriaBuilder.equal(root.get("ma") , filter.getSearch()) ,
                            criteriaBuilder.equal(root.get("ten") , filter.getSearch())));
                }
                if(!filter.getDanhMuc().isEmpty()  && filter.getDanhMuc() != null){
                    DanhMuc danhMuc = DanhMuc.builder().id(String.valueOf(filter.getDanhMuc())).build();
                    predicates.add(criteriaBuilder.and(criteriaBuilder.equal(root.get("danhMuc") , danhMuc)));
                }
                if(!filter.getChatLieu().isEmpty() && filter.getChatLieu() != null){
                    ChatLieu chatLieu = ChatLieu.builder().id(UUID.fromString(filter.getChatLieu())).build();
                    predicates.add(criteriaBuilder.and(criteriaBuilder.equal(root.get("chatLieu") , chatLieu)));
                }
                if(!filter.getMauSac().isEmpty() && filter.getMauSac() != null){
                    MauSac mauSac = MauSac.builder().id(UUID.fromString(filter.getMauSac())).build();
                    predicates.add(criteriaBuilder.and(criteriaBuilder.equal(root.join("chiTietSanPhams" , JoinType. LEFT).get("mauSac") ,mauSac)));
                }
                if(!filter.getKichCo().isEmpty() && filter.getKichCo() != null){
                    Size size = Size.builder().id(filter.getKichCo()).build();
                    predicates.add(criteriaBuilder.and(criteriaBuilder.equal(root.join("chiTietSanPhams" , JoinType.LEFT).get("size") ,size)));
                }
                if (!filter.getKieuDang().isEmpty() && filter.getKieuDang() != null) {
                    KieuDang kieuDang = KieuDang.builder().id(UUID.fromString(filter.getKieuDang())).build();
                    predicates.add(criteriaBuilder.and(criteriaBuilder.equal(root.get("kieuDang"), kieuDang)));
                }
                if (filter.getTrangThai() != null) {
                    predicates.add(criteriaBuilder.and(criteriaBuilder.equal(root.get("trangThai"), filter.getTrangThai())));
                }
                if (filter.getGioiTinh() != null) {
                    predicates.add(criteriaBuilder.and(criteriaBuilder.equal(root.get("gioiTinh"), filter.getGioiTinh())));
                }
                if (!sapXep.isEmpty() && sapXep != null && sapXep.equalsIgnoreCase("ngayTao")) {
                    query.orderBy(criteriaBuilder.desc(root.get(filter.getSapXep())));
                } else if (!sapXep.isEmpty() && sapXep != null && sapXep.equalsIgnoreCase("price-asc")) {
                    query.orderBy(criteriaBuilder.asc(root.get("giaBan")));
                } else {
                    query.orderBy(criteriaBuilder.desc(root.get("giaBan")));
                }
                return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
            }
        } , pageable);
    }

    @Override
    public Page<SanPham> getAllByTenOrMa(String value, Integer page) {
        Pageable pageable =  PageRequest.of(page - 1 , 10);
        Page<SanPham> sanPhams = iSanPhamRepository.getAllSanPhamByTenOrMa("%" + value + "%" , pageable);
        return sanPhams;
    }

    @Override
    public List<SanPham> theoTen(UUID chatLieu,UUID kieuDang,String danhMuc ) {
        return iSanPhamRepository.theoTen(chatLieu,kieuDang,danhMuc);
    }

    @Override
    public Page<SanPham> findAllGender(Pageable pageable, boolean gioi_tinh) {
        return iSanPhamRepository.findAllGender(pageable,gioi_tinh);
    }

}
