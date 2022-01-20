package com.app.repository;

import com.app.dto.Product;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Interface of all DB access calls
 */
@Repository
public interface ProductDAO {
     Product readProduct(String productId);
     List<Product> readAllProducts();
     void saveProduct(Product product);
     void deleteProduct(Product product);
}