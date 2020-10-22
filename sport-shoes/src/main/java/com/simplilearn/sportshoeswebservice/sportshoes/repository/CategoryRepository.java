package com.simplilearn.sportshoeswebservice.sportshoes.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.simplilearn.sportshoeswebservice.sportshoes.entity.Category;

@RepositoryRestResource
public interface CategoryRepository extends JpaRepository<Category, Long> {
	List<Category> findAllByOrderByNameAsc();
}
