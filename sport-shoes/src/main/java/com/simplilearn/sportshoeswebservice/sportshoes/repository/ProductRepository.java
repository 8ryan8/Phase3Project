package com.simplilearn.sportshoeswebservice.sportshoes.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.simplilearn.sportshoeswebservice.sportshoes.entity.Product;

@RepositoryRestResource
public interface ProductRepository extends JpaRepository<Product, Long> {
	List<Product> findByCategory_Id(long categoryId);
}
