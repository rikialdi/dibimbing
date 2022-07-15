package com.dibimbing.dibimbing.mybatis.service.impl;

import com.dibimbing.dibimbing.model.Barang;
import com.dibimbing.dibimbing.mybatis.model.BarangMyBatisMapper;
import com.dibimbing.dibimbing.mybatis.model.BarangMybatis;
import com.dibimbing.dibimbing.mybatis.repository.BarangRepoMybatis;
import com.dibimbing.dibimbing.mybatis.repository.BarangRepoMybatisXML;
import com.dibimbing.dibimbing.mybatis.service.BarangServiceMybatis;
import com.dibimbing.dibimbing.utils.QueryPS;
import com.dibimbing.dibimbing.utils.TemplateResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class BarangServiceImplMybatis implements BarangServiceMybatis {

    @Autowired
    BarangRepoMybatis barangRepoMybatis;
    @Autowired
    public TemplateResponse templateResponse;
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    QueryPS queryPS;
    @Autowired
    public BarangRepoMybatisXML barangRepoMybatisXML;
    @Override
    public BarangMybatis selectBlog(int rqid) {
        return barangRepoMybatis.selectBlog(rqid);
    }

    @Override
    public List<BarangMybatis> selectList(String rqnama) {
        return barangRepoMybatis.selectList(rqnama);
    }

    @Override
    public void insertProcedure(Double rqharga, String rqnama, String rqsatuan, int rqstok, int resid) {
         barangRepoMybatis.insertProcedure( rqharga,  rqnama,  rqsatuan,  rqstok,  resid);
    }

    @Override
    public void updateProcedure(Double rqharga, String rqnama, String rqsatuan, int rqstok, int resid) {
         barangRepoMybatis.updateProcedure( rqharga,  rqnama,  rqsatuan,  rqstok,  resid);

    }

    @Override
    public void deleteProcedure(int resid) {
        barangRepoMybatis.deleteProcedure( resid);
    }

    @Override
    public Map updatebarangoutwitheror(Double rqharga, String rqnama, String rqsatuan, int rqstok, int resid) {
//       barangRepoMybatis.updatebarangoutwitheror( rqharga,  rqnama,  rqsatuan,  rqstok,  resid,
//                 1.0,"","",1,"",1);
        Map<String , Object> map = new HashMap<>();
        map.put("rqharga",rqharga);
        map.put("rqnama",rqnama);
        map.put("rqsatuan",rqsatuan);
        map.put("rqstok",rqstok);
        map.put("resid",resid);
        map.put("resharga",null);
        map.put("resnama", null);
        map.put("ressatuan",null);
        map.put("resstok",null);
        map.put("reserordesc",null);
        map.put("reserorcode",null);
//      penting dibaca type data mybatis  https://mybatis.org/mybatis-3/apidocs/reference/org/apache/ibatis/type/JdbcType.html
        barangRepoMybatis.updatebarangoutwitheror2(map);
        System.out.println("resid="+(Integer) map.get("resid"));
        System.out.println("resharga="+(Double) map.get("resharga"));
        System.out.println("resnama="+(String) map.get("resnama"));
        System.out.println("ressatuan="+(String) map.get("ressatuan"));
        System.out.println("resstok="+(Integer) map.get("resstok"));
        System.out.println("reserordesc="+(String) map.get("reserordesc"));
        System.out.println("reserorcode="+(Integer) map.get("reserorcode"));


        Barang objBarang = new Barang();
        objBarang.setId(Long.parseLong(String.valueOf((Integer) map.get("resid"))));
        objBarang.setSatuan((String) map.get("ressatuan"));
        objBarang.setStok((Integer) map.get("resstok"));
        objBarang.setHarga((Double) map.get("resharga"));
        objBarang.setNama((String) map.get("resnama"));

    return     templateResponse.templateSukses(objBarang,(String) map.get("reserordesc"), String.valueOf((Integer) map.get("reserorcode")));

    }

    @Override
    public Map savebarangoutwitheror(Double rqharga, String rqnama, String rqsatuan, int rqstok, Integer resid) {
        jdbcTemplate.execute(queryPS.save);

        Map<String , Object> map = new HashMap<>();
        map.put("rqharga",rqharga);
        map.put("rqnama",rqnama);
        map.put("rqsatuan",rqsatuan);
        map.put("rqstok",rqstok);
        map.put("resid",(resid == null ? 0: resid));
        map.put("resharga",null);
        map.put("resnama", null);
        map.put("ressatuan",null);
        map.put("resstok",null);
        map.put("reserordesc",null);
        map.put("reserorcode",null);
//      penting dibaca type data mybatis  https://mybatis.org/mybatis-3/apidocs/reference/org/apache/ibatis/type/JdbcType.html
        barangRepoMybatis.savebarangoutwitheror(map);
        System.out.println("resid="+(Integer) map.get("resid"));
        System.out.println("resharga="+(Double) map.get("resharga"));
        System.out.println("resnama="+(String) map.get("resnama"));
        System.out.println("ressatuan="+(String) map.get("ressatuan"));
        System.out.println("resstok="+(Integer) map.get("resstok"));
        System.out.println("reserordesc="+(String) map.get("reserordesc"));
        System.out.println("reserorcode="+(Integer) map.get("reserorcode"));


        Barang objBarang = new Barang();
        objBarang.setId(Long.parseLong(String.valueOf((Integer) map.get("resid"))));
        objBarang.setSatuan((String) map.get("ressatuan"));
        objBarang.setStok((Integer) map.get("resstok"));
        objBarang.setHarga((Double) map.get("resharga"));
        objBarang.setNama((String) map.get("resnama"));
        return     templateResponse.templateSukses(objBarang,(String) map.get("reserordesc"), String.valueOf((Integer) map.get("reserorcode")));

    }

    @Override
    public Map updateProcedureXML(BarangMybatis item) {
        Map map = new HashMap();
        map.put("rqharga", item.getResharga());
        map.put("rqnama", item.getResnama());
        map.put("rqsatuan", item.getRessatuan());
        map.put("rqstok",item.getResstok());
        map.put("resid", item.getResid());
        showLog(map, "Insert Header");
        return barangRepoMybatisXML.updatebarang(map);
    }

    @Override
    public List<BarangMybatis> listBarangXML(BarangMybatis item) {
        Map map = new HashMap<>();
        map.put("rqnama", "%"+item.getResnama()+"%");
        showLog(map, "Insert Header");
        return barangRepoMybatisXML.listDataXML(map);
    }

    @Override
    public BarangMybatis getByIdBarangXML(Integer id) {
        Map map = new HashMap<>();
        map.put("rqid", id);
        showLog(map, " Header");
        return barangRepoMybatisXML.getByIdBarangXML(map);
    }

    @Override
    public Map testPS(BarangMybatis item) {
        Map map = new HashMap();
        map.put("rqid", "3343");
        map.put("resid",null);
        showLog(map, " Header");
        return barangRepoMybatisXML.testps(map);
    }


    public void showLog(Map<String, Object> map, String title) {
        System.out.println("Process : " + title);
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            String key = entry.getKey();
            Object value = entry.getValue();
            System.out.println(key + " : " + value);
        }
        System.out.println("========================");
    }

}
