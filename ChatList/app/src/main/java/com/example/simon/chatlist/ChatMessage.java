package com.example.simon.chatlist;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by Simon on 22.01.2018.
 */

public class ChatMessage {
    private String text;
    private String user;
    private long time;

    public String getText() {
        return text;
    }

    public String getUser() {
        return user;
    }

    public long getTime() {
        return time;
    }

    public ChatMessage(String text, String username, long unixtime) {
        this.text = text;
        this.user = username;
        this.time = unixtime;
    }

    @Override
    public String toString() {
        return this.user + ": " + this.text + " (" +  formatTime() + ")";
    }

    private String formatTime() {
        Date time = new Date(this.time);
        return "" + time.toString();
    }
}
