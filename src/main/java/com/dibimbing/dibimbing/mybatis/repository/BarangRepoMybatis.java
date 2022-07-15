package com.dibimbing.dibimbing.mybatis.repository;

import com.dibimbing.dibimbing.mybatis.model.BarangMyBatisMapper;
import com.dibimbing.dibimbing.mybatis.model.BarangMybatis;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.StatementType;

import java.util.List;
import java.util.Map;

@Mapper//https://github.com/mybatis/spring-boot-starter
public interface BarangRepoMybatis {
    @Select("SELECT resid,  resharga, resnama,  ressatuan, resstok FROM getByIDFunction(#{rqid});")
    BarangMybatis selectBlog(int rqid);

    @Select("select resid,resharga, resnama,  ressatuan,resstok from public.getBarang1(#{rqnama});")
    List<BarangMybatis> selectList(String rqnama);

    @Insert("call savebarang(#{rqharga,mode=IN},#{rqnama,mode=IN},#{rqsatuan,mode=IN},#{rqstok,mode=IN},#{resid,mode=INOUT})")
    void insertProcedure(Double rqharga, String rqnama, String rqsatuan, int rqstok, int resid);

    @Update("call updatebarang(#{rqharga,mode=IN},#{rqnama,mode=IN},#{rqsatuan,mode=IN},#{rqstok,mode=IN},#{resid,mode=INOUT})")
    void updateProcedure(Double rqharga, String rqnama, String rqsatuan, int rqstok, int resid);

    @Update(value = "call updatebarangoutwitheror(#{rqharga,mode=IN}," +
            "#{rqnama,mode=IN}," +
            "#{rqsatuan,mode=IN}," +
            "#{rqstok,mode=IN}," +
            "#{resid,mode=INOUT}," +
            "#{resharga,mode=INOUT}," +
            "#{resnama,mode=INOUT}," +
            "#{ressatuan,mode=INOUT}," +
            "#{resstok,mode=INOUT}," +
            "#{reserordesc,mode=INOUT}," +
            "#{reserorcode,mode=INOUT})")
    void updatebarangoutwitheror(Double rqharga,
                                          String rqnama,
                                          String rqsatuan,
                                          int rqstok,
                                          int resid,
                                          Double resharga,
                                          String resnama,
                                          String ressatuan,
                                          Integer resstok,
                                          String reserordesc,
                                          Integer reserorcode);

    @Update(value = "call updatebarangoutwitheror(#{rqharga,mode=IN,jdbcType=NUMERIC}," +
            "#{rqnama,mode=IN,jdbcType=VARCHAR}," +
            "#{rqsatuan,mode=IN,jdbcType=VARCHAR}," +
            "#{rqstok,mode=IN,jdbcType=INTEGER}," +
            "#{resid,mode=INOUT,jdbcType=INTEGER}," +
            "#{resharga,mode=INOUT,jdbcType=DOUBLE}," +
            "#{resnama,mode=INOUT,jdbcType=VARCHAR}," +
            "#{ressatuan,mode=INOUT,jdbcType=VARCHAR}," +
            "#{resstok,mode=INOUT,jdbcType=INTEGER}," +
            "#{reserordesc,mode=INOUT,jdbcType=VARCHAR}," +
            "#{reserorcode,mode=INOUT,jdbcType=INTEGER})")
    @Options(statementType = StatementType.CALLABLE)
    void updatebarangoutwitheror2(Map<String,Object> map);

    @Update(value = "call savebarangoutwitheror(#{rqharga,mode=IN,jdbcType=NUMERIC}," +
            "#{rqnama,mode=IN,jdbcType=VARCHAR}," +
            "#{rqsatuan,mode=IN,jdbcType=VARCHAR}," +
            "#{rqstok,mode=IN,jdbcType=INTEGER}," +
            "#{resid,mode=INOUT,jdbcType=INTEGER}," +
            "#{resharga,mode=INOUT,jdbcType=DOUBLE}," +
            "#{resnama,mode=INOUT,jdbcType=VARCHAR}," +
            "#{ressatuan,mode=INOUT,jdbcType=VARCHAR}," +
            "#{resstok,mode=INOUT,jdbcType=INTEGER}," +
            "#{reserordesc,mode=INOUT,jdbcType=VARCHAR}," +
            "#{reserorcode,mode=INOUT,jdbcType=INTEGER})")
    @Options(statementType = StatementType.CALLABLE)
    void savebarangoutwitheror(Map<String,Object> map);

    @Update(value = "call testsp(#{rqid,mode=IN,jdbcType=VARCHAR}," +
            "#{resid,mode=OUT,jdbcType=VARCHAR})")
    @Options(statementType = StatementType.CALLABLE)
    void test(Map<String,Object> map);

    @Update(value = "call testsplong(#{rqid,mode=IN,jdbcType=BIGINT}," +
            "#{resid,mode=INOUT,jdbcType=BIGINT})")
    @Options(statementType = StatementType.CALLABLE)
    void testLONG(Map<String,Object> map);

    @Update(value = "call testspdouble(#{rqid,mode=IN,jdbcType=DOUBLE}," +
            "#{resid,mode=INOUT,jdbcType=DOUBLE})")
    @Options(statementType = StatementType.CALLABLE)
    void testDOUBLE(Map<String,Object> map);

    @Update(value = "call testspInteger(#{rqid,mode=IN,jdbcType=INTEGER}," +
            "#{resid,mode=INOUT,jdbcType=INTEGER})")
    @Options(statementType = StatementType.CALLABLE)
    void testINTEGER(Map<String,Object> map);
    @Update("call deletebarang(#{rqid,mode=IN})")
    void deleteProcedure(int resid);

}