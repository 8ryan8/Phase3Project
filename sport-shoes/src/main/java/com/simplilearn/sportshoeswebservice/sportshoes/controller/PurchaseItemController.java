package com.simplilearn.sportshoeswebservice.sportshoes.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.simplilearn.sportshoeswebservice.sportshoes.entity.PurchaseItem;
import com.simplilearn.sportshoeswebservice.sportshoes.exception.PurchaseItemNotFound;
import com.simplilearn.sportshoeswebservice.sportshoes.repository.PurchaseItemRepository; 

@RestController
@RequestMapping("/api/v1")
public class PurchaseItemController {
	
	@Autowired
	PurchaseItemRepository purchaseItemRepository;
	
	@GetMapping("/purchase-item")
	@PreAuthorize("hasRole('ADMIN')")
	public List<PurchaseItem> getAllPurchaseItems(){
		return this.purchaseItemRepository.findAll();
	}
	
	@GetMapping("/purchase-item/{id}")
	@PreAuthorize("hasRole('ADMIN')")
	public PurchaseItem getPurchaseItemById(@PathVariable(value="id") long purchaseItemId) throws PurchaseItemNotFound{
		return this.purchaseItemRepository.findById(purchaseItemId)
				.orElseThrow(()-> new PurchaseItemNotFound("PurchaseItem Not Found with ID " + purchaseItemId));	
	}
		
		@GetMapping("/purchase-item/category/{id}")
		@PreAuthorize("hasRole('ADMIN')")
		public List<PurchaseItem> getAllItemsByPurchaseId(@PathVariable(value="id") long purchaseId) throws PurchaseItemNotFound{
			if (purchaseItemRepository.findByPurchase_Id(purchaseId).size() > 0) {
				return this.purchaseItemRepository.findByPurchase_Id(purchaseId);
			} else {
				throw new PurchaseItemNotFound("PurchaseItem Not Found with category ID " + purchaseId);
			}
		}
		
		@PutMapping("/purchase-item/{id}")
		@PreAuthorize("hasRole('ADMIN')")
		public PurchaseItem updatePurchaseItem(@RequestBody PurchaseItem purchaseItem, @PathVariable(value="id") long purchaseItemId) throws PurchaseItemNotFound{
	        if (purchaseItemRepository.findById(purchaseItemId).isPresent()){
	        	PurchaseItem existingPurchaseItem = purchaseItemRepository.findById(purchaseItemId).get();
	        	existingPurchaseItem.setPrice(purchaseItem.getPrice());
	        	existingPurchaseItem.setProduct(purchaseItem.getProduct());
	        	existingPurchaseItem.setPurchase(purchaseItem.getPurchase());
	        	existingPurchaseItem.setQty(purchaseItem.getQty());
	        	existingPurchaseItem.setRate(purchaseItem.getRate());
	        	existingPurchaseItem.setUserPurchaseItem(purchaseItem.getUserPurchaseItem());

	        	PurchaseItem purchaseItemUpdated = purchaseItemRepository.save(existingPurchaseItem);

	            return purchaseItemUpdated;
	        }else{
	            throw new PurchaseItemNotFound("PurchaseItem Not Found with ID " + purchaseItemId);
	        }
		}
		
		@DeleteMapping("/purchase-item/{id}")
		@PreAuthorize("hasRole('ADMIN')")
		public void deletePurchaseItem(@PathVariable(value="id") long purchaseItemId) throws PurchaseItemNotFound{
			PurchaseItem fetchPurchaseItem = this.purchaseItemRepository.findById(purchaseItemId)
			.orElseThrow(()-> new PurchaseItemNotFound("PurchaseItem Not Found with ID " + purchaseItemId));
			
			this.purchaseItemRepository.delete(fetchPurchaseItem);
		}
		@DeleteMapping("/purchase-item/purchase/{id}")
		@PreAuthorize("hasRole('ADMIN')")
		public void deletePurchaseItemByPurchaseId(@PathVariable(value="id") long purchaseId) throws PurchaseItemNotFound{
					List<PurchaseItem> fetchPurchaseItem;
					if (this.purchaseItemRepository.findByPurchase_Id(purchaseId).size() >0) {
						fetchPurchaseItem = this.purchaseItemRepository.findByPurchase_Id(purchaseId);
					}
					else {
						throw new PurchaseItemNotFound("PurchaseItem Not Found with ID " + purchaseId);
					}
			
			this.purchaseItemRepository.deleteInBatch(fetchPurchaseItem);
		}

	
}
