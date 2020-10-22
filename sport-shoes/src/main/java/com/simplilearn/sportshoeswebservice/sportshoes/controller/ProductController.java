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

import com.simplilearn.sportshoeswebservice.sportshoes.entity.Product;
import com.simplilearn.sportshoeswebservice.sportshoes.exception.ProductNotFound;
import com.simplilearn.sportshoeswebservice.sportshoes.repository.ProductRepository;
 
@RestController
@RequestMapping("/api/v1")
public class ProductController {
	
	@Autowired
	private ProductRepository productRepository;
	
	@GetMapping("/products")
	@PreAuthorize("hasRole('ADMIN')")
	public List<Product> getAllProducts(){
		return this.productRepository.findAll();
	}
	
	@GetMapping("/products/{id}")
	@PreAuthorize("hasRole('ADMIN')")
	public Product getProductById(@PathVariable(value="id") long productId){
		return this.productRepository.findById(productId)
				.orElseThrow(()-> new ProductNotFound("Product Not Found with ID " + productId));
	}
	
	@GetMapping("/products/category/{id}")
	@PreAuthorize("hasRole('ADMIN')")
	public List<Product> getProductByCategoryId(@PathVariable(value="id") long categoryId){
		if (productRepository.findByCategory_Id(categoryId).size() > 0) {
			return this.productRepository.findByCategory_Id(categoryId);
		} else {
			throw new ProductNotFound("Product Not Found with category ID " + categoryId);
		}
	}
	
	@PostMapping("/products")
	@PreAuthorize("hasRole('ADMIN')")
	public Product addProduct(@RequestBody Product product){
		return this.productRepository.save(product);
	}
	
	@PutMapping("/products/{id}")
	@PreAuthorize("hasRole('ADMIN')")
	public Product updateProduct(@RequestBody Product product, @PathVariable(value="id") long productId){
        if (productRepository.findById(productId).isPresent()){
        	Product existingProduct = productRepository.findById(productId).get();

        	existingProduct.setId(product.getId());
        	existingProduct.setCategory(product.getCategory());
        	existingProduct.setName(product.getName());
        	existingProduct.setPrice(product.getPrice());

        	Product updatedProduct = productRepository.save(existingProduct);

            return updatedProduct;
        }else{
            throw new ProductNotFound("Product Not Found with ID " + productId);
        }
	}
	
	@DeleteMapping("/products/{id}")
	@PreAuthorize("hasRole('ADMIN')")
	public void deleteProduct(@PathVariable(value="id") long productId){
		Product fetchProduct = this.productRepository.findById(productId)
		.orElseThrow(()-> new ProductNotFound("Product Not Found with ID " + productId));
		
		this.productRepository.delete(fetchProduct);
	}
	
}
