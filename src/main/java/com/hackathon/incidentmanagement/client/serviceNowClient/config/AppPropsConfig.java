package com.hackathon.incidentmanagement.client.serviceNowClient.config;

/*
 * To load runtime distributed configuration from Consul
 */


import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "config")
public class AppPropsConfig {


    private String URL;

    public String getURL() {
        return URL;
    }

    public void setURL(String URL) {
        this.URL = URL;
    }
}
