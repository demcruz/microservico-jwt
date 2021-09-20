package br.com.microservico.pagamento;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class MicroservicePagamanetoApplication {

	public static void main(String[] args) {
		SpringApplication.run(MicroservicePagamanetoApplication.class, args);
	}

}
