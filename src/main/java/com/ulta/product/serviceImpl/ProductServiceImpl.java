package com.ulta.product.serviceImpl;

import org.springframework.stereotype.Service;

import com.ulta.product.service.ProductService;

import io.sphere.sdk.client.SphereClient;
import io.sphere.sdk.products.ProductProjectionType;
import io.sphere.sdk.products.queries.ProductProjectionByIdGet;

@Service
public class ProductServiceImpl implements ProductService {

	@Override
	public ProductProjectionByIdGet getProductById(SphereClient client, String sku) {
		final ProductProjectionByIdGet fetch = ProductProjectionByIdGet.of("id", ProductProjectionType.CURRENT);
		System.out.println(fetch.toString());
		return fetch;
	}

	
	
	

	
}
