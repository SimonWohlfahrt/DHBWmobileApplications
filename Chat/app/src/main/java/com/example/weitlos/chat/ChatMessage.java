package com.example.weitlos.chat;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by D064042 on 22.12.2017.
 */

public class ChatMessage implements Parcelable {

    public String getMessage() {
        return message;
    }

    public String getUser() {
        return user;
    }

    private String message;
    private String user;

    public ChatMessage(String user, String message) {
        this.user = user;
        this.message = message;
    }

    protected ChatMessage(Parcel in) {
        message = in.readString();
        user = in.readString();
    }

    public static final Creator<ChatMessage> CREATOR = new Creator<ChatMessage>() {
        @Override
        public ChatMessage createFromParcel(Parcel in) {
            return new ChatMessage(in);
        }

        @Override
        public ChatMessage[] newArray(int size) {
            return new ChatMessage[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(message);
        parcel.writeString(user);
    }
}
