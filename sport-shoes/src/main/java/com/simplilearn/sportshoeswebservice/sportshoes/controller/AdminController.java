package com.simplilearn.sportshoeswebservice.sportshoes.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.simplilearn.sportshoeswebservice.sportshoes.entity.Admin;
import com.simplilearn.sportshoeswebservice.sportshoes.exception.AdminNotFound;
import com.simplilearn.sportshoeswebservice.sportshoes.repository.AdminRepository;

@RestController
@RequestMapping("/api/v1")
public class AdminController {

	@Autowired
	AdminRepository adminRepository;
	
	@GetMapping("/admin/{id}")
	@PreAuthorize("hasAuthority('admin:read')")
	public Admin getAdminById(@PathVariable(value="id") long adminId){
		return this.adminRepository.findById(adminId)
				.orElseThrow(()-> new AdminNotFound("Admin Not Found with ID " + adminId));
	}
	
	
	@PutMapping("/admin/{id}")
	@PreAuthorize("hasRole('ADMIN')")
	public Admin updateAdminPwd(@RequestBody Admin admin, @PathVariable(value="id") long adminId){
        if (adminRepository.findById(adminId).isPresent()){
        	Admin existingAdmin = adminRepository.findById(adminId).get();
        	existingAdmin.setAdminPwd(admin.getAdminPwd());

            Admin updatedAdmin = adminRepository.save(existingAdmin);

            return updatedAdmin;
        }else{
            throw new AdminNotFound("Admin Not Found with ID " + adminId);
        }
	}
}

