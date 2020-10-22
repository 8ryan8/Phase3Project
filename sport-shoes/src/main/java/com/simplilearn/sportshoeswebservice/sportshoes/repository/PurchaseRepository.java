package com.simplilearn.sportshoeswebservice.sportshoes.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.simplilearn.sportshoeswebservice.sportshoes.entity.Purchase;

@RepositoryRestResource
public interface PurchaseRepository extends JpaRepository<Purchase, Long> {
	List<Purchase> findAllByUser_id(long id);
}
