package com.example.web.service.impl;
import com.example.web.model.CTSPKhuyenMai;
import com.example.web.model.KhuyenMai;
import com.example.web.repository.ICTSPKhuyenMai;
import com.example.web.repository.IKhuyenMaiRepository;
import com.example.web.response.FilterKhuyenMai;
import com.example.web.service.IKhuyenMaiService;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
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

@Service
public class KhuyenMaiSerivceImpl implements IKhuyenMaiService {

    @Autowired
    private IKhuyenMaiRepository repository;

    @Autowired
    private ICTSPKhuyenMai ictspKhuyenMai;

    @Override
    public String addKhuyenMai(KhuyenMai khuyenMai ) {
       KhuyenMai km = repository.save(khuyenMai);
        khuyenMai.getChiTietSanPhams().forEach(o -> {
            CTSPKhuyenMai ctspKhuyenMai = CTSPKhuyenMai.builder()
                    .khuyenMai(km)
                    .chiTietSanPham(o)
                    .build();
            ictspKhuyenMai.save(ctspKhuyenMai);
        });
        return "redirect:/admin/khuyen-mai/";
    }

    @Override
    public Page<KhuyenMai> getAll(Integer page) {
        if(page < 0){
            return null;
        }else{
            Pageable pageable = PageRequest.of(page - 1 , 10);
            Page<KhuyenMai> pageKhuyenMai = repository.findAll(pageable);
            return pageKhuyenMai;
        }
    }

    @Override
    public Page<KhuyenMai> filterKhuyenMai(Integer page ,FilterKhuyenMai filter) {
      Pageable pageable = PageRequest.of(page - 1 , 10);
        return repository.findAll(new Specification<KhuyenMai>() {
            @Override
            public Predicate toPredicate(Root<KhuyenMai> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
             List<Predicate> predicateList = new ArrayList<>();
              if(!filter.getSearch().isEmpty() && filter.getSearch() != null){
                   predicateList.add(criteriaBuilder.and(criteriaBuilder.equal(root.get("ten") , filter.getSearch()) ,
                           criteriaBuilder.equal(root.get("ma") , filter.getSearch())));
              }
              if(!filter.getTrangThai().isEmpty() && filter.getTrangThai() != null){
                   predicateList.add(criteriaBuilder.and(criteriaBuilder.equal(root.get("trangThai") , filter.getTrangThai())));
              }
              if(!filter.getLoaiGiamGia().isEmpty() && filter.getLoaiGiamGia() != null){
                  predicateList.add(criteriaBuilder.and(criteriaBuilder.equal(root.get("loaiGiamGia") , Boolean.parseBoolean(filter.getLoaiGiamGia()))));

              }
                return criteriaBuilder.and(predicateList.toArray(new Predicate[predicateList.size()]));
            }
        } , pageable);
    }
}
