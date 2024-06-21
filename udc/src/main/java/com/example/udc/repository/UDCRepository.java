package com.example.udc.repository;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.udc.model.UDC;

public interface UDCRepository extends JpaRepository<UDC, Long> {

	List<UDC> findByParentId(Long parentId);
	List<UDC> findByParentIsNull(Sort sort);
	 List<UDC> findByParentIsNull();
	    List<UDC> findByParent(UDC parent);
	List<UDC> findByParentIsNullOrderByCode();
    List<UDC> findByParentOrderByCode(UDC parent);
    List<UDC> findByCodeContainingIgnoreCaseOrderByCode(String code);
    List<UDC> findByTitleContainingIgnoreCaseOrderByTitle(String title);
    List<UDC> findByTitleContainingIgnoreCase(String title);
    List<UDC> findByCode(String code);
   
    @Query("SELECT u FROM UDC u WHERE u.code = :code")
    UDC findUniqueByCode(@Param("code") String code);
}
