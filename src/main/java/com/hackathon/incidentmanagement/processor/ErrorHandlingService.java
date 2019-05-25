package com.hackathon.incidentmanagement.processor;

import com.hackathon.incidentmanagement.event.ErrorDetails;
import com.hackathon.incidentmanagement.stream.ErrorStreams;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;
import org.springframework.util.MimeTypeUtils;

import static org.apache.kafka.common.requests.DeleteAclsResponse.log;

@Service
@Slf4j
public class ErrorHandlingService {

    private final ErrorStreams errorStreams;

    public ErrorHandlingService(ErrorStreams errorStreams) {
        this.errorStreams = errorStreams;
    }

    public void sendErrorDetails(final ErrorDetails errorDetails) {
        log.info("Sending Error Details {}", errorDetails);

        MessageChannel messageChannel = errorStreams.outboundErrors();
        messageChannel.send(MessageBuilder
                .withPayload(errorDetails)
                .setHeader(MessageHeaders.CONTENT_TYPE, MimeTypeUtils.APPLICATION_JSON)
                .build());
    }


}
