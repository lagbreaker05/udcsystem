package com.example.udc.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.udc.model.Resource;
import com.example.udc.model.UDC;
import com.example.udc.repository.ResourceRepository;
import com.example.udc.repository.UDCRepository;
import com.example.udc.udcservices.ResourceService;
import com.example.udc.udcservices.UDCService;


@Controller
public class UDCController {

    @Autowired
    private UDCService udcService;
    
    @Autowired
    private ResourceService resourceService;
    
    @GetMapping("/user/udcs")
    public String getUDCs(Model model) {
        model.addAttribute("udcs", udcService.getRootUDCs());
        return "user/udcs";
    }

    @GetMapping("/user/udcs/children/{parentId}")
    @ResponseBody
    public List<UDC> getChildUDCs(@PathVariable Long parentId) {
        return udcService.getChildUDCs(parentId);
    }

    @GetMapping("/user/udcs/resources/{udcId}")
    @ResponseBody
    public List<Resource> getResources(@PathVariable Long udcId) {
        return resourceService.getResourcesByUDC(udcId);
    }

    @GetMapping("/admin/udcs")
    public String getUDCsAdmin(Model model) {
        model.addAttribute("udcs", udcService.getRootUDCs());
        return "admin/udcs";
    }

    @GetMapping("/admin/udcs/children/{parentId}")
    @ResponseBody
    public List<UDC> getChildUDCsAdmin(@PathVariable Long parentId) {
        return udcService.getChildUDCs(parentId);
    }

    @GetMapping("/admin/udcs/resources/{udcId}")
    @ResponseBody
    public List<Resource> getResourcesAdmin(@PathVariable Long udcId) {
        return resourceService.getResourcesByUDC(udcId);
    }
	
}
