package com.simplilearn.sportshoeswebservice.sportshoes.repository;

import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.simplilearn.sportshoeswebservice.sportshoes.SportShoesApplication;

@ExtendWith(SpringExtension.class)
@SpringBootTest (classes=SportShoesApplication.class)
class ProductRepositoryTest {
	
	//private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	ProductRepository repository;


}
