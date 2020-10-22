package com.simplilearn.sportshoeswebservice.sportshoes.entity;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity
@Table(name= "purchase_items")   
public class PurchaseItem { 
	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private long id;

	@ManyToOne
	private Purchase purchase;
	
	@ManyToOne
	private Product product;
	
	@ManyToOne
	private User userPurchaseItem;

	@Column(name = "rate")
	private BigDecimal rate;

	@Column(name = "qty")
	private int qty;

	@Column(name = "price")
	private BigDecimal price;
	
	public PurchaseItem() {}

	public PurchaseItem(Purchase purchase, Product product, int qty) {
		super();
		this.purchase = purchase;
		this.product = product;
		this.qty = qty;
		this.userPurchaseItem = purchase.getUser();
		this.rate = product.getPrice();
		this.price = BigDecimal.valueOf(this.qty).multiply(this.rate);
	}

	public long getId() {return id;}
	public Purchase getPurchase() {return purchase;}
	public Product getProduct() {return product;}
	public User getUserPurchaseItem() {return userPurchaseItem;}
	public BigDecimal getRate() {return rate;}
	public int getQty() {return qty;}
	public BigDecimal getPrice() {return price;}
	
	public void setId(long id) {this.id = id;}
	public void setPurchase(Purchase purchase) {this.purchase = purchase;}
	public void setProduct(Product product) {this.product = product;}
	public void setUserPurchaseItem(User userPurchaseItem) {this.userPurchaseItem = userPurchaseItem;}
	public void setRate(BigDecimal rate) {this.rate = rate;}
	public void setQty(int qty) {this.qty = qty;}
	public void setPrice(BigDecimal price) {this.price = price;}	
}
