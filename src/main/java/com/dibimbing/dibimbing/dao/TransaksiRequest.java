package com.dibimbing.dibimbing.dao;

import lombok.Data;

@Data
public class TransaksiRequest {
    public Long idBarang;
    public Long idPembeli;
    public Integer qty;
    public Double harga;
    public Long id;//id transaksi
}
