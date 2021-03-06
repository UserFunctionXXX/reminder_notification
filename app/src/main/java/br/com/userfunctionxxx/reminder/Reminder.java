package br.com.userfunctionxxx.reminder;

import androidx.annotation.NonNull;

import java.io.Serializable;

public class Reminder implements Serializable {
    private int id;
    private String title;
    private boolean done;
    private long when;

    public Reminder(int id, String title, long when) {
        this(title, when);
        this.id = id;
    }

    public Reminder(String title, long when) {
        this.title = title;
        this.when = when;
    }

    public String getTitle() {
        return title;
    }

    public Reminder setTitle(String title) {
        this.title = title;
        return this;
    }

    public boolean isDone() {
        return done;
    }

    public Reminder setDone(boolean done) {
        this.done = done;
        return this;
    }

    public Long getWhen() {
        return when;
    }

    public Reminder setWhen(long when) {
        this.when = when;
        return this;
    }

    public int getId() {
        return id;
    }
}
