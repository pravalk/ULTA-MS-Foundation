package com.ulta.product.controller;

import static com.ulta.product.constant.ProductConstants.VIEW_PRODUCT_BYPRODUCTKEY_URI;
import static com.ulta.product.constant.ProductConstants.VIEW_PRODUCT_BYPRODUCT_URI;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ulta.product.exception.ProductException;
import com.ulta.product.service.ProductService;
import com.ulta.product.serviceImpl.ClientService;

import io.sphere.sdk.client.SphereClient;
import io.sphere.sdk.products.Product;
import io.sphere.sdk.products.ProductProjection;
import io.sphere.sdk.queries.PagedQueryResult;

/**
 * @author KapilDe
 *
 */

@RestController
@RequestMapping("/")
public class ProductController {

	static Logger log = LoggerFactory.getLogger(ProductController.class);
	SphereClient client = null;

	ProductController() {
		try {
			client = ClientService.createSphereClient();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Autowired
	ProductService ProductService;

	@RequestMapping(value = VIEW_PRODUCT_BYPRODUCTKEY_URI, method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Product> getProductByKey(@PathVariable("productKey") String productKey)
			throws ProductException {

		Product product = null;
		log.info("getProductByKey method start");
		CompletableFuture<Product> products = ProductService.getProductByKey(client, productKey);

		try {

			if (null != products.get()) {
				product = products.get();
				log.info("get the product details successfully.");
			} else {
				log.info("getting product details as null");
				throw new ProductException("Product not found.");
			}

		} catch (ProductException ex) {
			log.error("exception during fetching the product detail-" + ex.getMessage());
			throw new ProductException(ex.getMessage());
		} catch (InterruptedException ex) {
			log.error("exception during fetching the product detail-" + ex.getMessage());
			throw new ProductException(ex.getMessage());

		} catch (ExecutionException ex) {
			log.error("exception during fetching the product detail-" + ex.getMessage());
			throw new ProductException(ex.getMessage());
		}
		log.info("getProductByKey method end");
		return ResponseEntity.ok().body(product);
	}

	@RequestMapping(value = VIEW_PRODUCT_BYPRODUCT_URI, method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<ProductProjection>> getProducts() {

		List<ProductProjection> product = null;
		log.info("getProducts method start");
		CompletableFuture<PagedQueryResult<ProductProjection>> products = ProductService.getProducts(client);

		try {
			if (null != products.get().getResults()) {
				product = products.get().getResults();
				log.info("get the product details successfully.");
			} else {
				log.info("getting product details as null");
				throw new ProductException("Product not found.");
			}
		} catch (InterruptedException ex) {
			log.error("exception during fetching the product detail-" + ex.getMessage());
			throw new ProductException(ex.getMessage());
		} catch (ExecutionException ex) {
			log.error("exception during fetching the product detail-" + ex.getMessage());
			throw new ProductException(ex.getMessage());
		}
		return ResponseEntity.ok().body(product);
	}

	/**
	 * setter method for product service
	 */
	public void setProductService(ProductService productService) {
		this.ProductService = productService;
	}

	/**
	 * setter method for SphereClient
	 */
	public void setSphereClient(SphereClient client) {
		this.client = client;
	}
}