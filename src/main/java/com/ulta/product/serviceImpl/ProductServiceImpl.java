package com.ulta.product.serviceImpl;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;

import org.springframework.stereotype.Service;

import com.ulta.product.service.ProductService;

import io.sphere.sdk.client.SphereClient;
import io.sphere.sdk.products.Product;
import io.sphere.sdk.products.queries.ProductByKeyGet;

/**
 * @author KapilDe
 *
 */
@Service
public class ProductServiceImpl implements ProductService {

	@Override
	public CompletableFuture<Product> getProductByKey(SphereClient client, String key) {
		
		 final ProductByKeyGet request = ProductByKeyGet.of(key);
		 CompletionStage<Product> pro= client.execute(request);
		 CompletableFuture<Product> returnProduct =pro.toCompletableFuture();
		 return returnProduct;
	}

	
	
	

	
}
