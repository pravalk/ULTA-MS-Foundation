package com.ulta.product.serviceImpl;

import java.io.IOException;
import java.util.Properties;

import io.sphere.sdk.client.SphereClient;
import io.sphere.sdk.client.SphereClientConfig;
import io.sphere.sdk.client.SphereClientFactory;

/**
 * 
 * @author BrijendraK
 *
 */
public class ClientService {
	/**
	 * Creates a blocking sphere client
	 * 
	 * @return Sphere client
	 * @throws IOException
	 */
	public static SphereClient createSphereClient() throws IOException {

		final SphereClientConfig clientConfig = loadCTPClientConfig();
		SphereClient client = SphereClientFactory.of().createClient(clientConfig);
		return client;

	}

	/**
	 * Sets a sphere client configuration
	 * 
	 * @return sphere client configuration
	 * @throws IOException
	 */
	private static SphereClientConfig loadCTPClientConfig() throws IOException {
		final Properties prop = new Properties();
		prop.load(ClientService.class.getResourceAsStream("/dev.properties"));
		SphereClientConfig config = SphereClientConfig.ofProperties(prop, "ct");
		return config;

	}
}