package com.app.services;

import com.app.dto.Product;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Interface for all DB access service calls
 */
@Service
public interface ProductService {
    Product readProduct(String productId);
    List<Product> readAllProducts();
    void saveProduct(Product product);
    void deleteProduct(Product product);

}
