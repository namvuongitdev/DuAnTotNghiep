package com.example.web.repository;

import com.example.web.model.Size;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface SizeRepository extends JpaRepository<Size, String> {
    @Query(value = "Select * from Size where id=?1", nativeQuery = true)
    Size getOne1(UUID id);

    @Query("select n from Size n where n.trangThai = 1")
    List<Size> getAll1();
}
