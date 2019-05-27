package com.hackathon.incidentmanagement.processor;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hackathon.incidentmanagement.client.serviceNowClient.RestClient;
import com.hackathon.incidentmanagement.event.ErrorDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Map;

@Component
public class ServiceNowProcessor {

    @Autowired
    private RestClient restClient;

    ObjectMapper objectMapper = new ObjectMapper();

    public String incidentRequest(String incidentNumber){
        return restClient.getIncidetDetails(incidentNumber);
    }

    public String createIncident(String errorDesc, String assignmrntGroup) throws IOException {
        //String response = restClient.createIncident("Null Pointer Exception", "BAPI");
        String response = restClient.createIncident(errorDesc, assignmrntGroup);
        Map map = objectMapper.readValue(response, Map.class);
        Map result = (Map) map.get("result");
       return result.get("number").toString();
    }
}
