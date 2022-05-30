package com.dibimbing.dibimbing.controller.tymeleaf;

import com.dibimbing.dibimbing.model.Barang;
import com.dibimbing.dibimbing.service.BarangService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Controller
@RequestMapping("/v1/view/barang")
public class BarangController {

    @Autowired
    public com.dibimbing.dibimbing.service.BarangService barangService;

    private final int ROW_PER_PAGE = 5;

    //    Index Page
    @GetMapping(value = {"/", "/index"})
    public String index(Model model) {
        model.addAttribute("title", "Title Saya");
        return "index";
    }


    @GetMapping(value = "/list")
    public String getBarang(Model model,
                            @RequestParam(value = "page", defaultValue = "1") int pageNumber) {
        List<Barang> barangs = barangService.dataMhs(pageNumber, ROW_PER_PAGE);

        long count = barangs== null ? 1 :barangs.size();
        boolean hasPrev = pageNumber > 1;
        boolean hasNext = (pageNumber * ROW_PER_PAGE) < count;
        model.addAttribute("barangs", barangs);
        model.addAttribute("hasPrev", hasPrev);
        model.addAttribute("prev", pageNumber - 1);
        model.addAttribute("hasNext", hasNext);
        model.addAttribute("next", pageNumber + 1);
        return "barang-list";
    }

    @GetMapping(value = {"/add"})
    public String showAddBarang(Model model) {
        Barang barang = new Barang();
        model.addAttribute("add", true);
        model.addAttribute("barang", barang);

        return "barang-edit";
    }

    @PostMapping(value = "/add")
    public String addBarang(Model model,
                            @ModelAttribute("barang") Barang barang) {
        try {
            System.out.println("nilai barnag barang=" + barang.getNama());
            Barang newBarang = barangService.save(barang);
            return "redirect:/v1/view/barang/" + String.valueOf(newBarang.getId());
        } catch (Exception ex) {

            String errorMessage = ex.getMessage();
            model.addAttribute("errorMessage", errorMessage);
            model.addAttribute("add", true);
            return "barang-edit";
        }
    }

    @GetMapping(value = {"/{barangId}/edit"})
    public String showEditBarang(Model model, @PathVariable long barangId) {
        Barang barang = null;
        barang = barangService.findById(barangId);
        model.addAttribute("add", false);
        model.addAttribute("barang", barang);
        return "barang-edit";
    }

    @PostMapping(value = {"/{barangId}/edit"})
    public String updateBarang(Model model,
                               @PathVariable long barangId,
                               @ModelAttribute("barang") Barang barang) {
        try {
            barang.setId(barangId);
            barangService.update(barang);
            return "redirect:/v1/view/barang/" + String.valueOf(barang.getId());
        } catch (Exception ex) {
            // log exception first,
            // then show error
            String errorMessage = ex.getMessage();
            model.addAttribute("errorMessage", errorMessage);

            model.addAttribute("add", false);
            return "barang-edit";
        }
    }

    @GetMapping(value = "/{barangId}")
    public String getBarangById(Model model, @PathVariable long barangId) {
        Barang barang = null;
        barang = barangService.findById(barangId);
        model.addAttribute("barang", barang);
        return "barang";
    }

    //delete
    @GetMapping(value = {"/{barangId}/delete"})
    public String showDeleteBarangById(
            Model model, @PathVariable long barangId) {
        Barang barang = null;
        barang = barangService.findById(barangId);
        model.addAttribute("allowDelete", true);
        model.addAttribute("barang", barang);
        return "barang";
    }

    @PostMapping(value = {"/{barangId}/delete"})
    public String deleteBarangById(
            Model model, @PathVariable long barangId) {
        barangService.deleted(barangId);
        return "redirect:/v1/view/barang/list";
    }
}

