package com.dibimbing.dibimbing.service;


import com.dibimbing.dibimbing.dao.TransaksiRequest;

import java.util.Map;

public interface TransaksiService {
    /*
    a. simpan transaksi
    post : /api/transaksi
    b. update transaksi
    put : /api/transaksi
    c. list transaksi : dalam satu API
 	- filter by nama barang
	- filter by nama pembeli
	- filter by tanggal pembelian barang
    get : /api/transaksi
    d. delete soft transaksi
    delete : /api/transaksi
     */

    public Map simpan(TransaksiRequest obj);

    public Map update(TransaksiRequest obj);

    public Map delete(Long obj);

    // list : controller
}
