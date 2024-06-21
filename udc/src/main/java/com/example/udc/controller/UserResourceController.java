package com.example.udc.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.udc.model.Resource;
import com.example.udc.udcservices.ResourceService;

@Controller
@RequestMapping("/user/resources")
public class UserResourceController {

    @Autowired
    private ResourceService resourceService;

    @GetMapping
    public String showResourcesPage(Model model) {
        List<Resource> resources = resourceService.getAllResourcesSortedByUDCCode();
        model.addAttribute("resources", resources);
        return "user/resources";
    }

    @GetMapping("/search")
    public String searchResources(@RequestParam("searchBy") String searchBy,
                                  @RequestParam("keyword") String keyword,
                                  Model model) {
        List<Resource> resources = resourceService.searchResources(searchBy, keyword);
        model.addAttribute("resources", resources);
        return "user/resources";
    }
}
