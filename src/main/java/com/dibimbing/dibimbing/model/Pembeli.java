package com.dibimbing.dibimbing.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@Table(name = "pembeli")
@Where(clause = "deleted_date is null")
public class Pembeli extends AbstractDate implements Serializable {
    //GenerationType.AUTO : nextvall all tabel sequense
    // GenerationType.IDENTITY : nextvall per tabel sequense
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nama", nullable = false, length = 45)
    private String nama;

    //step 1 one to one
    @OneToOne(mappedBy = "pembeli")
    private PembeliDetail pembeliDetail;
}
