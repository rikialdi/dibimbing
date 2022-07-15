
package com.dibimbing.dibimbing.mybatis.controller;


import com.dibimbing.dibimbing.config.Config;
import com.dibimbing.dibimbing.model.Barang;
import com.dibimbing.dibimbing.mybatis.model.BarangMybatis;
import com.dibimbing.dibimbing.mybatis.service.BarangServiceMybatis;
import com.dibimbing.dibimbing.repository.BarangRepository;
import com.dibimbing.dibimbing.service.BarangService;
import com.dibimbing.dibimbing.utils.SimpleStringUtils;
import com.dibimbing.dibimbing.utils.TemplateResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.ConstraintViolationException;
import javax.validation.Valid;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import com.dibimbing.dibimbing.mybatis.service.BarangServiceMybatis;
@RestController
@RequestMapping("/v1/sp/barang")
public class BarangControllerMybatis {


    @Autowired
    public BarangServiceMybatis barangServiceMybatis;

    Config config = new Config();

    @Autowired
    public TemplateResponse templateResponse;

    SimpleStringUtils simpleStringUtils = new SimpleStringUtils();

    @PostMapping("/save")
    public ResponseEntity<Map> dave(@RequestBody Barang objModel) {
        Map map = barangServiceMybatis.savebarangoutwitheror(objModel.getHarga(),
                objModel.getNama(),
                objModel.getSatuan(),
                objModel.getStok(),
                Integer.parseInt(objModel.getId().toString()));
        return new ResponseEntity<Map>(map, HttpStatus.OK);
    }

    @PutMapping("/update")
    public ResponseEntity<Map> update(@RequestBody Barang objModel) {
        Map map = barangServiceMybatis.updatebarangoutwitheror(objModel.getHarga(),
                objModel.getNama(),
                objModel.getSatuan(),
                objModel.getStok(),
                Integer.parseInt(objModel.getId().toString()));
        return new ResponseEntity<Map>(map, HttpStatus.OK);
    }

//    @DeleteMapping("/delete/{id}")
//    public ResponseEntity<Map> delete(@PathVariable(value = "id") Long id) {
//        Map map = barangService.delete(id);
//        return new ResponseEntity<Map>(map, HttpStatus.OK);
//    }

    @GetMapping("/list")
    public ResponseEntity<Map> listByBama(
            @RequestParam(required = false) String nama) {
        List<BarangMybatis> list = barangServiceMybatis.selectList("%"+nama+"%");
        return new ResponseEntity<Map>(templateResponse.templateSukses(templateResponse.conversiToBarang(list)), new HttpHeaders(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Map> delete(@PathVariable(value = "id") Integer id) {
        BarangMybatis obj = barangServiceMybatis.selectBlog(id);
        return new ResponseEntity<Map>(templateResponse.templateSukses(templateResponse.conversiToBarang(obj)), HttpStatus.OK);
    }


}
