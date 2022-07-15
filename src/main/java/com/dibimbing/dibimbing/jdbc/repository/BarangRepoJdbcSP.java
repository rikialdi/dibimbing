package com.dibimbing.dibimbing.jdbc.repository;

import com.dibimbing.dibimbing.jdbc.model.BarangJdbc;
import com.dibimbing.dibimbing.jdbc.model.BarangMapper;
import com.dibimbing.dibimbing.jdbc.service.BarangServicePS;
import com.dibimbing.dibimbing.model.Barang;
import com.dibimbing.dibimbing.utils.TemplateResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.CallableStatement;
import java.sql.Types;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Repository
public class BarangRepoJdbcSP implements BarangServicePS {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private DataSource dataSource;
    @Autowired
    public TemplateResponse templateResponse;

    @Override
    public Map getByIDSP(Long id) {
        try {

            SimpleJdbcCall jdbcCall = new SimpleJdbcCall(dataSource).withFunctionName("getbyidfunction");

            SqlParameterSource in = new MapSqlParameterSource()
                    .addValue("rqid", id);

            Map<String, Object> out = jdbcCall.execute(in);
            BarangJdbc objBarang = new BarangJdbc();
            objBarang.setId((Long) out.get("resid"));
            objBarang.setNama((String) out.get("resnama"));
            objBarang.setSatuan((String) out.get("ressatuan"));
            objBarang.setStok((Integer) out.get("resstok"));
            objBarang.setHarga((Double) out.get("resharga"));

           return templateResponse.templateSukses(objBarang);

        } catch (Exception e) {
            return  templateResponse.templateEror(e);
        }
    }
}
