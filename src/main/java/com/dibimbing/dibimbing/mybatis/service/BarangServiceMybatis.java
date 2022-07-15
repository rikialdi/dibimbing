package com.dibimbing.dibimbing.mybatis.service;

import com.dibimbing.dibimbing.model.Barang;
import com.dibimbing.dibimbing.mybatis.model.BarangMybatis;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


public interface BarangServiceMybatis {

    BarangMybatis selectBlog(int rqid);

    List<BarangMybatis> selectList(String rqnama);

    void insertProcedure(Double rqharga, String rqnama, String rqsatuan, int rqstok, int resid);

    void updateProcedure(Double rqharga, String rqnama, String rqsatuan, int rqstok, int resid);

    void deleteProcedure(int resid);

    Map updatebarangoutwitheror(Double rqharga, String rqnama, String rqsatuan, int rqstok, int resid);

    Map savebarangoutwitheror(Double rqharga, String rqnama, String rqsatuan, int rqstok, Integer resid);


//    use xml
   public Map updateProcedureXML(BarangMybatis item);
    List<BarangMybatis> listBarangXML(BarangMybatis item);
    BarangMybatis getByIdBarangXML(Integer id);

    public Map testPS(BarangMybatis item);


}
