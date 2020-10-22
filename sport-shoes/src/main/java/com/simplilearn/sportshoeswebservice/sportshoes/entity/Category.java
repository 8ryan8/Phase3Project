package com.simplilearn.sportshoeswebservice.sportshoes.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;  
 

@Entity
@Table(name= "category")   
public class Category { 
	
	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private long id;
	
	@Column(name = "name")
	private String name;
	
	@OneToMany(mappedBy="category")
	@JsonIgnore
	private List <Product> products;

	public Category(){}
	

	public Category(String name) {
		this.name = name;
	}

	public long getId() {return id;}
	public String getName() {return name;}
	public List<Product> getProducts() {return products;}
	
	public void setId(long id) {this.id = id;}
	public void setName(String name) {this.name = name;}
	public void setProducts(List<Product> products) {this.products = products;}
	
	
}
