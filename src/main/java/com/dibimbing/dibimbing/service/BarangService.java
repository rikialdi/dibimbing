package com.dibimbing.dibimbing.service;

import com.dibimbing.dibimbing.model.Barang;

import java.util.List;

public interface BarangService {
    public Barang save(Barang obj);
    public Barang update(Barang obj);
    public List<Barang> deleted(Long id);
    public List<Barang> dataMhs(int row,int page);
    public Barang findById(long obj);
}
