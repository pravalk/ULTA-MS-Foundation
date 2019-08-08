package com.ulta.product.serviceImpl;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
	static Logger log = LoggerFactory.getLogger(ProductServiceImpl.class);
	@Override
	public CompletableFuture<Product> getProductByKey(SphereClient client, String key) {
		 log.info("getProductByKey method start");
		 final ProductByKeyGet request = ProductByKeyGet.of(key);
		 CompletionStage<Product> pro= client.execute(request);
		 CompletableFuture<Product> returnProduct =pro.toCompletableFuture();
		 log.info("getProductByKey method end");
		 return returnProduct;
	}

	
	
	

	
}
