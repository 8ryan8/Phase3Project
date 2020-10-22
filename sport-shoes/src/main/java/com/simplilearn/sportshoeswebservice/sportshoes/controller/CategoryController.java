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

import com.simplilearn.sportshoeswebservice.sportshoes.entity.Category;
import com.simplilearn.sportshoeswebservice.sportshoes.exception.CategoryNotFound;
import com.simplilearn.sportshoeswebservice.sportshoes.repository.CategoryRepository; 

@RestController
@RequestMapping("/api/v1")
public class CategoryController {
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	
	@GetMapping("/category/{id}")
	@PreAuthorize("hasAuthority('admin:read')")
	public Category getCategoryById(@PathVariable(value="id") long categoryId){
		return this.categoryRepository.findById(categoryId)
				.orElseThrow(()-> new CategoryNotFound("Category Not Found with ID " + categoryId));
	}
	
	@GetMapping("/category")
	@PreAuthorize("hasAuthority('admin:read')")
	public List<Category> getAllCategories(){
		return this.categoryRepository.findAllByOrderByNameAsc();
	}
	
	@PostMapping("/category")
	@PreAuthorize("hasRole('ADMIN')")
	public Category addCategory(@RequestBody Category category){
		return this.categoryRepository.save(category);
	}
		
		
	@PutMapping("/category/{id}")
	@PreAuthorize("hasRole('ADMIN')")
	public Category updateCategory(@RequestBody Category category, @PathVariable(value="id") long categoryId){
        if (categoryRepository.findById(categoryId).isPresent()){
        	Category existingCategory = categoryRepository.findById(categoryId).get();

        	existingCategory.setId(category.getId());
        	existingCategory.setName(category.getName());

            Category updatedCategory = categoryRepository.save(existingCategory);

            return updatedCategory;
        }else{
            throw new CategoryNotFound("Category Not Found with ID " + categoryId);
        }
	}
	
	@DeleteMapping("/category/{id}")
	@PreAuthorize("hasRole('ADMIN')")
	public void deleteCategory(@PathVariable(value="id") long categoryId){
		Category fetchCategory = this.categoryRepository.findById(categoryId)
		.orElseThrow(()-> new CategoryNotFound("Category Not Found with ID " + categoryId));
		
		this.categoryRepository.delete(fetchCategory);
	}

	
}
