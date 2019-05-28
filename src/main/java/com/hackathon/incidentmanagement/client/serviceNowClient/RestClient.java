package com.hackathon.incidentmanagement.client.serviceNowClient;

import com.hackathon.incidentmanagement.event.ErrorDetails;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;

@Component
@RefreshScope
public class RestClient {

    @Autowired
    private RestTemplate restTemplate;

    @Value("${serviceNow.username}")
    private String username;
    @Value("${serviceNow.password}")
    private String password;
    @Value("${serviceNow.url}")
    private String url;

    @Value("${consul.URL}")
    private String curiousMonitorURL;

    @Value("${config.URL:localhost}")
    String URL;





    public String getIncidetDetails(String incidentNumber){
        String tableName = "incident";
        UriComponents uriComponents = UriComponentsBuilder.newInstance()
                .scheme("https")
                .host(url)
                .path("/api/now/table/")
                .path(tableName)
                .query("sysparm_query=incident=INC0010004")
                .build();


        ResponseEntity<String> responseEntity = restTemplate.exchange(uriComponents.toUriString(), HttpMethod.GET,new HttpEntity<String>(createHeaders()),String.class);
        return  responseEntity.toString();
    }


    public String createIncident(String description, String assignmentGroup){
        String tableName = "incident";
        UriComponents uriComponents = UriComponentsBuilder.newInstance()
                .scheme("https")
                .host(url)
                .path("/api/now/table/")
                .path(tableName)
                .build();
        Map<String, String> requestBody= new HashMap<>();
        requestBody.put("short_description", description);
        requestBody.put("assignment_group",assignmentGroup);

        ResponseEntity<String> responseEntity = restTemplate.exchange(uriComponents.toUriString(), HttpMethod.POST,new HttpEntity<>(requestBody,createHeaders()),String.class);
        return responseEntity.getBody();

    }

    private HttpHeaders createHeaders(){
        return new HttpHeaders() {{
            String auth = username + ":" + password;
            byte[] encodedAuth = Base64.encodeBase64(
                    auth.getBytes(Charset.forName("US-ASCII")) );
            String authHeader = "Basic " + new String( encodedAuth );
            set( "Authorization", authHeader );
            set("Content-Type","application/json");
        }};
    }

    public String sendIncidentNumber(String incNumber) {

        UriComponents uriComponents = UriComponentsBuilder.newInstance()
                .scheme("https")
                .host(curiousMonitorURL)
                .path("/cron")
                .build();

        Map<String, String> requestBody= new HashMap<>();
        //requestBody.put("errorDescription", errorDetails.getErrorDescription());
        //requestBody.put("assignmentGroup", errorDetails.getAssignmentGroup());


        ResponseEntity<String> responseEntity = restTemplate.exchange(uriComponents.toUriString(), HttpMethod.GET,new HttpEntity<>(requestBody, createHeaders()),String.class);
        return  responseEntity.toString();

    }
}
