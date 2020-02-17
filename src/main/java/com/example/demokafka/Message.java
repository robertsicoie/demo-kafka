package com.example.demokafka;

import org.json.JSONObject;

import java.time.LocalDateTime;
import java.util.Date;

public class Message {

    private Date timestamp;

    private String message;

    public Message(Date timestamp, String message) {
        this.timestamp = timestamp;
        this.message = message;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public String getMessage() {
        return message;
    }

    @Override
    public String toString() {
        return new JSONObject(this).toString();
    }
}
