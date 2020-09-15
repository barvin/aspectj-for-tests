package com.example.sut.entities;

import java.time.OffsetDateTime;
import java.util.Objects;

public class Reminder {
    private Integer id;
    private String text;
    private OffsetDateTime time;
    private boolean done;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public OffsetDateTime getTime() {
        return time;
    }

    public void setTime(OffsetDateTime time) {
        this.time = time;
    }

    public boolean isDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Reminder reminder = (Reminder) o;
        return done == reminder.done &&
              Objects.equals(text, reminder.text) &&
              Objects.equals(time, reminder.time);
    }

    @Override
    public int hashCode() {
        return Objects.hash(text, time, done);
    }

    @Override
    public String toString() {
        return "Reminder{" +
              "id=" + id +
              ", text='" + text + '\'' +
              ", time=" + time +
              ", done=" + done +
              '}';
    }
}
