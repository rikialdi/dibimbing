package com.dibimbing.dibimbing.mybatis.model;

import com.dibimbing.dibimbing.jdbc.model.BarangJdbc;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;


public class BarangMyBatisMapper  implements RowMapper<BarangMybatis> {

    @Override
    public BarangMybatis mapRow(ResultSet rs, int rowNum) throws SQLException {

        BarangMybatis barang = new BarangMybatis();
        barang.setResid(rs.getLong("resid"));
        barang.setResnama(rs.getString("resnama"));
        barang.setRessatuan(rs.getString("ressatuan"));
        barang.setResharga(rs.getDouble("resharga"));
        barang.setResstok(rs.getInt("resstok"));
        barang.setReserordesc(rs.getString("reserordesc"));
        barang.setReserorcode(rs.getInt("reserorcode"));
        return barang;

    }
}
