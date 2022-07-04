package com.dibimbing.dibimbing.model;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

@Service
public class TestBeans {

    @Bean
    public String simpanBarang(){
        return "Simpan Barang Berhasil";
    }

    @Bean
    public String updateBarang(){
        return "Update Barang Berhasil";
    }
}
