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
public class UDCService {
	
    @Autowired
    private UDCRepository udcRepository;
    
    public boolean udcHasChildren(UDC udc) {
        return udc.getChildren() != null && !udc.getChildren().isEmpty();
    }

    
    public List<UDC> udcChildren(UDC udc) {
        return udc.getChildren();
    }
    
    public List<UDC> getRootUDCs() {
        return udcRepository.findByParentIsNullOrderByCode();
    }

    public List<UDC> getChildUDCs(Long parentId) {
        UDC parent = udcRepository.findById(parentId).orElse(null);
        return udcRepository.findByParentOrderByCode(parent);
    }

    public List<UDC> findByTitle(String title) {
        return udcRepository.findByTitleContainingIgnoreCase(title);
    }

    public Optional<UDC> findByCode(String code) {
        return udcRepository.findByCode(code).stream().findFirst();
    }

    public Optional<UDC> findById(Long id) {
        return udcRepository.findById(id);
    }
    
    public UDC findUniqueByCodeUdc(String code) {
    	return udcRepository.findUniqueByCode(code);
    }

  
}
