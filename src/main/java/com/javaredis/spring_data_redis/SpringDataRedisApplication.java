package com.javaredis.spring_data_redis;

import com.javaredis.spring_data_redis.entity.Product;
import com.javaredis.spring_data_redis.repository.ProductDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@SpringBootApplication
@RestController
@RequestMapping("/product")
public class SpringDataRedisApplication {

	@Autowired
	private ProductDao dao;

	@PostMapping
	public Product save(@RequestBody Product pct){
		return dao.save(pct);
	}

	@GetMapping
	public List<Product> getAllProduct(){
		return dao.getAllRecord();
	}

	@GetMapping("/{id}")
	public Product getById(@PathVariable int id){
		return dao.findById(id);
	}

	@DeleteMapping("/{id}")
	public String deleteById(@PathVariable int id){
		return dao.delete(id);
	}

	public static void main(String[] args) {
		SpringApplication.run(SpringDataRedisApplication.class, args);
	}

}
