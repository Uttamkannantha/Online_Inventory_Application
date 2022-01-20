package com.app.repository;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.app.dto.Product;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * All DB call are here
 */
@Repository
public class ProductDAOImpl implements ProductDAO {
    Logger logger = LoggerFactory.getLogger(ProductDAOImpl.class);

    @Autowired
    private DynamoDBMapper dynamoDBMapper;

    /**
     * DB calls to read product
     * @param productId Product details of to be read product
     * @return read product
     */
    @Override
    public Product readProduct(String productId) {

        return dynamoDBMapper.load(Product.class, productId);
    }

    /**
     * Reads all product
     * @return List of read products
     */
    @Override
    public List<Product> readAllProducts() {

        return dynamoDBMapper.scan(Product.class,new DynamoDBScanExpression());
    }

    /**
     * Saves product
     * @param product Details of the product to be saved
     */
    @Override
    public void saveProduct(Product product) {

        dynamoDBMapper.save(product);
    }

    /**
     * Deletes product
     * @param product Details of the product to be deleted
     */
    @Override
    public void deleteProduct(Product product) {
        dynamoDBMapper.delete(product);
    }


}