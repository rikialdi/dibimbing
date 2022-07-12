package com.dibimbing.dibimbing.model
        ;

import lombok.Data;
import net.minidev.json.annotate.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Data
@Entity
@Table(name = "supplier")
public class Supplier implements Serializable {
    //GenerationType.AUTO : nextvall all tabel sequense
    // GenerationType.IDENTITY : nextvall per tabel sequense
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nama", nullable = false, length = 45)
    private String nama;

    @Column(name = "hp", nullable = false, length = 15)
    private String hp;

    @Column(name = "alamat", columnDefinition = "TEXT")
    private String alamat;

    @JsonIgnore
    @OneToMany(targetEntity=Barang.class)
    private List<Barang> barang;

}

