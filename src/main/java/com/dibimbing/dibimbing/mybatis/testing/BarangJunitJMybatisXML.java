package com.dibimbing.dibimbing.mybatis.testing;

import com.dibimbing.dibimbing.mybatis.model.BarangMybatis;
import com.dibimbing.dibimbing.mybatis.service.BarangServiceMybatis;
import com.dibimbing.dibimbing.utils.QueryPS;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.mapping.StatementType;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import javax.sql.DataSource;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class BarangJunitJMybatisXML {

    @Autowired
    BarangServiceMybatis barangServiceMybatis;

    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private DataSource dataSource;
    @Autowired
    QueryPS queryPS;

    @Autowired
    private SqlSessionFactory sqlSessionFactory;

    @Test
    public void suksesDataList() throws IOException {

        BarangMybatis obj = new BarangMybatis();
        obj.setResnama("");
        List<BarangMybatis> list = barangServiceMybatis.listBarangXML(obj);
        for (BarangMybatis data : list) {
            System.out.println("data 90= ============== ");
            System.out.println("id=" + data.getResid());
            System.out.println("nama=" + data.getResnama());
            System.out.println("satuan=" + data.getRessatuan());
            System.out.println("harga=" + data.getResharga());
            System.out.println("stok=" + data.getResstok());
        }

    }

    @Test
    public void suksesDataByID() throws IOException {
        BarangMybatis data = barangServiceMybatis.getByIdBarangXML(3);

        System.out.println("data 90= ============== ");
        System.out.println("id=" + data.getResid());
        System.out.println("nama=" + data.getResnama());
        System.out.println("satuan=" + data.getRessatuan());
        System.out.println("harga=" + data.getResharga());
        System.out.println("stok=" + data.getResstok());
    }

    @Test
    public void updateProcedureXML() throws IOException {

        try {
            BarangMybatis obj = new BarangMybatis();
            obj.setResid(3L);
            obj.setRessatuan("pcs");
            obj.setResstok(123);
            obj.setResnama("aplikasi");
            obj.setResharga(123.4);
            Map out = barangServiceMybatis.updateProcedureXML(obj);
            String resid = out.get("resid").toString();
            System.out.println(resid);
        } finally {

        }
    }

    @Test
    public void testps() throws IOException {
//            BarangMybatis obj = new BarangMybatis();
//            Map out = barangServiceMybatis.testPS(obj);
//            String resid = out.get("resid").toString();
//            System.out.println(resid);


    }



}
