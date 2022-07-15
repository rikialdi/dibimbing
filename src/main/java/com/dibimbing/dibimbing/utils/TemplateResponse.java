package com.dibimbing.dibimbing.utils;

import com.dibimbing.dibimbing.model.Barang;
import com.dibimbing.dibimbing.mybatis.model.BarangMybatis;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component // IOC - Beans
public class TemplateResponse {

    public Map templateSukses(Object objek){
        Map map = new HashMap();
        map.put("data", objek);
        map.put("message", "sukses");
        map.put("status", "200");
        return map;
    }

    public Map templateSukses(Object objek,String message, String status){
        Map map = new HashMap();
        map.put("data", objek);
        map.put("message", message);
        map.put("status",status);
        return map;
    }

    public Map templateEror(Object objek){
        Map map = new HashMap();
        map.put("message", objek);
        map.put("status", "404");
        return map;
    }
    public Map notFound(Object objek){
        Map map = new HashMap();
        map.put("message", objek);
        map.put("status", "404");
        return map;
    }


    public boolean chekNull(Object obj){
        if(obj == null){
            return true;
        }
        return  false;
    }

    public Barang conversiToBarang(BarangMybatis obj){
        Barang objBarang = new Barang();
        objBarang.setId(obj.getResid());
        objBarang.setSatuan(obj.getRessatuan());
        objBarang.setStok(obj.getResstok());
        objBarang.setHarga(obj.getResharga());
        objBarang.setNama(obj.getResnama());
        return  objBarang;
    }

    public List<Barang> conversiToBarang(List<BarangMybatis> list){
        List<Barang> listBarang=  new ArrayList<>();
        for(BarangMybatis obj : list){
            Barang objBarang = new Barang();
            objBarang.setId(obj.getResid());
            objBarang.setSatuan(obj.getRessatuan());
            objBarang.setStok(obj.getResstok());
            objBarang.setHarga(obj.getResharga());
            objBarang.setNama(obj.getResnama());
            listBarang.add(objBarang);
        }
        return  listBarang;
    }
}
