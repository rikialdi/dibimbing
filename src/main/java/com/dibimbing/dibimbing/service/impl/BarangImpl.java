package com.dibimbing.dibimbing.service.impl;

import com.dibimbing.dibimbing.model.Barang;
import com.dibimbing.dibimbing.repository.BarangRepository;
import com.dibimbing.dibimbing.service.BarangService;
import com.dibimbing.dibimbing.utils.TemplateResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class BarangImpl implements BarangService {


    @Autowired
    public BarangRepository barangRepository;

    public static final Logger log = LoggerFactory.getLogger(BarangImpl.class);


    @Autowired
    public TemplateResponse templateResponse;

    @Override
    public Map insert(Barang obj) {
        try {
            if (templateResponse.chekNull(obj.getNama())) {
                return templateResponse.templateEror("Nama is Requiered");
            }

            if (templateResponse.chekNull(obj.getHarga())) {
                return templateResponse.templateEror("Harga is requiered");
            }
            Barang barangSave = barangRepository.save(obj);
            return templateResponse.templateSukses(barangSave);
        } catch (Exception e) {
            return templateResponse.templateEror(e);
        }
    }

    @Override
    public Map getAll(int size, int page) {
        try {
            Pageable show_data = PageRequest.of(page, size);
            Page<Barang> list = barangRepository.getAllData(show_data);
            return templateResponse.templateSukses(list);
        } catch (Exception e) {
            log.error("ada eror di method getAll:" + e);
            return templateResponse.templateEror(e);
        }
    }


    @Override
    public Map update(Barang barangReq) {
        try {

            if (templateResponse.chekNull(barangReq.getId())) {
                return templateResponse.templateEror("Id Barang is required");
            }
            Barang chekIdBarang = barangRepository.getbyID(barangReq.getId());
            if (templateResponse.chekNull(chekIdBarang)) {
                return templateResponse.templateEror("Id Barang Not found");
            }

            chekIdBarang.setNama(barangReq.getNama());
            chekIdBarang.setHarga(barangReq.getHarga());
            chekIdBarang.setStok(barangReq.getStok());
            chekIdBarang.setSatuan(barangReq.getSatuan());
            Barang dosave = barangRepository.save(chekIdBarang);

            return templateResponse.templateSukses(dosave);
        } catch (Exception e) {
            return templateResponse.templateEror(e);
        }

    }

    @Override
    public Map delete(Long barang) {
        try {
            if (templateResponse.chekNull(barang)) {
                return templateResponse.templateEror("Id Barang is required");
            }

            Barang chekIdBarang = barangRepository.getbyID(barang);
            if (templateResponse.chekNull(chekIdBarang)) {
                return templateResponse.templateEror("Id Barang Not found");
            }

            chekIdBarang.setDeleted_date(new Date());//
            barangRepository.save(chekIdBarang);

            return templateResponse.templateSukses("sukses deleted");

        } catch (Exception e) {
            return templateResponse.templateEror(e);
        }
    }
}
