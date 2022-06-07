package com.dibimbing.dibimbing.controller;

import com.dibimbing.dibimbing.model.Supplier;
import com.dibimbing.dibimbing.service.SupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/v1/supplier")
public class SupplierController {

    @Autowired
    public SupplierService supplierService;

    @PostMapping("")
    public ResponseEntity<Map> save(@RequestBody Supplier objModel) {
        Map obj = supplierService.insert(objModel);
        return new ResponseEntity<Map>(obj, HttpStatus.OK);
    }
}
