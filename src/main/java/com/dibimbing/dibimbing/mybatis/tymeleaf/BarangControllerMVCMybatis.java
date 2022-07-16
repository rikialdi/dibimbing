package com.dibimbing.dibimbing.mybatis.tymeleaf;


import com.dibimbing.dibimbing.model.Barang;
import com.dibimbing.dibimbing.mybatis.model.BarangMybatis;
import com.dibimbing.dibimbing.mybatis.service.BarangServiceMybatis;
import com.dibimbing.dibimbing.repository.BarangRepository;
import com.dibimbing.dibimbing.service.BarangService;
import com.dibimbing.dibimbing.utils.TemplateResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/v1/view/sp/barang")
public class BarangControllerMVCMybatis {

    @Autowired
    public BarangRepository repoBarang;

    @Autowired
    public TemplateResponse templateResponse;

    @Autowired
    public BarangService serviceBarang;

    @Autowired
    public BarangServiceMybatis barangServiceMybatis;
    private final int ROW_PER_PAGE = 5;

    //    Index Page
    @GetMapping(value = {"/", "/index"})
    public String index(Model model) {
        model.addAttribute("title", "Title Saya");
        return "/sp/index";
    }


    @GetMapping(value = "/list")
    public String getBarang(Model model,
                            @RequestParam(value = "page", defaultValue = "0") int pageNumber) {
        List<BarangMybatis> obj = barangServiceMybatis.selectList("%%");
        //konversi ke barang response
        List<Barang> barangs =  templateResponse.conversiToBarang(obj);
        long count = repoBarang.count();
        boolean hasPrev = pageNumber > 1;
        boolean hasNext = (pageNumber * ROW_PER_PAGE) < count;
        model.addAttribute("barangs", barangs);
        model.addAttribute("hasPrev", hasPrev);
        model.addAttribute("prev", pageNumber - 1);
        model.addAttribute("hasNext", hasNext);
        model.addAttribute("next", pageNumber + 1);
        return "/sp/barang-list";
    }

    @GetMapping(value = {"/add"})
    public String showAddBarang(Model model) {
        Barang barang = new Barang();
        model.addAttribute("add", true);
        model.addAttribute("barang", barang);

        return "/sp/barang-edit";
    }

    @PostMapping(value = "/add")
    public String addBarang(Model model,
                            @ModelAttribute("barang") Barang barang) {
        try {
            System.out.println("nilai barnag barang=" + barang.getNama());
//            Map data = serviceBarang.insert(barang);
            Map data = barangServiceMybatis.savebarangoutwitheror(barang.getHarga(),
                    barang.getNama(), barang.getSatuan(), barang.getStok(), 0);

            Barang newBarang = (Barang) data.get("data");
            return "redirect:/v1/view/sp/barang/" + String.valueOf(newBarang.getId());
        } catch (Exception ex) {
            // log exception first,
            // then show error
            String errorMessage = ex.getMessage();
            model.addAttribute("errorMessage", errorMessage);

            //model.addAttribute("barang", barang);
            model.addAttribute("add", true);
            return "/sp/barang-edit";
        }
    }

    @GetMapping(value = {"/{barangId}/edit"})
    public String showEditBarang(Model model, @PathVariable long barangId) {
        Barang barang = null;
        try {
//            barang = repoBarang.getbyID(barangId);
            BarangMybatis obj = barangServiceMybatis.selectBlog(Integer.parseInt(String.valueOf(barangId)));
            barang = templateResponse.conversiToBarang(obj);
        } catch (ResourceNotFoundException ex) {
            model.addAttribute("errorMessage", "Barang not found");
        }
        model.addAttribute("add", false);
        model.addAttribute("barang", barang);
        return "/sp/barang-edit";
    }

    @PostMapping(value = {"/{barangId}/edit"})
    public String updateBarang(Model model,
                               @PathVariable long barangId,
                               @ModelAttribute("barang") Barang barang) {
        try {
            barang.setId(barangId);
            serviceBarang.update(barang);
            return "redirect:/v1/view/sp/barang/" + String.valueOf(barang.getId());
        } catch (Exception ex) {
            // log exception first,
            // then show error
            String errorMessage = ex.getMessage();
            model.addAttribute("errorMessage", errorMessage);

            model.addAttribute("add", false);
            return "/sp/barang-edit";
        }
    }

    @GetMapping(value = "/{barangId}")
    public String getBarangById(Model model, @PathVariable long barangId) {
        Barang barang = null;
        try {
//            barang = repoBarang.getbyID(barangId);
            BarangMybatis obj = barangServiceMybatis.selectBlog(Integer.parseInt(String.valueOf(barangId)));
            barang = templateResponse.conversiToBarang(obj);
        } catch (ResourceNotFoundException ex) {
            model.addAttribute("errorMessage", "Barang not found");
        }
        model.addAttribute("barang", barang);
        return "/sp/barang";
    }

    //delete
    @GetMapping(value = {"/{barangId}/delete"})
    public String showDeleteBarangById(
            Model model, @PathVariable long barangId) {
        Barang barang = null;
        try {
//            barang = repoBarang.getbyID(barangId);
            BarangMybatis obj = barangServiceMybatis.selectBlog(Integer.parseInt(String.valueOf(barangId)));
            barang = templateResponse.conversiToBarang(obj);
        } catch (ResourceNotFoundException ex) {
            model.addAttribute("errorMessage", "Barang not found");
        }
        model.addAttribute("allowDelete", true);
        model.addAttribute("barang", barang);
        return "/sp/barang";
    }

    @PostMapping(value = {"/{barangId}/delete"})
    public String deleteBarangById(
            Model model, @PathVariable long barangId) {
        try {
//            serviceBarang.delete(barangId);
//            method delete belum bikin
            return "redirect:/v1/view/sp/barang/list";
        } catch (ResourceNotFoundException ex) {
            String errorMessage = ex.getMessage();
            model.addAttribute("errorMessage", errorMessage);
            return "/sp/barang";
        }
    }
}
