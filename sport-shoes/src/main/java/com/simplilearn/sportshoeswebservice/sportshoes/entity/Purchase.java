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
@Table(name= "purchases")   
public class Purchase { 

	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private long id;
	
	@ManyToOne
	private User user;
	
	@CreationTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "date", updatable = false)
	private Date date;
	
	@Column(name = "gross_total")
	private BigDecimal total; 
	
	@OneToMany(mappedBy="purchase")
	@JsonIgnore
	private List <PurchaseItem> purchaseItems;
	
	public Purchase() {}

	public Purchase(User user, BigDecimal total) {
		this.user = user;
		this.total = total;
	}


	public long getId() {return id;}
	public User getUser() {return user;}
	public Date getDate() {return date;}
	public BigDecimal getTotal() {return total;}
	public List<PurchaseItem> getPurchaseItems() {return purchaseItems;}
	

	public void setId(long id) {this.id = id;}
	public void setUser(User user) {this.user = user;}
	public void setDate(Date date) {this.date = date;}
	public void setTotal(BigDecimal total) {this.total = total;}
	public void setPurchaseItems(List<PurchaseItem> purchaseItems) {this.purchaseItems = purchaseItems;}
}