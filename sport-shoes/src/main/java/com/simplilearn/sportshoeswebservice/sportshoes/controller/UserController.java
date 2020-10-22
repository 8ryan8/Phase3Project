package com.simplilearn.sportshoeswebservice.sportshoes.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.simplilearn.sportshoeswebservice.sportshoes.entity.User;
import com.simplilearn.sportshoeswebservice.sportshoes.exception.PurchaseItemNotFound;
import com.simplilearn.sportshoeswebservice.sportshoes.exception.UserNotFound;
import com.simplilearn.sportshoeswebservice.sportshoes.repository.UserRepository; 

@RestController
@RequestMapping("/api/v1")
public class UserController {
	
	@Autowired
	UserRepository userRepository;
	
	@GetMapping("/users/{id}")
	@PreAuthorize("hasAuthority('admin:read')")
	public User getUserById(@PathVariable(value="id") long userId){
		return this.userRepository.findById(userId)
				.orElseThrow(()-> new UserNotFound("User Not Found with ID " + userId));
	}
	
	@GetMapping("/users/email/{emailId:.+}")
	@PreAuthorize("hasAuthority('admin:read')")
	public List<User> getUserByEmailId(@PathVariable(value="emailId") String emailId) {
		if (this.userRepository.findAllByEmailId(emailId).size() > 0) {	
		return this.userRepository.findAllByEmailId(emailId);
		} else {
			throw new UserNotFound("User Not Found with email " + emailId);
		}
	}
	
	@PutMapping("/users/{id}")
	@PreAuthorize("hasRole('ADMIN')")
	public User updateUser(@RequestBody User user, @PathVariable(value="id") long userId) throws PurchaseItemNotFound{
        if (userRepository.findById(userId).isPresent()){
        	User existingUser = userRepository.findById(userId).get();
        	existingUser.setAddress(user.getAddress());
        	existingUser.setAge(user.getAge());
        	existingUser.setEmailId(user.getEmailId());
        	existingUser.setFname(user.getFname());
        	existingUser.setLname(user.getLname());
        	existingUser.setPwd(user.getPwd());
        	
        	User userUpdated = userRepository.save(existingUser);

            return userUpdated;
        }else{
            throw new PurchaseItemNotFound("User Not Found with ID " + userId);
        }
	}

	@GetMapping("/users")
	@PreAuthorize("hasAuthority('admin:read')")
	public List<User> getAllUsers(){
		return this.userRepository.findAll();
		//return this.userRepository.findAllByOrderByDateAddedAsc();
	}
	
}
