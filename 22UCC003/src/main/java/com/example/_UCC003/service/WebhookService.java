package com.example._UCC003.service;

import com.example._UCC003.model.WebhookRequest;
import com.example._UCC003.model.WebhookResponse;
import com.example._UCC003.model.SolutionRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class WebhookService {
    private static final String GENERATE_WEBHOOK_URL = "https://bfhldevapigw.healthrx.co.in/hiring/generateWebhook/JAVA";
    private static final String SUBMIT_SOLUTION_URL = "https://bfhldevapigw.healthrx.co.in/hiring/testWebhook/JAVA";
    private static final String SQL_SOLUTION = "SELECT F.max_sal AS SALARY, CONCAT(e.first_name,' ',e.last_name) AS NAME, TIMESTAMPDIFF(YEAR,e.dob,CURDATE()) AS AGE, d.department_name AS DEPARTMENT_NAME FROM (SELECT MAX(amount) AS max_sal FROM payments WHERE DAY(payment_time) <> 1) AS F JOIN payments AS p ON p.amount = F.max_sal AND DAY(p.payment_time) <> 1 JOIN employee AS e ON e.emp_id = p.emp_id JOIN department AS d ON d.department_id = e.department";

    @Autowired
    private RestTemplate restTemplate;

    public WebhookResponse generateWebhook() {
        WebhookRequest request = new WebhookRequest(
            "John Doe",
            "REG12347",
            "john@example.com"
        );

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<WebhookRequest> entity = new HttpEntity<>(request, headers);

        return restTemplate.postForObject(GENERATE_WEBHOOK_URL, entity, WebhookResponse.class);
    }

    public void submitSolution(String webhookUrl, String accessToken) {
        SolutionRequest request = new SolutionRequest(SQL_SOLUTION);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization", accessToken);
        HttpEntity<SolutionRequest> entity = new HttpEntity<>(request, headers);

        restTemplate.postForObject(webhookUrl, entity, String.class);
    }
} 