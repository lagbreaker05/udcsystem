package com.example.udc.controller;

import java.util.Date;
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
import com.example.udc.repository.UDCRepository;
import com.example.udc.udcservices.ResourceService;
import com.example.udc.udcservices.UDCService;

@Controller
@RequestMapping("/admin/resources")
public class ResourceController {
    @Autowired
    private ResourceService resourceService;
    @Autowired
    private UDCService udcService;

    @GetMapping
    public String getAllResources(Model model) {
        List<Resource> resources = resourceService.getAllResources();
        model.addAttribute("resources", resources);
        return "admin/resources";
    }

    @PostMapping
    public String addResource(@RequestParam String title,
                              @RequestParam String author,
                              @RequestParam String publicationDate,
                              @RequestParam String udcCode,
                              Model model) {
        try {
            Resource resource = new Resource();
            resource.setTitle(title);
            resource.setAuthor(author);
            resource.setPublicationDate(java.sql.Date.valueOf(publicationDate));

            resourceService.saveResource(resource, udcCode);

            return "redirect:/admin/resources";
        } catch (IllegalArgumentException e) {
            model.addAttribute("error", e.getMessage());
            return "error";
        }
    }

    @PostMapping("/edit")
    public String editResource(@RequestParam Long id,
                               @RequestParam String title,
                               @RequestParam String author,
                               @RequestParam String publicationDate,
                               @RequestParam String udcCode,
                               Model model) {
        try {
            Resource resource = new Resource();
            resource.setTitle(title);
            resource.setAuthor(author);
            resource.setPublicationDate(java.sql.Date.valueOf(publicationDate));
            resource.setUdc(udcService.findUniqueByCodeUdc(udcCode));
            resource.getUdc().setCode(udcCode);

            resourceService.updateResource(id, resource);

            return "redirect:/admin/resources";
        } catch (IllegalArgumentException e) {
            model.addAttribute("error", e.getMessage());
            return "admin/error";
        }
    }

    @PostMapping("/delete")
    public String deleteResource(@RequestParam Long id) {
        resourceService.deleteResource(id);
        return "redirect:/admin/resources";
    }
}