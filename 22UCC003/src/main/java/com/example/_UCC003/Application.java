package com.example._UCC003;

import com.example._UCC003.model.WebhookResponse;
import com.example._UCC003.service.WebhookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Application {

	@Autowired
	private WebhookService webhookService;

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Bean
	public CommandLineRunner run() {
		return args -> {
			// Generate webhook
			WebhookResponse response = webhookService.generateWebhook();
			
			// Submit solution
			webhookService.submitSolution(response.getWebhook(), response.getAccessToken());
			
			// Exit after completion
			System.exit(0);
		};
	}
}
