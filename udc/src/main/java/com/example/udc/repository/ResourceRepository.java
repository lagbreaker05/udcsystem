package com.example.udc.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.udc.model.Resource;

public interface ResourceRepository extends JpaRepository<Resource, Long> {
	List<Resource> findByTitleContainingIgnoreCase(String title);
}
