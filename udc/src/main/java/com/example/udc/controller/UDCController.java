package com.example.udc.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.udc.model.Resource;
import com.example.udc.model.UDC;
import com.example.udc.repository.ResourceRepository;
import com.example.udc.repository.UDCRepository;
import com.example.udc.udcservices.UDCService;

@Controller
public class UDCController {

	@Autowired
	private ResourceRepository resourceRepo;
	@Autowired
    private UDCRepository udcRepository;
	  @Autowired
	    private UDCService udcService;
	
	@GetMapping("user/resources")
	public String showProductList(Model model) {
		
		List<Resource> resources = resourceRepo.findAll();
		model.addAttribute("resources", resources);
		
		return "udc/resources";
	}
	
	 @GetMapping("user/udc")
	    public String showUDCList(Model model) {
		 List<UDC> rootNodes = udcRepository.findByParentIsNull();
	        model.addAttribute("rootNodes", rootNodes);
	        return "udc/udc_list";
	    }
	  @GetMapping("user/udc/children/{id}")
	    public String getChildren(@PathVariable("id") Long id, Model model) {
	        UDC parent = udcRepository.findById(id).orElse(null);
	        if (parent != null) {
	            List<UDC> children = udcRepository.findByParent(parent);
	            model.addAttribute("children", children);
	        }
	        return "udc/children";
	    }
	
}
