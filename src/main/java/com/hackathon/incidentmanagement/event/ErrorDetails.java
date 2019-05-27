package com.hackathon.incidentmanagement.event;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Map;

@Getter @Setter @ToString @Builder
public class ErrorDetails {
    String imageId;
    Map<String, Integer> incDescription;

}
