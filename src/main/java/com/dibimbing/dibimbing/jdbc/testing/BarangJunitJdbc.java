package com.dibimbing.dibimbing.jdbc.testing;

import com.dibimbing.dibimbing.jdbc.model.BarangJdbc;
import com.dibimbing.dibimbing.jdbc.repository.BarangRepoJdbc;
import com.dibimbing.dibimbing.model.Barang;
import com.dibimbing.dibimbing.repository.BarangRepository;
import com.dibimbing.dibimbing.service.BarangService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class BarangJunitJdbc {

    @Autowired
    BarangRepoJdbc barangRepoJdbc;

    @Test
    public void save() {
        Barang obj = new Barang();
        obj.setHarga(1235.0);
        obj.setNama("barang 4");
        obj.setStok(789);
        obj.setSatuan("pcs");
        int chek = barangRepoJdbc.save(obj);
        System.out.println("Value:"+chek);
    }

    @Test
    public void update() {
        Barang obj = new Barang();
        obj.setHarga(1235.0);
        obj.setNama("barang 4");
        obj.setStok(789);
        obj.setSatuan("pcs");
        obj.setId(15L);
        int chek = barangRepoJdbc.update(obj);
        System.out.println("Value:"+chek);
    }

    @Test
    public void findAll1() {
        List<BarangJdbc> obk =barangRepoJdbc.findAll();
        for(BarangJdbc o : obk){
            System.out.println("id:"+o.getId());
            System.out.println("nama :"+o.getNama());
            System.out.println("=======");
        }
    }

    @Test
    public void findAll2() {
        List<BarangJdbc> obk =barangRepoJdbc.findAll2();
        for(BarangJdbc o : obk){
            System.out.println("id:"+o.getId());
            System.out.println("nama :"+o.getNama());
            System.out.println("=======");
        }
    }

    @Test
    public void findAll3() {
       List<BarangJdbc> obk =barangRepoJdbc.findAll3();
       for(BarangJdbc o : obk){
           System.out.println("id:"+o.getId());
           System.out.println("nama :"+o.getNama());
           System.out.println("=======");
       }
    }

    @Test
    public void findById1() {
        BarangJdbc obj =barangRepoJdbc.findByBarangId(15L);
            System.out.println("id:"+obj.getId());
            System.out.println("nama :"+obj.getNama());
            System.out.println("=======");
    }

    @Test
    public void findById2() {
        BarangJdbc obj =barangRepoJdbc.findByBarangId2(15L);
        System.out.println("id:"+obj.getId());
        System.out.println("nama :"+obj.getNama());
        System.out.println("=======");
    }

    @Test
    public void delete() {
        int chek = barangRepoJdbc.delete(15L);
        System.out.println("Value:"+chek);
    }
}
