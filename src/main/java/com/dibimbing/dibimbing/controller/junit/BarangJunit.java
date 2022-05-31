package com.dibimbing.dibimbing.controller.junit;

import com.dibimbing.dibimbing.model.Barang;
import com.dibimbing.dibimbing.repository.BarangRepository;
import com.dibimbing.dibimbing.service.BarangService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class BarangJunit {

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private BarangService barangRestTemplateService;

    @Autowired
    public BarangRepository barangRepository;

    @Test
    public void contohJunit() {
        int a = 5;
        int b = 10;
        int c = a + b;
        assertEquals(15, c);

    }

    @Test
    public void contohJunit2() {
        int a = 5;
        int b = 10;
        int c = a * b;
        assertEquals(50, c);
    }

    @Test
    public void simpanBarangTampaRestTemplate() {
        Barang req = new Barang();
        req.setHarga(200.0);
        req.setNama("riski wisesar");
        req.setStok(100);
        req.setSatuan("kg");

        Map map = barangRestTemplateService.insert(req);
        assertEquals("200", map.get("status"));
        if (map.get("status").equals("200")) {
            System.out.println(map.get("data"));
            System.out.println(map.get("status"));
            System.out.println(map.get("message"));
        } else {
            System.out.println("terjadi eror");
        }
    }

    @Test
    public void updateBarangTampaRestTemplate() {
        Barang req = new Barang();
        req.setId(6L);
        req.setHarga(200.0);
        req.setNama("riski ubah");
        req.setStok(1001);
        req.setSatuan("kg");

        Map map = barangRestTemplateService.update(req);
        assertEquals("200", map.get("status"));
        if (map.get("status").equals("200")) {
            System.out.println(map.get("data"));
            System.out.println(map.get("status"));
            System.out.println(map.get("message"));
        } else {
            System.out.println("terjadi eror");
        }

    }

    @Test
    public void listBarangTampaRestTemplate() {
        Map map = barangRestTemplateService.getAll(10, 0);
        assertEquals("200", map.get("status"));
        if (map.get("status").equals("200")) {
            System.out.println(map.get("data"));
            System.out.println(map.get("status"));
            System.out.println(map.get("message"));
        } else {
            System.out.println("terjadi eror");
        }
    }





}
