package com.javaredis.spring_data_redis.repository;

import com.javaredis.spring_data_redis.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.sql.SQLOutput;
import java.util.List;

@Repository
public class ProductDao {
    @Autowired
    private RedisTemplate template;
    public static final String HASH_KEY_PRODUCT = "Product";

    public Product save(Product product){
        template.opsForHash().put(HASH_KEY_PRODUCT,product.getId(),product);
        return product;
    }
    public List<Product> getAllRecord(){
        return template.opsForHash().values(HASH_KEY_PRODUCT);
    }

    public Product findById(int Id){
        System.out.println("Getting frm the DB");
        return (Product) template.opsForHash().get(HASH_KEY_PRODUCT,Id);
    }

    public String delete(int Id){
        template.opsForHash().delete(HASH_KEY_PRODUCT,Id);
        return "Deletion done";
    }

}
