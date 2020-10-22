package com.simplilearn.sportshoeswebservice.sportshoes.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;  


@Entity
@Table(name= "admin")   
public class Admin { 

	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private long id;
	
	@Column(name = "admin_id")
	private String adminId;
	
	@Column(name = "admin_pwd")
	private String pwd;

	
	public Admin() {}
	
	public Admin(String adminId, String pwd) {
		this.adminId = adminId;
		this.pwd = pwd;
	}

	public long getId() {return this.id; }
	public String getAdminId() { return this.adminId;}
	public String getAdminPwd() { return this.pwd;}
	
	public void setId(long id) { this.id = id;}
	public void setAdminId(String value) { this.adminId= value;}
	public void setAdminPwd(String value) { this.pwd = value;}
}
