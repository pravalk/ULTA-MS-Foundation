package com.ulta.product.service;

import io.sphere.sdk.client.SphereClient;
import io.sphere.sdk.products.queries.ProductProjectionByIdGet;

public interface ProductService {

	public ProductProjectionByIdGet getProductById(SphereClient client,String sku);
}
