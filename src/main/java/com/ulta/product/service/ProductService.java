package com.ulta.product.service;

import java.util.concurrent.CompletableFuture;

import io.sphere.sdk.client.SphereClient;
import io.sphere.sdk.products.Product;
import io.sphere.sdk.products.ProductProjection;
import io.sphere.sdk.queries.PagedQueryResult;
import io.sphere.sdk.search.PagedSearchResult;

/**
 * 
 * @author BrijendraK
 *
 */
public interface ProductService {

	public CompletableFuture<Product> getProductByKey(SphereClient client,String key);

	public CompletableFuture<PagedQueryResult<ProductProjection>> getProducts(SphereClient client);
}
