package com.example.udc.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.udc.model.Resource;
import com.example.udc.model.UDC;

public interface ResourceRepository extends JpaRepository<Resource, Long> {
	List<Resource> findByTitleContainingIgnoreCaseOrderByTitle(String title);
    List<Resource> findByAuthorContainingIgnoreCaseOrderByAuthor(String author);
    List<Resource> findByUdcOrderByTitle(UDC udc);
    List<Resource> findByTitleContainingIgnoreCase(String title);
    List<Resource> findByAuthorContainingIgnoreCase(String author);
    
    List<Resource> findByUdc(UDC udc);
    List<Resource> findByAuthor(String keyword);
    List<Resource> findByTitle(String keyword);
    
    
    List<Resource> findAllByOrderByUdcCode();

    List<Resource> findByTitleContaining(String keyword);

    List<Resource> findByAuthorContaining(String keyword);

    List<Resource> findByUdcCodeContaining(String keyword);
}
