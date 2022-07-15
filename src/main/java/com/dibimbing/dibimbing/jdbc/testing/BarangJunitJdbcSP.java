package com.dibimbing.dibimbing.jdbc.testing;

import com.dibimbing.dibimbing.jdbc.model.BarangJdbc;
import com.dibimbing.dibimbing.jdbc.repository.BarangRepoJdbc;
import com.dibimbing.dibimbing.jdbc.repository.BarangRepoJdbcSP;
import com.dibimbing.dibimbing.jdbc.service.BarangServicePS;
import com.dibimbing.dibimbing.model.Barang;
import com.dibimbing.dibimbing.utils.QueryPS;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.jdbc.object.GenericStoredProcedure;
import org.springframework.jdbc.object.StoredProcedure;
import org.springframework.test.context.junit4.SpringRunner;

import javax.sql.DataSource;
import java.math.BigDecimal;
import java.sql.Types;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class BarangJunitJdbcSP {

    @Autowired
    BarangRepoJdbcSP barangRepoJdbc;

    @Autowired
    BarangServicePS barangServicePS;
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private DataSource dataSource;

    @Autowired
    QueryPS queryPS;

    @Test
    public void suksesFindById() {

        //create function
        jdbcTemplate.execute(queryPS.getByIDSP);

        Map map = barangServicePS.getByIDSP(1L);
        if (map.get("status").equals("200")) {
            System.out.println(map);
        } else {
            System.out.println(map.get("message"));
        }
        /*
        response
        {data=BarangJdbc(id=1, nama=nama 2, satuan=kg, harga=100000.0, stok=200), message=sukses, status=200}
         */
    }

    @Test
    public void test() { //eror
        //create SP
        jdbcTemplate.execute(queryPS.testSP);
        jdbcTemplate.setResultsMapCaseInsensitive(false);
        StoredProcedure procedure = new GenericStoredProcedure();
        procedure.setDataSource(dataSource);
        procedure.setSql("testsp");
        procedure.setFunction(false);

        SqlParameter[] parameters = {
                new SqlParameter("rqid", Types.VARCHAR),
                new SqlOutParameter("resid", Types.VARCHAR)
        };

        procedure.setParameters(parameters);
        procedure.compile();

        Map<String, Object> result = procedure.execute("445");
        System.out.println("nilai saya=" + (String) result.get("resid"));
    }

    @Test
    public void test2() {//eror
        //create SP
        jdbcTemplate.execute(queryPS.testSP);
        jdbcTemplate.setResultsMapCaseInsensitive(false);
        SimpleJdbcCall simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate).withProcedureName("testsp");
        Map<String, Object> execute = simpleJdbcCall.execute(new MapSqlParameterSource("rqid","123"),
                new MapSqlParameterSource("resid",null));

        Map<String, Object> simpleJdbcCallResult = simpleJdbcCall.execute(execute);
        System.out.println(simpleJdbcCallResult);

        System.out.println("nilai saya=" + (String) simpleJdbcCallResult.get("resid"));
    }

//    @Test
//    public void searchWeakUsers() {
//        SimpleJdbcCall call = new SimpleJdbcCall(jdbcTemplate)
//                .withProcedureName("getbyidfunction")
//                . declareParameters(
//                        new SqlParameter("rqid", Types.BIGINT),
//                        new SqlOutParameter("status_out", Types.BOOLEAN));
//
//        Map<String, Object> execute = call.execute(new MapSqlParameterSource("peron_id_in", person.getId()));
//
//        Map<String, Object> ok = new HashMap<>();
//        SimpleJdbcCall jdbcCall = new SimpleJdbcCall(dataSource).withFunctionName("");
//
//        SqlParameterSource in = new MapSqlParameterSource()
//                .addValue("rqid", 1) ;
//        Map<String, Object> out = jdbcCall.execute(in);
//
//        System.out.println((Long) out.get("resid"));
//        System.out.println((Double) out.get("resharga"));
//        System.out.println((String) out.get("resnama"));
//        System.out.println((String) out.get("ressatuan"));
//        System.out.println((Integer) out.get("resstok"));

//        SimpleJdbcCall simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate)
//                .withProcedureName("savebarangout")
//                .declareParameters(
//                        new SqlParameter("rqharga", Types.DOUBLE),
//                        new SqlParameter("rqnama", Types.VARCHAR),
//                        new SqlParameter("rqsatuan", Types.VARCHAR),
//                        new SqlParameter("rqstok", Types.INTEGER),
//                        new SqlParameter("resid", Types.INTEGER),
//                        new SqlOutParameter("resid", Types.INTEGER),
//                        new SqlOutParameter("resharga", Types.DOUBLE) ,
//                        new SqlOutParameter("resnama", Types.VARCHAR) ,
//                        new SqlOutParameter("ressatuan", Types.VARCHAR) ,
//                        new SqlOutParameter("resstok", Types.INTEGER));


//                .addValue("rqnama", "123")
//                .addValue("rqsatuan", "123")
//                .addValue("rqstok", 234)
//                .addValue("resid", null)
//                .addValue("resharga", null)
//                .addValue("resnama", null)
//                .addValue("ressatuan", null)
//                .addValue("resstok", null)
//    }


}
