package com.ulta.product.service;

import java.util.Locale;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import io.sphere.sdk.client.SphereClient;
import io.sphere.sdk.products.Product;
import io.sphere.sdk.products.ProductProjection;
import io.sphere.sdk.queries.PagedQueryResult;

/**
 * 
 * @author BrijendraK
 *
 */
public interface ProductService {

	public CompletableFuture<Product> getProductByKey(SphereClient client, String key);

	public CompletableFuture<PagedQueryResult<ProductProjection>> getProducts(SphereClient client);
	
	public CompletableFuture<PagedQueryResult<ProductProjection>> findProductsWithCategory (SphereClient client,String ctgId) throws InterruptedException, ExecutionException;
}
