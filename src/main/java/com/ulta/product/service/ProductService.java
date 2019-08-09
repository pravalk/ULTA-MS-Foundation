package com.ulta.product.service;

import java.util.concurrent.CompletableFuture;

import io.sphere.sdk.client.SphereClient;
import io.sphere.sdk.products.Product;

/**
 * 
 * @author BrijendraK
 *
 */
public interface ProductService {

	public CompletableFuture<Product> getProductByKey(SphereClient client, String key);
}
