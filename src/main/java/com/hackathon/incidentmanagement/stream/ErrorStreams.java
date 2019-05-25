package com.hackathon.incidentmanagement.stream;


import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.SubscribableChannel;

public interface ErrorStreams {
    String INPUT = "errors-in";
    String OUTPUT = "errors-out";

    @Input(INPUT)
    SubscribableChannel inboundErrors();

    @Output(OUTPUT)
    MessageChannel outboundErrors();

}
