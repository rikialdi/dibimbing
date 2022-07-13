package com.dibimbing.dibimbing.jdbc.model;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class BarangMapper  implements RowMapper<BarangJdbc> {

    @Override
    public BarangJdbc mapRow(ResultSet rs, int rowNum) throws SQLException {

        BarangJdbc barang = new BarangJdbc();
        barang.setId(rs.getLong("id"));
        barang.setNama(rs.getString("nama"));
        barang.setSatuan(rs.getString("satuan"));
        barang.setStok(rs.getInt("stok"));
        barang.setHarga(rs.getDouble("harga"));
//        barang.setCreatedDate(rs.getTimestamp("created_date").toLocalDateTime());

        return barang;

    }
}
