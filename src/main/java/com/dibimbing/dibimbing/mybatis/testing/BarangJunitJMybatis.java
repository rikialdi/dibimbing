package com.dibimbing.dibimbing.mybatis.testing;

import com.dibimbing.dibimbing.jdbc.repository.BarangRepoJdbcSP;
import com.dibimbing.dibimbing.jdbc.service.BarangServicePS;
import com.dibimbing.dibimbing.model.Barang;
import com.dibimbing.dibimbing.mybatis.model.BarangMybatis;
import com.dibimbing.dibimbing.mybatis.repository.BarangRepoMybatis;
import com.dibimbing.dibimbing.mybatis.service.BarangServiceMybatis;
import com.dibimbing.dibimbing.utils.QueryPS;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.jdbc.object.GenericStoredProcedure;
import org.springframework.jdbc.object.StoredProcedure;
import org.springframework.test.context.junit4.SpringRunner;

import javax.sql.DataSource;
import java.io.IOException;
import java.sql.Types;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class BarangJunitJMybatis {

    @Autowired
    BarangServiceMybatis barangServiceMybatis;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public BarangRepoMybatis barangRepoMybatis;
    @Autowired
    private DataSource dataSource;
    @Autowired
    QueryPS queryPS;

    @Autowired
    private SqlSessionFactory sqlSessionFactory;

    @Test
    public void getByIdIbatis1() throws IOException {
        SqlSession session = sqlSessionFactory.openSession();
        try {
            jdbcTemplate.execute(queryPS.getByIDSP);

            System.out.println("response  1=" + barangServiceMybatis.selectBlog(2));
        } finally {
            session.close();
        }
    }

    @Test
    public void getListIbatis1() throws IOException {
        SqlSession session = sqlSessionFactory.openSession();
        try {
            jdbcTemplate.execute(queryPS.getBarang1);
            System.out.println("response  1=" + barangServiceMybatis.selectList("%bar%"));
            List<BarangMybatis> list = barangServiceMybatis.selectList("%bar%");
            for (BarangMybatis data : list) {
                System.out.println("data ke ============== ");
                System.out.println("id=" + data.getResid());
                System.out.println("nama=" + data.getResnama());
                System.out.println("satuan=" + data.getRessatuan());
                System.out.println("harga=" + data.getResharga());
                System.out.println("stok=" + data.getResstok());
            }
        } finally {
            session.close();
        }
    }

    @Test
    public void suksesInsertIbatis1() throws IOException {
        SqlSession session = sqlSessionFactory.openSession();
        try {
            barangServiceMybatis.insertProcedure(123.0, "barang 90", "pcs", 12, 0);
            System.out.println("resId =");
        } finally {
            session.close();
        }
    }

    @Test
    public void suksesUpdateIbatis1() throws IOException {
        SqlSession session = sqlSessionFactory.openSession();
        try {
            jdbcTemplate.execute(queryPS.updatebarang);
            barangServiceMybatis.updateProcedure(123.0, "barang 90666 updated", "pcs", 12, 3);
            System.out.println("resId =");
        } finally {
            session.close();
        }
    }



    @Test
    public void suksesTestTypeData() throws IOException {
        SqlSession session = sqlSessionFactory.openSession();
        try {
            jdbcTemplate.execute(queryPS.testLong);
            Map<String , Object> map = new HashMap<>();
            map.put("rqid",23);
            map.put("resid",null);
            barangRepoMybatis.testLONG(map);
            System.out.println("out Long="+(Long) map.get("resid"));

            jdbcTemplate.execute(queryPS.testDouble);
            Map<String , Object> map2= new HashMap<>();
            map2.put("rqid",56.0);
            map2.put("resid",null);
            barangRepoMybatis.testDOUBLE(map2);
            System.out.println("out double ="+(Double) map2.get("resid"));

            jdbcTemplate.execute(queryPS.testInteger);
            Map<String , Object> map3= new HashMap<>();
            map3.put("rqid",90);
            map3.put("resid",null);
            barangRepoMybatis.testINTEGER(map3);
            System.out.println("out integer="+(Integer) map3.get("resid"));
        } finally {
            session.close();
        }
    }

    @Test
    public void suksesUpdatebarangoutwitheror() throws IOException {
        SqlSession session = sqlSessionFactory.openSession();
        try {
            jdbcTemplate.execute(queryPS.updatebarangoutwitheror);
            Map obj =   barangServiceMybatis.updatebarangoutwitheror(123.0, "barang 90666 updated", "pcs", 12, 3);
            System.out.println("resId ="+obj);
        } finally {
            session.close();
        }
    }

    @Test
    public void suksesSavebarangoutwitheror() throws IOException {
        SqlSession session = sqlSessionFactory.openSession();
        try {
            jdbcTemplate.execute(queryPS.updatebarangoutwitheror);
            Map obj =   barangServiceMybatis.savebarangoutwitheror(123.0, "barang baru89", "pcs", 12,1 );
            System.out.println("resId ="+obj);
        } finally {
            session.close();
        }
    }


    @Test
    public void suksesdeleteIbatis1() throws IOException {
        SqlSession session = sqlSessionFactory.openSession();
        try {
            jdbcTemplate.execute(queryPS.deletebarang);
            barangServiceMybatis.deleteProcedure(17);
            System.out.println("resId =");
        } finally {
            session.close();
        }
    }


}
