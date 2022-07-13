package com.dibimbing.dibimbing.jdbc.repository;

import com.dibimbing.dibimbing.jdbc.model.BarangJdbc;
import com.dibimbing.dibimbing.jdbc.model.BarangMapper;
import com.dibimbing.dibimbing.model.Barang;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Repository
public class BarangRepoJdbc {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public int save(Barang barang) {
        return jdbcTemplate.update(
                "insert into barang (created_date,updated_date,harga,nama,satuan,stok)\n" +
                        " values (?, ?, ?, ?, ?, ?)",
                LocalDateTime.now(), LocalDateTime.now(), barang.getHarga(),barang.getNama(),barang.getSatuan(),barang.getStok());
    }

    public int update(Barang barang) {
        return jdbcTemplate.update(
                "update barang set harga =?,  nama=?, satuan=?, stok=? where id =?",
                barang.getHarga(), barang.getNama(), barang.getSatuan(),barang.getStok(),barang.getId());
    }

    public List<BarangJdbc> findAll() {

        String sql = "SELECT * FROM barang";

        List<BarangJdbc> objbarang = new ArrayList<>();

        List<Map<String, Object>> rows = jdbcTemplate.queryForList(sql);

        for (Map row : rows) {
            BarangJdbc obj = new BarangJdbc();
            obj.setId(((Long) row.get("id")).longValue());
            obj.setNama((String) row.get("nama"));
            obj.setSatuan((String) row.get("satuan"));
            obj.setStok(((Integer) row.get("stok")));
            obj.setHarga(((Double) row.get("harga")));
//            obj.setCreatedDate(((Timestamp) row.get("CREATED_DATE")).toLocalDateTime());
            objbarang.add(obj);
        }

        return objbarang;
    }

    public List<BarangJdbc> findAll2() {

        String sql = "SELECT * FROM barang";

        List<BarangJdbc> customers = jdbcTemplate.query(
                sql,
                new BarangMapper());

        return customers;
    }

    public List<BarangJdbc> findAll3() {
        String sql = "SELECT id,nama,harga,satuan,stok FROM barang";
        List<BarangJdbc> obj = jdbcTemplate.query(
                sql,
                new BeanPropertyRowMapper(BarangJdbc.class));
        return obj;
    }

    public BarangJdbc findByBarangId(Long id) {

        String sql = "SELECT * FROM barang WHERE ID =?";

        return jdbcTemplate.queryForObject(sql, new Object[]{id}, new BarangMapper());

    }

    public BarangJdbc findByBarangId2(Long id) {

        String sql = "SELECT * FROM barang WHERE ID =?";

        return (BarangJdbc) jdbcTemplate.queryForObject(sql, new Object[]{id}, new BeanPropertyRowMapper(BarangJdbc.class));

    }

    public int delete(Long id) {
        return jdbcTemplate.update(
                "delete from barang where id =?",
                id);
    }
}
