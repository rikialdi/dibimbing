package com.dibimbing.dibimbing.mybatis.repository;

import com.dibimbing.dibimbing.mybatis.model.BarangMybatis;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
@Component
public class BarangRepoMybatisXML {
    @Autowired
    public SqlSessionFactory sqlSessionFactory;

    public Map<String, Object> updatebarang(Map<String, Object> map) {
        SqlSession session = sqlSessionFactory.openSession();
        try  {
            session.update("scmt_manual.updatebarangxml", map);
            session.commit();
            return map;
        } finally {
            session.close();
        }
    }

    public Map<String, Object> testps(Map<String, Object> map) {
        SqlSession session = sqlSessionFactory.openSession();
        try  {
            session.insert("scmt_manual.testsptyu", map);
            session.commit();
            return map;
        } finally {
            session.close();
        }
    }

    public List<BarangMybatis> listDataXML(Map map) {
        SqlSession session = sqlSessionFactory.openSession();
        try {
            List<BarangMybatis> list = session.selectList("scmt_manual.getListManual", map);
            return list;
        } finally {
            session.close();
        }
    }

    public BarangMybatis getByIdBarangXML(Map map) {
        SqlSession session = sqlSessionFactory.openSession();
        try {
            BarangMybatis list = session.selectOne("scmt_manual.getById", map);
            return list;
        } finally {
            session.close();
        }
    }

}