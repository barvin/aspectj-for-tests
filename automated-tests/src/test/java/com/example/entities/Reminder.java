package com.example.entities;

import lombok.Data;

import java.time.OffsetDateTime;

@Data
public class Reminder {
    private String text;
    private OffsetDateTime time;
    private Boolean done;

    public Reminder done(Boolean done) {
        this.done = done;
        return this;
    }

    public Reminder text(String text) {
        this.text = text;
        return this;
    }

    public Reminder time(OffsetDateTime time) {
        this.time = time;
        return this;
    }
}