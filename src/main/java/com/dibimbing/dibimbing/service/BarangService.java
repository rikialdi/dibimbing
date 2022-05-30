package com.dibimbing.dibimbing.service;

import com.dibimbing.dibimbing.model.Barang;

import java.util.List;
import java.util.Map;

public interface BarangService {
    public Map insert(Barang barang);

    public Map update(Barang barang);

    public Map delete(Long barang);

    public Map getAll(int size, int page);

}
