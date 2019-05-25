package com.hackathon.incidentmanagement.event;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @ToString @Builder
public class ErrorDetails {
    private String errorDescription;
    private String assignmentGroup;


}
