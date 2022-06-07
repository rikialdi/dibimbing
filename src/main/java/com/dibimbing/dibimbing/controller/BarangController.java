package com.dibimbing.dibimbing.controller;


import com.dibimbing.dibimbing.config.Config;
import com.dibimbing.dibimbing.model.Barang;
import com.dibimbing.dibimbing.repository.BarangRepository;
import com.dibimbing.dibimbing.service.BarangService;
import com.dibimbing.dibimbing.utils.SimpleStringUtils;
import com.dibimbing.dibimbing.utils.TemplateResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.ConstraintViolationException;
import javax.validation.Valid;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.Principal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/v1/barang")
public class BarangController {


    @Autowired
    public BarangService barangService;

    Config config = new Config();
    @Autowired
    public BarangRepository barangRepository;

    @Autowired
    public TemplateResponse templateResponse;

    SimpleStringUtils simpleStringUtils = new SimpleStringUtils();

    @PostMapping("/save/{idsupplier}")
    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<Map> save(@PathVariable(value = "idsupplier") Long idsupplier,
                                    @Valid @RequestBody Barang objModel) {
        Map obj = barangService.insert(objModel, idsupplier);
        return new ResponseEntity<Map>(obj, HttpStatus.OK);
    }

    @PutMapping("/update/{idsupplier}")
    public ResponseEntity<Map> update(@PathVariable(value = "idsupplier") Long idsupplier,
                                      @RequestBody Barang objModel ) {
        Map map = barangService.update(objModel, idsupplier);
        return new ResponseEntity<Map>(map, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Map> delete(@PathVariable(value = "id") Long id) {
        Map map = barangService.delete(id);
        return new ResponseEntity<Map>(map, HttpStatus.OK);
    }

    @GetMapping("/list")
    public ResponseEntity<Map> listByBama(
            @RequestParam() Integer page,
            @RequestParam() Integer size) {
        Map list = barangService.getAll(size, page);
        return new ResponseEntity<Map>(list, new HttpHeaders(), HttpStatus.OK);
    }

    /*
    kita ingin call rest kita sendiri: barang
    a. post localhost:8082/api/v1/binar/barang/save/{idsupplier}
    b. put localhost:8082/api/v1/binar/barang/update/{idsupplier}
    c. delete localhost:8082/api/v1/binar/barang/delete/{id}
    d. get localhost:8082/api/v1/binar/barang/list

     */

    @GetMapping("list-barang")// by seller
    public ResponseEntity<Map> listNotif(
            @RequestParam() Integer page,
            @RequestParam(required = true) Integer size,
            @RequestParam(required = false) String nama,
            @RequestParam(required = false) Double priceMin,
            @RequestParam(required = false) Double priceMax,
            @RequestParam(required = false) String orderby,
            @RequestParam(required = false) String ordertype,
            Principal principal) {
        /*
        1.principal : mendapatkan username berdasarkan token user yang akses di client
        2.  getShort : shoritng
         */
        Pageable show_data = simpleStringUtils.getShort(orderby, ordertype, page, size);
        Page<Barang> list = null;


        if (nama != null && priceMin !=null && priceMax != null ) {
            list = barangRepository.getDataByPriceAndNama(priceMin,priceMax, "'%" + nama + "%'",show_data);
        } else if ( priceMin !=null && priceMax != null ) {
            list = barangRepository.getDataByPrice(priceMin,priceMax, show_data);
        } else if (nama != null ) {
            list = barangRepository.findByNamaLike("%" + nama + "%", show_data);
        } else {
            list = barangRepository.getAllData(show_data);
        }
        return new ResponseEntity<Map>(templateResponse.templateSukses(list), new HttpHeaders(), HttpStatus.OK);
    }
}

//    @Autowired
//    public BarangService barangService;
//
//    Config config = new Config();
//
//    @Autowired
//    public BarangRepository barangRepository;
//
//    @Autowired
//    public TemplateResponse templateResponse;
//
//
//    @PostMapping("/save")
//    @ExceptionHandler(ConstraintViolationException.class)
//    public ResponseEntity<Map> save(@Valid @RequestBody Barang objModel) {
//        Map obj = barangService.insert(objModel);
//        return new ResponseEntity<Map>(obj, HttpStatus.OK);
//    }
//
//    @PutMapping("/update")
//    public ResponseEntity<Map> update(@RequestBody Barang objModel) {
//        Map map = barangService.update(objModel);
//        return new ResponseEntity<Map>(map, HttpStatus.OK);
//    }
//
//    @DeleteMapping("/delete/{id}")
//    public ResponseEntity<Map> delete(@PathVariable(value = "id") Long id) {
//        Map map = barangService.delete(id);
//        return new ResponseEntity<Map>(map, HttpStatus.OK);
//    }
//
//    @GetMapping("/list")
//    public ResponseEntity<Map> listByBama(
//            @RequestParam() Integer page,
//            @RequestParam() Integer size) {
//        Map list = barangService.getAll(size, page);
//        return new ResponseEntity<Map>(list, new HttpHeaders(), HttpStatus.OK);
//    }

