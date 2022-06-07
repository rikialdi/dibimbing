package com.dibimbing.dibimbing.repository;

import com.dibimbing.dibimbing.model.PembeliDetail;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PembeliDetailRepository extends
        PagingAndSortingRepository<PembeliDetail, Long> {

    @Query("select c from PembeliDetail c WHERE c.id = :id")
    public PembeliDetail getbyID(@Param("id") Long id);
}