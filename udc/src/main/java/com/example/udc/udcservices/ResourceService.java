package com.example.udc.udcservices;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.udc.model.Resource;
import com.example.udc.model.UDC;
import com.example.udc.repository.ResourceRepository;
import com.example.udc.repository.UDCRepository;

@Service
public class ResourceService {
	  @Autowired
	    private ResourceRepository resourceRepository;
	  
	  @Autowired
	    private UDCRepository udcRepository;
	  
	  @Autowired 
	    private UDCService udcService;

	  public List<Resource> getResourcesByUDC(Long udcId) {
	        UDC udc = new UDC();
	        udc.setId(udcId);
	        return resourceRepository.findByUdcOrderByTitle(udc);
	    }
	  public List<Resource> findByTitle(String title) {
	        return resourceRepository.findByTitle(title);
	    }

	    public List<Resource> findByAuthor(String author) {
	        return resourceRepository.findByAuthor(author);
	    }

	    public List<Resource> findByUdcCode(String code) {
	        return udcService.findByCode(code)
	                         .map(resourceRepository::findByUdc)
	                         .orElseThrow(() -> new RuntimeException("UDC not found"));
	    }
	    
	    public List<Resource> getAllResources() {
	        return resourceRepository.findAll();
	    }

	    public Optional<Resource> getResourceById(Long id) {
	        return resourceRepository.findById(id);
	    }

	    public Resource saveResource(Resource resource, String udcCode) {
	        UDC udc = udcRepository.findUniqueByCode(udcCode);
	        if (udc != null) {
	            resource.setUdc(udc);
	            return resourceRepository.save(resource);
	        } else {
	            throw new IllegalArgumentException("UDC with code " + udcCode + " not found");
	        }
	    }

	    public Resource updateResource(Long id, Resource resourceDetails) {
	        Resource resource = resourceRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Resource not found"));

	        resource.setTitle(resourceDetails.getTitle());
	        resource.setAuthor(resourceDetails.getAuthor());
	        resource.setPublicationDate(resourceDetails.getPublicationDate());
	        resource.setUdc(resourceDetails.getUdc());

	        return resourceRepository.save(resource);
	    }

	    public void deleteResource(Long id) {
	        resourceRepository.deleteById(id);
	    }
	    public List<Resource> getAllResourcesSortedByUDCCode() {
	        return resourceRepository.findAllByOrderByUdcCode();
	    }

	    public List<Resource> searchResources(String searchBy, String keyword) {
	        switch (searchBy) {
	            case "title":
	                return resourceRepository.findByTitleContaining(keyword);
	            case "author":
	                return resourceRepository.findByAuthorContaining(keyword);
	            case "udcCode":
	                return resourceRepository.findByUdcCodeContaining(keyword);
	            default:
	                throw new IllegalArgumentException("Invalid search parameter");
	        }
}}
