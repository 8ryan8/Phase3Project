package com.simplilearn.sportshoeswebservice.sportshoes.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.simplilearn.sportshoeswebservice.sportshoes.entity.Admin;

@RepositoryRestResource
public interface AdminRepository extends JpaRepository<Admin, Long> {
}
