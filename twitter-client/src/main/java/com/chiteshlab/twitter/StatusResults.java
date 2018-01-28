package com.chiteshlab.twitter;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class StatusResults {

    private final List<ResultDto> data;

    @JsonCreator
    public StatusResults(@JsonProperty("statuses") final List<ResultDto> data) {
        this.data = data;
    }

    public List<ResultDto> getData() {
        return data;
    }
}
