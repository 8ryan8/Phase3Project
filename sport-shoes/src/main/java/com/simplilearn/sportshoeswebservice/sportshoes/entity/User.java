package com.simplilearn.sportshoeswebservice.sportshoes.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;  


@Entity
@Table(name= "users")   
public class User { 


	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private long id;
	
	@Column(name = "fname")
	private String fname;
	
	@Column(name = "lname")
	private String lname;
	
	@Column(name = "address")
	private String address;

	@Column(name = "age")
	private int age;

	@CreationTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "date_added", updatable = false)
	private Date dateAdded;  
	
	@Column(name = "emailid")
	private String emailId;

	@Column(name = "pwd")
	private String pwd;
	
	@OneToMany (mappedBy="user")
	@JsonIgnore
	private List <Purchase> purchases;
	
	@OneToMany (mappedBy="userPurchaseItem")
	@JsonIgnore
	private List <PurchaseItem> purchaseItems;
	
	public User() {}

	public User(String fname, String lname, String address, int age, String emailId, String pwd) {
		super();
		this.fname = fname;
		this.lname = lname;
		this.address = address;
		this.age = age;
		this.emailId = emailId;
		this.pwd = pwd;
	}

	public long getId() {return id;}
	public String getFname() {return fname;}
	public String getLname() {return lname;}
	public String getAddress() {return address;}
	public int getAge() {return age;}
	public Date getDateAdded() {return dateAdded;}
	public String getEmailId() {return emailId;}
	public String getPwd() {return pwd;}
	public List<Purchase> getPurchases() {return purchases;}

	public void setID(long id) {this.id = id;}
	public void setFname(String fname) {this.fname = fname;}
	public void setLname(String lname) {this.lname = lname;}
	public void setAddress(String address) {this.address = address;}
	public void setAge(int age) {this.age = age;}
	public void setDateAdded(Date dateAdded) {this.dateAdded = dateAdded;}
	public void setEmailId(String emailId) {this.emailId = emailId;}
	public void setPwd(String pwd) {this.pwd = pwd;}
	public void setPurchases(List<Purchase> purchases) {this.purchases = purchases;}
	
	

}
