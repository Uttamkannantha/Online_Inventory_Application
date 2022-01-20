package com.app.services;

import com.app.dto.Product;
import com.app.repository.ProductDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service class to handle all requests
 */
@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductDAO productDao;


    Logger logger = LoggerFactory.getLogger(ProductServiceImpl.class);

    /**
     * Reads require product from DB
     * @param pid Product ID of the product to be read
     * @return Object containing read product details
     */
    @Override
    public Product readProduct(String pid) {

        logger.info("pid  ProductServiceImpl:{}",pid);
        return productDao.readProduct(pid);
    }

    /**
     * Reads all products from DB
     * @return returns list of all products
     */
    @Override
    public List<Product> readAllProducts() {
        return productDao.readAllProducts();
    }

    /**
     * Saves products in DB
     * @param product Product to be saved
     */
    @Override
    public void saveProduct(Product product) {
    productDao.saveProduct(product);
    }

    /**
     * Deletes a product
     * @param product Product to be deleted
     */
    @Override
    public void deleteProduct(Product product) {
        productDao.deleteProduct(product);
    }

}
