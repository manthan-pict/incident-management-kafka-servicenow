package com.hackathon.incidentmanagement;

import com.hackathon.incidentmanagement.client.serviceNowClient.config.AppPropsConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(value = AppPropsConfig.class)
public class IncidentManagementApplication {

	public static void main(String[] args) {
		SpringApplication.run(IncidentManagementApplication.class, args);
	}

}
