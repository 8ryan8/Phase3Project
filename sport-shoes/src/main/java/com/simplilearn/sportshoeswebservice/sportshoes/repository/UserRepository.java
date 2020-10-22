package com.simplilearn.sportshoeswebservice.sportshoes.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.simplilearn.sportshoeswebservice.sportshoes.entity.User;

@RepositoryRestResource
public interface UserRepository extends JpaRepository<User, Long> {
	List<User> findAllByEmailId(String emailId);
	List<User> findAllByOrderByDateAddedAsc();
}
