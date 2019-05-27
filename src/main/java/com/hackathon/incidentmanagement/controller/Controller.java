package com.hackathon.incidentmanagement.controller;

import com.hackathon.incidentmanagement.event.ErrorDetails;
import com.hackathon.incidentmanagement.processor.ErrorHandlingService;
import com.hackathon.incidentmanagement.processor.ServiceNowProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.HashMap;

@RestController
public class Controller {

    @Autowired
    private ErrorHandlingService errorHandlingService;

    @Autowired
    private ServiceNowProcessor processor;

    @GetMapping("/incident/get/{incidentNumber}")
    public String getIncidentDetails(@PathVariable("incidentNumber") String incidentNumber  ){
        return processor.incidentRequest(incidentNumber);
    }

//    @PostMapping("/incident/create")
//    public String createIncident(@RequestBody ErrorDetails errorDetails) throws IOException {
//        return processor.createIncident(errorDetails);
//    }

    @GetMapping("/errorDetails")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void errorDetails() {
        ErrorDetails errorDetails = ErrorDetails.builder().incDescription(new HashMap<String, Integer>() {
            {
                put("NullPointer", 5);
                put("databaseConnectivityError", 8);
                put("timeOutException", 10);
            }
        })
                .build();

        errorHandlingService.sendErrorDetails(errorDetails);
    }
}
