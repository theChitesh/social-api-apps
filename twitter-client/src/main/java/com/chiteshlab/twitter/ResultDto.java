package com.chiteshlab.twitter;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ResultDto {

    private final String id;
    private final String fromUser;
    private final String twitterMessage;

    private String createdAt;

    @JsonCreator
    public ResultDto(@JsonProperty("id") final String id,
                     @JsonProperty("text") final String twitterMessage,
                     @JsonProperty("fromUser") final String fromUser,
                     @JsonProperty("createdAt") final String createdAt) {
        this.id = id;
        this.twitterMessage = twitterMessage;
        this.fromUser = fromUser;
        this.createdAt = createdAt;
    }

    public String getId() {
        return id;
    }

    public String getTwitterMessage() {
        return twitterMessage;
    }

    public String getFromUser() {
        return fromUser;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    @Override
    public String toString() {
        return "ResultDto{" +
                "id='" + id + '\'' +
                ", twitterMessage='" + twitterMessage + '\'' +
                '}';
    }

}
