package com.ulta.product.controller;

import java.io.IOException;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import static com.ulta.product.constant.ProductConstants.VIEW_PRODUCT_BYPRODUCTKEY_URI;
import com.ulta.product.exception.ProductException;
import com.ulta.product.service.ProductService;
import com.ulta.product.serviceImpl.ClientService;
import io.sphere.sdk.client.SphereClient;
import io.sphere.sdk.products.Product;


/**
 * @author KapilDe
 *
 */
@RestController
@RequestMapping("/")
public class ProductController {

	static Logger log = LoggerFactory.getLogger(ProductController.class);
	SphereClient client = null;
	
	ProductController(){
	try {
		client = ClientService.createSphereClient();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	}
	
	@Autowired
	ProductService ProductService;
	
	@RequestMapping(value=VIEW_PRODUCT_BYPRODUCTKEY_URI, method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Product> getProductByKey(@PathVariable("productKey")  String productKey) throws ProductException {
		
		Product product = null;
        log.info("getProductByKey method start");
        CompletableFuture<Product> products= ProductService.getProductByKey(client,productKey);
      
        try {
        	
        	 if (null!= products.get()) {
        		 product = products.get();
        	 }
        	 else {
        		 throw new ProductException("No data Found");
        	 }
        		 
        	
		}  
        catch(ProductException ex) {
        	throw new ProductException(ex.getMessage());
        }
        catch (InterruptedException ex) {
        	throw new ProductException(ex.getMessage());
			
		} catch (ExecutionException ex) {
			throw new ProductException(ex.getMessage());
		}
        log.info("getProductByKey method start"); 
		return ResponseEntity.ok().body(product);
	}
}
