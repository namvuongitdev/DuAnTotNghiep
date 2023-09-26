package com.example.web.repository;
import com.example.web.model.KhuyenMai;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.UUID;

public interface IKhuyenMaiRepository extends JpaRepository<KhuyenMai , UUID>  , JpaSpecificationExecutor<KhuyenMai> {

    @Override
    <S extends KhuyenMai> S save(S entity);

    @Override
    Page<KhuyenMai> findAll(Pageable pageable);
}
