package com.dibimbing.dibimbing.jdbc.model;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class BarangMapperSP implements RowMapper<BarangJdbc> {

    @Override
    public BarangJdbc mapRow(ResultSet rs, int rowNum) throws SQLException {

        BarangJdbc barang = new BarangJdbc();
        barang.setId(rs.getLong("resid"));
        barang.setNama(rs.getString("resnama"));
        barang.setSatuan(rs.getString("ressatuan"));
        barang.setStok(rs.getInt("resstok"));
        barang.setHarga(rs.getDouble("resharga"));
//        barang.setCreatedDate(rs.getTimestamp("created_date").toLocalDateTime());

        return barang;

    }
}
