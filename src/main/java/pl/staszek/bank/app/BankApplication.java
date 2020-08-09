package pl.staszek.bank.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.reactive.function.client.WebClient;

@SpringBootApplication
public class BankApplication {

	@Bean
	public WebClient.Builder getWebClientBuilder(){
		return WebClient.builder();
	}

	public static void main(String[] args) {
		SpringApplication.run(BankApplication.class, args);
	}

}
