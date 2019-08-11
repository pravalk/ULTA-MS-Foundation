package com.ulta.product.controller;


import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;
import java.util.concurrent.CompletableFuture;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import com.ulta.product.exception.ProductException;
import com.ulta.product.service.ProductService;
import io.sphere.sdk.client.SphereClient;
import io.sphere.sdk.products.Product;

@SpringBootTest
public class ProductControllerTest {

	ProductController productController = new ProductController();

	@Mock
	SphereClient client;
	
	@Mock
	ProductService productService;
	
	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
		productController.setSphereClient(client);
	 	productController.setProductService(productService);
	}
	
	
	@Test(expected=ProductException.class)
	public void testgetProductBykeyWhenProductGetisNull() {
	
		CompletableFuture<Product> products=new CompletableFuture<Product>();
		Product value=null;
		products.complete(value);
		String key="Liquid-exception";
		when(productService.getProductByKey(client, key)).thenReturn(products);
		productController.getProductByKey(key);
	}
	
	@Test()
	public void testgetProductBykey() {
	
		CompletableFuture<Product> products=new CompletableFuture<Product>();
		Product value=Mockito.mock(Product.class);
		products.complete(value);
		String key="Liquid";
		when(productService.getProductByKey(client, key)).thenReturn(products);
		ResponseEntity<Product> result =productController.getProductByKey(key);
		assertEquals(HttpStatus.OK, result.getStatusCode());
	}
}
