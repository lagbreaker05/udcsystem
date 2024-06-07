package com.example.udc.repository;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.udc.model.UDC;

public interface UDCRepository extends JpaRepository<UDC, Long> {

	List<UDC> findByParentId(Long parentId);
	List<UDC> findByParentIsNull(Sort sort);
	 List<UDC> findByParentIsNull();
	    List<UDC> findByParent(UDC parent);
}
