package com.ulta.product.service;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;

import io.sphere.sdk.client.SphereClient;
import io.sphere.sdk.products.Product;

public interface ProductService {

	public CompletableFuture<Product> getProductById(SphereClient client,String sku);
}
