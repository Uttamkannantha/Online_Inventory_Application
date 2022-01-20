package com.app.controller;

import com.amazonaws.AmazonClientException;
import com.amazonaws.AmazonServiceException;
import com.app.dto.Product;
import com.app.services.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.server.ResponseStatusException;
import org.supercsv.io.CsvBeanWriter;
import org.supercsv.io.ICsvBeanWriter;
import org.supercsv.prefs.CsvPreference;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


@Controller
public class ProductController {

    @Autowired
    private ProductService productservice;

    Logger logger = LoggerFactory.getLogger(ProductController.class);


    /**
     * End-point to get require to product detail
     * @param product object to get required data from front end
     * @param model Model to send data across
     * @return loads updateProductDetails file
     */
    @PostMapping("/view")
    public String viewProduct(@ModelAttribute("UpdateProd") Product product, Model model){

        try {
            logger.info("Adding required products in inventory as the model attribute");
            model.addAttribute("Product", productservice.readProduct(product.getProductId()));
        }
        catch (AmazonServiceException e) {
            throw new ResponseStatusException(HttpStatus.valueOf(e.getStatusCode()), e.getMessage(), e);
        } catch (AmazonClientException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage(), e);
        }
        return "updateProductDetails";
    }

    /**
     * End-point to get all the products details
     * @param model Model to send data across
     * @return loads home page
     */
    @GetMapping("/viewAll")
    public String viewProducts(Model model){
        try {
            logger.info("Adding all products in inventory as the model attribute");
            model.addAttribute("AllProducts", productservice.readAllProducts());
        }
        catch (AmazonServiceException e) {
            throw new ResponseStatusException(HttpStatus.valueOf(e.getStatusCode()), e.getMessage(), e);
        } catch (AmazonClientException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage(), e);
        }
        return "home";
    }

    /**
     * Redirection point to addProduct page
     * @param model Model to send data across
     * @return Loads addProduct page
     */
    @GetMapping("/addProducts")
    public String addProduct(Model model){
        model.addAttribute("AllProducts", new Product());
        return "addProduct";
    }

    /**
     * Saves the added product
     * @param product Object to get data from front end
     * @return Loads/redirects to home page
     */
    @PostMapping("/saveProduct")
    public String saveProduct(@ModelAttribute("Products") Product product){
        try {
            logger.info("Saving product: {}",product.getProductId());
            productservice.saveProduct(product);
        }
        catch (AmazonServiceException e) {
            throw new ResponseStatusException(HttpStatus.valueOf(e.getStatusCode()), e.getMessage(), e);
        } catch (AmazonClientException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage(), e);
        }
        return "redirect:/viewAll";
    }

    /**
     * Redirection point to delete product page
     * @param model Model to send data across
     * @return  Loads/redirects to home page
     */
    @GetMapping("/delete")
    public String delete(Model model){
        model.addAttribute("DeleteProduct", new Product());
        return "deleteProduct";
    }

    /**
     * Deletes the asked product
     * @param product Object containing details of to be deleted product
     * @return Loads/redirects to home page
     */
    @PostMapping("/deleteProduct")
    public String deleteProduct(@ModelAttribute("Product") Product product){
        try {
            logger.info("Deleting product: {}",product.getProductId());
            productservice.deleteProduct(product);
        }
        catch (AmazonServiceException e) {
            throw new ResponseStatusException(HttpStatus.valueOf(e.getStatusCode()), e.getMessage(), e);
        } catch (AmazonClientException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage(), e);
        }
        return "redirect:/viewAll";
    }

    /**
     * Updates the value on DB
     * @param model model containing the details of objects to be updated
     * @return loads update page
     */
    @GetMapping("/update")
    public String update(Model model){
        model.addAttribute("UpdateProd", new Product());
        return "update";
    }

    /**
     * Generates csv file containing all products
     * @param response Resonse to download the file
     * @throws IOException Thwows error if error occurs in opening or writing the file
     */
    @GetMapping("/CSV-export")
    public void exportToCSV(HttpServletResponse response) throws IOException {
        response.setContentType("text/csv");
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss");
        String currentDateTime = dateFormatter.format(new Date());

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=products_" + currentDateTime + ".csv";
        response.setHeader(headerKey, headerValue);

        List<Product> products = productservice.readAllProducts();
        logger.info("Downloading the csv with {} products",products.size());
        ICsvBeanWriter csvWriter = new CsvBeanWriter(response.getWriter(), CsvPreference.STANDARD_PREFERENCE);
        String[] csvHeader = {"ProductId", "ProductName", "Quantity", "Price"};
        String[] nameMapping = {"productId", "productName", "quantity", "price"};

        csvWriter.writeHeader(csvHeader);

        for (Product product : products) {
            csvWriter.write(product, nameMapping);
        }

        csvWriter.close();

    }


}