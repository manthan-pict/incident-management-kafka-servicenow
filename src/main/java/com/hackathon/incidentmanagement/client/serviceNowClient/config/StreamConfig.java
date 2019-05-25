package com.hackathon.incidentmanagement.client.serviceNowClient.config;

import com.hackathon.incidentmanagement.stream.ErrorStreams;
import org.springframework.cloud.stream.annotation.EnableBinding;

@EnableBinding(ErrorStreams.class)
public class StreamConfig {
}
