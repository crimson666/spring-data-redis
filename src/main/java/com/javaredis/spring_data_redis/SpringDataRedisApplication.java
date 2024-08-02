package com.javaredis.spring_data_redis;

import com.javaredis.spring_data_redis.entity.Product;
import com.javaredis.spring_data_redis.repository.ProductDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.javaredis.spring_data_redis.repository.ProductDao.HASH_KEY_PRODUCT;

@SpringBootApplication
@RestController
@RequestMapping("/product")
@EnableCaching
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
	@Cacheable(key = "#id", value = HASH_KEY_PRODUCT,unless = "#result.price > 12")
 	public Product getById(@PathVariable int id){
		return dao.findById(id);
	}

	@DeleteMapping("/{id}")
	@CacheEvict(key = "#id", value = HASH_KEY_PRODUCT)
	public String deleteById(@PathVariable int id){
		return dao.delete(id);
	}

	public static void main(String[] args) {
		SpringApplication.run(SpringDataRedisApplication.class, args);
	}

}
