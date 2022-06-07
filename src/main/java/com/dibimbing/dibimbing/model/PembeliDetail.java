package com.dibimbing.dibimbing.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@Table(name = "pembelidetail")
public class PembeliDetail implements Serializable {
    //GenerationType.AUTO : nextvall all tabel sequense
    // GenerationType.IDENTITY : nextvall per tabel sequense
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "hp", length = 15)
    private String hp;

    @Column(name = "jk", length = 15)
    private String jk;

    @Column(name = "alamat", columnDefinition = "TEXT")
    private String alamat;

    // step one one : FK
    @JsonIgnore
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_pembeli", referencedColumnName = "id")
    private Pembeli pembeli;

}

