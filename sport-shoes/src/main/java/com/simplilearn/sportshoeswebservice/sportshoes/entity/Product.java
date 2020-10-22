package com.simplilearn.sportshoeswebservice.sportshoes.entity;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;  

@Entity
@Table(name= "product")   
public class Product { 

	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private long id;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "price")
	private BigDecimal price;
	
	@CreationTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "date_added", updatable = false)
	private Date dateAdded;  
	
	@ManyToOne
	private Category category;
	
	@OneToMany (mappedBy="product")
	@JsonIgnore
	private List <PurchaseItem> purchaseItems;

	public Product() {}

	public Product(String name, BigDecimal price, Category category) {
		this.name = name;
		this.price = price;
		this.category = category;
	}

	public long getId() {return id;}
	public String getName() {return name;}
	public BigDecimal getPrice() {return price;}
	public Date getDateAdded() {return dateAdded;}
	public Category getCategory() {return category;}
	public List<PurchaseItem> getPurchaseItems() {return purchaseItems;}
	
	public void setId(long id) {this.id = id;}
	public void setName(String name) {this.name = name;}
	public void setPrice(BigDecimal price) {this.price = price;}
	public void setDateAdded(Date dateAdded) {this.dateAdded = dateAdded;}
	public void setCategory(Category category) {this.category = category;}
	public void setPurchaseItems(List<PurchaseItem> purchaseItems) {this.purchaseItems = purchaseItems;}
	
}

