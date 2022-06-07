package com.dibimbing.dibimbing.model;

import lombok.Data;

import org.hibernate.annotations.Where;

import javax.persistence.Entity;
import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
@Data
@Entity
@Table(name = "barang")
@Where(clause = "deleted_date is null")
public class Barang  extends  AbstractDate implements Serializable {
    //GenerationType.AUTO : nextvall all tabel sequense
    // GenerationType.IDENTITY : nextvall per tabel sequense
    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nama", nullable = false, length = 45)
    private String nama;

    @Column(name = "stok", nullable = false, length = 11)
    private int stok;

    @Column(name = "satuan", nullable = false, length = 45)
    private String satuan;

    @Column(name = "harga", nullable = false, length = 11)
    private Double harga;

    // wajib
    @ManyToOne(targetEntity = Supplier.class, cascade = CascadeType.ALL)
    private Supplier supplier;//ok supplier_id
}

