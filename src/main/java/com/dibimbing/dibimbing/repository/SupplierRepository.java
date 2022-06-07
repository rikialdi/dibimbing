package com.dibimbing.dibimbing.repository;

import com.dibimbing.dibimbing.model.Supplier;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface SupplierRepository extends PagingAndSortingRepository<Supplier, Long> {

    @Query("select c from Supplier c WHERE c.id = :id")
    public Supplier getbyID(@Param("id") Long id);
}