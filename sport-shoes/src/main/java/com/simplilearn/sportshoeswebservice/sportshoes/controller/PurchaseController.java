package com.simplilearn.sportshoeswebservice.sportshoes.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.simplilearn.sportshoeswebservice.sportshoes.entity.Purchase;
import com.simplilearn.sportshoeswebservice.sportshoes.exception.PurchaseNotFound;
import com.simplilearn.sportshoeswebservice.sportshoes.repository.PurchaseRepository;

@RestController
@RequestMapping("/api/v1")
public class PurchaseController {
	
	@Autowired
	private PurchaseRepository purchaseRepository;
	
	@GetMapping("/purchase/{id}")
	@PreAuthorize("hasRole('ADMIN')")
	public Purchase getPurchaseById(@PathVariable(value="id") long purchaseId){
		return this.purchaseRepository.findById(purchaseId)
				.orElseThrow(()-> new PurchaseNotFound("Purchase Not Found with ID " + purchaseId));
	}

	@GetMapping("/purchase")
	@PreAuthorize("hasRole('ADMIN')")
	public List<Purchase> getAllPurchase(){
		return this.purchaseRepository.findAll();
	}	
	
	@GetMapping("/purchase/user/{id}")
	@PreAuthorize("hasRole('ADMIN')")
	public List<Purchase> getPurchaseByUserId(@PathVariable(value="id") long userId){
		if (this.purchaseRepository.findAllByUser_id(userId).size() > 0) {
			return this.purchaseRepository.findAllByUser_id(userId);
		} else {
			throw new PurchaseNotFound("Purchase Not Found with User ID " + userId);
		}	
	}
	
	@PostMapping("/purchase")
	@PreAuthorize("hasRole('ADMIN')")
	public Purchase addPurchase(@RequestBody Purchase purchase){
		return this.purchaseRepository.save(purchase);
	}

	@PutMapping("/purchase/{id}")
	@PreAuthorize("hasRole('ADMIN')")
	public Purchase updatePurchase(@RequestBody Purchase purchase, @PathVariable(value="id") long purchaseId){
        if (purchaseRepository.findById(purchaseId).isPresent()){
        	Purchase existingPurchase = purchaseRepository.findById(purchaseId).get();

        	existingPurchase.setId(purchase.getId());
        	existingPurchase.setUser(purchase.getUser());
        	existingPurchase.setTotal(purchase.getTotal());

        	Purchase updatedPurchase = purchaseRepository.save(existingPurchase);

            return updatedPurchase;
        }else{
            throw new PurchaseNotFound("Purchase Not Found with ID " + purchaseId);
        }
	}
	
	@DeleteMapping("/purchase/{id}")
	@PreAuthorize("hasRole('ADMIN')")
	public void deletePurchase(@PathVariable(value="id") long purchaseId){
		Purchase fetchPurchase = this.purchaseRepository.findById(purchaseId)
		.orElseThrow(()-> new PurchaseNotFound("Purchase Not Found with ID " + purchaseId));
		
		this.purchaseRepository.delete(fetchPurchase);
	} 
}
