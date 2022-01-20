package com.app.dto;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import org.springframework.stereotype.Component;


/**
 * Product class to map attributes of products table
 */
@Component
@DynamoDBTable(tableName = "products")
public class Product {

    @DynamoDBHashKey(attributeName = "productId")
    private String productId;
    @DynamoDBAttribute(attributeName = "productName")
    private String productName;
    @DynamoDBAttribute(attributeName = "quantity")
    private int quantity;
    @DynamoDBAttribute(attributeName = "price")
    private int price;

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
