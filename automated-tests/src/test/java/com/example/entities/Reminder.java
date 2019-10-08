package com.example.entities;

import java.time.OffsetDateTime;

public class Reminder {
    private String text;
    private OffsetDateTime time;
    private Boolean done;

    public Reminder done(Boolean done) {
        this.done = done;
        return this;
    }

    public Boolean isDone() {
        return done;
    }

    public void setDone(Boolean done) {
        this.done = done;
    }

    public Reminder text(String text) {
        this.text = text;
        return this;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Reminder time(OffsetDateTime time) {
        this.time = time;
        return this;
    }

    public OffsetDateTime getTime() {
        return time;
    }

    public void setTime(OffsetDateTime time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "Reminder{" +
                "text='" + text + '\'' +
                ", time=" + time +
                ", done=" + done +
                '}';
    }

}

