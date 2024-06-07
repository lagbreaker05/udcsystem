package com.example.udc.model;



import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

import java.util.List;

@Entity
public class UDC {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
	private String title;

	@Column(unique = true)
    private String code;

    @ManyToOne
    @JoinColumn(name = "parent_id")
    private UDC parent;

   

    @OneToMany(mappedBy = "udc", cascade = CascadeType.ALL)
    private List<Resource> resources;

    @OneToMany(mappedBy = "parent", cascade = CascadeType.ALL)
    private List<UDC> children;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public UDC getParent() {
		return parent;
	}

	public void setParent(UDC parent) {
		this.parent = parent;
	}

	public List<UDC> getChildren() {
		return children;
	}

	public void setChildren(List<UDC> children) {
		this.children = children;
	}

	public List<Resource> getResources() {
		return resources;
	}

	public void setResources(List<Resource> resources) {
		this.resources = resources;
	}

	
   
}