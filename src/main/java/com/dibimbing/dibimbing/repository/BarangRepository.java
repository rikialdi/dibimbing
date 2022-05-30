package com.dibimbing.dibimbing.repository;

import com.dibimbing.dibimbing.model.Barang;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface BarangRepository extends PagingAndSortingRepository<Barang, Long> {
    @Query("select c from Barang c")// nama class
    public Page<Barang> getAllData(Pageable pageable);

    @Query("select c from Barang c WHERE c.id = :idbarang")
    public Barang getbyID(@Param("idbarang") Long idbebas);
}
