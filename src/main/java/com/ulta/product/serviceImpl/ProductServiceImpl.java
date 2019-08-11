package com.ulta.product.serviceImpl;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.ulta.product.exception.ProductException;
import com.ulta.product.service.ProductService;

import io.sphere.sdk.client.SphereClient;
import io.sphere.sdk.products.Product;
import io.sphere.sdk.products.ProductProjection;
import io.sphere.sdk.products.queries.ProductByKeyGet;
import io.sphere.sdk.products.queries.ProductProjectionQuery;
import io.sphere.sdk.queries.PagedQueryResult;

/**
 * @author KapilDe
 *
 */
@Service
public class ProductServiceImpl implements ProductService {
	static Logger log = LoggerFactory.getLogger(ProductServiceImpl.class);

	@Override
	public CompletableFuture<Product> getProductByKey(SphereClient client, String key) throws ProductException {
		log.info("getProductByKey method start");
		final ProductByKeyGet request = ProductByKeyGet.of(key);
		CompletionStage<Product> pro = client.execute(request);
		CompletableFuture<Product> returnProduct = null;
		if (null != pro) {
			returnProduct = pro.toCompletableFuture();
		} else
			throw new ProductException("Product Data is empty");

		log.info("getProductByKey method end");
		return returnProduct;
	}

	@Override
	public CompletableFuture<PagedQueryResult<ProductProjection>> getProducts(SphereClient client) {
		log.info("getProducts method start");

		final ProductProjectionQuery pro = ProductProjectionQuery.ofCurrent();
		CompletionStage<PagedQueryResult<ProductProjection>> result = client.execute(pro);
		CompletableFuture<PagedQueryResult<ProductProjection>> returnProduct = null;
		if (null != result) {
			returnProduct = result.toCompletableFuture();
		} else
			throw new ProductException("Product Data is empty");

		log.info("getProducts method end");
		return returnProduct;
	}

}