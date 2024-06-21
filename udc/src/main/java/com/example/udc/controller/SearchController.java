package com.example.udc.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.udc.model.Resource;
import com.example.udc.model.UDC;
import com.example.udc.udcservices.ResourceService;
import com.example.udc.udcservices.UDCService;

@Controller
@RequestMapping("/search")
public class SearchController {

	@Autowired
    private UDCService udcService;

    @Autowired
    private ResourceService resourceService;

    @GetMapping("/search")
    public String searchPage() {
        return "admin/search";
    }

    @GetMapping("/admin/search")
    public String search(@RequestParam String type, @RequestParam String query, Model model) {
        switch (type) {
            case "udcTitle":
                List<UDC> udcsByTitle = udcService.findByTitle(query);
                model.addAttribute("udcs", udcsByTitle);
                return "admin/udc-results";
            case "udcCode":
                UDC udcByCode = udcService.findByCode(query)
                        .orElseThrow(() -> new RuntimeException("UDC not found"));
                model.addAttribute("udcs", List.of(udcByCode));
                return "admin/udc-results";
            case "resourceTitle":
                List<Resource> resourcesByTitle = resourceService.findByTitle(query);
                model.addAttribute("resources", resourcesByTitle);
                return "admin/resource-results";
            case "resourceAuthor":
                List<Resource> resourcesByAuthor = resourceService.findByAuthor(query);
                model.addAttribute("resources", resourcesByAuthor);
                return "admin/resource-results";
            default:
                throw new IllegalArgumentException("Invalid search type");
        }
    }

}
