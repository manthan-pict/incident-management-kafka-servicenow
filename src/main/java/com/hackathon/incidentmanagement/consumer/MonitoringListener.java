package com.hackathon.incidentmanagement.consumer;

import com.hackathon.incidentmanagement.client.serviceNowClient.RestClient;
import com.hackathon.incidentmanagement.event.ErrorDetails;
import com.hackathon.incidentmanagement.processor.ServiceNowProcessor;
import com.hackathon.incidentmanagement.stream.ErrorStreams;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Component
@Slf4j
public class MonitoringListener {

    @Autowired
    private RestClient restClient;

    @Autowired
    private ServiceNowProcessor processor;

    Map<String, String> assignmentGroup= new HashMap<String, String>() {
        {
            put("NullPointer", "BAPI");
            put("databaseConnectivityError", "ETL");
            put("timeOutException", "INFRA");
        }
    };;


    @StreamListener(ErrorStreams.INPUT)
    public void handleErrors(@Payload ErrorDetails errorDetails) throws IOException {
        log.info("Received Errors: {}", errorDetails);

        if(errorDetails != null) {
//           String incidentNumber =  restClient.sendIncidentNumber(errorDetails);
//            boolean matcher = Pattern.matches("INC[0-9]+", incidentNumber);
//           if(!matcher) {
//               processor.createIncident(errorDetails);
//           } else {
//               log.info("incident already exist for the given error {} with incidentId {}", errorDetails.getErrorDescription(), incidentNumber);
//           }

            errorDetails.getIncDescription().forEach((e,v) -> {
                try {

                    String incNumber = processor.createIncident(e.toString(),assignmentGroup.get(e).toString());
                    log.info("Received incident number: {}", incNumber);
                    restClient.sendIncidentNumber(incNumber);


                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            });

            //processor.createIncident(errorDetails);
        }





    }
}


//https://dzone.com/articles/spring-cloud-stream-with-kafka