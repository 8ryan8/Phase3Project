package com.simplilearn.sportshoeswebservice.sportshoes.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.simplilearn.sportshoeswebservice.sportshoes.entity.PurchaseItem;

@RepositoryRestResource
public interface PurchaseItemRepository extends JpaRepository<PurchaseItem, Long> {
	List<PurchaseItem> findByPurchase_Id (long purchaseId);
}
