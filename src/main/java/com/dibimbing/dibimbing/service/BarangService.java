package com.dibimbing.dibimbing.service;

import com.dibimbing.dibimbing.model.Barang;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Map;

public interface BarangService {
    public Map insert(Barang barang);

    public Map update(Barang barang);

    public Map delete(Long barang);

    public Map getAll(int size, int page);

    public Map findByNama(String nama, Integer page, Integer size);

    Page<Barang> findByNamaLike(String nama, Pageable pageable);

    public Map insert(Barang barang, Long idsupplier);

    public Map update(Barang barang, Long idsupplier);

}
